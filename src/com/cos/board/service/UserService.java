package com.cos.board.service;

import java.util.List;

import com.cos.board.domain.user.User;
import com.cos.board.domain.user.UserDao;
import com.cos.board.domain.user.dto.JoinReqDto;
import com.cos.board.domain.user.dto.LoginReqDto;

public class UserService {
	
	private UserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}
	public int 회원가입(JoinReqDto dto) {
		return userDao.save(dto);
	}
	
	public User 로그인(LoginReqDto dto) {
		return userDao.findByUsernameAndPassword(dto);
	}
	
	public List<User> 유저목록(int page){
		return userDao.findAll(page);
	}
	
	public int 삭제(int id) {
		return userDao.deleteById(id);
	}
	
	public int 유저수() {
		return userDao.count();
	}
}
