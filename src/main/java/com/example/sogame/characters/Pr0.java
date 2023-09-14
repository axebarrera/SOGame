package com.example.sogame.characters;

import com.example.sogame.moves.Moves;

import java.util.ArrayList;

public class Pr0 extends Fighter {
    public Pr0() {
        super("Pr0",40,30,0,0,
                "FighterUI/Fighter_Pro_UI.png",
                "charmodels/pro.png");
        attacks = new ArrayList<>();
        attacks = generateMoves("MoveFiles/pr0Moves.txt");
    }
}
