package interfaces;

import datatypes.UserData;
import dbadapter.DiscussionGroupData;

import java.util.ArrayList;

/**
 * Interface that provides all methods to interact with a guest
 */

public interface GCmds {
    ArrayList<DiscussionGroupData> addNewMember(UserData discussionGroupMember, String  groupId);
    void deleteDiscussionGroup();
    void addChat(DiscussionGroupData discussionGroupData);
    void leaveGroup(UserData user, String groupId);
}
