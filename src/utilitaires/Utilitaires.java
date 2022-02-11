package utilitaires;

import model.Data;
import model.Enemy;
import model.Perso;
import model.Player;

import java.util.Random;

public class Utilitaires {
	
	public static void fillMap(Data data) {
		for (int i = 0; i < data.map.length; i++) {
			for (int j = 0; j < data.map[i].length; j++) {
				data.map[i][j] = '.';
				data.map[0][j] = '0';
				data.map[data.map.length - 1][j] = '0';
			}
			data.map[i][0] = '0';
			data.map[i][data.map.length - 1] = '0';
		}
	}

	public static void printMap(Data data) {
		for (int i = 0; i < data.map.length; i++) {
			for (int j = 0; j < data.map.length; j++) {
				System.out.print(data.map[i][j] + " ");
			}
			System.out.println("");
		}
	}
	public static void fillMapWithPerso(Data data) {
		
		for (Perso perso: data.alPerso) {
			data.map[perso.getX()][perso.getY()] = perso.getName().charAt(0);
		}
		
		
	}
	
	public static void createPerso(Data data) {
		data.alPerso.add(new Player("Hugo", randomXY(1, data.map.length-1), randomXY(1, data.map[0].length-1)));
		int x;
		int y;
		for (int i = 0; i<5; i++) {
			
			do {
				x = randomXY(1, data.map.length-1);
				y = randomXY(1, data.map.length-1);
			}
			while(data.map[x][y] != '.' );
			
			data.alPerso.add(new Enemy("Ennemy", x,y));
		}
	}
	
	
	public static int randomXY(int min, int max) {
		
		Random r = new Random();
		return r.nextInt(min,max);
		
		
	}


}
