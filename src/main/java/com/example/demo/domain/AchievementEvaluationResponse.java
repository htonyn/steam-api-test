package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AchievementEvaluationResponse {

    private String steamId;
    private String gameName;
    private Integer achievementsUnlocked;
}
