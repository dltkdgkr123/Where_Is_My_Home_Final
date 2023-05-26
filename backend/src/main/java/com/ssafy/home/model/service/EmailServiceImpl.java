package com.ssafy.home.model.service;


import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ssafy.home.dto.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
	private final JavaMailSender mailSender;
	private static final String ADMIN_ADDRESS = "ssafytest3542@naver.com";

	@Async
	public void sendMail(Member member) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		message.addRecipients(Message.RecipientType.TO, member.getEmail());
		message.setSubject("구해줘 홈즈! 비밀번호 찾기 안내");
		String text = "[" + member.getUserid() + "]" + "님의 비밀번호는" + " [" + member.getUserpwd() + "]입니다.";
		message.setText(text, "utf-8");
		message.setFrom(new InternetAddress(ADMIN_ADDRESS, "구해줘홈즈"));
		mailSender.send(message);
	}

}