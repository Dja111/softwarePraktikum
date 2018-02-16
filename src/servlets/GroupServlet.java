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

public class GroupServlet extends HttpServlet {
    private int id;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("member") != null) {
            id = Integer.parseInt(request.getParameter("groupId"));
            UserData user = new UserData(request.getParameter("member"), "", 0);
            try {
                MRApplication.getInstance().addNewMember(
                        user, request.getParameter("groupId"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("id " + id);
            System.out.println(request.getParameter("chat"));
            DiscussionGroupData discussionGroupData = new DiscussionGroupData("Muskuloese", id, request.getParameter("chat"));
            MRApplication.getInstance().addChat(discussionGroupData);
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<DiscussionGroupData> discussionGroupData;
        discussionGroupData = DBFacade.getInstance().getDiscussionGroupData(this.id, "group");
        request.setAttribute("group", discussionGroupData);
        request.getRequestDispatcher("/group.ftl").forward(request, response);
    }
}
