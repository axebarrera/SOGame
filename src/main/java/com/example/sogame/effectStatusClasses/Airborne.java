package com.example.sogame.effectStatusClasses;

import com.example.sogame.characters.Fighter;
import com.example.sogame.status.EffectSection;
import com.example.sogame.status.StatusEffect;
import javafx.scene.image.Image;

public class Airborne extends StatusEffect {

    public Airborne(int turnLim, EffectSection section, int id, boolean stasisAffected, boolean isPositive, String s) {
        super(turnLim, section, id, stasisAffected, isPositive, s);
    }

    @Override
    public void initialEffect(Fighter fighter) {
        if (stasisAffected && fighter.isInStasis) {
            originalSection = section;
            section = EffectSection.STASIS;
        } else {
            fighter.invulnerable = true;
            fighter.untargetable = true;
            fighter.disabled = true;
        }

    }

    @Override
    public void applyEffect(Fighter fighter) {
        finalizeEffect(fighter);
    }

    @Override
    public void finalizeEffect(Fighter fighter) {
        fighter.invulnerable = false;
        fighter.untargetable = false;
        fighter.disabled = false;
        fighter.deleteStatusEffect(this.id);
    }

    @Override
    public void enhanceEffect(int dmg, int turns, int chance) {
        //This effect cannot be enhanced
    }
}
