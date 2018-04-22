

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		ServletContext sc=getServletContext();
		try
		{
			Class.forName(sc.getInitParameter("driver"));
			Connection con=DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("user"), sc.getInitParameter("pass"));
			String uname=req.getParameter("name");
			String password=req.getParameter("pass");
			PreparedStatement ps=con.prepareStatement("select * from student where username=? and pass=?");
			ps.setString(1, uname);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			boolean status=rs.next();
			pw.println("<body bgcolor='cyan'>");
			if(status==true)
			{   
				pw.println("<h1>Hai "+uname+"</h1>");
				pw.println("<h2>welcome to website</h2>");
			pw.println("<button><a style='text-decoration:none' href='http://localhost:8090/SignInAndSignUpServlet/'>Home</a></button>");
			pw.println("<button><a style='text-decoration:none' href='http://localhost:8090/SignInAndSignUpServlet/SignOut.html'>Signout</a></button>");
			}
			else
			{
				pw.println("<h2>please Enter Proper Values</h2>");
				pw.println("<button><a href='http://localhost:8090/SignInAndSignUpServlet/SignIn.html'>SignIn</a></button>");
				pw.println("<button><a style='text-decoration:none' href='http://localhost:8090/SignInAndSignUpServlet/ForGotPassword.html'>ForGotPassWord</a></button>");
			}
			pw.println("</body>");
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	
	}

}
