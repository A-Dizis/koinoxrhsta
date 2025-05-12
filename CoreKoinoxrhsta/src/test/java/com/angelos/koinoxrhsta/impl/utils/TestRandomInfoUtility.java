package com.angelos.koinoxrhsta.impl.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestRandomInfoUtility {

    /**
     * First names.
     */
    private static List<String> firstNames = new ArrayList<>();

    /**
     * Last names.
     */
    private static List<String> lastNames = new ArrayList<>();

    /**
     * Street names.
     */
    private static List<String> streetNames = new ArrayList<>();

    /**
     * Random generator.
     */
    private static Random random = new Random();

    static{
        firstNames.add("Petros");
        firstNames.add("Ioannis");
        firstNames.add("Giorgos");
        firstNames.add("Nikolaos");
        firstNames.add("Markos");
        firstNames.add("Fillipos");
        firstNames.add("Marios");
        firstNames.add("Evaggelos");
        firstNames.add("Maria");
        firstNames.add("Ioanna");
        firstNames.add("Margarita");

        lastNames.add("Georgiou");
        lastNames.add("Alexiou");
        lastNames.add("Nikolaou");
        lastNames.add("Panagiotou");
        lastNames.add("Evangelou");
        lastNames.add("Papantoniou");
        lastNames.add("Xristodoulou");
        lastNames.add("Dimitriou");
        lastNames.add("Zalou");
        lastNames.add("Memet");

        streetNames.add("Kondriktonos");
        streetNames.add("Kolokotroni");
        streetNames.add("Basileos Kostantinou");
        streetNames.add("Oulof Palme");
        streetNames.add("Adamantos");
        streetNames.add("Platonos");
        streetNames.add("Amarousiou");
        streetNames.add("Lidias");
        streetNames.add("Solonos");
        streetNames.add("Romanou");
    }

    /**
     * @return random first name
     */
    public static String getFirstName() {
        return firstNames.get(random.nextInt(firstNames.size()));
    }

    /**
     * @return random last name
     */
    public static String getLastName() {
        return lastNames.get(random.nextInt(lastNames.size()));
    }

    /**
     * @return random street name
     */
    public static String getStreetName() {
        return streetNames.get(random.nextInt(streetNames.size()));
    }

    public static LocalDate randomDate(LocalDate from, LocalDate to) {
        return LocalDate.ofEpochDay(ThreadLocalRandom.current().nextLong(from.toEpochDay(), to.toEpochDay()));
    }

    public static LocalDate randomDate(Date from, Date to) {
        return LocalDate.from(Instant.ofEpochMilli(ThreadLocalRandom.current().nextLong(from.getTime(), to.getTime())));
    }

    /**
     * return {@link random}.
     */
    public static Random getRandom() {
        return random;
    }
}
