package com.angelos.koinoxrhsta.impl.ws;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.angelos.koinoxrhsta.impl.dto.FlatDTO;
import com.angelos.koinoxrhsta.impl.dto.mappers.FlatMapper;
import com.angelos.koinoxrhsta.impl.exception.RepositoryException;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersister;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersisterFactory;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;

@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET,  RequestMethod.DELETE, RequestMethod.PUT})
@RestController
@RequestMapping("/flats")
public class FlatApi {

    FlatMapper mapper;
    GenericPersisterFactory gpFactory;
    GenericPersister<Flat, FlatKey> gpFlat;

    public FlatApi(GenericPersisterFactory gpFactory, FlatMapper mapper) throws RepositoryException {
        this.gpFactory = gpFactory;
        this.mapper = mapper;

        gpFlat = gpFactory.create(Flat.class);
    }

    @RequestMapping(path = "/findAll", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FlatDTO>> allFlat() {

        List<Flat> flats = gpFlat.findAll();

        List<FlatDTO> flatDTOs = flats.stream().map(q -> mapper.mapToDto(q)).collect(Collectors.toList());

        return ResponseEntity.ok().body(flatDTOs);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FlatDTO> addFlat(@RequestBody(required = true) FlatDTO flatDTO) {

        Flat flat = mapper.mapFromDto(flatDTO);
        gpFlat.save(flat);
        flatDTO = mapper.mapToDto(flat);

        return ResponseEntity.ok().body(flatDTO);
    }

    @RequestMapping(path = "/remove", method = RequestMethod.DELETE , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeFlat(@RequestBody(required = true) FlatDTO flatDTO) {

        Flat flat = mapper.mapFromDto(flatDTO);
        gpFlat.read(flat);
        gpFlat.delete(flat);

        return ResponseEntity.ok().body("Flat deleted");
    }

}
