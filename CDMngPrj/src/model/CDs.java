/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;


import java.io.Serializable;

public class CDs implements Serializable, Comparable {
    private int ID;
    private String name;
    private String type;
    private String title;
    private double price;
    private int year;

    public CDs() {
    }

    public CDs(int ID, String title, String name, String type, double price, int year) {
        this.ID = ID;
        this.title = title;
        this.name = name;
        this.type = type;
        this.price = price;
        this.year = year;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

   

    
    public void print() {
        System.out.println(ID + ", " + title + ", " + name + ", " + type + ", "
                + price + ", " + year);
    }
    
    @Override
    public int compareTo(Object o) {
        return this.getName().compareTo(((CDs)o).getName());
    }
     
}
