package angels;

import constants.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import main.BigWizz;

import java.io.IOException;

public class GoodBoy extends Angel {
    public GoodBoy(final String goodBoy) {
        setType(goodBoy);
    }

    @Override
    public final void modify(final Pyromancer pyro, final BigWizz bigWizz, final int id)
            throws IOException {
        if (pyro.getHP() > 0) {
            pyro.addModifier(Constants.Ang.BOYPYRMOD);
            pyro.addHP(Constants.Ang.BOYPYRHP);
            bigWizz.angelHelped(this, pyro, id);
        }
    }

    @Override
    public final void modify(final Rogue rogue, final BigWizz bigWizz, final int id)
            throws IOException {
        if (rogue.getHP() > 0) {
            rogue.addModifier(Constants.Ang.BOYROGMOD);
            rogue.addHP(Constants.Ang.BOYROGHP);
            bigWizz.angelHelped(this, rogue, id);
        }
    }

    @Override
    public final void modify(final Knight knight, final BigWizz bigWizz, final int id)
            throws IOException {
        if (knight.getHP() > 0) {
            knight.addModifier(Constants.Ang.BOYKNIMOD);
            knight.addHP(Constants.Ang.BOYKNIHP);
            bigWizz.angelHelped(this, knight, id);
        }
    }

    @Override
    public final void modify(final Wizard wizard, final BigWizz bigWizz, final int id)
            throws IOException {
        if (wizard.getHP() > 0) {
            wizard.addModifier(Constants.Ang.BOYWIZMOD);
            wizard.addHP(Constants.Ang.BOYWIZHP);
            bigWizz.angelHelped(this, wizard, id);
        }
    }


//    Knight: crește modificatorii de damage cu 40% și HP-ul cu 20
//    Pyromancer: crește modificatorii de damage cu 50% și HP-ul cu 30
//    Rogue: crește modificatorii de damage cu 40% și HP-ul cu 40
//    Wizard: crește modificatorii de damage cu 30% și HP-ul cu 50

}
