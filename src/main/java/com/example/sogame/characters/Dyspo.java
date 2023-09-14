package com.example.sogame.characters;

import com.example.sogame.moves.Moves;

import java.util.ArrayList;

public class Dyspo extends Fighter {

    public Dyspo() {
        super("Dyspo",35,30,0,0,
                "FighterUI/Fighter_Dyspo_UI.png",
                "charmodels/dyspo.png");
        attacks = new ArrayList<>();
        attacks = generateMoves("MoveFiles/dyspoMoves.txt");
    }
}
