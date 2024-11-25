import java.util.ArrayList;
import java.util.Scanner;

class Partida {
    private Jogador jogador1;
    private Jogador jogador2;
    private Tabuleiro tabuleiro;
    private GameStatus gameStatus;
    private Turno turno;
    private ArrayList<Turno> historico;


    Partida(){

        this.gameStatus = GameStatus.INGAME;
        this.historico = new ArrayList<>();
        this.tabuleiro = new Tabuleiro();
        this.jogador1 = new Jogador(Cor.BRANCO);
        this.jogador2 = new Jogador(Cor.PRETO);
        
        this.turno = null;
        
    }

    //criação de partida com tabuleiro para teste
    Partida(Tabuleiro tab){
        this.tabuleiro = tab;
        
        this.gameStatus = GameStatus.INGAME;
        this.historico = new ArrayList<>();
        this.jogador1 = new Jogador(Cor.BRANCO);
        this.jogador2 = new Jogador(Cor.PRETO);
        
        this.turno = null;
        
    }
    
    public void iniciarJogo() {
        
        Scanner s = new Scanner(System.in);
        while (this.gameStatus != GameStatus.XEQUEMATE) {

            if (this.gameStatus != GameStatus.INGAME) {
                System.out.println(gameStatus);
            }
            this.mostrarTela();


            if (this.turno == null || this.turno.getJogador().getCor() == Cor.PRETO) {
                this.turno = new Turno(this.jogador1);
            }else{
                this.turno = new Turno(this.jogador2);
            }

            while (true) {
                System.out.println("Qual peca mover? X jogardor: "+ this.turno.getJogador().getCor());
                int xInicial = s.nextInt();
                System.out.println("Qual peca mover? Y jogardor: "+ this.turno.getJogador().getCor());
                int yInicial = s.nextInt();

                boolean inTable = xInicial >= 0 && xInicial < 8 && yInicial >= 0 && yInicial < 8;

                if (inTable && this.tabuleiro.getTabuleiro()[xInicial][yInicial] != null && this.tabuleiro.getTabuleiro()[xInicial][yInicial].getCor() == turno.getJogador().getCor()) {
                    this.turno.setPecaMovida(this.tabuleiro.getTabuleiro()[xInicial][yInicial]);
                    this.turno.setLocalInicio(new Posicao(xInicial, yInicial));
                }else{
                    System.out.println("Posição invalida!!");  
                    continue;
                }

                System.out.println("Para aonde devo mover? X jogador: "+ this.turno.getJogador().getCor());
                int xFinal = s.nextInt();
                System.out.println("Para aonde devo mover? Y jogardor: "+ this.turno.getJogador().getCor());
                int yFinal = s.nextInt();

                
                
                if (this.turno.getPecaMovida().movimentoValido(new Posicao(xFinal, yFinal), tabuleiro)) {
                    
                    this.turno.setLocalFinal(new Posicao(xFinal, yFinal));
                    
                    this.tabuleiro.getTabuleiro()[this.turno.getLocalInicio().getPosicaoX()][this.turno.getLocalInicio().getPosicaoY()] = null;
                    this.tabuleiro.getTabuleiro()[this.turno.getLocalFinal().getPosicaoX()][this.turno.getLocalFinal().getPosicaoY()] = this.turno.getPecaMovida();

                    tabuleiro.updatePosition();

                    if (inXequeMate()) {
                        this.gameStatus = GameStatus.XEQUEMATE;
                    }else if (inXeque()) {
                        this.gameStatus = GameStatus.XEQUE;
                    }else{
                        this.gameStatus = GameStatus.INGAME;
                    }

                    this.turno.setStatus(gameStatus);
                    historico.add(turno);

                    break;
                }else{
                    System.out.println("Posição invalida!!");
                }
            }
        }

        s.close();
        
        for (Turno turno : historico) {
            System.out.println("-----------------------");
            System.out.println("Jogador: "+turno.getJogador().getCor());
            System.out.println("Status: "+ turno.getStatus());
            System.out.println("Peça movida: "+turno.getPecaMovida());
            System.out.println("Local Inicial: "+ turno.getLocalInicio().getPosicaoX() +" X, " + turno.getLocalInicio().getPosicaoY() + " Y");
            System.out.println("Local Final: " + turno.getLocalFinal().getPosicaoX()+ " X, " + turno.getLocalFinal().getPosicaoY()+" Y");
            System.out.println("-----------------------");
        }
        
        mostrarTela();
    }

    public void mostrarTela(){
        
        System.out.print(" ");
        for (int i = 0; i < tabuleiro.getTabuleiro().length; i++) {
            System.out.print(" "+i);    
        }
        
        System.out.println();
        
        for (int i = 0; i < tabuleiro.getTabuleiro().length; i++) {
            System.out.print(i+"|");
            for (int j = 0; j < tabuleiro.getTabuleiro()[i].length; j++) {
                
                if (tabuleiro.getTabuleiro()[i][j] == null) {
                    System.out.print(" |");
                    
                }else{
                    System.out.print(tabuleiro.getTabuleiro()[i][j] + "|");
                }
            }

            System.out.println();
        }

        System.out.println();
    };

    public boolean inXequeMate(){
        
        int countKings = 0;

        for (int index = 0; index < tabuleiro.getTabuleiro().length; index++) {
            for (int i = 0; i < tabuleiro.getTabuleiro()[index].length; i++) {
                if (tabuleiro.getTabuleiro()[index][i] != null && tabuleiro.getTabuleiro()[index][i].getClass().getName().equals("Rei")) {
                    countKings++;
                }
            }
        }

        
        return countKings < 2;
    }
    
    public boolean inXeque(){
        Peca reiInimigo = null;
        for (int index = 0; index < tabuleiro.getTabuleiro().length; index++) {
            for (int i = 0; i < tabuleiro.getTabuleiro()[index].length; i++) {
                if (tabuleiro.getTabuleiro()[index][i] != null && tabuleiro.getTabuleiro()[index][i].getClass().getName().equals("Rei") && tabuleiro.getTabuleiro()[index][i].getCor() != turno.getJogador().getCor()) {
                    reiInimigo = tabuleiro.getTabuleiro()[index][i];
                }
            }
        }
        
        
        return turno.getPecaMovida().movimentoValido(reiInimigo.getPosicao(),tabuleiro);
    }
}
