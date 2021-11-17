package com.otaku.utils;

/**
 * @author Otaku
 * @date 2021/10/19-22:08
 */
public class loginfailexception extends RuntimeException{
    public loginfailexception(){
        super();
    }

    public loginfailexception(String message) {
        super(message);
    }

    public loginfailexception(String message, Throwable cause) {
        super(message, cause);
    }

    public loginfailexception(Throwable cause) {
        super(cause);
    }

    public loginfailexception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
