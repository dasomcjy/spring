package com.spring.users;

public class UserDTO {
	
	//DTO (VO) : client ���� �ѱ� ������ ���� ��Ƽ� DAO�� ���� 
	//			DAO���� DB�� Select�� ���� DTO�� ��Ƽ� Ŭ���̾�Ʈ���� ���� 
	
	private String id;
	private String passsword;
	private String name;
	private String role;
	
	//�⺻ ������ 
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

	//toString() �޼ҵ� ������ : ��ü ��ü�� ��½� �ʵ��� ���� ���
	
	
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", passsword=" + passsword + ", name=" + name + ", role=" + role + "]";
	}
	

	
	

}
