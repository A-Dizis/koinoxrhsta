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

import com.angelos.koinoxrhsta.impl.dto.BuildingDTO;
import com.angelos.koinoxrhsta.impl.dto.mappers.BuildingMapper;
import com.angelos.koinoxrhsta.impl.exception.RepositoryException;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersister;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersisterFactory;
import com.angelos.koinoxrhsta.impl.po.Building;
import com.angelos.koinoxrhsta.impl.po.keys.BuildingKey;

@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET,  RequestMethod.DELETE, RequestMethod.PUT})
@RestController
@RequestMapping("/buildings")
public class BuildingApi {

    BuildingMapper mapper;
    GenericPersisterFactory gpFactory;
    GenericPersister<Building, BuildingKey> gpFlat;

    public BuildingApi(BuildingMapper mapper, GenericPersisterFactory gpFactory) throws RepositoryException {
        this.mapper = mapper;
        this.gpFactory = gpFactory;

        gpFlat = gpFactory.create(Building.class);
    }

    @RequestMapping(path = "/findAll", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BuildingDTO>> allBuilding() throws RepositoryException {

        List<Building> buildings = gpFlat.findAll();
        
        List<BuildingDTO> buildingDTOs = buildings.stream().map(q -> mapper.mapToDto(q)).collect(Collectors.toList());

        return ResponseEntity.ok().body(buildingDTOs);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BuildingDTO> addBuilding(@RequestBody(required = true) BuildingDTO buildingDTO) throws RepositoryException{
       
        Building building = mapper.mapFromDto(buildingDTO);
        gpFlat.save(building);
        buildingDTO = mapper.mapToDto(building); 

        return ResponseEntity.ok().body(buildingDTO);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BuildingDTO> alterBuilding(@RequestBody(required = true) BuildingDTO buildingDTO) throws RepositoryException{
       
        Building building = mapper.mapFromDto(buildingDTO);
        try {
            gpFlat.update(building);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        buildingDTO = mapper.mapToDto(building); 

        return ResponseEntity.ok().body(buildingDTO);
    }

    @RequestMapping(path = "/remove", method = RequestMethod.DELETE , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeBuilding(@RequestBody(required = true) BuildingDTO buildingDTO){
        
        Building building = mapper.mapFromDto(buildingDTO);
        building = gpFlat.read(building);
        gpFlat.delete(building);

        return ResponseEntity.ok().body("Building  " +  buildingDTO + " deleted");
    }
    
}
