package com.steamapi.test.controller;

import com.steamapi.test.domain.AchievementEvaluationResponse;
import com.steamapi.test.service.AchievementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AchievementController {

    private AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping("/getAchievements/application/{appId}/steamid/{steamId}")
    public ResponseEntity<AchievementEvaluationResponse> getPlayerAchievements(@PathVariable("appId") String appId,
                                                                               @PathVariable("steamId") String steamId) {
        return new ResponseEntity<>(achievementService.getPlayerAchievements(appId, steamId), HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(@RequestParam(value = "taco", required = false) String taco,
                       @RequestParam(value = "burrito", required = false) String burrito) {
        if (taco != null && burrito != null) {
            return "Hello " + taco + " and " + burrito;
        } else {
            return "Hello no name";
        }
    }

    @GetMapping("/getAchievements/application/{appId}/steamid")
    public String getPlayerAchievements(@PathVariable("appId") String appId) {
        return "youre in the wrong place";
    }

    @GetMapping("/add")
    public Integer addNumbers(@RequestParam(value = "taco", required = false) String taco,
                              @RequestParam(value = "burrito", required = false) String burrito) {
        try {
            Integer val1 = Integer.parseInt(taco);
            Integer val2 = Integer.parseInt(burrito);
            return val1 + val2;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
