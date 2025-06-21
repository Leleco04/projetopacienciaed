package modelo;

import estrutura.Fila;
import estrutura.ListaLigada;
import estrutura.Pilha;
import exceptions.JogadaInvalidaException;

public class Regra {

    public Regra() {}

    // Método para inserir carta na pilha de fundação (4 pilhas, ordenadas de forma crescente, mesmo naipe)
    public void validarInsercaoPilha(Pilha<Carta> pilha, Carta carta) throws JogadaInvalidaException {
        // Verifica se a pilha que irá receber a carta está vazia
        if (pilha.estaVazia()) {
            // Se estiver vazia, o número de carta deve ser 1 (ÀS)
            if (carta.getNumero() == 1) {
                // A carta é adicionada a pilha selecionada
                pilha.adicionar(carta);
                // Se a carta não for número 1 (ÀS)
            } else {
                // A excessão de jogada inválida é lançada
                throw new JogadaInvalidaException("\nA primeira carta da pilha de fundação deve ser um Ás.");
            }
        }
        // Se a pilha não estiver vazia
        else {
            // Pega a primeira carta da pilha
            Carta primeiraCarta = pilha.verTopo();

            // Verifica as regras para inserção na pilha de fundação
            // 1 - Verifica se o naipe das 2 cartas (Carta da pilha e carta a ser movimentada) são iguais
            // 2 - Verifica se a carta a ser movimentada é 1 número maior que a carta da pilha
            if (carta.getNaipeString().equals(primeiraCarta.getNaipeString()) && carta.getNumero() == primeiraCarta.getNumero() + 1) {
                // Adiciona a carta selecionada a pilha
                pilha.adicionar(carta);
                // Caso as regras não forem satisfeitas
            } else {
                // Lança una excessão de jogada inválida
                throw new JogadaInvalidaException("\nA carta deve seguir a sequência crescente e ser do mesmo naipe.");
            }
        }
    }

    // Método para inserir na lista (7 listas, ordenadas de forma decrescente, com cores alternadas)
    public void validarInsercaoLista(ListaLigada<Carta> lista, Carta carta) throws JogadaInvalidaException {
        if (lista.estaVazia()) {
            // Se a lista estiver vazia e o número da carta for 13 (REI - K), completa o movimento
            if (carta.getNumero() == 13) {
                // Torna a carta visível
                carta.mostrarCarta();
                // Adiciona a carta a lista ligada
                lista.adicionar(carta);
            } else {
                // Lança a excessão de movimento inválido
                throw new JogadaInvalidaException("\nA primeira carta da lista de construção deve ser um Rei (K).");
            }
            // Se a lista não estiver vazia
        } else {
            // Pega a carta que está no topo
            Carta primeiraCarta = lista.verTopo();

            // Faz a verificação de regras
            // 1 - Se a primeira carta da lista for 1 valor maior que a carta a ser movimentada
            // 2 - Se a primeira carta da lista tem a cor diferente da carta a ser movimentada
            if (primeiraCarta.getNumero() == carta.getNumero() + 1 && !primeiraCarta.getCorString().equals(carta.getCorString())) {
                // Adiciona a carta a lista ligada
                lista.adicionar(carta);
                // Se as regras não forem satisfeitas
            } else {
                // Lança a excessão de jogada inválida
                throw new JogadaInvalidaException("\nA carta deve seguir a sequência decrescente e ser de cor oposta.");
            }
        }
    }

    public void verificarMonteVazio(Fila<Carta> monte) throws JogadaInvalidaException {
        if(monte.estaVazia()) {
            throw new JogadaInvalidaException("\nEssa jogada não é válida, pois a lista está vazia.");
        }
    }

    public void verificarListaVazia(ListaLigada<Carta> lista) throws JogadaInvalidaException {
        if(lista.estaVazia()) {
            throw new JogadaInvalidaException("\nEssa jogada não é válida, pois a lista está vazia.");
        }
    }

    // Adicione este método de validação em Regra.java para a lógica acima funcionar
    public void validarInsercaoLista(Carta topoListaDestino, Carta cartaMovida) throws JogadaInvalidaException {
        if (topoListaDestino == null) { // Lista de destino vazia
            if (cartaMovida.getNumero() != 13) {
                throw new JogadaInvalidaException("A primeira carta de uma lista vazia deve ser um Rei.");
            }
        } else { // Lista de destino com cartas
            if (topoListaDestino.getCor().equals(cartaMovida.getCor())) {
                throw new JogadaInvalidaException("A carta deve ser de cor oposta.");
            }
            if (topoListaDestino.getNumero() != cartaMovida.getNumero() + 1) {
                throw new JogadaInvalidaException("A carta deve seguir a sequência decrescente.");
            }
        }
    }
}