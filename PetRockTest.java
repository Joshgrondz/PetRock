import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetRockTest {

    @Test
    void testGetName() {
        PetRock myRock = new PetRock("Rocky");
        assertEquals("Rocky", myRock.getName());
    }

    @Test
    void testSetMood() {
        PetRock myRock = new PetRock("Rocky");
        myRock.setMood("Happy");
        assertEquals("Happy", myRock.getMood());
    }

    @Test
    void testFeedTheRock() {
        PetRock myRock = new PetRock("Rocky");
        int initialHunger = myRock.getHunger();
        int initialBoredom = myRock.getBoredom();
        int initialEnergy = myRock.getEnergy();
        myRock.FeedTheRock();
        assertTrue(initialHunger > myRock.getHunger());
        assertTrue(initialBoredom < myRock.getBoredom());
        assertTrue(initialEnergy > myRock.getEnergy());
    }

    @Test
    void testPlayWithRock() {
        PetRock myRock = new PetRock("Rocky");
        int initialHunger = myRock.getHunger();
        int initialBoredom = myRock.getBoredom();
        int initialEnergy = myRock.getEnergy();
        myRock.PlayWithRock();
        assertTrue(initialHunger < myRock.getHunger());
        assertTrue(initialBoredom > myRock.getBoredom());
        assertTrue(initialEnergy > myRock.getEnergy());
    }

    @Test
    void testPolishTheRock() {
        PetRock myRock = new PetRock("Rocky");
        int initialHunger = myRock.getHunger();
        int initialBoredom = myRock.getBoredom();
        int initialEnergy = myRock.getEnergy();
        myRock.PolishTheRock();
        assertEquals("Happy", myRock.getMood());
        assertTrue(initialHunger > myRock.getHunger());
        assertTrue(initialBoredom > myRock.getBoredom());
        assertTrue(initialEnergy < myRock.getEnergy());
    }

    @Test
    void testUpdateMood() {
        PetRock myRock = new PetRock("Rocky");
        myRock.setEnergy(1); // Make the rock tired
        myRock.updateMood();
        assertEquals("Tired", myRock.getMood());
    }

    @Test
    void testCheckStats_KeepsWithinBounds() {
        PetRock myRock = new PetRock("Rocky");
        myRock.setHunger(-5);
        myRock.setBoredom(15);
        myRock.setEnergy(12);
        myRock.checkStats();
        assertEquals(0, myRock.getHunger());
        assertEquals(10, myRock.getBoredom());
        assertEquals(10, myRock.getEnergy());
    }

    @Test
    void testCheckStats_NoChangeWhenWithinBounds() {
        PetRock myRock = new PetRock("Rocky");
        myRock.setHunger(5);
        myRock.setBoredom(3);
        myRock.setEnergy(8);
        myRock.checkStats();
        assertEquals(5, myRock.getHunger());
        assertEquals(3, myRock.getBoredom());
        assertEquals(8, myRock.getEnergy());
    }
}
