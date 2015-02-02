package com.kirbybanman.travelclaimer;

import android.text.InputFilter;
import android.text.Spanned;

/*
 * Currency character sequence filter adapted from
 * 	http://stackoverflow.com/questions/5357455/limit-decimal-places-in-android-edittext
 * by user
 * 	peceps
 * on
 * 	1 Feb 2015
 */
public class CurrencyInputFilter implements InputFilter {

	  public CurrencyInputFilter() { }

	  @Override
	  public CharSequence filter(CharSequence source,
	      int start,
	      int end,
	      Spanned dest,
	      int dstart,
	      int dend) {


	    int dotPos = -1;
	    int len = dest.length();
	    for (int i = 0; i < len; i++) {
	      char c = dest.charAt(i);
	      if (c == '.' || c == ',') {
	        dotPos = i;
	        break;
	      }
	    }
	    if (dotPos >= 0) {

	      // do not accept >1 dots
	      if (source.equals(".") || source.equals(","))
	      {
	          return "";
	      }
	      
	      // if the text is entered before the dot, who cares
	      if (dend <= dotPos) {
	        return null;
	      }
	      
	      // do not ollow >2 decimal accuracy
	      if (len - dotPos > 2) {
	    	  return "";
	      }
	    } else if ((source.equals(".") || source.equals(",")) && 
	    			len - dend > 2) {
	    	return "";
	    }

	    return null;
	  }


}
