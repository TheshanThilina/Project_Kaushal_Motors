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
import models.BuyingOrder;
import models.Item;
import models.PurchaseDetails;

/**
 *
 * @author Vinnie
 */
public class BuyingOrderController {
    public static boolean addBuyingOrder(BuyingOrder buyingOrder, ArrayList<PurchaseDetails> PurchaseDetailsList, ArrayList<Item> itemList) throws ClassNotFoundException, SQLException {
        /*public class BuyingOrder {
    private String buyingId;
    private Date purchaseDate;
    private double totalCost;*/
        
        String query = "insert into BuyingOrder values(?, ?, ?)";
        Connection connection = DBConnection.getDBConnection().getConnection();
        connection.setAutoCommit(false);
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setObject(1, buyingOrder.getBuyingId());
            prepareStatement.setObject(2, buyingOrder.getPurchaseDate());
            prepareStatement.setObject(3, buyingOrder.getTotalCost());
            int res = prepareStatement.executeUpdate();
            if (res > 0) {
                boolean addPurchaseDetail = PurchaseDetailsController.addPurchaseDetail(PurchaseDetailsList);
                if (addPurchaseDetail) {
                    boolean updateItemUnitPrice= ItemController.updateItemUnitPrice(itemList);
                    if (updateItemUnitPrice) {
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                    }
                } else {
                    connection.rollback();
                }
            }
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
