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
public class BuildingDetail {
    public String landName;
    public String buildingType;
    public String buildingName;
    public int floors;
    public int rooms;
    public int houses;
    public int shops;
    public int img;
    public int chosen_status;

    public BuildingDetail(String landName, String buildingType, String buildingName, int floors, int rooms, int houses, int shops, int img, int chosen_status) {
        this.landName = landName;
        this.buildingType = buildingType;
        this.buildingName = buildingName;
        this.floors = floors;
        this.rooms = rooms;
        this.houses = houses;
        this.shops = shops;
        this.img = img;
        this.chosen_status = chosen_status;
    }

    public String getLandName() {
        return landName;
    }

    public void setLandName(String landName) {
        this.landName = landName;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getChosen_status() {
        return chosen_status;
    }

    public void setChosen_status(int chosen_status) {
        this.chosen_status = chosen_status;
    }
    
    
}
