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

import com.angelos.koinoxrhsta.def.pw.OwnerPw;
import com.angelos.koinoxrhsta.impl.dto.OwnerDTO;
import com.angelos.koinoxrhsta.impl.dto.mappers.OwnerMapper;
import com.angelos.koinoxrhsta.impl.po.Owner;
import com.angelos.koinoxrhsta.impl.po.keys.OwnerKey;

@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET,  RequestMethod.DELETE, RequestMethod.PUT})
@RestController
@RequestMapping("/owners")
public class OwnerApi {

    OwnerPw ownerPw;
    OwnerMapper mapper;

    public OwnerApi(OwnerPw ownerPw, OwnerMapper mapper) {
        this.ownerPw = ownerPw;
        this.mapper = mapper;
    }


    @RequestMapping(path = "/findAll", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OwnerDTO>> allOwners() {

        List<Owner> owners = ownerPw.findAll();

        List<OwnerDTO> ownerDTOs = owners.stream().map(q -> mapper.mapToDto(q)).collect(Collectors.toList());

        return ResponseEntity.ok().body(ownerDTOs);
    }

    @RequestMapping(path = "/remove", method = RequestMethod.DELETE , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeOwner(@RequestBody(required = true) OwnerDTO ownerDTO) {

        OwnerKey key = new OwnerKey();
        BeanUtils.copyProperties(ownerDTO, key);
        Owner owner = ownerPw.findById(key).get();
        ownerPw.delete(owner);

        return ResponseEntity.ok().body("Owner deleted" + ownerDTO);
    }
}
