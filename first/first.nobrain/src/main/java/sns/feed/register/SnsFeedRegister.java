package sns.feed.register;

import java.util.ArrayList;
import java.util.List;

import sns.account.domain.ISnsAccount;
import sns.feed.register.IFeedRegister.Result;
import sns.feed.register.domain.FeedContent;

public class SnsFeedRegister {

   /**
    * �ǵ� ����ϱ�
    * @param accounts ����� ����
    * @param message �޼��� ����
    * @return ��� ���, ������ Account �� ����
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
