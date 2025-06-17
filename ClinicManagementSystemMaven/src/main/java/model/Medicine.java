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
    private String name;
    private int categoryID;
    private int amount;
    private float price;
    private String Ingredient;
    private String Usage; 
    private String Preservation; 
    private LocalDate expiration_date;
    private LocalDate manufacture_date;
    private int quantity;
    private int unitID;
    private int warehouseID;
    
    public Medicine() {
    }

    public Medicine(int ID, String name, int categoryID, int amount, float price, String Ingredient, String Usage, String Preservation, LocalDate expiration_date, LocalDate manufacture_date, int quantity, int unitID, int warehouseID) {
        this.ID = ID;
        this.name = name;
        this.categoryID = categoryID;
        this.amount = amount;
        this.price = price;
        this.Ingredient = Ingredient;
        this.Usage = Usage;
        this.Preservation = Preservation;
        this.expiration_date = expiration_date;
        this.manufacture_date = manufacture_date;
        this.quantity = quantity;
        this.unitID = unitID;
        this.warehouseID = warehouseID;
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

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
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

    public String getIngredient() {
        return Ingredient;
    }

    public void setIngredient(String Ingredient) {
        this.Ingredient = Ingredient;
    }

    public String getUsage() {
        return Usage;
    }

    public void setUsage(String Usage) {
        this.Usage = Usage;
    }

    public String getPreservation() {
        return Preservation;
    }

    public void setPreservation(String Preservation) {
        this.Preservation = Preservation;
    }

    public LocalDate getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(LocalDate expiration_date) {
        this.expiration_date = expiration_date;
    }

    public LocalDate getManufacture_date() {
        return manufacture_date;
    }

    public void setManufacture_date(LocalDate manufacture_date) {
        this.manufacture_date = manufacture_date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public int getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(int warehouseID) {
        this.warehouseID = warehouseID;
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
