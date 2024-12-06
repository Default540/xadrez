public class Rainha extends Peca{

    Rainha(Cor c){
        super(c);
    }


    @Override
    public boolean movimentoValido(Posicao destino, Tabuleiro tabuleiro) {

        boolean validMoviment = false;

        Torre t = new Torre(this.getCor());
        t.setPosicao(this.getPosicao());

        Bispo b = new Bispo(this.getCor());
        b.setPosicao(this.getPosicao());
        

        if (b.movimentoValido(destino, tabuleiro) || t.movimentoValido(destino, tabuleiro)) {
            validMoviment = true;
            if (validMoviment) {
                // Verifica se o rei está em xeque após o movimento
                if (isInCheckAfterMove(destino, tabuleiro)) return false;
            }
            return validMoviment;
        }

        return validMoviment;
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
