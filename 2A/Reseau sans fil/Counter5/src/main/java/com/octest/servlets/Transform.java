package com.octest.servlets;

public class Transform {
	
	void doIt(double[] realIn,
            double[] imagIn,
            double scale,
            double[] realOut,
            double[] imagOut){
		for(int cnt = 0;cnt < realIn.length;cnt++){
		      correctAndRecombine(realIn[cnt],
		                         imagIn[cnt],
		                         cnt,
		                         realIn.length,
		                         scale,
		                         realOut,
		                         imagOut);
		    }//end for loop
		  }//end doIt

	 void correctAndRecombine(double realSample,
             double imagSample,
             int position,
             int length,
             double scale,
             double[] realOut,
             double[] imagOut){
		 //Calculate the complex transform values for
		 // each sample in the complex output series.
		 for(int cnt = 0; cnt < length; cnt++){
			 double angle = (2.0*Math.PI*cnt/length)*position;
			 //Calculate output based on real input
			 realOut[cnt] += realSample*Math.cos(angle)/scale;
			 imagOut[cnt] += realSample*Math.sin(angle)/scale;
			 //Calculate output based on imag input
			 realOut[cnt] -= imagSample*Math.sin(angle)/scale;
			 imagOut[cnt] += imagSample*Math.cos(angle)/scale;
		}//end for loop
		 }//end correctAndRecombine

}//end class transform
