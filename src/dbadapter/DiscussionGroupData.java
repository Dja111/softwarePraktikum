package dbadapter;

public class DiscussionGroupData {
    private int groupId;
    private String text;
    private String creator;

    public DiscussionGroupData(String creator, int groupId, String text) {
        this.creator = creator;
        this.groupId = groupId;
        this.text = text;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getText() {
        return text;
    }

    public String getCreator() {
        return creator;
    }
}

