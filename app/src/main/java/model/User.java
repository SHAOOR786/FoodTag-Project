package model;

public class User {
    private String uId;
    private String fName;
    private String lName;
    private String email;
    private String phone;
    private String password;

    public User(String fName, String lName, String email, String phone, String password) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public User(){
    }

    public User(String uId, String fName, String lName, String email, String phone, String password) {
        this.uId = uId;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
