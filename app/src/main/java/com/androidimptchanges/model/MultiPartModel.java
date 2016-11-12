package com.androidimptchanges.model;

import java.util.List;

/**
 * Created by android on 11/12/2016.
 */

public class MultiPartModel {


    /**
     * success : 1
     * pet_details : []
     */

    private String success;
    private List<String> pet_details;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<String> getPet_details() {
        return pet_details;
    }

    public void setPet_details(List<String> pet_details) {
        this.pet_details = pet_details;
    }
}
