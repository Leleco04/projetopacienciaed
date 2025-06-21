package modelo.enumeracao;

import static util.FormatadorConsole.*;

public enum Naipe {
    OUROS(NEGRITO + VERMELHO + "Ouro (♢)" + RESET),
    COPAS(NEGRITO + VERMELHO + "Copas (♡)" + RESET),
    ESPADAS(NEGRITO + PRETO + "Espadas (♤)" + RESET),
    PAUS(NEGRITO + PRETO + "Paus (♧)" + RESET);

    private final String naipe;

    private Naipe(String naipe) {
        this.naipe = naipe;
    }

    public String toString(){
        return this.naipe;
    }
}