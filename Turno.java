public class Turno {
    private Jogador jogador;
    private Peca pecaMovida;
    private Posicao localInicio;
    private Posicao localFinal;
    private GameStatus status;

    Turno(Jogador jogador){
        this.jogador = jogador;
    }

    public void setPecaMovida(Peca peca){
        this.pecaMovida = peca;
    }
    public Peca getPecaMovida(){
        return this.pecaMovida;
    }
    public Jogador getJogador(){
        return this.jogador;
    }

    public void setLocalInicio(Posicao localInicio) {
        this.localInicio = localInicio;
    }
    public Posicao getLocalInicio() {
        return localInicio;
    }
    public void setLocalFinal(Posicao localFinal) {
        this.localFinal = localFinal;
    }
    public Posicao getLocalFinal() {
        return localFinal;
    }
    public void setStatus(GameStatus status) {
        this.status = status;
    }
    public GameStatus getStatus() {
        return status;
    }
}
