package com.jogoDaVida;

public class Celula {

    public static int morta = 0;
    public static int viva = 1;

    int largura;
    int altura;
    int[][] tabuleiro;

    public Celula(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        this.tabuleiro = new int[largura][altura];
    }

    public static Celula copy(Celula celula) {
        Celula copy = new Celula(celula.largura, celula.altura);

        for (int y = 0; y < celula.altura; y++) {
            for (int x = 0; x < celula.largura; x++) {
                copy.setState(x, y, celula.getState(x, y));
            }
        }

        return copy;
    }

    public void printTabuleiro() {
        System.out.println("---");
        for (int y = 0; y < altura; y++) {
            String line = "|";
            for (int x = 0; x < largura; x++) {
                if (this.tabuleiro[x][y] == morta) {
                    line += ".";
                } else {
                    line += "*";
                }
            }
            line += "|";
            System.out.println(line);
        }
        System.out.println("---\n");
    }

    public void setViva(int x, int y) {
        this.setState(x, y, viva);
    }

    public void setMorta(int x, int y) {
        this.setState(x, y, morta);
    }

    public void setState(int x, int y, int state) {
        if (x < 0 || x >= largura) {
            return;
        }

        if (y < 0 || y >= altura) {
            return;
        }

        this.tabuleiro[x][y] = state;
    }

    public int countVizinhasVivas(int x, int y) {
        int count = 0;

        count += getState(x - 1, y - 1);
        count += getState(x, y - 1);
        count += getState(x + 1, y - 1);

        count += getState(x - 1, y);
        count += getState(x + 1, y);

        count += getState(x - 1, y + 1);
        count += getState(x, y + 1);
        count += getState(x + 1, y + 1);

        return count;
    }

    public int getState(int x, int y) {
        if (x < 0 || x >= largura) {
            return morta;
        }

        if (y < 0 || y >= altura) {
            return morta;
        }

        return this.tabuleiro[x][y];
    }

    public void step() {
        int[][] novoTabuleiro = new int[largura][altura];

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                int vizinhasVivas = countVizinhasVivas(x, y);
//Qualquer célula viva com menos de dois vizinhos vivos morre de solidão.
                if (getState(x, y) == viva) {
                    if (vizinhasVivas < 2) {
                        novoTabuleiro[x][y] = morta;
//Qualquer célula viva com dois ou três vizinhos vivos continua no mesmo estado para a próxima geração.
                    } else if (vizinhasVivas == 2 || vizinhasVivas == 3) {
                        novoTabuleiro[x][y] = viva;
//Qualquer célula viva com mais de três vizinhos vivos morre de superpopulação.
                    } else if (vizinhasVivas > 3) {
                        novoTabuleiro[x][y] = morta;
                    }
//Qualquer célula viva com dois ou três vizinhos vivos continua no mesmo estado para a próxima geração.
                } else {
                    if (vizinhasVivas == 3) {
                        novoTabuleiro[x][y] = viva;
                    }
                }

            }
        }

        this.tabuleiro = novoTabuleiro;
    }

}
