
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.User;
import business.UserDB;
@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {
	// ��� get-������� ������� �������� �����������
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���� ��� ���������������
		// ��������� ������
		HttpSession session = request.getSession();
		// ��������� �������� ������������
		User user = (User) session.getAttribute("user");
		// ���� �� ����
		if (user != null) {
			// ��������������� �� �������� �������
			getServletContext().getRequestDispatcher("/profile").forward(request, response);

		}
		// ���� ��� ���
		else {
			getServletContext().getRequestDispatcher("/authorization.jsp").forward(request, response);
		}

	}

	// ��� �������� ����������� ����� �������� � post-�����
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ��������� ������ � ������
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		//������� ������� ������������ � ������ ������� � ������� �� ���� ������
		User user=UserDB.authorization(login, password);
		// ���� ������������ � ����� ������� � ������� ���� � ���� ������, �� ����� ������ �� nul;
		if (UserDB.authorization(login, password) != null) {
			// �������� ������
			HttpSession session = request.getSession();
			// ��������� ������������ � ��������, ����� �� �������� � �������� ����������
			// ��� ������ ������� � ������ ������
			session.setAttribute("user", user);
			// ��������������� �� �������� �������
			getServletContext().getRequestDispatcher("/profile").forward(request, response);

		}
		// ���� ������������ � ����� ������� � ������� ��� � ���� ������
		else {
			// ��������� �� ������ ����� ������/������
			// ��������� ��������� � ��������
			request.setAttribute("message", "Incorrect login/password");
			// � ������ ������� ���� ������������� ������� jsp ��������
			getServletContext().getRequestDispatcher("/authorization.jsp").forward(request, response);
		}
	}

}
