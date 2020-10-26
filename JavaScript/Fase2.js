const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const menu = [];
const preus = [];

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

for (const plat in plats_preus) {
  menu.push(plat);
  preus.push(plats_preus[plat]);
}

/* PRENDRE COMANDA */
let comanda = [];
let seguirDemanant = 1;
let count = 0;
let stringMenu = "";

for (let i = 0; i < menu.length; i++) {
  stringMenu += `${menu[i]} - ${preus[i]} €\n`;
}

let recursiveAsyncReadline = () => {
  if (seguirDemanant == 0) {
    console.log(comanda);
    return rl.close();
  }
  if (count == 0) {
    rl.question(`\n${stringMenu}\nQuè voleu menjar ?`, (plat) => {
      comanda.push(plat);
      rl.question(
        "Alguna cosa més? (No: 0 / Si: qualsevol altre número) ",
        (resposta) => {
          seguirDemanant = resposta;
          count += 1;
          recursiveReadline();
        }
      );
    });
  } else {
      rl.question("Què voleu menjar ? ", (plat) => {
        comanda.push(plat);
        rl.question(
          "Alguna cosa més? (No: 0 / Si: qualsevol altre número) ",
          (resposta) => {
            seguirDemanant = resposta;
            count += 1;
            recursiveAsyncReadline();
          }
        );
      });
  }
};
recursiveAsyncReadline();

