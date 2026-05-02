package com.portfolio.controller;

import com.portfolio.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
@CrossOrigin
public class StatsController {

    private final StatsService service;

    // ✅ Add view
    @PostMapping("/view")
    public void addView(@RequestParam String page) {
        service.increment(page);
    }

    // ✅ Get views
    @GetMapping("/views")
    public Long getViews(@RequestParam String page) {
        return service.getViews(page);
    }
}