package com.example.sportevent;

public class JoinedEventItem {
    private int imageResource;
    private String textView1;
    private String textView2;

    public JoinedEventItem(int imageResource, String textView1, String textView2) {
        this.imageResource = imageResource;
        this.textView1 = textView1;
        this.textView2 = textView2;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTextView1() {
        return textView1;
    }

    public String getTextView2() {
        return textView2;
    }
}
