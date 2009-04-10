
public class Test {

	/**A main class for testing of various elements in the program.
	 * Please DON'T upload binary files produced by compiling this into the repo.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		/*Testing creating and editing a vehicle xml entry*/
			EditorVehicle v = new EditorVehicle("hungry.xml");
			v.addVehicleName("Hungry Vehicle");
			v.serialiseXMLDoc();
	}

}
