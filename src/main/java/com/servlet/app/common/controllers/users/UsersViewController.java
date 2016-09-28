package com.servlet.app.common.controllers.users;

import static com.servlet.app.common.AppStarter.getJspDir;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet.app.common.model.User;
import com.servlet.app.common.dao.UserDAO;
import com.servlet.app.common.services.UserService;

@WebServlet("/pages/users/")
public class UsersViewController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession(false).getAttribute("user");
        req.setAttribute("users", userService.getAllExcept(user));
        getServletContext().getRequestDispatcher(getJspDir() + "/users/view.jsp").forward(req, resp);
    }
}
