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
    private void menu(){
        System.out.println("1 - Puxar carta\n" +
                "0 - Parar");
    }
    private void mostrarMao(){
        System.out.println("Mão Jogador: " + maoJogador + "\nMão Dealer: " + maoDealer);
    }
    private void verificarResultado(int valor, Jogador jogador) {

        if (maoJogador > 21) {
            System.out.println("Você estourou! Dealer venceu.\n");
        }
        else if (maoDealer > 21) {
            System.out.println("Dealer estourou! Jogador venceu.\n");
            System.out.println(jogador.getNome() + " ganhou: " + (valor*2));
            jogador.adicionarSaldo(valor * 2);
        }
        else if (maoJogador > maoDealer) {
            System.out.println("Jogador venceu.\n");
            System.out.println(jogador.getNome() + " ganhou: " + (valor*2));
            jogador.adicionarSaldo(valor * 2);
        }
        else if (maoDealer > maoJogador) {
            System.out.println("Dealer venceu.\n");
        }
        else {
            System.out.println("Empate. Aposta devolvida.\n");

            jogador.adicionarSaldo(valor);
        }
    }

    public void jogar(int valor,Jogador jogador){
        if(!verificarSaldo(valor, jogador)){
            return;
        }
        Scanner sc =  new Scanner(System.in);
        maoDealer = puxarCarta()+ puxarCarta();
        maoJogador = puxarCarta()+ puxarCarta();
        int puxar=1;
        mostrarMao();
        while (puxar!=0 && maoJogador<=21){
            menu();
            puxar = sc.nextInt();
            if(puxar==1){
                maoJogador +=puxarCarta();

            }
            mostrarMao();
        }
        while(maoDealer<16 && maoJogador<=21){
            maoDealer += puxarCarta();
        }
        System.out.println("==== Resultado Final ====");
        mostrarMao();
        verificarResultado(valor, jogador);

    }
}
