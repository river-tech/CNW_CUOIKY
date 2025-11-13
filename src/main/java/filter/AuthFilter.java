package filter;

import model.bean.UserBean;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/students", "/search", "/add"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        UserBean user = session == null ? null : (UserBean) session.getAttribute("user");
        if (user == null) {
            ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login");
            return;
        }
        request.setAttribute("currentUser", user);
        chain.doFilter(request, response);
    }
}
