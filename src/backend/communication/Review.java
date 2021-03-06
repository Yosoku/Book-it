package backend.communication;

import backend.accommodations.Accommodation;
import backend.users.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * A record representing a Review instace with information about number of stars,a description,the User and the Accommodation
 * The record also implements the Serializable interface for storing in files
 *
 * @author Edward Koulakidis
 */
public record Review(int stars, String description, User user,
                     Accommodation accommodation) implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return stars == review.stars && Objects.equals(description, review.description) && Objects.equals(user, review.user) && Objects.equals(accommodation, review.accommodation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stars, description, user, accommodation);
    }

    /**
     * Initializer constructor which makes sure that stars are withing 1-5 inclusive boundaries
     *
     * @param stars         The number of stars
     * @param description   The description
     * @param user          The user who wrote the review
     * @param accommodation The accommodation which the review is about
     */
    public Review(int stars, String description, User user, Accommodation accommodation) {
        this.description = description;
        this.user = user;
        this.accommodation = accommodation;
        if (stars < 0)
            this.stars = 1;
        else this.stars = Math.min(stars, 5);
    }

    /**
     * Useful for printing to the user and also debugging
     *
     * @return a string representation of the review instance
     */
    @Override
    public String toString() {
        return "Review{" +
                "stars=" + stars +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", accommodation=" + accommodation +
                '}';
    }
}
