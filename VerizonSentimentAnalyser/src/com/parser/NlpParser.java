package com.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;

public class NlpParser {
	private InputStream sentenceIn;
	private InputStream wordIn;
	private InputStream posIn;
	public NlpParser(){
		
	}
	
	public  ArrayList<String> SentenceDetect(String sCurrentLine) throws InvalidFormatException,
	IOException {
		String file=this.getClass().getResource("/com/resource/en-sent.bin").toString();
		sentenceIn = new FileInputStream(file.substring(file.indexOf("/")+1));
		SentenceModel model = new SentenceModel(sentenceIn);
		SentenceDetectorME sdetector = new SentenceDetectorME(model);
		ArrayList<String> sentenceList = new ArrayList<String>();
		
		String sentences[] = sdetector.sentDetect(sCurrentLine);
			if(sentences != null){
				//System.out.println(sentences.length);
					
				
				for(String sentence : sentences){
					//System.out.println(sentence);
					sentenceList.add(sentence);
				}
				
			}
		return sentenceList;
	}
	
public  String[] tokenizer(String sentence ) throws Exception{
		String file=this.getClass().getResource("/com/resource/en-token.bin").toString();
		wordIn= new FileInputStream(file.substring(file.indexOf("/")+1));
		TokenizerModel  tokenizerModel= new TokenizerModel(wordIn);
		Tokenizer tokenizer = new TokenizerME(tokenizerModel);
		String tokens[] = tokenizer.tokenize(sentence);
		
		/*if(tokens !=null){
			for(int i=0; i< tokens.length;i++){
				//System.out.println(tokens[i]);
			}
		}*/
		
		return tokens;
		
	}

public  String[] posSentence(String[] words ) throws Exception{
	String file=this.getClass().getResource("/com/resource/en-pos-maxent.bin").toString();
	posIn= new FileInputStream(file.substring(file.indexOf("/")+1));
	
		POSModel model = new POSModel(posIn);
		POSTaggerME tagger = new POSTaggerME(model);
		String tags[] = tagger.tag(words);
		
		//double probs[] = tagger.probs();
		
	/*if(probs !=null){
		for(int i=0; i< probs.length;i++){
			System.out.println(probs[i]);
		}
	}*/
	
	return tags;
	
}
public  List<String> posSentence1(List<String> list  ) throws Exception{
	String file=this.getClass().getResource("/com/resource/en-pos-maxent.bin").toString();
	posIn= new FileInputStream(file.substring(file.indexOf("/")+1));
		POSModel model = new POSModel(posIn);
		POSTaggerME tagger = new POSTaggerME(model);
		List<String> val = tagger.tag(list);
		double probs[] = tagger.probs();
		
	/*if(probs !=null){
		for(int i=0; i< probs.length;i++){
			System.out.println(probs[i]);
		}
	}*/
	
	return val;
	
}

public  String posSentence2(String sentence)throws Exception{
	String file=this.getClass().getResource("/com/resource/english-left3words-distsim.tagger").toString();
	MaxentTagger tagger = new MaxentTagger(file.substring(file.indexOf("/")+1));
	String tagged = tagger.tagString(sentence);
	return tagged;
}

	public  String[] posParse(String s){
		String p[]=s.split(" ");	
		String pos[]=new String[p.length];
		if(p !=null){
			for(int i=0; i< p.length;i++){
				String val=p[i];
				if(val.indexOf("_")>=0){
					pos[i]=val.substring(val.lastIndexOf("_")+1);
				}
			}
	}
		return pos;
	}
	
	public  String[] wordParse(String s){
		String p[]=s.split(" ");	
		String word[]=new String[p.length];
		if(p !=null){
			for(int i=0; i< p.length;i++){
				String val=p[i];
				if(val.indexOf("_")>=0){
					word[i]=val.substring(0,val.lastIndexOf("_"));
				}
			}
	}
		return word;
	}
}
