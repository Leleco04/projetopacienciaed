package estrutura;

import exceptions.JogadaInvalidaException;
import modelo.Carta;

public class ListaLigada {
    private No<Carta> cabeca;
    private No<Carta> cauda;

    public ListaLigada() {
        this.cabeca = null;
    }

    public void exibirLista() {
        No<Carta> no = cabeca;
        int contagem = 1;
        while(no != null) {
            System.out.println(String.valueOf(contagem) + " - " + no.valor);
            contagem++;
            no = no.proximo;
        }
    }

    public void inserirInicio(Carta carta) throws JogadaInvalidaException {
        // Faz a validação de movimento das pilhas de construção (7)
        if (cabeca != null && cabeca.valor.getNumero() == carta.getNumero() - 1 && !cabeca.valor.getNaipeString().equals(carta.getNaipeString()) && !cabeca.valor.getCorString().equals(carta.getCorString())) {
            carta.mostrarCarta();
            No<Carta> novo = new No<Carta>(carta);
            novo.proximo.valor.esconderCarta();
            novo.proximo = cabeca;
            cabeca = novo;
        } else if (cabeca == null) {
            No<Carta> novo = new No<Carta>(carta);
            novo.proximo = cabeca;
            cabeca = novo;
        } else {
            throw new JogadaInvalidaException("Você não pode fazer esse movimento.");
        }
    }
}
