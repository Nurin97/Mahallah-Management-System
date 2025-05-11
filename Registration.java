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
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import java.util.ArrayList;
import java.util.Scanner;

public class Registration 
{
    private ArrayList<Student> studentList;
    private ArrayList<Staff> staffList;

   public Registration() 
{
    studentList = new ArrayList<>();
    staffList = new ArrayList<>();

    // student
    studentList.add(new Student("Ammar Johari", 2415327, "ammarjohari@mail.com", 013656, "Computer Science", "Faruq"));
    studentList.add(new Student("Ali Umar", 2415234, "aliomar@mail.com", 12345679, "Electrical Engineering", "Uthman"));
    studentList.add(new Student("Fatimah Zahra", 2415725, "fatimah@mail.com", 12345680, "Mechanical Engineering", "Aminah"));

    // staff
    staffList.add(new Staff("Dr. John Doe", 2337134, "johndoe@mail.com", 98765432, "Principal", "Siddiq"));
    staffList.add(new Staff("Prof. Sarah Ali", 2381430, "sarah@mail.com", 98765433, "Professor", "Aminah"));
    staffList.add(new Staff("Mr. Abu Bakar", 2344100, "abubako@mail.com", 98765434, "Administrator", "Salahuddin"));
}

    
    //method register
   public void registerMenu(Scanner sc) 
{
    System.out.println("Registration");

    String mahallahName = Mahallah.chooseMahallah(sc);
    String block = Mahallah.chooseBlock(sc);
    String floor = Mahallah.chooseFloor(sc);
    int roomNum = Room.chooseRoom(sc);
    String compartment = Room.chooseCompartment(sc);
    sc.nextLine();  

    System.out.println();
    System.out.println("Is this student or staff? ");
    System.out.println("1 - student");
    System.out.println("2 - staff");
    int type = sc.nextInt();
    sc.nextLine(); 

    System.out.println("Enter name: ");
    String name = sc.nextLine();
    
    System.out.println("Enter matric number: ");
    int id = sc.nextInt();
    sc.nextLine(); 
    
    System.out.println("Enter email: ");
    String email = sc.nextLine();
    
    System.out.println("Enter phone number: ");
    int phone = sc.nextInt();
    sc.nextLine(); 

    System.out.println();
    System.out.print("Enter Kulliyah: ");
    String position = sc.nextLine();  

    if (type == 1) // student
    {
        Student s = new Student(name, id, email, phone, position, mahallahName);  // Pass Mahallah
        studentList.add(s);
    } 
    else if (type == 2) // staff
    {
        Staff staff = new Staff(name, id, email, phone, position, mahallahName);  // Pass Mahallah
        staffList.add(staff);
    } 
    else 
    {
        System.out.println("Invalid type.");
    }

    System.out.println("Registration complete!");
    System.out.println("Welcome, " + name + "!");
    System.out.println("Mahallah: " + mahallahName + ", Block " + block + ", Floor " + floor + ", Room " + roomNum + ", Compartment: " + compartment);
}


    
    //method remove
    public void removeMenu(Scanner sc) 
    {
        System.out.println("\nWhich one would you like to remove?");
        System.out.println("1. Student");
        System.out.println("2. Staff");
        System.out.print("Enter choice: ");
        int type = sc.nextInt();

        if (type == 1) //student
        {
            if (studentList.isEmpty()) 
            {
                System.out.println("No students to remove.");
                return;
            }
            listPeople(studentList);
            System.out.print("Enter index to remove: ");
            int idx = sc.nextInt();
            if (idx >= 1 && idx <= studentList.size()) 
            {
                studentList.remove(idx - 1);
                System.out.println("Student removed.");
            } 
            else 
            {
                System.out.println("Invalid index.");
            }
        } 
        else if (type == 2) //staff
        {
            if (staffList.isEmpty()) 
            {
                System.out.println("No staff to remove.");
                return;
            }
            listPeople(staffList);
            System.out.print("Enter index to remove: ");
            int idx = sc.nextInt();
            if (idx >= 1 && idx <= staffList.size()) 
            {
                staffList.remove(idx - 1);
                System.out.println("Staff removed.");
            } 
            else 
            {
                System.out.println("Invalid index.");
            }
        } 
        else 
        {
            System.out.println("Invalid choice.");
        }
    }

    public void listPeople(ArrayList<? extends Person> list) 
    {
        int i = 1;
        for (Person p : list) 
        {
            System.out.print(i + ". ");
            p.displayInfo();
            System.out.println("-----");
            i++;
        }
    }

    public ArrayList<Student> getStudentList() 
    {
        return studentList;
    }

    public ArrayList<Staff> getStaffList() 
    {
        return staffList;
    }
}
