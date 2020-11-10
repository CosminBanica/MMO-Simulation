package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import main.BigWizz;

import java.io.IOException;

public class TheDoomer extends Angel {
    public TheDoomer(final String theDoomer) {
        setType(theDoomer);
    }

    @Override
    public final void modify(final Pyromancer pyro, final BigWizz bigWizz, final int id)
            throws IOException {
        if (pyro.getHP() > 0) {
            pyro.setHP(-1);
            bigWizz.angelHit(this, pyro, id);
            bigWizz.angelKilled(this, pyro, id);
        }
    }

    @Override
    public final void modify(final Rogue rogue, final BigWizz bigWizz, final int id)
            throws IOException {
        if (rogue.getHP() > 0) {
            rogue.setHP(-1);
            bigWizz.angelHit(this, rogue, id);
            bigWizz.angelKilled(this, rogue, id);
        }
    }

    @Override
    public final void modify(final Knight knight, final BigWizz bigWizz, final int id)
            throws IOException {
        if (knight.getHP() > 0) {
            knight.setHP(-1);
            bigWizz.angelHit(this, knight, id);
            bigWizz.angelKilled(this, knight, id);
        }
    }

    @Override
    public final void modify(final Wizard wizard, final BigWizz bigWizz, final int id)
            throws IOException {
        if (wizard.getHP() > 0) {
            wizard.setHP(-1);
            bigWizz.angelHit(this, wizard, id);
            bigWizz.angelKilled(this, wizard, id);
        }
    }

//    All: omoară un jucător

}
