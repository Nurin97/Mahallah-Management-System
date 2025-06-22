package com.mycompany.assignment_oop;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Registration reg = new Registration();
        SettingsManager settings = SettingsManager.getInstance();

        int choice = -1;

        System.out.println("Welcome to Mahallah Management System!");

        do {
            System.out.println();
            System.out.println("Choose the service:");
            System.out.println("1. Register");
            System.out.println("2. Remove");
            System.out.println("3. View Student Information");
            System.out.println("4. View Staff Information");
            System.out.println("5. Settings");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    reg.registerMenu(sc);
                } else if (choice == 2) {
                    reg.removeMenu(sc);
                } else if (choice == 3) {
                    viewPersonInfo("student", reg);
                } else if (choice == 4) {
                    viewPersonInfo("staff", reg);
                } else if (choice == 5) {
                    settings.settingsMenu(sc);
                    settings.viewAllMahallahs();
                } else if (choice == 6) {
                    System.out.println("Thank you for using Mahallah Management System!");
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
            }
        } while (choice != 6);

        sc.close();
    }

    public static void viewPersonInfo(String type, Registration reg) {
        System.out.println("\n" + type.toUpperCase() + " LIST:");

        if (type.equalsIgnoreCase("student")) {
            if (reg.getStudentList().isEmpty()) {
                System.out.println("No students registered.");
            } else {
                reg.listPeople(reg.getStudentList());
            }
        } else if (type.equalsIgnoreCase("staff")) {
            if (reg.getStaffList().isEmpty()) {
                System.out.println("No staff registered.");
            } else {
                reg.listPeople(reg.getStaffList());
            }
        } else {
            System.out.println("Invalid type.");
        }
    }
}
