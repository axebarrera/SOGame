package com.example.sogame.characters;

import com.example.sogame.moves.Moves;

import java.util.ArrayList;

public class Ritalin extends Fighter {
    public Ritalin() {
        super("Ritalin",35,30,0,0,
                "FighterUI/Fighter_Ritalin_UI.png",
                "charmodels/alex.png");
        attacks = new ArrayList<>();
        attacks = generateMoves("ritalinMoves.txt");
    }
}
