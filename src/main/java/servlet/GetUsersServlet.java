package servlet;

import dto.PersonDto;
import lombok.SneakyThrows;
import service.PersonServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import static servlet.Constants.*;

@WebServlet("/users")
public class GetUsersServlet extends HttpServlet {

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        PersonServiceImpl personServiceImpl = PersonServiceImpl.getInstance();
        List<PersonDto> list = personServiceImpl.findAll();
        req.setAttribute(LIST_NAME, list);
        req.getRequestDispatcher(USERS_JSP_WAY).forward(req, resp);
    }
}
