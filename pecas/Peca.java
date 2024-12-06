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

    public boolean mesmaCor(Posicao destino, Tabuleiro tabuleiro){
        Peca pecaDestino = tabuleiro.getTabuleiro()[destino.getPosicaoX()][destino.getPosicaoY()];
        return pecaDestino != null && pecaDestino.getCor() == this.getCor();
    }
    public abstract boolean movimentoValido(Posicao destino, Tabuleiro tabuleiro);
}