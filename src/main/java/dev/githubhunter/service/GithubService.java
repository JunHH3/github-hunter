package dev.githubhunter.service;

import dev.githubhunter.dto.GithubCommitDetailDto;
import dev.githubhunter.dto.GithubCommitDto;
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


}
