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
    private int categoryCode;
    private int amount;
    private float price;
    private String Ingredient;
    private String Dosage; 
    private String Usage; 
    private String Preservation; 
    private String Caution; 
    private LocalDate expiration_date;
    private LocalDate manufacture_date;
    private int unitCode;
    
    public Medicine() {
    }
    
    public Medicine(int ID, String name, int categoryCode, int amount, float price, String Ingredient, String Dosage, String Usage, String Preservation, String Caution, LocalDate expiration_date, LocalDate manufacture_date, int unitCode) {
        this.ID = ID;
        this.name = name;
        this.categoryCode = categoryCode;
        this.amount = amount;
        this.price = price;
        this.Ingredient = Ingredient;
        this.Dosage = Dosage;
        this.Usage = Usage;
        this.Preservation = Preservation;
        this.Caution = Caution;
        this.expiration_date = expiration_date;
        this.manufacture_date = manufacture_date;
        this.unitCode = unitCode;
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

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
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

    public String getDosage() {
        return Dosage;
    }

    public void setDosage(String Dosage) {
        this.Dosage = Dosage;
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

    public String getCaution() {
        return Caution;
    }

    public void setCaution(String Caution) {
        this.Caution = Caution;
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

    public int getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(int unitCode) {
        this.unitCode = unitCode;
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
