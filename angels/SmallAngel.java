package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import main.BigWizz;

import java.io.IOException;

public class SmallAngel extends Angel {
    public SmallAngel(final String smallAngel) {
        setType(smallAngel);
    }

    @Override
    public final void modify(final Pyromancer pyro, final BigWizz bigWizz, final int id)
            throws IOException {
        if (pyro.getHP() > 0) {
            pyro.addModifier(Constants.Ang.SMAPYRMOD);
            pyro.addHP(Constants.Ang.SMAPYRHP);
            bigWizz.angelHelped(this, pyro, id);
        }
    }

    @Override
    public final void modify(final Rogue rogue, final BigWizz bigWizz, final int id)
            throws IOException {
        if (rogue.getHP() > 0) {
            rogue.addModifier(Constants.Ang.SMAROGMOD);
            rogue.addHP(Constants.Ang.SMAROGHP);
            bigWizz.angelHelped(this, rogue, id);
        }
    }

    @Override
    public final void modify(final Knight knight, final BigWizz bigWizz, final int id)
            throws IOException {
        if (knight.getHP() > 0) {
            knight.addModifier(Constants.Ang.SMAKNIMOD);
            knight.addHP(Constants.Ang.SMAKNIHP);
            bigWizz.angelHelped(this, knight, id);
        }
    }

    @Override
    public final void modify(final Wizard wizard, final BigWizz bigWizz, final int id)
            throws IOException {
        if (wizard.getHP() > 0) {
            wizard.addModifier(Constants.Ang.SMAWIZMOD);
            wizard.addHP(Constants.Ang.SMAWIZHP);
            bigWizz.angelHelped(this, wizard, id);
        }
    }



//    Knight: crește modificatorii de damage cu 10% și HP-ul cu 10
//    Pyromancer: crește modificatorii de damage cu 15% și HP-ul cu 15
//    Rogue: crește modificatorii de damage cu 5% și HP-ul cu 20
//    Wizard: crește modificatorii de damage cu 10% și HP-ul cu 25

}
