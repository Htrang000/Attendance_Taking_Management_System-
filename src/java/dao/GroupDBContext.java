/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Group;

/**
 *
 * @author Admin
 */
public class GroupDBContext extends DBContext implements IDBContext<Group> {

    @Override
    public ArrayList<Group> getList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Group get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Group param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Group param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Group param) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getGroupName(int groupId) {
        String groupName = null;
        try {
            String sql = "SELECT Group_name FROM [Group] WHERE Group_id = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, groupId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                groupName = rs.getString("Group_name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groupName;
    }

    public ArrayList<Group> getListGroup(int courseID) {
        ArrayList<Group> groups = new ArrayList<>();
        String sql = "SELECT Group_id, Group_name FROM [Group]\n"
                + "WHERE Course_id = ?";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, courseID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Group c = new Group();
                c.setGroupId(rs.getInt("Group_id"));
                c.setGroupName(rs.getString("Group_name"));
                groups.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;
    }

    public ArrayList<Group> getListGroup(int courseID, int instructorID) {
        ArrayList<Group> groups = new ArrayList<>();
        String sql = "SELECT DISTINCT g.Group_id, g.Group_name FROM Instructor i JOIN Lesson l"
                + " ON i.Instructor_id = l.Instructor_id\n"
                + "JOIN [Group] g ON g.Group_id = l.Group_id\n"
                + "WHERE i.Instructor_id = ? AND g.Course_id = ?;";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, instructorID);
            stm.setInt(2, courseID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Group c = new Group();
                c.setGroupId(rs.getInt("Group_id"));
                c.setGroupName(rs.getString("Group_name"));
                groups.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;
    }

    public static void main(String[] args) {
        GroupDBContext gdb = new GroupDBContext();
        ArrayList<Group> groups = gdb.getListGroup(43,1);
        System.out.println(groups.size());
    }

}
