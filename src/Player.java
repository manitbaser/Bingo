import java.util.ArrayList;


public class Player extends Thread{
    Play play;
    ArrayList<Slip> values = new ArrayList<>();
    int score,read,id;
    Player(Play play, int id, ArrayList<Integer> list){
        this.play = play;
        this.id = id;
        for(int i = 0;i<list.size();i++){
            this.values.add(new Slip(list.get(i)));
        }
        this.score = this.read = 0;
    }
    @Override
    public void run(){
        while(true){
            if(!play.run(true,this)){
                System.out.println("Player " + id + "'s Score: " + this.score);
                break;
            }
            try{ Thread.sleep(100);}
            catch(Exception e){ System.out.println(e);}
        }
    }
}