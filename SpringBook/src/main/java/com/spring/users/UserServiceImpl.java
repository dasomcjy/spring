package com.spring.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")	// Spring Framework �� Bean ��� : userService
public class UserServiceImpl implements UserService {

	// ����Ͻ� ������ ó���ϴ� Ŭ���� 
	
						// @Autowired ����״ϼ��� Spring Framework�� ���� Bean�� 
	@Autowired						// Ÿ���� ã�Ƽ� ���� (DI)
	UserDAO userDAO ;
	
	@Override
	public UserDTO getUser(UserDTO dto) {
		
		System.out.println(dto.getId());
		System.out.println(dto.getPasssword());
		
		return userDAO.getUser(dto); 
		
		
	}

}