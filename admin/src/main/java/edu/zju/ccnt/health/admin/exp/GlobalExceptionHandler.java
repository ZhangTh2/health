package edu.zju.ccnt.health.admin.exp;

import edu.zju.ccnt.health.admin.response.ResponseCode;
import edu.zju.ccnt.health.admin.response.ServerResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ServerResponse handleException(Exception e) {
        if(e.getMessage().equals("token expired"))
            return ServerResponse.createByErrorCodeMessage(ResponseCode.EXPIRED_TOKEN.getCode(),ResponseCode.EXPIRED_TOKEN.getDesc());
        else if(e.getMessage().equals("other token exception"))
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LONGIN.getCode(),"error:"+e.getMessage());
        else return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),"error:"+e.getMessage());
    }
}
