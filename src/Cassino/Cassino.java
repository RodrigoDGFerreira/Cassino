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
    public int menuCadastro(){
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
            opcao = menuCadastro();
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
        String login, senha;

        System.out.println("Informe o Login:");
        login = scanner.nextLine();

        System.out.println("Informe a Senha");
        senha = scanner.nextLine();

        for (Jogador j: jogadores){
            if (j.getLogin().equals(login)){
                boolean autenticar = sistemaLogin.autenticar(j, login, senha);

                if (autenticar){
                    menuJogador(j);
                    return;
                }

                if (sistemaLogin.precisaTrocarSenha()){
                    redefinirSenha(j);
                    return;
                }

                return;
            }
        }

        System.out.println("Jogador não encontrado");
    }
    public void redefinirSenha(Jogador jogador){
        String novaSenha;
        boolean senhaAlterada;
        do{
            System.out.println("Informe a nova senha: ");
            novaSenha = scanner.nextLine().trim();
            senhaAlterada = jogador.alterarSenha(novaSenha);
        }while(!senhaAlterada);
        sistemaLogin.resetarTentativas();
        System.out.println("A senha foi alterada com sucesso");
    }

    public int menuJogo(){
        System.out.println("1 - Jogar Caça Niquel" +
                "\n2 - Jogar Black Jack (21)" +
                "\n3 - Jogar Roleta"+
                "\n4 - Ver dados Jogador: "+
                "\n5 - Depositar"+
                "\n6 - Histórico de apostas"+
                "\n0 - Sair da conta");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }
    public void menuJogador(Jogador jogador){
        int opcao;

        do{
            System.out.println("======= Menu Jogador =======");
            System.out.println("Jogador: " + jogador.getNome());
            System.out.println("Saldo: " + jogador.getSaldo());
            opcao = menuJogo();
            switch (opcao){
                case 1:{
                    jogar(new CacaNiquel(),jogador);
                    break;
                }
                case 2:{
                    jogar(new BlackJack(),jogador);
                    break;
                }
                case 3:{
                    jogar(new Roleta(),jogador);
                    break;
                }
                case 4:{
                    System.out.println(jogador);
                    break;
                }
                case 5:{
                    int saldo = depositar();
                    jogador.adicionarSaldo(saldo);
                    break;
                }
                case 6:{
                    verHistico(jogador);
                    break;
                }
                case 0:{
                    System.out.println("Saindo da conta");
                    break;
                }
                default:{
                    System.out.println("opção invalida");
                    break;
                }
            }

        }while(opcao !=0);
    }

    public int depositar(){
        System.out.println("Quantos deseja depositar: ");
        int saldo = scanner.nextInt();
        System.out.println("Saldo depositado: " + saldo);
        scanner.nextLine();
        return saldo;
    }
    public void jogar(Jogo jogo,Jogador jogador){
        System.out.println("Informe o valor que quer apostar: ");
        int valor = scanner.nextInt();
        scanner.nextLine();
        jogo.jogar(valor,jogador);
    }
    public void verHistico(Jogador jogador){
        if (jogador.getHistorico().isEmpty()){
            System.out.println("O Jogador não apostou!!!");
            return;
        }
        for (Aposta aposta:jogador.getHistorico()){
            System.out.println(aposta);
        }
    }


    public void consultarJogadores(){
        if (jogadores.isEmpty()){
            System.out.println("Nenhum jogador Cadastrado");
            return;
        }
        for (Jogador j: jogadores){
            System.out.println(j);
        }
    }
    public String toString() {
        return "Cassino{" +
                "jogadores=" + jogadores +
                '}';
    }

}
