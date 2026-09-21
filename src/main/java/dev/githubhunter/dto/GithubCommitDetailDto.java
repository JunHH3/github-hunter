package dev.githubhunter.dto;

public class GithubCommitDetailDto {

    private GithubCommitStatsDto stats;

    public GithubCommitStatsDto getStats() {
        return stats;
    }

    public void setStats(GithubCommitStatsDto stats) {
        this.stats = stats;
    }
}
