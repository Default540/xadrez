import java.util.ArrayList;
import java.util.Scanner;

class Partida {
    private Jogador jogador1;
    private Jogador jogador2;
    private Tabuleiro tabuleiro;
    private GameStatus gameStatus;
    private Turno turno;
    private ArrayList<Turno> historico;

    Partida() {
        this.gameStatus = GameStatus.INGAME;
        this.historico = new ArrayList<>();
        this.jogador1 = new Jogador(Cor.BRANCO);
        this.jogador2 = new Jogador(Cor.PRETO);

        // Inicializa o tabuleiro após a criação da partida
        Peca[][] pecasIniciais = {
            {new Torre(Cor.PRETO), new Cavalo(Cor.PRETO), new Bispo(Cor.PRETO), new Rainha(Cor.PRETO), new Rei(Cor.PRETO), new Bispo(Cor.PRETO), new Cavalo(Cor.PRETO), new Torre(Cor.PRETO)},
            {new Peao(Cor.PRETO), new Peao(Cor.PRETO), new Peao(Cor.PRETO), new Peao(Cor.PRETO), new Peao(Cor.PRETO), new Peao(Cor.PRETO), new Peao(Cor.PRETO), new Peao(Cor.PRETO)},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {new Peao(Cor.BRANCO), new Peao(Cor.BRANCO), new Peao(Cor.BRANCO), new Peao(Cor.BRANCO), new Peao(Cor.BRANCO), new Peao(Cor.BRANCO), new Peao(Cor.BRANCO), new Peao(Cor.BRANCO)},
            {new Torre(Cor.BRANCO), new Cavalo(Cor.BRANCO), new Bispo(Cor.BRANCO), new Rainha(Cor.BRANCO), new Rei(Cor.BRANCO), new Bispo(Cor.BRANCO), new Cavalo(Cor.BRANCO), new Torre(Cor.BRANCO)},
        };

        this.tabuleiro = new Tabuleiro(pecasIniciais, this);
    }

