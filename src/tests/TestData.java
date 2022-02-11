package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Data;
import utilitaires.Utilitaires;

class TestData {
	Data dTest;
	
	
	@BeforeEach
	void init() {
		dTest = new Data();
	}
	
	@Test
	void testFillMapInitialBorder() {
		boolean borderOk = true;
		Utilitaires.fillMap(dTest);
		for(int i = 0; i < dTest.map.length ; i ++) {
			for(int j = 0; j < dTest.map[i].length ; j ++) {
				
				if (dTest.map[0][j] != '0') borderOk=false;
				if (dTest.map[dTest.map.length-1][j] != '0') borderOk=false;
				
				
			}
			if (dTest.map[i][0] != '0') borderOk= false;
			if (dTest.map[i][dTest.map.length - 1] != '0') borderOk = false; 
		
			
		}
		assertTrue(borderOk);
		
	}
	
	
	@Test
	void testFillMapInitialInside() {
		boolean insideOk = true;
		Utilitaires.fillMap(dTest);
		for(int i = 1; i < dTest.map.length-1 ; i ++) {
			for(int j = 1; j < dTest.map[i].length-1; j ++) {
				
				if (dTest.map[i][j] != '.') {
					insideOk=false;
				}
				
			}
			
		}
		assertTrue(insideOk);
		
	}
	
	
	
	@Test
	void testNbOfPersoInArrayListWhenCreated() {
		Utilitaires.createPerso(dTest);
		assertEquals(6, dTest.alPerso.size());
	}
	
	@Test
	void randomXYTest() {
		int min = 1;
		int max = 8;
		
		int result = Utilitaires.randomXY(min, max);
		
		assertTrue(min <= result);
		assertTrue(max >= result);
	}
	
	@Test
	public void checkIfPersoOnMap() {
		// given
		Utilitaires.fillMap(dTest);
		Utilitaires.createPerso(dTest);
		
		// when
		Utilitaires.fillMapWithPerso(dTest);
		// then
		
				
	}
	
	@Test
	void testNumberOfPersoMap() {
		for (int i = 0; i < 1000; i++) {
			dTest = new Data();
			testNumberOfPersoOnMap();
		}
		
	}
	
	
	@Test
	void testNumberOfPersoOnMap() {
		
		Utilitaires.fillMap(dTest);
		Utilitaires.createPerso(dTest);
		
		
		int countPersoOnMap = 0;
		for (int i = 1; i<100; i++) {
			
			for ( int j = 1; j< dTest.map[i].length-1; j++) {
				if (dTest.map[i][j] != '.') {
						countPersoOnMap ++;
				}
			}
			
		}
	}
	
	@Test
	void testNumberOfPersonOnMap1000() {
		for (int i = 0; i < 1000; i++) {
			dTest = new Data();
			testNumberOfPersoOnMap();
		}
	}

}
