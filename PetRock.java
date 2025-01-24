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
        this.mood = Mood.valueOf(mood.toUpperCase());;
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
    public void FeedTheRock(){
        hunger -= 2;
        boredom++;
        energy--;
        beenPolished = false;
        updateMood();
        checkStats();
    }

    //Decreases Boredom by 3
    //Increases hunger
    //Decreases energy by 2
    public void PlayWithRock(){
        boredom -= 3;
        hunger++;
        energy -= 2;
        beenPolished = false;
        updateMood();
        checkStats();
    }

    //Sets mood to happy
    //Decreases hunger and boredom
    //Increases energy
    //Diminishing Returns
    public void PolishTheRock(){
        mood = Mood.HAPPY;
        if(!beenPolished){
            hunger--;
            boredom--;
            energy++;
            beenPolished = true;
            polishNumber++;
        }
        else{
            if(polishNumber < 2){
                hunger--;
                energy++;
                polishNumber++;
            }
            else if(polishNumber < 3){
                energy++;
                polishNumber++;
            }
            else{
                polishNumber++;
            }
        }
        checkStats();
    }

    public void updateMood(){
        if(energy <= 3){
            mood = Mood.TIRED;
        } else if (hunger >= 7) {
            mood = Mood.SAD;
        } else if (boredom >= 7) {
            mood = Mood.BORED;
        }
    }

    //checks that stats stay between (0-10)
    private void checkStats(){
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
