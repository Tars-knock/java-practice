package cn.tarsknock.utilbox.Exception;

public class MyException extends RuntimeException{
    private final String msg;

    public MyException(String msg) {
        super();
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + msg;
    }
}
