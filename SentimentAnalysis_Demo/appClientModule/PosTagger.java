

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class PosTagger {

	
	
	public static void main(String[] args) {
		
		/*if (args.length != 2) {
		      System.err.println("usage: java TaggerDemo modelFile fileToTag");
		      return;
		    }
		
		*/ 
		// Initialize the tagger
		MaxentTagger tagger = new MaxentTagger("C:/Java_Project/NlpDemo/src/english-left3words-distsim.tagger");
		 
		// The sample string
		String sample = "Blazing fast internet speeds... I'm blown away";
		 
		// The tagged string
		String tagged = tagger.tagString(sample);
		 
		//output the tagged sample string onto your console
		System.out.println("Input: " + sample);
		System.out.println("Output: "+ tagged);
		    
		  }

	
	}


