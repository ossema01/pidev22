package tn.esprit.wellbeing.modules.forum;

public class AbstractAnonymousForumObject<T> extends AbstractForumObject<T> {

	public static final String ANONYMOUS_CREATOR = "This post is created anonymously";

	@Override
	public String getCreatedBy() {
		return ANONYMOUS_CREATOR;
	}

}
