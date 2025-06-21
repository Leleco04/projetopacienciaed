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
    private ListaLigada<Carta>[] listaConstrucao = new ListaLigada[7];
    private Pilha<Carta>[] pilhaFundacao = new Pilha[4];
    private Fila<Carta> monte;
    private Regra regras;

    public Jogo() {
        regras = new Regra();
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

    public void iniciar() {

        this.monte = new Fila<>();

        for (int i = 0; i < listaConstrucao.length; i++) {
            this.listaConstrucao[i] = new ListaLigada<>();
        }

        for (int i = 0; i < pilhaFundacao.length; i++) {
            this.pilhaFundacao[i] = new Pilha<>();
        }

        embaralhar();
        esconderCartas();

        Carta[] cartasEmbaralhadas = baralho.getBaralho();
        int indiceCartaAtual = 0;

        for (int i = 0; i < 24; i++) {
            monte.adicionar(cartasEmbaralhadas[indiceCartaAtual++]);
        }
        if (!monte.estaVazia()) {
            monte.verTopo().mostrarCarta();
        }

        for (int i = 0; i < listaConstrucao.length; i++) {
            for (int j = 0; j <= i; j++) {
                listaConstrucao[i].adicionar(cartasEmbaralhadas[indiceCartaAtual++]);
            }
            if (!listaConstrucao[i].estaVazia()) {
                listaConstrucao[i].verTopo().mostrarCarta();
            }
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
        if(!lista.estaVazia()) {
            lista.verTopo().mostrarCarta();
        }
    }

    public void moverMonteParaLista(int indiceLista) throws JogadaInvalidaException {
        // Se o monte estiver vazio lança excessão
        if (this.monte.estaVazia()) {
            throw new JogadaInvalidaException("O monte está vazio, não há carta para mover.");
        }
        // Se o indíce não for válido, a excessão é lançada
        if (indiceLista < 0 || indiceLista >= this.listaConstrucao.length) {
            throw new JogadaInvalidaException("Pilha de fundação inválida. Escolha entre 1 e 7.");
        }

        Carta cartaParaMover = this.monte.verTopo();
        ListaLigada<Carta> lista = this.listaConstrucao[indiceLista];

        regras.validarInsercaoLista(lista, cartaParaMover);

        this.removerCartaMonte();
    }

    public void moverMonteParaFundacao(int indicePilha) throws JogadaInvalidaException {
        // Se o monte estiver vazio lança excessão
        if (this.monte.estaVazia()) {
            throw new JogadaInvalidaException("O monte está vazio, não há carta para mover.");
        }
        // Se o indíce não for válido, a excessão é lançada
        if (indicePilha < 0 || indicePilha >= this.pilhaFundacao.length) {
            throw new JogadaInvalidaException("Pilha de fundação inválida. Escolha entre 1 e 4.");
        }

        // Pega a carta a ser movimentada e a pilha que será adicionada
        Carta cartaParaMover = this.monte.verTopo();
        Pilha<Carta> pilhaDestino = this.pilhaFundacao[indicePilha];

        // Tenta inserir a carta na pilha
        regras.validarInsercaoPilha(pilhaDestino, cartaParaMover);

        // Se a carta for inserida, é removida do monte
        this.removerCartaMonte();
    }

    public Regra getRegras() {
        return regras;
    }

    public void moverListaParaFundacao(int indiceLista, int indicePilha) throws JogadaInvalidaException {
        if (indiceLista < 0 || indiceLista >= this.listaConstrucao.length) {
            throw new JogadaInvalidaException("Lista de construção inválida.");
        }
        if (indicePilha < 0 || indicePilha >= this.pilhaFundacao.length) {
            throw new JogadaInvalidaException("Pilha de fundação inválida.");
        }

        ListaLigada<Carta> listaOrigem = this.listaConstrucao[indiceLista];
        Pilha<Carta> pilhaDestino = this.pilhaFundacao[indicePilha];

        getRegras().verificarListaVazia(listaOrigem);

        Carta cartaParaMover = listaOrigem.verTopo();

        getRegras().validarInsercaoPilha(pilhaDestino, cartaParaMover);

        removerUmaCartaLista(listaOrigem);
    }

    public void moverConstrucaoParaConstrucao(int indiceOrigem, int indiceDestino, int quantidadeDeCartas) throws JogadaInvalidaException {
        if (indiceOrigem < 0 || indiceOrigem >= 7 || indiceDestino < 0 || indiceDestino >= 7 || indiceOrigem == indiceDestino) {
            throw new JogadaInvalidaException("Seleção de listas inválida.");
        }

        ListaLigada<Carta> listaOrigem = this.listaConstrucao[indiceOrigem];
        ListaLigada<Carta> listaDestino = this.listaConstrucao[indiceDestino];

        if (quantidadeDeCartas <= 0 || quantidadeDeCartas > listaOrigem.tamanho()) {
            throw new JogadaInvalidaException("Quantidade de cartas para mover é inválida.");
        }

        Carta cartaInicialDoBloco = listaOrigem.get(quantidadeDeCartas - 1);
        if (!cartaInicialDoBloco.isVisivel()) {
            throw new JogadaInvalidaException("Não é possível iniciar um movimento com uma carta virada para baixo.");
        }

        this.regras.validarInsercaoLista(listaDestino.verTopo(), cartaInicialDoBloco);

        ListaLigada<Carta> blocoParaMover = listaOrigem.removerDoTopo(quantidadeDeCartas);
        listaDestino.adicionarLista(blocoParaMover);

        if (!listaOrigem.estaVazia() && !listaOrigem.verTopo().isVisivel()) {
            listaOrigem.verTopo().mostrarCarta();
        }
    }
}