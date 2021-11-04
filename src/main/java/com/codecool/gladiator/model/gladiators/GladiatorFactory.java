package com.codecool.gladiator.model.gladiators;

import com.codecool.gladiator.util.RandomUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class GladiatorFactory {

    private List<String> names;

    public GladiatorFactory(String fileOfNames) {
        try {
            File file = new File(getClass().getClassLoader().getResource(fileOfNames).getFile());
            names = Files.readAllLines(file.toPath());
        } catch (IOException|NullPointerException e) {
            System.out.println("Names file not found or corrupted!");
            System.exit(1);
        }
    }

    /**
     * Picks a random name from the file given in the constructor
     *
     * @return gladiator name
     */
    private String getRandomName() {
        return RandomUtils.choseOneFromList(names);
    }

    /**
     * Instantiates a new gladiator with random name and type.
     * Creating an Archer, an Assassin, or a Brutal has the same chance,
     * while the chance of creating a Swordsman is the double of the chance of creating an Archer.
     * @return new Gladiator
     */
    public Gladiator generateRandomGladiator() {
        String name = getRandomName();
        int hp = RandomUtils.number(25, 100);
        int sp = RandomUtils.number(25, 100);
        int dex = RandomUtils.number(25, 100);
        int level = RandomUtils.number(1, 5);

        switch (RandomUtils.number(5)) {
            case 0:
                return new Archer(name, hp, sp, dex, level);
            case 1:
                return new Assassin(name, hp, sp, dex, level);
            case 2:
                return new Brutal(name, hp, sp, dex, level);
            default:
                return new Swordsman(name, hp, sp, dex, level);
        }

    }
}
