/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment_oop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Registration
{
    private final ArrayList<Student> studentList;
    private final ArrayList<Staff> staffList;

    private final String STUDENT_FILE = "C:\\Users\\LENOVO\\OneDrive - International Islamic University Malaysia\\Documents\\NetBeansProjects\\PROJECT_OOP\\src\\MahallahManagementSystem\\Student.txt";
    private final String STAFF_FILE = "C:\\Users\\LENOVO\\OneDrive - International Islamic University Malaysia\\Documents\\NetBeansProjects\\PROJECT_OOP\\src\\MahallahManagementSystem\\Staff.txt";

    public Registration() //1st choice
    {
        studentList = new ArrayList<>();
        staffList = new ArrayList<>();
    }

    public void registerMenu(Scanner sc) //menu 1st choice
    {
        System.out.println("Registration");
        System.out.println("\nIs this student or staff?");
        System.out.println("1 - Student");
        System.out.println("2 - Staff");
        System.out.print("Enter choice: ");
        int type = getValidChoice(sc, 1, 2);

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        int id = getValidInt(sc, "Enter matric/staff ID number: ");

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        int phone = getValidInt(sc, "Enter phone number: ");

        System.out.print("Enter Kulliyyah (for student) or Position (for staff): ");
        String position = sc.nextLine();

        Mahallah selectedMahallah = Mahallah.chooseMahallah(sc);
        if (selectedMahallah == null) 
        {
            System.out.println("No Mahallah selected. Registration halted.");
            return;
        }

        Block selectedBlock = Mahallah.chooseBlock(sc, selectedMahallah);
        String floor = Mahallah.chooseFloor(sc);
        String roomNum = Room.chooseRoom(sc);
        String compartment = Room.chooseCompartment(sc);

        if (type == 1) 
        {
            studentList.add(new Student(name, id, email, phone, position,
                    selectedMahallah.getName(), String.valueOf(selectedBlock.getBlockName()), floor, Integer.parseInt(roomNum), compartment));
        } 
        else 
        {
            staffList.add(new Staff(name, id, email, phone, position,
                    selectedMahallah.getName(), String.valueOf(selectedBlock.getBlockName()), floor, Integer.parseInt(roomNum), compartment));
        }

        saveData();

        System.out.println("\nRegistration complete!");
        System.out.println("Welcome, " + name + "!");
        System.out.println("Location: Mahallah " + selectedMahallah.getName() + ", Block " + selectedBlock.getBlockName()
                + ", Floor " + floor + ", Room " + roomNum + ", Compartment: " + compartment);
    }

    public void removeMenu(Scanner sc) //2nd choice
    {
        System.out.println("\nWhich one would you like to remove?");
        System.out.println("1. Student");
        System.out.println("2. Staff");
        System.out.print("Enter choice: ");

        int type = getValidChoice(sc, 1, 2);

        if (type == 1) 
        {
            if (studentList.isEmpty()) 
            {
                System.out.println("No students to remove.");
                return;
            }
            listPeople(studentList);
            System.out.print("Enter index to remove: ");
            int idx = getValidIndex(sc, studentList.size());
            studentList.remove(idx - 1);
            System.out.println("Student removed.");
        } 
        else 
        {
            if (staffList.isEmpty()) 
            {
                System.out.println("No staff to remove.");
                return;
            }
            listPeople(staffList);
            System.out.print("Enter index to remove: ");
            int idx = getValidIndex(sc, staffList.size());
            staffList.remove(idx - 1);
            System.out.println("Staff removed.");
        }

        saveData();
    }

    private int getValidInt(Scanner sc, String prompt) //event handling int
    {
        int num;
        while (true) 
        {
            System.out.print(prompt);
            if (sc.hasNextInt()) 
            {
                num = sc.nextInt();
                sc.nextLine();
                break;
            } 
            else 
            {
                System.out.println("Invalid input. Enter a number.");
                sc.nextLine();
            }
        }
        return num;
    }

    private int getValidChoice(Scanner sc, int min, int max) //event handling
    {
        int choice;
        while (true) 
        {
            if (sc.hasNextInt()) 
            {
                choice = sc.nextInt();
                sc.nextLine();
                if (choice >= min && choice <= max) 
                {
                    return choice;
                } 
                else 
                {
                    System.out.print("Invalid choice. Enter between " + min + " and " + max + ": ");
                }
            } 
            else 
            {
                System.out.print("Invalid input. Enter a number: ");
                sc.nextLine();
            }
        }
    }

    private int getValidIndex(Scanner sc, int size) //event handling
    {
        int idx;
        while (true) 
        {
            if (sc.hasNextInt()) 
            {
                idx = sc.nextInt();
                sc.nextLine();
                if (idx >= 1 && idx <= size) return idx;
                else System.out.print("Invalid index. Enter a number between 1 and " + size + ": ");
            } 
            else 
            {
                System.out.print("Invalid input. Enter a number: ");
                sc.nextLine();
            }
        }
    }

    public void listPeople(ArrayList<? extends Person> list) //event handling
    {
        int i = 1;
        for (Person p : list) 
        {
            System.out.println(i + ". ");
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
    
    public void addStudent(Student student) 
    {
        studentList.add(student);
    }


    public void saveData() //event handling save data
    {
    try (PrintWriter sw = new PrintWriter(new FileWriter(STUDENT_FILE))) 
    {
        for (Student s : studentList) 
        {
            sw.println(s.toFileString());
        }
    } catch (IOException e) 
    {
        System.out.println("Error saving students: " + e.getMessage());
    }

    try (PrintWriter sw = new PrintWriter(new FileWriter(STAFF_FILE))) {
        for (Staff s : staffList) {
            sw.println(s.toFileString());
        }
    } catch (IOException e) {
        System.out.println("Error saving staff: " + e.getMessage());
    }
}

    public void loadData() //event handling load data
    {
        File sFile = new File(STUDENT_FILE); //load std
        if (sFile.exists()) 
        {
            try (BufferedReader br = new BufferedReader(new FileReader(sFile))) {
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] parts = line.split(",");
                    if (parts.length >= 10) 
                    {
                        for (int i = 0; i < parts.length; i++) 
                        {
                            parts[i] = parts[i].trim();
                        }
                        studentList.add(new Student(parts[0], Integer.parseInt(parts[1]), parts[2],
                                Integer.parseInt(parts[3]), parts[4], parts[5], parts[6], parts[7],
                                Integer.parseInt(parts[8]), parts[9]));
                    }
                }
            } catch (IOException e) 
            {
                System.out.println("Error loading students: " + e.getMessage());
            }
        }

        File stfFile = new File(STAFF_FILE); //load staff
        if (stfFile.exists()) 
        {
            try (BufferedReader br = new BufferedReader(new FileReader(stfFile))) 
            {
                String line;
                while ((line = br.readLine()) != null) 
                {
                    String[] parts = line.split(",");
                    if (parts.length >= 10) {
                        for (int i = 0; i < parts.length; i++) 
                        {
                            parts[i] = parts[i].trim();
                        }
                        staffList.add(new Staff(parts[0], Integer.parseInt(parts[1]), parts[2],
                                Integer.parseInt(parts[3]), parts[4], parts[5], parts[6], parts[7],
                                Integer.parseInt(parts[8]), parts[9]));
                    }
                }
            } catch (IOException e) 
            {
                System.out.println("Error loading staff: " + e.getMessage());
            }
        }
    }
}
