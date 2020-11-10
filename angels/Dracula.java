package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import main.BigWizz;

import java.io.IOException;

public class Dracula extends Angel {
    public Dracula(final String dracula) {
        setType(dracula);
    }

    @Override
    public final void modify(final Pyromancer pyro, final BigWizz bigWizz, final int id)
            throws IOException {
        if (pyro.getHP() > 0) {
            pyro.addModifier(Constants.Ang.DRAPYRMOD);
            pyro.addHP(Constants.Ang.DRAPYRHP);
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
            rogue.addModifier(Constants.Ang.DRAROGMOD);
            rogue.addHP(Constants.Ang.DRAROGHP);
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
            knight.addModifier(Constants.Ang.DRAKNIMOD);
            knight.addHP(Constants.Ang.DRAKNIHP);
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
            wizard.addModifier(Constants.Ang.DRAWIZMOD);
            wizard.addHP(Constants.Ang.DRAWIZHP);
            bigWizz.angelHit(this, wizard, id);
            if (wizard.getHP() <= 0) {
                bigWizz.angelKilled(this, wizard, id);
            }
        }
    }


//    Knight: scade modificatorii de damage cu 20% și HP-ul cu 60
//    Pyromancer: scade modificatorii de damage cu 30% și HP-ul cu 40
//    Rogue: scade modificatorii de damage cu 10% și HP-ul cu 35
//    Wizard: scade modificatorii de damage cu 40% și HP-ul cu 20

}
