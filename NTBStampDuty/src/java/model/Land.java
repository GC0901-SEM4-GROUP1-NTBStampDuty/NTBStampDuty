/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Phuc
 */
public class Land {
    private int landID;
    private int size;
    private String addressID;
    private String buildingTypes;
    private String buildingPlan;
    private String buildStatus;
    private String img;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLandID() {
        return landID;
    }

    public void setLandID(int landID) {
        this.landID = landID;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getAddressID() {
        return addressID;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public String getBuildingTypes() {
        return buildingTypes;
    }

    public void setBuildingTypes(String buildingTypes) {
        this.buildingTypes = buildingTypes;
    }

    public String getBuildingPlan() {
        return buildingPlan;
    }

    public void setBuildingPlan(String buildingPlan) {
        this.buildingPlan = buildingPlan;
    }

    public String getBuildStatus() {
        return buildStatus;
    }

    public void setBuildStatus(String buildStatus) {
        this.buildStatus = buildStatus;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

   
}
