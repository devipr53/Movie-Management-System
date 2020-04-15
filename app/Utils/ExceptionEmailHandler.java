package Utils;

public class ExceptionEmailHandler {
    public static void send(Throwable e){
        System.out.println("Sending Email containing exception : " + e);
    }
}
