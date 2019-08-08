
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.ProductDB;
import business.User;
import business.UserDB;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��� get-������� ������� ����� ��� ���������� ������ � ������������
		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��������� ������� �� �������
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String telephone = request.getParameter("telephone");
		String login = request.getParameter("login");
		String password = request.getParameter("password");		
		
		try {
			//�������� ������������ �� ������ �� �����
			User user=new User(firstName, lastName, telephone, login, password);
			// ��������� ������������ � ���� � ��������� id ������������
			int user_id=UserDB.insert(user);
			//�������� ������������ � user_id, ����� ���������� ���� ��� �����, � � ������ � �������� ��� ������ �����������
			user=new User(user_id, firstName, lastName, telephone, login, password);
			// �������� ������
	        HttpSession session = request.getSession();
			//��������� ������������ � ��������, ����� �� �������� � �������� ���������� ��� ������ ������� � ������ ������
			session.setAttribute("user", user);			
			// ��������������� �� �������� �������
			getServletContext().getRequestDispatcher("/profile").forward(request, response);
		}
		// � ������ ������
		catch (Exception e) {
			// ���������� ��������� � ��������
			request.setAttribute("message", "This login/password is already used!");
			// ��������� ������� �� �������� ������
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

		}
	}

}
