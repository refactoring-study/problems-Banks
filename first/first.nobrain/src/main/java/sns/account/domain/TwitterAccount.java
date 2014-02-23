package sns.account.domain;


public class TwitterAccount implements SnsAccount {

    private String apiKey;
    private String snsId;

    public String getSNSId() {
        // TODO Auto-generated method stub
        return snsId;
    }

    public String getAPIKey() {
        // TODO Auto-generated method stub
        return apiKey;
    }

    public void setAPIKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setSNSId(String snsId) {
        this.snsId = snsId;
    }

    public AccountType getAccountType() {
        return AccountType.ACCOUNT_TYPE_TWITTER;
    }

}
