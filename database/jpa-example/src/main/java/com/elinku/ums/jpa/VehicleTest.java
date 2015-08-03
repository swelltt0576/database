package com.elinku.ums.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class VehicleTest {
    EntityManagerFactory emf = null;

    public VehicleTest() {
	// There is one entity manager factory per persistence unit
	emf = Persistence.createEntityManagerFactory("bjpatest");
    }

    public void createVehicle(Vehicle v) {
	// create a new entity manager from entity manager factory
	EntityManager em = emf.createEntityManager();
	try {
	    em.getTransaction().begin(); // start transaction
	    em.persist(v); // insert v into database
	    em.getTransaction().commit(); // commit transaction
	} catch (Throwable t) {
	    t.printStackTrace();
	    em.getTransaction().rollback(); // rollback transaction
	} finally {
	    em.close(); // close entity manager
	}
    }

    public List<Vehicle> retrieveVehicles(String make) {
	EntityManager em = emf.createEntityManager();
	List<Vehicle> result = null;
	try {
	    // create a JPA query on the Java entity Vehicle with a
	    // parameter. The 1st argument is the query string, and the 2nd
	    // argument indicates the type of the query result.
	    TypedQuery<Vehicle> query = em.createQuery("SELECT v FROM Vehicle v WHERE v.make = ?1", Vehicle.class);
	    query.setParameter(1, make);
	    // get a list of vehicles from database for the given make
	    result = query.getResultList();
	} finally {
	    em.close();
	}
	return result;
    }

    public Vehicle updateVehicle(Vehicle v) {
	EntityManager em = emf.createEntityManager();
	Vehicle v2 = null;
	try {
	    em.getTransaction().begin(); // start transaction
	    v2 = em.merge(v); // merge v into managed state
	    v2.setYear(2011); // update value again
	    em.getTransaction().commit(); // commit transaction
	} catch (Throwable t) {
	    t.printStackTrace();
	    em.getTransaction().rollback(); // rollback transaction
	} finally {
	    em.close(); // close entity manager
	}
	return v2; // return the updated object
    }

    public void deleteVehicle(Vehicle v) {
	EntityManager em = emf.createEntityManager();
	try {
	    em.getTransaction().begin(); // start transaction
	    em.remove(em.merge(v)); // delete v from database
	    em.getTransaction().commit(); // commit transaction
	} catch (Throwable t) {
	    t.printStackTrace();
	    em.getTransaction().rollback(); // rollback transaction
	} finally {
	    em.close(); // close entity manager
	}
    }

    public void close() {
	if (emf != null & emf.isOpen()) {
	    emf.close(); // close entity manager factory
	}
    }

    public static void main(String[] args) {
	try {
	    VehicleTest vehTest = new VehicleTest();
	    String vinPrimaryKey = "6B7HF16Y7SS244324";
	    Vehicle v = new Vehicle(vinPrimaryKey, "Ford", "Flex", 2010);
	    // insert the new Vehicle object into database. JPA assigns a
	    // value (typically 0 or 1) to the version field
	    vehTest.createVehicle(v);
	    // do update, then version should be incremented by 1
	    v.setMake("Mercury");
	    Vehicle v2 = vehTest.updateVehicle(v);
	    // Assert v2.getVersion()==v.getVersion()+1:"Update version error";
	    
	    
	    // retrieve all vehicles with make "Mercury"
	    List<Vehicle> vehicles = vehTest.retrieveVehicles("Mercury");
	    for (Vehicle h : vehicles) {
		System.out.println("vehicle retrieved: " + h.getVin());
	    }
	    // delete newly inserted/updated vehicle. Must pass in v2 as a
	    // parameter instead of v. Otherwise, OptimisticLockException
	    // would be thrown
	    vehTest.deleteVehicle(v2);
	    // clean up resource
	    vehTest.close();
	} catch (Throwable t) {
	    t.printStackTrace();
	}
    }
}
