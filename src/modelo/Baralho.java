package modelo;

import modelo.enumeracao.Naipe;
import java.util.Random;

public class Baralho {
    private Carta[] cartas = new Carta[52];

    public Baralho() {
        int i = 0;
        for (Naipe naipe : Naipe.values()) {
            for (int j = 1; j <= 13; j++) {
                cartas[i++] = new Carta(j, naipe);
            }
        }
    }

    public void exibirBaralho() {
        for(int i = 0; i < cartas.length; i++) {
            cartas[i].mostrarCarta();
            System.out.println(cartas[i].toString());
        }
    }

    public void embaralhar() {
        Random gerador = new Random();
        for (int i = cartas.length - 1; i > 0; i--) {
            int j = gerador.nextInt(i + 1);
            Carta temp = cartas[i];
            cartas[i] = cartas[j];
            cartas[j] = temp;
        }
    }

    public Carta[] getBaralho() {
        return cartas;
    }
}