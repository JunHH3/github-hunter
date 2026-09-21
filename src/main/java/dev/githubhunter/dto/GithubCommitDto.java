package dev.githubhunter.dto;


public class GithubCommitDto {

    private String sha;
    private GithubCommitInfoDto commit;

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public GithubCommitInfoDto getCommit() {
        return commit;
    }

    public void setCommit(GithubCommitInfoDto commit) {
        this.commit = commit;
    }
}
