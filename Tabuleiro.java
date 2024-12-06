class Tabuleiro {
    
    private Peca[][] tabuleiro = {
        {new Torre(Cor.PRETO), new Cavalo(Cor.PRETO), new Bispo(Cor.PRETO), new Rainha(Cor.PRETO), new Rei(Cor.PRETO), new Bispo(Cor.PRETO), new Cavalo(Cor.PRETO), new Torre(Cor.PRETO)},
        {new Peao(Cor.PRETO), new Peao(Cor.PRETO), new Peao(Cor.PRETO), new Peao(Cor.PRETO), new Peao(Cor.PRETO), new Peao(Cor.PRETO), new Peao(Cor.PRETO), new Peao(Cor.PRETO)},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {new Peao(Cor.BRANCO), new Peao(Cor.BRANCO), new Peao(Cor.BRANCO), new Peao(Cor.BRANCO), new Peao(Cor.BRANCO), new Peao(Cor.BRANCO), new Peao(Cor.BRANCO), new Peao(Cor.BRANCO)},
        {new Torre(Cor.BRANCO), new Cavalo(Cor.BRANCO), new Bispo(Cor.BRANCO), new Rainha(Cor.BRANCO), new Rei(Cor.BRANCO), new Bispo(Cor.BRANCO), new Cavalo(Cor.BRANCO), new Torre(Cor.BRANCO)},
    };
    private Partida partida;

    Tabuleiro(Peca[][] tab, Partida partida){
        this.tabuleiro = new Peca[8][8];
        this.tabuleiro = tab;
        this.partida = partida;

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


    Tabuleiro(Partida partida){
        updatePosition();
    }

    public Peca[][] getTabuleiro(){
        return this.tabuleiro;
    }

    public Object getPartida() {
        return this.partida;
    }
}