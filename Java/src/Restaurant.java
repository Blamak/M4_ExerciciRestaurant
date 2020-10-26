
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

	// getters i setters
	public List<String> getCarta() {
		return carta;
	}

	public void setCarta(List<String> carta) {
		this.carta = carta;

	}

	public List<Integer> getPreus() {
		return preus;
	}

	public void setPreus(List<Integer> preus) {
		this.preus = preus;
	}

	/* -------- MÈTODES -------- */

	// Omplir la carta i elps preus mitjançant un diccionari.
	public void ompleCartaIPreus(HashMap<String, Integer> plats_preus) {

		for (HashMap.Entry<String, Integer> plat_preu : plats_preus.entrySet()) {
			this.carta.add(plat_preu.getKey());
			this.preus.add(plat_preu.getValue());
		}
	}

	// Mostrar la carta i preus per consola.
	public void mostraCarta() {
		System.out.println("La nostre carta:\n");
		for (int i = 0; i < this.carta.size(); i++) {
			// Alinear els preus de la carta.
			int espais = 10 - this.carta.get(i).length();
			String espaisMesGuio = new String(new char[espais]).replace("/0", " ") + "- ";
			System.out.println(this.carta.get(i) + espaisMesGuio + this.preus.get(i) + " €");
		}
	}
	
}


