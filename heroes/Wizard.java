package heroes;

import angels.Angel;
import main.BigWizz;
import strategies.WizStrategy;

import java.io.IOException;

import static constants.Constants.Gen;
import static constants.Constants.Wiz;

import static java.lang.Math.min;

public class Wizard extends Hero {
    private WizStrategy strategy = new WizStrategy();
    public Wizard(final int health) {
        setHP(health);
        setMaxHP(health);
        setType("W");
        setTypeVerbose("Wizard");
    }

    public final void levelUp(final BigWizz bigWizz, final int j)
            throws IOException {
        int previousLevel = this.getLevel();
        int levelExpected = (this.getXP() - Gen.FIRSTLVLXP) / Gen.XPTOLVL + 1;
        if (this.getLevel() < levelExpected && this.getXP() >= Gen.FIRSTLVLXP) {
            addMaxHp(Wiz.HPPERLVL * (levelExpected - this.getLevel()));
            this.setLvl(levelExpected);
            if (this.getHP() > 0) {
                this.setHP(this.getMaxHP());
            }
            for (int i = 0; i < this.getLevel() - previousLevel; ++i) {
                bigWizz.heroLeveled(this, j, previousLevel + 1 + i);
            }
        }
    }

    private double drain(final Hero drained, final double heroModifier) {
        return (-(heroModifier + this.getModifier()) * (Wiz.BASEDRAIN + Wiz.PERLVLDRAIN
                * this.getLevel()) * min(Wiz.PERCENTOFHP
                * drained.getMaxHP(), drained.getHP()));
    }

    public final void deflect(final int damageReceived, final Hero deflected) {
        double heroModifier = 0;
        if (deflected.getType().equals("K")) {
            heroModifier = Wiz.DEFLECTKNIGHT;
        }
        if (deflected.getType().equals("R")) {
            heroModifier = Wiz.DEFLECTROGUE;
        }
        if (deflected.getType().equals("P")) {
            heroModifier = Wiz.DEFLECTPYRO;
        }
        double percent = (heroModifier + this.getModifier())
                * (Wiz.DEFLECTPERCENT + Wiz.DEFLECTPERLVL
                * this.getLevel());
        if (getCurrentTile().equals("D")) {
            deflected.addWizardDamage(Math.round(-(Wiz.LANDBONUS * percent
                    * damageReceived)));
        } else {
            deflected.addWizardDamage(Math.round(-(percent * damageReceived)));
        }

    }

    @Override
    public final int attack(final Pyromancer pyro) {
        if (getCurrentTile().equals("D")) {
            pyro.addWizardDamage((Wiz.LANDBONUS * drain(pyro,
                    Wiz.DRAINPYRO)));
        } else {
            pyro.addWizardDamage((drain(pyro, Wiz.DRAINPYRO)));
        }
        return 0;
    }

    @Override
    public final int attack(final Rogue rogue) {
        if (getCurrentTile().equals("D")) {
            rogue.addWizardDamage((Wiz.LANDBONUS * drain(rogue,
                    Wiz.DRAINROGUE)));
        } else {
            rogue.addWizardDamage((drain(rogue, Wiz.DRAINROGUE)));
        }
        return 0;
    }

    @Override
    public final int attack(final Knight knight) {
        if (getCurrentTile().equals("D")) {
            knight.addWizardDamage((Wiz.LANDBONUS * drain(knight,
                    Wiz.DRAINKNIGHT)));
        } else {
            knight.addWizardDamage((drain(knight, Wiz.DRAINKNIGHT)));
        }
        return 0;
    }

    @Override
    public final int attack(final Wizard wizz) {
        if (getCurrentTile().equals("D")) {
            wizz.addWizardDamage((Wiz.LANDBONUS * drain(wizz,
                    Wiz.DRAINWIZZ)));
        } else {
            wizz.addWizardDamage((drain(wizz, Wiz.DRAINWIZZ)));
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
