package dev.githubhunter.service;

import dev.githubhunter.dto.GithubCommitDetailDto;
import dev.githubhunter.dto.GithubCommitDto;
import dev.githubhunter.dto.GithubCommitStatsDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GithubService {

    private final RestClient restClient;

    public GithubService(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    public GithubCommitDto[] getCommits(){
        return restClient.get()
                .uri("https://api.github.com/repos/JunHH3/coding-test/commits")
                .retrieve()
                .body(GithubCommitDto[].class);
    }

    public GithubCommitDetailDto getCommitDetail(String sha) {
        return restClient.get()
                .uri("https://api.github.com/repos/JunHH3/coding-test/commits/{sha}", sha)
                .retrieve()
                .body(GithubCommitDetailDto.class);
    }

    public int getExp(int additions) {
        return 20 + (additions / 10) * 2;
    }

    public int totalExp(String sha) {
        GithubCommitDetailDto detail = getCommitDetail(sha);
        GithubCommitStatsDto stats = detail.getStats();
        int additions = stats.getAdditions();

        return getExp(additions);
    }
}
