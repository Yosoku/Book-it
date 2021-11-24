package communication;

@SuppressWarnings("unused")
public class Review {
    private final int stars;
    private final String description;



    public int getStars() {
        return stars;
    }

    public String getDescription() {
        return description;
    }

    public Review(int stars, String description) {
        this.description = description;
        if (stars < 0)
            this.stars = 1;
        else this.stars = Math.min(stars, 5);
    }
}
