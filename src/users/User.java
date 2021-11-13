package users;

public abstract class User {


    public enum Gender {
        MALE,FEMALE
    }

    protected String name;
    protected int age;
    protected String username;
    protected String email;
    protected Gender gender;
    protected String phone;

    public User(String name, int age, String username, String email, Gender gender, String phone) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
    }


    //=========================== Getters & Setters ==========================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
