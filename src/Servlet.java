import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Kamil on 28.04.2017.
 */
@WebServlet(urlPatterns = "/servlet")
public class Servlet extends HttpServlet {

    @EJB
    MessageReceiverSync2 receiverSync2;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        //response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.flush();
            out.println("");
            out.println("");

            out.println("Oczekiwanie na wezwanie");

            out.println("");
            out.println("");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //response.setIntHeader("Refresh", 5);
        receiverSync2.receiveMessage();
        processRequest(request,response);

        //request.getRequestDispatcher("hello.jsp").forward(request,response);

    }
}
