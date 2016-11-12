package com.androidimptchanges.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by android on 11/12/2016.
 */

public class PetDetails {


    /**
     * success : 1
     * petProfile : [{"petId":"101","categoryID":"1","categoryName":"dog","breedID":"1","breedName":"Affenpinscher","primaryPet":"no","findHome":"yes","availableForMate":"","petName":"rrrr","gender":"male","age":"3","description":"retrofit Pet","height":"2","weight":"60","userID":"30","ownerGender":"male","petOwner":"Sahil Desai","fbProfileImage":"http://graph.facebook.com/926752710707196/picture?type=large","latitude":"22.2970129","longitude":"73.1692388"}]
     * petImages : []
     * petMoment : null
     */

    @SerializedName("success")
    private String success;

    @SerializedName("petMoment")
    private Object petMoment;
    /**
     * petId : 101
     * categoryID : 1
     * categoryName : dog
     * breedID : 1
     * breedName : Affenpinscher
     * primaryPet : no
     * findHome : yes
     * availableForMate :
     * petName : rrrr
     * gender : male
     * age : 3
     * description : retrofit Pet
     * height : 2
     * weight : 60
     * userID : 30
     * ownerGender : male
     * petOwner : Sahil Desai
     * fbProfileImage : http://graph.facebook.com/926752710707196/picture?type=large
     * latitude : 22.2970129
     * longitude : 73.1692388
     */

    private List<PetProfileBean> petProfile;
    private List<String> petImages;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Object getPetMoment() {
        return petMoment;
    }

    public void setPetMoment(Object petMoment) {
        this.petMoment = petMoment;
    }

    public List<PetProfileBean> getPetProfile() {
        return petProfile;
    }

    public void setPetProfile(List<PetProfileBean> petProfile) {
        this.petProfile = petProfile;
    }

    public List<String> getPetImages() {
        return petImages;
    }

    public void setPetImages(List<String> petImages) {
        this.petImages = petImages;
    }

    public static class PetProfileBean {
        private String petId;
        private String categoryID;
        private String categoryName;
        private String breedID;
        private String breedName;
        private String primaryPet;
        private String findHome;
        private String availableForMate;
        private String petName;
        private String gender;
        private String age;
        private String description;
        private String height;
        private String weight;
        private String userID;
        private String ownerGender;
        private String petOwner;
        private String fbProfileImage;
        private String latitude;
        private String longitude;

        public String getPetId() {
            return petId;
        }

        public void setPetId(String petId) {
            this.petId = petId;
        }

        public String getCategoryID() {
            return categoryID;
        }

        public void setCategoryID(String categoryID) {
            this.categoryID = categoryID;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getBreedID() {
            return breedID;
        }

        public void setBreedID(String breedID) {
            this.breedID = breedID;
        }

        public String getBreedName() {
            return breedName;
        }

        public void setBreedName(String breedName) {
            this.breedName = breedName;
        }

        public String getPrimaryPet() {
            return primaryPet;
        }

        public void setPrimaryPet(String primaryPet) {
            this.primaryPet = primaryPet;
        }

        public String getFindHome() {
            return findHome;
        }

        public void setFindHome(String findHome) {
            this.findHome = findHome;
        }

        public String getAvailableForMate() {
            return availableForMate;
        }

        public void setAvailableForMate(String availableForMate) {
            this.availableForMate = availableForMate;
        }

        public String getPetName() {
            return petName;
        }

        public void setPetName(String petName) {
            this.petName = petName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getOwnerGender() {
            return ownerGender;
        }

        public void setOwnerGender(String ownerGender) {
            this.ownerGender = ownerGender;
        }

        public String getPetOwner() {
            return petOwner;
        }

        public void setPetOwner(String petOwner) {
            this.petOwner = petOwner;
        }

        public String getFbProfileImage() {
            return fbProfileImage;
        }

        public void setFbProfileImage(String fbProfileImage) {
            this.fbProfileImage = fbProfileImage;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }
}
