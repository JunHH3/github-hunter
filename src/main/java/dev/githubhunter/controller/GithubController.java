package dev.githubhunter.controller;

import dev.githubhunter.dto.GithubCommitDetailDto;
import dev.githubhunter.dto.GithubCommitDto;
import dev.githubhunter.service.GithubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/commits")
    public GithubCommitDto[] commits() {
        return githubService.getCommits();
    }

    @GetMapping("/commits/{sha}")
    public GithubCommitDetailDto commitDetailDto(@PathVariable String sha) {
        return githubService.getCommitDetail(sha);
    }

    @GetMapping("/commits/{sha}/exp")
    public int commitExp(@PathVariable String sha) {
        return githubService.totalExp(sha);
    }

    @GetMapping("/commits/exp")
    public int totalExp(){
        return githubService.getTotalExp();
    }
}
