package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import main.BigWizz;

import java.io.IOException;

public class DamageAngel extends Angel {
    public DamageAngel(final String damageAngel) {
        setType(damageAngel);
    }

    @Override
    public final void modify(final Pyromancer pyro, final BigWizz bigWizz, final int id)
            throws IOException {
        if (pyro.getHP() > 0) {
            pyro.addModifier(Constants.Ang.DMGPYRMOD);
            bigWizz.angelHelped(this, pyro, id);
        }
    }

    @Override
    public final void modify(final Rogue rogue, final BigWizz bigWizz, final int id)
            throws IOException {
        if (rogue.getHP() > 0) {
            rogue.addModifier(Constants.Ang.DMGROGMOD);
            bigWizz.angelHelped(this, rogue, id);
        }
    }

    @Override
    public final void modify(final Knight knight, final BigWizz bigWizz, final int id)
            throws IOException {
        if (knight.getHP() > 0) {
            knight.addModifier(Constants.Ang.DMGKNIMOD);
            bigWizz.angelHelped(this, knight, id);
        }
    }

    @Override
    public final void modify(final Wizard wizard, final BigWizz bigWizz, final int id)
            throws IOException {
        if (wizard.getHP() > 0) {
            wizard.addModifier(Constants.Ang.DMGWIZMOD);
            bigWizz.angelHelped(this, wizard, id);
        }
    }

//    Knight: crește modificatorii de damage cu 15%
//    Pyromancer: crește modificatorii de damage cu 20%
//    Rogue: crește modificatorii de damage cu 30%
//    Wizard: crește modificatorii de damage cu 40%


}
