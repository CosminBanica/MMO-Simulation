package angels;

import static constants.Constants.Gen;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import main.BigWizz;

import java.io.IOException;

public class LevelUpAngel extends Angel {
    public LevelUpAngel(final String levelUpAngel) {
        setType(levelUpAngel);
    }

    @Override
    public final void modify(final Pyromancer pyro, final BigWizz bigWizz, final int id)
            throws IOException {
        if (pyro.getHP() > 0) {
            pyro.raiseXP(Gen.FIRSTLVLXP + pyro.getLevel() * Gen.XPTOLVL - pyro.getXP());
            pyro.addModifier(Constants.Ang.LVLPYRMOD);
            bigWizz.angelHelped(this, pyro, id);
            pyro.levelUp(bigWizz, id);
        }
    }

    @Override
    public final void modify(final Rogue rogue, final BigWizz bigWizz, final int id)
            throws IOException {
        if (rogue.getHP() > 0) {
            rogue.raiseXP(Gen.FIRSTLVLXP + rogue.getLevel() * Gen.XPTOLVL - rogue.getXP());
            rogue.addModifier(Constants.Ang.LVLROGMOD);
            bigWizz.angelHelped(this, rogue, id);
            rogue.levelUp(bigWizz, id);
        }
    }

    @Override
    public final void modify(final Knight knight, final BigWizz bigWizz, final int id)
            throws IOException {
        if (knight.getHP() > 0) {
            knight.raiseXP(Gen.FIRSTLVLXP + knight.getLevel() * Gen.XPTOLVL - knight.getXP());
            knight.addModifier(Constants.Ang.LVLKNIMOD);
            bigWizz.angelHelped(this, knight, id);
            knight.levelUp(bigWizz, id);
        }
    }

    @Override
    public final void modify(final Wizard wizard, final BigWizz bigWizz, final int id)
            throws IOException {
        if (wizard.getHP() > 0) {
            wizard.raiseXP(Gen.FIRSTLVLXP + wizard.getLevel() * Gen.XPTOLVL - wizard.getXP());
            wizard.addModifier(Constants.Ang.LVLWIZMOD);
            bigWizz.angelHelped(this, wizard, id);
            wizard.levelUp(bigWizz, id);
        }
    }


//    Knight: oferă XP cât are nevoie jucătorul pentru a avansa la nivelul următor
//    și crește modificatorii cu 10%
//    Pyromancer: oferă XP cât are nevoie jucătorul pentru a avansa la nivelul următor
//    și crește modificatorii cu 20%
//    Rogue: oferă XP cât are nevoie jucătorul pentru a avansa la nivelul următor
//    și crește modificatorii cu 15%
//    Wizard: oferă XP cât are nevoie jucătorul pentru a avansa la nivelul următor
//    și crește modificatorii cu 25%

}
