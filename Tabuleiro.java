class Tabuleiro {
    
    private Peca[][] tabuleiro = {
        {new Torre(), new Cavalo(), new Bispo(), new Rainha(), new Rei(), new Bispo(), new Cavalo(), new Torre()},
        {new Peao(), new Peao(), new Peao(), new Peao(), new Peao(), new Peao(), new Peao(), new Peao()},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {new Peao(), new Peao(), new Peao(), new Peao(), new Peao(), new Peao(), new Peao(), new Peao()},
        {new Torre(), new Cavalo(), new Bispo(), new Rainha(), new Rei(), new Bispo(), new Cavalo(), new Torre()},
    };

    Tabuleiro(Peca[][] tab){
        this.tabuleiro = new Peca[8][8];
        this.tabuleiro = tab;

        updatePosition();
    }

    public void updatePosition(){
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if(tabuleiro[i][j] != null){
                    tabuleiro[i][j].setPosicao(new Posicao(i, j));
                }
            }
        }
    }

    public void setColors(){
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                if (tabuleiro[i][j] != null) {
                    if (i < 2) {
                        tabuleiro[i][j].setCor(Cor.PRETO);
                    }else{
                        tabuleiro[i][j].setCor(Cor.BRANCO);
                    }
                }
            }
        }
    }

    Tabuleiro(){
        setColors();
        updatePosition();
    }

    public Peca[][] getTabuleiro(){
        return this.tabuleiro;
    }
}