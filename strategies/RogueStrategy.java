package strategies;

import constants.Constants;
import heroes.Hero;

public class RogueStrategy extends Strategy {
    @Override
    public final void useStrat(final Hero hero) {
        if (hero.getMaxHP() / Constants.Str.ROGLOWER < hero.getHP() && hero.getHP()
                < hero.getMaxHP() / Constants.Str.ROGUPPER) {
            hero.addHP(-hero.getHP() / Constants.Str.ROGLOSEHP);
            hero.addModifier(Constants.Str.ROGGAINMOD);
        } else if (hero.getHP() < hero.getMaxHP() / Constants.Str.ROGLOWER) {
            hero.addHP(hero.getHP() / Constants.Str.ROGGAINHP);
            hero.addModifier(-Constants.Str.ROGLOSEMOD);
        }
    }

//    dacă (1/7 * MAX_LEVEL_HP) < CURRENT_HP < (1/5 * MAX_LEVEL_HP), el va renunța la 1/7
//    din HP-ul curent și va crește coeficienții cu 40%
//    dacă CURRENT_HP < (1/7 * MAX_LEVEL_HP), el va renunța la 10% din coeficienți și va primi 1/2
//    din HP-ul curent

}
