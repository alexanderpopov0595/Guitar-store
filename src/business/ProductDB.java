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
	// ������������� ���������� �����������
	private static String url = "jdbc:mysql://localhost/guitarstore?serverTimezone=Europe/Moscow&useSSL=false";
	private static String username = "root";
	private static String userpassword = "WinterFest1986";

	// ������ ��� ������ � ����� ������

	// 1. ����� ���������� ������ ����� ���������� ������

	public static ArrayList<Product> select() {
		// ������ ����� ������
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// ����������� ����������� � ���� �������
			try (Connection conn = DriverManager.getConnection(url, username, userpassword)) {
				// ������ �� ���������� ����� ������ �� ���� ������
				String sql = "SELECT * FROM products";
				// �������� �������
				Statement statement = conn.createStatement();
				// ��������� ������ � �������� ������
				ResultSet resultSet = statement.executeQuery(sql);
				// �������� �� ������ ���� �������, ������� ������ ������� ������ � �������� ���
				// � ������ ���� �������
				while (resultSet.next()) {
					// ���������� ���������� �� ���� ������
					int product_id = resultSet.getInt("product_id");
					String name = resultSet.getString("name");
					int price = resultSet.getInt("price");
					String type = resultSet.getString("type");
					String imagePath = resultSet.getString("imagePath");
					// �������� ������� � ������ �������� � ��� ����
					Product product = new Product(product_id, name, price, type, imagePath);
					// ���������� �������� � ������
					products.add(product);
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		// ������� �������������� ������
		return products;
	}

	// 2. ����� ���������� ����� �� ��� id � ���������� � ���������
	public static Product selectProduct(int product_id) {
		// ������� �������
		Product product = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// ����������� ����������� � ���� �������
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// ������ �� ���������� ������ � ����� id � ���������� � ������������, �������
				// ��������� ���� �����
				String sql = "SELECT * FROM users, products  WHERE users.user_id=products.user_id  AND products.product_id=? ";

				// �������� �������
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					// ����������� ��������
					preparedStatement.setInt(1, product_id);
					// ���������� �������
					ResultSet resultSet = preparedStatement.executeQuery();
					// ������� ������������ ��������
					if (resultSet.next()) {
						// ������ �������� � ���� ������������
						int user_id = resultSet.getInt("user_id");
						String firstName = resultSet.getString("firstName");
						String lastName = resultSet.getString("lastName");
						String telephone = resultSet.getString("telephone");

						// �������� ������������
						User user = new User(user_id, firstName, lastName, telephone);

						// ������ �������� � ���� ������
						String name = resultSet.getString("name");
						int price = resultSet.getInt("price");
						String type = resultSet.getString("type");
						String description = resultSet.getString("description");
						String imagePath = resultSet.getString("imagePath");
						// ������ �������� � �����
						product = new Product(product_id, name, price, type, description, imagePath, user);

					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

		// ������� �����
		return product;
	}

	// 3. ����� ���������� ������ ������ ����������� ������������
	public static ArrayList<Product> select(int user_id) {
		// �������� ������ ������
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// ������������� ����������� � ���� ������
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// ������ �� ���������� ������ ������ ������������ � ����� id
				String sql = "SELECT * FROM products WHERE products.user_id=?";
				// �������� �������
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					// ����������� ��������
					preparedStatement.setInt(1, user_id);
					// ���������� �������
					ResultSet resultSet = preparedStatement.executeQuery();
					// ������� ���������� �����������
					while (resultSet.next()) {

						// ���������� ���������� �� ���� ������
						int product_id = resultSet.getInt("product_id");
						String name = resultSet.getString("name");
						int price = resultSet.getInt("price");
						String type = resultSet.getString("type");
						String imagePath = resultSet.getString("imagePath");
						// �������� ������� � ������ �������� � ��� ����
						Product product = new Product(product_id, name, price, type, imagePath);
						// ���������� �������� � ������
						products.add(product);
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return products;
	}

	// 4. ����� ��������� ����� � ���� ������ �� ���������������� ������������
	public static int insert(Product product) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// ����������� � ���� ������
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// ������ �� ���������� ������ �������������
				String sql = "INSERT INTO products (user_id, name, price, type, description, imagePath) VALUES (?,?,?,?,?,?)";
				/*
				 * ���� ������ ��������� �� ������ sql-������, �� � generated_keys, ����� ������
				 * id ����� ������������ ������, ����� ����� �� ����� id ������� �� ��������
				 * ���������� ������.
				 */
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS)) {
					// ����������� ��������
					preparedStatement.setInt(1, product.getUserId());
					preparedStatement.setString(2, product.getName());
					preparedStatement.setInt(3, product.getPrice());
					preparedStatement.setString(4, product.getType());
					preparedStatement.setString(5, product.getDescription());
					preparedStatement.setString(6, product.getImagePath());
					// ��������� ������
					preparedStatement.execute();

					// ����������� id ������������ ������, ����� ����� �� id ������� � ��������
					// ����� ������
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

	// 5. ����� ������ ��������� � �������� ������
	public static void update(Product product) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// ������������� ����������� � ���� ������
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// ������ �� ��������� ������ � ����� id
				String sql = "UPDATE products SET name=?, price=?, type=?, description=?, imagePath=? WHERE product_id=? ";
				// �������� �������
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					// ����������� ��������
					preparedStatement.setString(1, product.getName());
					preparedStatement.setInt(2, product.getPrice());
					preparedStatement.setString(3, product.getType());
					preparedStatement.setString(4, product.getDescription());
					preparedStatement.setString(5, product.getImagePath());
					preparedStatement.setInt(6, product.getId());

					// ���������� �������
					preparedStatement.execute();

				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	// 6. ����� ������� ����� �� ��� id
	public static void delete(int product_id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// ������������� ����������� � ���� ������
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// ������ �� ��������� ������ � ����� id
				String sql = "DELETE FROM products WHERE product_id = ?";
				// �������� �������
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					// ����������� ��������
					preparedStatement.setInt(1, product_id);

					// ���������� �������
					preparedStatement.executeUpdate();

				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	// 7. ����� ���������� ��������� product_id �� ������� (������������ ���
	// ��������� ����� ������)
	public static int getLastId() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// ������������� ����������� � ���� ������
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// ������ �� ��������� id
				String sql = "SELECT MAX(product_id) FROM products";
				// �������� �������
				Statement statement = connection.createStatement();
				// ��������� ������ � �������� ������
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

	// 8. ����� ���� ����� �� ����������
	public static ArrayList<Product> selectSearch(String name, int price, String type, String order) {
		// �������� ������ ������
		ArrayList<Product> products = new ArrayList<Product>();
		//��������, ����� �� ���������� ���� ��������
		//���� ��� ����� null, �� �������� �� �� ����� ��������
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			// ������������� ����������� � ���� ������
			try (Connection connection = DriverManager.getConnection(url, username, userpassword)) {
				// ������ �� ���������� ������ ������ ������������ � ����� id
				String sql = "SELECT * FROM products WHERE name LIKE ? AND price LIKE ? AND type LIKE ? ORDER BY price";
				//���� ����� ������� ���������� �� ���������� ����
				if(order.equals("higer")) {
					sql = "SELECT * FROM products WHERE name LIKE ? AND price LIKE ? AND type LIKE ? ORDER BY price DESC";
				}
				// �������� �������
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					// ����������� ��������
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
					// ���������� �������
					ResultSet resultSet = preparedStatement.executeQuery();
					// ������� ���������� �����������
					while (resultSet.next()) {

						// ���������� ���������� �� ���� ������
						int product_id = resultSet.getInt("product_id");
						name = resultSet.getString("name");
						price = resultSet.getInt("price");
						type = resultSet.getString("type");
						String imagePath = resultSet.getString("imagePath");
						// �������� ������� � ������ �������� � ��� ����
						Product product = new Product(product_id, name, price, type, imagePath);
						// ���������� �������� � ������
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
