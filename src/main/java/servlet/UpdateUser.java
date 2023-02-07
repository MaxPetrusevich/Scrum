package servlet;


import dto.PersonDto;
import lombok.SneakyThrows;
import personDao.bean.Person;
import service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/update.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonService personService = PersonService.getInstance();
        personService.update(PersonDto.builder()
                        .id(Integer.valueOf(req.getParameter("Id")))
                        .name(req.getParameter("firstName"))
                        .surname(req.getParameter("lastName"))
                        .build());
        req.setAttribute("users", personService.findAll());
        req.getRequestDispatcher("jsp/users.jsp").forward(req, resp);
    }
}
