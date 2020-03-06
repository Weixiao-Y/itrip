package cn.weixiao.itrip;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailDemo {
	public static void main(String[] args) {
		// 用于发送邮件
		JavaMailSender mailSender = new JavaMailSenderImpl();
		// 创建邮件信息对象
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		// 收件人
		mailMessage.setTo("eryuerucao@163.com");
		// 发件人
		mailMessage.setFrom("13032910105@163.com");
		// 邮件主题
		mailMessage.setSubject("哈哈哈哈--------");
		// 设置邮件内容
		mailMessage.setText("啦啦啦啦啦-----");

		// 设置邮箱信息
		mailSender.send(mailMessage);
	}
}
