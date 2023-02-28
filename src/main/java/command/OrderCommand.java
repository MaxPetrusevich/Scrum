package command;

import converter.Converter;
import lombok.SneakyThrows;
import service.PersonService;
import service.PersonServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static servlet.Constants.LIST_NAME;
import static servlet.Constants.USERS_JSP_WAY;
import static servlet.MainServlet.FIELD;

public class OrderCommand implements Command{
    public static final String CURRENT_PAGE = "currentPage";
    public static final String FIELD = "field";
    PersonService personService = PersonServiceImpl.getInstance();
    @SneakyThrows
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        PersonServiceImpl personServiceImpl = PersonServiceImpl.getInstance();

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
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);

        if (field==null || field.isEmpty()) {
            req.setAttribute(LIST_NAME, personServiceImpl.findLimit(currentPage, recordsPerPage));
        } else {
            req.setAttribute(LIST_NAME, personServiceImpl.findLimitOrderByField(currentPage, recordsPerPage,field));
        }

        req.getRequestDispatcher(USERS_JSP_WAY).forward(req, resp);
    }
}
