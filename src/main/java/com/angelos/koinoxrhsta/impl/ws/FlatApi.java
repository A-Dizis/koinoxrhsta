package com.angelos.koinoxrhsta.impl.ws;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.angelos.koinoxrhsta.def.pw.FlatPw;
import com.angelos.koinoxrhsta.impl.dto.FlatDTO;
import com.angelos.koinoxrhsta.impl.dto.mappers.FlatMapper;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;

@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET,  RequestMethod.DELETE, RequestMethod.PUT})
@RestController
@RequestMapping("/flats")
public class FlatApi {

    FlatPw flatPw;
    FlatMapper mapper;

    public FlatApi(FlatPw flatPw, FlatMapper mapper) {
        this.flatPw = flatPw;
        this.mapper = mapper;
    }

    @RequestMapping(path = "/findAll", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FlatDTO>> allFlat() {

        List<Flat> flats = flatPw.findAll();

        List<FlatDTO> flatDTOs = flats.stream().map(q -> mapper.mapToDto(q)).collect(Collectors.toList());

        return ResponseEntity.ok().body(flatDTOs);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FlatDTO> addFlat(@RequestBody(required = true) FlatDTO flatDTO) {

        System.out.println(flatDTO);
        Flat flat = mapper.mapFromDto(flatDTO);
        flatPw.save(flat);
        flatDTO = mapper.mapToDto(flat);

        return ResponseEntity.ok().body(flatDTO);
    }

    @RequestMapping(path = "/remove", method = RequestMethod.DELETE , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeFlat(@RequestBody(required = true) FlatDTO flatDTO) {

        FlatKey key = new FlatKey();
        BeanUtils.copyProperties(flatDTO, key);
        Flat flat = flatPw.findById(key).get();
        flatPw.delete(flat);

        return ResponseEntity.ok().body("Flat deleted");
    }

}
