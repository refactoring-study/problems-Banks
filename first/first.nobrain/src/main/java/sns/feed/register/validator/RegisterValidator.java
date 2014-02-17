package sns.feed.register.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sns.account.AccountTypeUtil;
import sns.account.AccountTypeUtil.AccountType;
import sns.account.domain.ISnsAccount;
import sns.feed.register.IFeedRegister.Result;

public class RegisterValidator {

    /**
     * ������ SNS Ÿ���� ���ԵǾ� �ִ��� Ȯ����
     *
     * @param accounts sns ���� ����Ʈ
     * @return �������� sns ���� Ÿ�Ե�
     */
    public List<AccountType> checkAccountList(List<ISnsAccount> accounts) {

        List<AccountType> notInvolvedTypes = new ArrayList<AccountTypeUtil.AccountType>(Arrays.asList(AccountType
                .values()));
        notInvolvedTypes.remove(AccountType.ACCOUNT_TYPE_NO);

        AccountType tempType;
        for (int idx = 0, size = accounts.size(); idx < size; ++idx) {
            tempType = AccountTypeUtil.getAccountType(accounts.get(idx));
            notInvolvedTypes.remove(tempType);
        }

        for (int idx = 0, size = notInvolvedTypes.size(); idx < size; ++idx) {
            System.out.println(notInvolvedTypes.get(idx).name() + " : ������ �����ϴ�.");
        }

        return notInvolvedTypes;
    }

    /**
     * ������ ��� ����� ���� ��õ��� ���� �����ϱ�
     *
     * @param accounts ���� ���
     * @param resultList ������ ��� ���
     * @return ��õ��� ��� (��ϵ��� ���� SNS ����)
     *
     */
    public List<AccountType> notifyRetry(List<ISnsAccount> accounts, List<Result> resultList) {

        // ���� �� �������ε�...����...

        List<AccountType> notInvolvedTypes = new ArrayList<AccountTypeUtil.AccountType>();

        for (int idx = 0, size = accounts.size(); idx < size; ++idx) {
            if (resultList.get(idx) != Result.SUCCESS) {
                notInvolvedTypes.add(AccountTypeUtil.getAccountType(accounts.get(idx)));
            }
        }

        for (int idx = 0, size = notInvolvedTypes.size(); idx < size; ++idx) {
            System.out.println(notInvolvedTypes.get(idx).name() + " : ��õ� �ؾ��մϴ�.");
        }

        return notInvolvedTypes;
    }
}