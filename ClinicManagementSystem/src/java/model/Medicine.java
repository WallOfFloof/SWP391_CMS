/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDate;
/**
 *
 * @author admin
 */
public class Medicine {
    private int ID;
    private int lot_number;
    private String name;
    private int amount;
    private float price;
    private LocalDate manufacture_date;
    private LocalDate expiration_date;
    private LocalDate import_date;

    public Medicine() {
    }

    public Medicine(int ID, int lot_number, String name, int amount, float price, LocalDate manufacture_date, LocalDate expiration_date, LocalDate import_date) {
        this.ID = ID;
        this.lot_number = lot_number;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.manufacture_date = manufacture_date;
        this.expiration_date = expiration_date;
        this.import_date = import_date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getLot_number() {
        return lot_number;
    }

    public void setLot_number(int lot_number) {
        this.lot_number = lot_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDate getManufacture_date() {
        return manufacture_date;
    }

    public void setManufacture_date(LocalDate manufacture_date) {
        this.manufacture_date = manufacture_date;
    }

    public LocalDate getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(LocalDate expiration_date) {
        this.expiration_date = expiration_date;
    }

    public LocalDate getImport_date() {
        return import_date;
    }

    public void setImport_date(LocalDate import_date) {
        this.import_date = import_date;
    }
    
    public boolean dateChecker(LocalDate manufacture_date, LocalDate expiration_date, LocalDate import_date){
        if (manufacture_date.isAfter(expiration_date)){
            System.out.println("Invalid date");
            return false;
        }
        if (expiration_date.isAfter(LocalDate.now())){
            System.out.println("Medicine has expired");
            return false;
        }
        if (manufacture_date.isAfter(import_date) || expiration_date.isBefore(import_date)){
            System.out.println("Invalid import date");
            return false;
        }
        return true;
    } 
}
