package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Data;
import model.Enemy;
import model.Perso;
import model.Player;
import utilitaires.Utilitaires;

class TestData {

	Data dTest;

	@BeforeEach
	void init() {
		dTest = new Data();
	}

	@Test
	void borderMapTest() {
		// GIVEN
		boolean failure = true;

		// WHEN
		Utilitaires.fillMap(dTest);

		// THEN
		for (int i = 0; i < dTest.map.length; i++) {
			for (int j = 0; j < dTest.map[i].length; j++) {
				if (dTest.map[0][j] != '0' || dTest.map[dTest.map.length - 1][j] != '0') {
					failure = false;
				}
			}

			if (dTest.map[i][0] != '0' || dTest.map[i][dTest.map.length - 1] != '0') {
				failure = false;
			}
		}

		assertTrue(failure);

	}

	@Test
	void borderMapInitTest() {
		// GIVEN
		boolean insideInitOk = true;

		// WHEN
		Utilitaires.fillMap(dTest);

		// THEN
		for (int i = 1; i < dTest.map.length - 1; i++) {
			for (int j = 1; j < dTest.map[i].length - 1; j++) {
				if (dTest.map[i][j] != ' ') {
					insideInitOk = false;
				}
			}
		}

		assertTrue(insideInitOk);

	}

	@Test
	void nbOfPersoInArrayListWhenCreatedTest() {
		// GIVEN
		Utilitaires.fillMap(dTest);

		// WHEN
		Utilitaires.createPerso(dTest);

		// THEN
		assertEquals(6, dTest.alPerso.size());

	}

	@Test
	void randomXY999foisTest() {

		for (int i = 0; i < 1000; i++) {
			randomXYTest();
		}

	}

	@Test
	void randomXYTest() {
		// GIVEN
		int min = 1;
		int max = 8;
		// WHEN
		int result = Utilitaires.randomXY(min, max);

		// THEN
		assertTrue(min <= result);
		assertTrue(max >= result);

	}

	@Test
	void checkIfPersoOnMap() {
		// GIVEN
		Utilitaires.fillMap(dTest);
		Utilitaires.createPerso(dTest);

		// WHEN
		Utilitaires.fillMapWithPerso(dTest);

		// THEN
		for (int i = 0; i < dTest.alPerso.size(); i++) {
			Perso perso = dTest.alPerso.get(i);
			assertEquals(perso.getName().charAt(0), dTest.map[perso.getX()][perso.getY()]);
		}
	}

	@Test
	void nbrOfPersoOnMap100foisTest() {

		for (int i = 0; i < 100; i++) {
			dTest = new Data();
			nbrOfPersoOnMapTest();
		}

	}

	@Test
	void nbrOfPersoOnMapTest() {

		Utilitaires.fillMap(dTest);
		Utilitaires.createPerso(dTest);
		Utilitaires.fillMapWithPerso(dTest);

		int countPersoOnMap = 0;

		for (int i = 1; i < dTest.map.length - 1; i++) {
			for (int j = 1; j < dTest.map[i].length - 1; j++) {

				if (dTest.map[i][j] != ' ') {
					countPersoOnMap++;
				}
			}
		}

		assertEquals(dTest.alPerso.size(), countPersoOnMap);
	}
	
	@Test
	public void checkIfSaisieStringDoesNotReturnNull() {
		System.out.println("Une saisie : ");
		String toBeTested = Utilitaires.saisieString();
		assertNotNull(toBeTested);
	}
	
	@Test
	public static void mouvPlayerOnMapOk(Data dTest) {
		Perso p = new Player("Perso", 5, 5);
		Utilitaires.fillMap(dTest);
		int tempx = p.getX();
		int tempy = p.getY();
		dTest.map[p.getX()][p.getY()] = p.getName().charAt(0);
		Utilitaires.mouvPerso(dTest);
		assertTrue(dTest.map[p.getX()+1][p.getY()] == p.getName().charAt(0)  
				|| dTest.map[p.getX()-1][p.getY()] == p.getName().charAt(0) 
				|| dTest.map[p.getX()][p.getY()+1] == p.getName().charAt(0)
				|| dTest.map[p.getX()][p.getY()-1] == p.getName().charAt(0)
				&& dTest.map[p.getX()][p.getY()] != p.getName().charAt(0)
				);
		
	}
	
	@Test
	public static void mouvPlayerOnMapNotOnBorder(Data dTest) {
		Perso p = new Player("Perso", 8, 8);
		Utilitaires.fillMap(dTest);
		dTest.map[p.getX()][p.getY()] = p.getName().charAt(0);
		Utilitaires.mouvPerso(dTest);
		assertTrue( 
					dTest.map[p.getX()-1][p.getY()] == p.getName().charAt(0) 
				|| dTest.map[p.getX()][p.getY()-1] == p.getName().charAt(0)
				|| dTest.map[p.getX()][p.getY()] != p.getName().charAt(0)
				);
		
	}
	
	@Test
	public static void mouvEnnemyOnMapNotOnBorder(Data dTest) {
		Perso p = new Enemy("Enemy", 8, 8);
		Utilitaires.fillMap(dTest);
		dTest.map[p.getX()][p.getY()] = p.getName().charAt(0);
		Utilitaires.mouvPerso(dTest);
		assertTrue( 
					dTest.map[p.getX()-1][p.getY()] == p.getName().charAt(0) 
				|| dTest.map[p.getX()][p.getY()-1] == p.getName().charAt(0)
				|| dTest.map[p.getX()][p.getY()] != p.getName().charAt(0)
				);
		
	}
	
	@Test
	public void mouvEnemyOnMapOk() {
		Perso p = new Enemy("Enemy", 6,6);
		Utilitaires.fillMap(dTest);
		dTest.map[p.getX()][p.getY()] = p.getName().charAt(0);
		
		int tempsX = p.getX();
		int tempsY = p.getY();
		
		
		
		
	}
	
	@Test
	public void mouvEnemiesOnMapOk() {
		// GIVEN
		Utilitaires.fillMap(dTest);

		for (int i = 1; i <= 3; i++) {
			Perso p = new Enemy("E" + (i * 2), i * 2, i * 2);
			dTest.map[p.getX()][p.getY()] = p.getName().charAt(0);
			dTest.alPerso.add(p);
		}

		// WHEN
		Utilitaires.mouvPerso(dTest);

		// THEN
		for (Perso perso : dTest.alPerso) {

			int tempX = Integer.parseInt(perso.getName().substring(1, 2));
			int tempY = tempX;

			assertTrue((dTest.map[perso.getX() + 1][tempY] == perso.getName().charAt(0)
					|| dTest.map[perso.getX() - 1][tempY] == perso.getName().charAt(0)
					|| dTest.map[tempX][perso.getY() + 1] == perso.getName().charAt(0)
					|| dTest.map[tempX][perso.getY() - 1] == perso.getName().charAt(0))
					&& dTest.map[tempX][tempY] != perso.getName().charAt(0));
		}

	}



}
