package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
public class SecurityQuestion extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    public Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    public Member member;
    public String question;
    public String answer;

    public SecurityQuestion() {
    }

    public SecurityQuestion(Member member, String question, String answer) {
        super();
        this.member = member;
        this.question = question;
        this.answer = answer;
    }

    public static Model.Finder<Long, SecurityQuestion> find = new Model.Finder<Long, SecurityQuestion>(Long.class, SecurityQuestion.class);

}
