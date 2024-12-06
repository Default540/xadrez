abstract class Peca {
    protected Cor cor;
    protected Posicao posicao;

    
    Peca(Cor c){
        this.setCor(c);
    }

    public void setCor(Cor cor){
        this.cor = cor; 
    }

    public void setPosicao(Posicao posicao){
        this.posicao = posicao;
    }

    public Cor getCor(){
        return this.cor;
    }

    public Posicao getPosicao(){
        return this.posicao;
    }

    //retorna falso caso a posição final for uma peça da propria cor
    public boolean mesmaCor(Posicao destino, Tabuleiro tabuleiro){
        Peca pecaDestino = tabuleiro.getTabuleiro()[destino.getPosicaoX()][destino.getPosicaoY()];
        return pecaDestino != null && pecaDestino.getCor() == this.getCor();
    }
    public abstract boolean movimentoValido(Posicao destino, Tabuleiro tabuleiro);

    protected boolean isInCheckAfterMove(Posicao destino, Tabuleiro tabuleiro) {
         
         Posicao posicaoAtual = this.getPosicao();
        
         // Move a peça temporariamente para a posição de destino
         this.setPosicao(destino);
 
         // Verifica se o rei está em xeque após o movimento
         boolean inCheck = ((Partida) tabuleiro.getPartida()).inXeque(this.getCor());
 
         // Restaura a posição original da peça
         this.setPosicao(posicaoAtual);

         if (inCheck) {
             System.out.println("Movimento inválido, rei em xeque");
         }
 
         return inCheck;
    }
}