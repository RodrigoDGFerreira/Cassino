package Jogo;
import User.Jogador;
public abstract class Jogo {
    protected String jogo;

    protected boolean verificarSaldo(int valor,Jogador jogador){
        if(!jogador.verificarAposta(valor)){
            System.out.println("saldo insuficiente");
            return false;
        }
        return true;
    }


    public abstract void jogar(int valor, Jogador jogador);
}
