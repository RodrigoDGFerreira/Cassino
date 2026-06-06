import Jogo.CacaNiquel;
import Jogo.Jogo;
import User.Jogador;

void main() {
    Jogador j =  new Jogador();
    Jogo cacaNiquel = new CacaNiquel();
    j.setSaldo(200);
    cacaNiquel.jogar(300,j);
    System.out.println(j.getSaldo());

}
