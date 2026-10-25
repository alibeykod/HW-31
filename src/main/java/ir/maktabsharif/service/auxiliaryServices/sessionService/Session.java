package ir.maktabsharif.service.auxiliaryServices.sessionService;

import jakarta.servlet.http.HttpSession;

public class Session {
    public Integer visitCount(HttpSession session){
        Integer visitCount = (Integer)session.getAttribute("visitCount");
        if (visitCount == null){
            visitCount = 1;
        }else {
            visitCount ++ ;
        }
        session.setAttribute("visitCount" , visitCount);
        return visitCount;
    }
}
