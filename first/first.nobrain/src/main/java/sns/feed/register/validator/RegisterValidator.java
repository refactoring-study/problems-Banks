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
     * 지정된 SNS 타입이 포함되어 있는지 확인함
     *
     * @param accounts sns 계정 리스트
     * @return 미지정된 sns 계정 타입들
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
            System.out.println(notInvolvedTypes.get(idx).name() + " : 계정이 없습니다.");
        }

        return notInvolvedTypes;
    }

    /**
     * 계정별 등록 결과에 따라 재시도할 정보 추출하기
     *
     * @param accounts 계정 목록
     * @param resultList 계정별 등록 결과
     * @return 재시도할 목록 (등록되지 않은 SNS 포함)
     *
     */
    public List<AccountType> notifyRetry(List<ISnsAccount> accounts, List<Result> resultList) {

        // 여기 좀 구린내인데...으흠...

        List<AccountType> notInvolvedTypes = new ArrayList<AccountTypeUtil.AccountType>();

        for (int idx = 0, size = accounts.size(); idx < size; ++idx) {
            if (resultList.get(idx) != Result.SUCCESS) {
                notInvolvedTypes.add(AccountTypeUtil.getAccountType(accounts.get(idx)));
            }
        }

        for (int idx = 0, size = notInvolvedTypes.size(); idx < size; ++idx) {
            System.out.println(notInvolvedTypes.get(idx).name() + " : 재시도 해야합니다.");
        }

        return notInvolvedTypes;
    }
}
