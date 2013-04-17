package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.db.ebean.Model;

@Entity
public class SearchLog extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    public Integer id;
    @OneToOne(fetch = FetchType.EAGER)
    public Member member;
    public Date searchDate;

    public SearchLog() {
    }

    public SearchLog(Member member, Date searchDate) {
        super();
        this.member = member;
        this.searchDate = searchDate;
    }

    public static List<SearchLog> findLatestSearches() {
        List<SearchLog> sl = find.fetch("member")
                                 .order("searchDate desc")
                                 .findList();
        return sl;
    }

    public static Model.Finder<Long, SearchLog> find = new Model.Finder<Long, SearchLog>(Long.class, SearchLog.class);

}
