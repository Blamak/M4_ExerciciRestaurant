import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;



public class Fase3App {

	/**
	 * En aquesta fase de l'exercici es crea una inst�ncia de la classe adjunta Restaurant.
	 * La carta amb preus es pobla i es mostra per consola mitjan�ant 
	 * els m�todes d'inst�ncia ompleCartaIPreus() i mostraCarta().
	 * 
	 * Hi ha a m�s els m�todes est�tics de la classe Fase3App:
	 * - prenComanda(), per preguntar i guardar quins plats es vol menjar, 
	 * - calculaBitllets(), per mostrar per consola amb quins bitllets cal pagar.
	 * El par�metre per executar calculaBitllets() s'obt� del retorn de prenComanda().
	 */
	
	public static void main(String[] args) {
		
		// Inicialitzar les llistes de plats i preus.
		List <String> carta   = new ArrayList<>();
		List <Integer> preus  = new ArrayList<>();
		
		// Inst�ncia de la classe restaurant amb les llistes buides.
		Restaurant restaurantFase3 = new Restaurant(carta, preus);

		/* Diccionari de dades per crear plats amb el preu corresponent; 
		   clau: plat, valor: preu. */
		HashMap<String, Integer> plats_preus = new HashMap<>();
		plats_preus.put("carpaccio", 150);
		plats_preus.put("amanida", 15);
		plats_preus.put("pizza", 200);
		plats_preus.put("pasta", 250);
		plats_preus.put("butifarra", 300);
		plats_preus.put("flam", 25);
		plats_preus.put("fruita", 35);
		plats_preus.put("gelat", 45);
		
		// Omplir la llista de plats i la de preus amb les entrades del diccionari.
		restaurantFase3.ompleCartaIPreus(plats_preus);
		
		// Mostra la carta per consola.
		restaurantFase3.mostraCarta();
		
		// Preguntar qu� es vol menjar i guardar el preu final en una variable.
		int preuTotal = prenComanda(carta, plats_preus);
		
		// Mostra per consola els bitllets per pagar.
		calculaBitllets(preuTotal);
	}

	
/*                  ----       M�TODES EST�TICS:     ----                    */
	
	public static int prenComanda(List<String> carta, HashMap<String, Integer> plats_preus) {
		/* 
		 * M�tode que dins un while loop pregunta els plats a demanar
		 * i si es vol seguir demanant. 
		 *  */
		
		// Variables pels input i per guardar la comanda.
		Scanner clientInput   = new Scanner(System.in);
		List <String> comanda = new ArrayList<>();
		
		int seguirDemanant = 1;  // Amb valor 0 atura el while loop.
		int preuTotal      = 0;  // Guarda el preu de la comanda augmentant amb cada nou plat.
		
		while (seguirDemanant != 0) {
			System.out.println("\nQu� vols menjar?");
			String platDemanat = ""; // Pren el valor de cadascun dels plats demanats.
			
			try {
				platDemanat = clientInput.next();
				
				if (!carta.contains(platDemanat)) {
					// Si el plat no �s dins la carta, informar i tornar al principi del loop.
					System.out.println("Ho sentim, no tenim aquest plat al men� d'avui.");
					continue;
				} else {
					// Afegir el plat demanat a la comanda i sumar al preu final.			 
					comanda.add(platDemanat);
					preuTotal += plats_preus.get(platDemanat);
				}
				
				// Si el client contesta amb un valor no num�ric s'activa el catch: 
				System.out.println("Alguna cosa m�s? (No: 0 / S�: qualsevol altre n�mero)");
				seguirDemanant = clientInput.nextInt();
				
			} catch (InputMismatchException e) {
				clientInput.next(); // Evita que l'input incorrecte s'assigni en la propera iteraci�.
				
				// Esborrar l'�ltima comanda per evitar repeticions i restar-ne el preu.
				preuTotal -= plats_preus.get(platDemanat);
				comanda.remove(comanda.size() - 1);
				
				System.out.println("\nHas introdu�t un valor erroni.\nSi us plau, repeteix l'�ltima comanda.");
			}
		}
		
		clientInput.close(); // Tanca el Scanner.
		
		System.out.println("\nHas demanat: " + comanda + "\nEl preu total �s: " + preuTotal + " �\n");
		
		return preuTotal; // Valor necessari per executar el m�tode calculaBitllets().
	}
	
	public static void calculaBitllets(int preuComanda) {
		/* 
		 * Calcular quants bitllets de cada tipus calen per pagar el total
		 * de la comanda realitzada amb el m�tode prenComanda().
		 */
		
		// Tipus de bitllet.
		int cincCentsEU = 500;
		int dosCentsEU  = 200;
		int centEU      = 100;
		int cinquantaEU = 50;
		int vintEU      = 20;
		int deuEU       = 10;
		int cincEU      = 5;
		
		
		/* - Primer es divideix el cost de la comanda entre el bitllet de m�s valor;
		 *  obtenim el nombre de bitllets d'aquest tipus.
		 * 
		 * - El remanent de cada divisi� es divideix entre el seg�ent bitllet de valor inferior,
		 *   resultant la quantitat necess�ria per cada tipus.
		 * 
		 * - Mitjan�ant un condicional per cada bitllet
		 *  nom�s es mostren per consola els bitllets que cal utilitzar per pagar el total.
		 */
		
		int remanent = preuComanda; // El valor inicial del remanent �s el preu de la comanda.
		
		int bitlletsDe500 = Math.round((int) remanent / cincCentsEU);
		remanent %= cincCentsEU;
		if (bitlletsDe500 != 0) {
			System.out.println("Bitllets de 500: " + bitlletsDe500);			
		}
		
		int bitlletsDe200 = Math.round((int) remanent / dosCentsEU);
		remanent %= dosCentsEU;
		if (bitlletsDe200 != 0) {
			System.out.println("Bitllets de 200: " + bitlletsDe200);			
		}
		
		int bitlletsDe100 = Math.round((int) remanent / centEU);
		remanent %= centEU;
		if (bitlletsDe100 != 0) {
			System.out.println("Bitllets de 100: " + bitlletsDe100);			
		}
		
		int bitlletsDe50  = Math.round((int) remanent / cinquantaEU);
		remanent %= cinquantaEU;
		if (bitlletsDe50 != 0) {
			System.out.println("Bitllets de  50: " + bitlletsDe50);			
		}
		
		int bitlletsDe20  = Math.round((int) remanent / vintEU);
		remanent %= vintEU;
		if (bitlletsDe20 != 0) {
			System.out.println("Bitllets de  20: " + bitlletsDe20);			
		}
		
		int bitlletsDe10  = Math.round((int) remanent / deuEU);
		remanent %= deuEU;
		if (bitlletsDe10 != 0) {
			System.out.println("Bitllets de  10: " + bitlletsDe10);			
		}
		
		int bitlletsDe5   = Math.round((int) remanent / cincEU);
		if (bitlletsDe5 != 0) {
			System.out.println("Bitllets de   5: " + bitlletsDe5);			
		}
		
	}
}
