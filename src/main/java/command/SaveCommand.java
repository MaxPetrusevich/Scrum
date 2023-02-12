package command;

import converter.Converter;
import lombok.SneakyThrows;
import service.PersonServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static servlet.Constants.LIST_NAME;
import static servlet.Constants.USERS_JSP_WAY;

public class SaveCommand implements Command{
    @SneakyThrows
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        PersonServiceImpl personServiceImpl = PersonServiceImpl.getInstance();
        personServiceImpl.create(Converter.convert(req));
        req.setAttribute(LIST_NAME, personServiceImpl.findAll());
        req.getRequestDispatcher(USERS_JSP_WAY).forward(req, resp);
    }
}
