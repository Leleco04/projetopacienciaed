package estrutura;

public class No<T>{
    public T valor;
    public No<T> proximo;

    public No(T valor) {
        this.valor = valor;
        this.proximo = null;
    }
}
