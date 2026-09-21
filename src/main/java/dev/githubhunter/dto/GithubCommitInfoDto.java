package dev.githubhunter.dto;

public class GithubCommitInfoDto {

    private String message;
    private GithubAuthorDto author;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GithubAuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(GithubAuthorDto author) {
        this.author = author;
    }
}
