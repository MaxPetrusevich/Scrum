package command;

import dto.PersonDto;
import lombok.SneakyThrows;
import service.PersonService;
import service.PersonServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static servlet.Constants.LIST_NAME;
import static servlet.Constants.USERS_JSP_WAY;

public class SelectCommand implements Command {
    @SneakyThrows
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        PersonServiceImpl personServiceImpl = PersonServiceImpl.getInstance();

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

        req.setAttribute(LIST_NAME, personServiceImpl.findLimit((currentPage-1)*recordsPerPage,recordsPerPage));


//        List<PersonDto> list = personServiceImpl.findAll();
//        req.setAttribute(LIST_NAME, list);
        req.getRequestDispatcher(USERS_JSP_WAY).forward(req, resp);
    }
}
