package com.ssafy.home.model.service;

import com.ssafy.home.dto.Member;

public interface EmailService {

	public void sendMail(Member member) throws Exception;
}
