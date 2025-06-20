package util;

public final class FormatadorConsole {
    private FormatadorConsole(){}

    // Constantes com código ANSI para melhorar a visualização do console (cores e negrito)
    // Declaradas em uma classe diferente como sugerido pelo professor
    public static final String RESET = "\u001B[0m";
    public static final String PRETO = "\u001B[40m";
    public static final String VERMELHO = "\u001B[41m";
    public static final String NEGRITO = "\u001B[1m";
}
