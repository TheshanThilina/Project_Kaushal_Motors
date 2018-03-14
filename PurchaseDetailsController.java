/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Item;
import models.PurchaseDetails;

/**
 *
 * @author Vinnie
 */
public class PurchaseDetailsController {
    public static boolean addPurchaseDetail(ArrayList<PurchaseDetails> purchaseDetailList) throws ClassNotFoundException, SQLException {
        int res = 0;
        for (PurchaseDetails purchaseDetails : purchaseDetailList) {
            String query = "Insert into PurchaseDetails values(?,?,?,?,2)";
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1,purchaseDetails.getItemCode());
            preparedStatement.setObject(2,purchaseDetails.getBuyingId());
            preparedStatement.setObject(3,purchaseDetails.getPurchaseDiscount());
            preparedStatement.setObject(4,purchaseDetails.getQuantity());
            preparedStatement.setObject(5,purchaseDetails.getUnitPrice());
            res += preparedStatement.executeUpdate();
        }
        if (purchaseDetailList.size() == res) {
            return true;
        }
        return false;
    }
}

