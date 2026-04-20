package com.worldline.gopay.routing;

public class BancafitFallbackActivation {
    private IssuerFallbackConfig config;

    public BancafitFallbackActivation(IssuerFallbackConfig config) {
        this.config = config;
    }

    public void activateBancafitFallback() {
        config.setTimeout(8000);
        FallbackPolicy policy = new FallbackPolicy();
        policy.setIssuerBin("IT-BANCAFIT-11");
        policy.setFallbackEnabled(true);
        policy.setPrimaryRoute("3DS_V2");
        policy.setFallbackRoute("ISSUER_LEGACY");
        applyFallbackPolicy(policy);
    }

    private void applyFallbackPolicy(FallbackPolicy policy) {
        System.out.println("Fallback policy activated for: " + policy.getIssuerBin());
        System.out.println("Primary route: " + policy.getPrimaryRoute());
        System.out.println("Fallback route: " + policy.getFallbackRoute());
    }

    public static class FallbackPolicy {
        private String issuerBin;
        private boolean fallbackEnabled;
        private String primaryRoute;
        private String fallbackRoute;

        public String getIssuerBin() {
            return issuerBin;
        }

        public void setIssuerBin(String issuerBin) {
            this.issuerBin = issuerBin;
        }

        public boolean isFallbackEnabled() {
            return fallbackEnabled;
        }

        public void setFallbackEnabled(boolean fallbackEnabled) {
            this.fallbackEnabled = fallbackEnabled;
        }

        public String getPrimaryRoute() {
            return primaryRoute;
        }

        public void setPrimaryRoute(String primaryRoute) {
            this.primaryRoute = primaryRoute;
        }

        public String getFallbackRoute() {
            return fallbackRoute;
        }

        public void setFallbackRoute(String fallbackRoute) {
            this.fallbackRoute = fallbackRoute;
        }
    }
}