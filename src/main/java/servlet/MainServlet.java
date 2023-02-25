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


    public static final String GET = "GET";
    public static final String POST = "POST";
    private String command;
    private Command commandExecutor;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        command = req.getParameter(COMMAND);
        if (ADD.compareTo(command) == 0) {
            addCommand(req,resp);
        } else if (SELECT.compareTo(command) == 0) {
            selectCommand(req,resp);
        } else if (UPDATE.compareTo(command) == 0) {
            updateCommand(req,resp);
        } else if (DELETE.compareTo(command) == 0) {
            deleteCommand(req,resp);
        } else if (LOGIN_REG.compareTo(command) == 0) {
            loginRegCommand(req,resp);
        } else if (LOGIN_AUTO.compareTo(command) == 0) {
            loginAutoCommand(req,resp);
        }
    }

    protected void addCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("role", req.getParameter("role"));
        req.setAttribute("status", req.getParameter("status"));

        if (GET.equals(req.getMethod())) {
            req.getRequestDispatcher(ADD_JSP_WAY).forward(req, resp);
        } else {
            commandExecutor = new SaveCommand();
            commandExecutor.execute(req, resp);
        }
    }

    protected void selectCommand(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("role", req.getParameter("role"));
        req.setAttribute("status", req.getParameter("status"));
        commandExecutor = new SelectCommand();
        commandExecutor.execute(req, resp);
    }

    protected void updateCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("role", req.getParameter("role"));
        req.setAttribute("status", req.getParameter("status"));
        if (GET.equals(req.getMethod())) {
            req.setAttribute(ID, req.getParameter(ID));
            req.setAttribute(FIRST_NAME, req.getParameter(FIRST_NAME));
            req.setAttribute(LAST_NAME, req.getParameter(LAST_NAME));
            req.getRequestDispatcher(UPDATE_JSP_WAY).forward(req, resp);
        } else {
            commandExecutor = new UpdateCommand();
            commandExecutor.execute(req, resp);
        }
    }

    protected void deleteCommand(HttpServletRequest req, HttpServletResponse resp) {
        if (POST.equals(req.getMethod())) {
            req.setAttribute("role", req.getParameter("role"));
            req.setAttribute("status", req.getParameter("status"));
            req.setAttribute(ID, req.getParameter(ID));
            commandExecutor = new DeleteCommand();
            commandExecutor.execute(req, resp);
        }
    }

    protected void loginRegCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("role", req.getParameter("role"));
        req.setAttribute("status", req.getParameter("status"));
        if (GET.equals(req.getMethod())) {
            req.setAttribute(ACTION, REG);
            req.getRequestDispatcher(LOGIN_JSP_WAY).forward(req, resp);
        } else {
             req.setAttribute(NAME, req.getParameter(NAME));
            req.setAttribute(EMAIL, req.getParameter(EMAIL));
            req.setAttribute(PASSWORD, req.getParameter(PASSWORD));
            commandExecutor = new LoginRegCommand();
            commandExecutor.execute(req, resp);
        }
    }

    protected void loginAutoCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("role", req.getParameter("role"));
        req.setAttribute("status", req.getParameter("status"));
        if (GET.equals(req.getMethod())) {
            req.setAttribute(ACTION, AUTO);
            req.getRequestDispatcher(LOGIN_JSP_WAY).forward(req, resp);
        } else {
            req.setAttribute(EMAIL, req.getParameter(EMAIL));
            req.setAttribute(PASSWORD, req.getParameter(PASSWORD));
            commandExecutor = new LoginAutoCommand();
            commandExecutor.execute(req, resp);
        }
    }
}
