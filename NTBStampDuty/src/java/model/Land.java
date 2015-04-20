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
    private int addressID;
    private String buildingTypes;
    private String buildingPlan;
    private int buildStatus;
    private String img;

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

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
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

    public int getBuildStatus() {
        return buildStatus;
    }

    public void setBuildStatus(int buildStatus) {
        this.buildStatus = buildStatus;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
