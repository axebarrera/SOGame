package com.example.sogame.status;

//Way to determine when the effect should be executed
public enum EffectSection {
    BEGINNING,
    END,
    ONATTACK,

    ONDEFENSE,
    STASIS
}
/**
 * SETUP: MoveType is chosen through menu
 * SETUP: Attack id is saved before target selection
 * 1. Check move target mode and add targets to array
 * 2. Check if effects apply, those that do store in array (2 arrays, self and target)
 * 3. Check if move hits (make sure target is not disabled, if disabled skip to 7)
 * 5. If move hits apply self effects and initialize
 * 6. Move through targets array doing the following:
 *      6.1. Run on attack effects
 *      6.2. Calculate total damage being done
 *      6.3. Check ONDEFENSE status effects of target
 *      6.4. If skipApplicationStep=true go to next target or step 7
 *      6.5. Apply damage to target
 *      6.6. Apply and initialize effect on target
 * 7. Execute end of turn effects
 * 8. Different Possibilities for this step:
 *      8.1. If isDead skip turn completely
 *      8.2. If asleep, run Beginning and End effects then go next turn
 *      8.3. If None of the above move to next turn and run Beginning Effects
 * 9. Update Health, SP, UltMeter, and Status of Characters
 */
