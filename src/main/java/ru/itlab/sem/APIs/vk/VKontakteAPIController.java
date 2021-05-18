package ru.itlab.sem.APIs.vk;

//@Controller
public class VKontakteAPIController {
    private static final String authURL = "https://oauth.vk.com/authorize";
    private static final String client_id = "7856965";
    private static final String redirect_uri = "http://localhost:8080/sign/oauth";
    private static final String display = "page";
    private static final String scope = "email";
    private static final String response_type = "code";

    private static final String accessTokenURL = "https://oauth.vk.com/access_token";
    private static final String client_secret = "";
    private static final String at_redirect_uri = "";
    private static final String code = "";

    private static final String API_URL = "https://api.vk.com/method/";
    private static final String method = "account.getProfileInfo";
    private static final String parameters = "";
    private static final String access_token = "";
    private static final String v = "";

    public static String getURL4Code() {
        return authURL + "?client_id=" + client_id + "&redirect_uri=" + redirect_uri
                + "&display=" + display + "&scope=" + scope + "&response_type=" + response_type;
    }

    public static String getURL4AccessToken() {
        return "";
    }

    public static String getURL4API() {
        return API_URL + method + "?" + parameters + "&access_token=" + access_token + "&v=" + v;
    }
}
