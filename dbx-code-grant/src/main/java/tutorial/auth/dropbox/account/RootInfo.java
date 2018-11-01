package tutorial.auth.dropbox.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RootInfo {

    @SerializedName(".tag")
    @Expose
    private String tag;
    @SerializedName("root_namespace_id")
    @Expose
    private String rootNamespaceId;
    @SerializedName("home_namespace_id")
    @Expose
    private String homeNamespaceId;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRootNamespaceId() {
        return rootNamespaceId;
    }

    public void setRootNamespaceId(String rootNamespaceId) {
        this.rootNamespaceId = rootNamespaceId;
    }

    public String getHomeNamespaceId() {
        return homeNamespaceId;
    }

    public void setHomeNamespaceId(String homeNamespaceId) {
        this.homeNamespaceId = homeNamespaceId;
    }

}
