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
    private int LandID;
    private int Size;
    private int AddressID;
    private String BuildingTypes;
    private String BuildingPlan;
    private int BuildStatus;
    private String Img;

    public int getLandID() {
        return LandID;
    }

    public void setLandID(int LandID) {
        this.LandID = LandID;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int Size) {
        this.Size = Size;
    }

    public int getAddressID() {
        return AddressID;
    }

    public void setAddressID(int AddressID) {
        this.AddressID = AddressID;
    }

    public String getBuildingTypes() {
        return BuildingTypes;
    }

    public void setBuildingTypes(String BuildingTypes) {
        this.BuildingTypes = BuildingTypes;
    }

    public String getBuildingPlan() {
        return BuildingPlan;
    }

    public void setBuildingPlan(String BuildingPlan) {
        this.BuildingPlan = BuildingPlan;
    }

    public int getBuildStatus() {
        return BuildStatus;
    }

    public void setBuildStatus(int BuildStatus) {
        this.BuildStatus = BuildStatus;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }
}
