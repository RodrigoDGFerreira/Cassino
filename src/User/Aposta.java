package User;

import Jogo.Jogo;

import java.time.LocalDateTime;

public class Aposta {
    private int valor;
    private String resultado;
    private LocalDateTime data;
    private Jogo tipoJogo;
    private Jogador jogador;

    public Aposta() {
        this.data = LocalDateTime.now();
    }

    public Aposta(int valor, String resultado, Jogo tipoJogo, Jogador jogador) {
        this.valor = valor;
        this.resultado = resultado;
        this.tipoJogo = tipoJogo;
        this.jogador = jogador;
        this.data = LocalDateTime.now();
    }
    public int getValor() {return valor;}
    public void setValor(int valor) {this.valor = valor;}

    public String getResultado() {return resultado;}
    public void setResultado(String resultado) {this.resultado = resultado;}

    public LocalDateTime getData() {return data;}
    public void setData(LocalDateTime data) {this.data = data;}

    public Jogo getTipoJogo() {return tipoJogo;}
    public void setTipoJogo(Jogo tipoJogo) {this.tipoJogo = tipoJogo;}

    public Jogador getJogador() {return jogador;}
    public void setJogador(Jogador jogador) {this.jogador = jogador;}



    public String toString() {
        return "Aposta{" +
                "valor=" + valor +
                ", resultado='" + resultado + '\'' +
                ", data=" + data +
                ", tipoJogo=" + tipoJogo +
                ", jogador=" + jogador.getNome() +
                '}';
    }
}
