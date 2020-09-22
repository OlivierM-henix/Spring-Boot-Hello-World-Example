package com.reytech.demo;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.reytech.demo.Personne;

@SpringBootTest
class StandardAssertionsTest {

    @DisplayName("Tests standards")
    @Test
    void standardAssertions() {
    	
        assertEquals(2, 2);
        assertNotEquals(2, 3);
        
        // affiche le message si le test ne passe pas
        assertTrue(true, "Un premier message.");
        assertFalse(false, () ->  "Un " + "autre " + "message" + "." );  
    }
    
    
    @Test
    void groupedAssertions() {
        Personne pers = new Personne("Nassur", "Moumadi");

        assertAll("personne", () -> assertEquals("Nassur", pers.getFirstName()),
                              () -> assertEquals("Moumadi", pers.getLastName()));
    }


}
