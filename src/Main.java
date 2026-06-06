import Jogo.*;
import User.Jogador;


void main() {
    Jogador j =  new Jogador();
    Jogo cacaNiquel = new CacaNiquel();
    j.setSaldo(200);
    j.setNome("Rodrigo");
    cacaNiquel.jogar(100,j);
    System.out.println(j.getSaldo());


}
