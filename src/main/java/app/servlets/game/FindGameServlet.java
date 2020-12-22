package app.servlets.game;

import app.helpers.DataBaseHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "findGameServlet", value = "/FindGame")
public class FindGameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DataBaseHelper dbHelper = new DataBaseHelper();
        ArrayList<String> gamesList = dbHelper.GetGamesList();

        req.setAttribute("gamesList", gamesList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/game/findGame.jsp");
        requestDispatcher.forward(req,resp);
    }
}