package com.angelos.koinoxrhsta.impl.ws;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.angelos.koinoxrhsta.def.pw.BuildingPw;
import com.angelos.koinoxrhsta.impl.dto.BuildingDTO;
import com.angelos.koinoxrhsta.impl.dto.mappers.BuildingMapper;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersister;
import com.angelos.koinoxrhsta.impl.po.Building;
import com.angelos.koinoxrhsta.impl.po.keys.BuildingKey;

@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET,  RequestMethod.DELETE, RequestMethod.PUT})
@RestController
@RequestMapping("/buildings")
public class BuildingApi {

	BuildingPw buildingPw;
    BuildingMapper mapper;
    GenericPersister<Building, BuildingKey> pst;

    public BuildingApi(BuildingPw buildingPw, BuildingMapper mapper, GenericPersister<Building, BuildingKey> pst) {
        this.buildingPw = buildingPw;
        this.mapper = mapper;
        this.pst = pst;
    }

    @RequestMapping(path = "/findAll", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BuildingDTO>> allBuilding() {

        List<Building> buildings = buildingPw.findAll();
        
        List<BuildingDTO> buildingDTOs = buildings.stream().map(q -> mapper.mapToDto(q)).collect(Collectors.toList());

        return ResponseEntity.ok().body(buildingDTOs);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BuildingDTO> addBuilding(@RequestBody(required = true) BuildingDTO buildingDTO){
       

        System.out.println(buildingDTO);
        Building building = mapper.mapFromDto(buildingDTO);
        buildingPw.save(building);
        buildingDTO = mapper.mapToDto(building); 

        return ResponseEntity.ok().body(buildingDTO);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BuildingDTO> alterBuilding(@RequestBody(required = true) BuildingDTO buildingDTO){
       
        System.out.println(buildingDTO);
        BuildingKey key = new BuildingKey();
        key.setBuildingId(buildingDTO.getBuildingId());
        
        Building building = pst.read(key);
        // Optional<Building> optionalBuilding = buildingPw.findById(key);
        if(/* !optionalBuilding.isPresent() */building == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).header("errorMessage",  "No entry was found.").build();
        }
        
        Building forUpdating = mapper.mapFromDto(buildingDTO);
        forUpdating.setKey(key);
        forUpdating.setLastVersion(/*optionalBuilding.get()*/ building.getLastVersion());
        forUpdating = buildingPw.save(forUpdating);
        buildingDTO = mapper.mapToDto(forUpdating); 

        return ResponseEntity.ok().body(buildingDTO);
    }

    @RequestMapping(path = "/remove", method = RequestMethod.DELETE , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeBuilding(@RequestBody(required = true) BuildingDTO buildingDTO){
        
        BuildingKey key = new BuildingKey();
        BeanUtils.copyProperties(buildingDTO, key);
        Building building = buildingPw.findById(key).get();
        buildingPw.delete(building);

        return ResponseEntity.ok().body("Building  " +  buildingDTO + " deleted");
    }
    
}
