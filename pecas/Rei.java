class Rei extends Peca {

    Rei(Cor c){
        super(c);
    }


    @Override
    public boolean movimentoValido(Posicao destino, Tabuleiro tabuleiro) {
        int x = this.getPosicao().getPosicaoX();
        int y = this.getPosicao().getPosicaoY();
        
        // Verifica se a posição de destino está dentro do tabuleiro
        boolean inTable = destino.getPosicaoX() >= 0 && destino.getPosicaoX() < 8 && destino.getPosicaoY() >= 0 && destino.getPosicaoY() < 8;
        if (!inTable) return false;
        
        //retorna falso caso a posição final for uma peça da propria cor
        if(mesmaCor(destino, tabuleiro)) return false;

        // Verifica se o rei está em xeque após o movimento
        if (isInCheckAfterMove(destino, tabuleiro)) return false;
        

        boolean validMoviment = false;


        if ((destino.getPosicaoX() == x + 1 || destino.getPosicaoX() == x || destino.getPosicaoX() == x - 1) && 
            (destino.getPosicaoY() == y + 1 || destino.getPosicaoY() == y || destino.getPosicaoY() == y - 1)) {
            validMoviment = true;
        }

        return validMoviment;
    }

    @Override
    protected boolean isInCheckAfterMove(Posicao destino, Tabuleiro tabuleiro) {
         
         Posicao posicaoAtual = this.getPosicao();
        
         // Move o rei temporariamente para a posição de destino
         this.setPosicao(destino);
 
         // Verifica se o rei está em xeque após o movimento
         boolean inCheck = ((Partida) tabuleiro.getPartida()).inXeque(this.getCor());
 
         // Restaura a posição original do rei
         this.setPosicao(posicaoAtual);
 
         return inCheck;
    }
    
    @Override
    public String toString() {
        
        if (this.cor ==  Cor.BRANCO) {
            return "R";
        }else{
            return "r";
        }
    }
}