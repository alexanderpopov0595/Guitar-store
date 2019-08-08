package business;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	// id продукта
	private int product_id;
	// id владельца
	private int user_id;
	// Имя продукта
	private String name;
	// Цена продукта
	private int price;
	// Тип продукта
	private String type;
	// Описание
	private String description;
	// Информация о пользователе
	private User user;
	//Путь до изображения
	private String imagePath;

	// Пустой JavaBean конструктор
	public Product() {
	}

	// Этот конструктор используется для метода select, когда требуется вывести
	// только название, цену,тип товара и его изображение, а по id перейти на страницу этого товара
	public Product(int product_id, String name, int price, String type, String imagePath) {
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.imagePath=imagePath;
	}

	// Это конструктор используется в методе selectProduct, когда требуется вывести
	// всю информацию о товаре и контактную информацию о владельце
	public Product(int product_id, String name, int price, String type, String description, String imagePath, User user) {
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.description = description;
		this.user = user;
		this.imagePath=imagePath;
	}

	// Этот конструктор используется для метода insert
	public Product(int user_id,String name, int price, String type, String description, String imagePath) {
		this.user_id=user_id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.description = description;
		this.imagePath=imagePath;
	}
	//Полный конструктор для метода edit
	public Product(int product_id, int user_id,String name, int price, String type, String description, String imagePath) {
		this.product_id=product_id;
		this.user_id=user_id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.description = description;
		this.imagePath=imagePath;
	}
	// Геттеры и сеттеры

	

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
