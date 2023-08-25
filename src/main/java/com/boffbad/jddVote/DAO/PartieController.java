package com.boffbad.jddVote.DAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boffbad.jddVote.model.Jeu;
import com.boffbad.jddVote.model.Partie;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PartieController {

	@Autowired
	private PartieRepository partieRepository;

	@Autowired
	private JeuRepository jeuRepository;

	@PostMapping("/partie/save")
	public Partie inserPartie(@RequestBody String params) {
		System.out.println(params);
		JSONObject json = new JSONObject(params);
		JSONObject jsonPartie = json.getJSONObject("partie");
		JSONArray idList = jsonPartie.getJSONArray("listeIdJoueurs");
		String idJeu = jsonPartie.getString("idJeu");
		String[] arr = new String[idList.length()];

		List<Partie> listePartie = new ArrayList<Partie>();
		Partie p = null;
		for (int i = 0; i < idList.length(); i++) {
			arr[i] = idList.getString(i);
			Partie checkPartie = partieRepository.findByIdJeuAndIdJoueur(Long.parseLong(idJeu), Long.parseLong(idList.getString(i)));
			if (checkPartie == null) {
				if (arr[i].trim() != "") {
					p = new Partie();
					p.setIdJeu(Long.parseLong(idJeu));
					p.setIdJoueur(Long.parseLong(arr[i]));
					partieRepository.save(p);
				}
			}
		}

		return p;
	}

	@GetMapping("/partie/{id}")
	public List<Partie> findPartieByJoueurId(@PathVariable("id") Long id) {
		List<Partie> partiesForJoueur = partieRepository.findByIdJoueur(id);
		return partiesForJoueur;

	}

	@GetMapping("/partie/count/{idJoueur}/{type}")
	public int countPartiesForJoueur(@PathVariable("idJoueur") Long idJoueur, @PathVariable("type") String type) {
		int playedOfType = 0;
		List<Partie> partieList = this.findPartieByJoueurId(idJoueur);
		for (Partie partie : partieList) {
			Jeu j = jeuRepository.findById(partie.getIdJeu()).get();
			if (type != null && j.getCategorie() != null && type.toUpperCase().equals(j.getCategorie().toUpperCase())) {
				playedOfType++;
			}
		}
		return playedOfType;

	}
	
	@GetMapping("/partie/max")
	public long findMaxPartiePlayed() {
		long nbPartiesMax = 0;
		Object [][] test = partieRepository.groupByIdJoueur();
		
		List<Object[]> objList =  Arrays.asList(test);
		Collections.sort(objList,new Comparator<Object[]>() {

			@Override
			public int compare(Object[] o1, Object[] o2) {
				// TODO Auto-generated method stub
				return (int)((Long)o2[1] - (Long)o1[1]);
			}
		});
		nbPartiesMax = (Long) objList.get(0)[1];
		return nbPartiesMax;
	}
}
