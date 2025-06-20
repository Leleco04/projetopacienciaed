package estrutura;

import modelo.Carta;

public class Fila<T> extends Estrutura<T> {
    private No<T> cabeca;
    private No<T> cauda;

    public Fila() {
        this.cabeca = null;
        this.cauda = null;
    }

    @Override
    public void adicionar(T valor) {
        No<T> novo = new No<>(valor);
        if (cabeca == null) {
            cabeca = novo;
            cauda = novo;
        } else {
            cauda.proximo = novo;
            cauda = novo;
        }
    }

    @Override
    public T verTopo() {
        return cabeca.valor;
    }

    @Override
    public boolean estaVazia() {
        return cabeca == null;
    }

    @Override
    public void exibir() {
        No<T> no = cabeca;
        while(no != null) {
            System.out.println(no.valor.toString());
            no = no.proximo;
        }
    }

    @Override
    public T remover() {
        if (cabeca == null) {
            return null;
        }
        T valor = cabeca.valor;
        cabeca = cabeca.proximo;
        if (cabeca == null) {
            cauda = null;
        }
        return valor;
    }
}
