package command;

import lombok.SneakyThrows;
import service.PersonServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static servlet.Constants.*;

public class DeleteCommand implements Command {

    public static final String CURRENT_PAGE = "currentPage";
    public static final String FIELD = "field";

    @SneakyThrows
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        PersonServiceImpl personServiceImpl = PersonServiceImpl.getInstance();
        personServiceImpl.delete(Integer.parseInt((String) req.getAttribute(ID)));
        String currentPageS = req.getParameter(CURRENT_PAGE);
        String field = req.getParameter(FIELD);

        int currentPage = (currentPageS==null || currentPageS.isEmpty())?1:Integer.parseInt(currentPageS);
        int recordsPerPage = 3;

        int rows = personServiceImpl.getCountRows();
        int countPages = rows / recordsPerPage;
        if (rows % recordsPerPage > 0) {
            countPages++;
        }

        req.setAttribute("countPages", countPages);
        req.setAttribute(CURRENT_PAGE, currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);

        if (field==null || field.isEmpty()) {
            req.setAttribute(LIST_NAME, personServiceImpl.findLimit(currentPage, recordsPerPage));
        } else {
            req.setAttribute(LIST_NAME, personServiceImpl.findLimitOrderByField(currentPage, recordsPerPage,field));
        }

//        req.setAttribute(LIST_NAME, personServiceImpl.findAll());
        req.getRequestDispatcher(USERS_JSP_WAY).forward(req, resp);
    }
}
