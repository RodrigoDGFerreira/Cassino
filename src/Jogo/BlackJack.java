package Jogo;

import User.Jogador;

import java.util.Random;

public class BlackJack extends Jogo{
    public int puxarCarta(){
        int carta;
        Random r = new Random();
        carta = r.nextInt(10)+1;
        return carta;
    }

    public void jogar(int valor,Jogador jogador){
        jogador.verificarAposta(valor);
    }
}
