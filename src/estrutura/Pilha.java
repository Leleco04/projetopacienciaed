package estrutura;

import exceptions.JogadaInvalidaException;
import modelo.Carta;

public class Pilha {
    private No<Carta> cabeca;

    public Pilha() {
        this.cabeca = null;
    }

    public void push(Carta carta) throws JogadaInvalidaException {
        // Faz a validação do movimento das pilhas de funcação (4 naipes)
        if (cabeca != null && cabeca.valor.getNumero() == carta.getNumero() - 1 && cabeca.valor.getNaipeString().equals(carta.getNaipeString()) && cabeca.valor.getCorString().equals(carta.getCorString())) {
            No<Carta> novo = new No<Carta>(carta);
            novo.proximo = cabeca;
            cabeca = novo;
        } else if (cabeca == null && carta.getNumero() == 1) {
            No<Carta> novo = new No<Carta>(carta);
            novo.proximo = cabeca;
            cabeca = novo;
        } else {
            throw new JogadaInvalidaException("Você não pode fazer esse movimento.");
        }
    }

    public void exibirPilha() {
        No<Carta> atual = cabeca;
        while (atual != null) {
            System.out.println(atual.valor);
            atual = atual.proximo;
        }
    }
}