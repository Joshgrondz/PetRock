    import java.util.*;

    public class Menu {

    //   _______
    //  /       \
    // /         \
    // |   O   O  |
    // \    ___   /
    //  \_______/

        public static void main(String[] args) {
            int currentInput = 0;
            int lastTurnsChoice = 0;
            Scanner sc = new Scanner(System.in);
            Random random = new Random();


            System.out.println("Welcome to Pet Rock");
            System.out.println(
                    "    _________\n" +
                    "   /         \\\n" +
                    "  /           \\\n" +
                    " |   O   O    |\n" +
                    "  \\    ___   /\n" +
                    "   \\________/\n");
            System.out.print("What is your rocks name? : ");
            PetRock myRock = new PetRock(sc.nextLine());
            System.out.println("Your rock's name is officially " + myRock.getName());

            while(currentInput != 5){
                System.out.println(
                        "    _________\n" +
                                "   /         \\\n" +
                                "  /           \\\n" +
                                " |   O   O    |\n" +
                                "  \\    ___   /\n" +
                                "   \\________/\n");
                System.out.println("Choose an option : ");
                System.out.println("1. Feed the rock");
                System.out.println("2. Play with the rock");
                System.out.println("3. Polish the rock");
                System.out.println("4. Check the rocks stats");
                System.out.println("5. Quit (abandon your rock)");
                currentInput = sc.nextInt();

                //check if out of energy
                if(myRock.getEnergy() == 0){
                    System.out.println("The rock is tired (energy 0), resting");
                    currentInput = 6; //rest
                }

                //check if violating cooldowns
                if(currentInput == lastTurnsChoice){
                    System.out.println("Cant do the same thing twice in a row! Resting instead");
                    currentInput = 6;
                }

                switch(currentInput){
                    case 1:
                        myRock.FeedTheRock();
                        break;
                    case 2:
                        myRock.PlayWithRock();
                        break;
                    case 3:
                        myRock.PolishTheRock();
                        break;
                    case 4:
                        myRock.toString();
                        break;
                    case 5:
                        System.out.println(
                                        "    _________\n" +
                                        "   /         \\\n" +
                                        "  /           \\\n" +
                                        " |   X    X   |\n" +
                                        "  \\    ___   /\n" +
                                        "   \\________/\n" +
                                myRock.getName() + " has been brutally killed. You may now leave.");
                        break;
                    default:
                        System.out.println("You Do Nothing! +1 Energy");
                        myRock.setEnergy(myRock.getEnergy() + 1);
                        break;
                }

                lastTurnsChoice = currentInput;
                System.out.print("A turn passes, your rock gets bored and hungry (+1 to each)");
                myRock.setHunger(myRock.getHunger() + 1);
                myRock.setBoredom(myRock.getBoredom() + 1);

                if (random.nextInt(4) == 0) { // 25% chance (0 out of 0-3)
                    randomEvents(myRock);
                } else {
                    System.out.println("Nothing unusual happened. The day ends.");
                }

            }
        }

        private static void randomEvents(PetRock myRock) {
            Random random = new Random();
            int event = random.nextInt(6); // Generates a number between 0 and 3

            switch (event) {
                case 0:
                    System.out.println("Your rock found a shiny pebble! It’s happier now!");
                    myRock.setMood("Happy");
                    break;
                case 1:
                    System.out.println("Your rock got some extra sleep! Energy restored!");
                    myRock.setEnergy(myRock.getEnergy() + 5); // Adjust value as needed
                    break;
                case 2:
                    System.out.println("Your rock is scared by a sudden noise!");
                    myRock.setMood("Sad");
                    break;
                case 3:
                    System.out.println("Your rock compared itself to unrealistic body images online and started a diet. Hunger Decreased.");
                    myRock.setHunger(myRock.getHunger() - 2);
                    break;
                case 4:
                    System.out.println("Your got hit by a car. Energy decreased!");
                    myRock.setEnergy(myRock.getEnergy() - 2);
                default:
                    break;
            }

        }
    }

