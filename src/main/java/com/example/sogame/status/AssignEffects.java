package com.example.sogame.status;

import com.example.sogame.effectStatusClasses.*;
import com.example.sogame.effectStatusClasses.Void;

//Class designed to create the object of a status effect and return to caller
public class AssignEffects {
    public static StatusEffect createEffect(int id){
        StatusEffect status=null;
        //Should have 40 cases (0-39) for all status effects
        switch (id){
            case 0:
                status = createDefenseUp();
                break;
            case 1:
                status = createAttackUp();
                break;
            case 2:
                status = createCharged();
                break;
            case 3:
                status = createEvasionup();
                break;
            case 4:
                status = createHpRegen();
                break;
            case 5:
                status = createSpRegen();
                break;
            case 6:
                status = createStatusStasis();
                break;
            case 7:
                status = createSilenced();
                break;
            case 8:
                status = createConfused();
                break;
            case 9:
                status = createBurned();
                break;
            case 10:
                status = createParalyzed();
                break;
            case 11:
                status = createFrostBite();
                break;
            case 12:
                status = createAirborne();
                break;
            case 13:
                status = createBulwark();
                break;
            case 14:
                status = createDefer();
                break;
            case 15:
                status = createHemorrhage();
                break;
            case 16:
                status = createSuppressed();
                break;
            case 17:
                status = createAsleep();
                break;
            case 18:
                status = createDizzy();
                break;
            case 19:
                status = createPoison();
                break;
            case 20:
                status = createDefenseDown();
                break;
            case 21:
                status = createAttackDown();
                break;
            case 22:
                status = createEvasionDown();
                break;
            case 23:
                status = createStatusResistance();
                break;
            case 24:
                status = createSanctified();
                break;
            case 25:
                status = createPupDDown();
                break;
            case 26:
                status = createLucky();
                break;
            case 27:
                status = createDupPdown();
                break;
            case 28:

                break;
            case 29:
                status = createUndying();
                break;
            case 30:
                status = createTaunted();
                break;
            case 31:
                status = createElementalCharge();
                break;
            case 32:
                status = createCursed();
                break;
            case 33:
                status = createDefensePierce();
                break;
            case 34:
                status = createInvulnerable();
                break;
            case 35:
                status = createCharmed();
                break;
            case 36:
                status = createLifeSteal();
                break;
            case 37:
                status = createBloodLink();
                break;
            case 38:
                status = createMangled();
                break;
            case 39:
                status = createVoid();
                break;
            case 40:
                status = createBackline();
                break;
            case 41:
                status = createExpedited();
                break;

            default:
                //Should never reach this
                System.out.println("Invalid effect ID");
                break;
        }
        return status;
    }

