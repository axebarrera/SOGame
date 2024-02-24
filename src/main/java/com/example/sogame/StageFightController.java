package com.example.sogame;

import com.example.sogame.mechanism.Fighter;
import com.example.sogame.moves.Moves;
import com.example.sogame.moves.TargetType;
import com.example.sogame.status.AssignEffects;
import com.example.sogame.status.EffectSection;
import com.example.sogame.status.StatusEffect;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.Random;

public class StageFightController implements SceneSwitcher{
    private HelloApplication application;
    //Constant Variables
    private final int BEGINBASIC = 0;
    private final int ENDBASIC = 2;
    private final int BEGINABILITIES = 3;
    private final int ENDABILITIES = 6;
    private final int BEGINULTIMATES = 7;
    private final int ENDULTIMATES = 8;
    //Characters organized from left to right
    //Initialize FXML connections
    @FXML
    private ImageView char1,char2,char3,char4;
    @FXML
    private ImageView char1UI,char2UI,char3UI,char4UI;
    @FXML
    private Text char1HP,char2HP,char3HP,char4HP;
    @FXML
    private Text char1SP,char2SP,char3SP,char4SP;
    @FXML
    private Text char1Ult,char2Ult,char3Ult,char4Ult;
    @FXML
    private Text char1Name,char2Name,char3Name,char4Name;
    @FXML
    private Pane char1NamePane,char2NamePane,char3NamePane,char4NamePane;
    @FXML
    private Text turnNameText,generalMovesUITitle,descriptionUIText;
    @FXML
    private Pane mainUIItems,mainUIAttack,mainUITactics;
    @FXML
    private Pane attackUIPane,generalMovesUIPane,descriptionUIPane;
    @FXML
    private HBox attackUIBasic,attackUIAbilities,attackUIUltimates,mainUIMenu;
    @FXML
    private VBox generalMovesUIVbox;
    @FXML
    private Circle attackUIExit,generalMovesUIExit;
    @FXML
    private ImageView selectionUIExit;

    //Initialize Global Variables
    int[] turnOrder;
    int currTurn=0;
    Fighter[] fighters;
    ArrayList<EventHandler<MouseEvent>> mouseEventsList = new ArrayList<>();
    ArrayList<EventHandler<MouseEvent>> mouseHoverEventsList = new ArrayList<>();
    ArrayList<Pane> paneEventList = new ArrayList<>();
    Font moveFont = new Font(30);
    Random numGenerator = new Random();

    public int lastMoveID;
    public ArrayList<Fighter> lastMoveTargets = new ArrayList<>();
    public int turnType;//0=Items,1=Attacks,2=Tactics
    public int totalDamageDone;
    public boolean skipApplicationStep = false;

    /** New Setup **/


    /** End New Setup **/


    /** Initialization */
    @Override
    public void setApplication(HelloApplication application) {
        this.application = application;
    }

    public void initialize(){
        //Initialize mainUI
        mainUIInit();
        attackUIInit();
    }

    public void mainUIInit(){
        //TODO: Items and Tactics Initialization for later
        mainUIAttack.setOnMouseClicked(event->enterAttackMenu());

        //Character Models Hover for choosing
        char1.setOnMouseEntered(event->namePlateAppearHandler(char1NamePane));
        char2.setOnMouseEntered(event->namePlateAppearHandler(char2NamePane));
        char3.setOnMouseEntered(event->namePlateAppearHandler(char3NamePane));
        char4.setOnMouseEntered(event->namePlateAppearHandler(char4NamePane));

        char1.setOnMouseClicked(event->executeTurn(fighters[0]));
        char2.setOnMouseClicked(event->executeTurn(fighters[1]));
        char3.setOnMouseClicked(event->executeTurn(fighters[2]));
        char4.setOnMouseClicked(event->executeTurn(fighters[3]));

        char1.setOnMouseExited(event->namePlateRemoveHandler(char1NamePane));
        char2.setOnMouseExited(event->namePlateRemoveHandler(char2NamePane));
        char3.setOnMouseExited(event->namePlateRemoveHandler(char3NamePane));
        char4.setOnMouseExited(event->namePlateRemoveHandler(char4NamePane));

        selectionUIExit.setOnMouseClicked(event->cancelMove());
    }

    public void attackUIInit(){
        attackUIExit.setOnMouseClicked(event->exitAttackMenu());
        attackUIBasic.setOnMouseClicked(event-> enterAttackGeneralMenu("BASIC",BEGINBASIC,ENDBASIC));
        attackUIAbilities.setOnMouseClicked(event-> enterAttackGeneralMenu("ABILITIES",BEGINABILITIES,ENDABILITIES));
        attackUIUltimates.setOnMouseClicked(event-> enterAttackGeneralMenu("ULTIMATES",BEGINULTIMATES,ENDULTIMATES));
        generalMovesUIExit.setOnMouseClicked(event-> exitAttackGeneralMenu());
    }

