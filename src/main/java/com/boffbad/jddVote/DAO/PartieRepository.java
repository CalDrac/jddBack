package com.boffbad.jddVote.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.boffbad.jddVote.model.Partie;

@RepositoryRestResource
@CrossOrigin(origins = "*")
public interface PartieRepository extends CrudRepository<Partie, Long> {

	List<Partie> findByIdJoueur(Long id);

	Partie findByIdJeuAndIdJoueur(long l, long m);

	
	/*@Query("select max(nb) from(" + 
			"SELECT idJoueur,count(*) as nb FROM jdd.partie" + 
			"group by idJoueur) as tmp")
	public int getMaxPartiesPlayed();*/
	
	@Query("select idJoueur, count(p.idJoueur) from com.boffbad.jddVote.model.Partie p group by p.idJoueur")
	Object[][] groupByIdJoueur();
}
