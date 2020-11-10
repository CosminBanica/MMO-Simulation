package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import main.BigWizz;

import java.io.IOException;

public class XPAngel extends Angel {
    public XPAngel(final String xpAngel) {
        setType(xpAngel);
    }

    @Override
    public final void modify(final Pyromancer pyro, final BigWizz bigWizz, final int id)
            throws IOException {
        if (pyro.getHP() > 0) {
            pyro.raiseXP(Constants.Ang.XPPYR);
            bigWizz.angelHelped(this, pyro, id);
            pyro.levelUp(bigWizz, id);
        }
    }

    @Override
    public final void modify(final Rogue rogue, final BigWizz bigWizz, final int id)
            throws IOException {
        if (rogue.getHP() > 0) {
            rogue.raiseXP(Constants.Ang.XPROG);
            bigWizz.angelHelped(this, rogue, id);
            rogue.levelUp(bigWizz, id);
        }
    }

    @Override
    public final void modify(final Knight knight, final BigWizz bigWizz, final int id)
            throws IOException {
        if (knight.getHP() > 0) {
            knight.raiseXP(Constants.Ang.XPKNI);
            bigWizz.angelHelped(this, knight, id);
            knight.levelUp(bigWizz, id);
        }
    }

    @Override
    public final void modify(final Wizard wizard, final BigWizz bigWizz, final int id)
            throws IOException {
        if (wizard.getHP() > 0) {
            wizard.raiseXP(Constants.Ang.XPWIZ);
            bigWizz.angelHelped(this, wizard, id);
            wizard.levelUp(bigWizz, id);
        }
    }

//    Knight: crește XP-ul cu 45
//    Pyromancer: crește XP-ul cu 50
//    Rogue: crește XP-ul cu 40
//    Wizard: crește XP-ul cu 60

}
