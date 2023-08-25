package com.boffbad.jddVote.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "poids_vote")
public class PoidsVote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	int nbJeux;
	int valeurVote;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNbJeux() {
		return nbJeux;
	}
	public void setNbJeux(int nbJeux) {
		this.nbJeux = nbJeux;
	}
	public int getValeurVote() {
		return valeurVote;
	}
	public void setValeurVote(int valeurVote) {
		this.valeurVote = valeurVote;
	}
	
}

