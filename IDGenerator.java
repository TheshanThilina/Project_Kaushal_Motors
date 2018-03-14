/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Vinnie
 */
public class IDGenerator {
    SimpleDateFormat sdt;
    Date date;
    String buyingID;
    
    public String GenBuyingID(){
        sdt=new SimpleDateFormat("YYYYMMddhhmmss");
        date=new Date();
        buyingID="BID"+sdt.format(date);
        return buyingID;
    }
}
