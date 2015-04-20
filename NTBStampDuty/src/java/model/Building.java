/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Phuc
 */
public class Building {
    private int buildingID;
    private int landID;
    private int buidingTypeID;
    private String buildingName;
    private int addressID;
    private int floors;
    private int rooms;
    private int houses;
    private int shops;
    private Date dateContructed;
    private int completedPercent;

    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

    public int getLandID() {
        return landID;
    }

    public void setLandID(int landID) {
        this.landID = landID;
    }

    public int getBuidingTypeID() {
        return buidingTypeID;
    }

    public void setBuidingTypeID(int buidingTypeID) {
        this.buidingTypeID = buidingTypeID;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getHouses() {
        return houses;
    }

    public void setHouses(int houses) {
        this.houses = houses;
    }

    public int getShops() {
        return shops;
    }

    public void setShops(int shops) {
        this.shops = shops;
    }

    public Date getDateContructed() {
        return dateContructed;
    }

    public void setDateContructed(Date dateContructed) {
        this.dateContructed = dateContructed;
    }

    public int getCompletedPercent() {
        return completedPercent;
    }

    public void setCompletedPercent(int completedPercent) {
        this.completedPercent = completedPercent;
    }
    
    
}
