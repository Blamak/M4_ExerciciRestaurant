
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Restaurant {

	// Arrays per la carta i el preus.
	private List<String> carta  = new ArrayList<>();
	private List<Integer> preus = new ArrayList<>();

	// Constructor
	public Restaurant(List<String> carta, List<Integer> preus) {
		this.carta = carta;
		this.preus = preus;
	}


/*                      -----     MÈTODES D'INSTÀNCIA      -----                        */

	// Omplir la carta i els preus mitjançant un diccionari de dades.
	public void ompleCartaIPreus(HashMap<String, Integer> plats_preus) {

		for (HashMap.Entry<String, Integer> plat_preu : plats_preus.entrySet()) {
			this.carta.add(plat_preu.getKey());
			this.preus.add(plat_preu.getValue());
		}
	}

	/* Mostrar la carta i preus per consola iterant sobre els dos arrays, 
	  que estan sincronitzats - al primer plat li correspon el primer preu, etc. */
	public void mostraCarta() {
		System.out.println("La nostra carta:\n");
		
		for (int i = 0; i < this.carta.size(); i++) {
			/* Abans d'imprimir, per alinear verticalment els preus de la carta
			 * afegim espais en funció de la llargada del nom del plat.
			 */
			int espais = 10 - this.carta.get(i).length();
			String espaisMesGuio = new String(new char[espais]).replace("/0", " ") + "- ";
			
			System.out.println(this.carta.get(i) + espaisMesGuio + this.preus.get(i) + " €");
		}
	}
	
}


