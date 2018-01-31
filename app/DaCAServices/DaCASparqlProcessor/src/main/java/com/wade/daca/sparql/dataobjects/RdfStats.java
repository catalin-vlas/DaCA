package com.wade.daca.sparql.dataobjects;

/**
 * Created by catavlas on 1/30/2018.
 */
public class RdfStats {

    private int Size; //file size in Bytes
    private int NrTriples;
    private int NrNodes;
    private int NrBlankNodes;
    private int NrLiterals;
    private int NrURINodes;
    private int NrType;
    private int MaxInDegree;
    private int MaxOutDegree;
    private int MaxDegree;
    private String MaxInDegreeNode;
    private String MaxOutDegreeNode;
    private String MaxDegreeNode;
    private String NamespaceID;

    public RdfStats() {
        Size = 0;
        NrTriples=0;
        NrNodes=0;
        NrBlankNodes=0;
        NrLiterals=0;
        NrURINodes=0;
        NrType=0;
        MaxInDegree=0;
        MaxOutDegree=0;
        MaxDegree=0;

        MaxInDegreeNode="";
        MaxOutDegreeNode="";
        MaxDegreeNode="";
    }

    public String getNamespaceID() {
        return NamespaceID;
    }

    public void setNamespaceID(String namespaceID) {
        NamespaceID = namespaceID;
    }

    public int getNrLiterals() {
        return NrLiterals;
    }

    public void setNrLiterals(int nrLiterals) {
        NrLiterals = nrLiterals;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    public int getNrTriples() {
        return NrTriples;
    }

    public void setNrTriples(int nrTriples) {
        NrTriples = nrTriples;
    }

    public int getNrNodes() {
        return NrNodes;
    }

    public void setNrNodes(int nrNodes) {
        NrNodes = nrNodes;
    }

    public int getNrBlankNodes() {
        return NrBlankNodes;
    }

    public void setNrBlankNodes(int nrBlankNodes) {
        NrBlankNodes = nrBlankNodes;
    }

    public int getNrURINodes() {
        return NrURINodes;
    }

    public void setNrURINodes(int nrURINodes) {
        NrURINodes = nrURINodes;
    }

    public int getNrType() {
        return NrType;
    }

    public void setNrType(int nrType) {
        NrType = nrType;
    }

    public int getMaxInDegree() {
        return MaxInDegree;
    }

    public void setMaxInDegree(int maxInDegree) {
        MaxInDegree = maxInDegree;
    }

    public int getMaxOutDegree() {
        return MaxOutDegree;
    }

    public void setMaxOutDegree(int maxOutDegree) {
        MaxOutDegree = maxOutDegree;
    }

    public int getMaxDegree() {
        return MaxDegree;
    }

    public void setMaxDegree(int maxDegree) {
        MaxDegree = maxDegree;
    }

    public String getMaxInDegreeNode() {
        return MaxInDegreeNode;
    }

    public void setMaxInDegreeNode(String maxInDegreeNode) {
        MaxInDegreeNode = maxInDegreeNode;
    }

    public String getMaxOutDegreeNode() {
        return MaxOutDegreeNode;
    }

    public void setMaxOutDegreeNode(String maxOutDegreeNode) {
        MaxOutDegreeNode = maxOutDegreeNode;
    }

    public String getMaxDegreeNode() {
        return MaxDegreeNode;
    }

    public void setMaxDegreeNode(String maxDegreeNode) {
        MaxDegreeNode = maxDegreeNode;
    }


    @Override
    public String toString() {
        return "RdfStats{" +
                "Size=" + Size +
                ", NrTriples=" + NrTriples +
                ", NrNodes=" + NrNodes +
                ", NrBlankNodes=" + NrBlankNodes +
                ", NrLiterals=" + NrLiterals +
                ", NrURINodes=" + NrURINodes +
                ", NrType=" + NrType +
                ", MaxInDegree=" + MaxInDegree +
                ", MaxOutDegree=" + MaxOutDegree +
                ", MaxDegree=" + MaxDegree +
                ", MaxInDegreeNode='" + MaxInDegreeNode + '\'' +
                ", MaxOutDegreeNode='" + MaxOutDegreeNode + '\'' +
                ", MaxDegreeNode='" + MaxDegreeNode + '\'' +
                '}';
    }
}
