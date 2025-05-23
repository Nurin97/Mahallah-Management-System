/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment_oop;

/**
 *
 * @author LENOVO
 */
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
    }

    public void registerMenu(Scanner sc) 
    {
        System.out.println("Registration");

       
        String mahallahName = Mahallah.chooseMahallah(sc);
        String block = Mahallah.chooseBlock(sc);
        String floor = Mahallah.chooseFloor(sc);
        int roomNum = Room.chooseRoom(sc);
        String compartment = Room.chooseCompartment(sc);

        sc.nextLine();

        System.out.print("Is this a Student or Staff? (1-Student, 2-Staff): ");
        int type = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter phone number: ");
        int phone = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter position: ");
        String position = sc.nextLine();

        if (type == 1) 
        {
            Student s = new Student(name, id, email, phone, position);
            studentList.add(s);
        } 
        else if (type == 2) 
        {
            Staff staff = new Staff(name, id, email, phone, position);
            staffList.add(staff);
        } 
        else 
        {
            System.out.println("Invalid type.");
        }

        System.out.println("Registration complete!");
        System.out.println("Mahallah: " + mahallahName + ", Block " + block + ", Floor " + floor + ", Room " + roomNum + ", Compartment: " + compartment);
    }

    public void removeMenu(Scanner sc) 
    {
        System.out.println("\nWhich one would you like to remove?");
        System.out.println("1. Student");
        System.out.println("2. Staff");
        System.out.print("Enter choice: ");
        int type = sc.nextInt();

        if (type == 1) 
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
        else if (type == 2) 
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
