import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name="RANKS")

@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, region="rankcache")
public class Rank implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Rank(){
		
	}
	Rank(Skill s,People sub,People obs,long scr){
		this.skill=s;
		this.subject=sub;
		this.observer=obs;
		this.score=scr;
	}
	@Id
	@Column(name="RANK_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private long id;
	
	 @ManyToOne(optional = false,fetch = FetchType.EAGER)
	    @JoinColumn(name="SKILL_ID")
	    private Skill skill;

	 @ManyToOne(optional = false,fetch = FetchType.EAGER)
	    @JoinColumn(name="SUBJECT_ID")
	    private People subject;
	
	 @ManyToOne(optional = false,fetch = FetchType.EAGER)
	    @JoinColumn(name="OBSERVER_ID")
	    private People observer;
	 
	 @Column(name="SCORE")
	
	 private long score;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public People getSubject() {
		return subject;
	}

	public void setSubject(People subject) {
		this.subject = subject;
	}

	public People getObserver() {
		return observer;
	}

	public void setObserver(People observer) {
		this.observer = observer;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}
	
	
	 
	 
}
