package com.demo.parceltrackcheck.parceltrackingcheck.service.parceltrackcheck;

import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.ParcelRegisterSucessfulmodel;
import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.ParcelRegistermodel;
import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.ParcelTrackStatus;
import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.Parceltrackcheckmodel;

import java.util.List;

public interface ParcelTrackService {
    public boolean loginValidation(String username, String password);

    public int createProfile(Parceltrackcheckmodel parceltrackcheckmodel);

    public Object createNewParcel(ParcelRegistermodel parcelRegistermodel);

    public List<ParcelRegisterSucessfulmodel> getProfiles();

    public ParcelTrackStatus getTrackStatus(String tid);
}
