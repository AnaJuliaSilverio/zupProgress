package com.zup.zupProgress.config;

import com.zup.zupProgress.model.ChallengeModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigChallenge {
    @Bean
    public ChallengeModel challengeModel() {
        return new ChallengeModel();
    }
}
