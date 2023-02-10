package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static servlet.Constants.*;

@WebServlet("/dispatcher")
public class Dispatcher extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ID, req.getParameter(ID));
        req.setAttribute(FIRST_NAME, req.getParameter(FIRST_NAME));
        req.setAttribute(LAST_NAME, req.getParameter(LAST_NAME));
        req.getServletContext().getRequestDispatcher("/"+UPDATE_JSP_WAY).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ID, req.getParameter(ID));
        req.getServletContext().getRequestDispatcher(DELETE).forward(req,resp);
    }
}
