/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.land;

/**
 *
 * @author Phuc
 */
public class Land {

    private int landID;
    private int size;
    private String address;
    private int buildingTypes;
    private String img;
    private int price;
    private int available_status;

    public Land() {
    }

    public Land(int landID, int size, String address, int price, int buildingTypes, String img, int available_status) {
        this.landID = landID;
        this.size = size;
        this.address = address;
        this.buildingTypes = buildingTypes;
        this.img = img;
        this.price = price;
        this.available_status = available_status;
    }

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

    public int getBuildingTypes() {
        return buildingTypes;
    }

    public void setBuildingTypes(int buildingTypes) {
        this.buildingTypes = buildingTypes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAvailable_status() {
        return available_status;
    }

    public void setAvailable_status(int available_status) {
        this.available_status = available_status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
