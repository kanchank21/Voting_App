package vote;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class login2 extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int voterid=Integer.parseInt(req.getParameter("id"));
		
		PrintWriter pw=res.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			PreparedStatement ps=con.prepareStatement("select * from votingapp.voters where voterid=?");
			ps.setInt(1, voterid);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				if(voterid==(rs.getInt(1)))
				{
					RequestDispatcher rd1=req.getRequestDispatcher("vote.html");
					rd1.forward(req, res);
				}
				
			}
			else
			{
					RequestDispatcher rd=req.getRequestDispatcher("signin.html");
					rd.include(req, res);
					pw.println("<html><body><h3 style='color:red'>Invalid VoterId</h3></body></html>");	
			}
							
					
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
