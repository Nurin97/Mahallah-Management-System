/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment_oop;

/**
 *
 * @author LENOVO
 */


public class Student extends Person 
{
    private String position; // e.g., committee role

    public Student(String name, int ID, String email, int phoneNumber, String position) {
        super(name, ID, email, phoneNumber);
        this.position = position;
    }

    @Override
    public void displayInfo() 
    {
        System.out.println("Student Name: " + name);
        System.out.println("ID: " + ID);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Course: " + position);
    }

    @Override
    public String toFileString() 
    {
        return "Student," + super.toFileString() + "," + position;
    }
}
