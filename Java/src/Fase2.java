import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Fase2 {

	public static void main(String[] args) {

		int cincEU;
		int deuEU;
		int vintEU;
		int cinquantaEU;
		int centEU;
		int dosCentsEU;
		int cincCentsEU;
		int total;
		
		List <String>  menu  = new ArrayList<>();
		List <Integer> preus = new ArrayList<>();
		
		HashMap <String, Integer> plats_preus = new HashMap<>();
		plats_preus.put("Carpaccio", 10);
		plats_preus.put("Amanida", 9);
		plats_preus.put("Pizza", 20);
		plats_preus.put("Pasta", 15);
		plats_preus.put("Butifarra", 30);
		plats_preus.put("Flam", 8);
		plats_preus.put("Fruita", 7);
		plats_preus.put("Gelat", 6);
		
		for(HashMap.Entry<String, Integer> plat_preu : plats_preus.entrySet()) {
			menu.add(plat_preu.getKey());
			preus.add(plat_preu.getValue());
		}
		
		System.out.println("El nostre menú:");
		for (int i = 0; i < menu.size(); i++) {
			System.out.println(menu.get(i) + " - " + preus.get(i) + "€");
		}
		
		// Prendre comanda:
		Scanner client        = new Scanner(System.in);
		List <String> comanda = new ArrayList<>(); 
		int seguirDemanant    = 1;
		
		System.out.println("Què voleu menjar?");
		while (seguirDemanant != 0) {
			comanda.add(client.next());
			System.out.println("Alguna cosa més? Sí=1/No=0");
			seguirDemanant = client.nextInt();
		}
		client.close();
		
		System.out.println(comanda);

	}

}
