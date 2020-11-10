package main;

import angels.Angel;
import loaders.InputLoader;
import fileio.FileSystem;
import heroes.Hero;

import java.io.IOException;
import java.util.List;

public final class Main {
    private Main() { }

    public static void main(final String[] args) throws IOException {
        // Clasa care imi creeaza harta si eroii
        InputLoader inputLoader = InputLoader.getInstance(args[0], args[1]);
        FileSystem fileSystem = inputLoader.getFileSystem();
        int nrPlayers = inputLoader.getP();
        MapTile[][] maptiles = inputLoader.getMaptiles();
        List<Hero> heroes = inputLoader.getHeroes();
        int nrRounds = inputLoader.getR();
        Angel[][] angels = inputLoader.getAngels();
        int[] angelsPerRound = inputLoader.getAngelsPerRound();

        // Clasa in care are loc jocul
        GamePlay gamePlay = new GamePlay(nrRounds, maptiles, heroes,
                nrPlayers, fileSystem, angels, angelsPerRound);
        gamePlay.playRounds();
        gamePlay.printStatus();

        fileSystem.close();
    }
}
