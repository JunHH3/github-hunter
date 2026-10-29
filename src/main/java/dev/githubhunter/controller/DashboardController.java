package dev.githubhunter.controller;

import dev.githubhunter.dto.Level;
import dev.githubhunter.dto.TodayStats;
import dev.githubhunter.service.GitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final GitService gitService;

    public DashboardController(GitService gitService) {
        this.gitService = gitService;
    }

    @GetMapping("/")
    public String dashboard(Model model){
        TodayStats summary = gitService.getTodayStats();
        Level level = gitService.getLevelInfo();

        model.addAttribute("summary", summary);
        model.addAttribute("level", level);

        return "dashboard";
    }
}
