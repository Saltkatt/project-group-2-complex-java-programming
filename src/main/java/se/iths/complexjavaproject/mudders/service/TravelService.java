package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.model.MonsterModel;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;
import se.iths.complexjavaproject.mudders.util.ServiceUtilities;

/**
 * Skapad av Elin och Tonny.
 */
@Service
public class TravelService {

    @Autowired
    CombatService combatService;

    private int diceRoll;
    MonsterModel monsterModel;

    public void daysToTown(){
        int daysToTown = 0;
        //List of towns, days to town corresponds to index.
    }

    public PlayerCharacter travel(PlayerCharacter playerCharacter) {
        diceRoll = ServiceUtilities.generateRandomIntIntRange(1, 20);
        //Travelling to next town.
        if (diceRoll >= 18) {
            //might be ambushed
            encounter(playerCharacter);
            return playerCharacter;
        }else{
                //might find pot of gold
                return potOfGold(playerCharacter);
            }
        }


    /*
    public MonsterModel createNewMonster() {
//        return monsterModel.toDTO(addMonster);
        return null;
    }
    */
    private PlayerCharacter encounter(PlayerCharacter playerCharacter){
        //Loop
        monsterModel = MonsterService.createNewRandomMonster(playerCharacter.getLevel());
        //Send message:
        combatService.fight(playerCharacter, monsterModel);

        System.out.println("You are being ambushed by a " + monsterModel.getName()
                + "\n Escape or Attack?");
        //Send to CombatController
//        return combatService.fight(playerCharacter.toModel(), monsterModel);
        return playerCharacter;
    }

    private PlayerCharacter potOfGold(PlayerCharacter playerCharacter){
        int coinsGained = ServiceUtilities.generateRandomIntIntRange(1, 5);
        String msg = "You have found " + coinsGained + " gold coins!";
        //TODO: Check if coins gained returns the actual value
        int newCurrencyValue = coinsGained + playerCharacter.getCurrency();
        playerCharacter.setCurrency(newCurrencyValue);

        //daysToTown =- 1;
        return playerCharacter;
    }
}
