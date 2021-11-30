package communication;


import accommodations.Accommodation;
import users.User;

import java.io.Serial;
import java.io.Serializable;

public record Review(int stars, String description, User user,
                     Accommodation accommodation) implements Serializable {
    public Review(int stars, String description, User user, Accommodation accommodation) {
        this.description = description;
        this.user = user;
        this.accommodation = accommodation;
        if (stars < 0)
            this.stars = 1;
        else this.stars = Math.min(stars, 5);
    }

    @Override
    public String toString() {
        return "Review{" +
                "stars=" + stars +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", accommodation=" + accommodation +
                '}';
    }

    @Serial
    private static final long serialVersionUID = 0;
}
