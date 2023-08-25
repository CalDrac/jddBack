package com.boffbad.jddVote.DAO;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boffbad.jddVote.model.Jeu;
import com.boffbad.jddVote.model.Property;
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PropertyController {
	@Autowired
	private PropertyRepository propertyRepository;
	@GetMapping("/properties")
	public List<Property> findJeux() {
		return (List<Property>) propertyRepository.findAll();
	}
	
	@GetMapping("/property/name/{name}")
	public Property findPropertyByName(@PathVariable("name") String name) {
		return propertyRepository.findPropertyByNom(name);

	}

	@PutMapping("/property/update/{name}")
	public Property updateProperty(@PathVariable("name") String name,@RequestBody Property prop) {

		return propertyRepository.save(prop);

	}

}

