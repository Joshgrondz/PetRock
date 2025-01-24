public class PetRock {
    private String name;
    private String mood;
    private int hunger;
    private int boredom;
    private int energy;

    public PetRock(String Name){
        name = Name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
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

    public void FeedTheRock(){
        hunger -= 2;
        boredom++;
        energy--;
    }

    public void PlayWithRock(){
        boredom -= 3;
        hunger++;
        energy -= 2;
    }

    public void PolishTheRock(){

    }

    public void updateMood(){

    }
}
