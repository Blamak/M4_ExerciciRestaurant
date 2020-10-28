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
  carpaccio: 150,
  amanida: 15,
  pizza: 200,
  pasta: 250,
  butifarra: 300,
  flam: 25,
  fruita: 35,
  gelat: 45,
};

// Recórrer l'objecte per poblar els arrays 'menu' i 'preus'.
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
let preuFinal = 0; // Per anar sumant el preu de cada plat.

let textMenu = ""; // El menú que apareixerà a la primera iteració.
// Recórrer un a un els elements dels 2 arrays per crear el text que mostra el menú.
for (let i = 0; i < menu.length; i++) {
  textMenu += `${menu[i]} - ${preus[i]} €\n`;
}

let comptador = 0; // Per distingir la primera iteració i només mostrar un cop el menú.
function prenComanda() {
  /* Funció recursiva per prendre la comanda.
     La funció niada prendreNota() s'encarrega d'obtenir els input del client. */

  // Cas base, atura la recursivitat i mostra la comanda, el preu final i els bitllets per pagar (funció definida al final).
  if (seguirDemanant == 0) {
    console.log(`Has demanat: ${comanda}`);
    console.log(`El preu total és: ${preuFinal}\n`);
    calculaBitllets(preuFinal, bitllets);
    return rl.close();
  }

  /* A la primera iteració la pregunta per demanar el plat
  ha d'anar precedida del menú. */
  let preguntaPlat = "";
  if (comptador == 0) {
    preguntaPlat = `\n${textMenu}\nQuè voleu menjar? `; // Amb menú.
  } else {
    preguntaPlat = `\nQuè voleu menjar? `; // Sense menú.
  }

  prendreNota(preguntaPlat);
}

function prendreNota(preguntaPlat) {
  rl.question(preguntaPlat, (plat) => {
    // Si el plat és al menú, afegir-lo a la comanda i sumar el preu al total.
    if (menu.includes(plat)) {
      preuFinal += plats_preus[plat];
      comanda.push(plat);
      rl.question(
        // Resposta "0" atura la recursivitat.
        "Alguna cosa més? (No: 0 / Sí: qualsevol altre número) ",
        (resposta) => {
          seguirDemanant = resposta;
          comptador += 1; // El menú no apareixerà a la propera iteració.

          // Nova crida a la funció mare.
          prenComanda();
        }
      );
    } else {
      // Plat demanat no inclòs al menú:
      comptador += 1; // El menú no apareixerà a la propera iteració.
      rl.write("\nHo sentim, no tenim aquest plat avui.\n");

      // Nova crida a la funció mare.
      prenComanda();
    }
  });
}

// Crida la funció.
prenComanda();


/* --------------------------------------------------------------------------------- */
/*                                  CALCULAR BITLLETS:                               */

const bitllets = [500, 200, 100, 50, 20, 10, 5]; // Array amb els diferents tipus de bitllets.

// Objecte per disposar de la quantitat de cada bitllet per assolir el preu final. Tots inicialitzats a zero.
const bitlletsQuantitat = { 500: 0, 200: 0, 100: 0, 50: 0, 20: 0, 10: 0, 5: 0 };

function calculaBitllets(preu, bitllets) {
  /* Funció, també recursiva (sorry), per calcular i mostrar per consola els bitllets per pagar la comanda. */

  // Cas base per aturar la recursivitat i imprimir el resultat.
  if (preu === 0) {
    for (let bitllet in bitlletsQuantitat) {
      if (bitlletsQuantitat[bitllet] !== 0) { // Només imprimir els tipus bitllets necessaris.
        console.log(`Bitllets de ${bitllet}: ${bitlletsQuantitat[bitllet]}`);
      }
    }
    return null; 
  }

  // Mentre el preu sigui major que el bitllet, suma 1 al valor de la clau corresponent dins l'objecte i repeteix.
  if (preu >= bitllets[0]) { // Comencem pel bitllet més gran.
    remanent = preu - bitllets[0];
    bitlletsQuantitat[bitllets[0]] += 1;
    return calculaBitllets(remanent, bitllets);
  } else { 
    // Un cop exhaurit el primer bitllet, s'elimina de l'array bitllets i es crida novament la funció.
    bitllets.shift();
    return calculaBitllets(preu, bitllets);
  }
}
