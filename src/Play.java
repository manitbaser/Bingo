import java.util.*;
import java.io.*;

public class Play{
    Stack<Integer> number;
    boolean finish = false;
    int n,count;
    
    Play(int n){
        number = new Stack<>();
        this.n = this.count = n;
    }

    public boolean run( boolean isPlayer, Player player){
        synchronized(number){
//        	To check the thread
//        	System.out.println(Thread.currentThread());
            if(finish) return false;
            if(isPlayer){
                if(player.read>=number.size()||this.count==n) return true;
                player.read++;
                this.count++;
                for(Slip slip : player.values){
                    if(slip.num==this.number.peek()){
                        if(slip.checked) break;
                        slip.checked = true;
                        player.score++;
                        if(player.score==3){
                            System.out.println("\nThe Winner is "+player.id);
                            System.out.println("\n");
                            this.finish = true;
                            return false;
                        }
                        break;
                    }
                }
                return true;
            }
            else{
                if(this.count<this.n) return true;
                Random val = new Random();
                int value = val.nextInt(51);
                this.number.add(value);
                if(this.number.size()>10){
                    this.finish = true;
                    System.out.println("\nNo player won\n");
                    return false;
                }
                System.out.println("Moderator Generated: "+ value);
                this.count = 0;
                return true;
            }
        }
    }
    public static void main(String[] args) throws IOException{


    	try{
//        	System.out.print("\nEnter the number of Players: ");
//    		2 ways of declaring n:-
//        	Option 1: Asking the user for the value of n
//        	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//    		int n = Integer.parseInt(in.readLine());
//  	  	Option 2: Fixing the value of n beforehand
	        int n = 10;
	        Play play = new Play(n);
//			Singleton Design Pattern
	        Mod moderator = Mod.GetInstance(play);
	        Thread mod = new Thread (moderator);
	        mod.setName("Mod");
//			Factory Design Pattern
	        Thread[] players = new Thread[n];
	        CreatePlayers generate = new CreatePlayers(n,play);
	        Player[] generated = generate.Generate();
	        for(int i = 0;i<n;i++){
	            players[i] = generated[i];
	            players[i].setName("Player"+(i+1));
	        }
	        System.out.println("\nStarting the game:");
	        for(int i = 0;i<n;i++) players[i].start();
	        mod.start();
    	}
        catch (NumberFormatException e) {
            System.out.println("Kindly enter an integer value for n");
         }
    }
}