package refactoring.first.account;

import refactoring.first.account.Account;
import refactoring.first.account.AccountState;

public class GooglePlusAccount implements Account {
    private final String id;
    private final String pw;
    private final boolean autoLogin;

    public GooglePlusAccount(String id, String pw, boolean autoLogin) {
        this.id = id;
        this.pw = pw;
        this.autoLogin = autoLogin;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getPw() {
        return pw;
    }

    @Override
    public boolean isAutoLogin() {
        return autoLogin;
    }

    @Override
    public AccountState getStatus() {
        return null;
    }
}
