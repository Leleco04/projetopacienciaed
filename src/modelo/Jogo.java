package modelo;

import estrutura.Fila;
import estrutura.ListaLigada;
import estrutura.Pilha;
import exceptions.JogadaInvalidaException;

import java.util.Random;

public class Jogo {
    static Random gerador = new Random();
    private Baralho baralho;
    private Carta[] cartas;
    private ListaLigada[] listaConstrucao;
    private Pilha[] pilhaFundacao;
    private Fila monte;

    public Jogo() {
        baralho = new Baralho();
        listaConstrucao = new ListaLigada[7];
        pilhaFundacao = new Pilha[4];
        monte = new Fila();
        cartas = baralho.getBaralho();

        for (int i = 0; i < listaConstrucao.length; i++) {
            listaConstrucao[i] = new ListaLigada();
        }

        for (int i = 0; i < pilhaFundacao.length; i++) {
            pilhaFundacao[i] = new Pilha();
        }
    }

    public void iniciar() {
        embaralhar();
        int rnd;
        int contagem = 0;
        int[] numerosGerados = new int[52];
        for (int i = 0; i < 24; i++) {
            rnd = gerador.nextInt(1, cartas.length);
            while (!verificarNumero(rnd, numerosGerados)) {
                rnd = gerador.nextInt(1, cartas.length);
            }
            numerosGerados[contagem] = rnd;
            monte.enqueue(cartas[rnd]);
            contagem++;
        }
        monte.get().mostrarCarta();
    }

    public void embaralhar() {
        baralho.embaralhar();
    }

    public void inserirListaConstrucao(Carta carta) {
        try {
            listaConstrucao[0].inserirInicio(carta);
        } catch(JogadaInvalidaException e){
            System.out.println(e.getMessage());
        }
    }

    public void inserirPilhaFundacao(Carta carta) {
        try {
            Regra.inserirPilha(pilhaFundacao[0], carta);
        } catch(JogadaInvalidaException e){
            System.out.println(e.getMessage());
        }
    }

    public void exibirListaConstrucao() {
        listaConstrucao[0].exibirLista();
    }

    public boolean verificarNumero(int n, int[] numerosGerados) {
        for (int i = 0; i < numerosGerados.length; i++) {
            if (numerosGerados[i] == n) {
                return false;
            }
        }
        return true;
    }

    public void mostrarMonte() {
        monte.exibirFila();
    }

    public Fila getMonte() {
        return monte;
    }

    public void verEstadoAtual() {
        System.out.println("\nMonte: ");
        monte.exibirFila();

        System.out.println("\nLista: ");
        listaConstrucao[0].exibirLista();
    }
}
