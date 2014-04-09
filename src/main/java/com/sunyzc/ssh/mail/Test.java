package com.sunyzc.ssh.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Test {
	/** 发送邮件的最少代码Demo */
	public static void main(String[] args) throws MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.163.com");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("sunyzc", "password...");
			}
		});
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("sunyzc@163.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("sunyzc@163.com"));
		message.setSubject("标题");
		message.setText("邮件正文......");
		Transport.send(message);
	}
}
