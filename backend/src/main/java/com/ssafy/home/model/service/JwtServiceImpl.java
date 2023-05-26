package com.ssafy.home.model.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService {

	final static int EXPIRE_MINUTES = 1;

	final static String SECRET_KEY = "ssafy";

	@Override
	public <T> String createToken(String key, T data, String subject) {

		try {
			int time = 0;
			if (subject == "access-token") {
				time = 1000 * 60 * EXPIRE_MINUTES;
			} else if (subject == "refresh-token") {
				time = 1000 * 60 * 60 * 24 * 7 * 2 * EXPIRE_MINUTES;
			} else {
				log.debug("another token!!!!!!!!!!");
			}
			String token = Jwts.builder().setHeaderParam("typ", "JWT").setHeaderParam("alg", "HS256").claim(key, data)
					.setExpiration(new Date(System.currentTimeMillis() + time))
					.setSubject(subject).signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes("UTF-8")).compact();

			log.debug("발급된 {} 토큰: {}", subject, token);
			return token;
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("토큰 생성 실패!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return null;
		}

	}

}
