import Jogo.*;
import User.Jogador;


void main() {
    Jogador j =  new Jogador();
    Jogo cacaNiquel = new BlackJack();
    j.setSaldo(200);
    cacaNiquel.jogar(100,j);


}
