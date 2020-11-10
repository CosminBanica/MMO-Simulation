package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import main.BigWizz;

import java.io.IOException;

public class LifeGiver extends Angel {
    public LifeGiver(final String lifeGiver) {
        setType(lifeGiver);
    }

    @Override
    public final void modify(final Pyromancer pyro, final BigWizz bigWizz, final int id)
            throws IOException {
        if (pyro.getHP() > 0) {
            pyro.addHP(Constants.Ang.LIFPYRHP);
            bigWizz.angelHelped(this, pyro, id);
        }
    }

    @Override
    public final void modify(final Rogue rogue, final BigWizz bigWizz, final int id)
            throws IOException {
        if (rogue.getHP() > 0) {
            rogue.addHP(Constants.Ang.LIFROGHP);
            bigWizz.angelHelped(this, rogue, id);
        }
    }

    @Override
    public final void modify(final Knight knight, final BigWizz bigWizz, final int id)
            throws IOException {
        if (knight.getHP() > 0) {
            knight.addHP(Constants.Ang.LIFKNIHP);
            bigWizz.angelHelped(this, knight, id);
        }
    }

    @Override
    public final void modify(final Wizard wizard, final BigWizz bigWizz, final int id)
            throws IOException {
        if (wizard.getHP() > 0) {
            wizard.addHP(Constants.Ang.LIFWIZHP);
            bigWizz.angelHelped(this, wizard, id);
        }
    }


//    Knight: crește HP-ul cu 100
//    Pyromancer: crește HP-ul cu 80
//    Rogue: crește HP-ul cu 90
//    Wizard: crește HP-ul cu 120

}