    public void startGame(int[] turnOrder, Fighter[] fighters){
        this.turnOrder = turnOrder;
        this.fighters = fighters;
        //Generate Models and UI
        setModelsAndUI();

        //Initialize Health, SP, UltMeter, and Special Points
        updateUIStats();

        //Set the name for the first Turn
        setTurnName();

        //Set Teammates
        setTeammates();
    }

    /** END INITIALIZATION */

    /** UI INTERACTION FUNCTIONS */
    public void namePlateAppearHandler(Pane p){
        p.setVisible(true);
    }
    public void namePlateRemoveHandler(Pane p){
        p.setVisible(false);
    }

    public void enterAttackMenu(){
        attackUIPane.setVisible(true);
        turnType=1;
    }

    public void exitAttackMenu(){
        attackUIPane.setVisible(false);
    }

    //Basic, Abilities, and Ultimates Menus should call this function with appropriate parameters
    public void enterAttackGeneralMenu(String title, int beginning, int end){
        Fighter f = fighters[turnOrder[currTurn]];
        generalMovesUITitle.setText(title);
        generateListOfMoves(generalMovesUIVbox,f.attacks,beginning,end);
        attackUIPane.setVisible(false);
        generalMovesUIPane.setVisible(true);
        mainUIMenu.setVisible(false);
        descriptionUIPane.setVisible(true);
    }

    public void exitAttackGeneralMenu(){
        //Will use Turn Type to determine which menu to make visible
        attackUIPane.setVisible(true);

        generalMovesUIPane.setVisible(false);
        clearMouseEvents();
        generalMovesUIVbox.getChildren().clear();
        mainUIMenu.setVisible(true);
        descriptionUIPane.setVisible(false);
    }

    public void chooseMove(int id){

        Fighter f  = fighters[turnOrder[currTurn]];
        if(f.verifyChosenMove(id)) {
            if (!fighters[0].untargetable) char1.setDisable(false);
            if (!fighters[1].untargetable) char2.setDisable(false);
            if (!fighters[2].untargetable) char3.setDisable(false);
            if (!fighters[3].untargetable) char4.setDisable(false);
            generalMovesUIPane.setVisible(false);
            selectionUIExit.setVisible(true);
            lastMoveID = id;
        }else{
            //Add Error Textbox, print statement is temporary
            System.out.println(f.invalidMoveErrorMessage(id));
        }
    }

    public void cancelMove(){
        disableCharacters();
        generalMovesUIPane.setVisible(true);
        selectionUIExit.setVisible(false);
    }

    public void disableCharacters(){
        char1.setDisable(true);
        char2.setDisable(true);
        char3.setDisable(true);
        char4.setDisable(true);
    }

    public void resetGUIAfterMove(){
        //Characters should be disabled by this point
        //Removes X to back out
        selectionUIExit.setVisible(false);
        descriptionUIPane.setVisible(false);
        mainUIMenu.setVisible(true);
        clearMouseEvents();
        generalMovesUIVbox.getChildren().clear();
        setTurnName();

    }

    public void setDescriptionText(String content){
        descriptionUIText.setText(content);
    }

    /** END UI INTERACTION FUNCTIONS */

    /** GAME MECHANICS START */

