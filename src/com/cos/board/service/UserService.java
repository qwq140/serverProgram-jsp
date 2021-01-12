package com.cos.board.service;

import com.cos.board.domain.user.UserDao;
import com.cos.board.domain.user.dto.JoinReqDto;

public class UserService {
	
	private UserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}
	public int 회원가입(JoinReqDto dto) {
		return userDao.save(dto);
	}
}
