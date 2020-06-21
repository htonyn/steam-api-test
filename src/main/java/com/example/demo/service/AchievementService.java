package com.example.demo.service;

import com.example.demo.component.SteamApiHttpComponent;
import static com.example.demo.domain.SteamAchievements.PlayerStats;
import static com.example.demo.domain.SteamAchievements.PlayerStats.Achievement;

import com.example.demo.domain.AchievementEvaluationResponse;
import org.springframework.stereotype.Service;

@Service
public class AchievementService {
    private SteamApiHttpComponent steamApiHttpComponent;

    public AchievementService(SteamApiHttpComponent steamApiHttpComponent) {
        this.steamApiHttpComponent = steamApiHttpComponent;
    }

    public AchievementEvaluationResponse getPlayerAchievements(String appId, String steamId) {
        PlayerStats playerStats = steamApiHttpComponent.getPlayerAchievements(appId, steamId);
        Integer achieved = 0;

        for (Achievement achievement : playerStats.getAchievements()) {
            if (achievement.getAchieved().equals(1)) {
                achieved++;
            }
        }

        return AchievementEvaluationResponse.builder()
                .gameName(playerStats.getGameName())
                .steamId(playerStats.getSteamID())
                .achievementsUnlocked(achieved)
                .build();
    }
}