    public void executeTurn(Fighter target){
        //Disable Characters to ensure no repeats are encountered
        disableCharacters();
        //Removes x to prevent unwanted interactions
        selectionUIExit.setVisible(false);

        //Get Basic Objects
        Fighter caster = fighters[turnOrder[currTurn]];
        Moves move = caster.attacks.get(lastMoveID);
        //Drain Resources from caster
        caster.hp -= move.resource[0];
        caster.sp -= move.resource[1];
        caster.special -= move.resource[2];
        caster.modifyUlt(move.resource[0]);
        if(move.isUlt) caster.ultPoints = 0;
        //Add Targets to List
        addTargetsToList(target,caster,move);
        //Generate and calculate self affecting status effects only
        for(int i=0;i<move.effects.size();i++){
            if(move.effectToSelf[i] && getPercentageResult(move.chanceOfEffectApplying[i])){
                StatusEffect effect = AssignEffects.createEffect(move.effects.get(i));
                caster.effects.add(effect);
                effect.initialEffect(caster);//Initiate Effects
            }
        }
        //Check if fighter is able to attack
        if(!caster.disabled){
            totalDamageDone = 0;
            //Cycle through all targets from right to left
            for(int i=lastMoveTargets.size()-1;i>=0;i--){
                //Get current target
                Fighter t = lastMoveTargets.get(i);
                //Check if attack hits this target
                if(getPercentageResult(100-t.bonusDodgeChance)){
                    //Activate all on attack effects
                    caster.executeStatusEffects(EffectSection.ONATTACK);
                    //Get damage that occurs on target
                    int currentDamage = calculateDamageOfMove(t,caster,move);

                    if(!skipApplicationStep) {
                        //Check which effects land
                        for (int j = 0; j < move.effects.size(); j++) {
                            if (!move.effectToSelf[i] && getPercentageResult(move.chanceOfEffectApplying[i] +
                                    caster.effectApplyModifier - t.effectDodgeModifier)) {
                                StatusEffect e = AssignEffects.createEffect(move.effects.get(i));
                                t.effects.add(e);
                                e.initialEffect(t);
                            }
                        }
                        //Apply Damage
                        totalDamageDone += t.modifyHealth(-currentDamage);
                    }
                    //Reset ONATTACK Effects
                    caster.executeStatusEffects(EffectSection.ONATTACK);
                }
            }

        }

        //Run end of turn effects
        caster.executeStatusEffects(EffectSection.END);
        nextTurn();
        Fighter nextF = fighters[turnOrder[currTurn]];
        while(nextF.asleep || nextF.isDead || nextF.disabled){
            if(nextF.asleep || nextF.disabled){
                //Run Beginning Effects
                nextF.executeStatusEffects(EffectSection.BEGINNING);

                //Run End Effects
                nextF.executeStatusEffects(EffectSection.END);
            }
            nextTurn();
            nextF = fighters[turnOrder[currTurn]];
        }
        //Run Beginning Effects
        nextF.executeStatusEffects(EffectSection.BEGINNING);

        //Update UI Values
        updateUIStats();
        //Resets GUI to beginning of turn
        resetGUIAfterMove();
        //TODO: ADD LOGS OUTPUT


    }

    public int calculateDamageOfMove(Fighter target, Fighter caster, Moves move){
        int sum = 0;
        if(!move.isFrontal || !target.bulwark) {
            for (int i = 0; i < move.damage.length && move.damage[i] != 0; i++) {
                int temp = move.damage[i] + caster.getAttackDamage() - target.getDefense();
                sum += Math.max(temp, 0);
            }
        }
        return sum;
    }

    public void addTargetsToList(Fighter target,Fighter caster,Moves move){
        lastMoveTargets.clear();

        TargetType mode = move.mode;
        if(mode == TargetType.INDIVIDUAL){
            lastMoveTargets.add(target);
        }else if(mode == TargetType.TEAM){
            lastMoveTargets.add(target);
            lastMoveTargets.add(target.teammate);
        }else if(mode == TargetType.ALLEXCLUSIVE){
            lastMoveTargets.add(target);
            lastMoveTargets.add(target.teammate);
            lastMoveTargets.add(caster.teammate);
        }else if(mode == TargetType.ALLINCLUSIVE){
            lastMoveTargets.add(target);
            lastMoveTargets.add(target.teammate);
            lastMoveTargets.add(caster.teammate);
            lastMoveTargets.add(caster);
        }

    }

    /**
     * provide parent VBox where list will be created
     * provide moveList for character
     * index for start and end, both are inclusive
     */
    public void generateListOfMoves(VBox parent,ArrayList<Moves> moveList,int start,int end){
        for(int i=start;i<end+1;i++){
            //Generate Name and SPCost
            Moves move = moveList.get(i);
            Text moveName = formatTextForMoveMenu(move.name);
            TextFlow tf = new TextFlow(moveName);
            tf.setPrefWidth(250);

            Text spCost = formatTextForMoveMenu((move.resource[1])+" SP");
            
            //This VBox Contains the move and effects HBoxes
            VBox container = new VBox();
            parent.getChildren().add(container);

            //Generate Move HBox
            HBox top = new HBox(); //Create Objects
            VBox left = new VBox();
            VBox right = new VBox();

            top.setHgrow(left,Priority.ALWAYS); //Set Hgrow property
            top.setHgrow(right,Priority.ALWAYS);

            left.setAlignment(Pos.CENTER_LEFT); //Set Alignment Correctly for each
            right.setAlignment(Pos.CENTER_RIGHT);

            left.setPrefHeight(moveName.getLayoutBounds().getHeight()+4);//Wrap VBoxes around text with some margin
            right.setPrefHeight(spCost.getLayoutBounds().getHeight()+4);

            top.getChildren().addAll(left,right);//Add left and right to HBox
            left.getChildren().add(tf);
            right.getChildren().add(spCost);
            //Generate Effect HBox
            HBox bot = new HBox();
            ArrayList<Image> icons = new ArrayList<>();
            for(Integer n : move.effects){
                StatusEffect e = AssignEffects.createEffect(n);
                icons.add(new Image(getClass().getResource(e.effectIcon).toExternalForm()));
            }
            for(Image im: icons){
                ImageView iv = new ImageView(im);
                modifyGeneralUIImage(iv);
                bot.getChildren().add(iv);
            }
            //Add Move and Effect HBox to Container
            container.getChildren().addAll(top,bot);

            //Create Event Listener
            final int id = i;

            //Creates Events for clicking the move to execute and hovering allows for description to be displayed
            EventHandler<MouseEvent> eventHandler = event -> chooseMove(id);
            EventHandler<MouseEvent> hoverHandler = event -> setDescriptionText(move.description);
            container.setOnMouseClicked(eventHandler);
            container.setOnMouseEntered(hoverHandler);
            mouseHoverEventsList.add(hoverHandler);
            mouseEventsList.add(eventHandler);
            paneEventList.add(container);
        }
    }

