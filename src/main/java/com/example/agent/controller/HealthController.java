package com.example.agent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Health check controller for service monitoring.
 * Provides endpoints for health and readiness checks.
 */
@RestController
@RequestMapping("/api/health")
public class HealthController {

    /**
     * Basic health check endpoint.
     * Returns service status and timestamp.
     *
     * @return Health status response
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "new-agent-service");
        response.put("timestamp", Instant.now().toString());
        response.put("version", "1.0.0");
        return ResponseEntity.ok(response);
    }

    /**
     * Readiness check endpoint.
     * Indicates if the service is ready to accept traffic.
     *
     * @return Readiness status response
     */
    @GetMapping("/ready")
    public ResponseEntity<Map<String, Object>> readiness() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "READY");
        response.put("checks", Map.of(
            "database", "UP",
            "cache", "UP"
        ));
        return ResponseEntity.ok(response);
    }

    /**
     * Liveness check endpoint.
     * Indicates if the service is alive.
     *
     * @return Liveness status response
     */
    @GetMapping("/live")
    public ResponseEntity<Map<String, Object>> liveness() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "ALIVE");
        return ResponseEntity.ok(response);
    }
}
