package estrutura;

public class Pilha<T> extends Estrutura<T> {
    private No<T> cabeca;

    public Pilha() {
        this.cabeca = null;
    }

    @Override
    public T verTopo() {
        return cabeca.valor;
    }

    @Override
    public T remover() {
        T valorRemovido = cabeca.valor;
        cabeca = cabeca.proximo;
        return valorRemovido;
    }

    @Override
    public void adicionar(T valor) {
        No<T> novo = new No<>(valor);
        novo.proximo = cabeca;
        cabeca = novo;
    }

    @Override
    public boolean estaVazia() {
        return cabeca == null;
    }

    @Override
    public void exibir() {
        No<T> atual = cabeca;
        while (atual != null) {
            System.out.println(atual.valor.toString());
            atual = atual.proximo;
        }
    }
}