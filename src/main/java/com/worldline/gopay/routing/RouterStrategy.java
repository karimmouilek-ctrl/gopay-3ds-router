package com.worldline.gopay.routing;

public interface RouterStrategy {
    RoutingDecision route(ThreeDSRequest request);
    int getPriority();
}
