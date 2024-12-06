import java.util.Scanner;

public class Peao extends Peca {
    
    Peao(Cor c){
        super(c);
    }
    
    @Override
    public boolean movimentoValido(Posicao destino, Tabuleiro tabuleiro) {
        
        int x = this.getPosicao().getPosicaoX();
        int y = this.getPosicao().getPosicaoY();
        
        boolean inTable = destino.getPosicaoX() >= 0 && destino.getPosicaoX() < 8 && destino.getPosicaoY() >= 0 && destino.getPosicaoY() < 8;
        if (!inTable) return false;

        //retorna falso caso a posição final for uma peça da propria cor
        if(mesmaCor(destino, tabuleiro)) return false;
        
        int countMove = 1;
        boolean validMoviment = false;
        
        
        if (this.getCor() == Cor.BRANCO) {
        
            //primeiro movimento do peão pode ser 2 ou 1
            if (getPosicao().getPosicaoX() == 6) countMove = 2;    
            
            if (destino.getPosicaoX() < x && Math.abs(destino.getPosicaoX() - x) <= countMove) {
                
                if (destino.getPosicaoY() == y) {
                    
                    validMoviment = tabuleiro.getTabuleiro()[destino.getPosicaoX()][destino.getPosicaoY()] == null;
                    
                }else if (destino.getPosicaoY() == y + 1|| destino.getPosicaoY() == y - 1 && destino.getPosicaoX() - x == 1) {
                    
                    //o movimento no eixo y só é valido ao matar uma peça
                    validMoviment = tabuleiro.getTabuleiro()[destino.getPosicaoX()][destino.getPosicaoY()] != null;   
                }
            }

        
        }else if (this.getCor() == Cor.PRETO) {

            if (getPosicao().getPosicaoX() == 1) countMove = 2;

            
            if (destino.getPosicaoX() > x && Math.abs(destino.getPosicaoX() - x) <= countMove) {
                
                if (destino.getPosicaoY() == y) {
                    
                    validMoviment = true;
                    
                }else if (destino.getPosicaoY() + 1 == y || destino.getPosicaoY() - 1 == y && destino.getPosicaoX() - x == 1) {
                    
                    validMoviment = tabuleiro.getTabuleiro()[destino.getPosicaoX()][destino.getPosicaoY()] != null;  
                }
            }
            
        }

        if (validMoviment) {
            // Verifica se o rei está em xeque após o movimento
            if (isInCheckAfterMove(destino, tabuleiro)) return false;
        }

        //promove o peão
        if (validMoviment && (destino.getPosicaoX() == 0 || destino.getPosicaoX() == 7)) {
            promover(destino, tabuleiro);
        }
        return validMoviment;
    }

    private void promover(Posicao destino, Tabuleiro tabuleiro) {
        // Remove o peão do tabuleiro
        tabuleiro.getTabuleiro()[destino.getPosicaoX()][destino.getPosicaoY()] = null;
        Scanner s = new Scanner(System.in);

        System.out.println("Escolha a peça para promover o peão: " +
                "\n1 - Rainha: 1" +
                "\n2 - Torre: 2" +
                "\n3 - Bispo: 3" +
                "\n4 - Cavalo: 4");
        s.nextInt();

        Peca novaPeca = null;
        switch (s.nextInt()) {
            case 1:
                novaPeca = new Rainha(this.getCor());
                break;
            case 2:
                novaPeca = new Torre(this.getCor());
                break;
            case 3:
                novaPeca = new Bispo(this.getCor());
                break;
            case 4:
                novaPeca = new Cavalo(this.getCor());
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
        
        // Coloca a nova peça no tabuleiro
        tabuleiro.getTabuleiro()[destino.getPosicaoX()][destino.getPosicaoY()] = novaPeca;

        System.out.println("Peão promovido a " + novaPeca.getClass().getSimpleName());
    }

    
    @Override
    public String toString() {
        
        if (this.cor ==  Cor.BRANCO) {
            return "P";
        }else{
            return "p";
        }
    }
}
