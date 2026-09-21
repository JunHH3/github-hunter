package dev.githubhunter.controller;

import dev.githubhunter.dto.GithubCommitDto;
import dev.githubhunter.service.GithubService;
import org.springframework.web.bind.annotation.GetMapping;
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
}
