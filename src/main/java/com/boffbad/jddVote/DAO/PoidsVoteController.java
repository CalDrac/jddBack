package com.boffbad.jddVote.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boffbad.jddVote.model.Joueur;
import com.boffbad.jddVote.model.Partie;
import com.boffbad.jddVote.model.PoidsVote;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PoidsVoteController {

	@Autowired
	private PoidsVoteRepository poidsVoteRepository;

	@GetMapping("/poidsvote")
	public List<PoidsVote> findPoidsVote() {
		return (List<PoidsVote>) poidsVoteRepository.findAll();
	}
	
}
