package com.spring.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")	// Spring Framework 에 Bean 등록 : userService
public class UserServiceImpl implements UserService {

	// 비즈니스 로직을 처리하는 클래스 
	
						// @Autowired 어노테니션이 Spring Framework에 생성 Bean의 
	@Autowired						// 타입을 찾아서 주입 (DI)
	UserDAO userDAO ;
	
	@Override
	public UserDTO getUser(UserDTO dto) {
		
		System.out.println(dto.getId());
		System.out.println(dto.getPasssword());
		
		return userDAO.getUser(dto); 
		
		
	}

}