    public void iniciarJogo() {
        
        Scanner s = new Scanner(System.in);
        while (this.gameStatus != GameStatus.XEQUEMATE) {

            if (this.gameStatus != GameStatus.INGAME) {
                System.out.println(gameStatus);
            }
            this.mostrarTela();


            // Verifica se é o primeiro turno ou se o turno atual é do jogador 2
            if (this.turno == null || this.turno.getJogador().getCor() == Cor.PRETO) {
                this.turno = new Turno(this.jogador1);
            }else{
                this.turno = new Turno(this.jogador2);
            }

            while (true) {
                System.out.println("Jogador: "+ this.turno.getJogador().getCor()+ 
                "\nQual peça deseja mover? Linha Coluna");
                
                int xInicial = s.nextInt();
                int yInicial = s.nextInt();

                boolean inTable = xInicial >= 0 && xInicial < 8 && yInicial >= 0 && yInicial < 8;

                // Verifica se a posição de origem está dentro do tabuleiro
                if (inTable && this.tabuleiro.getTabuleiro()[xInicial][yInicial] != null && this.tabuleiro.getTabuleiro()[xInicial][yInicial].getCor() == turno.getJogador().getCor()) {
                    this.turno.setPecaMovida(this.tabuleiro.getTabuleiro()[xInicial][yInicial]);
                    this.turno.setLocalInicio(new Posicao(xInicial, yInicial));
                }else{
                    System.out.println("Posição invalida!!");  
                    continue;
                }

                System.out.println("Jogador: "+ this.turno.getJogador().getCor()+ 
                "\nPara onde deseja mover? Linha Coluna");
                int xFinal = s.nextInt();
                int yFinal = s.nextInt();

                // Verifica se a posição de destino está dentro do tabuleiro
                if (xFinal < 0 || xFinal > 7 || yFinal < 0 || yFinal > 7) {
                    System.out.println("Posição invalida!!");
                    continue;
                }
                if (gameStatus == GameStatus.XEQUE) {
                    System.out.println("Você está em xeque, salve seu rei!");
                }
                
                // Verifica se o movimento é válido para a peça selecionada e se a posição de destino está vazia ou com uma peça adversária
                if (this.turno.getPecaMovida().movimentoValido(new Posicao(xFinal, yFinal), tabuleiro)) {
                    
                    this.turno.setLocalFinal(new Posicao(xFinal, yFinal));
                    
                    this.tabuleiro.getTabuleiro()[this.turno.getLocalInicio().getPosicaoX()][this.turno.getLocalInicio().getPosicaoY()] = null;
                    this.tabuleiro.getTabuleiro()[this.turno.getLocalFinal().getPosicaoX()][this.turno.getLocalFinal().getPosicaoY()] = this.turno.getPecaMovida();

                    tabuleiro.updatePosition();

                    if (inXequeMate(Cor.BRANCO) || inXequeMate(Cor.PRETO)) {
                        this.gameStatus = GameStatus.XEQUEMATE;
                    }else if (inXeque(Cor.BRANCO) || inXeque(Cor.PRETO)) {
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
            System.out.println("Local Inicial: "+ turno.getLocalInicio().getPosicaoX() +" Linha, " + turno.getLocalInicio().getPosicaoY() + " Coluna");
            System.out.println("Local Final: " + turno.getLocalFinal().getPosicaoX()+ " Linha, " + turno.getLocalFinal().getPosicaoY()+" Coluna");
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

    public boolean inXeque(Cor corRei) {
        Peca rei = null;
        // Encontra a posição do rei
        for (int index = 0; index < tabuleiro.getTabuleiro().length; index++) {
            for (int i = 0; i < tabuleiro.getTabuleiro()[index].length; i++) {
                Peca peca = tabuleiro.getTabuleiro()[index][i];
                if (peca != null && peca instanceof Rei && peca.getCor() == corRei) {
                    rei = peca;
                    break;
                }
            }
        }
        // Se não encontrar o rei, retorna falso
        if (rei == null) {
            return false;
        }

        // Verifica se alguma peça pode atacar o rei
        Posicao posicaoRei = rei.getPosicao();
        for (int index = 0; index < tabuleiro.getTabuleiro().length; index++) {
            for (int i = 0; i < tabuleiro.getTabuleiro()[index].length; i++) {
                Peca peca = tabuleiro.getTabuleiro()[index][i];
                if (peca != null && peca.getCor() != corRei && peca.movimentoValido(posicaoRei, tabuleiro)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Verifica se o rei está em xeque mate, verificando se o rei não pode se mover para nenhuma posição válida ou se alguma peça pode salvar o rei.
    public boolean inXequeMate(Cor corRei) {
        if (!inXeque(corRei)) {
            return false;
        }

        for (int index = 0; index < tabuleiro.getTabuleiro().length; index++) {
            for (int i = 0; i < tabuleiro.getTabuleiro()[index].length; i++) {
                Peca peca = tabuleiro.getTabuleiro()[index][i];
                if (peca != null && peca.getCor() == corRei) {
                    Posicao posicaoOriginal = peca.getPosicao();
                    for (int x = 0; x < tabuleiro.getTabuleiro().length; x++) {
                        for (int y = 0; y < tabuleiro.getTabuleiro()[x].length; y++) {
                            Posicao destino = new Posicao(x, y);
                            if (peca.movimentoValido(destino, tabuleiro)) {
                                Peca pecaDestino = tabuleiro.getTabuleiro()[x][y];
                                tabuleiro.getTabuleiro()[x][y] = peca;
                                tabuleiro.getTabuleiro()[posicaoOriginal.getPosicaoX()][posicaoOriginal.getPosicaoY()] = null;
                                peca.setPosicao(destino);

                                boolean stillInCheck = inXeque(corRei);

                                // Reverte o movimento
                                tabuleiro.getTabuleiro()[posicaoOriginal.getPosicaoX()][posicaoOriginal.getPosicaoY()] = peca;
                                tabuleiro.getTabuleiro()[x][y] = pecaDestino;
                                peca.setPosicao(posicaoOriginal);

                                if (!stillInCheck) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
        
}
