package User;

public class SistemaLogin {
    private int tentativas;

    public SistemaLogin() {
        this.tentativas = 0;
    }
    public int getTentativas() {
        return tentativas;
    }

    public void setTentativas(int tentativas) {
        this.tentativas = tentativas;
    }

    public boolean autenticar(Jogador jogador, String login, String senha) {
        if (jogador.getLogin().equals(login) && jogador.getSenha().equals(senha)) {
            tentativas = 0;
            System.out.println("Login realizado com sucesso");
            return true;
        }
        tentativas++;
        System.out.println("Login ou senha incorretos");
        if (tentativas >= 3) {
            System.out.println("Você errou 3 vezes. Será necessário redefinir a senha.");
        }
        return false;
    }


    public boolean precisaTrocarSenha() {
        return tentativas >= 3;
    }
    public void resetarTentativas(){
        tentativas = 0;
    }

    public boolean trocarSenha(Jogador jogador, String novaSenha) {
        if (jogador.senhaUtilizada(novaSenha)) {
            System.out.println("A nova senha não pode ser igual às últimas 3 senhas.");
            return false;
        }
        jogador.alterarSenha(novaSenha);
        tentativas = 0;
        System.out.println("Senha alterada com sucesso.");
        return true;
    }


    @Override
    public String toString() {
        return "SistemaLogin{" +
                "tentativas=" + tentativas +
                '}';
    }
}