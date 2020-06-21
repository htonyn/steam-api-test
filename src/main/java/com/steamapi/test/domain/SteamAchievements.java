package com.steamapi.test.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SteamAchievements {
    public PlayerStats playerstats;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PlayerStats {
        public String gameName;
        public String steamID;
        public List<Achievement> achievements;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Achievement {
            public String apiname;
            public Integer achieved;
            public Long unlocktime;
        }
    }

}
