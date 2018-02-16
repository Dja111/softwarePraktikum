package dbadapter;

/**
 * This class is used to declare access data for the SQL server
 *
 * @author
 */

public class Configuration {

    private static final String SERVER = "home/muskuloese/Desktop";
    private static final String TYPE = "sqlite";
    private static final int PORT = 5432;
    private static final String USER = "muskuloese";
    private static final String PASSWORD = "";
    public static final String DATABASE = "MR.db";

    public static String getDATABASE() {
        return DATABASE;
    }

    public static String getSERVER() {
        return SERVER;
    }

    public static String getTYPE() {
        return TYPE;
    }

    public static int getPORT() {
        return PORT;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }
}
