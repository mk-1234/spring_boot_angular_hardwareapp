package hr.tvz.krznar.hardwareapp.review;

public class ReviewDTO {

    private String title;
    private String text;
    private Integer rating;

    public ReviewDTO(String title, String text, Integer rating) {
        this.title = title;
        this.text = text;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Integer getRating() {
        return rating;
    }

}
