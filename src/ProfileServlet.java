
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.ProductDB;
import business.User;
import business.Product;
import business.ProductDB;
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �������� ������
        HttpSession session = request.getSession();
		// ��������� ������������
		User user=(User) session.getAttribute("user");	
		
		// ��������� ������ �������� ������������ ����� user_id
		ArrayList<Product> products = ProductDB.select(user.getId());
		
		
		//��������� � ��������� ���������, ������� � ����� ������������ ����� ������
		//���� ����� ���� - ������� ��� ����������
		if(products.size()!=0) {			
			request.setAttribute("message", "You've got "+products.size()+" items.");
		}
		//���� ��� - ������� ��������� "��� ������"
		else {
			request.setAttribute("message", "You've got no items.");	
		}
		
		// ��������� ������ ������ � ���������
		request.setAttribute("products", products);		
		
		// ��������������� �� �������� �������
		request.getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
	}
	//������������� �� ���� ������� ���� �� post-������ ����������� ��������, ������� ����� ���������� doGet �����
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
