package com.steamapi.test.controller

import com.steamapi.test.domain.AchievementEvaluationResponse
import com.steamapi.test.service.AchievementService
import org.springframework.http.HttpStatus
import spock.lang.Specification

class AchievementControllerTest extends Specification {

    AchievementController achievementController
    AchievementService achievementService = Mock()

    def setup() {
        achievementController = new AchievementController(achievementService)
    }

    def "getPlayerAchievements"() {
        given:
            def appId = "123456"
            def steamId = "111111111111"
        when:
            def result = achievementController.getPlayerAchievements(appId, steamId)

        then:
            1 * achievementService.getPlayerAchievements(appId, steamId) >> generatePlayerStats(steamid: steamId)
            result.getStatusCode() == HttpStatus.OK
            result.getBody().achievementsUnlocked == 10
            result.getBody().steamId == steamId
            result.getBody().gameName == "DEFAULT_GAMENAME"

    }

    def generatePlayerStats(Map map = [:]) {
        return AchievementEvaluationResponse.builder()
                .gameName((map.gamename) ? ((String) map.gamename) : "DEFAULT_GAMENAME")
                .steamId((map.steamid) ? ((String) map.steamid) : "DEFAULT_STEAMID")
                .achievementsUnlocked((map.achieved) ? ((Integer) map.achieved) : 10)
                .build()
    }

}