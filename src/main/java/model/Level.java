package model;

import java.io.*;

public class Level {

    private String string;
    private int diamondValue;
    private int nbDiamondToDisplayOutlet;
    private int levelInt;

    /**
     * Constructor of a Level
     * Initialise the attribute string  with the file who correspond to the level
     * Initialise levelInt, diamondValue, nbDiamondToDisplayOutlet according
     * to the int at param
     *
     * @param nbLevel
     */
    public Level(int nbLevel) {
        levelInt = nbLevel;
        if (!(nbLevel > 0 && nbLevel <= 3)) {
            throw new RuntimeException("This level don't exist");
        }
        try (var in = Level.class.getResourceAsStream("/level/level" + nbLevel + ".txt")) {
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader buffer = new BufferedReader(isr);

            String line = buffer.readLine();
            StringBuilder builder = new StringBuilder();

            while (line != null) {
                builder.append(line).append("\n");
                line = buffer.readLine();
            }

            string = builder.toString();
        } catch (IOException e) {

        }
        switch (nbLevel) {
            case 1 -> {
                diamondValue = 10;
                nbDiamondToDisplayOutlet = 10;
            }
            case 2 -> {
                diamondValue = 20;
                nbDiamondToDisplayOutlet = 11;
            }
            case 3 -> {
                diamondValue = 30;
                nbDiamondToDisplayOutlet = 12;
            }
        }

    }

    /**
     * Getter of the level diamondValue
     *
     * @return int
     */
    public int getDiamondValue() {
        return diamondValue;
    }

    /**
     * Getter of the level nbDiamondToDisplayOutlet
     *
     * @return int
     */
    public int getNbDiamondToDisplayOutlet() {
        return nbDiamondToDisplayOutlet;
    }

    /**
     * Getter of the string level
     *
     * @return
     */
    public String getString() {
        return string;
    }

    /**
     * Getter of the number of the level
     *
     * @return
     */
    public int getLevelInt() {
        return levelInt;
    }
}


