package com.weirdsoft.Planner;

import javax.inject.Named;

@Named("testBean")
public class TestBean {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
