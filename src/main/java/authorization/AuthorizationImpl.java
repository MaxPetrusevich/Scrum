package authorization;


import bean.User;
import lombok.SneakyThrows;
import personDao.dao.Dao;
import personDao.service.DaoUserImpl;
import personDao.util.DataForTable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AuthorizationImpl implements Authorization {
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";
    public static final String NAME = "Name";
    private final Dao dao = getDao();

    @SneakyThrows
    @Override
    public void signIn(HttpServletRequest req) {
        List<User> users = dao.selectAll();
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        for (User user :
                users) {
            if (user.getEmail().compareTo(email) == 0) {
                if (user.getPassword().compareTo(password) == 0) {
                    req.setAttribute("status", "Auto");
                    req.setAttribute("role", user.isRole() ? "Admin" : "User");
                    return;
                }
            }
        }
        req.setAttribute("status", "NoAuto");
    }

    @SneakyThrows
    @Override
    public void registration(HttpServletRequest req) {
        String name = req.getParameter(NAME);
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        boolean role = false;
        User user = User.builder().name(name).email(email).password(password).role(role).build();
        dao.save(user);
        req.setAttribute("status", "Auto");
        req.setAttribute("users", dao.selectAll());

    }

    @SneakyThrows
    private Dao getDao() {
        DataForTable<User> data = new DataForTable<>(User.builder().build());
        data.updateInfoInData();
        return new DaoUserImpl(data);
    }
}
