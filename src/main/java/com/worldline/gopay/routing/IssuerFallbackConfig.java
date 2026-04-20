// Centralized timeout configuration for Italian issuer
// Add the necessary logic to handle timeout settings.
public class IssuerFallbackConfig {
    // Configuration properties
    private int timeout;

    public IssuerFallbackConfig(int timeout) {
        this.timeout = timeout;
    }

    // Getters and setters
    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}