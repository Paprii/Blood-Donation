/**
 * A java class named "profile" to send the name and review
 */
package com.example.reviewfeature;


/**
 * A class named 'profile'
 */
public class profile {
    String name;
    String review;

    /**
     * Constuctor of 'profile' class
     * @param name
     * @param review
     */
    public profile(String name, String review) {
        this.name = name;
        this.review = review;
    }

    /**
     * GET method()
     * @return name
     */
    public String getName() {

        return name;
    }

    /**
     *  Get method()
     * @return review
     */
    public String getReview() {

        return review;
    }
}
