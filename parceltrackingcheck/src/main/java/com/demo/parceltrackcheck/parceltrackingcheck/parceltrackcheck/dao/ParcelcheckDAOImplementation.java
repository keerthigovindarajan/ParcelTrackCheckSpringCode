package com.demo.parceltrackcheck.parceltrackingcheck.parceltrackcheck.dao;

import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.ParcelRegisterSucessfulmodel;
import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.ParcelRegistermodel;
import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.ParcelTrackStatus;
import com.demo.parceltrackcheck.parceltrackingcheck.com.demo.parceltrackcheck.model.Parceltrackcheckmodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by mahithaa.
 */
@Slf4j
@Repository
public class ParcelcheckDAOImplementation implements ParcelcheckDAO {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ParcelcheckDAOImplementation.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    //LOGIN VALIDATION
    public boolean loginValidation(String username, String password) {
        try {
            Parceltrackcheckmodel dbValue = (Parceltrackcheckmodel) jdbcTemplate.queryForObject("select * from login where username=?",
                    new Object[]{username}, new BeanPropertyRowMapper(Parceltrackcheckmodel.class));
            String salt = "1p/RCrsiRPZxFSDgg2aT6GPjX9SbHEEoIkbz4Zhv4bA=";
            boolean passwordMatch = PasswordUtils.verifyUserPassword(password, dbValue.getPassword(), salt);
            if (passwordMatch && username.equals(dbValue.getUsername())) {
                return true;
            }
        } catch (Exception e) {
            log.error("invalid credentials", e);
        }
        return false;
    }

    //USER REGISTRATION
    public int createProfile(Parceltrackcheckmodel parceltrackcheckmodel) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String user = parceltrackcheckmodel.getUsername();
        String pass = usercheck(parceltrackcheckmodel.getPassword());
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("insert into login(username,password)values(?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, parceltrackcheckmodel.getUsername());
            preparedStatement.setString(2, pass);
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    //PASSWORD ENCRYPTION
    public String usercheck(String pass) {

        String salt = "1p/RCrsiRPZxFSDgg2aT6GPjX9SbHEEoIkbz4Zhv4bA=";
        String mySecurePassword = PasswordUtils.generateSecurePassword(pass, salt);
        return mySecurePassword;
    }


    //REGISTRATION FOR NEW PARCEL
    public Object createNewParcel(ParcelRegistermodel parcelRegistermodel) {

        //TRACK ID GENERATION
        UUID trackid = UUID.randomUUID();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("insert into register_parcel(track_id,name,source_address,source_city,destination_address,destination_city,phone_number,weight,kilometer,amount)values(?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(2, parcelRegistermodel.getName());
            preparedStatement.setString(3, parcelRegistermodel.getSource_address());
            preparedStatement.setString(4, parcelRegistermodel.getSource_city());
            preparedStatement.setString(5, parcelRegistermodel.getDestination_address());


            //APPENDING DESTINATION CITY
            String str = parcelRegistermodel.getDestination_city();
            preparedStatement.setString(6, str);
            String substr_destination_city = str.substring(0, 4);
            String trackid_append_destination_city = substr_destination_city + String.valueOf(trackid);

            preparedStatement.setString(1, trackid_append_destination_city);
            preparedStatement.setString(7, parcelRegistermodel.getPhone_number());
            preparedStatement.setInt(8, parcelRegistermodel.getWeight());
            preparedStatement.setDouble(9, parcelRegistermodel.getKilometer());
            preparedStatement.setDouble(10, parcelRegistermodel.getAmount());
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey() == null ? null : keyHolder.getKey().intValue();
    }

    //Track id + amount display method
    public List<ParcelRegisterSucessfulmodel> getProfiles() {
        List<ParcelRegisterSucessfulmodel> profileList = new ArrayList<>();
        Collection<Map<String, Object>> rows = null;
        rows = jdbcTemplate.queryForList("SELECT * FROM register_parcel where id=? ");
        rows.stream().map((row) -> {
            ParcelRegisterSucessfulmodel parcelRegisterSucessfulmodel = new ParcelRegisterSucessfulmodel();
            parcelRegisterSucessfulmodel.setId((int) row.get("id"));
            parcelRegisterSucessfulmodel.setTrackid((String) row.get("track_id"));
            parcelRegisterSucessfulmodel.setName((String) row.get("name"));
            parcelRegisterSucessfulmodel.setAmount((int) row.get("amount"));
            return parcelRegisterSucessfulmodel;
        }).forEach((ss) -> {
            profileList.add(ss);
        });
        return profileList;
    }


    public ParcelTrackStatus getTrackStatus(String track_id) {

        ParcelTrackStatus parcelTrackStatus = new ParcelTrackStatus();
        String source = null;
        try {
            source = jdbcTemplate.queryForObject(
                    "select source_city  from register_parcel where track_id=?",
                    String.class, track_id);
            String destination = jdbcTemplate.queryForObject(
                    "select destination_city from register_parcel where track_id=?",
                    String.class, track_id);

            parcelTrackStatus.setSource_city(source);
            parcelTrackStatus.setDestination_city(destination);
        } catch (EmptyResultDataAccessException e) {
            parcelTrackStatus.setErrormessages("Invalid Trackid");
        }
        return parcelTrackStatus;
    }


}








