package sns.feed.register;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sns.account.domain.SnsAccount;
import sns.account.domain.SnsAccount.AccountType;
import sns.feed.register.IFeedRegister.Result;
import sns.feed.register.domain.FeedContent;

public class SnsFeedRegister {

    public interface OnAccountExcludeCheck {

        public void onExcludeAccount(List<AccountType> accounts);

    }

    public interface OnFeedResult {

        public void onFeedFailResult(List<AccountType> failedAccount);
    }


    private OnAccountExcludeCheck accountCheck;
    private OnFeedResult feedResult;

    /**
     * 피드 등록하기
     *
     * @param accounts 등록할 계정
     * @param message 메세지 내용
     * @return 등록 결과, 순서는 Account 와 동일
     */
    public Map<SnsAccount, IFeedRegister.Result> registerFeed(List<SnsAccount> accounts, String message) {

        // 계정 확인
        checkAccount(accounts);

        // 피드 발송
        FeedContent feedContent = FeedContent.createMessage(message);
        Map<SnsAccount, IFeedRegister> feedRegisters = getFeedRegisters(accounts);
        Map<SnsAccount, IFeedRegister.Result> results = sendFeed(feedRegisters, feedContent);

        // 실패 처리
        retryFeedResult(results);

        return results;
    }

    private void retryFeedResult(Map<SnsAccount, IFeedRegister.Result> results) {
        List<AccountType> retryList = retryList(results);

        if (feedResult != null && retryList.size() > 0) {
            feedResult.onFeedFailResult(retryList);
        }
    }

    private void checkAccount(List<SnsAccount> accounts) {
        List<AccountType> checkAccountList = checkAccountList(accounts);

        if (accountCheck != null && checkAccountList.size() > 0) {
            accountCheck.onExcludeAccount(checkAccountList);
        }
    }

    private Map<SnsAccount, Result> sendFeed(Map<SnsAccount, IFeedRegister> registers, FeedContent feedContent) {

        Map<SnsAccount, IFeedRegister.Result> results = new HashMap<SnsAccount, Result>();

        Result registerResult;

        for (SnsAccount account : registers.keySet()) {
            registerResult = registers.get(account).register(account, feedContent);
            results.put(account, registerResult);
        }

        return results;
    }

    private Map<SnsAccount, IFeedRegister> getFeedRegisters(List<SnsAccount> accounts) {
        Map<SnsAccount, IFeedRegister> feedRegister = new HashMap<SnsAccount, IFeedRegister>();

        for (int idx = 0, size = accounts.size(); idx < size; ++idx) {
            SnsAccount account = accounts.get(idx);
            feedRegister.put(account, FeedRegisterFactory.createFeedRegister(account));
        }
        return feedRegister;
    }

    /**
     * 계정별 등록 결과에 따라 재시도할 정보 추출하기
     *
     * @param accounts 계정 목록
     * @param resultList 계정별 등록 결과
     * @return 재시도할 목록 (등록되지 않은 SNS 포함)
     *
     */
    private List<AccountType> retryList(Map<SnsAccount, Result> registerResults) {

        List<AccountType> notInvolvedTypes = new ArrayList<AccountType>();

        for (SnsAccount account : registerResults.keySet()) {
            if (registerResults.get(account) != Result.SUCCESS) {
                notInvolvedTypes.add(account.getAccountType());
            }
        }

        return notInvolvedTypes;
    }

    /**
     * 지정된 SNS 타입이 포함되어 있는지 확인함
     *
     * @param accounts sns 계정 리스트
     * @return 미지정된 sns 계정 타입들
     */
    private List<AccountType> checkAccountList(List<SnsAccount> accounts) {

        List<AccountType> notInvolvedTypes = AccountType.getAccountTypes();

        AccountType tempType;
        for (int idx = 0, size = accounts.size(); idx < size; ++idx) {
            tempType = accounts.get(idx).getAccountType();
            notInvolvedTypes.remove(tempType);
        }

        return notInvolvedTypes;
    }

    public void setAccountCheck(OnAccountExcludeCheck accountCheck) {
        this.accountCheck = accountCheck;
    }

    public void setFeedResult(OnFeedResult feedResult) {
        this.feedResult = feedResult;
    }

}
