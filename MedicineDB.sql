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