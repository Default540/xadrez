public class Rainha extends Peca{

    Rainha(Cor c){
        super(c);
    }
    Rainha(){
        
    }

    @Override
    public boolean movimentoValido(Posicao destino, Tabuleiro tabuleiro) {

        Torre t = new Torre();
        t.setPosicao(this.getPosicao());
        t.setCor(this.getCor());

        Bispo b = new Bispo();
        b.setPosicao(this.getPosicao());
        b.setCor(this.getCor());


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
