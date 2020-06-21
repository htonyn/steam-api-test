package com.example.demo.component;

import com.example.demo.domain.AchievementEvaluationResponse;
import com.example.demo.domain.SteamAchievements;
import static com.example.demo.domain.SteamAchievements.PlayerStats;
import static com.example.demo.domain.SteamAchievements.PlayerStats.Achievement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SteamApiHttpComponent {
    private RestTemplate restTemplate;
    private String apiKey;
    private String hostUrl;
    private String GET_ACHIEVEMENTS_PATH = "/ISteamUserStats/GetPlayerAchievements/v0001";

    public SteamApiHttpComponent(@Value("${steam.host-url}") final String hostUrl,
                                 @Value("${steam.api-key}") final String apiKey) {
        this.restTemplate = new RestTemplate();
        this.hostUrl = hostUrl;
        this.apiKey = apiKey;
    }

    public PlayerStats getPlayerAchievements(String appId, String steamId) {
        String url = hostUrl + GET_ACHIEVEMENTS_PATH + "?appid=" + appId + "&key=" + apiKey + "&steamid=" + steamId;
        SteamAchievements achievements = restTemplate.getForEntity(url, SteamAchievements.class).getBody();
        return achievements.getPlayerstats();
    }
}
