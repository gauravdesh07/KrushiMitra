package com.gaurav.krushimitra;

import java.io.Serializable;

public class WeekUser implements Serializable {
    public String getTitle() {
        return title;
    }

    public WeekUser(String title, String description) {
        this.title = title;
        this.description = description;
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

    String title,description;
}
