package com.octest.servlets;
import com.octest.servlets.Transform;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;

import org.apache.commons.*;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.*;
import org.apache.commons.math4.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

import org.apache.commons.math3.complex.Complex;

import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;


//import org.apache.commons.math3.transform.TransformType;




/**
 * Servlet implementation class Counter
 */
public class Counter5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int SLIDING_WINDOW = 128;
	
    private static final double SAMPLING_PERIOD = 10240; // 50ms
    
    public static ArrayList<Double> iStep = new ArrayList<Double>();
	
	final static ArrayList<Float> data = new ArrayList<Float>();
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Counter5() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String a = request.getParame();		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Récupérer les données des capteurs depuis la requête
		
		Map<String, String[]> requestParams = request.getParameterMap();
		
		
		
		
        String Z = request.getParameter("z");
        //String T = request.getParameter("t");
        

        // Effectuer le traitement des données pour calculer le nombre de pas
        
        float zfloat = Float.parseFloat(Z);
        //long time = Long.parseLong(T);
        
        data.add(zfloat);
        
        
        
        if (data.size() % SLIDING_WINDOW == 0) {
            double iThStep = countStep(data);
            iStep.add(iThStep);
            if (iThStep == 0) {
                data.clear();
            }
        }
        //clearConsole();
        
        double sum = 0;
        for(Double d : iStep) {
        	sum += d;
        }
        
        sum = sum / 10000;
        //System.out.println("Step Count: " + (int)sum);
        
        
        // Envoyer la réponse avec le résultat du calcul
        //response.setContentType("text/plain");
        response.getWriter().write(String.valueOf((int)sum));
        
        //response.getWriter().write((longueur));

	}
	
	/*private double countStep1(ArrayList<Float> gWindow) {
		
		Transform transform = new Transform();
		
		// Convert the ArrayList data to a float array
	    double[] realIn = new double[gWindow.size()];
	    double[] imagIn = new double[gWindow.size()];
	    double[] realOut = new double[gWindow.size()];
	    double[] imagOut = new double[gWindow.size()];
	    for (int i = 0; i < gWindow.size(); i++) {
	        realIn[i] = gWindow.get(i);
	        imagIn[i] = 0;
	    }
	    
	    transform.doIt(realIn,imagIn,2.0,realOut,imagOut);
		
	    int cadance = (int) calculateFundamentalFrequency(realOut);
	    System.out.println(cadance);
	    
	    double stepCount = (cadance * SAMPLING_PERIOD);
		
		return stepCount;
	}*/
	
	
private double countStep(ArrayList<Float> data) {
		
	FastFourierTransformer fftTransformer = new FastFourierTransformer(DftNormalization.STANDARD);
	double[] datadouble = new double[data.size()];
	for (int i = 0; i < data.size(); i++) {
        datadouble[i] = (data.get(i)); 
	}
        
    Complex[] transformedData = fftTransformer.transform(datadouble, TransformType.FORWARD);
    
    double[] magnitudes = new double[transformedData.length / 2];
    
    for (int i = 0; i < magnitudes.length; i++) {
        magnitudes[i] = transformedData[i].abs();
    }
    
    int cadance = (int) calculateFundamentalFrequency(magnitudes);
    //System.out.println(cadance);
    
    double stepCount =  (cadance * SAMPLING_PERIOD);
    
    return stepCount;
    
    }
			
                
	private double calculateFundamentalFrequency(double[] fftMagnitude) {
		int maxIndex = 1;
        double maxValue = fftMagnitude[1];
        for (int i = 1; i < fftMagnitude.length; i++) {
        	System.out.println(fftMagnitude[i]);
        	
        	if (fftMagnitude[i] > maxValue) {
        		maxValue = fftMagnitude[i];
                maxIndex = i;
                }
             }
        System.out.println("###################################################");
        System.out.println ("la valeur maximal est : " + maxValue);
        System.out.println ("l'indice maximal est : " + maxIndex);
        System.out.println("###################################################");

        return maxValue/fftMagnitude.length;
    }
}
	
    
	
	



