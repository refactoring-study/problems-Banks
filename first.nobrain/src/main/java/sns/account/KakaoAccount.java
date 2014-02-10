package sns.account;

public class KakaoAccount implements ISnsAccount {

    private String apiKey;
    private String snsId;

    public String getSNSId() {
        return snsId;
    }

    public String getAPIKey() {
        return apiKey;
    }

    public void setAPIKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setSNSId(String snsId) {
        this.snsId = snsId;
    }

}
