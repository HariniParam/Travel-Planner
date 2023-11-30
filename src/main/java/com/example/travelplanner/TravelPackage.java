package com.example.travelplanner;

public class TravelPackage {
    private int packageId;
    private String packageName;
    private String packageDescription;
    private String destination;
    private int duration;
    private double price;
    private String inclusions;
    private String exclusions;
    private String startDate;
    private String endDate;
    private int availableSeats;
    private String packageImages;
    private double averageRating;
    private String contactEmail;
    private String contactPhone;


    public TravelPackage() {

        this.packageId = 0;
        this.packageName = "";
        this.packageDescription = "";
        this.destination = "";
        this.duration = 0;
        this.price = 0;
        this.inclusions = "";
        this.exclusions = "";
        this.startDate = "";
        this.endDate = "";
        this.availableSeats = 0;
        this.packageImages = "";
        this.averageRating = 0;
        this.contactEmail = "";
        this.contactPhone = "";
    }

    public TravelPackage(int packageId, String packageName, String packageDescription, String destination, int duration, double price, String inclusions, String exclusions, String startDate, String endDate, int availableSeats, String packageImages, double averageRating, String ratingsReviews, String contactEmail, String contactPhone) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.packageDescription = packageDescription;
        this.destination = destination;
        this.duration = duration;
        this.price = price;
        this.inclusions = inclusions;
        this.exclusions = exclusions;
        this.startDate = startDate;
        this.endDate = endDate;
        this.availableSeats = availableSeats;
        this.packageImages = packageImages;
        this.averageRating = averageRating;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }


    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInclusions() {
        return inclusions;
    }

    public void setInclusions(String inclusions) {
        this.inclusions = inclusions;
    }

    public String getExclusions() {
        return exclusions;
    }

    public void setExclusions(String exclusions) {
        this.exclusions = exclusions;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getPackageImages() {
        return packageImages;
    }

    public void setPackageImages(String packageImages) {
        this.packageImages = packageImages;
    }
    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}

