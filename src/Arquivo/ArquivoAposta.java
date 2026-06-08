package Arquivo;

import Jogo.BlackJack;
import Jogo.CacaNiquel;
import Jogo.Jogo;
import Jogo.Roleta;
import User.Aposta;
import User.Jogador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ArquivoAposta {
    private String caminho = "apostas.txt";

    public void salvarApostas(ArrayList<Jogador> jogadores) {
        try {
            FileWriter arquivo = new FileWriter(caminho);
            PrintWriter gravar = new PrintWriter(arquivo);

            for (Jogador jogador : jogadores) {
                for (Aposta aposta : jogador.getHistorico()) {
                    gravar.println(
                            jogador.getLogin() + ";" +
                                    aposta.getTipoJogo() + ";" +
                                    aposta.getValor() + ";" +
                                    aposta.getResultado() + ";" +
                                    aposta.getData()
                    );
                }
            }

            gravar.close();

        } catch (Exception e) {
            System.out.println("Erro ao salvar apostas: " + e.getMessage());
        }
    }

    public void carregarApostas(ArrayList<Jogador> jogadores) {
        try {
            File arquivo = new File(caminho);

            if (!arquivo.exists()) {
                return;
            }

            FileReader leitorArquivo = new FileReader(arquivo);
            BufferedReader leitor = new BufferedReader(leitorArquivo);

            String linha;

            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");

                if (dados.length < 5) {
                    continue;
                }

                String loginJogador = dados[0];
                String nomeJogo = dados[1];
                int valor = Integer.parseInt(dados[2]);
                String resultado = dados[3];
                LocalDateTime data = LocalDateTime.parse(dados[4]);

                Jogador jogador = buscarJogadorPorLogin(jogadores, loginJogador);

                if (jogador != null) {
                    Jogo jogo = criarJogoPeloNome(nomeJogo);

                    Aposta aposta = new Aposta(valor, resultado, jogo, jogador);
                    aposta.setData(data);

                    jogador.adicionarAposta(aposta);
                }
            }

            leitor.close();

        } catch (Exception e) {
            System.out.println("Erro ao carregar apostas: " + e.getMessage());
        }
    }

    private Jogador buscarJogadorPorLogin(ArrayList<Jogador> jogadores, String login) {
        for (Jogador jogador : jogadores) {
            if (jogador.getLogin().equals(login)) {
                return jogador;
            }
        }

        return null;
    }

    private Jogo criarJogoPeloNome(String nomeJogo) {
        if (nomeJogo.equals("BlackJack")) {
            return new BlackJack();
        } else if (nomeJogo.equals("Caça-Níquel")) {
            return new CacaNiquel();
        } else if (nomeJogo.equals("Roleta")) {
            return new Roleta();
        }

        return null;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    @Override
    public String toString() {
        return "ArquivoAposta{" +
                "caminho='" + caminho + '\'' +
                '}';
    }
}