package com.mbean;

import javax.faces.event.ActionEvent;

import com.analyzer.AnalyzedData;
import com.analyzer.SentimentAnalyzer;


public class AnalyzerManagedBean {
	public AnalyzerManagedBean(){
		System.out.println(this.getClass().getResource("/com/resource/en-pos-maxent.bin"));
		analyzedData = new AnalyzedData();
	}
	private AnalyzedData analyzedData;
	public void commentAnalyzer(ActionEvent event )throws Exception{
		SentimentAnalyzer analyzer= new SentimentAnalyzer();
		analyzedData=analyzer.doAnalysis(analyzedData.getComment());
		System.out.println(analyzedData.getResult());
	}
	
	public void reset(ActionEvent event )throws Exception{
		analyzedData= new AnalyzedData();
	}
	
	public static void main(String a[]){
		
		try {
			//AnalyzerManagedBean d= new AnalyzerManagedBean();
			SentimentAnalyzer analyzer= new SentimentAnalyzer();
			AnalyzedData data=analyzer.doAnalysis("This very good");
			System.out.println(data.getResult());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public AnalyzedData getAnalyzedData() {
		return analyzedData;
	}

	public void setAnalyzedData(AnalyzedData analyzedData) {
		this.analyzedData = analyzedData;
	}
	
	public boolean getRenderResult() {
		return (analyzedData!=null && analyzedData.getResult()!=null && analyzedData.getResult().length()>0);
	}
	
	
}
