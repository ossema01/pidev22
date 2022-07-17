package tn.esprit.wellbeing.modules.forum.exception;

public class ObjectNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(Long id) {
		super("No Forum object found with the id: "+id);
	}
}
