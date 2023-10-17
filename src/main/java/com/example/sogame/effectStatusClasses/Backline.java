package com.example.sogame.effectStatusClasses;

import com.example.sogame.characters.Fighter;
import com.example.sogame.status.EffectSection;
import com.example.sogame.status.StatusEffect;
import javafx.scene.image.Image;

public class Backline extends StatusEffect {
    public Backline(int turnLim, EffectSection section, int id, boolean stasisAffected, boolean isPositive, String s) {
        super(turnLim, section, id, stasisAffected, isPositive, s);
    }

    @Override
    public void initialEffect(Fighter fighter) {
        if (stasisAffected && fighter.isInStasis) {
            originalSection = section;
            section = EffectSection.STASIS;
        } else {
            fighter.bonusDodgeChance += 30;
        }
        
        return;
    }

    @Override
    public void applyEffect(Fighter fighter) {
        //Repeated application does nothing
    }

    @Override
    public void finalizeEffect(Fighter fighter) {
        fighter.bonusDodgeChance -= 30;
    }

    @Override
    public void enhanceEffect(int dmg, int turns, int chance) {
        //Effect cannot be enhanced
    }
}
