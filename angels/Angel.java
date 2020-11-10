package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import main.BigWizz;

import java.io.IOException;

public abstract class Angel {
    private int row = -1;
    private int column = -1;
    private String type;
    public final String getType() {
        return type;
    }

    public final void setType(final String type) {
        this.type = type;
    }

    public abstract void modify(Pyromancer pyro, BigWizz bigWizz, int id) throws IOException;

    public abstract void modify(Rogue rogue, BigWizz bigWizz, int id) throws IOException;

    public abstract void modify(Knight knight, BigWizz bigWizz, int id) throws IOException;

    public abstract void modify(Wizard wizard, BigWizz bigWizz, int id) throws IOException;


    public final int getRow() {
        return row;
    }

    public final void setRow(final int row) {
        this.row = row;
    }

    public final int getColumn() {
        return column;
    }

    public final void setColumn(final int column) {
        this.column = column;
    }
}
