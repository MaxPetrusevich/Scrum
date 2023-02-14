package command;

import authorization.Authorization;
import authorization.AuthorizationImpl;

import lombok.SneakyThrows;

import service.PersonService;
import service.PersonServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static servlet.Constants.USERS_JSP_WAY;

public class LoginRegCommand implements Command{
    @SneakyThrows
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        Authorization authorization = new AuthorizationImpl();
        authorization.registration(req);
        PersonService personService = PersonServiceImpl.getInstance();
        req.setAttribute("users", personService.findAll());
        req.getRequestDispatcher(USERS_JSP_WAY).forward(req,resp);
    }
}
