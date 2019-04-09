package com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model;

public class ParcelTrackDetails {
    private int id;
    private String name;
    private double amount;
    private String trackid;
    private String source_city;
    private String destination_city;
    private String errormessages;

    public String getTrackid() {
        return trackid;
    }

    public void setTrackid(String trackid) {
        this.trackid = trackid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSource_city() {
        return source_city;
    }

    public void setSource_city(String source_city) {
        this.source_city = source_city;
    }

    public String getDestination_city() {
        return destination_city;
    }

    public void setDestination_city(String destination_city) {
        this.destination_city = destination_city;
    }

    public String getErrormessages() {
        return errormessages;
    }

    public void setErrormessages(String errormessages) {
        this.errormessages = errormessages;
    }


}
