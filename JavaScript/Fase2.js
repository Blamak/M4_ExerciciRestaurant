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


/* --------------------------------------------------------------------------------*/
/*                                  PRENDRE COMANDA:                               */

  /*
   * El mòdul readline utilitzat en aquest exercici no permet l'ús d'un bucle while
   * per repetir les preguntes al client; s'ha optat per una solució amb una funció recursiva.
   */

let comanda = []; // Guarda cadascun dels plats demanats.
let seguirDemanant = 1; // El valor 0 és el cas base de la funció recursiva.

let textMenu = ""; // El menú que apareixerà a la primera iteració.
for (let i = 0; i < menu.length; i++) {
  // Recórrer un a un els elements dels 2 arrays per crear el text que mostra el menú.
  textMenu += `${menu[i]} - ${preus[i]} €\n`;
}

let comptador = 0; // Per distingir la primera iteració.
function prenComanda() {
  /* Funció recursiva per prendre la comanda.
     Una altra funció niada, prendreNota(), s'encarrega d'obtenir els input del client. */

  // Cas base, atura la recursivitat i mostra la comanda.
  if (seguirDemanant == 0) {
    console.log(comanda);
    return rl.close();
  }

  /* A la primera iteració (comptador a zero) la pregunta per demanar el plat
     ha d'anar precedida del menú. */
  let preguntaPlat = "";
  if (comptador == 0) {
    preguntaPlat = `\n${textMenu}\nQuè voleu menjar? `;
  } else {
    preguntaPlat = `\nQuè voleu menjar? `;
  }

  prendreNota(preguntaPlat);
}

function prendreNota(preguntaPlat) {
  rl.question(preguntaPlat, (plat) => {
    comanda.push(plat); // Afegir el plat demanat a la llista de plats.

    rl.question(
      // Resposta "0" atura la recursivitat.
      "Alguna cosa més? (No: 0 / Si: qualsevol altre número) ",
      (resposta) => {
        seguirDemanant = resposta;
        comptador += 1;

        // Nova crida a la funció mare.
        prenComanda();
      }
    );
  });
}

prenComanda();
