package com.rules;

import com.dictionary.Dictionary;

public class RulesEngin {
	
	public int getScore(String[] words, Dictionary dictionary){
		int score=0;
		String word="";
		for(int i=0;i<words.length;i++){
			String val=words[i];
			
			if(!val.trim().equalsIgnoreCase("not")){
				if(word.length()>0){
					word=word+" "+val.trim();
				}else{
					word=val.trim();
				}
				if(dictionary.getWordList().containsKey(word.trim().toLowerCase())){
					int d =dictionary.getWordList().get(word.trim().toLowerCase()).intValue();
					score=score+d;
				}
				word="";
			}else{
				if(i>0 && words[i-1].trim().toLowerCase().equalsIgnoreCase("does")){
					word =words[i-1].trim().toLowerCase()+" "+word+" "+val.trim().toLowerCase();
				}else{
					word =word+" "+val.trim().toLowerCase();
				}
			}
		}
		return score;
	}
	
	public double getScore(String[] words, Dictionary dictionary, String[] pos){
		double score=0;
		String word="";
		/*for(int i=0; i< pos.length;i++){
			System.out.print(pos[i]+"_"+words[i]+"  ");
		}*/
		for(int i=0;i<words.length;i++){
			String val=words[i].trim().toLowerCase();
			String p=pos[i].trim();
			Double d=null;
			
			if(p.startsWith("JJ")){
				d= dictionary.getDictionarys().get(val+"#a");
				//System.out.println(val+"_"+p+"--->"+d);
			}else if(p.startsWith("VB")){
				d= dictionary.getDictionarys().get(val+"#v");
				//System.out.println(val+"_"+p+"--->"+d);
			}else if(p.startsWith("NN")){
				d= dictionary.getDictionarys().get(val+"#n");
				//System.out.println(val+"_"+p+"--->"+d);
			}else if(p.startsWith("RB")){
				d= dictionary.getDictionarys().get(val+"#r");
				//System.out.println(val+"_"+p+"--->"+d);
			}
			
			if(d!=null){
				score=score+d.doubleValue();
				
			}
			
		}
	//	System.out.println("score--->"+score);
		return score;
	}

}
