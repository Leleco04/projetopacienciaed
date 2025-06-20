package modelo;

import modelo.enumeracao.Cor;
import modelo.enumeracao.Naipe;

import static util.FormatadorConsole.*;

public class Carta {
    private int numero; // 1 = Ás, 11 = Valete, 12 = Dama, 13 = Rei
    private Naipe naipe;
    private Cor cor;
    private boolean visivel;

    public Carta(int numero, Naipe naipe) {
        this.numero = numero;
        this.naipe = naipe;
        this.cor = (naipe == Naipe.COPAS || naipe == Naipe.OUROS) ? Cor.VERMELHA : Cor.PRETA;
        this.visivel = false;
    }

    public int getNumero() {
        return numero;
    }

    public String getNaipeString() {
        if(isVisivel()) {
            if(cor.equals(Cor.VERMELHA)) return NEGRITO + VERMELHO + String.valueOf(naipe) + RESET;
            else return NEGRITO + PRETO + BRANCO + String.valueOf(naipe) + RESET;
        } else {
            return "[?]";
        }
    }

    public String getCorString() {
        if(cor.equals(Cor.VERMELHA)) return NEGRITO + VERMELHO + String.valueOf(cor) + RESET;
        else return NEGRITO + PRETO + BRANCO + String.valueOf(cor) + RESET;
    }

    public Cor getCor() {
        return cor;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void mostrarCarta() {
        this.visivel = true;
    }

    public void esconderCarta() {
        this.visivel = false;
    }

    public String toString() {
        if (!visivel) return "[?]";
        String[] nomes = {"Ás", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valete", "Dama", "Rei"};
        return nomes[numero - 1] + " de " + getNaipeString() + ".";
    }
}