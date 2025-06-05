package estrutura;

import modelo.Carta;

public class Fila {
    private No<Carta> cabeca;
    private No<Carta> cauda;

    public Fila() {
        this.cabeca = null;
        this.cauda = null;
    }

    public void enqueue(Carta valor) {
        No<Carta> novo = new No<Carta>(valor);
        if (cabeca == null) {
            cabeca = novo;
            cauda = novo;
        } else {
            cauda.proximo = novo;
            cauda = novo;
        }
    }

    public void rodarFila() {
        cabeca.valor.esconderCarta();
        enqueue(dequeue());
        cabeca.valor.mostrarCarta();
        exibirFila();
    }

    public void exibirFila() {
        No<Carta> no = cabeca;
        int contagem = 1;
        while(no != null) {
            System.out.println(String.valueOf(contagem) + " - " + no.valor);
            contagem++;
            no = no.proximo;
        }
    }

    public Carta dequeue() {
        if (cabeca == null) {
            return null;
        }
        Carta valor = cabeca.valor;
        cabeca = cabeca.proximo;
        if (cabeca == null) {
            cauda = null;
        }
        return valor;
    }

    public Carta get() {
        return cabeca.valor;
    }
}
