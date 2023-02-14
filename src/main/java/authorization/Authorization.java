package authorization;

import javax.servlet.http.HttpServletRequest;

public interface Authorization {
    void signIn(HttpServletRequest req);
    void registration(HttpServletRequest req);
}
