package refactoring.first.account;

import refactoring.first.account.Account;
import refactoring.first.account.AccountState;

public class FaceBookAccount implements Account {

    private final String id;
    private final String pw;
    private final boolean autoLogin;

    public FaceBookAccount(String id, String pw, boolean autoLogin) {
        this.id = id;
        this.pw = pw;
        this.autoLogin = autoLogin;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public boolean isAutoLogin() {
        return autoLogin;
    }

    @Override
    public AccountState getStatus() {
        return AccountState.NORMAL;
    }
}
