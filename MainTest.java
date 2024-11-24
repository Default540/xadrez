public class MainTest {
    public static void main(String[] args) {
        
        Peca teste1 = new Cavalo();
        Peca teste2 = new Bispo();
        Peca teste3 = new Bispo();

        teste1.setCor(Cor.BRANCO);
        teste2.setCor(Cor.PRETO);
        teste3.setCor(Cor.PRETO);
        

        Peca[][] t = {
            {new Torre(), new Cavalo(), new Bispo(), new Rainha(), new Rei(),  new Bispo(), new Cavalo(), new Torre()},
            {new Peao(),  null,   new Peao(),  new Peao(),   new Peao(), new Peao(),  new Peao(),   new Peao()},
            {null,        null,         null,        null,         teste2,       null,        null,         null},
            {null,        null,       null,        teste1,         null,       teste3,        null,         null},
            {null,        null,         null,        null,         null,       null,        null,         null},
            {null,        null,         null,        new Peao(Cor.PRETO),         null,       null,        null,         null},
            {null,        new Peao(),   new Peao(),  new Peao(Cor.BRANCO), new Peao(), new Peao(), new Peao(), new Peao()},
            {new Torre(), new Cavalo(), new Bispo(), new Rainha(), new Rei(),  new Bispo(), new Cavalo(), new Torre()},
        };

        Tabuleiro tab = new Tabuleiro(t);
        tab.updatePosition();
        Partida partida = new Partida(tab);

        partida.iniciarJogo(); 
    }
}
