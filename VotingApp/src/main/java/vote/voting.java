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

public class voting extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int voterid=Integer.parseInt(req.getParameter("id"));
		String cname= req.getParameter("vote");
		PrintWriter out=res.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			PreparedStatement ps1=con.prepareStatement("select * from votingapp.candidate where voterid=?");
			ps1.setInt(1, voterid);
			ResultSet rs=ps1.executeQuery();
			if(rs.next())
			{
				if(voterid==(rs.getInt(1)))
				{
					RequestDispatcher rd=req.getRequestDispatcher("vote.html");
					rd.include(req, res);
					out.println("<html><body ><h3 style='color:red'>You have votted already</h3> </body></html>");
				}
			}
			else {
				PreparedStatement ps=con.prepareStatement("insert into votingapp.candidate(Voterid,cname)values(?,?)");
				ps.setInt(1, voterid);
				ps.setString(2, cname);
				ps.execute();
				out.print("<html><body>Thank you for Your Vote</body></html>");	
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
}
