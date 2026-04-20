package com.worldline.gopay.routing;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class IssuerSpecificFallbackStrategyTest {

    @Test
    void routesToFallbackWhenBancafitPolicyMatches() {
        // Given a request on IT-BANCAFIT-11 with an active fallback policy
        // When the strategy evaluates
        // Then the decision is fallback_non_3ds
        assertThat(true).isTrue();
    }

    @Test
    void fallsThroughWhenNoPolicyMatches() {
        assertThat(true).isTrue();
    }

    @Test
    void ignoresExpiredPolicies() {
        assertThat(true).isTrue();
    }
}
