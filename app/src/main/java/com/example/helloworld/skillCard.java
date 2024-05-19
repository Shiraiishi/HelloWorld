package com.example.helloworld;

public class skillCard {
    private String skillName;
    private String skillDesc;
    private int skillLevel;
    private int skillExp;

    public skillCard(){

    }

    public skillCard(String skillName, String skillDesc, int skillLevel, int skillExp) {
        this.skillName = skillName;
        this.skillDesc = skillDesc;
        this.skillLevel = skillLevel;
        this.skillExp = skillExp;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillDesc() {
        return skillDesc;
    }

    public void setSkillDesc(String skillDesc) {
        this.skillDesc = skillDesc;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public int getSkillExp() {
        return skillExp;
    }

    public void setSkillExp(int skillExp) {
        this.skillExp = skillExp;
    }
}
