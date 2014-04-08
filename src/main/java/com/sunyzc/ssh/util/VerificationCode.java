package com.sunyzc.ssh.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerificationCode {
	private static int IMAGE_WIDTH = 80;
	private static int IMAGE_HEIGHT = 30;
	// 验证码数量
	private static int NUM = 4;
	// 干扰线数量
	private static int LINE = 4;
	private static char[] SHOWTEXT = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9' };

	/**
	 * 生成随机的验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	public void generator(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Random r = new Random();
		// 图片的内存映像
		BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
		// 获得画笔对象
		Graphics g = image.getGraphics();
		g.setColor(randomColor(200, 250));
		g.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
		g.setColor(new Color(0, 0, 0));

		// 用于存储随机生成的验证码
		StringBuffer number = new StringBuffer();

		// 绘制验证码
		for (int i = 0; i < NUM; i++) {
			g.setColor(randomColor(20, 200));
			int h = (int) ((IMAGE_HEIGHT * 50 / 100) * r.nextDouble() + (IMAGE_HEIGHT * 50 / 100));
			g.setFont(new Font(null, Font.BOLD | Font.ITALIC, h));
			String ch = String.valueOf(SHOWTEXT[r.nextInt(SHOWTEXT.length)]);
			number.append(ch);
			g.drawString(ch, i * IMAGE_WIDTH / NUM * 90 / 100, h);
		}

		// 绘制干扰线
		for (int i = 0; i <= LINE; i++) {
			g.setColor(randomColor(100, 200));
			g.drawLine(r.nextInt(IMAGE_WIDTH), r.nextInt(IMAGE_HEIGHT), r.nextInt(IMAGE_WIDTH), r.nextInt(IMAGE_HEIGHT));
		}
		request.getSession().setAttribute("imageCode", number.toString());
		System.out.println("----VerificationCode----" + number.toString());
		g.dispose();
		// 有用
		response.reset();
		ImageIO.write(image, "jpeg", response.getOutputStream());
	}

	private Color randomColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
