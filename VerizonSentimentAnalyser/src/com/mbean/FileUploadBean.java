package com.mbean;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.ChartDataModel;
import org.richfaces.model.StringChartDataModel;
import org.richfaces.model.UploadedFile;

import com.analyzer.AnalyzedData;
import com.analyzer.SentimentAnalyzer;
 

@ManagedBean
@SessionScoped
public class FileUploadBean implements Serializable {
	List<AnalyzedData> results=new ArrayList<AnalyzedData>();
    StringChartDataModel pie;
    List<GDPRecord> gdp;
    
    public void listener(FileUploadEvent event) throws Exception {
        UploadedFile item = event.getUploadedFile();
      
        /*file.setLength(item.getData().length);
        file.setName(item.getName());
        file.setData(item.getData());
        files.add(file);*/
        SentimentAnalyzer analyzer= new SentimentAnalyzer();
        ByteArrayInputStream bi= new ByteArrayInputStream(item.getData());
        BufferedReader br = new BufferedReader(new InputStreamReader(bi));
        results=analyzer.doAnalysis(br);
        for(AnalyzedData data:results){
			System.out.println(data.getResult());
		}
        init();
        
    }
	public List<AnalyzedData> getResults() {
		return results;
	}
	public void setResults(List<AnalyzedData> results) {
		this.results = results;
	}
	
	@PostConstruct
	public void init(){
	int pov=0;
	int neg=0;
	int nue=0;
		for(AnalyzedData data:results){
			if(data.getResult().equalsIgnoreCase("Positive")){
				pov++;
			}else if(data.getResult().equalsIgnoreCase("Negative")){
				neg++;
			}else if(data.getResult().equalsIgnoreCase("Neutral")){
				nue++;
			}
		}
		
	 pie = new StringChartDataModel(ChartDataModel.ChartType.pie);
     pie.put("Positive", pov);
     pie.put("Negative", neg);
     pie.put("Neutral", nue);
     gdp = new LinkedList<GDPRecord>();
     gdp.add(new GDPRecord("Positive", pov));
     gdp.add(new GDPRecord("Negative",neg));
     gdp.add(new GDPRecord("Neutral", nue));
   }
	public StringChartDataModel getPie() {
		return pie;
	}
	public void setPie(StringChartDataModel pie) {
		this.pie = pie;
	}
	
	 public List<GDPRecord> getGdp() {
		return gdp;
	}
	public void setGdp(List<GDPRecord> gdp) {
		this.gdp = gdp;
	}

	public class GDPRecord{
	        private final String result;
	        private final int val;
	        

	        public GDPRecord(String result, int pov) {
	            this.result = result;
	            this.val = pov;
	            
	        }

	        public String getResult() {
	            return result;
	        }

			public int getVal() {
				return val;
			}

	      


	    }

	public boolean getRenderResult() {
		return (results!=null && results.size()>0);
	}
	
	public void reset(ActionEvent event )throws Exception{
		results=new ArrayList<AnalyzedData>();
		init();
		System.out.println("FileuploadBean :: reset");
	}
	

}