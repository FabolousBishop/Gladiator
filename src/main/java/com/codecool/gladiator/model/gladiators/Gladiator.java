package com.codecool.gladiator.model.gladiators;

public abstract class Gladiator {

    private final String name;
    private final int baseHp;
    private final int baseSp;
    private final int baseDex;
    private double currentHp;
    private int level;


    /**
     * Constructor for Gladiators
     *
     * @param name the gladiator's name
     * @param baseHp the gladiator's base Health Points
     * @param baseSp the gladiator's base Strength Points
     * @param baseDex the gladiator's base Dexterity Points
     * @param level the gladiator's starting Level
     */
    public Gladiator(String name, int baseHp, int baseSp, int baseDex, int level) {
        this.name = name;
        this.baseHp = baseHp;
        this.baseSp = baseSp;
        this.baseDex = baseDex;
        this.level = level;
        currentHp = getMaximumHp();
    }

    /**
     * @return HP multiplier of the gladiator subclass
     */
    protected abstract Multiplier getHpMultiplier();

    /**
     * @return SP multiplier of the gladiator subclass
     */
    protected abstract Multiplier getSpMultiplier();

    /**
     * @return DEX multiplier of the gladiator subclass
     */
    protected abstract Multiplier getDexMultiplier();

    private double statisticCalculator(int baseStatistic, double statisticMultiplier){
        return baseStatistic * statisticMultiplier * level;
    }

    public double getMaximumHp() {
        return statisticCalculator(baseHp, getHpMultiplier().getValue());
    }

    public double getMaximumSp() {
        return statisticCalculator(baseHp, getSpMultiplier().getValue());
    }

    public double getMaximumDex() {
        return statisticCalculator(baseHp, getDexMultiplier().getValue());
    }

    public void decreaseHp(double damage) {
         currentHp = currentHp - damage;
    }

    public boolean isDead() {
        //true when HP <= 0
        return currentHp <= 0;
    }

    /**
     * @return Gladiator's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the full name of the gladiator
     * assembled by the subtype and the name
     * (e.g. "Brutal Brutus" or "Archer Leo")
     *
     * @return the full name
     */
    public String getFullName() {
        //first argument is just name of the Gladiator or it's Subclass
        return
                String.format("%s %s", this.getClass().getSimpleName(), name);
    }

    public int getLevel() {
        return level;
    }

    public void levelUp() {
        level++;
    }

    public enum Multiplier {
        Low(0.75),
        Medium(1.0),
        High(1.25);

        private final double value;

        Multiplier(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }
    }

}