    public static StatusEffect createDefenseUp(){
        return new Defenseup(2,EffectSection.END,0,true,true,"effectIcons/effectIcons/defense_boost.png");
    }
    public static StatusEffect createAttackUp(){
        return new Attackup(2,EffectSection.END,1,true,true,"effectIcons/attack_boost.png");
    }
    public static StatusEffect createCharged(){
        return new Charged(0,EffectSection.ONATTACK,2,true,true,"effectIcons/charged.png");
    }
    public static StatusEffect createEvasionup(){
        return new Evasionup(3,EffectSection.END,3,false,true,"effectIcons/evasion_boost.png");
    }
    public static StatusEffect createHpRegen(){
        return new Hpregen(3,EffectSection.BEGINNING,4,true,true,"effectIcons/hp_regenerate.png");
    }
    public static StatusEffect createSpRegen(){
        return new Spregen(3,EffectSection.BEGINNING,5,true,true,"effectIcons/sp_regenerate.png");
    }
    public static StatusEffect createStatusStasis(){
        return new Statusstasis(2,EffectSection.END,6,false,false,"effectIcons/status_stasis.png");
    }
    public static StatusEffect createSilenced(){
        return new Silenced(2,EffectSection.END,7,false,false,"effectIcons/silenced.png");
    }
    public static StatusEffect createConfused(){
        return new Confused(3,EffectSection.END,8,false,false,"effectIcons/confused.png");
    }
    public static StatusEffect createBurned(){
        return new Burn(2,EffectSection.END,9,false,false,"effectIcons/burned.png");
    }
    public static StatusEffect createParalyzed(){
        return new Paralyzed(3,EffectSection.BEGINNING,10,false,false,"effectIcons/paralyzed.png");
    }
    public static StatusEffect createFrostBite(){
        return new Frostbite(3,EffectSection.END,11,false,false,"effectIcons/frostbite.png");
    }
    public static StatusEffect createAirborne(){
        return new Airborne(1,EffectSection.BEGINNING,12,false,false,"effectIcons/airborne.png");
    }
    public static StatusEffect createBulwark(){
        return new Bulwark(1,EffectSection.BEGINNING,13,false,false,"effectIcons/bulwark.png");
    }
    public static StatusEffect createDefer(){
        return new Defer(1,EffectSection.BEGINNING,14,false,false,"effectIcons/defer.png");
    }
    public static StatusEffect createHemorrhage(){
        return new Hemorrhage(2,EffectSection.END,15,false,false,"effectIcons/hemorrhage.png");
    }
    public static StatusEffect createSuppressed(){
        return new Suppressed(2,EffectSection.END,16,false,false,"effectIcons/suppressed.png");
    }
    public static StatusEffect createAsleep(){
        return new Asleep(3,EffectSection.BEGINNING,17,false,false,"effectIcons/asleep.png");
    }
    public static StatusEffect createDizzy(){
        return new Dizzy(3,EffectSection.ONATTACK,18,false,false,"effectIcons/dizzy.png");
    }
    public static StatusEffect createPoison(){
        return new Poisoned(3,EffectSection.END,19,false,false,"effectIcons/poisoned.png");
    }
    public static StatusEffect createDefenseDown(){
        return new Defensedown(2,EffectSection.END,20,false,false,"effectIcons/defense_reduction.png");
    }
    public static StatusEffect createAttackDown(){
        return new Attackdown(2,EffectSection.END,21,false,false,"effectIcons/attack_reduction.png");
    }
    public static StatusEffect createEvasionDown(){
        return new Evasiondown(3,EffectSection.END,22,false,false,"effectIcons/evasion_reduction.png");
    }
    public static StatusEffect createStatusResistance(){
        return new Statusresistance(0,EffectSection.END,23,false,false,"effectIcons/status_resistance.png");
    }
    public static StatusEffect createSanctified(){
        return new Sanctified(3,EffectSection.END,24,true,true,"effectIcons/sanctified.png");
    }
    public static StatusEffect createPupDDown(){
        return new Pupddown(3,EffectSection.END,25,true,true,"effectIcons/p_up_-_d_down.png");
    }
    public static StatusEffect createLucky(){
        return new Lucky(0,EffectSection.END,26,true,true,"effectIcons/lucky.png");
    }
    public static StatusEffect createDupPdown(){
        return new Duppdown(3,EffectSection.END,27,true,true,"effectIcons/d_up_-_p_down.png");
    }
    public static StatusEffect createUndying(){
        return new Undying(2,EffectSection.END,29,false,true,"effectIcons/undying.png");
    }
    public static StatusEffect createTaunted(){
        return new Taunted(2,EffectSection.END,30,false,false,"effectIcons/taunted.png");
    }
    public static StatusEffect createElementalCharge(){
        return new Elementalcharge(3,EffectSection.ONATTACK,31,false,true,"effectIcons/elemental_charge.png");
    }
    public static StatusEffect createCursed(){
        return new Cursed(3,EffectSection.END,32,false,false,"effectIcons/cursed.png");
    }
    public static StatusEffect createDefensePierce(){
        return new Defensepierce(0,EffectSection.ONATTACK,33,false,true,"effectIcons/defense_pierce.png");
    }
    public static StatusEffect createInvulnerable(){
        return new Invulnerable(1,EffectSection.END,34,false,true,"effectIcons/invulnerable.png");
    }
    public static StatusEffect createCharmed(){
        return new Charmed(2,EffectSection.END,35,false,false,"effectIcons/charmed.png");
    }
    public static StatusEffect createLifeSteal(){
        return new Lifesteal(0,EffectSection.END,36,true,true,"effectIcons/lifesteal.png");
    }
    public static StatusEffect createBloodLink(){
        return new Bloodlink(0,EffectSection.BEGINNING,37,false,false,"effectIcons/blood_link.png");
    }
    public static StatusEffect createMangled(){
        return new Mangled(4,EffectSection.END,38,false,false,"effectIcons/mangled.png");
    }
    public static StatusEffect createVoid(){
        return new Void(0,EffectSection.BEGINNING,39,false,false,"effectIcons/void.png");
    }
    public static StatusEffect createBackline(){
        return new Backline(0,EffectSection.END,40,false,false,"effectIcons/evasion_boost.png");
    }
    public static StatusEffect createExpedited(){
        return new Expedited(0,EffectSection.BEGINNING,41,false,false,"effectIcons/expedited.png");
    }


}
