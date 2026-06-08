package Cassino;
import User.*;
import Jogo.*;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;
import Arquivo.ArquivoJogador;
import Arquivo.ArquivoAposta;

public class Cassino {
    private ArrayList<Jogador> jogadores;
    private SistemaLogin sistemaLogin;
    private Scanner scanner;
    private ArquivoJogador arquivoJogador;
    private ArquivoAposta arquivoAposta;

    public Cassino(){
        this.arquivoJogador = new ArquivoJogador();
        this.arquivoAposta = new ArquivoAposta();

        this.jogadores = arquivoJogador.carregarJogadores();
        this.arquivoAposta.carregarApostas(jogadores);

        this.sistemaLogin = new SistemaLogin();
        this.scanner = new Scanner(System.in);
    }
    private int lerInteiro(String mensagem) {
        int numero;

        while (true) {
            try {
                System.out.println(mensagem);
                numero = scanner.nextInt();
                scanner.nextLine();
                return numero;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
                scanner.nextLine();
            }
        }
    }
    public int menuCadastro(){
        System.out.println("======= Cassinão do Rodrigão =======");
        System.out.println("1 - Cadastrar Jogador" +
                "\n2 - Login" +
                "\n3 - Consultar Jogadores"+
                "\n0 - Sair");
        return lerInteiro("escolha uma opção:");
    }
    public void iniciar(){
        int opcao;
        try {
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
                        arquivoJogador.salvarJogador(jogadores);
                        arquivoAposta.salvarApostas(jogadores);
                        System.out.println("Saindo do Sistema...");
                        break;
                    }
                    default:{
                        System.out.println("Opção invalida!");
                    }
                }
            }while(opcao!=0);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

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
        arquivoJogador.salvarJogador(jogadores);
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
        arquivoJogador.salvarJogador(jogadores);
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
        return lerInteiro("Escolha uma opção:");
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
                    arquivoJogador.salvarJogador(jogadores);
                    break;
                }
                case 6:{
                    verHistico(jogador);
                    break;
                }
                case 0:{
                    arquivoJogador.salvarJogador(jogadores);
                    arquivoAposta.salvarApostas(jogadores);
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
        int saldo = lerInteiro("Quantos deseja depositar:");
        System.out.println("Saldo depositado: " + saldo);
        return saldo;
    }
    public void jogar(Jogo jogo, Jogador jogador){
        int valor = lerInteiro("Informe o valor que quer apostar: ");
        jogo.jogar(valor, jogador);

        arquivoJogador.salvarJogador(jogadores);
        arquivoAposta.salvarApostas(jogadores);
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
