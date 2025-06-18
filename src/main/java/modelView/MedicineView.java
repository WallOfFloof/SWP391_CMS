// Model: MedicineDetail.java
package modelView;

public class MedicineView {
    private int medicineId;
    private String name;
    private String categoryName;
    private String unitName;
    private double price;
    private String ingredient;
    private String usage;
    private String expiration;
    private String distributorName;
    private String distributorAddress;
    private String distributorEmail;
    private String distributorPhone;
    private String imageUrl; // placeholder for future

    public MedicineView() {
    }

    public MedicineView(int medicineId, String name, String categoryName, String unitName, double price, String ingredient, String usage, String expiration, String distributorName, String distributorAddress, String distributorEmail, String distributorPhone, String imageUrl) {
        this.medicineId = medicineId;
        this.name = name;
        this.categoryName = categoryName;
        this.unitName = unitName;
        this.price = price;
        this.ingredient = ingredient;
        this.usage = usage;
        this.expiration = expiration;
        this.distributorName = distributorName;
        this.distributorAddress = distributorAddress;
        this.distributorEmail = distributorEmail;
        this.distributorPhone = distributorPhone;
        this.imageUrl = imageUrl;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public String getDistributorAddress() {
        return distributorAddress;
    }

    public void setDistributorAddress(String distributorAddress) {
        this.distributorAddress = distributorAddress;
    }

    public String getDistributorEmail() {
        return distributorEmail;
    }

    public void setDistributorEmail(String distributorEmail) {
        this.distributorEmail = distributorEmail;
    }

    public String getDistributorPhone() {
        return distributorPhone;
    }

    public void setDistributorPhone(String distributorPhone) {
        this.distributorPhone = distributorPhone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
