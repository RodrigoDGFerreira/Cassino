package Cassino;
import User.*;
import Jogo.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Cassino {
    private ArrayList<Jogador> jogadores;
    private SistemaLogin sistemaLogin;
    private Scanner scanner;

    public Cassino(){
        this.jogadores = new ArrayList<>();
        this.sistemaLogin = new SistemaLogin();
        this.scanner = new Scanner(System.in);
    }
    public int menu(){
        System.out.println("======= Cassinão do Rodrigão =======");
        System.out.println("1 - Cadastrar Jogador" +
                "\n2 - Login" +
                "\n3 - Consultar Jogadores"+
                "\n0 - Sair");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }
    public void iniciar(){
        int opcao;
        do{
            opcao = menu();
            switch (opcao){
                case 1:{
                    cadastrarJogador();
                    break;
                }
                case 2:{
                    login();
                    break;
                }
                case 3:{
                    consultarJogadores();
                    break;
                }
                case 0:{
                    System.out.println("Saindo do Sistema...");
                    break;
                }
                default:{
                    System.out.println("Opção invalida!");
                }
            }
        }while(opcao!=0);
    }
    private String verificarVazio(String mensagem){
        String trem;
        do{
            System.out.println(mensagem);
            trem = scanner.nextLine().trim();
            if (trem.isBlank()){
                System.out.println("O campo não pode ser vazio: ");
            }
        }while(trem.isBlank());
        return trem;
    }

    public void cadastrarJogador(){
        String nome,login,senha;
        nome = verificarVazio("Informe seu Nome: ");
        login = verificarVazio("Informe seu Login: ");
        senha = verificarVazio("Informe sua Senha: ");
        Jogador jogador = new Jogador(nome,login,senha);
        jogadores.add(jogador);
        System.out.println("Jogador cadastrado com Sucesso");
    }
    public void login(){

    }
    public void consultarJogadores(){

    }
}
