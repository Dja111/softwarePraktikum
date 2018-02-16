package servlets;

import dbadapter.DBFacade;
import dbadapter.DiscussionGroupData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<DiscussionGroupData> dData = DBFacade.getInstance().getDiscussionGroupData(1,"default");
        request.setAttribute("discussionData", dData);
        request.setAttribute("yi", "you");
        request.getRequestDispatcher("/index.ftl").forward(request, response);
    }
}
