package estrutura;

import exceptions.JogadaInvalidaException;
import modelo.Carta;

public class Pilha {
    private No<Carta> cabeca;

    public Pilha() {
        this.cabeca = null;
    }

    public void push(Carta carta) {
        // Faz a validação do movimento das pilhas de funcação (4 naipes)
        if (cabeca != null) {
            No<Carta> novo = new No<Carta>(carta);
            novo.proximo = cabeca;
            cabeca = novo;
        }
    }

    public void exibirPilha() {
        No<Carta> atual = cabeca;
        while (atual != null) {
            System.out.println(atual.valor);
            atual = atual.proximo;
        }
    }

    public Carta get() {
        if(this.cabeca != null) {
            return cabeca.valor;
        }
        return null;
    }
}