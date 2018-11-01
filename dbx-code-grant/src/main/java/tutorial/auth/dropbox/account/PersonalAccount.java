package tutorial.auth.dropbox.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonalAccount {

    @SerializedName("account_id")
    @Expose
    private String accountId;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("email_verified")
    @Expose
    private Boolean emailVerified;
    @SerializedName("disabled")
    @Expose
    private Boolean disabled;
    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("referral_link")
    @Expose
    private String referralLink;
    @SerializedName("is_paired")
    @Expose
    private Boolean isPaired;
    @SerializedName("account_type")
    @Expose
    private AccountType accountType;
    @SerializedName("root_info")
    @Expose
    private RootInfo rootInfo;
    @SerializedName("profile_photo_url")
    @Expose
    private String profilePhotoUrl;
    @SerializedName("country")
    @Expose
    private String country;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getReferralLink() {
        return referralLink;
    }

    public void setReferralLink(String referralLink) {
        this.referralLink = referralLink;
    }

    public Boolean getIsPaired() {
        return isPaired;
    }

    public void setIsPaired(Boolean isPaired) {
        this.isPaired = isPaired;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public RootInfo getRootInfo() {
        return rootInfo;
    }

    public void setRootInfo(RootInfo rootInfo) {
        this.rootInfo = rootInfo;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
