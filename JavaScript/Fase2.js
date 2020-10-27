// Importar el mòdul per llegir els inputs.
const readline = require("readline");

// Crear la interfaç.
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

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

// Recórrer l'objecte per popular els arrays 'menu' i 'preus'.
for (const plat in plats_preus) {
  menu.push(plat);
  preus.push(plats_preus[plat]);
}

/*   ----  PRENDRE COMANDA: ----    */
/* El mòdul readline utilitzat en aquest exercici no permet l'ús d'un bucle while
   per repetir les preguntes al client; s'ha optat per una solució amb una funció recursiva. */

let comanda = []; // Guarda cadascun dels plats demanats.
let seguirDemanant = 1; // El valor 0 és el cas base de la funció recursiva.

/* Comptador per distingir la primera iteració de les altres; 
   només a la primera ha d'aparèixer el menú.*/
let count = 0;

let stringMenu = ""; // Emmagatzema el menú que apareixerà a la primera iteració.
// Recórrer un a un els elements dels 2 arrays per crear el text que mostra el menú.
for (let i = 0; i < menu.length; i++) {
  stringMenu += `${menu[i]} - ${preus[i]} €\n`;
}

// Funció recursiva per prendre la comanda.
let prenComanda = () => {
  // Cas base que atura la recursió i mostra la comanda.
  if (seguirDemanant == 0) {
    console.log(comanda);
    return rl.close();
  }

  if (count == 0) {
    // La primera crida a la funció fa aparèixer per consola el menú ("stringMenu").
    rl.question(`\n${stringMenu}\nQuè voleu menjar ?`, (plat) => {
      comanda.push(plat); // Afegir el plat demanat a la llista de plats.

      rl.question(
        // La resposta "0" atura la recursivitat en assolir el cas base ↑.
        "Alguna cosa més? (No: 0 / Si: qualsevol altre número) ",
        (resposta) => {
          seguirDemanant = resposta;
          count += 1;
          prenComanda();
        }
      );
    });

  } else {
      // A partir de la segona iteració no s'imprimeix el menú.
      rl.question("Què voleu menjar ? ", (plat) => {
        comanda.push(plat);

        rl.question(
          "Alguna cosa més? (No: 0 / Si: qualsevol altre número) ",
          (resposta) => {
            seguirDemanant = resposta;
            prenComanda();
          }
        );
      });
  }
};

prenComanda();

// Sorry for the messy code, a la Fase3 ho intento millorar...