package strategies;

import constants.Constants;
import heroes.Hero;

public class KnightStrategy extends Strategy {
    @Override
    public final void useStrat(final Hero hero) {
        if (hero.getMaxHP() / Constants.Str.KNILOWER < hero.getHP() && hero.getHP()
                < hero.getMaxHP() / Constants.Str.KNIUPPER) {
            hero.addHP(-hero.getHP() / Constants.Str.KNILOSEHP);
            hero.addModifier(Constants.Str.KNIGAINMOD);
        } else if (hero.getHP() < hero.getMaxHP() / Constants.Str.KNILOWER) {
            hero.addHP(hero.getHP() / Constants.Str.KNIGAINHP);
            hero.addModifier(-Constants.Str.KNILOSEMOD);
        }
    }

//    dacă (1/3 * MAX_LEVEL_HP) < CURRENT_HP < (1/2 * MAX_LEVEL_HP), el va renunța la 1/5
//    din HP-ul curent și va crește coeficienții cu 50%
//    dacă CURRENT_HP < (1/3 * MAX_LEVEL_HP), el va renunța la 20% din coeficienți și va primi
//    1/4 din HP-ul curent

}
