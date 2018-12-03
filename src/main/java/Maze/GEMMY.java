package Maze;

import inventory.Models.Gem;

import java.util.ArrayList;

public class GEMMY {
    public static Gem PINKGEM = new Gem("gem", "pink gem", "", 50, 50, true, 100);
    public static Gem GREENGEM = new Gem("ggem", "green gem", "", 50, 50, true, 100);
    public static Gem BLUEGEM = new Gem("bgem", "blue gem", "", 50, 50, true, 100);

    public static ArrayList<Gem> gemList(int pinkAmnt, int blueAmnt, int greenAmnt) {
        ArrayList<Gem> gems = new ArrayList<Gem>();
        Gem pgems[] = new Gem[pinkAmnt];
        Gem ggems[] = new Gem[greenAmnt];
        Gem bgems[] = new Gem[blueAmnt];
        for (int i = 0; i < pinkAmnt; i++) {
            PINKGEM = new Gem("gem", "pink gem", "", 50, 50, true, 100);
            pgems[i] = PINKGEM;
            gems.add(pgems[i]);
        }
        for (int i = 0; i < greenAmnt; i++) {
            GREENGEM = new Gem("ggem", "green gem", "", 50, 50, true, 100);
            ggems[i] = GREENGEM;
            gems.add(ggems[i]);
        }
        for (int i = 0; i < blueAmnt; i++) {
            BLUEGEM = new Gem("bgem", "blue gem", "", 50, 50, true, 100);
            bgems[i] = BLUEGEM;
            gems.add(bgems[i]);
        }
        return gems;

    }
}