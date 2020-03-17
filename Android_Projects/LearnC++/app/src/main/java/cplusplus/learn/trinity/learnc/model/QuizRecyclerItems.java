package cplusplus.learn.trinity.learnc.model;

public class QuizRecyclerItems {
    private String title;
    private String bestScore;
    private String attempts;
    private int icon;

    public QuizRecyclerItems() {
    }

    public QuizRecyclerItems(String title, String bestScore, String attempts, int icon) {
        this.title = title;
        this.bestScore = bestScore;
        this.attempts = attempts;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBestScore() {
        return bestScore;
    }

    public void setBestScore(String bestScore) {
        this.bestScore = bestScore;
    }

    public String getAttempts() {
        return attempts;
    }

    public void setAttempts(String attempts) {
        this.attempts = attempts;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}