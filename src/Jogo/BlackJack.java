package Jogo;

import User.Jogador;
import java.util.Random;
import java.util.Scanner;

public class BlackJack extends Jogo{
    private int maoDealer =0;
    private int maoJogador =0;

    private int puxarCarta(){
        int carta;
        Random r = new Random();
        carta = r.nextInt(10)+1;
        return carta;
    }
    private boolean vericarMaoDealer(){
        if (maoDealer<=16){
            return true;
        }
        return false;
    }
    private void menu(){
        System.out.println("1 - Puxar carta\n" +
                "0 - Parar");
    }
    private void mostrarMao(){
        System.out.println("Mão Jogador: "+ + maoJogador+ "\nMão Dealer: " + maoDealer);
    }
    public void verificar(){
        if(maoJogador ==21&& maoDealer ==21){
            System.out.println("Empate");
        } else if (maoDealer <= 21 && maoJogador>21) {
            System.out.println("Dealer Ganhou!!!");
        }else if(maoJogador ==21 && (maoDealer>21 || maoDealer<21)){
            System.out.println("Jogador Ganhou");
        }
    }

    public void jogar(int valor,Jogador jogador){
        if(!verificarSaldo(valor, jogador)){
            return;
        }
        Scanner sc =  new Scanner(System.in);
        maoDealer = puxarCarta()*2;
        maoJogador = puxarCarta()*2;
        int puxar=0;
        mostrarMao();
        while (puxar!=0){
            menu();
            puxar = sc.nextInt();
            if(puxar==1){
                maoJogador +=puxarCarta();
            }
            if(vericarMaoDealer()){
                maoDealer +=puxarCarta();
            }
            mostrarMao();
        }




        sc.close();
    }
}
