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
    private int BuildingID;
    private int LandID;
    private int BuidingTypeID;
    private String BuildingName;
    private int AddressID;
    private int Floors;
    private int Rooms;
    private int Houses;
    private int Shops;
    private Date DateContructed;
    private int CompletedPercent;
    private String Img;

    public int getBuildingID() {
        return BuildingID;
    }

    public void setBuildingID(int BuildingID) {
        this.BuildingID = BuildingID;
    }

    public int getLandID() {
        return LandID;
    }

    public void setLandID(int LandID) {
        this.LandID = LandID;
    }

    public int getBuidingTypeID() {
        return BuidingTypeID;
    }

    public void setBuidingTypeID(int BuidingTypeID) {
        this.BuidingTypeID = BuidingTypeID;
    }

    public String getBuildingName() {
        return BuildingName;
    }

    public void setBuildingName(String BuildingName) {
        this.BuildingName = BuildingName;
    }

    public int getAddressID() {
        return AddressID;
    }

    public void setAddressID(int AddressID) {
        this.AddressID = AddressID;
    }

    public int getFloors() {
        return Floors;
    }

    public void setFloors(int Floors) {
        this.Floors = Floors;
    }

    public int getRooms() {
        return Rooms;
    }

    public void setRooms(int Rooms) {
        this.Rooms = Rooms;
    }

    public int getHouses() {
        return Houses;
    }

    public void setHouses(int Houses) {
        this.Houses = Houses;
    }

    public int getShops() {
        return Shops;
    }

    public void setShops(int Shops) {
        this.Shops = Shops;
    }

    public Date getDateContructed() {
        return DateContructed;
    }

    public void setDateContructed(Date DateContructed) {
        this.DateContructed = DateContructed;
    }

    public int getCompletedPercent() {
        return CompletedPercent;
    }

    public void setCompletedPercent(int CompletedPercent) {
        this.CompletedPercent = CompletedPercent;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }
}
