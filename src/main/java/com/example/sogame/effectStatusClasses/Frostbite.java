package com.example.sogame.effectStatusClasses;

import com.example.sogame.mechanism.Fighter;
import com.example.sogame.status.EffectSection;
import com.example.sogame.status.StatusEffect;

public class Frostbite extends StatusEffect {
    public Frostbite(int turnLim, EffectSection section, int id, boolean stasisAffected, boolean isPositive, String s) {
        super(turnLim, section, id, stasisAffected, isPositive, s);
    }

    @Override
    public void initialEffect(Fighter fighter) {
        if (stasisAffected && fighter.isInStasis) {
            originalSection = section;
            section = EffectSection.STASIS;
        } else {
            // TODO
        }
        
        return;
    }

    @Override
    public void applyEffect(Fighter fighter) {
        // TODO
        return;
    }

    @Override
    public void finalizeEffect(Fighter fighter) {
        // TODO
        return;
    }

    @Override
    public void enhanceEffect(int dmg, int turns, int chance) {
        // TODO
        return;
    }
}
