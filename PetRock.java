import java.util.Random;

public class PetRock {
    private String name;
    private Mood mood;
    private int hunger;
    private int boredom;
    private int energy;
    private boolean beenPolished;
    private int polishNumber;

    //possible moods
    private enum Mood {
        HAPPY("Happy"),
        SAD("Sad"),
        BORED("Bored"),
        TIRED("Tired");

        private final String moodText;

        Mood(String moodText) {
            this.moodText = moodText;
        }

        public String getMoodText() {
            return moodText;
        }
    }

    public PetRock(String Name){
        name = Name;
        beenPolished = false;
        polishNumber = 0;
        hunger = 2;
        boredom = 2;
        energy = 5;
        mood = Mood.HAPPY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMood() {
        return mood.getMoodText();
    }

    //will return a illegalStateException if mood is set incorrectly
    public void setMood(String mood) {
        this.mood = Mood.valueOf(mood.toUpperCase());
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getBoredom() {
        return boredom;
    }

    public void setBoredom(int boredom) {
        this.boredom = boredom;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public String toString() {
        return "PetRock{" +
                "name='" + name + '\'' +
                ", mood='" + mood + '\'' +
                ", hunger=" + hunger +
                ", boredom=" + boredom +
                ", energy=" + energy +
                '}';
    }

    //Reduces hunger by 2
    //Increases boredom
    //Reduces Energy
    public void FeedTheRock() {
        Random random = new Random();
        System.out.println("Feeding the rock...");
        System.out.println("Hunger before: " + hunger);
        hunger -= random.nextInt(3) + 2; //random subtraction of 2-4
        checkStats();
        System.out.println("Hunger after: " + hunger);
        System.out.println("It seems sated.. for now");
        boredom++;
        energy--;
        beenPolished = false;
        polishNumber = 0;
        updateMood();
        checkStats();
    }


    //Decreases Boredom by 3
    //Increases hunger
    //Decreases energy by 2
    public void PlayWithRock(){
        Random random = new Random();
        System.out.println("Playing with the rock...");
        System.out.println("Boredom before: " + boredom);
        boredom -= random.nextInt(3) + 2;
        hunger++;
        energy -= 1;
        beenPolished = false;
        polishNumber = 0;
        checkStats();
        System.out.println("Boredom after: " + boredom);
        System.out.println("It had a good time.");
        updateMood();
        checkStats();
    }

    //Sets mood to happy
    //Decreases hunger and boredom
    //Increases energy
    //Diminishing Returns
    public void PolishTheRock(){
        System.out.println("You Polish the Rock...");
        System.out.println("Polish before: " + polishNumber);
        mood = Mood.HAPPY;
        if(!beenPolished){
            hunger--;
            boredom--;
            energy++;
            beenPolished = true;
            polishNumber++;
            checkStats();
            System.out.println("Polish after: " + polishNumber);
            System.out.println("Its a bit shiny!");
        }
        else{
            if(polishNumber < 2){
                hunger--;
                energy++;
                polishNumber++;
                checkStats();
                System.out.println("Polish after: " + polishNumber);
                System.out.println("Its a quite shiny!");
            }
            else if(polishNumber < 3){
                energy++;
                polishNumber++;
                checkStats();
                System.out.println("Polish after: " + polishNumber);
                System.out.println("Its shiny!");
            }
            else{
                polishNumber++;
                checkStats();
                System.out.println("Polish after: " + polishNumber);
                System.out.println("Its very shiny!");
            }
        }
        checkStats();
    }

    public void updateMood(){
        if (energy <= 2){
            mood = Mood.TIRED;
        }
        else if(((hunger >= 4 && hunger <= 7)||( boredom >= 4 && boredom <= 7)) && energy > 3){
            mood = Mood.BORED;
        }
        else if ((hunger > 7 || boredom > 7 || energy <= 3)){
            mood = Mood.SAD;
        }
        else if(hunger < 4 && boredom < 4 && energy > 3){
            mood = Mood.HAPPY;
        }
    }

    //checks that stats stay between (0-10)
    public void checkStats(){
        if(hunger < 0 || hunger > 10){
            if (hunger < 0){
                hunger = 0;
            }
            if (hunger > 10){
                hunger = 10;
            }
        }
        if(boredom < 0 || boredom > 10){
            if (boredom < 0){
                boredom = 0;
            }
            if (boredom > 10){
                boredom = 10;
            }
        }
        if(energy < 0 || energy > 10){
            if (energy < 0){
                energy = 0;
            }
            if (energy > 10){
                energy = 10;
            }
        }
    }
}
