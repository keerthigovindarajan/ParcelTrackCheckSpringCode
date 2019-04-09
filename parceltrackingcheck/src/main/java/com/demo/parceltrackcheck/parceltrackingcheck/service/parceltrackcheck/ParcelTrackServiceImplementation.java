package com.demo.parceltrackcheck.parceltrackingcheck.service.parceltrackcheck;

import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.ParcelRegisterSucessfulmodel;
import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.ParcelRegistermodel;
import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.ParcelTrackStatus;
import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.Parceltrackcheckmodel;
import com.demo.parceltrackcheck.parceltrackingcheck.parceltrackcheck.dao.ParcelcheckDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ParcelTrackServiceImplementation implements ParcelTrackService {

    @Autowired
    ParcelcheckDAO parcelcheckDAO;

    @Override
    public boolean loginValidation(String username, String password) {
        return parcelcheckDAO.loginValidation(username, password);
    }

    @Transactional
    @Override
    public int createProfile(Parceltrackcheckmodel parceltrackcheckmodel) {
        return parcelcheckDAO.createProfile(parceltrackcheckmodel);


    }

    @Override
    public Object createNewParcel(ParcelRegistermodel parcelRegistermodel) {
        return parcelcheckDAO.createNewParcel(parcelRegistermodel);

    }

    @Override
    public List<ParcelRegisterSucessfulmodel> getProfiles() {
        return parcelcheckDAO.getProfiles();

    }

    @Override
    public ParcelTrackStatus getTrackStatus(String tid) {
        return parcelcheckDAO.getTrackStatus(tid);

    }
}
