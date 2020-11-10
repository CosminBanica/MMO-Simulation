package heroes;


import angels.Angel;
import main.BigWizz;

import java.io.IOException;

import static constants.Constants.Gen;

import static java.lang.Math.max;

public abstract class Hero {
    private int maxHP;
    private int hP;
    private int xP = 0;
    private int lvl = 0;
    private int ignitedRounds = 0;
    private int ignitedDmg = 0;
    private int snaredRounds = 0;
    private int paralysisDmg = 0;
    private int paralysisRounds = 0;
    private int fought = 0;
    private String type;
    private String typeVerbose;
    private int row = 0;
    private int column = 0;
    private String currentTile;
    private double modifier = 0;
    private int dead = 0;
    private double wizardDamage = 0;

    public final double getModifier() {
        return modifier;
    }

    public final void setModifier(final double newModifier) {
        this.modifier = newModifier;
    }

    public final void addModifier(final double addedModifier) {
        this.modifier += addedModifier;
    }

    public abstract void levelUp(BigWizz bigWizz, int j) throws IOException;

    public abstract int attack(Pyromancer pyro);

    public abstract int attack(Rogue rogue);

    public abstract int attack(Knight knight);

    public abstract int attack(Wizard wizard);

    public abstract int attacked(Hero hero);

    public final String getType() {
        return type;
    }

    public final void setType(final String type) {
        this.type = type;
    }

    public final int getRow() {
        return row;
    }

    public final int getColumn() {
        return column;
    }

    public final void setTile(final int x, final int y) {
        this.row = x;
        this.column = y;
    }


    public final int getFought() {
        return fought;
    }

    public final void setFought(final int hadFight) {
        this.fought = hadFight;
    }

    public final int getXP() {
        return xP;
    }

    public final void addXP(final Hero loser) {
        xP += max(0, Gen.XPTHRESHOLD - (this.getLevel() - loser.getLevel()) * Gen.ADDEDXP);
    }

    public final void raiseXP(final int moreXp) {
        this.xP += moreXp;
    }


    public final int getHP() {
        return hP;
    }

    public final void setHP(final int newHp) {
        this.hP = newHp;
    }

    public final void addHP(final int addedHp) {
        this.hP += addedHp;
        if (this.hP > this.maxHP) {
            this.hP = this.maxHP;
        }
    }

    public final int getMaxHP() {
        return maxHP;
    }

    public final void setMaxHP(final int newMaxHP) {
        this.maxHP = newMaxHP;
    }

    public final void addMaxHp(final int x) {
        maxHP += x;
    }

    public final int getLevel() {
        return lvl;
    }

    public final void setLvl(final int newLvl) {
        this.lvl = newLvl;
    }

    public final String getCurrentTile() {
        return currentTile;
    }

    public final void setCurrentTile(final String newTile) {
        this.currentTile = newTile;
    }

    public void deflect(final int damageReceived, final Hero deflected) { };

    public final void setIgnitedRounds(final int newIgnitedRounds) {
        this.ignitedRounds = newIgnitedRounds;
    }

    public final void setIgnitedDmg(final int newIgnitedDmg) {
        this.ignitedDmg = newIgnitedDmg;
    }

    public final int getSnaredRounds() {
        return snaredRounds;
    }

    public final void setSnaredRounds(final int newSnaredRounds) {
        this.snaredRounds = newSnaredRounds;
    }

    public final void setParalysisDmg(final int newParalysisDmg) {
        this.paralysisDmg = newParalysisDmg;
    }

    public final void setParalysisRounds(final int newParalysisRounds) {
        this.paralysisRounds = newParalysisRounds;
    }

    public final int getParalysisRounds() {
        return paralysisRounds;
    }

    public final void clearDebuff() {
        ignitedDmg = 0;
        ignitedRounds = 0;
        snaredRounds = 0;
        paralysisDmg = 0;
        paralysisRounds = 0;
    }

    public final void roundDebuffs() {
        if (ignitedRounds > 0) {
            addHP(-ignitedDmg);
            ignitedRounds--;
        } else {
            ignitedDmg = 0;
        }
        if (this.getSnaredRounds() == 0 && this.getParalysisRounds() == 0) { // pls
            this.applyStrategy();
        }
        if (snaredRounds > 0) {
            snaredRounds -= 1;
        }
        if (paralysisRounds > 0) {
            addHP(-paralysisDmg);
            paralysisRounds--;
        } else {
            paralysisDmg = 0;
        }
    }

    public abstract void getModified(Angel angel, BigWizz bigWizz,
                                     int id) throws IOException;

    public abstract void applyStrategy();

    public final int getDead() {
        return dead;
    }

    public final void setDead(final int dead) {
        this.dead = dead;
    }

    public final String getTypeVerbose() {
        return typeVerbose;
    }

    public final void setTypeVerbose(final String typeVerbose) {
        this.typeVerbose = typeVerbose;
    }

    public final double getWizardDamage() {
        return wizardDamage;
    }

    public final void setWizardDamage(final double wizardDamage) {
        this.wizardDamage = wizardDamage;
    }

    public final void addWizardDamage(final double x) {
        this.wizardDamage += x;
    }

}
