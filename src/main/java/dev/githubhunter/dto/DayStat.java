package dev.githubhunter.dto;

public class DayStat {

    private String date;
    private int count;
    private int exp;

    public DayStat(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public int getCount() {
        return count;
    }

    public int getExp() {
        return exp;
    }

    public void addCommit(int commitExp) {
        count++;
        exp += commitExp;
    }
}
