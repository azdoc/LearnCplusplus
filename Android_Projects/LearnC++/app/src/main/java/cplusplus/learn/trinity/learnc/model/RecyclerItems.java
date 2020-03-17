package cplusplus.learn.trinity.learnc.model;

public class RecyclerItems {
    private String title;
    private String description;
    private int icon;
 
    public RecyclerItems() {
    }
 
    public RecyclerItems(String title, String description, int icon) {
        this.title = title;
        this.description = description;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}