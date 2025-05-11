/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment_oop;

/**
 *
 * @author LENOVO
 */
import java.io.*;
import java.util.ArrayList;

public class MahallahOffice 
{
    
    private ArrayList<Person> people = new ArrayList<>();


    public void addPerson(Person person) 
    {
        people.add(person);
    }

    public void removePerson(Person person) 
    {
        people.remove(person);
    }

    public void displayPeople() 
    {
        for (Person p : people) 
        {
            p.displayInfo();
            System.out.println("-----");
        }
    }

    public ArrayList<Person> getPeople() 
    {
        return people;
    }
    
}
