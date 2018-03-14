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

/**
 *
 * @author Vinnie
 */
public class ItemController {

    public int addItem(Item item) throws ClassNotFoundException, SQLException{
        String sql = "Insert into Item values(?,?,?,?)";  // assume that, database has item table
        Connection conn=(Connection) controllers.DBConnection.getDBConnection();
        PreparedStatement preparedStatement=conn.prepareStatement(sql);
        preparedStatement.setObject(1, item.getItemCode());
        preparedStatement.setObject(2, item.getDescription());
        preparedStatement.setObject(3, item.getUnitPrice());
        
        return preparedStatement.executeUpdate(); 
    }
    public static boolean updateItemUnitPrice(ArrayList<Item> itemList) throws ClassNotFoundException, SQLException {
        int res = 0;
        for (Item item : itemList) {
            String query = "update Item set unitPrice = ? where code = ?";
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setObject(1, item.getUnitPrice());     
            prepareStatement.setObject(2, item.getItemCode());
            res += prepareStatement.executeUpdate();
        }
        if (itemList.size() == res) {
            return true;
        }
        return false;
    }
    
}
