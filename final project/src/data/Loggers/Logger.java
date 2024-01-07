package data.Loggers;

import data.Application;
import data.Userdata.UserData;

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

        System.err.println("The user whose name ("+ UserData.getName() +") try to make "+ action );

    }
}
