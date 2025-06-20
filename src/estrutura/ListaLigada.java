package estrutura;

public class ListaLigada<T> extends Estrutura<T> {
    private No<T> cabeca;
    private No<T> cauda;

    public ListaLigada() {
        this.cabeca = null;
    }

    @Override
    public T verTopo() {
        if(this.cabeca == null) {
            return null;
        }
        return cabeca.valor;
    }

    @Override
    public boolean estaVazia() {
        return cabeca == null;
    }

    @Override
    public void exibir() {
        No<T> no = cabeca;
        while (no != null) {
            System.out.println(no.valor.toString());
            no = no.proximo;
        }
    }

    // Mover validação para a classe Regra
    @Override
    public void adicionar(T valor) {
        No<T> novo = new No<>(valor);
        novo.proximo = cabeca;
        this.cabeca = novo;

        /*

        if (cabeca != null && cabeca.valor.getNumero() == carta.getNumero() - 1 && !cabeca.valor.getNaipeString().equals(carta.getNaipeString()) && !cabeca.valor.getCorString().equals(carta.getCorString())) {
            carta.mostrarCarta();
            No<Carta> novo = new No<>(carta);
            novo.proximo.valor.esconderCarta();
            novo.proximo = cabeca;
            cabeca = novo;
        } else if (cabeca == null) {
            No<Carta> novo = new No<>(carta);
            novo.proximo = cabeca;
            cabeca = novo;
        }

         */
    }

    @Override
    public T remover() {
        T removido = cabeca.valor;

        cabeca = cabeca.proximo;
        if (cabeca == null) {
            cauda = null;
        }
        return removido;
    }

    public T get(int indice) {
        No<T> atual = cabeca;
        for(int i = 0; atual != null && i < indice; i++) {
            atual = atual.proximo;
        }
        return atual.valor;
    }
}
