package business;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Configuration.ConfigParser;

public class ProductDB {
	// Инициализация параметров подключения
	private static String url = "jdbc:mysql://localhost/guitarstore?serverTimezone=Europe/Moscow&useSSL=false";
	private static String username = "root";
	private static String userpassword = "WinterFest1986";

	// Методы для работы с базой данных

	// 1. Метод возвращает список всего доступного товара

	public static ArrayList<Product> select() {
		// Список всего товара
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// Уставливаем подключение к базе даннных
			try (Connection conn = DriverManager.getConnection(url, username, userpassword)) {
				// Запрос на извлечение всего товара из базы данных
				String sql = "SELECT * FROM products";
				// Создание запроса
				Statement statement = conn.createStatement();
				// Выполнить запрос и получить данные
				ResultSet resultSet = statement.executeQuery(sql);
				// Пройтись по списку всех товаров, создать объект каждого товара и добавить его
				// в список всех товаров
				while (resultSet.next()) {
					// Извлечение параметров из базы данных
					int product_id = resultSet.getInt("product_id");
					String name = resultSet.getString("name");
					int price = resultSet.getInt("price");
					String type = resultSet.getString("type");
					String imagePath = resultSet.getString("imagePath");
					// Создание объекта и запись значений в его поля
					Product product = new Product(product_id, name, price, type, imagePath);
					// Добавление продукта в список
					products.add(product);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		// Вернуть сформированный список
		return products;
	}

	// 2. Метод возвращает товар по его id и информацию о владельце
	public static Product selectProduct(int product_id) {
		// Создаем продукт
		Product product = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// Уставливаем подключение к базе даннных
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// Запрос на извлечение товара с таким id и информации о пользователе, который
				// разместил этот товар
				String sql = "SELECT * FROM users, products  WHERE users.user_id=products.user_id  AND products.product_id=? ";

				// Создание запроса
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					// Подстановка значения
					preparedStatement.setInt(1, product_id);
					// Выполнение запроса
					ResultSet resultSet = preparedStatement.executeQuery();
					// Перебор возвращенных значений
					if (resultSet.next()) {
						// Запись значений в поля пользователя
						int user_id = resultSet.getInt("user_id");
						String firstName = resultSet.getString("firstName");
						String lastName = resultSet.getString("lastName");
						String telephone = resultSet.getString("telephone");

						// Создание пользователя
						User user = new User(user_id, firstName, lastName, telephone);

						// Запись значений в поля товара
						String name = resultSet.getString("name");
						int price = resultSet.getInt("price");
						String type = resultSet.getString("type");
						String description = resultSet.getString("description");
						String imagePath = resultSet.getString("imagePath");
						// Запись значений в товар
						product = new Product(product_id, name, price, type, description, imagePath, user);

					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

		// Вернуть товар
		return product;
	}

	// 3. Метод возвращает список товара конкретного пользователя
	public static ArrayList<Product> select(int user_id) {
		// Создание списка товара
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// Устанавливаем подключение к базе данных
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// Запрос на извлечение списка товара пользователя с таким id
				String sql = "SELECT * FROM products WHERE products.user_id=?";
				// Создание запроса
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					// Подстановка значений
					preparedStatement.setInt(1, user_id);
					// Выполнения запроса
					ResultSet resultSet = preparedStatement.executeQuery();
					// Перебор полученных результатов
					while (resultSet.next()) {

						// Извлечение параметров из базы данных
						int product_id = resultSet.getInt("product_id");
						String name = resultSet.getString("name");
						int price = resultSet.getInt("price");
						String type = resultSet.getString("type");
						String imagePath = resultSet.getString("imagePath");
						// Создание объекта и запись значений в его поля
						Product product = new Product(product_id, name, price, type, imagePath);
						// Добавление продукта в список
						products.add(product);
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return products;
	}

	// 4. Метод добавляет товар в базу данных от соответствующего пользователя
	public static int insert(Product product) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// Подключение к базе данных
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// Запрос на добавление товара пользователем
				String sql = "INSERT INTO products (user_id, name, price, type, description, imagePath) VALUES (?,?,?,?,?,?)";
				/*
				 * Этот запрос принимает не только sql-запрос, но и generated_keys, чтобы узнать
				 * id этого вставленного товара, Чтобы затем по этому id перейти на страницу
				 * созданного товара.
				 */
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS)) {
					// Подстановка значений
					preparedStatement.setInt(1, product.getUserId());
					preparedStatement.setString(2, product.getName());
					preparedStatement.setInt(3, product.getPrice());
					preparedStatement.setString(4, product.getType());
					preparedStatement.setString(5, product.getDescription());
					preparedStatement.setString(6, product.getImagePath());
					// Выполнить запрос
					preparedStatement.execute();

					// Определение id добавленного товара, чтобы затем по id перейти к странице
					// этого товара
					ResultSet resultSet = preparedStatement.getGeneratedKeys();
					if (resultSet.next()) {
						return resultSet.getInt(1);
					}

				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	// 5. Метод вносит изменения в описание товара
	public static void update(Product product) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// Устанавливаем подключение к базе данных
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// Запрос на изменение товара с таким id
				String sql = "UPDATE products SET name=?, price=?, type=?, description=?, imagePath=? WHERE product_id=? ";
				// Создание запроса
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					// Подстановка значений
					preparedStatement.setString(1, product.getName());
					preparedStatement.setInt(2, product.getPrice());
					preparedStatement.setString(3, product.getType());
					preparedStatement.setString(4, product.getDescription());
					preparedStatement.setString(5, product.getImagePath());
					preparedStatement.setInt(6, product.getId());

					// Выполнения запроса
					preparedStatement.execute();

				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	// 6. Метод удаляет товар по его id
	public static void delete(int product_id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// Устанавливаем подключение к базе данных
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// Запрос на изменение товара с таким id
				String sql = "DELETE FROM products WHERE product_id = ?";
				// Создание запроса
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					// Подстановка значений
					preparedStatement.setInt(1, product_id);

					// Выполнения запроса
					preparedStatement.executeUpdate();

				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	// 7. Метод возвращает последний product_id из таблицы (используется для
	// генерации имени товара)
	public static int getLastId() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// Устанавливаем подключение к базе данных
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// Запрос на последний id
				String sql = "SELECT MAX(product_id) FROM products";
				// Создание запроса
				Statement statement = connection.createStatement();
				// Выполнить запрос и получить данные
				ResultSet resultSet = statement.executeQuery(sql);
				if (resultSet.next()) {
					return resultSet.getInt(1);
				}

			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return 0;
	}

	// 8. Метод ищет товар по параметрам
	public static ArrayList<Product> selectSearch(String name, int price, String type, String order) {
		// Создание списка товара
		ArrayList<Product> products = new ArrayList<Product>();
		//Проверка, какие из параметров были переданы
		//Если они равны null, то заменить их на любое значение
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// Устанавливаем подключение к базе данных
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// Запрос на извлечение списка товара пользователя с таким id
				String sql = "SELECT * FROM products WHERE name LIKE ? AND price LIKE ? AND type LIKE ? ORDER BY price";
				//Если нужен порядок сортировки по уменьшению цены
				if(order.equals("higer")) {
					sql = "SELECT * FROM products WHERE name LIKE ? AND price LIKE ? AND type LIKE ? ORDER BY price DESC";
				}
				// Создание запроса
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					// Подстановка значений
					if (name==null) name="%";				
					preparedStatement.setString(1, name);
					if (price==0) {
						preparedStatement.setString(2, "%");
					}
					else {
						preparedStatement.setInt(2, price);
					}
					if (type==null) type="%";					
					preparedStatement.setString(3, type);
					// Выполнения запроса
					ResultSet resultSet = preparedStatement.executeQuery();
					// Перебор полученных результатов
					while (resultSet.next()) {

						// Извлечение параметров из базы данных
						int product_id = resultSet.getInt("product_id");
						name = resultSet.getString("name");
						price = resultSet.getInt("price");
						type = resultSet.getString("type");
						String imagePath = resultSet.getString("imagePath");
						// Создание объекта и запись значений в его поля
						Product product = new Product(product_id, name, price, type, imagePath);
						// Добавление продукта в список
						products.add(product);
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return products;
	}
	
}
