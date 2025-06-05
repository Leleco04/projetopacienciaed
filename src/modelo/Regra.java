package modelo;

import estrutura.Pilha;
import exceptions.JogadaInvalidaException;

public class Regra {
    public static void inserirPilha(Pilha pilha, Carta carta) throws JogadaInvalidaException {
        // Faz a validação do movimento das pilhas de funcação (4 naipes)
        if (pilha.get() != null && pilha.get().getNumero() == carta.getNumero() - 1 && pilha.get().getNaipeString().equals(carta.getNaipeString()) && pilha.get().getCorString().equals(carta.getCorString()) || pilha.get() == null && carta.getNumero() == 1) {
            pilha.push(carta);
        } else {
            throw new JogadaInvalidaException("Você não pode fazer esse movimento.");
        }
    }
}