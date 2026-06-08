package Jogo;

import User.Jogador;
import java.util.Random;
import java.util.Scanner;
import User.Aposta;

public class BlackJack extends Jogo{
    private String nome = "BlackJack";
    private int maoDealer =0;
    private int maoJogador =0;

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public int getMaoDealer() {return maoDealer;}
    public void setMaoDealer(int maoDealer) {this.maoDealer = maoDealer;}
    public int getMaoJogador() {return maoJogador;}
    public void setMaoJogador(int maoJogador) {this.maoJogador = maoJogador;}



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
            Aposta aposta = new Aposta(valor,"Derrota",this,jogador);
            jogador.adicionarAposta(aposta);
        }
        else if (maoDealer > 21) {
            System.out.println("Dealer estourou! Jogador venceu.\n");
            System.out.println(jogador.getNome() + " ganhou: " + (valor*2));
            jogador.adicionarSaldo(valor * 2);
            Aposta aposta = new Aposta(valor,"Venceu",this,jogador);
            jogador.adicionarAposta(aposta);
        }
        else if (maoJogador > maoDealer) {
            System.out.println("Jogador venceu.\n");
            System.out.println(jogador.getNome() + " ganhou: " + (valor*2));
            jogador.adicionarSaldo(valor * 2);
            Aposta aposta = new Aposta(valor,"Venceu",this,jogador);
            jogador.adicionarAposta(aposta);
        }
        else if (maoDealer > maoJogador) {
            System.out.println("Dealer venceu.\n");
            Aposta aposta = new Aposta(valor,"Derrota",this,jogador);
            jogador.adicionarAposta(aposta);
        }
        else {
            System.out.println("Empate. Aposta devolvida.\n");
            Aposta aposta = new Aposta(valor,"Empate",this,jogador);
            jogador.adicionarAposta(aposta);
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
    public String toString() {
        return "BlackJack{" +
                "nome='" + nome + '}';
    }
}
