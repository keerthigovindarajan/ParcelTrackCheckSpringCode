package com.demo.parceltrackcheck.parceltrackingcheck.parceltrackcheck.dao;

import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.ParcelRegisterSucessfulmodel;
import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.ParcelRegistermodel;
import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.ParcelTrackStatus;
import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.Parceltrackcheckmodel;

import java.util.List;

/**
 * Created by mahithaa.
 */
public interface ParcelcheckDAO {
    public boolean loginValidation(String username, String password);

    public int createProfile(Parceltrackcheckmodel parceltrackcheckmodel);

    public Object createNewParcel(ParcelRegistermodel parcelRegistermodel);

    public List<ParcelRegisterSucessfulmodel> getProfiles();

    public ParcelTrackStatus getTrackStatus(String tid);
}
