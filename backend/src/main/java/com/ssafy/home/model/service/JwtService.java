package com.ssafy.home.model.service;

public interface JwtService {

	<T> String createToken(String key, T data, String subject);
}
