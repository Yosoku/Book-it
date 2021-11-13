package communication;

import users.User;

public class Review {
    private final User user;
    private final int stars;
    private final String description;

    public User getUser() {
        return user;
    }

    public int getStars() {
        return stars;
    }

    public String getDescription() {
        return description;
    }

    public Review(User user, int stars, String description){
        this.user = user;
        this.description = description;
        if(stars<0)
            this.stars = 1;
        else if(stars>5) this.stars = 5;
        else this.stars = stars;
    }
}
