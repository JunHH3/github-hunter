package dev.githubhunter.dto;

public class CommitRecord {

    private String sha;
    private CommitBody commit;

    public String getSha() {
        return sha;
    }

    public CommitBody getCommit() {
        return commit;
    }
}
