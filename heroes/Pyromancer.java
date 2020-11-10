package heroes;

import angels.Angel;
import main.BigWizz;
import strategies.PyroStrategy;

import java.io.IOException;

import static constants.Constants.Gen;
import static constants.Constants.Pyr;

public class Pyromancer extends Hero {
    private PyroStrategy strategy = new PyroStrategy();
    public Pyromancer(final int health) {
        setHP(health);
        setMaxHP(health);
        setType("P");
        setTypeVerbose("Pyromancer");
    }

    public final void levelUp(final BigWizz bigWizz, final int j)
            throws IOException {
        int previousLevel = this.getLevel();
        int levelExpected = (this.getXP() - Gen.FIRSTLVLXP) / Gen.XPTOLVL + 1;
        if (this.getLevel() < levelExpected && this.getXP() >= Gen.FIRSTLVLXP) {
            addMaxHp(Pyr.HPPERLVL * (levelExpected - this.getLevel()));
            this.setLvl(levelExpected);
            if (this.getHP() > 0) {
                this.setHP(this.getMaxHP());
            }
            for (int i = 0; i < this.getLevel() - previousLevel; ++i) {
                bigWizz.heroLeveled(this, j, previousLevel + 1 + i);
            }
        }
    }

    private double fireblast(final double heroModifier) {
        return (Pyr.BLASTBASE + Pyr.BLASTPERLVL * this.getLevel())
                * (heroModifier);
    }

    private double igniteBase(final double heroModifier) {
        return (Pyr.IGNITEBASE + Pyr.IGNPERLVL * this.getLevel())
                * (heroModifier);
    }

    private double igniteTick(final double heroModifier) {
        return (Pyr.TICKBASE + Pyr.TICKPERLVL * this.getLevel())
                * (heroModifier);
    }

    @Override
    public final int attack(final Rogue rogue) {
        rogue.clearDebuff();
        if (getCurrentTile().equals("V")) {
            rogue.addHP((int) Math.round(-Pyr.LANDBONUS * fireblast(Pyr.ROGUEBONUS
                    + this.getModifier())));
            rogue.addHP((int) Math.round(-Pyr.LANDBONUS * igniteBase(Pyr.ROGUEBONUS
                    + this.getModifier())));
            rogue.setIgnitedRounds(2);
            rogue.setIgnitedDmg((int) Math.round(Pyr.LANDBONUS * igniteTick(Pyr.ROGUEBONUS
                    + this.getModifier())));
        } else {
            rogue.addHP(-(int) fireblast(Pyr.ROGUEBONUS + this.getModifier()));
            rogue.addHP(-(int) igniteBase(Pyr.ROGUEBONUS + this.getModifier()));
            rogue.setIgnitedRounds(2);
            rogue.setIgnitedDmg((int) igniteTick(Pyr.ROGUEBONUS + this.getModifier()));
        }
        return 0;
    }

    @Override
    public final int attack(final Pyromancer pyro) {
        pyro.clearDebuff();
        if (getCurrentTile().equals("V")) {
            pyro.addHP((int) Math.round(-Pyr.LANDBONUS * fireblast(Pyr.PYROBONUS
                    + this.getModifier())));
            pyro.addHP((int) Math.round(-Pyr.LANDBONUS * igniteBase(Pyr.PYROBONUS
                    + this.getModifier())));
            pyro.setIgnitedRounds(2);
            pyro.setIgnitedDmg((int) Math.round(Pyr.LANDBONUS * igniteTick(Pyr.PYROBONUS
                    + this.getModifier())));
        } else {
            pyro.addHP(-(int) fireblast(Pyr.PYROBONUS + this.getModifier()));
            pyro.addHP(-(int) igniteBase(Pyr.PYROBONUS + this.getModifier()));
            pyro.setIgnitedRounds(2);
            pyro.setIgnitedDmg((int) igniteTick(Pyr.PYROBONUS + this.getModifier()));
        }
        return 0;
    }

    @Override
    public final int attack(final Wizard wizz) {
        int damageDone;
        wizz.clearDebuff();
        if (getCurrentTile().equals("V")) {
            damageDone = -(int) (Math.round(Pyr.LANDBONUS * fireblast(1)))
                    - (int) Math.round(Pyr.LANDBONUS * igniteBase(1));
            wizz.addHP((int) Math.round(-Pyr.LANDBONUS * fireblast(Pyr.WIZZBONUS
                    + this.getModifier())));
            wizz.addHP((int) Math.round(-Pyr.LANDBONUS * igniteBase(Pyr.WIZZBONUS
                    + this.getModifier())));
            wizz.setIgnitedRounds(2);
            wizz.setIgnitedDmg((int) Math.round(Pyr.LANDBONUS * igniteTick(Pyr.WIZZBONUS
                    + this.getModifier())));
        } else {
            damageDone = -(int) Math.round(fireblast(1))
                    - (int) Math.round(igniteBase(1));
            wizz.addHP(-(int) fireblast(Pyr.WIZZBONUS + this.getModifier()));
            System.out.println("FIREBLAST: " + (-(int) fireblast(Pyr.WIZZBONUS
                    + this.getModifier())));
            wizz.addHP(-(int) igniteBase(Pyr.WIZZBONUS + this.getModifier()));
            System.out.println("IGNITE: " + (-(int) igniteBase(Pyr.WIZZBONUS
                    + this.getModifier())));
            wizz.setIgnitedRounds(2);
            wizz.setIgnitedDmg((int) igniteTick(Pyr.WIZZBONUS + this.getModifier()));
        }
        return damageDone;
    }

    @Override
    public final int attack(final Knight knight) {
        knight.clearDebuff();
        if (getCurrentTile().equals("V")) {
            knight.addHP((int) Math.round(-(Pyr.KNIGHTBONUS + this.getModifier())
                    * Math.round(fireblast(Pyr.LANDBONUS))));
            knight.addHP((int) Math.round(-(Pyr.KNIGHTBONUS + this.getModifier())
                    * Math.round(igniteBase(Pyr.LANDBONUS))));
            knight.setIgnitedRounds(2);
            knight.setIgnitedDmg((int) Math.round((Pyr.KNIGHTBONUS + this.getModifier())
                    * Math.round(igniteTick(Pyr.LANDBONUS))));
        } else {
            knight.addHP((int) Math.round(-(Pyr.KNIGHTBONUS + this.getModifier())
                    * (int) fireblast(1)));
            knight.addHP((int) Math.round(-(Pyr.KNIGHTBONUS + this.getModifier())
                    * (int) igniteBase(1)));
            knight.setIgnitedRounds(2);
            knight.setIgnitedDmg((int) Math.round((Pyr.KNIGHTBONUS + this.getModifier())
                    * (int) igniteTick(1)));
        }
        return 0;
    }

    public final int attacked(final Hero hero) {
        return hero.attack(this);
    }

    @Override
    public final void getModified(final Angel angel, final BigWizz bigWizz,
                                  final int id) throws IOException {
        angel.modify(this, bigWizz, id);
    }

    @Override
    public final void applyStrategy() {
        strategy.useStrat(this);
    }
}
