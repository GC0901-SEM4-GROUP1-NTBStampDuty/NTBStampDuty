/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author SonNguyen
 */
public class Period {
    private int project_id;
    private String period1;
    private String period2;
    private String period3;
    private int percent;
    private Date p1,p2,p3;  

    public Date getP1() {
        return p1;
    }

    public void setP1(Date p1) {
        this.p1 = p1;
    }

    public Date getP2() {
        return p2;
    }

    public void setP2(Date p2) {
        this.p2 = p2;
    }

    public Date getP3() {
        return p3;
    }

    public void setP3(Date p3) {
        this.p3 = p3;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getPeriod1() {
        return period1;
    }

    public void setPeriod1(String period1) {
        this.period1 = period1;
    }

    public String getPeriod2() {
        return period2;
    }

    public void setPeriod2(String period2) {
        this.period2 = period2;
    }

    public String getPeriod3() {
        return period3;
    }

    public void setPeriod3(String period3) {
        this.period3 = period3;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
    
    
}
