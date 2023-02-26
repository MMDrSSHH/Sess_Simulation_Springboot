package Sess.Sess.users;

public class User {
    private int id;
    private String firstName;
    private String lastName;

    private String roll;

    public User() {
    }

    public User(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    public User(int id, String firstName, String lastName) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    public User(int userId, String firstName, String lastName, String roll) {
        this(userId, firstName, lastName);
        this.setRoll(roll);
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.getFirstName() + this.getLastName();
    }

    @Override
    public String toString() {
        return "id:" + this.getId() + ","
                + "firstName:" + this.getFirstName() + ","
                + "lastName:" + this.getLastName() + ","
                + "roll:" + this.getRoll();
    }
}
