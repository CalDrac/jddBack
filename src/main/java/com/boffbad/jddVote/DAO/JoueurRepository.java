package com.boffbad.jddVote.DAO;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.repository.CrudRepository;
import com.boffbad.jddVote.model.Joueur;

@RepositoryRestResource
@CrossOrigin(origins = "*")
public interface JoueurRepository extends CrudRepository<Joueur,Long>{

}
