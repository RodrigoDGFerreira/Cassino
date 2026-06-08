package Jogo;

import User.Jogador;
import User.Aposta;
import java.util.Random;
import java.util.Scanner;

public class Roleta extends Jogo{
    private String nome = "Roleta";

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    protected int sortear(){
        Random r = new Random();
        int numero;
        numero = r.nextInt(37);
        return numero;
    }
    protected void menu(){
        System.out.println("Qual deseja apostar: \n1 - Par\n2 - Impar\n0 - Numero 0");
    }
    protected void verificarAposta(int opcao, int resultado, int valor, Jogador jogador, int numeroSorteado){
        System.out.println("O número sorteado foi: " + numeroSorteado);

        if(opcao == resultado){
            if(resultado == 0){
                System.out.println("O jogador ganhou: " + valor * 10);
                jogador.adicionarSaldo(valor * 10);
                Aposta aposta = new Aposta(valor,"Vitoria",this,jogador);
                jogador.adicionarAposta(aposta);
            } else {
                System.out.println("O jogador ganhou: " + valor * 2);
                jogador.adicionarSaldo(valor * 2);
                Aposta aposta = new Aposta(valor,"Vitoria",this,jogador);
                jogador.adicionarAposta(aposta);
            }
        } else {
            System.out.println("O jogador perdeu!!!");
            Aposta aposta = new Aposta(valor,"Derrota",this,jogador);
            jogador.adicionarAposta(aposta);
        }
    }
    protected int verificar(int numero){
        if(numero == 0){
            return 0;
        } else if(numero % 2 == 0){
            return 1;
        } else {
            return 2;
        }
    }

    public void jogar(int valor, Jogador jogador) {
        if(!verificarSaldo(valor, jogador)){
            return;
        }

        Scanner sc = new Scanner(System.in);

        menu();
        int opcao = sc.nextInt();
        if(opcao < 0 || opcao > 2){
            System.out.println("Opção inválida.");
            jogador.adicionarSaldo(valor);
            Aposta aposta = new Aposta(valor,"Invalidada",this,jogador);
            jogador.adicionarAposta(aposta);
            return;
        }
        int numeroSorteado = sortear();
        int resultado = verificar(numeroSorteado);

        verificarAposta(opcao, resultado, valor, jogador, numeroSorteado);
    }
    public String toString() {
        return "Roleta{" +
                "nome='" + nome + '}';
    }
}
