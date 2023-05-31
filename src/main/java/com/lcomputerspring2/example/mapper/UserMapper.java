package com.lcomputerspring2.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;

import com.lcomputerspring2.example.domain.User;

@Mapper
public interface UserMapper {
	//유저 읽기 
	public User readUser(String username);
	
	//유저 생성 
	public void createUser(User user);
	
	//권한 읽기 
	public List<GrantedAuthority> readAuthorities(String username);
	
	//권한 생성 
	public void createAuthority(User user);
}
