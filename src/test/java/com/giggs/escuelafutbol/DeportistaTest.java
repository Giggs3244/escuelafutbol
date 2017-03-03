package com.giggs.escuelafutbol;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;

public class DeportistaTest {

	@PersistenceContext
	private EntityManager em;

	@Test
	public void insertarDeportistaDocumento() {
		assertTrue("1" == "1");
	}

	@Test
	public void probarEntityManager() {
		System.out.println(em);
		assertTrue(em != null);
	}

}
