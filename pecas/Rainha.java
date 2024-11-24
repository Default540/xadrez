public class Rainha extends Peca{

    Rainha(Cor c){
        super(c);
    }


    @Override
    public boolean movimentoValido(Posicao destino, Tabuleiro tabuleiro) {

        Torre t = new Torre(this.getCor());
        t.setPosicao(this.getPosicao());

        Bispo b = new Bispo(this.getCor());
        b.setPosicao(this.getPosicao());
        

        if (b.movimentoValido(destino, tabuleiro) || t.movimentoValido(destino, tabuleiro)) {
            return true;
        }

        return false;
    }

    
    @Override
    public String toString() {
        
        if (this.cor ==  Cor.BRANCO) {
            return "D";
        }else{
            return "d";
        }
    }
}
