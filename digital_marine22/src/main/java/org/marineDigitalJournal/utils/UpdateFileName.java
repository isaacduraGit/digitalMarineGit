package org.marineDigitalJournal.utils;

import java.io.File;

public class UpdateFileName {
	
	
	
	
		   public static void main(String[] args) {      
		      File f = null;
		      File f1 = null;
		      String v, v1;
		      boolean bool = false;
		      
		      try {
		         // create new files
		         f = new File("C:\\data.nc");
		         f1 = new File("C:\\Program Files");
		         
		         // get file name or directory name
		         v = f.getName();
		         v1 = f1.getName();
		         
		         // true if the file path exists
		         bool = f.exists();
		         
		         // if file exists
		         if(bool) {
		         
		            // prints
		            System.out.println("File name: "+v);
		         }
		         
		         // true if the directory exists
		         bool = f1.exists();
		         
		         // if the folder exists
		         if(bool) {
		         
		            // prints
		            System.out.print("Folder name: "+v1);
		         }
		         
		      } catch(Exception e) {
		         // if any error occurs
		         e.printStackTrace();
		      }
		   }
		}


