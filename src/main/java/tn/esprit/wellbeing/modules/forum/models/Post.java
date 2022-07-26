package tn.esprit.wellbeing.modules.forum.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import tn.esprit.wellbeing.modules.forum.AbstractForumObject;

@Entity
@Table(name="post")
public class Post extends AbstractForumObject<String>{
	
}
