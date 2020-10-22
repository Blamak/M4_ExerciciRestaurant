import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Fase3 {

	public static void main(String[] args) {

		List<String> menu = new ArrayList<>();
		List<Integer> preus = new ArrayList<>();

		HashMap<String, Integer> plats_preus = new HashMap<>();
		plats_preus.put("Carpaccio", 10);
		plats_preus.put("Amanida", 9);
		plats_preus.put("Pizza", 20);
		plats_preus.put("Pasta", 15);
		plats_preus.put("Butifarra", 30);
		plats_preus.put("Flam", 8);
		plats_preus.put("Fruita", 7);
		plats_preus.put("Gelat", 6);

		for (HashMap.Entry<String, Integer> plat_preu : plats_preus.entrySet()) {
			menu.add(plat_preu.getKey());
			preus.add(plat_preu.getValue());
		}

		System.out.println("El nostre menú:");
		for (int i = 0; i < menu.size(); i++) {
			System.out.println(menu.get(i) + " - " + preus.get(i) + "€");
		}

		// Prendre comanda:
		Scanner client = new Scanner(System.in);
		List<String> comanda = new ArrayList<>();
		int seguirDemanant = 1;

		while (seguirDemanant != 0) {
			System.out.println("Què vols menjar?");
			try {
				String platDemanat = client.next();
				comanda.add(platDemanat);

				System.out.println("Alguna cosa més? (No: 0 / Sí: qualsevol altre número)");
				seguirDemanant = client.nextInt();

			} catch (Exception e) {
				client.next();
				comanda.clear();
				System.out.println("Has introduït un valor erroni.\nComencem des del principi.");
			}
		}
		client.close();

		System.out.println(comanda);
	}

}
