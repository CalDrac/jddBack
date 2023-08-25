package com.boffbad.jddVote.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.boffbad.jddVote.model.Jeu;
@RepositoryRestResource
@CrossOrigin(origins = "*")
public interface JeuRepository extends CrudRepository<Jeu,Long>{

	ArrayList<Jeu> findAllByOrderByNomAsc();

	ArrayList<Jeu> findAllByOrderByResultatDesc();

}