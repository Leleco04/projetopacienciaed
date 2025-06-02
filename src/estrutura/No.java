package estrutura;

public class No<Carta>{
    public Carta valor;
    public No<Carta> proximo;

    public No(Carta valor) {
        this.valor = valor;
        this.proximo = null;
    }
}
