package dev.githubhunter.controller;

import dev.githubhunter.dto.TodaySummaryDto;
import dev.githubhunter.service.GithubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final GithubService githubService;

    public DashboardController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/")
    public String dashboard(Model model){
        TodaySummaryDto summary = githubService.getTodaySummary();

        model.addAttribute("summary", summary);

        return "dashboard";
    }
}
