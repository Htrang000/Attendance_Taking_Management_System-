/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
   private int studentId;
   private String studentCode;
   private String name;
   private String img;
   private Date dob;
   private boolean gender;
   private String phone;
   private String email;
   private String address;
   private Account account;
   private Set<Group> groups;
   private int percentageAttendance;
}
