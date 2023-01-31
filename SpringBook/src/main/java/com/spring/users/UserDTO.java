package com.spring.users;

public class UserDTO {
	
	//DTO (VO) : client 에서 넘긴 변수의 값을 담아서 DAO에 전달 
	//			DAO에서 DB를 Select한 값을 DTO에 담아서 클라이언트에게 전달 
	
	private String id;
	private String passsword;
	private String name;
	private String role;
	
	//기본 생성자 
	public UserDTO() {}
	
	//GEtter /Setter
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasssword() {
		return passsword;
	}

	public void setPasssword(String passsword) {
		this.passsword = passsword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	//toString() 메소드 재정의 : 객체 자체를 출력시 필드의 값을 출력
	
	
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", passsword=" + passsword + ", name=" + name + ", role=" + role + "]";
	}
	

	
	

}
