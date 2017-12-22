package eu.virtuwind.monitoring.impl;

public class LatencyJitterWrapper {


    private Long latency;
    private Long jitter;

    public LatencyJitterWrapper(Long latency, Long jitter) {
        this.latency = latency;
        this.jitter = jitter;
    }


    public Long getLatency() {
        return latency;
    }

    public Long getJitter() {
        return jitter;
    }
}
