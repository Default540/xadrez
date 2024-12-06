public class MainTest {
    public static void main(String[] args) {
        
        Peca teste1 = new Cavalo(Cor.BRANCO);
        Peca teste2 = new Bispo(Cor.PRETO);
        Peca teste3 = new Bispo(Cor.PRETO);
        

        Peca[][] t = {
            {new Torre(Cor.PRETO), new Cavalo(Cor.PRETO), new Bispo(Cor.PRETO), new Rainha(Cor.PRETO), new Rei(Cor.PRETO),  new Bispo(Cor.PRETO), new Cavalo(Cor.PRETO), new Torre(Cor.PRETO)},
            {new Peao(Cor.PRETO),  null,   new Peao(Cor.PRETO),  new Peao(Cor.PRETO),   new Peao(Cor.PRETO), new Peao(Cor.PRETO),  new Peao(Cor.PRETO),   new Peao(Cor.PRETO)},
            {null,        null,         null,        null,         teste2,       null,        null,         null},
            {null,        null,       null,        teste1,         null,       teste3,        null,         null},
            {null,        null,         null,        null,         null,       null,        null,         null},
            {null,        null,         null,        new Peao(Cor.PRETO),         null,       null,        null,         null},
            {null,        new Peao(Cor.BRANCO),   new Peao(Cor.BRANCO),  new Peao(Cor.BRANCO), new Peao(Cor.BRANCO), new Peao(Cor.BRANCO), new Peao(Cor.BRANCO), new Peao(Cor.BRANCO)},
            {new Torre(Cor.BRANCO), new Cavalo(Cor.BRANCO), new Bispo(Cor.BRANCO), new Rainha(Cor.BRANCO), new Rei(Cor.BRANCO),  new Bispo(Cor.BRANCO), new Cavalo(Cor.BRANCO), new Torre(Cor.BRANCO)},
        };

        Tabuleiro tab = new Tabuleiro(t, new Partida());
        tab.updatePosition();
        Partida partida = new Partida();

        partida.iniciarJogo(); 
    }
}
