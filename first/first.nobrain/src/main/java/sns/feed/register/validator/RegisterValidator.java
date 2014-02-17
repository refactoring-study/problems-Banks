package sns.feed.register.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sns.SnsTypeUtil;
import sns.SnsTypeUtil.SnsType;
import sns.account.domain.ISnsAccount;
import sns.feed.register.IFeedRegister.Result;

public class RegisterValidator {

    /**
     * ������ SNS Ÿ���� ���ԵǾ� �ִ��� Ȯ����
     *
     * @param accounts sns ���� ����Ʈ
     * @return �������� sns ���� Ÿ�Ե�
     */
    public List<SnsType> checkAccountList(List<ISnsAccount> accounts) {

        List<SnsType> notInvolvedTypes = new ArrayList<SnsTypeUtil.SnsType>(Arrays.asList(SnsType
                .values()));
        notInvolvedTypes.remove(SnsType.ACCOUNT_TYPE_NO);

        SnsType tempType;
        for (int idx = 0, size = accounts.size(); idx < size; ++idx) {
            tempType = SnsTypeUtil.getSnsType(accounts.get(idx));
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
    public List<SnsType> notifyRetry(List<ISnsAccount> accounts, List<Result> resultList) {

        // ���� �� �������ε�...����...

        List<SnsType> notInvolvedTypes = new ArrayList<SnsTypeUtil.SnsType>();

        for (int idx = 0, size = accounts.size(); idx < size; ++idx) {
            if (resultList.get(idx) != Result.SUCCESS) {
                notInvolvedTypes.add(SnsTypeUtil.getSnsType(accounts.get(idx)));
            }
        }

        for (int idx = 0, size = notInvolvedTypes.size(); idx < size; ++idx) {
            System.out.println(notInvolvedTypes.get(idx).name() + " : ��õ� �ؾ��մϴ�.");
        }

        return notInvolvedTypes;
    }
}
