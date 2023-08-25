package com.boffbad.jddVote.DAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boffbad.jddVote.model.Jeu;
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class JeuController {
	@Autowired
	private JeuRepository jeuRepository;
	@GetMapping("/jeux")
	public List<Jeu> findJeux() {
		List<Jeu> jeux = (List<Jeu>) jeuRepository.findAllByOrderByNomAsc();
		return jeux;
	}
	@GetMapping("/jeux/{categorie}/{forVotes}")
	public List<Jeu> findJeuxByCategorie(@PathVariable("categorie") String categorie, @PathVariable("forVotes") boolean forVotes) {

		ArrayList<Jeu> jeux;
		List<Jeu> jeuxParcours;
		if (!forVotes) {
			jeux = jeuRepository.findAllByOrderByNomAsc();
			jeuxParcours = (List<Jeu>) jeuRepository.findAllByOrderByNomAsc();
		} else {
			jeux = jeuRepository.findAllByOrderByResultatDesc();
			Collections.sort(jeux, new Comparator<Jeu>() {

				@Override
				public int compare(Jeu j1, Jeu j2) {
					// TODO Auto-generated method stub
					return (int)(j2.getResultat() - j1.getResultat());
				}

			});
			jeuxParcours = (List<Jeu>) jeuRepository.findAllByOrderByResultatDesc();
		}
		for (Jeu jeu : jeuxParcours) {
			if (!jeu.getCategorie().equals(categorie)) {
				jeux.remove(jeu);
			}
		}
		if(forVotes) {
			jeuxParcours.clear();
			jeuxParcours.addAll(jeux);
			jeux.clear();
			jeux.add(jeuxParcours.get(0));
			jeux.add(jeuxParcours.get(1));
			jeux.add(jeuxParcours.get(2));
			jeux.add(jeuxParcours.get(3));
			jeux.add(jeuxParcours.get(4));

		}
		return jeux;
	}

	@GetMapping("/jeu/id/{id}")
	public Jeu findJeuById(@PathVariable("id") Long id) {

		Optional<Jeu> optJeu = jeuRepository.findById(id);
		Jeu jeu = null;

		if (optJeu.isPresent()) {
			jeu = optJeu.get();

		}
		return jeu;
	}
		
}