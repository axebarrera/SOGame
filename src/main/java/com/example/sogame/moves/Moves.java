package com.example.sogame.moves;

import com.example.sogame.StageFightController;

import java.util.ArrayList;
import java.util.Arrays;

public class Moves {
    public String name;
    public int[] damage;//Total damage of move
    public int[] resource; //Should have 3 slots, 1: health, 2:sp, 3:special
    public int numHits;//Number of hits, if not attacking move then 0
    public boolean isFrontal;//Whether attack can be blocked by frontal barriers
    //1:Fire 2:Lightning 3:Ice
    public int element;//Element of attack
    public boolean isUlt;//Whether this attack uses Ultimate points
    //Targeting should select team
    public String description;
    public TargetType mode;
    public ArrayList<Integer> effects; //List of status effects attack will call
    public boolean[] effectToSelf;
    public int[] chanceOfEffectApplying;

    public Moves(String name, int[] damage, int[] resource,
                 int numHits, boolean isFrontal, int element,
                 boolean isUlt, TargetType mode, ArrayList<Integer> effects, boolean[] effectToSelf,
                 int[] chanceOfEffectApplying,
                 String description) {

        this.name = name;
        this.damage = damage;
        this.resource = resource;
        this.numHits = numHits;
        this.isFrontal = isFrontal;
        this.element = element;
        this.isUlt = isUlt;
        this.mode = mode;
        this.effects = effects;
        this.effectToSelf=effectToSelf;
        this.chanceOfEffectApplying=chanceOfEffectApplying;
        this.description = description;
    }

    public boolean usesResources(){
        if(isUlt) return false;
        for(int i=0;i<resource.length;i++){
            if(resource[i]!=0)return false;
        }
        return true;
    }

    public boolean containsEffect(int id){
        for(int i=0;i<effects.size();i++){
            if(id == effects.get(i))return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return name+"{" +
                "damage=" + Arrays.toString(damage) +
                ", resource=" + Arrays.toString(resource) +
                ", numHits=" + numHits +
                ", isFrontal=" + isFrontal +
                ", element=" + element +
                ", isUlt=" + isUlt +
                ", mode=" + mode +
                ", effects=" + effects +
                '}';
    }
    //Health, Resources, Number of Hits, isFrontal(Boolean), element(still not included), effects(go until 0 is reached)




}
