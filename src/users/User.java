package users;

import communication.Message;
import communication.Review;

import java.util.ArrayList;
import java.util.List;

public abstract class User {


    public enum Gender {
        MALE,FEMALE
    }
    protected List<Message> inbox;
    protected String name;
    protected int age;
    protected String username;
    protected String email;
    protected Gender gender;
    protected String phone;
    protected List<Review> writtenReviews; // Reviews the User writes in other accommodations

    public User(String name, int age, String username, String email, Gender gender, String phone) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        inbox = new ArrayList<Message>();
        writtenReviews = new ArrayList<Review>();

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

    public void addMessage(Message message){
        if(message!=null)
            inbox.add(message);
    }

    public List<Message> viewAllMessages()
    {
        return inbox;
    }

    public List<Message> viewUnseenMessages()
    {
        List<Message> temp = new ArrayList<Message>();
        for(Message m : inbox)
        {
            if(!m.isSeen())
                temp.add(m);
        }
        return temp;
    }

    public List<Review> readAllReviews(){
        return writtenReviews;
    }

}
