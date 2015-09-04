/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.building;

/**
 *
 * @author Phuc
 */
public class BuildingDetail {
    public int landID;
    public int buildingTypeID;
    public String buildingName;
    public int floors;
    public int rooms;
    public int houses;
    public int shops;
    public String img;
    public int chosen_status;

    public BuildingDetail(int landID, int buildingTypeID, String buildingName, int floors, int rooms, int houses, int shops, String img, int chosen_status) {
        this.landID = landID;
        this.buildingTypeID = buildingTypeID;
        this.buildingName = buildingName;
        this.floors = floors;
        this.rooms = rooms;
        this.houses = houses;
        this.shops = shops;
        this.img = img;
        this.chosen_status = chosen_status;
    }

    public int getLandID() {
        return landID;
    }

    public void setLandID(int landID) {
        this.landID = landID;
    }

    public int getBuildingTypeID() {
        return buildingTypeID;
    }

    public void setBuildingTypeID(int buildingTypeID) {
        this.buildingTypeID = buildingTypeID;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getChosen_status() {
        return chosen_status;
    }

    public void setChosen_status(int chosen_status) {
        this.chosen_status = chosen_status;
    }
    
    
}
