import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Fase2 {

	/**
	 * En aquest projecte hi ha dos mètodes main, a Fase2 i Fase3,
	 * només per complir amb els requeriments de l'exercici. 
	 * 
	 * A la Fase3 s'intenta estructurar el codi en mètodes i classes,
	 * també complint amb el requeriments.
	 */
	
	public static void main(String[] args) {

		// Arrays pel menú i el preus.
		List<String> carta  = new ArrayList<>();
		List<Integer> preus = new ArrayList<>();

		/* Diccionari de dades per crear plats amb el preu corresponent; 
		    clau: plat, valor: preu. */
		HashMap<String, Integer> plats_preus = new HashMap<>();
		plats_preus.put("Carpaccio", 150);
		plats_preus.put("Amanida", 15);
		plats_preus.put("Pizza", 200);
		plats_preus.put("Pasta", 250);
		plats_preus.put("Butifarra", 300);
		plats_preus.put("Flam", 25);
		plats_preus.put("Fruita", 35);
		plats_preus.put("Gelat", 45);

		// Omplir els arrays "carta" i "preus" amb les entrades de "plats_preus".
		for (HashMap.Entry<String, Integer> plat_preu : plats_preus.entrySet()) {
			carta.add(plat_preu.getKey());
			preus.add(plat_preu.getValue());
		}

		// Mostrar el menú per consola.
		System.out.println("La nostre carta:");
		for (int i = 0; i < carta.size(); i++) {
			System.out.println(carta.get(i) + " - " + preus.get(i) + "€");
		}
		

		/*    ---      PRENDRE COMANDA     ---         */

		// Objecte per llegir les dades introduïdes pel client.
		Scanner clientInput = new Scanner(System.in);

		List<String> comanda = new ArrayList<>(); // Array amb els plats demanats pel client
		int seguirDemanant = 1; // Variable que si canvia a 0 aturarà el while loop ↓

		System.out.println("Què voleu menjar?");
		while (seguirDemanant != 0) {
			// Omplir l'array "comanda" amb els inputs del client.
			comanda.add(clientInput.next());
			// Preguntar si es vol seguir demanant.
			System.out.println("Alguna cosa més? Sí=1/No=0");
			seguirDemanant = clientInput.nextInt();
		}
		// Un cop trencat el while, tancar el Scanner.
		clientInput.close();

		System.out.println(comanda);

	}

}
