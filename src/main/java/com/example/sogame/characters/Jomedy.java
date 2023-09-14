package com.example.sogame.characters;

import java.util.ArrayList;

public class Jomedy extends Fighter {

    public Jomedy() {
        super("Jomedy",40,30,0,0,
                "FighterUI/Fighter_Jomedy_UI.png",
                "charmodels/jomedy.png");
        attacks = new ArrayList<>();
        attacks = generateMoves("MoveFiles/jomedyMoves.txt");
    }

}
