package com.boffbad.jddVote.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boffbad.jddVote.model.Jeu;
import com.boffbad.jddVote.model.Partie;
import com.boffbad.jddVote.model.PoidsVote;
import com.boffbad.jddVote.model.Resultat;
import com.boffbad.jddVote.model.Vote;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class VoteController {

	@Autowired
	private VoteRepository voteRepository;
	@Autowired
	private PoidsVoteRepository poidsVoteRepository;
	@Autowired
	private JeuRepository jeuRepository;
	@Autowired
	private JoueurRepository joueurRepository;
	@Autowired
	private PartieRepository partieRepository;

	@GetMapping("/votes")
	public List<Vote> findAllVotes() {

		List<Vote> votes = (List<Vote>) voteRepository.findAll();
		return votes;
	}

	@GetMapping("/vote/id/{id}")
	public List<Vote> findVote(@PathVariable("id") Long id) {

		List<Vote> votes = (List<Vote>) voteRepository.findByIdJoueur(id);
		return votes;
	}

	@PostMapping("/vote/save")
	public Vote insererVote(@RequestBody Vote vote) {
		return voteRepository.save(vote);
	}

	@GetMapping("/vote/summary/famille")
	public List<Resultat> getListeResultatsFamille() {

		List<Resultat> listeResultats = new ArrayList<Resultat>();
		List<Jeu> listJeu = (List<Jeu>) jeuRepository.findAll();
		Map<Long, Resultat> mapResultats = new HashMap<Long, Resultat>();
		for (Jeu jeu : listJeu) {
			if(jeu.getCategorie().equals("Famille")) {
			Resultat r = new Resultat();
			r.setJeu(jeu);
			// calcul des points
			int nbPoints = 0;

			r.setNbPoints(nbPoints);
			listeResultats.add(r);
			mapResultats.put(jeu.getId(), r);
			}
		}

		List<Vote> listVotes = this.findAllVotes();
		// V1 : 1 vote = 1 point
		/*
		 * for (Vote vote : listVotes) { Long idJeu = vote.getIdJeu(); Resultat r =
		 * mapResultats.get(idJeu); r.setNbPoints(r.getNbPoints() + 1);
		 * mapResultats.replace(idJeu, r); }
		 */
		List<PoidsVote> poidsVote = (List<PoidsVote>) poidsVoteRepository.findAll();
		// V2 : poids vote
		for (Vote vote : listVotes) {
			// Récupérer le joueur
			// Compter son nb de parties
			// Associer le poids du vote
			// Ajouter le poids du vote au score du résultat
			// ???
			// Profit
			Long idJeu = vote.getIdJeu();
			Jeu j = jeuRepository.findById(idJeu).get();
			if (j.getCategorie().equals("Famille")) {
				
				Long idJoueur = vote.getIdJoueur();
				List<Partie> listeParties = partieRepository.findByIdJoueur(idJoueur);
				List<Partie> listePartiesParcours = partieRepository.findByIdJoueur(idJoueur);

				for (Partie partie : listePartiesParcours) {
					if (!jeuRepository.findById(partie.getIdJeu()).get().getCategorie().equals("Famille")) {
						listeParties.remove(partie);
					}
				}

				int nbParties = listeParties.size();
				int valeurVote = 1;
				if (nbParties != 0) {
					valeurVote = poidsVote.get(nbParties - 1).getValeurVote();
				}
				
				Resultat r = mapResultats.get(idJeu);
				r.setNbPoints(r.getNbPoints() + valeurVote);
				System.out.println("Jeu : " + j.getNom() + "| valeur : " + valeurVote + "| nouvelle valeur : " + r.getNbPoints());
				mapResultats.replace(idJeu, r);
			}
		}
		listeResultats = new ArrayList<Resultat>(mapResultats.values());
		//save
		for (Resultat r : listeResultats) {
			Jeu j = r.getJeu();
			j.setResultat(new Long(r.getNbPoints()));
			jeuRepository.save(j);
		}
		return listeResultats;
	}
	@GetMapping("/vote/summary/expert")
	public List<Resultat> getListeResultatsExpert() {

		List<Resultat> listeResultats = new ArrayList<Resultat>();
		List<Jeu> listJeu = (List<Jeu>) jeuRepository.findAll();
		Map<Long, Resultat> mapResultats = new HashMap<Long, Resultat>();
		for (Jeu jeu : listJeu) {
			if(jeu.getCategorie().equals("Expert")) {
			Resultat r = new Resultat();
			r.setJeu(jeu);
			// calcul des points
			int nbPoints = 0;

			r.setNbPoints(nbPoints);
			listeResultats.add(r);
			mapResultats.put(jeu.getId(), r);
			}
		}

		List<Vote> listVotes = this.findAllVotes();
		// V1 : 1 vote = 1 point
		/*
		 * for (Vote vote : listVotes) { Long idJeu = vote.getIdJeu(); Resultat r =
		 * mapResultats.get(idJeu); r.setNbPoints(r.getNbPoints() + 1);
		 * mapResultats.replace(idJeu, r); }
		 */
		List<PoidsVote> poidsVote = (List<PoidsVote>) poidsVoteRepository.findAll();
		// V2 : poids vote
		for (Vote vote : listVotes) {
			// Récupérer le joueur
			// Compter son nb de parties
			// Associer le poids du vote
			// Ajouter le poids du vote au score du résultat
			// ???
			// Profit
			Long idJeu = vote.getIdJeu();
			Jeu j = jeuRepository.findById(idJeu).get();
			if (j.getCategorie().equals("Expert")) {
				
				Long idJoueur = vote.getIdJoueur();
				List<Partie> listeParties = partieRepository.findByIdJoueur(idJoueur);
				List<Partie> listePartiesParcours = partieRepository.findByIdJoueur(idJoueur);

				for (Partie partie : listePartiesParcours) {
					if (!jeuRepository.findById(partie.getIdJeu()).get().getCategorie().equals("Expert")) {
						listeParties.remove(partie);
					}
				}

				int nbParties = listeParties.size();
				int valeurVote = 1;
				if (nbParties != 0) {
					valeurVote = poidsVote.get(nbParties - 1).getValeurVote();
				}
				
				Resultat r = mapResultats.get(idJeu);
				r.setNbPoints(r.getNbPoints() + valeurVote);
				System.out.println("Jeu : " + j.getNom() + "| valeur : " + valeurVote + "| nouvelle valeur : " + r.getNbPoints());
				mapResultats.replace(idJeu, r);
			}
		}
		listeResultats = new ArrayList<Resultat>(mapResultats.values());
		for (Resultat r : listeResultats) {
			Jeu j = r.getJeu();
			j.setResultat(new Long(r.getNbPoints()));
			jeuRepository.save(j);
		}
		//save
		return listeResultats;
	}
}
