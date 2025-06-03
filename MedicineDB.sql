-- Create the database
CREATE DATABASE Prescription_test;
GO

-- Use the database
USE Prescription_test;
GO

-- Unit table
CREATE TABLE Unit (
    UnitCode INT PRIMARY KEY NOT NULL,
    UnitName NVARCHAR(255) UNIQUE
);

-- Category table
CREATE TABLE Category (
    CategoryCode INT PRIMARY KEY NOT NULL,
    CategoryName NVARCHAR(255) UNIQUE
);

-- Distributor table
CREATE TABLE Distributor (
    DistributorID INT PRIMARY KEY NOT NULL,
    DistributorName NVARCHAR(255),
    [Address] NVARCHAR(MAX),
    DistributorEmail NVARCHAR(255),
    DistributorPhone NVARCHAR(10)
);

-- Medicine table
CREATE TABLE Medicine (
    MedicineID INT PRIMARY KEY NOT NULL,
    MedicineName NVARCHAR(255),
    CategoryCode INT,
    Amount INT,
    MedicinePrice FLOAT,
    Ingredient NVARCHAR(MAX),
    Dosage NVARCHAR(MAX),
    Usage NVARCHAR(MAX),
    Preservation NVARCHAR(MAX),
    Caution NVARCHAR(MAX),
    ExpDate DATE,
    ManuDate DATE,
    UnitCode INT,
    FOREIGN KEY (UnitCode) REFERENCES Unit(UnitCode),
    FOREIGN KEY (CategoryCode) REFERENCES Category(CategoryCode)
);

-- Prescription table
CREATE TABLE Prescription (
    PrescriptionID INT PRIMARY KEY NOT NULL,
    TotalPrice FLOAT,
    SellDate DATE,
    PrescribedBy NVARCHAR(255),
    PatientID INT,
    PatientName NVARCHAR(255),
    PatientPhone NVARCHAR(10)
);

-- PrescriptionItem table
CREATE TABLE PrescriptionItem (
    PrescriptionID INT NOT NULL,
    PrescriptionItemID INT PRIMARY KEY NOT NULL,
    MedicineID INT,
    MedicineName NVARCHAR(255),
    ItemPrice FLOAT,
    FOREIGN KEY (PrescriptionID) REFERENCES Prescription(PrescriptionID),
    FOREIGN KEY (MedicineID) REFERENCES Medicine(MedicineID)
);

-- ImportInfo table
CREATE TABLE ImportInfo (
    ImportID INT PRIMARY KEY NOT NULL,
    DistributorID INT,
    MedicineID INT,
    ImportDate DATE,
    ImportAmount INT,
    FOREIGN KEY (DistributorID) REFERENCES Distributor(DistributorID),
    FOREIGN KEY (MedicineID) REFERENCES Medicine(MedicineID)
);

CREATE TABLE [Role] (
    RoleID INT PRIMARY KEY NOT NULL,
    RoleName NVARCHAR(255)
);

CREATE TABLE Account (
    AccountID INT PRIMARY KEY NOT NULL,
    AccountName NVARCHAR(255),
    [Password] NVARCHAR(255),
    RoleID INT,
    FOREIGN KEY (RoleID) REFERENCES [Role](RoleID)
);

CREATE TABLE Pharmacist (
    PharmacistID INT PRIMARY KEY NOT NULL,
    PharmacistName NVARCHAR(255),
    DateOfBirth DATE,
    SSiD NVARCHAR(12),
    [Address] NVARCHAR(MAX),
    Email NVARCHAR(255),
    PhoneNo NVARCHAR(255),
    RoleID INT,
    AccountID INT,
    FOREIGN KEY (RoleID) REFERENCES [Role](RoleID),
    FOREIGN KEY (AccountID) REFERENCES Account(AccountID)
);

INSERT INTO Unit (UnitCode, UnitName) VALUES
(1, 'Tablet'),
(2, 'Capsule'),
(3, 'Syrup');

INSERT INTO Category (CategoryCode, CategoryName) VALUES
(1, 'Pain Relief'),
(2, 'Antibiotics'),
(3, 'Cough and Cold');

INSERT INTO Distributor (DistributorID, DistributorName, [Address], DistributorEmail, DistributorPhone) VALUES
(1, 'MediSupply Co.', '123 Health St.', 'contact@medisupply.com', '1234567890'),
(2, 'PharmaWorld Ltd.', '456 Wellness Ave.', 'sales@pharmaworld.com', '0987654321');

INSERT INTO Medicine (
    MedicineID, MedicineName, CategoryCode, Amount, MedicinePrice,
    Ingredient, Dosage, Usage, Preservation, Caution, ExpDate, ManuDate, UnitCode
) VALUES
(1, 'Paracetamol', 1, 100, 0.50, 'Acetaminophen', '500mg', 'Take after meals', 'Keep in cool place', 'Do not overdose', '2025-12-31', '2024-12-31', 1),
(2, 'Amoxicillin', 2, 200, 0.75, 'Amoxicillin Trihydrate', '250mg', 'Take 3 times daily', 'Store below 25°C', 'Complete the course', '2025-08-30', '2024-12-31', 2);

INSERT INTO Prescription (
    PrescriptionID, TotalPrice, SellDate, PrescribedBy, PatientID, PatientName, PatientPhone
) VALUES
(1, 1.25, '2025-06-01', 'Dr. Smith', 1001, 'Alice Brown', '1112223333'),
(2, 0.75, '2025-06-02', 'Dr. John', 1002, 'Bob White', '4445556666');

INSERT INTO PrescriptionItem (
    PrescriptionID, PrescriptionItemID, MedicineID, MedicineName, ItemPrice
) VALUES
(1, 1, 1, 'Paracetamol', 0.50),
(1, 2, 2, 'Amoxicillin', 0.75),
(2, 3, 2, 'Amoxicillin', 0.75);

INSERT INTO ImportInfo (
    ImportID, DistributorID, MedicineID, ImportDate, ImportAmount
) VALUES
(1, 1, 1, '2025-05-01', 100),
(2, 2, 2, '2025-05-05', 200);

INSERT INTO [Role] (RoleID, RoleName) VALUES
(1, 'Admin'),
(2, 'Pharmacist'),
(3, 'Manager'),
(4, 'Patient'),
(5, 'Doctor'),
(6, 'Receptionist');


INSERT INTO Account (AccountID, AccountName, [Password], RoleID) VALUES
(1, 'admin_user', 'admin123', 1),
(2, 'pharma_user', 'pharma123', 2),
(3, 'manager_user', 'manager123', 3);


INSERT INTO Pharmacist (
    PharmacistID, PharmacistName, DateOfBirth, SSID, [Address], Email, PhoneNo, RoleID, AccountID
) VALUES
(1, 'John Doe', '1990-05-12', 'SS1234567890', '123 Main St', 'john.doe@email.com', '0123456789', 2, 2),
(2, 'Jane Smith', '1985-08-24', 'SS9876543210', '456 Elm St', 'jane.smith@email.com', '0987654321', 2, 2);
