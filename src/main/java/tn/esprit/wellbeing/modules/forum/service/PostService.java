package tn.esprit.wellbeing.modules.forum.service;

import tn.esprit.wellbeing.modules.forum.models.Post;
import tn.esprit.wellbeing.modules.userManagement.user.entity.User;

public interface PostService extends AbstractForumObjectService<Post> {

	Post[] findByCreatedBy(String username);
}
