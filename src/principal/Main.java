package principal;

import estrutura.ListaLigada;
import exceptions.JogadaInvalidaException;
import modelo.Carta;
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
                    jogo.iniciar();

                    while (!sairJogo) {
                        System.out.println("\nEscolha uma ação");
                        System.out.println("a - movimentar uma carta da fila para pilha");
                        System.out.println("b - movimentar da fila para a fila");
                        System.out.println("c - movimentar uma carta da fila para uma das listas ligadas");
                        System.out.println("d - movimentar uma carta de uma das listas ligadas para uma das pilhas");
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
                                try {
                                    jogo.getRegras().verificarMonteVazio(jogo.getMonte());

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
                                } catch (JogadaInvalidaException e) {
                                    System.out.println(e.getMessage());
                                }

                                break;
                            case 'c':
                                // OK
                                movimentarFilaParaLista(jogo);
                                break;
                            case 'd':
                                // OK
                                try {
                                    exibirListasConstrucao(jogo);
                                    System.out.println("\nEscolha a lista de origem:");
                                    int lista = teclado.nextInt();

                                    System.out.println(jogo.getPilhasToString());
                                    System.out.println("\nEscolha a pilha de destino:");
                                    int pilha = teclado.nextInt();

                                    jogo.moverListaParaFundacao(lista - 1, pilha - 1);
                                } catch (JogadaInvalidaException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 'e':
                                try {
                                    exibirListasConstrucao(jogo);

                                    System.out.print("\nDe qual lista você quer mover? (1-7): ");
                                    int origem = teclado.nextInt();

                                    System.out.print("Quantas cartas do topo você quer mover?: ");
                                    int qtdCartas = teclado.nextInt();

                                    System.out.print("Para qual lista você quer mover? (1-7): ");
                                    int destino = teclado.nextInt();

                                    jogo.moverConstrucaoParaConstrucao(origem - 1, destino - 1, qtdCartas);

                                    System.out.println("\nMovimentação realizada com sucesso!");

                                } catch (JogadaInvalidaException | IndexOutOfBoundsException e) {
                                    System.out.println("\nERRO NA JOGADA: " + e.getMessage());
                                }
                                break;
                            case 'f':
                                // OK
                                jogo.iniciar();
                                System.out.println("\nJogo reiniciado");
                                break;
                            case 'g':
                                // OK
                                exibirListasConstrucao(jogo);
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

    public static void movimentarFilaParaPilha(Jogo jogo) {
        try {
            jogo.getRegras().verificarMonteVazio(jogo.getMonte());

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

            jogo.moverMonteParaFundacao(pilha - 1);
        } catch (JogadaInvalidaException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    public static void movimentarFilaParaLista(Jogo jogo) {
        try {
            jogo.getRegras().verificarMonteVazio(jogo.getMonte());

            System.out.println("\nMonte: ");
            System.out.println(jogo.getMonte().verTopo());

            exibirListasConstrucao(jogo);

            System.out.println("\nSelecione a lista para qual deseja mover:");

            int iLista = teclado.nextInt();

            jogo.moverMonteParaLista(iLista - 1);
        } catch (JogadaInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void exibirListasConstrucao(Jogo jogo) {
        for (int i = 0; i < jogo.getListaConstrucao().length; i++) {
            System.out.println("\nLista " + String.valueOf(i + 1) + ":");
            jogo.getListaConstrucao()[i].exibir();
        }
    }
}