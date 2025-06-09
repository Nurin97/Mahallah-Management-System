package com.mycompany.assignment_oop;

import java.util.ArrayList;

public class Block {
    private char blockName;
    private ArrayList<Room> rooms;

    public Block(char blockName) {
        this.blockName = blockName;
        this.rooms = new ArrayList<>();
    }

    public char getBlockName() {
        return blockName;
    }
    @Override
    public String toString() {
        return String.valueOf(getBlockName()); // or use your block's display name
    }
    public void setBlockName(char name) { ... }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public boolean removeRoom(String roomNumber) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getRoomNumber().equals(roomNumber)) {
                rooms.remove(i);
                return true;
            }
        }
        return false;
    }

    public void displayRooms() {
        System.out.println("Block " + blockName);
        if (rooms.isEmpty()) {
            System.out.println("  No rooms.");
        } else {
            for (Room room : rooms) {
                System.out.print("  ");
                room.display();
            }
        }
    }

    public ArrayList<Room> getRoomsByFloor(String floor) {
        ArrayList<Room> roomsOnFloor = new ArrayList<>();
        for (Room r : rooms) {
            if (r.getFloor().equalsIgnoreCase(floor)) {
                roomsOnFloor.add(r);
            }
        }
        return roomsOnFloor;
    }
    
}
