package business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDB {
	// ������������� ���������� �����������
	private static String url = "jdbc:mysql://localhost/guitarstore?serverTimezone=Europe/Moscow&useSSL=false";
	private static String username = "root";
	private static String userpassword = "WinterFest1986";

	// ������ ��� ������ � ����� ������

	// 1. ����� ������������ ������������ � ���� ������
	public static int insert(User user) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// ����������� � ���� ������
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// ������ �� ���������� ������ �������������
				String sql = "INSERT INTO users (firstName, lastName, telephone, login, password) VALUES (?,?,?,?,?)";
				/*
				 * ���� ������ ��������� �� ������ sql-������, �� � generated_keys, ����� ������
				 * id ����� ������������ ������������, ����� ����� �� ����� id ������� ��
				 * �������� ���������� ������������.
				 */
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS)) {
					// ����������� ��������
					preparedStatement.setString(1, user.getFirstName());
					preparedStatement.setString(2, user.getLastName());
					preparedStatement.setString(3, user.getTelephone());
					preparedStatement.setString(4, user.getLogin());
					preparedStatement.setString(5, user.getPassword());

					// ��������� ������
					preparedStatement.execute();

					// ����������� id ������������ ������������, ����� ����� �� id ������� �
					// �������� ����� ������������
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

	// 2. ����� ���������� ������������� � ����� ������� � �������, ���� �� ���� �
	// ���� ������
	public static User authorization(String login, String password) {
		// �������� ������������
		User user = null;
		// ����������� ����������� � ���� �������
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// ������ �� ����� � ������� ������������ � ����� ������� � �������
				String sql = "SELECT  * FROM users WHERE login=? AND password=?";
				// ����������� �������� � sql-������
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					// ����������� �������� � ������
					preparedStatement.setString(1, login);
					preparedStatement.setString(2, password);
					// ���������� ������� � ��������� �����������
					ResultSet resultSet = preparedStatement.executeQuery();
					// ��������, ������������� �� ������������ ����� � ������ ���������
					while (resultSet.next()) {
						// ������ ����� ������������
						int user_id = resultSet.getInt("user_id");
						String firstName = resultSet.getString("firstName");
						String lastName = resultSet.getString("lastName");
						String telephone = resultSet.getString("telephone");
						
						// �������� ������������
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

	// 3. ����� ������ ��������� ������ ������������ � ���� ������
	public static void update(User user) {
		
		// ����������� ����������� � ���� �������
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// ������ �� ����� � ������� ������������ � ����� ������� � �������
				String sql = "UPDATE users SET firstName=?, lastName=?, telephone=?, login=?, password=? WHERE user_id=?";
				// ����������� �������� � sql-������
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					// ����������� �������� � ������
					preparedStatement.setString(1, user.getFirstName());
					preparedStatement.setString(2, user.getLastName());
					preparedStatement.setString(3, user.getTelephone());
					preparedStatement.setString(4, user.getLogin());
					preparedStatement.setString(5, user.getPassword());
					preparedStatement.setInt(6, user.getId());
					// ���������� ������� �
					preparedStatement.executeUpdate();					
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
	}
	//4. ����� ������� ������������ �� ��� id � ��� ��� ������
		public static void delete(int user_id) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
				// ������������� ����������� � ���� ������
				try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
					// ������ �� �������� ������������ � ����� id
					String sql = "DELETE FROM users  WHERE user_id=?";
					
					// �������� �������
					try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
						// ����������� ��������
						preparedStatement.setInt(1, user_id);						
						// ���������� �������
						preparedStatement.executeUpdate();	
						}
					//������ �� �������� ��� �������
					sql="DELETE FROM products WHERE user_id=?";
					// �������� �������
					try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
						// ����������� ��������
						preparedStatement.setInt(1, user_id);						
						// ���������� �������
						preparedStatement.executeUpdate();	
						}
					
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	
}
