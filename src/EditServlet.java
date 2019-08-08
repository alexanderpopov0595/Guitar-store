

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import business.Product;
import business.ProductDB;
import business.User;
@WebServlet("/edit")
@MultipartConfig
public class EditServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��������� id ���� ������, ��� ������ edit ���� ������
		int product_id=Integer.parseInt(request.getParameter("id"));
		//��������� ���������������� �������� �� ���� ������
		Product product=ProductDB.selectProduct(product_id);		
		//��������� ������� � ��������
		request.setAttribute("product", product);
		//������������� �� �������� edit.jsp, ����� ������� �� �������� ������
		getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��������� ������ �� �����
		int product_id=Integer.parseInt(request.getParameter("id"));	
		int user_id=Integer.parseInt(request.getParameter("user_id"));
		String name=request.getParameter("name");
		int price=Integer.parseInt(request.getParameter("price"));
		String type=request.getParameter("type");
		String description=request.getParameter("description");
		// ��������� �����������
		Part part = request.getPart("image");
		String imagePath=request.getParameter("imagePath");
		//���� ��������� ����� ����
		if (part.getSize()!=0) {
			String fileName=product_id+".jpg";		
			String absolutePath = request.getServletContext().getRealPath("Images");
			String savePath = absolutePath + File.separator + fileName;	
			// �������� ����������� � �����
			part.write(savePath);
		
			imagePath="Images/"+fileName;
		}		
		
		//�������� ������ � ������ ����������
		Product product=new Product(product_id, user_id,name, price, type, description, imagePath);
		
		//��������� ��������� � ���� ������
		ProductDB.update(product);
		
		//�������� product_id � ��������, ����� ProductServlet ��� ������� ��� �� ���� ������
		request.setAttribute("product_id", product_id);
		// ������� � �������� � ��������� � ��� ����� post
		getServletContext().getRequestDispatcher("/product").forward(request, response);
		
		
	}

}
