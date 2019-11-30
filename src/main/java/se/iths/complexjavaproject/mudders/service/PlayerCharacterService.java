package se.iths.complexjavaproject.mudders.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.model.PlayerCharacter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class PlayerCharacterService {

    public static PlayerCharacter convertToModel (String playerJson) throws BadDataException {
        PlayerCharacter playerCharacter = new PlayerCharacter();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            playerCharacter = objectMapper.readValue(playerJson, PlayerCharacter.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (playerCharacter.getCharacterName().isBlank()) {
            throw new BadDataException("No name entered!");
        }

        return playerCharacter;
    }

}
