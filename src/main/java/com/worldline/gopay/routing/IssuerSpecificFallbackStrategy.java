package com.worldline.gopay.routing;

import com.worldline.gopay.config.ConfigServiceClient;
import com.worldline.gopay.config.FallbackPolicyLoader;
import com.worldline.gopay.observability.FallbackMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

/**
 * Fallback ciblé par issuer en cas de dégradation ACS.
 * Déclenchée par INC-GP-031 (mars 2026) : dégradation récurrente IT-BANCAFIT-11.
 */
@Component
public class IssuerSpecificFallbackStrategy implements RouterStrategy {

    private static final Logger log = LoggerFactory.getLogger(IssuerSpecificFallbackStrategy.class);

    private final FallbackPolicyLoader policyLoader;
    private final ConfigServiceClient configService;
    private final FallbackMetrics metrics;

    public IssuerSpecificFallbackStrategy(FallbackPolicyLoader p, ConfigServiceClient c, FallbackMetrics m) {
        this.policyLoader = p; this.configService = c; this.metrics = m;
    }

    @Override
    public RoutingDecision route(ThreeDSRequest request) {
        long timeoutMs = configService.getLong("gopay.routing.fallback.timeout", 8000L);

        Optional<FallbackPolicy> match = policyLoader.getActivePolicies().stream()
            .filter(p -> p.matches(request)).findFirst();

        if (match.isEmpty()) return RoutingDecision.defaultRouting(request);
        FallbackPolicy policy = match.get();
        if (policy.getValidUntil().isBefore(Instant.now())) return RoutingDecision.defaultRouting(request);

        log.info("fallback_decision issuer={} region={} policy={} action={} tx_id={}",
            request.getIssuerBin(), request.getRegion(), policy.getId(), policy.getAction(), request.getTxId());
        metrics.incrementFallback(request.getRegion(), request.getIssuerBin(), policy.getAction());
        return RoutingDecision.fallback(request, policy);
    }

    @Override public int getPriority() { return 10; }
}
