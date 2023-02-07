package servlet;


import dto.PersonDto;
import lombok.SneakyThrows;
import personDao.bean.Person;
import personDao.dao.DaoPerson;
import service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddUserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/add.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonService personService = PersonService.getInstance();
        PersonDto person = PersonDto.builder().name(req.getParameter("firstName")).surname(req.getParameter("lastName")).build();
        personService.create(person);
        req.setAttribute("users", personService.findAll());
        req.getRequestDispatcher("jsp/users.jsp").forward(req, resp);
    }
}
