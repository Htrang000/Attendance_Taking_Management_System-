/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Feature;

/**
 *
 * @author Admin
 */
public class FeatureDBContext extends DBContext implements IDBContext<Feature> {

    @Override
    public ArrayList<Feature> getList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Feature get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Feature param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Feature param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Feature param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Set<Feature> getListByRoleID(int roleId, String featureUrl) {
        Set<Feature> features = new HashSet<>();
        String sql = "SELECT f.Feature_id, f.Feature_url FROM Feature f JOIN Role_Feature rf ON f.Feature_id = rf.Feature_id\n"
                + "WHERE f.Feature_url = ? AND rf.Role_id = ?";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, featureUrl);
            stm.setInt(2, roleId);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Feature f = new Feature();
                f.setFeatureId(rs.getInt("Feature_id"));
                f.setFeatureUrl(rs.getString("Feature_url"));
                features.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeatureDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return features;
    }

    
    public static void main(String[] args) {
        FeatureDBContext fdbc = new FeatureDBContext();
        Set<Feature> features = fdbc.getListByRoleID(1, "/instructor/scheduleOfWeek");
        System.out.println(features.size());
    }
}
