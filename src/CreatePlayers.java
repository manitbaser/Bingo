import java.util.*;
class Slip{
    int num;
    boolean checked = false;
    Slip(int num){
        this.num = num;
    }
}
public class CreatePlayers {
    int n;
    Player[] players;
    private Play play;
    public static ArrayList<Integer> generateValues(){
        final ArrayList<Integer> list = new ArrayList<>();
        final Random val = new Random();
        for(int i = 0;i<10;i++){
            list.add(val.nextInt(51));
        }
        return list;
    }
    CreatePlayers(final int n,final Play play){
        this.n = n;
        players = new Player[n];
        this.play = play;
    }

    Player[] Generate(){
        Player[] players = new Player[n];
        for(int i = 0;i<n;i++){
            final ArrayList<Integer> slip = generateValues();
            players[i] = new Player(play,i+1,slip);
            System.out.println( "Player " + ( i + 1) + " has slip: " + slip);
        }
        return players;
    }
}