package command;

import lombok.SneakyThrows;
import service.PersonServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static servlet.Constants.*;

public class DeleteCommand implements Command {
    @SneakyThrows
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        PersonServiceImpl personServiceImpl = PersonServiceImpl.getInstance();
        personServiceImpl.delete(Integer.parseInt((String) req.getAttribute(ID)));
        String currentPageS = req.getParameter("currentPage");

        int currentPage = (currentPageS==null)?1:Integer.parseInt(currentPageS);
        int recordsPerPage = 3;

        int rows = personServiceImpl.getCountRows();
        int countPages = rows / recordsPerPage;
        if (rows % recordsPerPage > 0) {
            countPages++;
        }

        req.setAttribute("countPages", countPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);

        req.setAttribute(LIST_NAME, personServiceImpl.findLimit(currentPage,recordsPerPage));

//        req.setAttribute(LIST_NAME, personServiceImpl.findAll());
        req.getRequestDispatcher(USERS_JSP_WAY).forward(req, resp);
    }
}
