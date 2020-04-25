package com.imooc.exception;

/**
 * @date 2020//4/20
 */
public class UserNotExistException extends RuntimeException {
    private static final long serialVersionUID = -6217897190745766939L;
    private String id;
    public UserNotExistException(String id) {
        super("user not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
