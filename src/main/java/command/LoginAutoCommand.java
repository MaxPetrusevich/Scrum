package command;

import authorization.Authorization;
import authorization.AuthorizationImpl;

import lombok.SneakyThrows;

import service.PersonService;
import service.PersonServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static servlet.Constants.USERS_JSP_WAY;

public class LoginAutoCommand implements Command{



    @SneakyThrows
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        Authorization authorization = new AuthorizationImpl();
        authorization.signIn(req);
        PersonService personService = PersonServiceImpl.getInstance();

        int currentPage = 1;
        int recordsPerPage = 3;

        int rows = personService.getCountRows();
        int countPages = rows / recordsPerPage;
         if (rows % recordsPerPage > 0) {
            countPages++;
        }

        req.setAttribute("countPages", countPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);

        req.setAttribute("users", personService.findLimit(0,recordsPerPage));
        req.getRequestDispatcher(USERS_JSP_WAY).forward(req,resp);
    }
}
