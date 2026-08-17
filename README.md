# Xadrez

Jogo de xadrez em Java, jogado via console, com validação completa das regras de movimentação de cada peça.

## Sobre o projeto

Projeto desenvolvido em dupla para a disciplina de Programação Orientada a Objetos do IFRS - Rio Grande, aplicando herança e polimorfismo na modelagem de um jogo de xadrez funcional. Fui responsável pela estrutura geral do projeto (modelagem das peças, tabuleiro e lógica de movimentação); a validação de xeque-mate foi desenvolvida em conjunto com um colega.

## Funcionalidades

- Representação do tabuleiro 8x8 com posicionamento inicial completo das peças
- Validação completa dos movimentos de cada peça (Peão, Torre, Cavalo, Bispo, Rainha, Rei), implementada individualmente na classe de cada peça
- Diferenciação de cor (branco/preto) via enum `Cor`
- Controle de fluxo da partida através da classe `Partida`
- Detecção de xeque-mate

> Movimentos especiais como o roque (troca simultânea entre rei e torre) ainda não foram implementados.

## Tecnologias

- Java (orientação a objetos: herança, polimorfismo)

## Estrutura do projeto

- `Peca` — classe base abstrata para as peças
- `Cavalo`, `Bispo`, `Torre`, `Rainha`, `Rei`, `Peao` — implementações específicas de cada peça, cada uma validando seus próprios movimentos
- `Cor` — enum para definir o lado (branco/preto)
- `Tabuleiro` — gerencia o posicionamento e o estado do tabuleiro
- `Partida` — controla o fluxo do jogo e a lógica de xeque-mate
- `Main` — classe principal, inicia o jogo para partidas normais
- `MainTest` — classe usada para testar e validar a movimentação das peças isoladamente

## Como executar

\`\`\`bash
git clone https://github.com/Default540/xadrez.git
javac *.java
java Main
\`\`\`

## Aprendizados

Aplicação prática de herança e polimorfismo na modelagem de um domínio com regras bem definidas — cada peça encapsula sua própria lógica de movimentação, o que tornou o código mais organizado do que uma abordagem baseada em condicionais soltas.
