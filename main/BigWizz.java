package main;

import angels.Angel;
import fileio.FileSystem;
import heroes.Hero;

import java.io.IOException;

public class BigWizz {
    private FileSystem fileSystem;

    public BigWizz(final FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    public final void angelSpawned(final Angel angel) throws IOException {
        fileSystem.writeWord("Angel " + angel.getType()
                + " was spawned at " + angel.getRow() + " " + angel.getColumn());
        fileSystem.writeNewLine();
    }

    public final void angelHelped(final Angel angel, final Hero hero, final int id)
            throws IOException {
        fileSystem.writeWord(angel.getType() + " helped "
                + hero.getTypeVerbose() + " " + id);
        fileSystem.writeNewLine();
    }

    public final void angelHit(final Angel angel, final Hero hero, final int id)
            throws IOException {
        fileSystem.writeWord(angel.getType() + " hit "
                + hero.getTypeVerbose() + " " + id);
        fileSystem.writeNewLine();
    }

    public final void heroLeveled(final Hero hero, final int id, final int level)
            throws IOException {
        fileSystem.writeWord(hero.getTypeVerbose() + " "
                + id + " reached level " + level);
        fileSystem.writeNewLine();
    }

    public final void heroRevived(final Hero hero, final int id) throws IOException {
        fileSystem.writeWord("Player " + hero.getTypeVerbose()
                + " " + id + " was brought to life by an angel");
        fileSystem.writeNewLine();
    }

    public final void heroKilled(final Hero killer, final Hero killed, final int id1,
                                 final int id2) throws IOException {
        fileSystem.writeWord("Player " + killed.getTypeVerbose()
                + " " + id2 + " was killed by " + killer.getTypeVerbose() + " " + id1);
        fileSystem.writeNewLine();
    }

    public final void angelKilled(final Angel killer, final Hero killed,
                                  final int id) throws IOException {
        fileSystem.writeWord("Player " + killed.getTypeVerbose()
                + " " + id + " was killed by an angel");
        fileSystem.writeNewLine();
    }
}
