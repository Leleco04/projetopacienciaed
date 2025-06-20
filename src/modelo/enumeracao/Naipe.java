package modelo.enumeracao;

public enum Naipe {
    OUROS("Ouro (♢)"),
    COPAS("Copas (♡)"),
    ESPADAS("Espadas (♤)"),
    PAUS("Paus (♧)");

    private final String naipe;

    private Naipe(String naipe) {
        this.naipe = naipe;
    }

    public String toString(){
        return this.naipe;
    }
}