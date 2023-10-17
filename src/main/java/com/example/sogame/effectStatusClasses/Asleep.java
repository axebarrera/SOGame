package com.example.sogame.effectStatusClasses;

import com.example.sogame.characters.Fighter;
import com.example.sogame.status.EffectSection;
import com.example.sogame.status.StatusEffect;
import javafx.scene.image.Image;

public class Asleep extends StatusEffect {
    public Asleep(int turnLim, EffectSection section, int id, boolean stasisAffected, boolean isPositive, String s) {
        super(turnLim, section, id, stasisAffected, isPositive, s);
    }

    @Override
    public void initialEffect(Fighter fighter) {
        if (stasisAffected && fighter.isInStasis) {
            originalSection = section;
            section = EffectSection.STASIS;
        } else {
            fighter.asleep = true;
        }
    }

    @Override
    public void applyEffect(Fighter fighter) {
        if(!fighter.asleep || turns <=0) finalizeEffect(fighter);
        turns-=1;
    }

    @Override
    public void finalizeEffect(Fighter fighter) {
        fighter.deleteStatusEffect(this.id);
    }

    @Override
    public void enhanceEffect(int dmg, int turns, int chance) {
        if(turns == 0) this.turns = this.turnLim;
        this.turns+=turns;
    }
}
