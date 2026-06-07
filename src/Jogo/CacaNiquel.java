package Jogo;

import User.Jogador;
import java.util.Random;


public class CacaNiquel extends Jogo{
    private String nome = "Caça-Níquel";

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    protected int sortear(){
        Random random = new Random();
        return random.nextInt(7) + 1;
   }

    private boolean verificarJogada(int n1, int n2, int n3){
       return n1 == n2 && n1 == n3;
   }

    public void jogar(int valor, Jogador jogador) {
        if(!verificarSaldo(valor, jogador)){
           return;
        }
        int n1,n2,n3;
        n1 = sortear();
        n2 = sortear();
        n3 = sortear();

        if(verificarJogada(n1,n2,n3)){
                System.out.println(" | " +n1 + " | " + n2 + " | " + n3 + " | ");
                System.out.println("Parabéns " + jogador.getNome() + " Ganhou!!! " + (valor*5));
                jogador.adicionarSaldo(valor*5);
        }else{
                System.out.println(" | " +n1 + " | " + n2 + " | " + n3 + " | ");
                System.out.println(jogador.getNome() +" perdeu");

            }
        }
    public String toString() {
        return "CacaNiquel{" +
                "nome='" + nome + '\'' +
                '}';
    }

}
