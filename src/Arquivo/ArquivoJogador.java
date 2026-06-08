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
            

        }
    }
}
