package ir.maktabsharif.service.auxiliaryServices.logService;

import lombok.Getter;
import lombok.Setter;

public class LogService {

    @Setter
    @Getter

    private String prefix;

    public void log(String message) {
        System.out.println("[" + prefix + "] " + message);
    }
}
