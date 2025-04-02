package rus.logovo.StoryTelling.Dialog;

public class Rest {
    private final String text;
    private final Runnable action;

    public Rest(String text, Runnable action) {
        this.text = text;
        this.action = action;
    }

    public String getText() {
        return text;
    }

    public Runnable getAction() {
        return action;
    }
}
