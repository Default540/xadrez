public class Tabuleiro {
    private Peca[][] tabuleiro;
    private Partida partida;

    public Tabuleiro(Peca[][] tab, Partida partida) {
        this.tabuleiro = tab;
        this.partida = partida;
        updatePosition();
    }

    public Partida getPartida() {
        return partida;
    }

    public Peca[][] getTabuleiro() {
        return tabuleiro;
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
}