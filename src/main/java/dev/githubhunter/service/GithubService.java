package dev.githubhunter.service;

import dev.githubhunter.dto.GithubCommitDetailDto;
import dev.githubhunter.dto.GithubCommitDto;
import dev.githubhunter.dto.GithubCommitStatsDto;
import dev.githubhunter.dto.TodaySummaryDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Service
public class GithubService {

    private final RestClient restClient;

    public GithubService(RestClient.Builder builder, @Value("${GITHUB_TOKEN}") String githubToken) {
        this.restClient = builder
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + githubToken)
                .build();
    }

    public GithubCommitDto[] getCommits() {
        return restClient.get()
                .uri("https://api.github.com/repos/JunHH3/github-hunter/commits")
                .retrieve()
                .body(GithubCommitDto[].class);
    }

    public GithubCommitDetailDto getCommitDetail(String sha) {
        return restClient.get()
                .uri("https://api.github.com/repos/JunHH3/github-hunter/commits/{sha}", sha)
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

    public int getTotalExp() {
        GithubCommitDto[] commits = getCommits();
        int totalExp = 0;

        for (GithubCommitDto commit : commits) {
            String sha = commit.getSha();
            int exp = totalExp(sha);
            totalExp += exp;
        }

        return totalExp;
    }

    public boolean isToday(String date) {
        ZoneId korea = ZoneId.of("Asia/Seoul");

        OffsetDateTime commitDateTime = OffsetDateTime.parse(date);
        LocalDate commitDate = commitDateTime
                .atZoneSameInstant(korea)
                .toLocalDate();

        LocalDate today = LocalDate.now(korea);

        return commitDate.equals(today);
    }

    public int getTodayTotalExp() {
        GithubCommitDto[] commits = getCommits();
        int totalExp = 0;

        for (GithubCommitDto commit : commits) {
            String date = commit.getCommit().getAuthor().getDate();

            if (isToday(date)) {
                String sha = commit.getSha();
                int exp = totalExp(sha);
                totalExp += exp;
            }
        }
        return totalExp;
    }

    public int getTodayCommitCount() {
        GithubCommitDto[] commits = getCommits();
        int count = 0;

        for (GithubCommitDto commit : commits) {
            String date = commit.getCommit().getAuthor().getDate();

            if (isToday(date)) {
                count++;
            }
        }
        return count;
    }

    public TodaySummaryDto getTodaySummary() {
        TodaySummaryDto summary = new TodaySummaryDto();
        GithubCommitDto[] commits = getCommits();

        int commitCount = 0;
        int additions = 0;
        int deletions = 0;
        int exp = 0;

        for (GithubCommitDto commit : commits) {
            String date = commit.getCommit().getAuthor().getDate();

            if (isToday(date)) {
                commitCount++;


                String sha = commit.getSha();
                GithubCommitDetailDto detail = getCommitDetail(sha);
                GithubCommitStatsDto stats = detail.getStats();

                additions += stats.getAdditions();
                deletions += stats.getDeletions();
                exp += getExp(stats.getAdditions());
            }
        }
        summary.setCommitCount(commitCount);
        summary.setAdditions(additions);
        summary.setDeletions(deletions);
        summary.setExp(exp);

        return summary;
    }
}
