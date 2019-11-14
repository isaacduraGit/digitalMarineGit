package org.marineDigitalJournal;

import org.marineDigitalJournal.neuralnet.Assign_Var;
import org.marineDigitalJournal.neuralnet.Thread_Var;

public class SSCCE_main {

	public static String limit_CHL = "";

	public static void main(String[] args) {

		Thread_Var thread_Var=new  Thread_Var();
		
		thread_Var.launch();
		

		if (limit_CHL.equals("Test")) {

			System.out.print("test" + limit_CHL);
		} else {
			System.out.print(limit_CHL);
		}

	}

}
