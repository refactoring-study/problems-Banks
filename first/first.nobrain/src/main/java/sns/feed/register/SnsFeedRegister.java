package sns.feed.register;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sns.account.AccountTypeUtil;
import sns.account.AccountTypeUtil.AccountType;
import sns.account.domain.ISnsAccount;
import sns.feed.register.IFeedRegister.Result;
import sns.feed.register.domain.FeedContent;

public class SnsFeedRegister {

    /**
     * 지정된 SNS 타입이 포함되어 있는지 확인함
     * @param accounts sns 계정 리스트
     * @return 미지정된 sns 계정 타입들
     */
    public List<AccountType> checkAccountList(List<ISnsAccount> accounts) {

        List<AccountType> notInvolvedTypes = new ArrayList<AccountTypeUtil.AccountType>(Arrays.asList(AccountType.values()));
        notInvolvedTypes.remove(AccountType.ACCOUNT_TYPE_NO);

        AccountType tempType;
        for(int idx = 0, size = accounts.size(); idx < size; ++idx) {
            tempType = AccountTypeUtil.getAccountType(accounts.get(idx));
            notInvolvedTypes.remove(tempType);
        }

        return notInvolvedTypes;
    }

   /**
    * 피드 등록하기
    * @param accounts 등록할 계정
    * @param message 메세지 내용
    * @return 등록 결과, 순서는 Account 와 동일
    */
    public List<Result> registerFeed(List<ISnsAccount> accounts, String message) {

        FeedContent feedContent = FeedContent.createMessage(message);

        List<IFeedRegister> feedRegisters = getFeedRegisters(accounts);

        List<IFeedRegister.Result> feedResults = sendFeed(feedRegisters, feedContent);

        return feedResults;
    }

    private List<Result> sendFeed(List<IFeedRegister> feedRegister, FeedContent feedContent) {

        List<IFeedRegister.Result> results = new ArrayList<IFeedRegister.Result>();

        Result registerResult;
        for(int idx = 0, size = feedRegister.size(); idx < size; ++idx) {
            registerResult = feedRegister.get(idx).register(feedContent);
            results.add(registerResult);
        }

        return results;
    }

    private List<IFeedRegister> getFeedRegisters(List<ISnsAccount> accounts) {
        List<IFeedRegister> feedRegister = new ArrayList<IFeedRegister>();

        for (int idx = 0, size = accounts.size(); idx < size; ++idx) {
            feedRegister.add(FeedRegisterFactory.createFeedRegister(accounts.get(idx)));
        }
        return feedRegister;
    }

}
