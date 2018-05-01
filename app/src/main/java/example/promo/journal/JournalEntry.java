package example.promo.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable {
    private int id, drawableMood, timestamp;
    private String title, content;

    public JournalEntry(int id, int drawableMood, int timestamp, String title, String content) {
        this.id = id;
        this.drawableMood = drawableMood;
        this.timestamp = timestamp;
        this.title = title;
        this.content = content;
    }

    // getter and setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // getter and setter for drawableMood
    public int getDrawableMood() {
        return drawableMood;
    }

    public void setDrawableMood(int drawableMood) {
        this.drawableMood = drawableMood;
    }

    // getter and setter for timestamp
    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    // getter and setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // getter and setter for content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
