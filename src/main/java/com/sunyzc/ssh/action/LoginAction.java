package com.sunyzc.ssh.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sunyzc.ssh.entity.Authority;
import com.sunyzc.ssh.entity.Resource;
import com.sunyzc.ssh.entity.Role;
import com.sunyzc.ssh.entity.User;
import com.sunyzc.ssh.service.UserService;
import com.sunyzc.ssh.util.SysConstants;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = -7429590793003952492L;
	private static final String HOME = "home";
	private String loginName;
	private String password;
	private Boolean rememberMe;
	private String verificationCode;
	private InputStream inputStream;
	private UserService userService;

	public String loginUI() throws Exception {
		return LOGIN;
	}

	public String login() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String code = (String) session.get(SysConstants.SESSION_SECURITY_CODE);
		if (code != null && !code.equalsIgnoreCase(verificationCode)) {
			addActionError("Verification code is incorrect");
			return LOGIN;
		}
		User user = userService.getUserByLoginName(loginName);
		if (user == null) {
			addActionError("Login name does not exist");
			return LOGIN;
		} else if (password == null || !password.equals(user.getPassword())) {
			addActionError("Password is incorrect");
			return LOGIN;
		}
		Set<String> allGrantedActionPaths = new HashSet<String>();
		for (Role role : user.getRoles())
			for (Authority authority : role.getAuthorities())
				for (Resource resource : authority.getResources())
					if (resource.getActionPath() != null)
						allGrantedActionPaths.add(resource.getActionPath());
		session.put(SysConstants.USER_IN_SESSION, user);
		session.put(SysConstants.ALL_AUTHORISED_ACTION_PATHS, allGrantedActionPaths);
		if (rememberMe) {
			// 记录cookie
			// TODO 需要对登陆信息加密，必要的话还要记录UserAgent等防盗
			// Cookie cookie = new Cookie(SysConstants.COOKIE_FOR_LOGIN_INFO, model.getLoginName() + "|" + model.getPassword());
			// cookie.setMaxAge(14 * 24 * 60 * 60);// 设置Cookie的过期时间为2周
			// cookie.setPath(ServletActionContext.getServletContext().getContextPath());
			// ServletActionContext.getResponse().addCookie(cookie);
		}
		return HOME;
	}

	public String logout() throws Exception {
		// 销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		// 清除cookie
		Cookie cookie = new Cookie(SysConstants.COOKIE_FOR_LOGIN_INFO, null);
		cookie.setMaxAge(0);
		ServletActionContext.getResponse().addCookie(cookie);
		return LOGIN;
	}

	public String getCode() throws Exception {
		int width = 90; // 验证码图片的宽度
		int height = 30; // 验证码图片的高度
		int codeCount = 4; // 验证码字符个数
		int fontHeight = height - 10; // 字体高度
		int codeX = (width - 4) / (codeCount + 1); // 第一个字符的x轴值，因为后面的字符坐标依次递增，所以它们的x轴值是codeX的倍数
		int codeY = height - 4; // codeY ,验证字符的y轴值，因为并行所以值一样
		char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
				'7', '8', '9' }; // codeSequence 表示字符允许出现的序列值
		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D gd = buffImg.createGraphics();
		// 创建一个随机数生成器类
		Random random = new Random();
		// 将图像填充为白色
		gd.setColor(Color.LIGHT_GRAY);
		gd.fillRect(0, 0, width, height);
		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
		// 设置字体。
		gd.setFont(font);
		// 画边框。
		gd.setColor(Color.BLACK);
		gd.drawRect(0, 0, width - 1, height - 1);
		// 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到。
		gd.setColor(Color.gray);
		for (int i = 0; i < 16; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}
		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;
		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字。
			String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			// 用随机产生的颜色将验证码绘制到图像中。
			gd.setColor(new Color(red, green, blue));
			gd.drawString(strRand, (i + 1) * codeX, codeY);
			// 将产生的四个随机数组合在一起。
			randomCode.append(strRand);
		}
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
		ImageIO.write(buffImg, "JPEG", imageOut);
		// ImageIO.write((RenderedImage) VerificationCode.generatorRandomCode(), "JPEG", imageOut);
		imageOut.close();
		inputStream = new ByteArrayInputStream(output.toByteArray());
		ActionContext.getContext().getSession().put(SysConstants.SESSION_SECURITY_CODE, randomCode.toString());
		// inputStream = new FileInputStream(new File("C:/Users/sunyzc/SkyDrive/About_Me/Avatar/卡通脸谱/小生(200x200).jpg"));
		return "image";
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
