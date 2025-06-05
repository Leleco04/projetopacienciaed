package principal;

import modelo.Jogo;

import java.util.Scanner;

public class Main {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        Jogo jogo = new Jogo();

        menuPrincipal(jogo);
    }

    public static void menuPrincipal(Jogo jogo) {
        boolean finalizar = false;
        boolean sairJogo = false;

        do {
            System.out.println("\nEscolha uma opção: ");
            System.out.println("1 - Embaralhar.");
            System.out.println("2 - Iniciar jogo.");
            System.out.println("3 - Sair.");

            int opcao = teclado.nextInt();

            switch (opcao) {
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
                        System.out.println("h - sair");
                        char acao = teclado.next().charAt(0);
                        switch (Character.toLowerCase(acao)) {
                            case 'a':
                                jogo.inserirPilhaFundacao(jogo.getMonte().get());
                                break;
                            case 'b':
                                jogo.getMonte().rodarFila();
                                break;
                            case 'c':
                                jogo.inserirListaConstrucao(jogo.getMonte().dequeue());
                                break;
                            case 'd':
                                break;
                            case 'e':
                                break;
                            case 'f':
                                jogo.iniciar();
                                break;
                            case 'g':
                                jogo.verEstadoAtual();
                                break;
                            case 'h':
                                sairJogo = true;
                                break;
                            default:
                                System.out.println("Ação inválida.");
                                break;
                        }
                    }
                    break;
                case 3:
                    finalizar = true;
                    break;
                default:
                    System.out.println("\nOpção inválida.");
                    break;
            }

        } while (!finalizar);
    }
}