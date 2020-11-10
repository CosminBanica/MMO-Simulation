package main;

import angels.Angel;
import heroes.Hero;
import fileio.FileSystem;

import java.io.IOException;
import java.util.List;

public final class GamePlay {
    private int nrRounds;
    private MapTile[][] maptiles;
    private List<Hero> heroes;
    private int nrPlayers;
    private FileSystem fileSystem;
    private BigWizz bigWizz;
    private Angel[][] angels;
    private int[] angelsPerRound;

    GamePlay(final int nrRounds, final MapTile[][] maptiles,
             final List<Hero> heroes, final int nrPlayers,
             final FileSystem fileSystem, final Angel[][] angels,
             final int[] angelsPerRound) {
        this.nrRounds = nrRounds;
        this.maptiles = maptiles;
        this.heroes = heroes;
        this.nrPlayers = nrPlayers;
        this.fileSystem = fileSystem;
        this.bigWizz = new BigWizz(fileSystem);
        this.angels = angels;
        this.angelsPerRound = angelsPerRound;
    }

    public void playRounds() throws IOException {
        for (int i = 0; i < nrRounds; ++i) {
            fileSystem.writeWord("~~ Round " + (i + 1) + " ~~");
            fileSystem.writeNewLine();
            String type = fileSystem.nextWord();
            for (int j = 0; j < nrPlayers; ++j) {
                if (heroes.get(j).getHP() > 0) {
                    if (heroes.get(j).getSnaredRounds() == 0) {
                        if (type.charAt(j) == 'U' && heroes.get(j).getRow() - 1 >= 0) {
                            heroes.get(j).setTile(heroes.get(j).getRow() - 1,
                                    heroes.get(j).getColumn());
                        }
                        if (type.charAt(j) == 'D' && heroes.get(j).getRow() + 1 <= 20) {
                            heroes.get(j).setTile(heroes.get(j).getRow() + 1,
                                    heroes.get(j).getColumn());
                        }
                        if (type.charAt(j) == 'L' && heroes.get(j).getColumn() - 1 >= 0) {
                            heroes.get(j).setTile(heroes.get(j).getRow(),
                                    heroes.get(j).getColumn() - 1);
                        }
                        if (type.charAt(j) == 'R' && heroes.get(j).getColumn() + 1 <= 20) {
                            heroes.get(j).setTile(heroes.get(j).getRow(),
                                    heroes.get(j).getColumn() + 1);
                        }
                        heroes.get(j).setCurrentTile(maptiles[heroes.get(j).getRow()]
                                [heroes.get(j).getColumn()].getType());
                    }
                }
                heroes.get(j).roundDebuffs();
            }

            for (int j = 0; j < nrPlayers; ++j) {
                Hero hero = heroes.get(j);
                fileSystem.writeWord(j + " " + hero.getType() + " "
                        + hero.getLevel() + " " + hero.getXP() + " "
                        + hero.getHP() + " " + hero.getRow() + " " + hero.getColumn()
                        + " " + hero.getModifier());
                fileSystem.writeNewLine();
            }

            // Pentru fiecare erou se verifica daca s-a intalnit cu altul
            for (int j = 0; j < nrPlayers; ++j) {
                if (heroes.get(j).getFought() == 0 && heroes.get(j).getHP() > 0) {
                    heroes.get(j).setFought(1);
                    for (int k = 0; k < nrPlayers; ++k) {

                        // Daca s-a intalnit cu alt erou, are loc o lupta
                        if (j != k && heroes.get(j).getColumn() == heroes.get(k).getColumn()
                                && heroes.get(j).getRow() == heroes.get(k).getRow()
                                && heroes.get(k).getHP() > 0) {
                            int damageReceived1 = heroes.get(j).attacked(heroes.get(k));
                            int damageReceived2 = heroes.get(k).attacked(heroes.get(j));

                            // Daca unul din ei e wizard, se foloseste damageReceived
                            if ((heroes.get(j).getType().equals("W"))
                                    != heroes.get(k).getType().equals("W")) {
                                if (heroes.get(j).getType().equals("W")) {
                                    heroes.get(j).deflect(-damageReceived1, heroes.get(k));
                                    heroes.get(k).addHP((int)
                                            Math.round(heroes.get(k).getWizardDamage()));
                                } else {
                                    heroes.get(k).deflect(-damageReceived2, heroes.get(j));
                                    heroes.get(j).addHP((int)
                                            Math.round(heroes.get(j).getWizardDamage()));
                                }
                            }
                            if (heroes.get(j).getType().equals("W")
                                    && heroes.get(k).getType().equals("W")) {
                                heroes.get(j).addHP((int)
                                        Math.round(heroes.get(j).getWizardDamage()));
                                heroes.get(k).addHP((int)
                                        Math.round(heroes.get(k).getWizardDamage()));
                            }
                            heroes.get(j).setWizardDamage(0);
                            heroes.get(k).setWizardDamage(0);
                            heroes.get(k).setFought(1);
                            fileSystem.writeWord(heroes.get(j).getTypeVerbose() + " "
                            + j + " fights "
                                    + heroes.get(k).getTypeVerbose() + " " + k);
                            fileSystem.writeNewLine();
                            if (heroes.get(j).getHP() <= 0) {
                                heroes.get(k).addXP(heroes.get(j));
                            }
                            if (heroes.get(k).getHP() <= 0) {
                                heroes.get(j).addXP(heroes.get(k));
                            }
                            if (heroes.get(k).getHP() <= 0) {
                                bigWizz.heroKilled(heroes.get(j), heroes.get(k), j, k);
                            }
                            if (heroes.get(j).getHP() <= 0) {
                                bigWizz.heroKilled(heroes.get(k), heroes.get(j), k, j);
                            }

                            for (int r = 0; r < nrPlayers; ++r) {
                                Hero hero = heroes.get(r);
                                fileSystem.writeWord(r + " " + hero.getType() + " "
                                        + hero.getLevel() + " " + hero.getXP() + " "
                                        + hero.getHP() + " " + hero.getRow() + " "
                                        + hero.getColumn()
                                        + " " + hero.getModifier());
                                fileSystem.writeNewLine();
                            }
                            // La finalul luptei, levelUp daca e necesar
                            heroes.get(j).levelUp(bigWizz, j);
                            heroes.get(k).levelUp(bigWizz, k);
                        }
                    }
                }
            }
            for (int j = 0; j < nrPlayers; ++j) {
                heroes.get(j).setFought(0);
            }

            // Angel time
            for (int k = 0; k < angelsPerRound[i]; ++k) {
                bigWizz.angelSpawned(angels[i][k]);
                for (int j = 0; j < nrPlayers; ++j) {
                    if (heroes.get(j).getColumn() == angels[i][k].getColumn()
                            && heroes.get(j).getRow() == angels[i][k].getRow()) {
                        heroes.get(j).getModified(angels[i][k], bigWizz, j);
                    }
                }
            }
            fileSystem.writeNewLine();

            for (int j = 0; j < nrPlayers; ++j) {
                Hero hero = heroes.get(j);
                fileSystem.writeWord(j + " " + hero.getType() + " "
                        + hero.getLevel() + " " + hero.getXP() + " "
                        + hero.getHP() + " " + hero.getRow() + " " + hero.getColumn()
                        + " " + hero.getModifier());
                fileSystem.writeNewLine();
            }
        }
    }

    // La final, afisarea statusul-ui eroilor
    public void printStatus() throws IOException {
        fileSystem.writeWord("~~ Results ~~");
        fileSystem.writeNewLine();
        for (int i = 0; i < nrPlayers; ++i) {
            Hero hero = heroes.get(i);
            if (hero.getHP() <= 0) {
                fileSystem.writeWord(hero.getType() + " dead");
                fileSystem.writeNewLine();
            } else {
                fileSystem.writeWord(hero.getType() + " "
                        + hero.getLevel() + " " + hero.getXP() + " "
                        + hero.getHP() + " " + hero.getRow() + " " + hero.getColumn());
                fileSystem.writeNewLine();
            }
        }
    }
}
