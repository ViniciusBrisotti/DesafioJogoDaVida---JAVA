# DesafioJogoDaVida---JAVA

## Informações gerais sobre o projeto:

Projeto baseado no Jogo da Vida de John Conways, cujo objetivo é simular o comportamento das células.
- A lógica do programa foi feita em Java 11. 
- Para a parte visual do projeto foi utilizada a biblioteca gráfica JavaFX.

## Opções:

Os seguintes botões realizam as ações listadas:

Inserir:            Insere uma célula no local do tabuleiro escolhido pelo usuário.

Apagar:             Apaga a célula selecionada pelo usuário.

Resetar:            O tabuleiro retorna ao estado inicial das células, ou seja, exibindo-as como foram inseridas pelo usuário.

Simulação de Etapa: Mostra ao usuário o resultado obtido quanto a disposição das células logo após a inserção da célula em determinada localização do tabuleiro.

Iniciar:            Inicia a aplicação do algorítimo conforme regras do jogo. De acordo com a disposição das células escolhida pelo usuário novas disposições de células serão                       exibidas.

Parar:              Para a sequência de mutação da disposição das células no tabuleiro no exato momento em que é selecionada afim de que o usuário possa analisar a imagem das                         posições das células obtidas naquela iteração.


## Regras: 

As seguintes regras compõe o core do algorítimo no qual a aplicação foi desenvolvida:

- Qualquer célula viva com menos de dois vizinhos vivos morre de solidão.
- Qualquer célula viva com dois ou três vizinhos vivos continua no mesmo estado para a próxima geração.
- Qualquer célula viva com mais de três vizinhos vivos morre de superpopulação.
- Qualquer célula viva com dois ou três vizinhos vivos continua no mesmo estado para a próxima geração.

## Propósito da Aplicação:

Enunciado do desafio proposto:

Você precisa implementar sua própria versão do Jogo da Vida, criado pelo matemático John Horton Conway em 1970. Este é um jogo do tipo zero-player, o que significa que sua evolução é determinada por seu estado inicial, não necessitando de interação subsequente. O universo do Jogo da Vida, é um tabuleiro bidimensional de células quadradas, onde cada uma delas tem um de dois estados possíveis: viva ou morta.
Cada célula interage com suas oito vizinhas adjacentes horizontais, verticais ou diagonais. A imagem abaixo ilustra quem são cada uma das células vizinhas de uma célula qualquer no tabuleiro.
