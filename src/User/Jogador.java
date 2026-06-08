package User;

import java.util.ArrayList;

public class Jogador {
    private String nome;
    private String login;
    private String senha;
    private int saldo;
    private ArrayList<Aposta> historico;
    private ArrayList<String> ultimasSenhas;

    public Jogador() {
        this.historico = new ArrayList<>();
        this.ultimasSenhas = new ArrayList<>();
    }

    public Jogador(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.saldo = 0;
        this.historico = new ArrayList<>();
        this.ultimasSenhas = new ArrayList<>();
        this.ultimasSenhas.add(senha);
    }

    public boolean verificarAposta(int valor) {
        return valor > 0 && valor <= saldo;
    }

    public void adicionarSaldo(int valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    public void removerSaldo(int valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
        }
    }

    public void adicionarAposta(Aposta aposta) {
        historico.add(aposta);
    }

    public boolean senhaUtilizada(String novaSenha) {
        return ultimasSenhas.contains(novaSenha);
    }

    public boolean alterarSenha(String novaSenha) {
        if (senhaUtilizada(novaSenha)) {
            System.out.println("A nova senha não pode ser igual às últimas 3 senhas.");
            return false;
        }

        this.senha = novaSenha;

        if (ultimasSenhas.size() == 3) {
            ultimasSenhas.remove(0);
        }

        ultimasSenhas.add(novaSenha);

        System.out.println("Senha alterada com sucesso!");
        return true;
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}


    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}


    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}


    public int getSaldo() {return saldo;}
    public void setSaldo(int saldo) {this.saldo = saldo;}


    public ArrayList<Aposta> getHistorico() {return historico;}
    public void setHistorico(ArrayList<Aposta> historico) {this.historico = historico;}


    public ArrayList<String> getUltimasSenhas() {return ultimasSenhas;}
    public void setUltimasSenhas(ArrayList<String> ultimasSenhas) {this.ultimasSenhas = ultimasSenhas;}

    @Override
    public String toString() {
        return "Jogador{" +
                "nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", saldo=" + saldo +
                ", quantidadeApostas=" + historico.size() +
                '}';
    }
}