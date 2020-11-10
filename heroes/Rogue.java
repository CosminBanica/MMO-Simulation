package heroes;

import angels.Angel;
import main.BigWizz;
import strategies.RogueStrategy;

import java.io.IOException;

import static constants.Constants.Gen;
import static constants.Constants.Rog;

public class Rogue extends Hero {
    private RogueStrategy strategy = new RogueStrategy();
    private int crit = -1;

    public Rogue(final int health) {
        setHP(health);
        setMaxHP(health);
        setType("R");
        crit = -1;
        setTypeVerbose("Rogue");
    }

    public final void levelUp(final BigWizz bigWizz, final int j)
            throws IOException {
        int previousLevel = this.getLevel();
        int levelExpected = (this.getXP() - Gen.FIRSTLVLXP) / Gen.XPTOLVL + 1;
        if (this.getLevel() < levelExpected && this.getXP() >= Gen.FIRSTLVLXP) {
            addMaxHp(Rog.HPPERLVL * (levelExpected - this.getLevel()));
            this.setLvl(levelExpected);
            if (this.getHP() > 0) {
                this.setHP(this.getMaxHP());
            }
            for (int i = 0; i < this.getLevel() - previousLevel; ++i) {
                bigWizz.heroLeveled(this, j, previousLevel + 1 + i);
            }
        }
    }

    // This method is used to get the damage without modifying crit
    private double backstab(final double heroModifier) {
        crit++;
        if (crit % Rog.CRITMOD == 0) {
            crit--;
            if (getCurrentTile().equals("W")) {
                return -Rog.CRITPERCENT * (heroModifier + this.getModifier()) * (Rog.STABBASE
                        + Rog.STABPERLVL * this.getLevel());
            }
        }
        return -(heroModifier)
                * (Rog.STABBASE + Rog.STABPERLVL * this.getLevel());
    }

    // This method modifies crit
    private double realBackstab(final double heroModifier) {
        crit++;
        if (crit % Rog.CRITMOD == 0) {
            crit = 0;
            if (getCurrentTile().equals("W")) {
                return -Rog.CRITPERCENT * (heroModifier + this.getModifier()) * (Rog.STABBASE
                        + Rog.STABPERLVL * this.getLevel());
            }
        }
        return -(heroModifier)
                * (Rog.STABBASE + Rog.STABPERLVL * this.getLevel());
    }

    private double paralysis(final double heroModifier) {
        return (heroModifier)
                * (Rog.PARABASE + Rog.PARAPERLVL * this.getLevel());
    }

    @Override
    public final int attack(final Pyromancer pyro) {
        pyro.clearDebuff();
        if (getCurrentTile().equals("W")) {
            pyro.addHP(-(int) Math.round(-Rog.LANDBONUS
                    * realBackstab(Rog.STABPYRO + this.getModifier())));
            pyro.addHP((int) Math.round(-Rog.LANDBONUS
                    * paralysis(Rog.PARAPYRO + this.getModifier())));
            pyro.setSnaredRounds(Rog.BONUSSNARE);
            pyro.setParalysisRounds(Rog.BONUSSNARE);
            pyro.setParalysisDmg((int) Math.round(Rog.LANDBONUS
                    * paralysis(Rog.PARAPYRO + this.getModifier())));
        } else {
            pyro.addHP((int) realBackstab(Rog.STABPYRO + this.getModifier()));
            pyro.addHP(-(int) paralysis(Rog.PARAPYRO + this.getModifier()));
            pyro.setSnaredRounds(Rog.SNARETIME);
            pyro.setParalysisRounds(Rog.SNARETIME);
            pyro.setParalysisDmg((int) Math.round(paralysis(Rog.PARAPYRO
                    + this.getModifier())));
        }
        return 0;
    }

    @Override
    public final int attack(final Rogue rogue) {
        rogue.clearDebuff();
        if (getCurrentTile().equals("W")) {
            rogue.addHP(-(int) Math.round(-Rog.LANDBONUS
                    * realBackstab(Rog.STABROGUE + this.getModifier())));
            rogue.addHP((int) Math.round(-Rog.LANDBONUS
                    * paralysis(Rog.PARAROGUE + this.getModifier())));
            rogue.setSnaredRounds(Rog.BONUSSNARE);
            rogue.setParalysisRounds(Rog.BONUSSNARE);
            rogue.setParalysisDmg((int) Math.round(Rog.LANDBONUS
                    * paralysis(Rog.PARAROGUE + this.getModifier())));
        } else {
            rogue.addHP((int) Math.round(realBackstab(Rog.STABROGUE
                    + this.getModifier())));
            rogue.addHP(-(int) paralysis(Rog.PARAROGUE + this.getModifier()));
            rogue.setSnaredRounds(Rog.SNARETIME);
            rogue.setParalysisRounds(Rog.SNARETIME);
            rogue.setParalysisDmg((int) Math.round(paralysis(Rog.PARAROGUE
                    + this.getModifier())));
        }
        return 0;
    }

    @Override
    public final int attack(final Knight knight) {
        knight.clearDebuff();
        if (getCurrentTile().equals("W")) {
            knight.addHP(-(int) Math.round(-Rog.LANDBONUS
                    * realBackstab(Rog.STABKNIGHT + this.getModifier())));
            knight.addHP((int) Math.round(-Rog.LANDBONUS
                    * paralysis(Rog.PARAKNIGHT + this.getModifier())));
            knight.setSnaredRounds(Rog.BONUSSNARE);
            knight.setParalysisRounds(Rog.BONUSSNARE);
            knight.setParalysisDmg((int) Math.round(Rog.LANDBONUS
                    * paralysis(Rog.PARAKNIGHT + this.getModifier())));
        } else {
            knight.addHP((int) realBackstab(Rog.STABKNIGHT + this.getModifier()));
            knight.addHP(-(int) paralysis(Rog.PARAKNIGHT + this.getModifier()));
            knight.setSnaredRounds(Rog.SNARETIME);
            knight.setParalysisRounds(Rog.SNARETIME);
            knight.setParalysisDmg((int) Math.round(paralysis(Rog.PARAKNIGHT
                    + this.getModifier())));
        }
        return 0;
    }

    @Override
    public final int attack(final Wizard wizz) {
        int damageDone;
        wizz.clearDebuff();
        if (getCurrentTile().equals("W")) {
            damageDone = (int) Math.round(Rog.LANDBONUS * backstab(1))
                    - (int) Math.round(Rog.LANDBONUS * paralysis(1));
            wizz.addHP(-(int) Math.round(-Rog.LANDBONUS
                    * realBackstab(Rog.STABWIZZ + this.getModifier())));
            wizz.addHP((int) Math.round(-Rog.LANDBONUS
                    * paralysis(Rog.PARAWIZZ + this.getModifier())));
            wizz.setSnaredRounds(Rog.BONUSSNARE);
            wizz.setParalysisRounds(Rog.BONUSSNARE);
            wizz.setParalysisDmg((int) Math.round(Rog.LANDBONUS
                    * paralysis(Rog.PARAWIZZ + this.getModifier())));
        } else {
            damageDone = (int) Math.round(backstab(1))
                    - (int) Math.round(paralysis(1));
            wizz.addHP((int) realBackstab(Rog.STABWIZZ + this.getModifier()));
            wizz.addHP(-(int) paralysis(Rog.PARAWIZZ + this.getModifier()));
            wizz.setSnaredRounds(Rog.SNARETIME);
            wizz.setParalysisRounds(Rog.SNARETIME);
            wizz.setParalysisDmg((int) Math.round(paralysis(Rog.PARAWIZZ
                    + this.getModifier())));
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
