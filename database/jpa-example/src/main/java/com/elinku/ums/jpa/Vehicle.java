package com.elinku.ums.jpa;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Version;

@Entity
@Table(name = "VEHICLE", schema = "JPATEST")
public class Vehicle {
    @Id
    @Column(name = "VIN")
    protected String vin;
    @Column(name = "MAKE")
    protected String make;
    protected String model;
    @Column(name = "MODEL_YEAR")
    protected int year;
    @Version
    protected int version;

    public Vehicle() { // default constructor is required.
	super();
    }

    public Vehicle(String vin) {
	super();
	this.vin = vin;
    }

    public Vehicle(String vin, String make, String model, int year) {
	super();
	this.vin = vin;
	this.make = make;
	this.model = model;
	this.year = year;
    }

    public String getVin() {
	return this.vin;
    }

    public String getMake() {
	return this.make;
    }

    public void setMake(String make) {
	this.make = make;
    }

    public String getModel() {
	return this.model;
    }

    public void setModel(String model) {
	this.model = model;
    }

    public int getYear() {
	return this.year;
    }

    public void setYear(int modelYear) {
	this.year = modelYear;
    }

    public int getVersion() {
	return version;
    }

    public void setVersion(int version) {
	this.version = version;
    }

}
