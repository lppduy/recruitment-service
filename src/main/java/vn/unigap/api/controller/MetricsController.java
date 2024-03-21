// MetricsController.java
package vn.unigap.api.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.unigap.api.service.MetricsService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MetricsController {
    private final MetricsService metricsService;

    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping("/metrics")
    public Map<String, Object> getMetrics(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("numEmployer", metricsService.getNumEmployers(fromDate, toDate));
        metrics.put("numJob", metricsService.getNumJobs(fromDate, toDate));
        metrics.put("numSeeker", metricsService.getNumSeekers(fromDate, toDate));
        metrics.put("numResume", metricsService.getNumResumes(fromDate, toDate));

        // Get the data for each day
        List<Map<String, Object>> chart = metricsService.getDailyMetrics(fromDate, toDate);
        metrics.put("chart", chart);

        return metrics;
    }
}