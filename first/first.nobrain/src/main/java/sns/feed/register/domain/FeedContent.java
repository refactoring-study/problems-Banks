package sns.feed.register.domain;

public class FeedContent {

    private String message;

    private FeedContent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static FeedContent createMessage(String message) {
        return new FeedContent(message);
    }

}
