/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IDBContext <T> {
    public ArrayList<T> getList();
    public T get(int id);
    public void insert(T param);
    public void delete(T param);
    public void update(T param);
}
