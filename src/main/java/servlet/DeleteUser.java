package servlet;


import lombok.SneakyThrows;
import personDao.bean.Person;
import service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/delete.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonService personService = PersonService.getInstance();
        personService.delete(Integer.valueOf(req.getParameter("Id")));
        req.setAttribute("users", personService.findAll());
        req.getRequestDispatcher("jsp/users.jsp").forward(req, resp);

//        resp.setContentType("text/html;charset=UTF-8");
//        int id = Integer.parseInt(req.getParameter("id"));
//        PersonDAO db = new PersonDAO();
//        db.delete(id);
//        resp.sendRedirect("jsp/users.jsp"); users?
    }
}
