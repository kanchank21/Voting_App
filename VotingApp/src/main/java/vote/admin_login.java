package vote;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class admin_login extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email =req.getParameter("email");
		String pass=req.getParameter("pass");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			PreparedStatement ps=con.prepareStatement("select * from votingapp.admins where email=? and pass=?");
			ps.setString(1, email);
			ps.setString(2,pass);
			
			RequestDispatcher rd=req.getRequestDispatcher("count.html");
			rd.forward(req, res);
			
		} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
		}
		
	}
}
