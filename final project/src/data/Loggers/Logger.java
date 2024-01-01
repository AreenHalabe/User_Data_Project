package data.Loggers;

import data.Application;

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
    public void NotifyAction(String action) {
        String Name = Application.getLoginUserName();

        System.err.println("The user whose name ("+ Name +") try to make "+ action );

    }
}
