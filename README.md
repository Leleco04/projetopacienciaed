Jogo de Paciência em Java
📖 Sobre o Projeto
Este projeto é uma implementação do clássico jogo de cartas Paciência, desenvolvido inteiramente em Java como uma aplicação em console. O principal objetivo foi aplicar e solidificar os conceitos fundamentais da Programação Orientada a Objetos (POO), criando um software modular, coeso e de fácil manutenção.

O jogo simula todas as regras e funcionalidades da versão tradicional, permitindo que o utilizador interaja através de comandos de texto no terminal.

✨ Funcionalidades
Início de Jogo Completo: Distribuição automática das 28 cartas nas sete colunas do tableau e das 24 cartas restantes no monte de compra.

Movimentação de Cartas:

Mover cartas do monte para as pilhas de fundação.

Mover cartas do monte para as colunas de construção.

Mover cartas das colunas de construção para as pilhas de fundação.

Mover uma ou mais cartas entre as colunas de construção.

Validação de Regras: O sistema valida todas as jogadas de acordo com as regras do Paciência:

Fundações: Ordem crescente (Ás a Rei) e mesmo naipe.

Construção: Ordem decrescente (Rei a Ás) e cores alternadas.

Apenas Reis podem ser movidos para colunas vazias.

Visualização do Jogo: Exibição clara do estado atual do tabuleiro, incluindo o monte, as fundações e todas as colunas de construção, utilizando cores e formatação para melhor legibilidade.

Reiniciar e Sair: Opções para reiniciar o jogo a qualquer momento ou para encerrar a aplicação.

🏗️ Arquitetura e Design
O projeto foi estruturado com base nos pilares da POO para garantir uma arquitetura limpa e desacoplada.

Pilares da POO Aplicados:
Abstração: A classe abstrata Estrutura<T> define um contrato genérico para Pilha, Fila e ListaLigada, permitindo que o sistema as trate de forma polimórfica.

Encapsulamento: O estado dos objetos (como os atributos da classe Carta) é protegido e privado, sendo acessado apenas através de métodos públicos, o que garante a integridade dos dados.

Herança: As classes Pilha, Fila e ListaLigada herdam da classe Estrutura, reutilizando a definição da interface e estabelecendo uma hierarquia clara.

Polimorfismo: A mesma chamada de método, como adicionar(), tem comportamentos diferentes dependendo se o objeto é uma Pilha, Fila ou ListaLigada, simplificando a lógica no controlo do jogo.

Estrutura de Pacotes
O código está organizado em pacotes com responsabilidades bem definidas:

principal: Contém a classe Main, responsável pela interface com o utilizador e pelo ciclo de vida da aplicação.

modelo: As classes que representam as entidades do domínio do problema (Carta, Baralho, Jogo) e as regras de negócio (Regra).

estrutura: Estruturas de dados genéricas (Pilha, Fila, ListaLigada, No) usadas para construir o jogo.

exceptions: Exceções personalizadas (JogadaInvalidaException) para um controlo de erros mais semântico.

util: Classes utilitárias, como o FormatadorConsole, para formatação da saída no terminal.

🛠️ Tecnologias Utilizadas
Java 11+

Programação Orientada a Objetos

Estruturas de Dados (Pilha, Fila, Lista Ligada) implementadas de forma manual, sem o uso de bibliotecas java como ArrayList, List, Collections, etc.

Siga as instruções no menu do jogo para jogar!

👤
https://github.com/Leleco04
https://www.linkedin.com/in/leandro-hideki-tsuchida-3ab4742a0/
