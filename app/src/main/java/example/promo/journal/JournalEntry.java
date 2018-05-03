package example.promo.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable {
    /** The following class stores a journal entry and contains getters and setters for the variables
     * title, timestamp, content and mood. */

    // initializes properties...
    private String title, timestamp, content, drawableMood;

    // constructor
    public JournalEntry(String title, String content, String drawableMood, String timestamp) {
        this.title = title;
        this.content = content;
        this.drawableMood = drawableMood;
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

    // getter and setter for drawableMood
    public String getDrawableMood() {
        return drawableMood;
    }

    public void setDrawableMood(String drawableMood) {
        this.drawableMood = drawableMood;
    }

    // getter and setter for timestamp
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
