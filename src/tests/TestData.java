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
		Utilitaires.fillMap(dTest);

	}
	
	@Test
	void testFillMapInitialBorder() {
		boolean borderOk = true;
		Utilitaires.fillMap(dTest);
		for(int i = 0; i< dTest.map.length; i++) {
			for(int j = 0; j< dTest.map[i].length; j++) {
				if(dTest.map[0][j] != '0' ) borderOk=false;	
				if(dTest.map[dTest.map.length-1][j] != '0' ) borderOk=false;
			}
			if(dTest.map[i][0]!= '0') borderOk=false;
			if(dTest.map[i][dTest.map[i].length-1]!= '0') borderOk=false;
		}
		assertTrue(borderOk);
	}
	
	@Test
	void testFillMapInitialInside() {
		boolean insideOk = true;
		Utilitaires.fillMap(dTest);
		for(int i = 1; i< dTest.map.length-1; i++) {
			for(int j = 1; j< dTest.map[i].length-1; j++) {
				if(dTest.map[i][j] != ' ') insideOk=false;	
			}
		}
		assertTrue(insideOk);
	}
	
	
	@Test
	void testNbOfPersoInArrayListWhenCreated() {
		Utilitaires.fillMap(dTest);
		Utilitaires.createPerso(dTest);
		assertEquals(6, dTest.alPerso.size());
		
		//dTest.alPerso.contains(typeOf(this.Player.class));
		
	}
	
	@Test
	void testNumberOfPersoOnMap1000() {
		for(int i =0; i<1000; i++) {
			dTest = new Data();

			testNumberOfPersoOnMap();
		}
	}
	
	@Test
	void testNumberOfPersoOnMap() {
		
		//Given
		Utilitaires.fillMap(dTest);
		Utilitaires.createPerso(dTest);
		
		//When
		Utilitaires.fillMapWithPerso(dTest);
		
		int countPersoOnMap = 0;
		for(int i = 1; i< dTest.map.length-1; i++) {
			for(int j = 1; j< dTest.map[i].length-1; j++) {
				if(dTest.map[i][j] != ' ') countPersoOnMap++;	
			}
		}
		assertEquals(countPersoOnMap, dTest.alPerso.size());
	}
	
	
	@Test
	void testRandomNumberGenerator100() {
		for(int i =0; i<100; i++) {
			testRandomNumberGenerator();
		}
	}
	
	@Test
	void testRandomNumberGenerator(){
		
		int i = Utilitaires.randomXY(1, 9);

	assertTrue(i<=8 && i>=1);
	}
	
	@Test
	public void checkIfPersoOnMap() {
		//Given
		Utilitaires.fillMap(dTest);
		Utilitaires.createPerso(dTest);
		
		//When
		Utilitaires.fillMapWithPerso(dTest);
		
		//Then
		assertEquals(dTest.alPerso.get(0).getName().charAt(0),
				dTest.map[dTest.alPerso.get(0).getX()]
				[dTest.alPerso.get(0).getY()]);
	}

	@Test
	public void mouvPlayerOnMapOk() {
		Perso p = new Player("Player", 5,5);
		Utilitaires.fillMap(dTest);
		int tempX = p.getX();
		int tempY = p.getY();
		dTest.alPerso.add(p);
		dTest.map[p.getX()][p.getY()] = p.getName().charAt(0);
		Utilitaires.mouvPerso(dTest);
		assertTrue((dTest.map[p.getX()+1][p.getY()] == p.getName().charAt(0)
				||dTest.map[p.getX()-1][p.getY()] == p.getName().charAt(0)
				||dTest.map[p.getX()][p.getY()+1] == p.getName().charAt(0)
				||dTest.map[p.getX()][p.getY()-1] == p.getName().charAt(0))
				&&dTest.map[tempX][tempY] != p.getName().charAt(0));
	}
	
	@Test
	public void mouvPlayerOnMapNotOnBorder() {
		Perso p = new Player("Player", 8,8 );
		Utilitaires.fillMap(dTest);
		dTest.map[p.getX()][p.getY()] = p.getName().charAt(0);
		int tempX = p.getX();
		int tempY = p.getY();
		Utilitaires.mouvPerso(dTest);
		assertTrue((dTest.map[tempX-1][tempY] == p.getName().charAt(0)
				||dTest.map[tempX][tempY-1] == p.getName().charAt(0))
				||dTest.map[tempX][tempY] == p.getName().charAt(0));
	}
	
	@Test
	public void mouvEnemyOnMapOk() {
		Perso p = new Enemy("Enemy", 5,5);
		dTest.alPerso.add(p);
		Utilitaires.fillMap(dTest);
		dTest.map[p.getX()][p.getY()] = p.getName().charAt(0);
		int tempX = p.getX();
		int tempY = p.getY();
		
		Utilitaires.mouvPerso(dTest);
		
		assertTrue((dTest.map[tempX+1][tempY] == p.getName().charAt(0)
				||dTest.map[tempX-1][tempY] == p.getName().charAt(0)
				||dTest.map[tempX][tempY+1] == p.getName().charAt(0)
				||dTest.map[tempX][tempY-1] == p.getName().charAt(0))
				&&dTest.map[tempX][tempY] != p.getName().charAt(0));
	}
	
	@Test
	public void mouvEnemiesOnMapOk() {
		int nbOfEnemiesToBeTested = 3;
		int [][] tempPos = new int [3][2];
		Utilitaires.fillMap(dTest);

		for(int i = 0; i <nbOfEnemiesToBeTested; i++) {
			dTest.alPerso.add(new Enemy("Enemy", (i+1)*2, (i+1)*2));
			tempPos[i][0] = (i+1)*2;
			tempPos[i][1] = (i+1)*2;
		}
		for (Perso p : dTest.alPerso) {
			dTest.map[p.getX()][p.getY()]=p.getName().charAt(0);
		}
		Utilitaires.mouvPerso(dTest);
		
		for(int i = 0; i <nbOfEnemiesToBeTested; i++) {
			assertTrue((dTest.map[tempPos[i][0]+1][tempPos[i][1]] == dTest.alPerso.get(i).getName().charAt(0)
					||dTest.map[tempPos[i][0]-1][tempPos[i][1]] == dTest.alPerso.get(i).getName().charAt(0)
					||dTest.map[tempPos[i][0]][tempPos[i][1]+1] == dTest.alPerso.get(i).getName().charAt(0)
					||dTest.map[tempPos[i][0]][tempPos[i][1]-1] == dTest.alPerso.get(i).getName().charAt(0))
					&&dTest.map[tempPos[i][0]][tempPos[i][1]] != dTest.alPerso.get(i).getName().charAt(0));
			}
	}
	
	
	
	
	@Test
	public void mouvEnemyOnMapNotOnBorder() {
		Perso p = new Enemy("Enemy", 8,8 );
		Utilitaires.fillMap(dTest);
		dTest.map[p.getX()][p.getY()] = p.getName().charAt(0);
		int tempX = p.getX();
		int tempY = p.getY();
		Utilitaires.mouvPerso(dTest);
		assertTrue((dTest.map[tempX-1][tempY] == p.getName().charAt(0)
				||dTest.map[tempX][tempY-1] == p.getName().charAt(0))
				||dTest.map[tempX][tempY] == p.getName().charAt(0));
	}
	
	
	
	
	/*@Test
	public void checkIfSaisieStringDoesNotReturnNull() {
		String toBeTested = Utilitaires.saisieString();
		assertNotNull(toBeTested);
	}
	
	@Test
	public void checkIfSaisieStringReturnException() {
		String toBeTested = Utilitaires.saisieString();
		assertNotNull(toBeTested);
	}*/
	

	@Test
	public void testMovePlayerTowardsEnemySpot(){
		// WHEN
		
		// THEN
		// add method on moovPerso, regarde si le jeu doit s'arrete ou il continue en cas de rencontre elle return un boolean
		
		// on appelle mooove,
		// 
		
	}
	
	@Test
	public void testMoveEnemyTowardsPlayerSpot(){
		// GIVEN
		
		
		// WHEN
		
		// THEN
		
	}
	
	@Test
	public void testMoveEnemyTowardEnemySpot() {
		// GIVEN
		
		
		// WHEN
		
		// THEN
		
	}
	
	@Test
	public void testConflictAndGameOver() {
		Perso p = new Player("Player", 5, 5);
		// dTest.alPerso.add(new Enemy("Enemy", 5,6));
		dTest.map[5][6] = 'e';
		assertFalse(Utilitaires.isGameOn(dTest, p, 0, 1));
	}
	
	
	@Test
	public void testNoConflictAndGameOn() {
		Perso p = new Player("Player", 5, 5);
		// dTest.alPerso.add(new Enemy("Enemy", 5,6));
		dTest.map[5][6] = 'e';
		assertTrue(Utilitaires.isGameOn(dTest, p, 0, -1));
	}
}
