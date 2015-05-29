package com.dw;

import java.util.*;

/**
 *
 * @author conceptGO/Joe Sam Shirah
 */
public class dwRF4D1Handler 
{ 
  private static ResourceBundle rbText = ResourceBundle.getBundle( "dwRF4Demo1" ); 

  private Date dSelected;
  private List<String> lOrderBySource = new ArrayList<String>(), 
                       lOrderByTarget = new ArrayList<String>(); 
  private String sCity,
                 sSuccess = "dwRFSuccess"; 

    
  public dwRF4D1Handler()
  { 
    lOrderBySource.add( rbText.getString("age") ); 
    lOrderBySource.add( rbText.getString("city") ); 
    lOrderBySource.add( rbText.getString("county") ); 
    lOrderBySource.add( rbText.getString("firstName") ); 
    lOrderBySource.add( rbText.getString("gender") ); 
    lOrderBySource.add( rbText.getString("lastName") ); 
    lOrderBySource.add( rbText.getString("region") ); 
    lOrderBySource.add( rbText.getString("state") ); 

  } // end constructor

  public String process()
  {
    return sSuccess;
  } 

  public String processResult()
  {
    return sSuccess;
  } 
    
// Getters and Setters 

  public String getCity()
  {
    return sCity;
  }
 
  public List getOrderBySource()
  {
    return lOrderBySource;
  }

  public List getOrderByTarget()
  {
    return lOrderByTarget;
  }

  public Date getSelectedDate()
  {
    return dSelected; 
  } 

  public TimeZone getTimeZone()
  {
    return TimeZone.getDefault(); 
  } 

  public void setCity(String city)
  {
    sCity = city;
  }

  public void setOrderBySource( List<String> orderBySource  )
  {
    lOrderBySource = orderBySource;
  }

  public void setOrderByTarget(List<String> orderByTarget )
  {
    lOrderByTarget = orderByTarget; 
  }

  public void setSelectedDate(Date date)
  {
    dSelected = date;
  }

} // end class dWRF4D1Handler
