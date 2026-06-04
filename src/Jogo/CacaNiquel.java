package Jogo;

import User.Jogador;
import java.util.Random;


public class CacaNiquel extends Jogo{
    protected int sortear(){
        Random random = new Random();
        return random.nextInt(7) + 1;
   }
   Jogador j = new Jogador();

   private boolean verificarJogada(int n1, int n2, int n3){
       return n1 == n2 && n1 == n3;
   }

   public void jogar(int valor, Jogador jogador) {
        int n1,n2,n3;
        n1 = sortear();
        n2 = sortear();
        n3 = sortear();

        if(jogador.verificarAposta(valor)){
            if(verificarJogada(n1,n2,n3)){
                System.out.println(" | " +n1 + " | " + n2 + " | " + n3 + " | ");
                System.out.println("Parabéns você Ganhou!!");
                jogador.adicionarSaldo(valor);
            }else{
                System.out.println(" | " +n1 + " | " + n2 + " | " + n3 + " | ");
                System.out.println("Você perdeu");
                jogador.removerSaldo(valor);
            }
        }else{
            System.out.println("Saldo insuficiente");
        }

    }
}
