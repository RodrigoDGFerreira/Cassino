import Jogo.CacaNiquel;
import Jogo.Jogo;
import User.Jogador;

void main() {
    Jogador j = new Jogador();
    j.setSaldo(100);
    Jogo jogo = new CacaNiquel();
    jogo.jogar(1000,j);

}
