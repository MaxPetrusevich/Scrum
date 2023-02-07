package servlet;

import dto.PersonDto;
import lombok.SneakyThrows;
import service.PersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class GetUsersServlet extends HttpServlet {

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonService personService = PersonService.getInstance();
        List<PersonDto> list = personService.findAll();
        req.setAttribute("users", list);
        req.getRequestDispatcher("jsp/users.jsp").forward(req, resp);
    }
}
