package servlet;


import converter.Converter;
import dto.PersonDto;
import lombok.SneakyThrows;
import service.PersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static servlet.Constants.*;
@WebServlet("/update")
public class UpdateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(UPDATE_JSP_WAY).forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
        PersonServiceImpl personServiceImpl = PersonServiceImpl.getInstance();
        PersonDto personDto = Converter.convert(req);
        personServiceImpl.update(personDto);
        req.setAttribute(LIST_NAME, personServiceImpl.findAll());
        req.getRequestDispatcher(USERS_JSP_WAY).forward(req, resp);
    }
}
