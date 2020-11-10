package strategies;

import constants.Constants;
import heroes.Hero;

public class WizStrategy extends Strategy {
    @Override
    public final void useStrat(final Hero hero) {
        if (hero.getMaxHP() / Constants.Str.WIZLOWER < hero.getHP() && hero.getHP()
                < hero.getMaxHP() / Constants.Str.WIZUPPER) {
            hero.addHP(-hero.getHP() / Constants.Str.WIZLOSEHP);
            hero.addModifier(Constants.Str.WIZGAINMOD);
        } else if (hero.getHP() < hero.getMaxHP() / Constants.Str.WIZLOWER
                && hero.getSnaredRounds() == 0) {
            hero.addHP(hero.getHP() / Constants.Str.WIZGAINHP);
            hero.addModifier(-Constants.Str.WIZLOSEMOD);
        }
    }

//    dacă (1/4 * MAX_LEVEL_HP) < CURRENT_HP < (1/2 * MAX_LEVEL_HP), el va renunța la 1/10
//    din HP-ul curent și va crește coeficienții cu 60%
//    dacă CURRENT_HP < (1/4 * MAX_LEVEL_HP), el va renunța la 20% din coeficienți și va primi 1/5
//    din HP-ul curent

}
