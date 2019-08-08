package business;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	//id ������������
	private int user_id;
	//��� ������������
	private String firstName;
	//������� ������������
	private String lastName;
	//�������
	private String telephone;
	//�����
	private String login;
	//������
	private String password;
	
	//������ JavaBean �����������
	public User() {}
	
	//���� ����������� ������������  � ������ selectProduct ��� ������ ���������� � ������������
	public User(int user_id, String firstName, String lastName, String telephone) {
		this.user_id=user_id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.telephone=telephone;	
	}
	
	//���� ����������� ������������ � ������ insert
	public User (String firstName, String lastName, String telephone, String login, String password) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.telephone=telephone;
		this.login=login;
		this.password=password;
	}
	//���� ����������� ������������ ��� ����������� ���������� � ������������ � ������ ��������
	public User (int user_id, String firstName, String lastName, String telephone, String login, String password) {
		this.user_id=user_id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.telephone=telephone;
		this.login=login;
		this.password=password;
	}
	
	
	
	//������� � �������
	
	//user_id
	public void setId(int user_id) {
		this.user_id=user_id;
	}
	public int getId() {
		return user_id;
	}
	
	//firstName
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	public String getFirstName() {
		return firstName;
	}
	
	//lastName
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}
	public String getLastName() {
		return lastName;
	}
	
	//telephone
	public void setTelephone(String telephone) {
		this.telephone=telephone;
	}
	public String getTelephone() {
		return telephone;
	}
	
	//login
	public void setLogin(String login) {
		this.login=login;
	}
	public String getLogin() {
		return login;
	}
	
	//password
	public void setPassword(String password) {
		this.password=password;
	}
	public String getPassword() {
		return password;
	}
}
