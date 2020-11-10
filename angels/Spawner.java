package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import main.BigWizz;

import java.io.IOException;

public class Spawner extends Angel {
    public Spawner(final String spawner) {
        setType(spawner);
    }

    @Override
    public final void modify(final Pyromancer pyro, final BigWizz bigWizz, final int id)
            throws IOException {
        if (pyro.getHP() <= 0) {
            pyro.setHP(Constants.Ang.SPAPYRHP);
            bigWizz.angelHelped(this, pyro, id);
            bigWizz.heroRevived(pyro, id);
        }
    }

    @Override
    public final void modify(final Rogue rogue, final BigWizz bigWizz, final int id)
            throws IOException {
        if (rogue.getHP() <= 0) {
            rogue.setHP(Constants.Ang.SPAROGHP);
            bigWizz.angelHelped(this, rogue, id);
            bigWizz.heroRevived(rogue, id);
        }
    }

    @Override
    public final void modify(final Knight knight, final BigWizz bigWizz, final int id)
            throws IOException {
        if (knight.getHP() <= 0) {
            knight.setHP(Constants.Ang.SPAKNIHP);
            bigWizz.angelHelped(this, knight, id);
            bigWizz.heroRevived(knight, id);
        }
    }

    @Override
    public final void modify(final Wizard wizard, final BigWizz bigWizz, final int id)
            throws IOException {
        if (wizard.getHP() <= 0) {
            wizard.setHP(Constants.Ang.SPAWIZHP);
            bigWizz.angelHelped(this, wizard, id);
            bigWizz.heroRevived(wizard, id);
        }
    }


//    Knight: readuce jucătorul la viață și îi setează viața rămasă la 200
//    Pyromancer: readuce jucătorul la viață și îi setează viața rămasă la 150
//    Rogue: readuce jucătorul la viață și îi setează viața rămasă la 180
//    Wizard: readuce jucătorul la viață și îi setează viața rămasă la 120

}
