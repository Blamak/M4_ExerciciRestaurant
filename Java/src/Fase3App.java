import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Fase3App {

	public static void main(String[] args) {
		
		
		List <String> carta   = new ArrayList<>();
		List <Integer> preus  = new ArrayList<>();
		Restaurant restaurant = new Restaurant(carta, preus);

		HashMap<String, Integer> plats_preus = new HashMap<>();
		plats_preus.put("carpaccio", 150);
		plats_preus.put("amanida", 15);
		plats_preus.put("pizza", 200);
		plats_preus.put("pasta", 250);
		plats_preus.put("butifarra", 300);
		plats_preus.put("flam", 25);
		plats_preus.put("fruita", 35);
		plats_preus.put("gelat", 45);
		
		/* Omplir els arrays "carta" i "preus" amb les entrades de "plats_preus"
		*  usant el mètode de la classe Restaurant
		*/
		restaurant.ompleCartaIPreus(plats_preus);
		
		// Mostra la carta per consola.
		restaurant.mostraCarta();
		
		int preuTotal = prenComanda(carta, plats_preus);
		
		
		calculaBitllets(preuTotal);
	
	}

	
	// Prendre comanda
	public static int prenComanda(List<String> carta, HashMap<String, Integer> plats_preus) {
		
		Scanner clientInput   = new Scanner(System.in);
		List <String> comanda = new ArrayList<>();
		
		int preuTotal      = 0;
		int seguirDemanant = 1;
		String platDemanat = "";
		
		while (seguirDemanant != 0) {
			System.out.println("\nQuè vols menjar?");
			try {
				platDemanat = clientInput.next();
				if (carta.contains(platDemanat)) {
					comanda.add(platDemanat);
					preuTotal += plats_preus.get(platDemanat);
				} else {
					System.out.println("Ho sentim, no tenim aquest plat al menú d'avui.");
					continue;
				}
				
				System.out.println("Alguna cosa més? (No: 0 / Sí: qualsevol altre número)");
				seguirDemanant = clientInput.nextInt();
				
			} catch (Exception e) {
				// Esborra la última comanda per tornar al principi del bucle i evitar repeticions.
				preuTotal -= plats_preus.get(platDemanat);
				clientInput.next();
				comanda.remove(comanda.size() - 1);
				System.out.println("Has introduït un valor erroni.\nSi us plau, repeteix la última comanda.");
			}
		}
		clientInput.close();
		
		System.out.println("\nHas demanat: " + comanda + "\nEl preu total és: " + preuTotal + " €\n");
		return preuTotal;
	}
	
	// Calcula amb quins bitllets pagar.
	public static void calculaBitllets(int preuTotal) {
		
		int cincEU      = 5;
		int deuEU       = 10;
		int vintEU      = 20;
		int cinquantaEU = 50;
		int centEU      = 100;
		int dosCentsEU  = 200;
		int cincCentsEU = 500;
		
		int resta = preuTotal;
		
		int bitlletsDe500 = Math.round((int) resta / cincCentsEU);
		resta %= cincCentsEU;
		
		int bitlletsDe200 = Math.round((int) resta / dosCentsEU);
		resta %= dosCentsEU;
		
		int bitlletsDe100 = Math.round((int) resta / centEU);
		resta %= centEU;
		
		int bitlletsDe50  = Math.round((int) resta / cinquantaEU);
		resta %= cinquantaEU;
		
		int bitlletsDe20  = Math.round((int) resta / vintEU);
		resta %= vintEU;
		
		int bitlletsDe10  = Math.round((int) resta / deuEU);
		resta %= deuEU;
		
		int bitlletsDe5   = Math.round((int) resta / cincEU);
		
		System.out.println("Bitllets de 500: " + bitlletsDe500);
		System.out.println("Bitllets de 200: " + bitlletsDe200);
		System.out.println("Bitllets de 100: " + bitlletsDe100);
		System.out.println("Bitllets de 50:  " + bitlletsDe50);
		System.out.println("Bitllets de 20:  " + bitlletsDe20);
		System.out.println("Bitllets de 10:  " + bitlletsDe10);
		System.out.println("Bitllets de 5:   " + bitlletsDe5);
		
	}
}
