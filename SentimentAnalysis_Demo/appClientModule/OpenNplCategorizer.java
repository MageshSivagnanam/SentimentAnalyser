import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.dictionary.Dictionary;
import com.parser.NlpParser;
import com.rules.RulesEngin;


public class OpenNplCategorizer {

		@SuppressWarnings("resource")
		public static void main(String[] args) {
	
			try {
				NlpParser parser= new NlpParser();
				BufferedReader br = null;
				RulesEngin ruleEngin= new RulesEngin();
				Dictionary dictionary= new Dictionary();
				String sCurrentLine;
				br = new BufferedReader(new FileReader("C:/Sample/DataHackathon/inputsample.txt"));
			    
				while ((sCurrentLine = br.readLine()) != null) {
					int pov=0;
					int neg=0;
					int nut=0;
					String result="";
					ArrayList<String> sentenceList = parser.SentenceDetect(sCurrentLine);
					List<String> val= parser.posSentence1(sentenceList);
					/*for(String sentence: val){
						System.out.println(val);
					}*/
					System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
					for(String sentence: sentenceList){
						
						
						String tagged = parser.posSentence2(sentence);
						String[] words =parser.wordParse(tagged);
						String[] pos =parser.posParse(tagged);
						double d=ruleEngin.getScore(words, dictionary,pos);
						
						if((d>0)){
							pov++;
						}else if(d<0){
							neg++;
						}else{
							nut++;
						}
						
						
					}
					/*System.out.println("==============");
					System.out.println("pov-->"+pov);
					System.out.println("neg-->"+neg);
					System.out.println("nut-->"+nut);*/
					
					
					int cal =pov-neg;
					
					if(cal<0 ){
						result="Negative";
					}else if(cal>0){
						result="Positive";
					}else{
						result="Neutral";
						
					}
					System.out.println("result-->"+result);
					System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	
}
