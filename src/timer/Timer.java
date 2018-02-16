package timer;

import application.MRApplication;

/**
 * Timer class to call the method checkEmpty in the application. Main method
 * can be executed in a scheduled way.
 *
 * @author muskuloese
 */
public class Timer {

    public static void main(String[] args) {
        MRApplication mbApp = new MRApplication();
        mbApp.deleteDiscussionGroup();
        System.out.println("All empty groups with less that 2 members are successfully deleted");
    }
}
