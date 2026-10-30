package dev.githubhunter.dto;

public class CommitBody {
    private String message;
    private AuthorInfo author;

    public String getMessage() {
        return message;
    }

    public AuthorInfo getAuthor() {
        return author;
    }
}
