package com.leandro.gamelist.dto;

public class ReplacementDTO {

    private int sourceIndex;
    private int targetIndex;

    public ReplacementDTO(){}
    

    public ReplacementDTO(int sourceIndex, int targetIndex) {
        this.sourceIndex = sourceIndex;
        this.targetIndex = targetIndex;
    }


    public int getSourceIndex() {
        return sourceIndex;
    }


    public void setSourceIndex(int sourceIndex) {
        this.sourceIndex = sourceIndex;
    }


    public int getTargetIndex() {
        return targetIndex;
    }


    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    
}
