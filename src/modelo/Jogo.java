package modelo;

import estrutura.Estrutura;
import estrutura.Fila;
import estrutura.ListaLigada;
import estrutura.Pilha;
import exceptions.JogadaInvalidaException;

import java.util.Random;

public class Jogo {
    static Random gerador = new Random();
    private Baralho baralho;
    private Carta[] cartas;
    private ListaLigada<Carta>[] listaConstrucao = new ListaLigada[7];
    private Pilha<Carta>[] pilhaFundacao = new Pilha[4];
    private Fila<Carta> monte;

    public Jogo() {
        baralho = new Baralho();
        monte = new Fila<>();
        cartas = baralho.getBaralho();

        for (int i = 0; i < listaConstrucao.length; i++) {
            listaConstrucao[i] = new ListaLigada<>();
        }

        for (int i = 0; i < pilhaFundacao.length; i++) {
            pilhaFundacao[i] = new Pilha<>();
        }
    }

    public ListaLigada<Carta>[] getListaConstrucao() {
        return listaConstrucao;
    }

    public Pilha<Carta>[] getPilhaFundacao() {
        return pilhaFundacao;
    }

    public void iniciar() throws JogadaInvalidaException {

        this.monte = new Fila<>();

        for (int i = 0; i < listaConstrucao.length; i++) {
            this.listaConstrucao[i] = new ListaLigada<>();
        }

        for (int i = 0; i < pilhaFundacao.length; i++) {
            this.pilhaFundacao[i] = new Pilha<>();
        }

        embaralhar();
        esconderCartas();

        int[] numerosGerados = new int[52];

        // Inicialize o vetor com -1 ou outro valor inválido
        for (int k = 0; k < 52; k++) {
            numerosGerados[k] = -1;
        }

        int contagem = 0;
        int rnd;

        for (int i = 0; i < 24; i++) {
            do {
                rnd = gerador.nextInt(cartas.length);
            } while (!verificarNumero(rnd, numerosGerados));
            numerosGerados[contagem] = rnd;
            monte.adicionar(cartas[rnd]);
            contagem++;
        }

        monte.verTopo().mostrarCarta();

        for (int j = 0; j < listaConstrucao.length; j++) {
            for (int x = 0; x <= j; x++) {
                do {
                    rnd = gerador.nextInt(cartas.length);
                } while (!verificarNumero(rnd, numerosGerados));
                numerosGerados[contagem] = rnd;
                // ... lógica de mostrar/esconder e adicionar a carta ...
                Regra.inserirLista(listaConstrucao[j], cartas[rnd], "inicio");
                contagem++;
            }
            listaConstrucao[j].verTopo().mostrarCarta();
        }
    }

    public void embaralhar() {
        baralho.embaralhar();
    }

    public void esconderCartas() {
        for(int i = 0; i < baralho.getBaralho().length; i++) {
            cartas[i].esconderCarta();
        }
    }

    public void rodarFila() {
        monte.verTopo().esconderCarta();
        monte.adicionar(monte.remover());
        monte.verTopo().mostrarCarta();
    }

    public boolean verificarNumero(int n, int[] numerosGerados) {
        for (int i = 0; i < numerosGerados.length; i++) {
            if (numerosGerados[i] == n) {
                return false;
            }
        }
        return true;
    }

    public Fila<Carta> getMonte() {
        return monte;
    }

    public String getMonteToString() {
        StringBuilder monte = new StringBuilder();
        monte.append("\nMonte: \n");
        monte.append(getMonte().verTopo());
        return String.valueOf(monte);
    }

    public void verEstadoAtual() {
        for (ListaLigada lista : listaConstrucao) {
            lista.exibir();
            System.out.println();
        }
    }

    public void removerCartaMonte() {
        monte.remover();
        monte.verTopo().mostrarCarta();
    }

    public String getPilhasToString() {
        StringBuilder pilhas = new StringBuilder();
        pilhas.append("\nPilhas: \n");

        int contagem = 1;

        for (Pilha pilha : pilhaFundacao) {
            if (!pilha.estaVazia()) pilhas.append(contagem + " - " + pilha.verTopo() + "\n");
            else pilhas.append(contagem + " - Vazia.\n");
            contagem++;
        }

        return String.valueOf(pilhas);
    }

    public void removerUmaCartaLista(ListaLigada<Carta> lista) {
        lista.remover();
        lista.verTopo().mostrarCarta();
    }
}