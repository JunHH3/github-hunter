package dev.githubhunter.controller;

import dev.githubhunter.dto.CommitDetail;
import dev.githubhunter.dto.Commit;
import dev.githubhunter.dto.DayStat;
import dev.githubhunter.service.GitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GitController {

    private final GitService gitService;

    public GitController(GitService gitService) {
        this.gitService = gitService;
    }

    @GetMapping("/commits")
    public Commit[] commits() {
        return gitService.getCommits();
    }

    @GetMapping("/commits/{sha}")
    public CommitDetail commitDetailDto(@PathVariable String sha) {
        return gitService.getCommitDetail(sha);
    }

    @GetMapping("/commits/{sha}/exp")
    public int commitExp(@PathVariable String sha) {
        return gitService.getCommitExp(sha);
    }

    @GetMapping("/commits/exp")
    public int getCommitExp(){
        return gitService.getTotalExp();
    }

    @GetMapping("/commits/exp/today")
    public int todayExp() {
        return gitService.getTodayExp();
    }

    @GetMapping("/stats/week")
    public List<DayStat> weekStats(){
        List<DayStat> stats = gitService.getWeekStats();
        return stats;
    }
}
