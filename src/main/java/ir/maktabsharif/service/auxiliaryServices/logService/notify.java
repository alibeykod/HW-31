package ir.maktabsharif.service.auxiliaryServices.logService;

public class notify {
    private String prefix;

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void log(String message){
        System.out.println("[ " +  prefix + "] " + message);
    }
}
