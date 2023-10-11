/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
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
public class Instructor {
    private int instructorId;
    private String instructorCod;
    private String firstName;
    private String lastName;
    private String img;
    private Date dob;
    private boolean gender;
    private String phone;
    private String email;
    private String address;
    private Campus campus;
    private Account account;
}
