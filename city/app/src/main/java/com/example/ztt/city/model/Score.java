package com.example.ztt.city.model;

public class Score {
    //课程名
    private String scoreName;
    //学分
    private String credit;
    //课程成绩
   private String scoreAchievement;
    //期末成绩
    private String terminalAchievement;
    //平时成绩
    private String peacetimeAchievement;

    public String getScoreName() {
        return scoreName;
    }

    public void setScoreName(String scoreName) {
        this.scoreName = scoreName;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getScoreAchievement() {
        return scoreAchievement;
    }public void setScoreAchievement(String scoreAchievement) {
        this.scoreAchievement = scoreAchievement;
    }

    public String getTerminalAchievement() {
        return terminalAchievement;
    }

    public void setTerminalAchievement(String terminalAchievement) {
        this.terminalAchievement = terminalAchievement;
    }

    public String getPeacetimeAchievement() {
        return peacetimeAchievement;
    }

    public void setPeacetimeAchievement(String peacetimeAchievement) {
        this.peacetimeAchievement = peacetimeAchievement;
    }



}
