package Jogo;

import java.util.Random;

public class CacaNiquel extends Jogo{
    protected int sortear(){
        Random random = new Random();
        return random.nextInt(9) + 1;
   }

   private boolean verificarAposta(int n1, int n2, int n3){
        if(n1 == n2 && n1==n3){
            return true;
        }
        return false;
   }

   public void jogar() {
        int n1,n2,n3;
        n1 = sortear();
        n2 = sortear();
        n3 = sortear();

        if(verificarAposta(n1,n2,n3)){
            System.out.println(" | " +n1 + " | " + n2 + " | " + n3 + " | ");
            System.out.println("Parabéns você Ganhou!!");
        }else{
            System.out.println(" | " +n1 + " | " + n2 + " | " + n3 + " | ");
            System.out.println("Você perdeu");
        }
    }



}
