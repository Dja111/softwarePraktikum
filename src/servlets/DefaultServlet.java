package servlets;

import application.MRApplication;
import datatypes.UserData;
import dbadapter.DBFacade;
import dbadapter.DiscussionGroupData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class DefaultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("member") != null) {
            UserData user = new UserData(request.getParameter("member"), "", 0);
            try {
                MRApplication.getInstance().leaveGroup(
                        user, request.getParameter("groupId"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<DiscussionGroupData> discussionGroupData1;
        ArrayList<DiscussionGroupData> discussionGroupData2;

        discussionGroupData1 = DBFacade.getInstance().getDiscussionGroupData(1, "default");
        discussionGroupData2 = DBFacade.getInstance().getDiscussionGroupData(2, "default");


        request.setAttribute("group1", discussionGroupData1);
        request.setAttribute("group2", discussionGroupData2);
        request.getRequestDispatcher("/defaultWebPage.ftl").forward(request, response);
    }
}
