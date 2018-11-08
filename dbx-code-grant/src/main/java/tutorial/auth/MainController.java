package tutorial.auth;

import com.google.gson.Gson;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lambdaworks.redis.*;
import tutorial.auth.dropbox.account.PersonalAccount;

@Controller
public class MainController {

    String authEndpoint = "https://www.dropbox.com/oauth2/authorize";
    String tokenEndpoint = "https://api.dropboxapi.com/oauth2/token";
    String accountInfoEndpoint = "https://api.dropboxapi.com/2/users/get_account";

    String clientId;
    String clientSecret;
    String redirectURI = "http://localhost:8090/cb";

    private RedisClient redisClient;

    @Inject
    public MainController(RedisClient rClient) {
        this.redisClient = rClient;
        RedisConnection<String, String> connection = redisClient.connect();
        clientId = connection.get("clientId");
        clientSecret = connection.get("clientSecret");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainPage(ModelMap model) {

        if (isSettingsMissing()) {
            return missingSettingsError(model);
        }

        String authRequest = authEndpoint.concat("?response_type=code")
                .concat("&client_id=").concat(clientId)
                .concat("&redirect_uri=").concat(redirectURI)
                .concat("&state=1234");

		model.addAttribute("authEndpoint", authRequest);
		return "main";
	}

    @RequestMapping(value = "/cb", method = RequestMethod.GET)
    public String callbackHandler(@RequestParam(value = "code", required = true) String code,
            ModelMap model)
            throws IOException {

        if (isSettingsMissing()) {
            return missingSettingsError(model);
        }

        model.addAttribute("code", code);

        // get the access token
        String tokenRequest =
                tokenEndpoint.concat("?grant_type=authorization_code")
                .concat("&client_id=").concat(clientId)
                .concat("&client_secret=").concat(clientSecret)
                .concat("&redirect_uri=")
                        .concat(URLEncoder.encode(redirectURI, "UTF-8"))
                .concat("&code=").concat(code);

        HttpPost getAccessTokenRequest = new HttpPost(tokenRequest);
        getAccessTokenRequest.addHeader("Accept", "application/json");
        getAccessTokenRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
        getAccessTokenRequest.setHeader("charset", "utf-8");

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(getAccessTokenRequest);

        if (response.getStatusLine().getStatusCode() != 200) {
            model.addAttribute("fullResponse",
                    String.format(" TokenRequest: %s", response.toString()));
            return handleError(response, model);
        }

        final Gson gson = new Gson();
        Reader streamReader = new InputStreamReader(response.getEntity().getContent());
        final AccessTokenResponse atResponse = gson.fromJson(streamReader, AccessTokenResponse.class);

        model.addAttribute("accessToken", atResponse.getAccessToken());

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("account_id", atResponse.getAccountId());
        String accountJsonString = gson.toJson(params);

        // get account info with the access token
        HttpPost getAccountInfoRequest = new HttpPost(accountInfoEndpoint);
        getAccountInfoRequest.addHeader("Accept", "application/json");
        getAccountInfoRequest.addHeader("Content-Type", "application/json   ");
        getAccountInfoRequest.addHeader("Authorization", "Bearer " + atResponse.getAccessToken());
        getAccountInfoRequest.setEntity(new StringEntity(accountJsonString, Consts.UTF_8));
        response = httpClient.execute(getAccountInfoRequest);

        if (response.getStatusLine().getStatusCode() != 200) {
            model.addAttribute("fullResponse",
                    String.format(" AccountInfoRequest %s", response.toString()));
            return handleError(response, model);
        }
        streamReader =
                new InputStreamReader(response.getEntity().getContent());
        PersonalAccount account =
                gson.fromJson(streamReader, PersonalAccount.class);

        model.addAttribute("userName", account.getName().getDisplayName());

        return "main";
    }

    private String handleError(HttpResponse response, ModelMap model) {

        model.addAttribute("errorMessage",
                "Error. HTTP status code: " + response.getStatusLine().getStatusCode()
                + "\nReason: " + response.getStatusLine().getReasonPhrase() + model.get("fullResponse"));

        return "error";
    }

    private String missingSettingsError(ModelMap model) {
        model.addAttribute("errorMessage",
                "Error. Please set values for 'clientId' and 'clientSecret' in MainController.java");

        return "error";
    }

    private boolean isSettingsMissing() {
        return StringUtils.isEmpty(clientId) || StringUtils.isEmpty(clientSecret);
    }

}
