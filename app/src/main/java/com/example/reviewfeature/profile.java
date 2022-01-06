package com.example.reviewfeature;

public class profile {
    String name;
    String review;

    public profile(String name, String review) {
        this.name = name;
        this.review = review;
    }


    public String getName() {

        return name;
    }

    public String getReview() {

        return review;
    }
}
