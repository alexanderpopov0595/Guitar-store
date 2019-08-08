
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.User;
import business.UserDB;
@WebServlet("/settings")
public class SettingsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��� get-������ ������� ������������ �������� ��� �������������� �������
		getServletContext().getRequestDispatcher("/settings.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �������� ������ �� �����
	
		int user_id=Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String telephone = request.getParameter("telephone");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		//�������� ������������
		User user=new User(user_id,firstName, lastName, telephone, login,password);
		
		//��������� ������ ������������ � ����� user_id
		UserDB.update(user);
		
		// �������� ������
        HttpSession session = request.getSession();
		//��������� ������������ � ��������, ����� �� �������� � �������� ���������� ��� ������ ������� � ������ ������
		session.setAttribute("user", user);			
		// ��������������� �� �������� �������
		getServletContext().getRequestDispatcher("/profile").forward(request, response);
		

	}

}
