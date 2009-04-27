package test.UtilMethods;

import vehicles.util.*;

public class UtilMethodsTest {
	public static void main(String[] args){
		UtilMethods.resetXML();
		UtilMethods.deleteFile("xml/environments/default_environment1.env");
	}
}
