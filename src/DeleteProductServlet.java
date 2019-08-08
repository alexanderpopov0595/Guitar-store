

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.ProductDB;


@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�������� �� �������� � ��������������� id ������
		int product_id=Integer.parseInt(request.getParameter("id"));
		
		//������� ��� �� ���� ������
		ProductDB.delete(product_id);
		//������� ����������� ������
		String absolutePath = request.getServletContext().getRealPath("Images");
		String savePath = absolutePath + File.separator + product_id+".jpg";		
		File file=new File(savePath);
		file.delete();
		//�������������� ������� �� �������� �������
		getServletContext().getRequestDispatcher("/profile").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
