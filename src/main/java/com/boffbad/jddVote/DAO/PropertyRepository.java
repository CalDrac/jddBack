package com.boffbad.jddVote.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.boffbad.jddVote.model.Jeu;
import com.boffbad.jddVote.model.Property;

@RepositoryRestResource
@CrossOrigin(origins = "*")
public interface PropertyRepository extends CrudRepository<Property,Long>{

	Property findPropertyByNom(String name);

}
