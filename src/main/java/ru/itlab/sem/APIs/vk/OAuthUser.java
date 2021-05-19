package ru.itlab.sem.APIs.vk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.net.URI;

@Data
@AllArgsConstructor
@ToString
public class OAuthUser {
    private String first_name;
    private int id;
    private String last_name;
    private boolean can_access_closed;
    private boolean is_closed;
    private String screen_name;
    private URI photo_big;
    private Place city;
    private Place country;
//    private String email;

    public String getLocation() {
        return city.getTitle() + ", " + country.getTitle();
    }

    @Data
    private class Place {
        private int id;
        private String title;
    }
}
