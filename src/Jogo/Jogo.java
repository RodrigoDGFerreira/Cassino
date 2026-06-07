package Jogo;
import User.Jogador;
public abstract class Jogo {
    private String jogo;
    public String getJogo() {return jogo;}

    public void setJogo(String jogo) {this.jogo = jogo;}

    protected boolean verificarSaldo(int valor,Jogador jogador){
        if(!jogador.verificarAposta(valor)){
            System.out.println("saldo insuficiente");
            return false;
        }
        jogador.removerSaldo(valor);
        return true;
    }
    public abstract void jogar(int valor, Jogador jogador);

    public String toString() {
        return "Jogo: " + jogo;
    }
}
