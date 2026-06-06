package User;

import java.util.ArrayList;

public class Jogador {
    private String nome;
    private String login;
    private String senha;
    private double saldo;
    private ArrayList<Aposta> hitorico;

    public Jogador(){}
    public Jogador(String nome,String login,String senha){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        hitorico = new ArrayList<>();
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}

    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}

    public double getSaldo() {return saldo;}
    public void setSaldo(double saldo) {this.saldo = saldo;}

    public boolean verificarAposta(int valor){
        return valor<=saldo;
    }
    public void verificarSaldo(int valor){

    }
    public void adicionarSaldo(int valor){
        saldo +=valor;
    }
    public void removerSaldo(int valor){
        saldo -=valor;
    }


    public String toString(){
        return "Jogador: " + nome +
                "\nLogin: " + login +
                "\nSaldo: " + saldo;
    }
}
