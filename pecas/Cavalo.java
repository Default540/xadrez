public class Cavalo extends Peca {

    Cavalo(Cor c){
        super(c);
    }
    Cavalo(){
        
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


        //cima baixo
        if (destino.getPosicaoX() -2 == x || destino.getPosicaoX() +2 == x) {
            if ((destino.getPosicaoY() == y-1 || destino.getPosicaoY() == y+1)) {
                validMoviment = true;
            }

        //esquerda direita   
        }else if (destino.getPosicaoY() -2 == y || destino.getPosicaoY() +2 == y) {
            if ((destino.getPosicaoX() == x-1 || destino.getPosicaoX() == x+1)) {
                validMoviment = true;
            }
        }
        return validMoviment;
    }

   
    @Override
    public String toString() {
        
        if (this.cor ==  Cor.BRANCO) {
            return "C";
        }else{
            return "c";
        }
    }
}
