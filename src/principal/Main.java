package principal;

import model.Data;
import utilitaires.Utilitaires;

public class Main {
	
	static Data d = new Data();
	public static void main(String[] args) {
		//fillMap();
		Utilitaires.fillMap(d);
		//printMap();
		//Utilitaires.printMap(d);
		
		Utilitaires.createPerso(d);
		
		Utilitaires.fillMapWithPerso(d);
		
		
		Utilitaires.printMap(d);
	}

}
