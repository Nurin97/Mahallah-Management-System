/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment_oop;

/**
 *
 * @author LENOVO
 */
public abstract class Person 
{
    protected String name;
    protected int ID;
    protected String email;
    protected int phoneNumber;

    public Person(String name, int ID, String email, int phoneNumber) 
    {
        this.name = name;
        this.ID = ID;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public abstract void displayInfo();

    public String toFileString() 
    {
        return name + "," + ID + "," + email + "," + phoneNumber;
    }
}
