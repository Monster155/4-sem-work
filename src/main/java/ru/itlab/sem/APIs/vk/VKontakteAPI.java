package ru.itlab.sem.APIs.vk;

public class VKontakteAPI {
    private static final String authURL = "https://oauth.vk.com/authorize";
    private static final String client_id = "7858017";
    private static final String redirect_uri = "http://localhost:8080/sign/oauth";
    private static final String display = "page";
    private static final String scope = "email";
    private static final String response_type = "code";
//    private static final String response_type = "token";

    private static final String accessTokenURL = "https://oauth.vk.com/access_token";
    private static final String client_secret = "t0lFRLyvVyfGHlCIzD67";
    private static final String at_redirect_uri = "http://localhost:8080/sign/oauth";

    private static final String API_URL = "https://api.vk.com/method/";
    //    private static final String method = "account.getInfo";
    private static final String method = "users.get";
    private static final String parameters = "fields=uid,first_name,last_name,screen_name,photo_big,city,country";
    private static final String v = "5.130";

    public static String getURL4Code() {
        return authURL + "?client_id=" + client_id + "&redirect_uri=" + redirect_uri
                + "&display=" + display + "&scope=" + scope + "&response_type=" + response_type;
    }

    public static String getURL4AccessToken(String code) {
        return accessTokenURL + "?client_id=" + client_id + "&client_secret=" + client_secret
                + "&redirect_uri=" + at_redirect_uri + "&code=" + code;
    }

    public static String getURL4getProfileAPI(String access_token) {
        return API_URL + method + "?" + parameters + "&access_token=" + access_token + "&v=" + v;
    }
}
