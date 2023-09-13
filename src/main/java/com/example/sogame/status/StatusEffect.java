package com.example.sogame.status;

import com.example.sogame.characters.Fighter;
import javafx.scene.image.Image;

public class StatusEffect {
    protected int turns;

    protected EffectSection section; //0:beginning of turn, 1:end of turn, 2:On Attack

    protected EffectSection originalSection;

    protected int id;

    protected boolean stasisAffected;
    protected boolean isPositive;
    public String effectIcon;

    protected boolean killed; //Safety measure, should be checked for in finalize and should be set to true in finalize

    public StatusEffect(int turnLim, EffectSection section, int id, boolean stasisAffected, boolean isPositive,String icon) {
        this.turns = turnLim;
        this.section=section;
        killed=false;
        this.id = id;
        this.stasisAffected = stasisAffected;
        this.isPositive = isPositive;
        this.effectIcon = icon;
    }

    /**
     * Executed when effect is applied for first time
     */
    public void initialEffect(Fighter fighter){

        return;
    }

    public void enhanceEffect(int dmg,int turns,int chance){
        return;
    }

    public boolean isSameEffect(int id){
        if(this.id == id) return true;
        return false;
    }
    /**
     * Executed at the moment section is correct
     */
    public void applyEffect(Fighter fighter){
        return;
    }

    /**
     * Executed when turn reaches 0
     */
    public void finalizeEffect(Fighter fighter){
        return;
    }

    public EffectSection getSection(){
        return section;
    }

    public int getTurns(){
        return turns;
    }
}
