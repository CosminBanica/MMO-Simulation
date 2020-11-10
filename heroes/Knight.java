package heroes;

import angels.Angel;
import main.BigWizz;
import strategies.KnightStrategy;

import java.io.IOException;

import static constants.Constants.Gen;
import static constants.Constants.Kng;

public class Knight extends Hero {
    private KnightStrategy strategy = new KnightStrategy();
    public Knight(final int health) {
        setHP(health);
        setMaxHP(health);
        setType("K");
        setTypeVerbose("Knight");
    }

    public final void levelUp(final BigWizz bigWizz, final int j)
            throws IOException {
        int previousLevel = this.getLevel();
        int levelExpected = (this.getXP() - Gen.FIRSTLVLXP) / Gen.XPTOLVL + 1;
        if (this.getLevel() < levelExpected && this.getXP() >= Gen.FIRSTLVLXP) {
            addMaxHp(Kng.HPPERLVL * (levelExpected - this.getLevel()));
            this.setLvl(levelExpected);
            if (this.getHP() > 0) {
                this.setHP(this.getMaxHP());
            }
            for (int i = 0; i < this.getLevel() - previousLevel; ++i) {
                bigWizz.heroLeveled(this, j, previousLevel + 1 + i);
            }
        }
    }

    private double execute(final Hero executed, final double heroModifier) {
        double limit = Kng.EXECLIMIT + Kng.LIMITPERLVL * getLevel();
        if (limit > Kng.MAXLIMIT) {
            limit = Kng.MAXLIMIT;
        }
        if (executed.getHP() <= executed.getMaxHP() * limit) {
            return -executed.getHP();
        } else {
            return -(Kng.EXECDMG + Kng.EXECPRLVL * this.getLevel()) * heroModifier;
        }
    }

    private double slam(final double heroModifier) {
        return -(Kng.SLAMDMG + Kng.SLAMPERLVL * this.getLevel()) * heroModifier;
    }

    @Override
    public final int attack(final Pyromancer pyro) {
        pyro.clearDebuff();
        if (getCurrentTile().equals("L")) {
            pyro.addHP((int) Math.round(Kng.LANDBONUS * execute(pyro,
                    Kng.EXECPYRO + this.getModifier())));
            pyro.addHP((int) Math.round(Kng.LANDBONUS * slam(Kng.SLAMPYRO + this.getModifier())));
            pyro.setSnaredRounds(1);
        } else {
            pyro.addHP((int) Math.round(execute(pyro, Kng.EXECPYRO + this.getModifier())));
            pyro.addHP((int) Math.round(slam(Kng.SLAMPYRO + this.getModifier())));
            pyro.setSnaredRounds(1);
        }
        return 0;
    }

    @Override
    public final int attack(final Rogue rogue) {
        rogue.clearDebuff();
        if (getCurrentTile().equals("L")) {
            rogue.addHP((int) Math.round(Kng.LANDBONUS * execute(rogue,
                    Kng.EXECROGUE + this.getModifier())));
            rogue.addHP((int) Math.round(Kng.LANDBONUS * slam(Kng.SLAMROGUE + this.getModifier())));
            rogue.setSnaredRounds(1);
        } else {
            rogue.addHP((int) Math.round(execute(rogue, Kng.EXECROGUE + this.getModifier())));
            rogue.addHP((int) Math.floor(slam(Kng.SLAMROGUE + this.getModifier())));
            rogue.setSnaredRounds(1);
        }
        return 0;
    }

    @Override
    public final int attack(final Knight knight) {
        knight.clearDebuff();
        if (getCurrentTile().equals("L")) {
            knight.addHP((int) Math.round(Kng.LANDBONUS * execute(knight,
                    Kng.EXECKNIGHT)));
            knight.addHP((int) Math.round(Kng.LANDBONUS * slam(Kng.SLAMKNIGHT
                    + this.getModifier())));
            knight.setSnaredRounds(1);
        } else {
            knight.addHP((int) Math.round(execute(knight, Kng.EXECKNIGHT)));
            knight.addHP((int) Math.round(slam(Kng.SLAMKNIGHT + this.getModifier())));
            knight.setSnaredRounds(1);
        }
        return 0;
    }

    @Override
    public final int attack(final Wizard wizz) {
        int damageDone;
        wizz.clearDebuff();
        if (getCurrentTile().equals("L")) {
            damageDone = -((int) Math.round(Kng.LANDBONUS * (200 + 30 * this.getLevel()))
                    - (int) Math.round(Kng.LANDBONUS * slam(1)));
            wizz.addHP((int) Math.round(Kng.LANDBONUS * execute(wizz,
                    Kng.EXECWIZZ + this.getModifier())));
            wizz.addHP((int) Math.round(Kng.LANDBONUS * slam(Kng.SLAMWIZZ + this.getModifier())));
            wizz.setSnaredRounds(1);
        } else {
            damageDone = -((200 + 30 * this.getLevel())
                    - (int) Math.round(slam(1)));
            wizz.addHP((int) Math.round(execute(wizz, Kng.EXECWIZZ + this.getModifier())));
            wizz.addHP((int) Math.round(slam(Kng.SLAMWIZZ + this.getModifier())));
            wizz.setSnaredRounds(1);
        }
        return damageDone;
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
