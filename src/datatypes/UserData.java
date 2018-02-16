package datatypes;

public class UserData {
    private String username;
    private String email;
    private int age;

    // TODO may be you want to add email to the database
    public UserData(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }
    public String getUserName() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}
