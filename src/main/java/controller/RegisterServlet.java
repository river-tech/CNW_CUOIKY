package controller;

import model.bean.UserBean;
import model.bo.UserBO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserBO userBO = new UserBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBean user = new UserBean();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setLastname(request.getParameter("lastname"));
        user.setRole(request.getParameter("role"));

        boolean created = userBO.register(user);
        if (!created) {
            String keyword = user.getUsername() == null ? "" : user.getUsername();
            response.sendRedirect(request.getContextPath() + "/search?keyword=" + keyword);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
