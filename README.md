🃏 Jogo de Paciência em Java
📖 Sobre o Projeto
Este projeto é uma implementação do clássico jogo de cartas Paciência, desenvolvido inteiramente em Java como uma aplicação de console.

O principal objetivo é aplicar e solidificar os conceitos fundamentais da Programação Orientada a Objetos (POO), criando um software modular, coeso e de fácil manutenção.

O jogo simula todas as regras e funcionalidades da versão tradicional, permitindo que o utilizador interaja através de comandos de texto no terminal.

✨ Funcionalidades
Início de Jogo Completo
Distribuição automática das 28 cartas nas sete colunas do tableau e das 24 cartas restantes no monte de compra.

Movimentação de Cartas:

Mover cartas do monte para as pilhas de fundação.

Mover cartas do monte para as colunas de construção.

Mover cartas das colunas de construção para as pilhas de fundação.

Mover uma ou mais cartas entre as colunas de construção.

Validação de Regras:

Fundações: Ordem crescente (Ás a Rei) e mesmo naipe.

Construção: Ordem decrescente (Rei a Ás) e cores alternadas.

Apenas Reis podem ser movidos para colunas vazias.

Visualização do Jogo:
Exibição clara do estado atual do tabuleiro, incluindo o monte, as fundações e todas as colunas de construção, utilizando cores e formatação para melhor legibilidade.

Reiniciar e Sair:
Opções para reiniciar o jogo a qualquer momento ou encerrar a aplicação.

🏗️ Arquitetura e Design
O projeto foi estruturado com base nos pilares da POO para garantir uma arquitetura limpa e desacoplada.

Pilares da POO Aplicados
Abstração
A classe abstrata Estrutura define um contrato genérico para Pilha, Fila e ListaLigada, permitindo que o sistema as trate de forma polimórfica.

Encapsulamento
O estado dos objetos (como os atributos da classe Carta) é protegido e acessado apenas através de métodos públicos.

Herança
As classes Pilha, Fila e ListaLigada herdam de Estrutura, reutilizando a definição da interface e estabelecendo uma hierarquia clara.

Polimorfismo
Métodos como adicionar() têm comportamentos diferentes dependendo se o objeto é uma Pilha, Fila ou ListaLigada, simplificando a lógica do controle do jogo.

📦 Estrutura de Pacotes
principal – Classe Main, responsável pela interface com o utilizador e pelo ciclo de vida da aplicação.

modelo – Entidades do domínio como Carta, Baralho, Jogo e regras (Regra).

estrutura – Estruturas de dados genéricas (Pilha, Fila, ListaLigada, No).

exceptions – Exceções personalizadas como JogadaInvalidaException.

util – Classes utilitárias como FormatadorConsole, para formatar a saída no terminal.

🛠️ Tecnologias Utilizadas
Java 11+

Programação Orientada a Objetos

Estruturas de Dados implementadas manualmente:

Pilha

Fila

Lista Ligada

⚠️ Nota: Nenhuma biblioteca padrão de estruturas foi utilizada (ArrayList, List, Collections, etc).

▶️ Como Jogar
Siga as instruções no menu do jogo para interagir com o sistema e mover as cartas de acordo com as regras do Paciência!

👤 Autor
Leandro Hideki Tsuchida

GitHub: Leleco04

LinkedIn: Leandro Hideki Tsuchida
