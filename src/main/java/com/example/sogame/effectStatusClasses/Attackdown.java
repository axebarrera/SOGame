package com.example.sogame.effectStatusClasses;

import com.example.sogame.characters.Fighter;
import com.example.sogame.status.EffectSection;
import com.example.sogame.status.StatusEffect;
import javafx.scene.image.Image;

public class Attackdown extends StatusEffect {
    int totalReduction = 0;
    public Attackdown(int turnLim, EffectSection section, int id, boolean stasisAffected, boolean isPositive, String s) {
        super(turnLim, section, id, stasisAffected, isPositive, s);
    }

    @Override
    public void initialEffect(Fighter fighter) {
        if (stasisAffected && fighter.isInStasis) {
            originalSection = section;
            section = EffectSection.STASIS;
        } else {

        }
        
        return;
    }

    @Override
    public void applyEffect(Fighter fighter) {
        if(turns<=0) finalizeEffect(fighter);
        turns -= 1;
    }

    @Override
    public void finalizeEffect(Fighter fighter) {

    }

    @Override
    public void enhanceEffect(int dmg, int turns, int chance, Fighter fighter) {

    }
}
