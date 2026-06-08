package Arquivo;

import User.Jogador;

import java.io.*;
import java.util.ArrayList;

public class ArquivoJogador {
    private String caminho = "jogadores.txt";

    public void salvarJogador(ArrayList<Jogador> jogadores){
        try{
            FileWriter arquivo = new FileWriter(caminho);
            PrintWriter gravar = new PrintWriter(arquivo);

            for (Jogador j: jogadores){
                gravar.println(
                                j.getNome() + ";" +
                                j.getLogin() + ";" +
                                j.getSenha() + ";" +
                                j.getSaldo()

                );
            }
            gravar.close();


        } catch (Exception e) {
            System.out.println("Erro ao Salvar jogadores: " + e.getMessage());
        }
    }

    public ArrayList<Jogador> carregarJogadores(){
        ArrayList<Jogador> jogadores = new ArrayList<>();
        try{
            File arquivo = new File(caminho);
            if (!arquivo.exists()){
                return jogadores;
            }
            FileReader lerArquivo = new FileReader(caminho);
            BufferedReader leitor = new BufferedReader(lerArquivo);

            String inserirLinha;

            while((inserirLinha = leitor.readLine()) !=null){
                String[] dado = inserirLinha.split(";");
                String nome = dado[0];
                String login = dado[1];
                String senha = dado[2];
                int saldo = Integer.parseInt(dado[3]);

                Jogador j = new Jogador(nome,login,senha);
                j.setSaldo(saldo);

                jogadores.add(j);
            }
            leitor.close();

        } catch (Exception e) {
            System.out.println("Erro ao Carregar Jogadores: " + e.getMessage());
        }
        return jogadores;
    }

    public String getCaminho(){return caminho;}
    public void setCaminho(String caminho) {this.caminho = caminho;}

    @Override
    public String toString() {
        return "ArquivoJogador{" +
                "caminho='" + caminho + '\'' +
                '}';
    }
}
