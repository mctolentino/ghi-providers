package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import play.db.ebean.Model;

@Embeddable
public class WithHistory extends Model {

	private static final long serialVersionUID = 1L;

	@Column(name = "CREUSER")
	public Long createUserId;
	@Column(name = "CREDATE")
	public Date createDate;
	@Column(name = "UPDUSER")
	public Long updateUserId;
	@Column(name = "UPDDATE")
	public Date updateDate;

}
