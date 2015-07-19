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
public class CompareDate {

    public int compareDate(int period, Date date1, Date date2) {
        int status = 0;
        int compare = date2.compareTo(date1);
        int y = date2.getYear() - date1.getYear();
        int m = date2.getMonth() - date1.getMonth();
        int d = date2.getDay() - date1.getDay();
        switch (compare) {
            case -1:
                status = -1;
            case 0:
                status = -1;
                break;
            case 1:
                if (y == 0) {
                    if (m <= 1 && m > 0) {
                        status = 1;
                    } else if (m <= 0) {
                        if (d > 0) {
                            status = 1;
                        } else {
                            status = -1;
                        }
                    }
                } else if (y < 0) {
                    status = -1;
                } else {
                    status = 0;
                }
                break;
        }
        return status;
    }

    public int compareDataPercent(int flat, int percent) {
        int status = 0;
        if (flat == 1 && percent < 100) {
            status = 1;
        } else if (flat == -1 && percent < 100) {
            status = -1;
        }else{
            status = 0;
        }
        return status;
    }
}
