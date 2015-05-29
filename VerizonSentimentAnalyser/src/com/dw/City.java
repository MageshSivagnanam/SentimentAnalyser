package com.dw;

import java.util.*;

/**
 *
 * @author conceptGO/Joe Sam Shirah
 */
public class City
{
  private static ResourceBundle rbText = ResourceBundle.getBundle( "dwRF4Demo1" ); 
  
  private List   lA, 
                 lEmpty, 
                 lJ, 
                 lMerged; 

  public City()
  {
    String[] asCityA = rbText.getString("citiesA").split(":");
    String[] asCityJ = rbText.getString("citiesJ").split(":");
    String[] asMerged = new String[ asCityA.length + asCityJ.length ]; 

    System.arraycopy(asCityA, 0, asMerged, 0, asCityA.length);
    System.arraycopy(asCityJ, 0, asMerged, asCityA.length, asCityJ.length); 
    lMerged = Arrays.asList(asMerged); 

    lA = lMerged.subList( 0, asCityA.length ); 
    lJ = lMerged.subList( asCityA.length, asMerged.length ); 

    lEmpty = Arrays.asList(new String[0]); 
  }

  public List suggest(String begin) 
  {
    ArrayList<String> alResult = null;
    char c; 
    Iterator iterator = null;
    String sKeyed = begin.trim();

    if( sKeyed.length() == 0 )
    { return lMerged; }

    sKeyed = sKeyed.toLowerCase(); 
    c = sKeyed.charAt(0); 
    if(c != 'a' && c != 'j')
    { return lEmpty; }

    alResult = new ArrayList<String>(); 
        
    if( c == 'a' )
    { iterator = lA.iterator(); }
    else
    { iterator = lJ.iterator(); }
        
    while (iterator.hasNext()) 
    {
      String sElem = (String)iterator.next();
      if (sElem.toLowerCase().startsWith(sKeyed) )
      { alResult.add(sElem); }
    }
    return alResult;
  } // end suggest

public static void main( String[] args )
{
   new City(); 
   System.exit(0); 
}
    
} // end class City
