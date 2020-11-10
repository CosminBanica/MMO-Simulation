package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import main.BigWizz;

import java.io.IOException;

public class DarkAngel extends Angel {
    public DarkAngel(final String darkAngel) {
        setType(darkAngel);
    }

    @Override
    public final void modify(final Pyromancer pyro, final BigWizz bigWizz, final int id)
            throws IOException {
        if (pyro.getHP() > 0) {
            pyro.addHP(Constants.Ang.DRKPYRHP);
            bigWizz.angelHit(this, pyro, id);
            if (pyro.getHP() <= 0) {
                bigWizz.angelKilled(this, pyro, id);
            }
        }
    }

    @Override
    public final void modify(final Rogue rogue, final BigWizz bigWizz, final int id)
            throws IOException {
        if (rogue.getHP() > 0) {
            rogue.addHP(Constants.Ang.DRKROGHP);
            bigWizz.angelHit(this, rogue, id);
            if (rogue.getHP() <= 0) {
                bigWizz.angelKilled(this, rogue, id);
            }
        }
    }

    @Override
    public final void modify(final Knight knight, final BigWizz bigWizz, final int id)
            throws IOException {
        if (knight.getHP() > 0) {
            knight.addHP(Constants.Ang.DRKKNIHP);
            bigWizz.angelHit(this, knight, id);
            if (knight.getHP() <= 0) {
                bigWizz.angelKilled(this, knight, id);
            }
        }
    }

    @Override
    public final void modify(final Wizard wizard, final BigWizz bigWizz, final int id)
            throws IOException {
        if (wizard.getHP() > 0) {
            wizard.addHP(Constants.Ang.DRKWIZHP);
            bigWizz.angelHit(this, wizard, id);
            if (wizard.getHP() <= 0) {
                bigWizz.angelKilled(this, wizard, id);
            }
        }
    }


//    Knight: scade HP-ul cu 40
//    Pyromancer: scade HP-ul cu 30
//    Rogue: scade HP-ul cu 10
//    Wizard: scade HP-ul cu 20

}
