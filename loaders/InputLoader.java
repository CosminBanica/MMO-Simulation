package loaders;

import angels.*;
import heroes.Hero;
import heroes.Wizard;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import fileio.FileSystem;
import main.MapTile;
import static constants.Constants.Gen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class InputLoader {
    private static InputLoader instance = null;
    private FileSystem fileSystem;
    private int rows;
    private int columns;
    private int nrPlayers;
    private MapTile[][] maptiles;
    private List<Hero> heroes;
    private int nrRounds;
    private Angel[][] angels;
    private int[] angelsPerRound;

    private InputLoader(final String in, final String out) throws IOException {
        fileSystem = new FileSystem(in, out);
        rows = fileSystem.nextInt();
        columns = fileSystem.nextInt();
        maptiles = new MapTile[Gen.MAXMAPSIZE][Gen.MAXMAPSIZE];
        heroes = new ArrayList<>();
        angels = new Angel[Gen.MAXANGELS][Gen.MAXANGELS];
        angelsPerRound = new int[Gen.MAXANGELS];
        for (int i = 0; i < rows; ++i) {
            String tileType = fileSystem.nextWord();
            for (int j = 0; j < columns; ++j) {
                maptiles[i][j] = new MapTile();
                maptiles[i][j].setType(String.valueOf(tileType.charAt(j)));
            }
        }
        nrPlayers = fileSystem.nextInt();
        for (int i = 0; i < nrPlayers; ++i) {
            String type = fileSystem.nextWord();
            if (type.equals("W")) {
                heroes.add(new Wizard(Gen.WIZZHP));
            }
            if (type.equals("R")) {
                heroes.add(new Rogue(Gen.ROGUEHP));
            }
            if (type.equals("K")) {
                heroes.add(new Knight(Gen.KNIGHTHP));
            }
            if (type.equals("P")) {
                heroes.add(new Pyromancer(Gen.PYROHP));
            }
            heroes.get(i).setTile(fileSystem.nextInt(), fileSystem.nextInt());
            heroes.get(i).setCurrentTile(maptiles[heroes.get(i).getRow()]
                    [heroes.get(i).getColumn()].getType());
        }
        nrRounds = fileSystem.nextInt();
        // Skipping the rounds
        for (int i = 0; i < nrRounds; ++i) {
            String junk = fileSystem.nextWord();
        }
        // Reading angels
        for (int i = 0; i < nrRounds; ++i) {
            int angelsThisRound = fileSystem.nextInt();
            angelsPerRound[i] = angelsThisRound;
            for (int j = 0; j < angelsThisRound; ++j) {
                String angel = fileSystem.nextWord();
                addAngel(angel, angels, i, j);
                String[] splitAngel = angel.split(",");
                angels[i][j].setRow(Integer.parseInt(splitAngel[1]));
                angels[i][j].setColumn(Integer.parseInt(splitAngel[2]));
            }
        }
        // Set fileSystem back to rounds
        fileSystem = resetFileSystem(fileSystem, in, out);
    }

    private FileSystem resetFileSystem(FileSystem oldFileSystem, final String in,
                                       final String out) throws IOException {
        fileSystem = new FileSystem(in, out);
        String wordJunk;
        int intJunk = fileSystem.nextInt();
        intJunk = fileSystem.nextInt();
        for (int i = 0; i < intJunk; ++i) {
            wordJunk = fileSystem.nextWord();
        }
        intJunk = fileSystem.nextInt();
        for (int i = 0; i < intJunk; ++i) {
            wordJunk = fileSystem.nextWord();
            int positionJunk = fileSystem.nextInt();
            positionJunk = fileSystem.nextInt();
        }
        intJunk = fileSystem.nextInt();
        return fileSystem;
    }

    private void addAngel(final String angel, final Angel[][] angels, final int i,
                          final int j) {
        if (angel.contains("Damage")) {
            angels[i][j] = new DamageAngel("DamageAngel");
        }
        if (angel.contains("Dark")) {
            angels[i][j] = new DarkAngel("DarkAngel");
        }
        if (angel.contains("Dracula")) {
            angels[i][j] = new Dracula("Dracula");
        }
        if (angel.contains("Good")) {
            angels[i][j] = new GoodBoy("GoodBoy");
        }
        if (angel.contains("Level")) {
            angels[i][j] = new LevelUpAngel("LevelUpAngel");
        }
        if (angel.contains("Life")) {
            angels[i][j] = new LifeGiver("LifeGiver");
        }
        if (angel.contains("Small")) {
            angels[i][j] = new SmallAngel("SmallAngel");
        }
        if (angel.contains("Spawner")) {
            angels[i][j] = new Spawner("Spawner");
        }
        if (angel.contains("Doomer")) {
            angels[i][j] = new TheDoomer("TheDoomer");
        }
        if (angel.contains("XP")) {
            angels[i][j] = new XPAngel("XPAngel");
        }
    }

    public static InputLoader getInstance(final String in, final String out)
            throws IOException {
        if (instance == null) {
            instance = new InputLoader(in, out);
        }
        return instance;
    }

    public FileSystem getFileSystem() {
        return fileSystem;
    }

    public int getN() {
        return rows;
    }

    public int getM() {
        return columns;
    }

    public int getP() {
        return nrPlayers;
    }

    public MapTile[][] getMaptiles() {
        return maptiles;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public int getR() {
        return nrRounds;
    }

    public Angel[][] getAngels() {
        return angels;
    }

    public int[] getAngelsPerRound() {
        return angelsPerRound;
    }
}
