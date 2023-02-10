package servlet;


import converter.Converter;
import lombok.SneakyThrows;
import service.PersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.Constants.*;

@WebServlet("/delete")
public class DeleteUser extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      super.doGet(req,resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
        PersonServiceImpl personServiceImpl = PersonServiceImpl.getInstance();
        personServiceImpl.delete(Integer.parseInt((String) req.getAttribute(ID)));
        req.setAttribute(LIST_NAME, personServiceImpl.findAll());
        req.getRequestDispatcher(USERS_JSP_WAY).forward(req, resp);


    }
}
