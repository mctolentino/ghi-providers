package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Question extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;
	public String question;
	
	public Question(Long id, String question) {
		super();
		this.id = id;
		this.question = question;
	}

	public static Model.Finder<Long, Question> find = new Model.Finder<Long, Question>(
            Long.class, Question.class);

	
}
