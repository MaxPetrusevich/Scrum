package servlet;

import command.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlet.Constants.*;

@WebServlet("/main")
public class MainServlet extends HttpServlet {


    private String command;
    private Command commandExecutor;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        command = req.getParameter(COMMAND);
        if (ADD.compareTo(command) == 0) {
            req.getRequestDispatcher(ADD_JSP_WAY).forward(req, resp);
        } else if (SELECT.compareTo(command) == 0) {
            commandExecutor = new SelectCommand();
            commandExecutor.execute(req, resp);
        } else if (UPDATE.compareTo(command) == 0) {
            req.setAttribute(ID, req.getParameter(ID));
            req.setAttribute(FIRST_NAME, req.getParameter(FIRST_NAME));
            req.setAttribute(LAST_NAME, req.getParameter(LAST_NAME));
            req.getRequestDispatcher(UPDATE_JSP_WAY).forward(req, resp);
        } else if (LOGIN_REG.compareTo(command) == 0) {
            req.setAttribute(ACTION, REG);
            req.getRequestDispatcher(LOGIN_JSP_WAY).forward(req, resp);
        } else if (LOGIN_AUTO.compareTo(command) == 0) {
            req.setAttribute(ACTION, AUTO);
            req.getRequestDispatcher(LOGIN_JSP_WAY).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        command = req.getParameter(COMMAND);
        if (ADD.compareTo(command) == 0) {
            commandExecutor = new SaveCommand();
            commandExecutor.execute(req, resp);
        } else if (SELECT.compareTo(command) == 0) {
            commandExecutor = new SelectCommand();
            commandExecutor.execute(req, resp);
        } else if (UPDATE.compareTo(command) == 0) {
            commandExecutor = new UpdateCommand();
            commandExecutor.execute(req, resp);
        } else if (DELETE.compareTo(command) == 0) {
            req.setAttribute(ID, req.getParameter(ID));
            commandExecutor = new DeleteCommand();
            commandExecutor.execute(req, resp);
        } else if (LOGIN_REG.compareTo(command) == 0) {
            req.setAttribute(NAME, req.getParameter(NAME));
            req.setAttribute(EMAIL, req.getParameter(EMAIL));
            req.setAttribute(PASSWORD, req.getParameter(PASSWORD));
            commandExecutor = new LoginRegCommand();
            commandExecutor.execute(req, resp);
        } else if (LOGIN_AUTO.compareTo(command) == 0) {
            req.setAttribute(EMAIL, req.getParameter(EMAIL));
            req.setAttribute(PASSWORD, req.getParameter(PASSWORD));
            commandExecutor = new LoginAutoCommand();
            commandExecutor.execute(req, resp);
        }
    }
}
