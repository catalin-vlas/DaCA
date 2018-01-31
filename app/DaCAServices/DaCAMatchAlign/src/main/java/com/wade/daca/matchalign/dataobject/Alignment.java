package com.wade.daca.matchalign.dataobject;

public class Alignment {

    private String entity1;
    private String entity2;
    private String relation;
    private double confidence;

    public Alignment() {
    }

    public Alignment(String entity1, String entity2, String relation, double confidence) {
        this.entity1 = entity1;
        this.entity2 = entity2;
        this.relation = relation;
        this.confidence = confidence;
    }

    public String getEntity1() {
        return entity1;
    }

    public void setEntity1(String entity1) {
        this.entity1 = entity1;
    }

    public String getEntity2() {
        return entity2;
    }

    public void setEntity2(String entity2) {
        this.entity2 = entity2;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    @Override
    public String toString() {
        return "Alignment{" +
                "entity1='" + entity1 + '\'' +
                ", entity2='" + entity2 + '\'' +
                ", relation='" + relation + '\'' +
                ", confidence=" + confidence +
                '}';
    }

}
