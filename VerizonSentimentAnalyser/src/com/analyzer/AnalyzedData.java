package com.analyzer;

import java.util.Map;

public class AnalyzedData {
private String result;
private String comment;
private int posCount;
private int negCount;
private int neuCount;
private Map<String, Double> sentenceScore;
public String getResult() {
	return result;
}
public void setResult(String result) {
	this.result = result;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public int getPosCount() {
	return posCount;
}
public void setPosCount(int posCount) {
	this.posCount = posCount;
}
public int getNegCount() {
	return negCount;
}
public void setNegCount(int negCount) {
	this.negCount = negCount;
}
public int getNeuCount() {
	return neuCount;
}
public void setNeuCount(int neuCount) {
	this.neuCount = neuCount;
}
public Map<String, Double> getSentenceScore() {
	return sentenceScore;
}
public void setSentenceScore(Map<String, Double> sentenceScore) {
	this.sentenceScore = sentenceScore;
}


}
