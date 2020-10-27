// Accés a las funcions prenComanda() i prenNota()
const { prenComanda } = require("./prenComanda");

// Arrays amb els plats i els preus. Estaran sincronitzats (1er plat → 1er preu, etc.).
const menu = [];
const preus = [];

// Objecte per crear parelles plat-preu (clau-valor).
const plats_preus = {
  Carpaccio: 150,
  Amanida: 15,
  Pizza: 200,
  Pasta: 250,
  Butifarra: 300,
  Flam: 25,
  Fruita: 35,
  Gelat: 45,
};


// }

/*   ----  PRENDRE COMANDA: ----    */

/* El mòdul readline utilitzat en aquest exercici no permet l'ús d'un bucle while
   per repetir les preguntes al client; s'ha optat per una solució amb una funció recursiva. */



// Funcions en arxiu adjacent després de refactoritzar.
prenComanda();

