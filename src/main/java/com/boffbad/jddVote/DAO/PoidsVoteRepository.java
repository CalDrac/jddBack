package com.boffbad.jddVote.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.boffbad.jddVote.model.Partie;
import com.boffbad.jddVote.model.PoidsVote;

@RepositoryRestResource
@CrossOrigin(origins = "*")
public interface PoidsVoteRepository extends CrudRepository<PoidsVote, Long> {

}
