public class Mod extends Thread{
    Play play;
    private static Mod mod;
    private Mod(final Play play){
        System.out.println("\nModerator Started\n");
        this.play = play;
    }
    public static Mod GetInstance(final Play play){
        if(mod==null) mod = new Mod(play);
        return mod;
    }
    protected Object clone() throws CloneNotSupportedException{
        throw new CloneNotSupportedException("Not supported, try again");
    }
    @Override
    public void run(){
        while(true){
            if(!play.run(false,null)) break;
            try{ Thread.sleep(1000);}
            catch(final Exception e){System.out.println(e);}
        }
    }
}