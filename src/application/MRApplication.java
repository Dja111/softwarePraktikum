package application;

import datatypes.UserData;
import dbadapter.DBFacade;
import dbadapter.DiscussionGroupData;
import interfaces.GCmds;

import java.util.ArrayList;

public class MRApplication implements GCmds {
    private static MRApplication instance;

    /**
     * Implementation of the Singleton pattern
     *
     * @return Aurel
     */

    public static MRApplication getInstance() {
        if (instance == null) {
            instance = new MRApplication();
        }
        return instance;
    }

    @Override
    public ArrayList<DiscussionGroupData> addNewMember(UserData discussionGroupMember, String groupId) {
        ArrayList<DiscussionGroupData> result = null;
        DBFacade instance = DBFacade.getInstance();
        int id = Integer.parseInt(groupId);
        try {
            instance.addDiscussionGroupMember(discussionGroupMember, id);
            result = instance.getDiscussionGroupData(id, "group");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteDiscussionGroup() {

    }

    @Override
    public void addChat(DiscussionGroupData discussionGroupData) {
        DBFacade instance = DBFacade.getInstance();
        try {
            instance.addChat(discussionGroupData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void leaveGroup(UserData user, String groupId) {
        DBFacade instance = DBFacade.getInstance();
        try {
            instance.leaveGroup(user, groupId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
