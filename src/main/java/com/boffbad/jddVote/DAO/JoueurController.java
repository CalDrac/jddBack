package com.boffbad.jddVote.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boffbad.jddVote.model.Joueur;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class JoueurController {

	@Autowired
	private JoueurRepository joueurRepository;

	@GetMapping("/joueurs")
	public List<Joueur> findJoueurs() {

		List<Joueur> users = (List<Joueur>) joueurRepository.findAll();
		return users;
	}
	
	@GetMapping("/joueur/id/{id}")
	public Joueur findJoueurById(@PathVariable("id") Long id) {

		Optional<Joueur> findById = joueurRepository.findById(id);
		
		Joueur joueur =null;
		
		if(findById.isPresent()) {
			joueur = findById.get();
		}
		return joueur;

	}
	
	@PostMapping("/joueur/save")
	public Joueur insererJoueur(@RequestBody Joueur joueur) {
		 return joueurRepository.save(joueur);
	}
	
}
