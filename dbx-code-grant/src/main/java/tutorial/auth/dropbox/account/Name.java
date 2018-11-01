package tutorial.auth.dropbox.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("given_name")
    @Expose
    private String givenName;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("familiar_name")
    @Expose
    private String familiarName;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("abbreviated_name")
    @Expose
    private String abbreviatedName;

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFamiliarName() {
        return familiarName;
    }

    public void setFamiliarName(String familiarName) {
        this.familiarName = familiarName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAbbreviatedName() {
        return abbreviatedName;
    }

    public void setAbbreviatedName(String abbreviatedName) {
        this.abbreviatedName = abbreviatedName;
    }

}
