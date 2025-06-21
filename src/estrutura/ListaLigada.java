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

    @Override
    public void adicionar(T valor) {
        No<T> novo = new No<>(valor);
        if (this.estaVazia()) {
            this.cabeca = novo;
            this.cauda = novo;
        } else {
            novo.proximo = this.cabeca;
            this.cabeca = novo;
        }
    }

    @Override
    public T remover() {
        if (estaVazia()) {
            return null;
        }

        T removido = cabeca.valor;
        cabeca = cabeca.proximo;

        if (cabeca == null) {
            cauda = null;
        }

        return removido;
    }

    // Retorna o valor de acordo com o índice
    public T get(int indice) {
        // Valor da cabeça
        No<T> atual = this.cabeca;

        for (int i = 0; i < indice; i++) {
            // Atualiza até chegar no índice
            atual = atual.proximo;
        }

        return atual.valor;
    }

    // Retorna o tamanho da lista ligada
    public int tamanho() {
        // Começa o contador (tamanho) em 0
        int contador = 0;
        // Valor da cabeça
        No<T> atual = this.cabeca;
        // Enquanto não chegar na parte nula da lista
        while (atual != null) {
            // Aumenta o contador
            contador++;
            // Atualiza o valor atual
            atual = atual.proximo;
        }
        return contador;
    }

    // Retorna o índice de acordo com o valor
    public int getIndice(T valor) {
        // Valor da cabeça
        No<T> atual = this.cabeca;
        // Inicia o índice em 0
        int indice = 0;
        while (atual != null) {
            // Quando o valor atual for igual ao valor passado por parâmetro o índice é retornado
            if (atual.valor.equals(valor)) {
                return indice;
            }
            // Enquanto o if não for satisfeito
            atual = atual.proximo;
            indice++;
        }
        // Caso acabe a lista e o valor não é encontrado, retorna -1 (1 valor abaixo do primeiro índice)
        return -1;
    }

    // Remove uma quantidade de nós do topo da lista
    public ListaLigada<T> removerDoTopo(int quantidade) {
        // Se a lista estiver vazia ou a quantidade de nós a remover for menor que 0
        if (quantidade <= 0 || estaVazia()) {
            return new ListaLigada<>();
        }

        // Inicia uma lista auxiliar (a que será removida)
        ListaLigada<T> listaAux = new ListaLigada<>();
        listaAux.cabeca = this.cabeca;

        No<T> noAtual = this.cabeca;
        // Avança até o último nó do bloco que será removido
        for (int i = 0; i < quantidade - 1; i++) {
            if (noAtual.proximo == null) {
                this.cabeca = null;
                this.cauda = null;
                listaAux.cauda = noAtual;
                return listaAux;
            }
            noAtual = noAtual.proximo;
        }

        listaAux.cauda = noAtual;

        this.cabeca = noAtual.proximo;

        if(this.cabeca == null) {
            this.cauda = null;
        }

        listaAux.cauda.proximo = null;

        return listaAux;
    }

    // Uma lista é adicionada a principal
    public void adicionarLista(ListaLigada<T> outraLista) {
        // Verifica se o bloco de cartas a ser movido está vazio
        if (outraLista == null || outraLista.estaVazia()) {
            return;
        }
        // Pega o último valor da outra lista
        // Aponta para o primeiro da lista atual
        outraLista.cauda.proximo = this.cabeca;

        // A cabeça da lista atual aponta para a outra cabeça
        this.cabeca = outraLista.cabeca;

        // Se a lista atual era nula, agora a cauda é a cauda da nova lista
        if (this.cauda == null) {
            this.cauda = outraLista.cauda;
        }
    }
}
