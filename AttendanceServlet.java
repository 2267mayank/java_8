import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.LocalDate;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int roll = Integer.parseInt(request.getParameter("roll"));
        String name = request.getParameter("name");
        String status = request.getParameter("status");
        LocalDate date = LocalDate.now();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "password");
            PreparedStatement ps = con.prepareStatement("INSERT INTO attendance VALUES (?, ?, ?, ?)");
            ps.setInt(1, roll);
            ps.setString(2, name);
            ps.setDate(3, java.sql.Date.valueOf(date));
            ps.setString(4, status);
            ps.executeUpdate();

            out.println("<h3>Attendance Marked Successfully!</h3>");
            con.close();
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}
