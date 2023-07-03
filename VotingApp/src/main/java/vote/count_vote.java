package vote;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class count_vote extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String cname=req.getParameter("action");
		PrintWriter out=res.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			PreparedStatement ps=con.prepareStatement("SELECT distinct count(cname) as total_votes,cname FROM votingapp.candidate where cname=?");
			ps.setString(1, cname);
			ResultSet rs  =ps.executeQuery();
			while(rs.next())
			{
				int a=rs.getInt(1);
				String b=rs.getString(2);
				out.println("<html><body>Total number of votes for "+b+":   "+a+"</body></html>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	
}
}
