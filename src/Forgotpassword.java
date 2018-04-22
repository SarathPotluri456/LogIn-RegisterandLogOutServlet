

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Forgotpassword
 */
@WebServlet("/pass1")
public class Forgotpassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forgotpassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Register Driver Completed");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			System.out.println("Connection Established");
			String uname=req.getParameter("name1");
            PreparedStatement ps=con.prepareStatement("select *from student where username=?");
            ps.setString(1, uname);
            ResultSet rs=ps.executeQuery();
            pw.println("<body bgcolor='cyan'>");
            pw.println("<center><h2>YourPassWord is:</h2></center>");
            while(rs.next())
            {
            	pw.print("<center>"+rs.getString(3)+"</center>");
            }
            pw.println("<button><a href='http://localhost:8090/SignInAndSignUpServlet/'>Home</a></button>");
            
            pw.println("</body>");
            
            con.close();
            
            
			
			
	}catch(Exception e)
		{
		
		}

	}

}
