package business;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	// id ��������
	private int product_id;
	// id ���������
	private int user_id;
	// ��� ��������
	private String name;
	// ���� ��������
	private int price;
	// ��� ��������
	private String type;
	// ��������
	private String description;
	// ���������� � ������������
	private User user;
	//���� �� �����������
	private String imagePath;

	// ������ JavaBean �����������
	public Product() {
	}

	// ���� ����������� ������������ ��� ������ select, ����� ��������� �������
	// ������ ��������, ����,��� ������ � ��� �����������, � �� id ������� �� �������� ����� ������
	public Product(int product_id, String name, int price, String type, String imagePath) {
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.imagePath=imagePath;
	}

	// ��� ����������� ������������ � ������ selectProduct, ����� ��������� �������
	// ��� ���������� � ������ � ���������� ���������� � ���������
	public Product(int product_id, String name, int price, String type, String description, String imagePath, User user) {
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.description = description;
		this.user = user;
		this.imagePath=imagePath;
	}

	// ���� ����������� ������������ ��� ������ insert
	public Product(int user_id,String name, int price, String type, String description, String imagePath) {
		this.user_id=user_id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.description = description;
		this.imagePath=imagePath;
	}
	//������ ����������� ��� ������ edit
	public Product(int product_id, int user_id,String name, int price, String type, String description, String imagePath) {
		this.product_id=product_id;
		this.user_id=user_id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.description = description;
		this.imagePath=imagePath;
	}
	// ������� � �������

	

	// product_id
	public void setId(int product_id) {
		this.product_id = product_id;
	}

	public int getId() {
		return product_id;
	}

	// user_id
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public int getUserId() {
		return user_id;
	}

	// name
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	// price
	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	// type
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	// description
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	// user
	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	//imagePath
	public void setImagePath(String imagePath) {
		this.imagePath=imagePath;
	}
	public String getImagePath() {
		return imagePath;
	}

}
