package com.example.sogame.effectStatusClasses;

import com.example.sogame.characters.Fighter;
import com.example.sogame.status.EffectSection;
import com.example.sogame.status.StatusEffect;
import javafx.scene.image.Image;

public class Bloodlink extends StatusEffect {
    public Bloodlink(int turnLim, EffectSection section, int id, boolean stasisAffected, boolean isPositive, String s) {
        super(turnLim, section, id, stasisAffected, isPositive, s);
    }

    @Override
    public void initialEffect(Fighter fighter) {
        if (stasisAffected && fighter.isInStasis) {
            originalSection = section;
            section = EffectSection.STASIS;
        } else {
            //Set bloodlinked flag
            fighter.bloodLinked=true;
            fighter.getSFC().currF.bloodLinked = true;

            //Set Bloodlinked partners
            fighter.bloodBuddy = fighter.getSFC().currF; //Sets targets bloodbuddy
            fighter.bloodBuddy.bloodBuddy = fighter; //Sets Casters bloodbuddy

            applyEffect(fighter);
        }

    }

    @Override
    public void applyEffect(Fighter fighter) {
        Fighter partner = fighter.bloodBuddy;

    }

    //Questions to ask, How is everything copied over, through reference or value (Do I pass the object or update the values)
    //This affects turn ticking on effects
    private void copyEffects(Fighter src, Fighter dest){
        //This method should Allow for the copying of effects, built to remove redundancy.
    }

    @Override
    public void finalizeEffect(Fighter fighter) {
        // TODO
        return;
    }

    @Override
    public void enhanceEffect(int dmg, int turns, int chance) {
        //Cannot be enhanced
    }
}