    public void modifyGeneralUIImage(ImageView iv){
        iv.setFitWidth(60);
        iv.setFitHeight(30);
        iv.setPreserveRatio(true);
    }

    public Text formatTextForMoveMenu(String content){
        Text t = new Text();
        t.setText(content);
        t.getStyleClass().add("SMBFontLetters");
        t.setFont(moveFont);
        return t;
    }

    /** UPDATE UI VALUES */

    public void setModelsAndUI(){
        char1.setImage(new Image(getClass().getResource(fighters[0].getModel()).toExternalForm()));
        char1UI.setImage(new Image(getClass().getResource(fighters[0].getUI()).toExternalForm()));

        char2.setImage(new Image(getClass().getResource(fighters[1].getModel()).toExternalForm()));
        char2UI.setImage(new Image(getClass().getResource(fighters[1].getUI()).toExternalForm()));

        char3.setImage(new Image(getClass().getResource(fighters[2].getModel()).toExternalForm()));
        char3UI.setImage(new Image(getClass().getResource(fighters[2].getUI()).toExternalForm()));

        char4.setImage(new Image(getClass().getResource(fighters[3].getModel()).toExternalForm()));
        char4UI.setImage(new Image(getClass().getResource(fighters[3].getUI()).toExternalForm()));
    }

    public void updateUIStats(){
        char1HP.setText(fighters[0].hp + "/" + fighters[0].maxhp);
        char1SP.setText(fighters[0].sp + "/" + fighters[0].maxsp);
        char1Ult.setText(fighters[0].ultPoints + "/" + fighters[0].ultLim);
        char1Name.setText(fighters[0].name);

        char2HP.setText(fighters[1].hp + "/" + fighters[1].maxhp);
        char2SP.setText(fighters[1].sp + "/" + fighters[1].maxsp);
        char2Ult.setText(fighters[1].ultPoints + "/" + fighters[1].ultLim);
        char2Name.setText(fighters[1].name);

        char3HP.setText(fighters[2].hp + "/" + fighters[2].maxhp);
        char3SP.setText(fighters[2].sp + "/" + fighters[2].maxsp);
        char3Ult.setText(fighters[2].ultPoints + "/" + fighters[2].ultLim);
        char3Name.setText(fighters[2].name);

        char4HP.setText(fighters[3].hp + "/" + fighters[3].maxhp);
        char4SP.setText(fighters[3].sp + "/" + fighters[3].maxsp);
        char4Ult.setText(fighters[3].ultPoints + "/" + fighters[3].ultLim);
        char4Name.setText(fighters[3].name);
    }

    public void nextTurn(){
        currTurn = (currTurn+1)%4;
    }

    public void setTeammates(){
        fighters[0].teammate = fighters[1];
        fighters[1].teammate = fighters[0];
        fighters[2].teammate = fighters[3];
        fighters[3].teammate = fighters[2];
    }

    public void clearMouseEvents(){
        for(int i=mouseEventsList.size()-1;i>=0;i--){
            paneEventList.get(i).removeEventHandler(MouseEvent.MOUSE_CLICKED,mouseEventsList.get(i));
            paneEventList.get(i).removeEventHandler(MouseEvent.MOUSE_ENTERED,mouseHoverEventsList.get(i));
            mouseEventsList.remove(i);
            paneEventList.remove(i);

        }
    }

    public boolean getPercentageResult(int chanceOfHit){
        int randomVal = numGenerator.nextInt(100);
        return (randomVal<chanceOfHit)?true:false;
    }

    public void setTurnName(){
        turnNameText.setText(fighters[turnOrder[currTurn]].name);
    }

    /** END UPDATE UI VALUES */
}
