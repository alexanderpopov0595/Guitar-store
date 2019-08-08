package business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDB {
	// Инициализация параметров подключения
	private static String url = "jdbc:mysql://localhost/guitarstore?serverTimezone=Europe/Moscow&useSSL=false";
	private static String username = "root";
	private static String userpassword = "WinterFest1986";

	// Методы для работы с базой данных

	// 1. Метод регистрирует пользователя в базе данных
	public static int insert(User user) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// Подключение к базе данных
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// Запрос на добавление товара пользователем
				String sql = "INSERT INTO users (firstName, lastName, telephone, login, password) VALUES (?,?,?,?,?)";
				/*
				 * Этот запрос принимает не только sql-запрос, но и generated_keys, чтобы узнать
				 * id этого вставленного пользователя, Чтобы затем по этому id перейти на
				 * страницу созданного пользователя.
				 */
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS)) {
					// Подстановка значений
					preparedStatement.setString(1, user.getFirstName());
					preparedStatement.setString(2, user.getLastName());
					preparedStatement.setString(3, user.getTelephone());
					preparedStatement.setString(4, user.getLogin());
					preparedStatement.setString(5, user.getPassword());

					// Выполнить запрос
					preparedStatement.execute();

					// Определение id добавленного пользователя, чтобы затем по id перейти к
					// странице этого пользователя
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

	// 2. Метод возвращает пользвователя с таким логином и паролем, если он есть в
	// базе данных
	public static User authorization(String login, String password) {
		// Создание пользователя
		User user = null;
		// Уставливаем подключение к базе даннных
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// Запрос на поиск в таблице пользователя с таким логином и паролем
				String sql = "SELECT  * FROM users WHERE login=? AND password=?";
				// Подстановка значения в sql-запрос
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					// Подстановка значений в запрос
					preparedStatement.setString(1, login);
					preparedStatement.setString(2, password);
					// Выполнение запроса и получение результатов
					ResultSet resultSet = preparedStatement.executeQuery();
					// Проверка, соответствуют ли возвращенные логин и пароль введенным
					while (resultSet.next()) {
						// Запись полей пользователя
						int user_id = resultSet.getInt("user_id");
						String firstName = resultSet.getString("firstName");
						String lastName = resultSet.getString("lastName");
						String telephone = resultSet.getString("telephone");
						
						// Создание пользователя
						user = new User(user_id, firstName, lastName, telephone, login, password);
						return user;
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return user;
	}

	// 3. Метод вносит изменения данных пользователя в базу данных
	public static void update(User user) {
		
		// Уставливаем подключение к базе даннных
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// Запрос на поиск в таблице пользователя с таким логином и паролем
				String sql = "UPDATE users SET firstName=?, lastName=?, telephone=?, login=?, password=? WHERE user_id=?";
				// Подстановка значения в sql-запрос
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					// Подстановка значений в запрос
					preparedStatement.setString(1, user.getFirstName());
					preparedStatement.setString(2, user.getLastName());
					preparedStatement.setString(3, user.getTelephone());
					preparedStatement.setString(4, user.getLogin());
					preparedStatement.setString(5, user.getPassword());
					preparedStatement.setInt(6, user.getId());
					// Выполнение запроса в
					preparedStatement.executeUpdate();					
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
	}
	//4. Метод удаляет пользователя по его id и все его товары
		public static void delete(int user_id) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
				// Устанавливаем подключение к базе данных
				try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
					// Запрос на удаление пользователя с таким id
					String sql = "DELETE FROM users  WHERE user_id=?";
					
					// Создание запроса
					try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
						// Подстановка значений
						preparedStatement.setInt(1, user_id);						
						// Выполнения запроса
						preparedStatement.executeUpdate();	
						}
					//Запрос на удаление его товаров
					sql="DELETE FROM products WHERE user_id=?";
					// Создание запроса
					try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
						// Подстановка значений
						preparedStatement.setInt(1, user_id);						
						// Выполнения запроса
						preparedStatement.executeUpdate();	
						}
					
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	
}
