

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/signup")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
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
			String name=req.getParameter("name");
			String uname=req.getParameter("name1");
			String password=req.getParameter("pass");
			PreparedStatement ps=con.prepareStatement("insert into student values(?,?,?)");
			ps.setString(1, name);
			ps.setString(2, uname);
			ps.setString(3, password);
			int i=ps.executeUpdate();
			pw.println("<body bgcolor='cyan'>");
			pw.println("<center>");
			if(i>0)
			{
				pw.println("<h1>Record Inserted Successfully</h1>");
			}
			pw.println("<button><a style='text-decoration:none' href='http://localhost:8090/SignInAndSignUpServlet/SignIn.html'>SignIn</a></button>");
			pw.println("<button><a style='text-decoration:none' href='http://localhost:8090/SignInAndSignUpServlet/'>Home</a></button>");
	        pw.println("</center");
	        pw.println("</body>");
		}catch(Exception e)
		{
			
		}
	}

}
