package strategies;

import constants.Constants;
import heroes.Hero;

public class PyroStrategy extends Strategy {
    @Override
    public final void useStrat(final Hero hero) {
        if (hero.getMaxHP() / Constants.Str.PYRLOWER < hero.getHP() && hero.getHP()
                < hero.getMaxHP() / Constants.Str.PYRUPPER) {
            hero.addHP(-hero.getHP() / Constants.Str.PYRLOSEHP);
            hero.addModifier(Constants.Str.PYRGAINMOD);
        } else if (hero.getHP() < hero.getMaxHP() / Constants.Str.PYRLOWER) {
            hero.addHP(hero.getHP() / Constants.Str.PYRGAINHP);
            hero.addModifier(-Constants.Str.PYRLOSEMOD);
        }
    }

//    dacă (1/4 * MAX_LEVEL_HP) < CURRENT_HP < (1/3 * MAX_LEVEL_HP), el va renunța la 1/4
//    din HP-ul curent și va crește coeficienții cu 70%
//    dacă CURRENT_HP < (1/4 * MAX_LEVEL_HP), el va renunța la 30% din coeficienți și va primi 1/3
//    din HP-ul curent

}
