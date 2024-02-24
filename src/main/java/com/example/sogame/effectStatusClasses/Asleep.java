package com.example.sogame.effectStatusClasses;

import com.example.sogame.mechanism.Fighter;
import com.example.sogame.status.EffectSection;
import com.example.sogame.status.StatusEffect;

public class Asleep extends StatusEffect {
    int turnsLeft;
    public Asleep(int turnLim, EffectSection section, int id, boolean stasisAffected, boolean isPositive, String s) {
        super(turnLim, section, id, stasisAffected, isPositive, s);
    }

    @Override
    public void initialEffect(Fighter fighter) {
        if (stasisAffected && fighter.isInStasis) {
            originalSection = section;
            section = EffectSection.STASIS;
        } else {
            turnsLeft = 3;
            fighter.asleep = true;
        }
    }

    @Override
    public void applyEffect(Fighter fighter) {
        if(!fighter.asleep || turnsLeft <=0) finalizeEffect(fighter);
        turnsLeft-=1;
    }

    @Override
    public void finalizeEffect(Fighter fighter) {
        fighter.deleteStatusEffect(this.id);
    }

    @Override
    public void enhanceEffect(int dmg, int turns, int chance) {
        turnsLeft+=turns;
    }
}
