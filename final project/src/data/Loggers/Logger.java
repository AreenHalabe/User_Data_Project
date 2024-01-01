package data.Loggers;

public class Logger implements Loggers{
    private static Logger logger;

    private Logger(){}


    public static Logger CreatLogger(){
        if(logger == null){
            logger = new Logger();
        }
        return logger;
    }
    @Override
    public void NotifyAction(String name , String action) {
        System.err.println("The user whose name ("+ name +") is currently make "+ action );

    }
}
