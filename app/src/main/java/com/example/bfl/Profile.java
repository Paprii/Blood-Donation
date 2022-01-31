package com.example.bfl;

/**
 * This class help to set and get data into the database
 * this class acts like model in this project as it manages data for the database
 */
public class Profile {
    /**
     * Puts Name of the Donor interested for the registration into the DB
     */
    String name;
    /**
     * Puts Email of the Donor interested for the registration into the DB
     */
    String email;
    /**
     * Puts Password of the Donor interested for the registration into the DB
     */
    String password;
    /**
     * Puts Bloodgroup of the Donor interested for the registration into the DB
     */
    String bloodGroup;
    /**
     * Puts Weight of the Donor interested for the registration into the DB
     */
    String weight;
    /**
     * Puts Contact Number of the Donor interested for the registration into the DB
     */
    String phone;

    /**
     * Empty Constructor
     */
    public Profile() {
    }

    /**
     * This Constructor Initiates Donor's Registration Data into DB
     *
     * @param name       Name of The Donor
     * @param email      Email of the Donor
     * @param bloodGroup Bloodgroup of the Donor
     * @param password   Password of the Donor
     * @param phone      ContactNumber of the Donor
     */
    public Profile(String name, String email, String bloodGroup,String weight, String password, String phone) {
        this.name = name;
        this.email = email;
        this.bloodGroup = bloodGroup;
        this.weight=weight;
        this.password = password;
        this.phone = phone;
    }

    /**
     * This Constructor Initiates Donor's Registration Data into DB
     * @param name Name of The Donor
     * @param bloodGroup Bloodgroup of the Donor
     * @param phone  ContactNumber of the Donor
     * @param email Email of the Donor
     * @param weight Weight of the donor
     */
    public Profile(String name, String bloodGroup, String phone, String email,String weight) {
        this.name = name;
        this.email = email;
        this.bloodGroup = bloodGroup;
        this.weight=weight;
        this.phone = phone;
    }

    /**
     * Retrieves Donor's Name from the DB
     *
     * @return Donor's Name
     */
    public String getName() {
        return name;
    }

    /**
     * Puts Donor's Name into the DB
     *
     * @param name Donor's Registered Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves Donor's Email from the DB
     *
     * @return Donor's Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Puts Donor's Email into the DB
     *
     * @param name Donor's Registered Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves Donor's BloodGroup from the DB
     *
     * @return Donor's BloodGroup
     */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /**
     * Puts Donor's Bloodgroup into the DB
     *
     * @param name Donor's Registered Bloodgroup
     */
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    /**
     * Retrieves Donor's Weight from the DB
     *
     * @return Donor's Weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     * Puts Donor's Weight into the DB
     *
     * @param weight Donor's Registered Weight
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }
    /**
     * Retrieves Donor's Contact Number from the DB
     *
     * @return Donor's Contact Number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Puts Donor's Contact Number into the DB
     *
     * @param name Donor's Registered  Contact Number
     *             public void setPhone(String phone) {
     *             this.phone = phone;
     *             }
     *             /**
     *             Retrieves Donor's Password from the DB
     * @return Donor's Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Puts Donor's Password into the DB
     *
     * @param name Donor's Registered Password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
