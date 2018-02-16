package dbadapter;

import datatypes.UserData;
import interfaces.IDiscussionGroupData;

import java.sql.*;
import java.util.ArrayList;

public class DBFacade implements IDiscussionGroupData {
    private static DBFacade instance;

    /**
     * Constructor which loads the driver for the type
     */

    private DBFacade() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implementation of the Singleton pattern
     *
     * @return
     */

    public static DBFacade getInstance() {
        if (instance == null) {
            instance = new DBFacade();
        }
        return instance;
    }

    /**
     * Function that return all group members from the database.
     */

    public ArrayList<DiscussionGroupData> getDiscussionGroupData(int groupId, String type) {
        String sqlSelect = "";
        switch (type) {
            case "group":
                sqlSelect = "SELECT * FROM DiscussionGroupA WHERE groupId = ?";
                break;
            case "default":
                sqlSelect = "SELECT DISTINCT member, groupId, isAdmin FROM DiscussionGroupA WHERE groupId = ?";
        }
        ArrayList<DiscussionGroupData> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(
                "jdbc:" + Configuration.getTYPE() + ":/"
                        + Configuration.getSERVER() + "/"
                        + Configuration.getDATABASE())) {
            try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
                ps.setInt(1, groupId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        DiscussionGroupData temp = new DiscussionGroupData(rs.getString(1), rs.getInt(2), rs.getString(3));
                        result.add(temp);
                    }
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return result;
    }

    @Override
    public void addDiscussionGroupMember(UserData discussionGroupMember, int groupId) {
        String sqlSelect = "INSERT INTO DiscussionGroupMember (groupId, member) VALUES (?,?)";
        try (Connection connection = DriverManager.getConnection(
                "jdbc:" + Configuration.getTYPE() + ":/"
                        + Configuration.getSERVER() + "/"
                        + Configuration.getDATABASE())) {
            try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
                ps.setInt(1, groupId);
                ps.setString(2, discussionGroupMember.getUserName());
                ps.executeUpdate();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void addChat(DiscussionGroupData discussionGroupData) {
        String sqlSelect = "INSERT INTO DiscussionGroupA (member, groupId, chat, isAdmin) VALUES (?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(
                "jdbc:" + Configuration.getTYPE() + ":/"
                        + Configuration.getSERVER() + "/"
                        + Configuration.getDATABASE())) {
            try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
                ps.setString(1, discussionGroupData.getCreator());
                ps.setInt(2, discussionGroupData.getGroupId());
                ps.setString(3, discussionGroupData.getText());
                ps.setInt(4, 1);
                ps.executeUpdate();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void leaveGroup(UserData user, String groupId) {
        String sqlSelect = "DELETE FROM DiscussionGroupA WHERE groupId = ? AND member = ?";
        try (Connection connection = DriverManager.getConnection(
                "jdbc:" + Configuration.getTYPE() + ":/"
                        + Configuration.getSERVER() + "/"
                        + Configuration.getDATABASE())) {
            try (PreparedStatement ps = connection.prepareStatement(sqlSelect)) {
                ps.setInt(1, Integer.parseInt(groupId));
                ps.setString(2, user.getUserName());
                ps.executeUpdate();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        String sqlSelect2 = "DELETE FROM DiscussionGroupMember WHERE groupId = ? AND member = ?";
        try (Connection connection = DriverManager.getConnection(
                "jdbc:" + Configuration.getTYPE() + ":/"
                        + Configuration.getSERVER() + "/"
                        + Configuration.getDATABASE())) {
            try (PreparedStatement ps = connection.prepareStatement(sqlSelect2)) {
                ps.setInt(1, Integer.parseInt(groupId));
                ps.setString(2, user.getUserName());
                ps.executeUpdate();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
