package principal;

import estrutura.ListaLigada;
import exceptions.JogadaInvalidaException;
import modelo.Jogo;
import modelo.Regra;

import java.util.Scanner;

public class Main {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        Jogo jogo = new Jogo();

        menuPrincipal(jogo);

        teclado.close();
    }

    public static void menuPrincipal(Jogo jogo) {
        boolean sairMenu = false;
        boolean sairJogo = false;

        do {
            System.out.println("\nEscolha uma opção: ");
            System.out.println("1 - Embaralhar.");
            System.out.println("2 - Iniciar jogo.");
            System.out.println("3 - Sair.");

            int opcaoPrincipal = teclado.nextInt();

            switch (opcaoPrincipal) {
                case 1:
                    jogo.embaralhar();
                    break;
                case 2:
                    try {
                        jogo.iniciar();
                    } catch (JogadaInvalidaException e) {
                        System.out.println(e.getMessage());
                    }

                    while (!sairJogo) {
                        System.out.println("\nEscolha uma ação");
                        System.out.println("a - movimentar uma carta da fila para pilha");
                        System.out.println("b - movimentar da fila para a fila");
                        System.out.println("c - movimentar uma carta da fila para uma das listas ligadas");
                        System.out.println("d - movimentar uma carta de uma das listas ligadas para uma das pilhas");

                        // PODE movimentar 1 ou mais cartas por vez
                        System.out.println("e - movimentar uma carta de uma lista ligada para outra lista ligada");

                        System.out.println("f - reiniciar o jogo");
                        System.out.println("g - ver estado atual do jogo");

                        char acao = teclado.next().charAt(0);

                        switch (Character.toLowerCase(acao)) {
                            case 'a':
                                // OK
                                movimentarFilaParaPilha(jogo);
                                break;
                            case 'b':
                                // OK

                                System.out.println("\n" + jogo.getMonte().verTopo());

                                boolean sair = false;

                                while (!sair) {
                                    System.out.println("\nEscolha uma opção: ");
                                    System.out.println("1 - Usar carta");
                                    System.out.println("2 - Ver próxima carta");
                                    System.out.println("3 - Sair");

                                    int opcaoMonte = teclado.nextInt();

                                    switch (opcaoMonte) {
                                        case 1:
                                            System.out.println("\nEscolha uma opção");
                                            System.out.println("1 - Mover para pilha (fundação)");
                                            System.out.println("2 - Mover para lista (construção)");
                                            System.out.println("3 - Sair");

                                            opcaoMonte = teclado.nextInt();

                                            switch (opcaoMonte) {
                                                case 1:
                                                    movimentarFilaParaPilha(jogo);
                                                    sair = true;
                                                    break;
                                                case 2:
                                                    movimentarFilaParaLista(jogo);
                                                    sair = true;
                                                    break;
                                                case 3:
                                                    sair = true;
                                                    break;
                                                default:
                                                    System.out.println("\nOpção inválida.");
                                                    break;
                                            }
                                            break;
                                        case 2:
                                            jogo.rodarFila();
                                            System.out.println("\n" + jogo.getMonte().verTopo());
                                            break;
                                        case 3:
                                            sair = true;
                                            break;
                                    }
                                }

                                break;
                            case 'c':
                                // OK
                                movimentarFilaParaLista(jogo);
                                break;
                            case 'd':
                                // OK

                                for (int i = 0; i < jogo.getListaConstrucao().length; i++) {
                                    System.out.println("\nLista " + String.valueOf(i + 1) + ":");
                                    System.out.println(jogo.getListaConstrucao()[i].verTopo());
                                }

                                System.out.println("\nEscolha a carta que deseja mover: \n");

                                int lista = teclado.nextInt();

                                System.out.println(jogo.getPilhasToString());

                                System.out.println("\nPara qual pilha deseja mover: ");

                                int iPilha = teclado.nextInt();

                                try {
                                    Regra.inserirPilha(jogo.getPilhaFundacao()[iPilha - 1], jogo.getListaConstrucao()[lista - 1].verTopo());
                                    jogo.removerUmaCartaLista(jogo.getListaConstrucao()[lista - 1]);
                                } catch (JogadaInvalidaException e) {
                                    System.out.println(e.getMessage());
                                }

                                break;
                            case 'e':
                                // Movimentar de uma lista para outra

                                for (ListaLigada l : jogo.getListaConstrucao()) {
                                    System.out.print("1 - ");
                                    l.exibir();
                                }
                                break;
                            case 'f':
                                // OK
                                try {
                                    jogo.iniciar();
                                    System.out.println("\nJogo reiniciado");
                                } catch(JogadaInvalidaException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 'g':
                                // OK
                                for (int i = 0; i < jogo.getListaConstrucao().length; i++) {
                                    System.out.println("\nLista " + String.valueOf(i + 1) + ":");
                                    jogo.getListaConstrucao()[i].exibir();
                                }
                                System.out.println(jogo.getMonteToString());
                                System.out.println(jogo.getPilhasToString());
                                break;
                            default:
                                // OK
                                System.out.println("\nAção inválida.");
                                break;
                        }
                    }
                    break;
                case 3:
                    // OK
                    sairMenu = true;
                    break;
                default:
                    // OK
                    System.out.println("\nOpção inválida.");
                    break;
            }
        } while (!sairMenu);
    }

    // Mover esses 2 métodos para Jogo

    public static void movimentarFilaParaPilha(Jogo jogo) {
        System.out.println("\nMonte: ");
        System.out.println(jogo.getMonte().verTopo() + "\n");

        for (int i = 0; i < jogo.getPilhaFundacao().length; i++) {
            System.out.println("Pilha número " + (String.valueOf(i + 1) + ": "));
            if (!jogo.getPilhaFundacao()[i].estaVazia()) {
                System.out.println(jogo.getPilhaFundacao()[i].verTopo());
            }
        }

        System.out.println("\nPara qual pilha deseja movimentar a carta?");

        int pilha = teclado.nextInt();

        try {
            Regra.inserirPilha(jogo.getPilhaFundacao()[pilha - 1], jogo.getMonte().verTopo());
            jogo.removerCartaMonte();
        } catch (JogadaInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void movimentarFilaParaLista(Jogo jogo) {
        System.out.println("\nMonte: ");
        System.out.println(jogo.getMonte().verTopo());

        for (int i = 0; i < jogo.getListaConstrucao().length; i++) {
            System.out.println("\nLista " + String.valueOf(i + 1) + ":");
            jogo.getListaConstrucao()[i].exibir();
        }

        System.out.println("\nSelecione a lista para qual deseja mover:");

        int iLista = teclado.nextInt();

        try {
            Regra.inserirLista(jogo.getListaConstrucao()[iLista - 1], jogo.getMonte().verTopo());
            jogo.removerCartaMonte();
        } catch (JogadaInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }
}