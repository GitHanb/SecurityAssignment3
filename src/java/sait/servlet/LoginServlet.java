package sait.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sait.db.PasswordUtil;
import sait.db.UsersDB;
import sait.domain.User;

public class LoginServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String logout = request.getParameter("logout");
        request.setAttribute("message", request.getParameter("message"));

        if (logout != null)
        {
            HttpSession session = request.getSession();
            session.invalidate();
            request.setAttribute("message", "Logged out");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = null;
        try
        {
            user = UsersDB.getUser(username);
        } catch (Exception ex)
        {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (username != null && password != null && !username.equals("") && !password.equals(""))
        {
            try
            {
                if (user != null)
                {
                    //get salt from db
                    String salt = user.getSalt();
                    //get hash from db
                    String hashFromDB = user.getHashedandsaltedpassword();
                    //create a new hash value hash(user typed password + salt from DB)
                    String hashNew = PasswordUtil.hashPassword(password+salt);
                    if (hashFromDB.equals(hashNew))
                    {
                        HttpSession session = request.getSession();
                        session.setAttribute("username", username);

                        boolean isAdmin = UsersDB.isAdmin(username);
                        session.setAttribute("isAdmin", isAdmin);
                        response.sendRedirect("users");
                        return;
                    }
                    else
                    {
                        request.setAttribute("message", "Invalid password!");
                    }
                } else
                {
                    request.setAttribute("message", "Invalid username!");
                }
            } catch (Exception ex)
            {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else
        {
            request.setAttribute("message", "Both username and password are required!");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
