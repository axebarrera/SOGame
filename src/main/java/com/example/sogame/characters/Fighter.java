package com.example.sogame.characters;

import com.example.sogame.StageFightController;
import com.example.sogame.moves.Moves;
import com.example.sogame.moves.TargetType;
import com.example.sogame.status.EffectSection;
import com.example.sogame.status.StatusEffect;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Fighter {
    public String name; //name of fighter
    public int maxhp; //Max HP of fighter, hp cant go over this value
    public int hp; //current hp of fighter
    public int maxsp; //Max SP of fighter, sp cant go over this value
    public int sp; //current sp of fighter
    public int defense; //base defense of fighter
    public int modDefense; //defense increase/decrease value held here
    public int ultPoints; //current ult points
    public int ultLim; //Limit of ult points
    public int attackDamage;//bonus attack damage
    public int maxSpecial; //Max Special Points a fighter is allowed to have
    public int special; //Current Special points a fighter has
    public int bonusDodgeChance; //Modifies the base chance(100) of being hit by a move
    public int effectApplyModifier; //Increase/Decrease chances of fighter's move from applying effect
    public int effectDodgeModifier; //Increase/Decrease chances of fighter receiving an effect
    public Fighter teammate; //Fighter's Teammate

    public boolean isInStasis;
    public boolean invulnerable;
    public boolean bulwark;
    public boolean untargetable;
    public boolean disabled;
    public boolean asleep;
    public boolean curse;
    public boolean mangled;
    public boolean sanctified;
    public boolean undying;
    public boolean taunted;
    public boolean silenced;
    public String charModel;
    public String charUI;

    public ArrayList<StatusEffect> effects;
    public ArrayList<Moves> attacks;
    public boolean isDead;

    StageFightController sfc;

    public Fighter(String name,int hp, int sp, int defense, int special, String charUI, String charModel) {
        this.name = name;
        this.hp = hp;
        this.sp = sp;
        this.defense = defense;
        this.ultLim = hp-10;
        this.special = special;
        ultPoints=0;
        attackDamage = 0;
        effects = new ArrayList<>();
        this.disabled = false;
        this.charUI = charUI;
        this.charModel = charModel;
        maxhp = hp;
        maxsp = sp;
        modDefense = 0;
        maxSpecial = special;
        bonusDodgeChance = 0;
        isInStasis = false;
        curse = false;
        mangled = false;
        sanctified=false;
        effectDodgeModifier=0;
        effectApplyModifier=0;
        isDead = false;
        undying = false;
        asleep =false;
        invulnerable = false;
        bulwark = false;
        untargetable = false;
        taunted = false;
        silenced = false;
    }

    public String getModel(){return charModel;}
    public String getUI(){return charUI;}

    public void setSFC(StageFightController SFC){
        this.sfc = SFC;
    }

    protected ArrayList<Moves> generateMoves(String filePath){
        ArrayList<Moves> list = new ArrayList<>();

        //Create reader to read Text File
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            //System.out.println("reading: "+filePath);
            //Go until there is no more lines left
            while ((line = reader.readLine()) != null) {
                String[] numberStrings = line.split(" "); // Split by space

                //System.out.println(Arrays.toString(numberStrings));
                int[] holder = new int[28];
                //Turn each string into an integer and place in holder
                for (int i=1;i<numberStrings.length-1;i++) {
                    try {
                        holder[i-1] = Integer.parseInt(numberStrings[i]);
                    } catch (NumberFormatException e) {
                        System.out.println("Skipped non-integer token: "+i);
                    }
                }
                //Create variables to call Moves Constructor
                String name = numberStrings[0].replace('_',' '); //Char name

                String description = numberStrings[numberStrings.length-1].replace('_',' ');

                int[] dmg = new int[5];//Damage per hit
                for(int i=0;i<5;i++){
                    dmg[i]=holder[i];
                }

                int[] rsrc = new int[3];//Resources
                for(int i=0;i<3;i++){
                    rsrc[i] = holder[i+5];
                }

                boolean isFrontal = (holder[9]>0)?true:false;
                boolean isUlt = (holder[11]>0)?true:false;
                TargetType moveType;
                switch (holder[12]){
                    case 0:
                        moveType = TargetType.INDIVIDUAL;
                        break;
                    case 1:
                        moveType = TargetType.TEAM;
                        break;
                    case 2:
                        moveType = TargetType.ALLEXCLUSIVE;
                        break;
                    case 3:
                        moveType = TargetType.ALLINCLUSIVE;
                        break;
                    default:
                        System.out.println("TargetType Incorrect: "+ name);
                        moveType = TargetType.INDIVIDUAL;
                        break;
                }

                ArrayList<Integer> effects = new ArrayList<>();
                for(int i=13;i<18 && holder[i]!=0;i++){
                    effects.add(holder[i]-1);
                }

                int[] effectChance = new int[5];
                for(int i=18;i<23;i++){
                    effectChance[i-18]=holder[i];
                }

                boolean[] effectMode = new boolean[5];
                for(int i=23;i<28;i++){
                    if(holder[i]==0)effectMode[i-23]=false;
                    else effectMode[i-23]=true;
                }

                //Assign Move to list
                Moves tempMove = new Moves(name,dmg,rsrc,holder[8],isFrontal,
                        holder[10],isUlt,moveType,effects,effectMode,effectChance,description);
                list.add(tempMove);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //Returns amount of damage done (dmg is positive, healing is negative)
    public int modifyHealth(int val){
        int initialHP = hp;
        if(sanctified && val>0)val = val+2;
        if(curse && val>0)val=val*-1;
        if(val>0){//positive
            if(mangled)val = (val>3)?val-3:0;
            if(!isInStasis){
                hp+=val;
                if(hp>maxhp)hp=maxhp;
            }
        }else{
            if(!invulnerable) {
                if(asleep && val<0)asleep = false;
                hp += val;
                modifyUlt(-val);
                if (hp < 0) hp = 0;
            }
        }
        return initialHP-hp;
    }

    public void modifySP(int val){
        if(val>0){//positive
            if(!isInStasis){
                sp+=val;
                if(sp>maxsp)sp=maxsp;
            }
        }else{
            sp+=val;
            if(sp<0) System.out.println("ERROR: SP SHOULD NEVER BE NEGATIVE");
        }
    }

    public void modifyUlt(int val){
        if(!isInStasis) {
            ultPoints += val;
            if (ultPoints > ultLim) ultPoints = ultLim;
        }
    }

    public int getDefense(){
        if(modDefense>0){
            return (defense+modDefense<5)?defense+modDefense:4;
        }else{
            return (defense+modDefense>-3)?modDefense+defense:-2;
        }
    }

    public int getAttackDamage(){
        return attackDamage;
    }

    public void executeStatusEffects(EffectSection s){
        for(StatusEffect e : effects){
            if(e.getSection() == s){
                e.applyEffect(this);
            }
        }
    }

    public void deleteStatusEffect(int id){
        for(int i=0;i<effects.size();i++){
            if(effects.get(i).isSameEffect(id)) effects.remove(i);
        }
    }

    public boolean ultimateAvailable() {
        return ultPoints==ultLim;
    }


    public boolean verifyChosenMove(int moveID){
        Moves m = attacks.get(moveID);

        //Check if target is silenced
        if(silenced && m.usesResources()) return false;

        //Check if fighter is taunted and if move contains status effects
        if(taunted && !m.effects.isEmpty()) return false;

        //Verify SP
        if(sp < m.resource[1]) return false;

        //Verify Health
        if(hp < m.resource[0])return false;

        //Verify Ultimate
        if(m.isUlt && !ultimateAvailable()) return false;

        return true;
    }

    public String invalidMoveErrorMessage(int moveID){
        Moves m = attacks.get(moveID);

        //Silenced
        if(silenced && m.usesResources()) return name + " is Silenced";

        //Check if fighter is taunted and if move contains status effects
        if(taunted && !m.effects.isEmpty()) return  name + " is Taunted";

        String template = "Not Enough ";

        //Verify SP
        if(sp < m.resource[1]) return template + "SP";

        //Verify Health
//        if(hp < m.resource[0]) return template + "HP";

        //Verify Ultimate
        if(m.isUlt && !ultimateAvailable()) return template + "Ultimate Points";

        return "ERROR: invalidMoveErrorMessage and verifyChosenMove differ outputs";
    }

    @Override
    public String toString(){
        return name;
    }
}
