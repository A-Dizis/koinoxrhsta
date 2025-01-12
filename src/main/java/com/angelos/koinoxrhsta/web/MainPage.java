package com.angelos.koinoxrhsta.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.angelos.koinoxrhsta.impl.exception.MapperException;
import com.angelos.koinoxrhsta.impl.exception.RepositoryException;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericMapperFactory;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersister;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersisterFactory;
import com.angelos.koinoxrhsta.impl.po.Building;
import com.angelos.koinoxrhsta.impl.po.keys.BuildingKey;

@Controller
@SuppressWarnings("unused")
public class MainPage {

    private GenericMapperFactory gmFactory;
    private GenericPersisterFactory gpFactory;
    private GenericPersister<Building, BuildingKey> gpBuilding;

    public MainPage(GenericPersisterFactory gpFactory) throws RepositoryException, MapperException {
        this.gpFactory = gpFactory;

        gpBuilding = gpFactory.create(Building.class);
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("buildings", gpBuilding.findAll());
        return "index";
    }

}
