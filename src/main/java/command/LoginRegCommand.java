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
    public static final int FIRST_PAGE = 1;
    public static final int RECORDS_PER_PAGE = 3;

    @SneakyThrows
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        Authorization authorization = new AuthorizationImpl();
        authorization.registration(req);
        PersonService personService = PersonServiceImpl.getInstance();
//        req.setAttribute("users", personService.findAll());
        int currentPage = FIRST_PAGE;
        int recordsPerPage = RECORDS_PER_PAGE;

        int rows = personService.getCountRows();
        int countPages = rows / recordsPerPage;
        if (rows % recordsPerPage > 0) {
            countPages++;
        }

        req.setAttribute("countPages", countPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);

        req.setAttribute("users", personService.findLimit(currentPage,recordsPerPage));
        req.getRequestDispatcher(USERS_JSP_WAY).forward(req,resp);
    }
}
