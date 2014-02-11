package sns.exception;

public class NotAuthorException extends Exception {

    private static final long serialVersionUID = -3625632930655718702L;

    public NotAuthorException(String arg0) {
        super("인증 권한이 없거나 해제된 상태입니다.");
    }


}
