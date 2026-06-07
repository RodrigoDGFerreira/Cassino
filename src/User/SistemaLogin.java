package User;

public class SistemaLogin{
    private int tentativas;

    public SistemaLogin(){
        this.tentativas=0;
    }
    public boolean autenticar(Jogador jogador,String user,String senha){
        if(jogador.getNome().equals(user) && jogador.getSenha().equals(senha)){
            tentativas =0;
            System.out.println("Login realizado com sucesso");
            return true;
        }
        tentativas++;
        System.out.println("Login e Senha Incorreto");

        if (tentativas>=3){
            System.out.println("Você errou o Login 3 vezes. Será necessario redefinir a senha");
        }
        return false;
    }

    public int getTentativas() {
        return tentativas;
    }

    public void setTentativas(int tentativas) {
        this.tentativas = tentativas;
    }

    @Override
    public String toString() {
        return "SistemaLogin{" +
                "tentativas=" + tentativas +
                '}';
    }
}
