package vote;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class register extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int voterid =Integer.parseInt(req.getParameter("id"));
		String uname=req.getParameter("name");
		String mno=req.getParameter("mno");
		String address=req.getParameter("loc");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			PreparedStatement ps=con.prepareStatement("insert into votingapp.voters(voterid,uname,mno,address)values(?,?,?,?)"); 
			
			ps.setInt(1, voterid);
			ps.setString(2, uname);
			ps.setString(3, mno);
			ps.setString(4, address);
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		PrintWriter pw=res.getWriter();
//		for(int i=1;i<4;i++) {
//			try {
//				Thread.sleep(700);
//				System.out.print(".  ");
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		RequestDispatcher rd=req.getRequestDispatcher("register.html");
		rd.include(req, res);
		pw.println("<html><body style='color:green'><h3>You have Registerd successfully</h3></body><html>");

	}

}
