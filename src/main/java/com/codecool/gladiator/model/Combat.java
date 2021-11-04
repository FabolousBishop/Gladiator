package com.codecool.gladiator.model;

import com.codecool.gladiator.model.gladiators.Gladiator;
import com.codecool.gladiator.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Combat class, used for simulating fights between pairs of gladiators
 */
public class Combat {

    private Gladiator gladiator1;
    private Gladiator gladiator2;

    private final List<String> combatLog;

    //Global damage used in combat
    double actualDamage = 0;

    public Combat(Contestants contestants) {
        switch (RandomUtils.number(1)){
            case 0:
                this.gladiator1 = contestants.gladiator1;
                this.gladiator2 = contestants.gladiator2;
            case 1:
                this.gladiator1 = contestants.gladiator1;
                this.gladiator2 = contestants.gladiator2;
        }
        this.combatLog = new ArrayList<>();
    }

    /**
     * Simulates the combat and returns the winner.
     * If one of the opponents is null, the winner is the one that is not null
     * If both of the opponents are null, the return value is null
     *
     * @return winner of combat
     */
    public Gladiator simulate() {
        boolean isDefenderAlive;
        do {
            if (isChanceToHit()){
                damageDealt();
                setLog("hit");
            } else {
                setLog("miss");
            }
            isDefenderAlive = !gladiator2.isDead();
            if (isDefenderAlive)
                swapGladiators();

        } while (isDefenderAlive);

        return gladiator1;
    }

    public Gladiator getGladiator1() {
        return gladiator1;
    }

    public Gladiator getGladiator2() {
        return gladiator2;
    }

    private int chanceToHit() {
        int dexComparison = (int) Math.round(gladiator1.getMaximumDex() - gladiator2.getMaximumDex());

        if (dexComparison <= 10) return 10;
        if (dexComparison >= 100) return 100;

        return dexComparison;
    }
    private boolean isChanceToHit() {
        return RandomUtils.percentageSuccess(chanceToHit());
    }

    private void damageDealt() {
        actualDamage = gladiator1.getMaximumSp() * RandomUtils.number(1, 5)/10;
        gladiator2.decreaseHp(actualDamage);
    }

    private void swapGladiators() {
        Gladiator gladiatorToSwap = gladiator1;
        gladiator1 = gladiator2;
        gladiator2 = gladiatorToSwap;
    }

    private void setLog(String log){
        String attackerName = gladiator1.getName();
        switch (log) {
            case "hit":
                combatLog.add(String.format("%s deals %s damage",
                        attackerName, actualDamage));
                break;
            case "miss":
                combatLog.add(String.format("%s missed", attackerName));
                break;
        }
    }

    public String getCombatLog(String separator) {
        return String.join(separator, combatLog);
    }
}
