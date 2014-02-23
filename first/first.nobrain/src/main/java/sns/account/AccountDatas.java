package sns.account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sns.account.domain.SnsAccount;

public class AccountDatas {

    private static AccountDatas instace;

    private List<SnsAccount> accounts;

    private AccountDatas() {
        accounts = Collections.synchronizedList(new ArrayList<SnsAccount>());
    }

    public static AccountDatas getInstance() {
        if (instace == null) {
            instace = new AccountDatas();
        }

        return instace;
    }

    public void addAccount(SnsAccount snsAccount) {
        accounts.add(snsAccount);
    }

    public void removeAccount(SnsAccount snsAccount) {
        accounts.remove(snsAccount);
    }

    public SnsAccount get(int index) {
        return accounts.get(index);
    }

    public List<SnsAccount> getAccounts() {
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
