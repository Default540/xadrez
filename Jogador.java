public class Jogador {
    private Cor pecaCor;

    Jogador(Cor pecaCor){
        this.setPecaCor(pecaCor);;
    }

    public Cor getCor(){
        return this.pecaCor;
    }
    public void setPecaCor(Cor cor){
        this.pecaCor = cor;
    }
}
