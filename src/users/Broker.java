package users;

public class Broker extends User{
    private String company;

    public Broker(String name, int age, String username, String email, Gender gender, String phone,String company) {
        super(name, age, username, email, gender, phone);
        this.company = this.company;
    }

    @Override
    public String toString() {
        return "Broker{" +
                "company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                '}';
    }
}
