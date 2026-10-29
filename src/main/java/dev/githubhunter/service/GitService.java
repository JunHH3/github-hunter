package dev.githubhunter.service;

import dev.githubhunter.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class GitService {

    private final RestClient restClient;

    public GitService(RestClient.Builder builder, @Value("${GITHUB_TOKEN}") String githubToken) {
        this.restClient = builder
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + githubToken)
                .build();
    }

    public Commit[] getCommits() {
        return restClient.get()
                .uri("https://api.github.com/repos/JunHH3/github-hunter/commits")
                .retrieve()
                .body(Commit[].class);
    }

    public CommitDetail getCommitDetail(String sha) {
        return restClient.get()
                .uri("https://api.github.com/repos/JunHH3/github-hunter/commits/{sha}", sha)
                .retrieve()
                .body(CommitDetail.class);
    }

    public int getExp(int additions) {
        return 20 + (additions / 10) * 2;
    }

    public int getCommitExp(String sha) {
        CommitDetail detail = getCommitDetail(sha);
        CommitStats stats = detail.getStats();
        int additions = stats.getAdditions();

        return getExp(additions);
    }

    public int getTotalExp() {
        Commit[] commits = getCommits();
        int totalExp = 0;

        for (Commit commit : commits) {
            String sha = commit.getSha();
            int exp = getCommitExp(sha);
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

    public int getTodayExp() {
        Commit[] commits = getCommits();
        int totalExp = 0;

        for (Commit commit : commits) {
            String date = commit.getCommit().getAuthor().getDate();

            if (isToday(date)) {
                String sha = commit.getSha();
                int exp = getCommitExp(sha);
                totalExp += exp;
            }
        }
        return totalExp;
    }

    public int getTodayCount() {
        Commit[] commits = getCommits();
        int count = 0;

        for (Commit commit : commits) {
            String date = commit.getCommit().getAuthor().getDate();

            if (isToday(date)) {
                count++;
            }
        }
        return count;
    }

    public TodayStats getTodayStats() {
        TodayStats summary = new TodayStats();
        Commit[] commits = getCommits();

        int commitCount = 0;
        int additions = 0;
        int deletions = 0;
        int exp = 0;

        for (Commit commit : commits) {
            String date = commit.getCommit().getAuthor().getDate();

            if (isToday(date)) {
                commitCount++;


                String sha = commit.getSha();
                CommitDetail detail = getCommitDetail(sha);
                CommitStats stats = detail.getStats();

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

    public int getLevel(int totalExp) {
        return (totalExp / 100);
    }

    public int getCurrentExp(int totalExp) {
        return totalExp % 100;
    }

    public int getProgress(int totalExp) {
        int currentExp = getCurrentExp(totalExp);

        return currentExp;
    }

    public Level getLevelInfo(){
        int totalExp = getTotalExp();

        Level levelDto = new Level();

        levelDto.setTotalExp(totalExp);
        levelDto.setLevel(getLevel(totalExp));
        levelDto.setCurrentExp(getCurrentExp(totalExp));
        levelDto.setProgress(getProgress(totalExp));

        return levelDto;
    }

    public List<DayStat> getWeekStats(){
        List<DayStat> week = new ArrayList<>();
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));

        for (int i = 0; i <= 6; i++) {
            LocalDate date = today.minusDays(6 - i);
            DayStat stat = new DayStat(date.toString());
            week.add(stat);
        }

        Commit[] commits = getCommits();
        for (Commit commit : commits) {
            String date = commit.getCommit()
                    .getAuthor()
                    .getDate();
            OffsetDateTime commitTime = OffsetDateTime.parse(date);

            LocalDate commitDate = commitTime
                    .atZoneSameInstant(ZoneId.of("Asia/Seoul"))
                    .toLocalDate();

            for (DayStat stat : week) {
                if (commitDate.equals(LocalDate.parse(stat.getDate()))) {
                    String sha = commit.getSha();
                    int commitExp = getCommitExp(sha);
                    stat.addCommit(commitExp);
                }
            }
        }
        return week;
    }

}
