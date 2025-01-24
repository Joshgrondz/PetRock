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
    }

    public void updateMood(){
        if(energy <= 3){
            mood = Mood.TIRED;
        } else if (hunger <= 3) {
            mood = Mood.SAD;
        } else if (boredom <= 3) {
            mood = Mood.BORED;
        }
    }
}
