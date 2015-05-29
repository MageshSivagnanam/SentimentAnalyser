package com.analyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dictionary.Dictionary;
import com.parser.NlpParser;
import com.rules.RulesEngin;

public class SentimentAnalyzer {
	private NlpParser parser;
	RulesEngin ruleEngin;
	Dictionary dictionary;
	public SentimentAnalyzer(){
		 parser= new NlpParser();
		 ruleEngin= new RulesEngin();
		 dictionary= new Dictionary();
		 System.out.println(this.getClass().getResource("/com/resource/en-pos-maxent.bin"));
	}
	public AnalyzedData doAnalysis(String comment)throws Exception{
		AnalyzedData data= new AnalyzedData();
		ArrayList<String> sentenceList = parser.SentenceDetect(comment);
		List<String> val= parser.posSentence1(sentenceList);
		int pov=0;
		int neg=0;
		int nut=0;
		String result="";
		Map<String, Double> sentenceScore = new HashMap<String, Double>();
		for(String sentence: sentenceList){
			String tagged = parser.posSentence2(sentence);
			String[] words =parser.wordParse(tagged);
			String[] pos =parser.posParse(tagged);
			double d=ruleEngin.getScore(words, dictionary,pos);
			sentenceScore.put(sentence,new Double(d));
			if((d>0)){
				pov++;
			}else if(d<0){
				neg++;
			}else{
				nut++;
			}
		}
		int cal =pov-neg;
		if(cal<0 ){
			result="Negative";
		}else if(cal>0){
			result="Positive";
		}else{
			result="Neutral";
		}
		data.setComment(comment);
		data.setNegCount(nut);
		data.setNeuCount(neg);
		data.setPosCount(pov);
		data.setResult(result);
		data.setSentenceScore(sentenceScore);
		
		return data;
		
	}
	
	public List<AnalyzedData> doAnalysis(BufferedReader br)throws Exception{
		String sCurrentLine;
		List<AnalyzedData> datas= new ArrayList<AnalyzedData>();
		while ((sCurrentLine = br.readLine()) != null) {
			AnalyzedData data= new AnalyzedData();
			ArrayList<String> sentenceList = parser.SentenceDetect(sCurrentLine);
			List<String> val= parser.posSentence1(sentenceList);
			int pov=0;
			int neg=0;
			int nut=0;
			String result="";
			Map<String, Double> sentenceScore = new HashMap<String, Double>();
			for(String sentence: sentenceList){
				String tagged = parser.posSentence2(sentence);
				String[] words =parser.wordParse(tagged);
				String[] pos =parser.posParse(tagged);
				double d=ruleEngin.getScore(words, dictionary,pos);
				sentenceScore.put(sentence,new Double(d));
				if((d>0)){
					pov++;
				}else if(d<0){
					neg++;
				}else{
					nut++;
				}
			}
			int cal =pov-neg;
			if(cal<0 ){
				result="Negative";
			}else if(cal>0){
				result="Positive";
			}else{
				result="Neutral";
			}
			data.setComment(sCurrentLine);
			data.setNegCount(nut);
			data.setNeuCount(neg);
			data.setPosCount(pov);
			data.setResult(result);
			data.setSentenceScore(sentenceScore);
			datas.add(data);
		}
		
		return datas;
		
	}
	
	public static void main(String arg[]){
		SentimentAnalyzer analyzer= new SentimentAnalyzer(); 
		try{
			//AnalyzedData data =analyzer.doAnalysis("this very Bad");
			//System.out.println(data.getResult());
			/*List<AnalyzedData> datas =analyzer.doAnalysis(new FileReader("C:/Sample/DataHackathon/inputsample.txt"));
			for(AnalyzedData data:datas){
				System.out.println(data.getResult());
			}*/
			//System.out.println();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
