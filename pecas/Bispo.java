public class Bispo extends Peca {

    Bispo(Cor c) {
        super(c);
    }

    @Override
    public boolean movimentoValido(Posicao destino, Tabuleiro tabuleiro) {

        tabuleiro.updatePosition();

        int x = this.getPosicao().getPosicaoX();
        int y = this.getPosicao().getPosicaoY();

        boolean inTable = destino.getPosicaoX() >= 0 && destino.getPosicaoX() < 8 && destino.getPosicaoY() >= 0
                && destino.getPosicaoY() < 8;
        if (!inTable)
            return false;

        // retorna falso caso a posição final for uma peça da propria cor
        if (tabuleiro.getTabuleiro()[destino.getPosicaoX()][destino.getPosicaoY()] != null
                && tabuleiro.getTabuleiro()[destino.getPosicaoX()][destino.getPosicaoY()].getCor() == this.getCor())
            return false;

        boolean validMoviment = false;

        boolean cimaDireita = destino.getPosicaoX() < x && destino.getPosicaoY() > y
                && x + y == destino.getPosicaoY() + destino.getPosicaoX();
        boolean baixoEsquerda = destino.getPosicaoX() > x && destino.getPosicaoY() < y
                && x + y == destino.getPosicaoY() + destino.getPosicaoX();
        boolean baixoDireita = destino.getPosicaoX() > x && destino.getPosicaoY() > y
                && x - y == destino.getPosicaoX() - destino.getPosicaoY();
        boolean cimaEsquerda = destino.getPosicaoX() < x && destino.getPosicaoY() < y
                && x - y == destino.getPosicaoX() - destino.getPosicaoY();

        if (cimaDireita) {
            for (int i = 1; i <= x - destino.getPosicaoX(); i++) {
                if (tabuleiro.getTabuleiro()[x - i][y + i] != null && i != x - destino.getPosicaoX()) {
                    return false;
                }
            }
            validMoviment = true;

        } else if (baixoEsquerda) {
            for (int i = 1; i <= destino.getPosicaoX() - x; i++) {
                if (tabuleiro.getTabuleiro()[x + i][y - i] != null && i != destino.getPosicaoX() - x) {
                    return false;
                }
            }
            validMoviment = true;
        } else if (baixoDireita) {
            for (int i = 1; i <= destino.getPosicaoX() - x; i++) {
                if (tabuleiro.getTabuleiro()[x + i][y + i] != null && i != destino.getPosicaoX() - x) {
                    return false;
                }
            }
            validMoviment = true;
        } else if (cimaEsquerda) {
            for (int i = 1; i <= x - destino.getPosicaoX(); i++) {
                if (tabuleiro.getTabuleiro()[x - i][y - i] != null && i != x - destino.getPosicaoX()) {
                    return false;
                }
            }
            validMoviment = true;
        }

        return validMoviment;
    }

    @Override
    public String toString() {

        if (this.cor == Cor.BRANCO) {
            return "B";
        } else {
            return "b";
        }
    }
}
