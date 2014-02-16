package sns.account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sns.account.domain.ISnsAccount;

public class AccountDatas {

    private static AccountDatas instace;

    private List<ISnsAccount> accounts;

    private AccountDatas() {
        accounts = Collections.synchronizedList(new ArrayList<ISnsAccount>());
    }

    public static AccountDatas getInstance() {
        if (instace == null) {
            instace = new AccountDatas();
        }

        return instace;
    }

    public void addAccount(ISnsAccount snsAccount) {
        accounts.add(snsAccount);
    }

    public void removeAccount(ISnsAccount snsAccount) {
        accounts.remove(snsAccount);
    }

    public ISnsAccount get(int index) {
        return accounts.get(index);
    }

    public List<ISnsAccount> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void release() {
        synchronized (accounts) {
            accounts.clear();
        }

        synchronized (instace) {
            instace = null;
        }
    }

}
