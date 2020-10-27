const { menu } = require("./Fase3");

// Importar el mòdul per llegir els inputs.
const readline = require("readline");

// Crear la interfaç.
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

// Recórrer l'objecte per popular els arrays 'menu' i 'preus'.
for (const plat in plats_preus) {
    menu.push(plat);
    preus.push(plats_preus[plat]);
}

let comanda = []; // Guarda cadascun dels plats demanats.
// exports.comanda = comanda;
let seguirDemanant = 1; // El valor 0 és el cas base de la funció recursiva ↓
// exports.seguirDemanant = seguirDemanant;



let stringMenu = ""; // Emmagatzema el menú que apareixerà a la primera iteració.
// Recórrer un a un els elements dels 2 arrays per crear el text que mostra el menú.
for (let i = 0; i < menu.length; i++) {
  stringMenu += `${menu[i]} - ${preus[i]} €\n`;
}

/* Comptador per distingir la primera iteració de les altres; 
   només a la primera ha d'aparèixer el menú.*/
let count = 0;

// Funció recursiva per prendre la comanda.
function prenComanda() {
  // Cas base que atura la recursió i mostra la comanda.
  if (seguirDemanant == 0) {
    console.log(comanda);
    return rl.close();
  }
  prendreNota();
}

function prendreNota() {
  let pregunta = "";
  if (count == 0) {
    pregunta = `\n${stringMenu}\nQuè voleu menjar ?`;
  } else {
    pregunta = `\nQuè voleu menjar ?`;
  }

  rl.question(pregunta, (plat) => {
    comanda.push(plat); // Afegir el plat demanat a la llista de plats.

    rl.question(
      "Alguna cosa més? (No: 0 / Si: qualsevol altre número) ",
      (resposta) => {
        seguirDemanant = resposta;
        count += 1;

        // Nova crida a la funció recursiva, fins assolir el cas base.
        prenComanda();
      }
    );
  });
}

exports.prenComanda = prenComanda;
