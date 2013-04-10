package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
public class SecurityQuestion extends Model {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	public Member member;
	public String question;
	public String answer;
	
}
