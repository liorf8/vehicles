package vehicles.environment;


class valueOutOfBounds extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public valueOutOfBounds(String msg){
		super (msg);
	}
	
	public String getMessage(){
		return super.getMessage();
	}

}
