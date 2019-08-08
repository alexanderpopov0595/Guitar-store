package business;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	//id пользователя
	private int user_id;
	//Имя пользователя
	private String firstName;
	//Фамилия пользователя
	private String lastName;
	//Телефон
	private String telephone;
	//Логин
	private String login;
	//Пароль
	private String password;
	
	//Пустой JavaBean конструктор
	public User() {}
	
	//Этот конструктор используется  в методе selectProduct для вывода информации о пользователе
	public User(int user_id, String firstName, String lastName, String telephone) {
		this.user_id=user_id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.telephone=telephone;	
	}
	
	//Этот конструктор используется в методе insert
	public User (String firstName, String lastName, String telephone, String login, String password) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.telephone=telephone;
		this.login=login;
		this.password=password;
	}
	//Этот конструктор используется для отображения информации о пользователе в личном кабинете
	public User (int user_id, String firstName, String lastName, String telephone, String login, String password) {
		this.user_id=user_id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.telephone=telephone;
		this.login=login;
		this.password=password;
	}
	
	
	
	//Геттеры и сеттеры
	
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
