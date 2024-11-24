public class Torre extends Peca {

    Torre(Cor c){
        super(c);
    }
    Torre(){
        
    }

    @Override
    public boolean movimentoValido(Posicao destino, Tabuleiro tabuleiro) {
        
        int x = this.getPosicao().getPosicaoX();
        int y = this.getPosicao().getPosicaoY();
        
        boolean inTable = destino.getPosicaoX() >= 0 && destino.getPosicaoX() < 8 && destino.getPosicaoY() >= 0 && destino.getPosicaoY() < 8;
        if (!inTable) return false;
        
        //retorna falso caso a posição final for uma peça da propria cor
        if (tabuleiro.getTabuleiro()[destino.getPosicaoX()][destino.getPosicaoY()] != null && tabuleiro.getTabuleiro()[destino.getPosicaoX()][destino.getPosicaoY()].getCor() == this.getCor()) return false;

        boolean validMoviment = false;

        if (destino.getPosicaoX() != x && destino.getPosicaoY() == y) {

            //baixo para cima
            if (x > destino.getPosicaoX()) {

                for (int i = x-1; i >= destino.getPosicaoX(); i--) {

                    if (tabuleiro.getTabuleiro()[i][y] != null && i != destino.getPosicaoX()) {
                        return false;
                    }
                }

                validMoviment = true;

                //cima para baixo
            }else if (x < destino.getPosicaoX()) {
                for (int i = x+1; i <= destino.getPosicaoX(); i++) {
                    if (tabuleiro.getTabuleiro()[i][y] != null && i != destino.getPosicaoX()) {
                        return false;
                    }
                    
                }
                validMoviment = true;
            }
        
        }else if (destino.getPosicaoY() != y && destino.getPosicaoX() == x) {

            //esquerda
            if (y > destino.getPosicaoY()) {
                for (int i = y-1; i >= destino.getPosicaoY(); i--) {
                    if (tabuleiro.getTabuleiro()[x][i] != null && i != destino.getPosicaoY()) {
                        return false;
                    }
                }
                validMoviment = true;

                //direita
            }else if (y < destino.getPosicaoY()) {
                for (int i = y+1; i <= destino.getPosicaoY(); i++) {
                    if (tabuleiro.getTabuleiro()[x][i] != null && i != destino.getPosicaoY()) {
                        return false;
                    }
                }
                validMoviment = true;
            }
        }

        return validMoviment;
    }

    

    @Override
    public String toString() {
        
        if (this.cor ==  Cor.BRANCO) {
            return "T";
        }else{
            return "t";
        }
    }
}
