package com.steamapi.test.service;

import com.steamapi.test.component.SteamApiHttpComponent;
import static com.steamapi.test.domain.SteamAchievements.PlayerStats;
import static com.steamapi.test.domain.SteamAchievements.PlayerStats.Achievement;

import com.steamapi.test.domain.AchievementEvaluationResponse;
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
