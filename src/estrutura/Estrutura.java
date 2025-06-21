package estrutura;

public abstract class Estrutura<T> {
    public abstract void adicionar(T valor);
    public abstract T remover();
    public abstract T verTopo();
    public abstract boolean estaVazia();
    public abstract void exibir();
}
