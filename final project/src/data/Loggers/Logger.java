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
    public void NotifyAction(String action , String name) {
        System.out.println("The user whose name ("+ name +") is currently "+ action + " his data");
    }
}
