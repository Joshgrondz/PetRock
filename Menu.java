import java.util.*;
import java.io.*;

public class Menu {

    public static void main(String[] args) {
        int currentInput = 0;
        int lastTurnsChoice = 69420;
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        PetRock myRock = loadState();
        if (myRock == null) {
            System.out.println("Welcome to Pet Rock");
            System.out.println(
                    """
                                _________
                               /         \\
                              /           \\
                             |   O   O    |
                              \\    ___   /
                               \\________/
                            """);
            System.out.print("What is your rock's name? : ");
            myRock = new PetRock(sc.nextLine());
            System.out.println("Your rock's name is officially " + myRock.getName());
        }

        while (true) {
            displayRock(myRock);
            System.out.println("Choose an option : ");
            System.out.println("1. Feed the rock");
            System.out.println("2. Play with the rock");
            System.out.println("3. Polish the rock");
            System.out.println("4. Check the rock's stats");
            System.out.println("5. Quit (The rock will remain saved)");
            System.out.println("6. Rest");
            System.out.println("7. Delete");
            currentInput = sc.nextInt();

            // Check if out of energy
            if (myRock.getEnergy() == 0 && currentInput != 5 && currentInput != 69 && currentInput != 4 && currentInput != 6) {
                System.out.println("The rock is tired (energy 0), resting");
                currentInput = 6; // Rest
            }

            // Check if violating cooldowns
            if (currentInput == lastTurnsChoice && currentInput != 5 && currentInput != 69 && currentInput != 4 && currentInput != 6) {
                System.out.println("Can't do the same thing twice in a row! Resting instead");
                currentInput = 6;
            }

            switch (currentInput) {
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
                    System.out.println(myRock.toString());
                    break;
                case 5:
                    System.out.println("Thank you for using Pet Rock");
                    saveState(myRock);
                    System.exit(69);
                    break;
                case 7:
                    System.out.println(
                            "    _________\n" +
                                    "   /         \\\n" +
                                    "  /           \\\n" +
                                    " |   X    X   |\n" +
                                    "  \\    ___   /\n" +
                                    "   \\________/\n" +
                                    myRock.getName() + " has been brutally killed. You may now leave.");
                    deleteState(); // Delete saved state
                    System.exit(69);
                    break;
                default:
                    System.out.println("You Do Nothing! +3 Energy");
                    myRock.setEnergy(myRock.getEnergy() + 3);
                    break;
            }

            lastTurnsChoice = currentInput;
            System.out.println("A turn passes, your rock gets bored and hungry (+1 to each)");
            myRock.setHunger(myRock.getHunger() + 1);
            myRock.setBoredom(myRock.getBoredom() + 1);

            if (random.nextInt(4) >=1) { // 25% chance (0 out of 0-3)
                randomEvents(myRock);
            } else {
                System.out.println("Nothing unusual happened. The day ends.");
            }

            // Save state after every action
            myRock.updateMood();
            myRock.checkStats();
            saveState(myRock);
        }
    }

    private static void randomEvents(PetRock myRock) {
        Random random = new Random();
        int event = random.nextInt(6); // Generates a number between 0 and 5

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
                System.out.println("Your rock grows more powerful..");
                myRock.setHunger(myRock.getHunger() - 4);
                myRock.setEnergy(myRock.getEnergy() +3);
                myRock.setBoredom(myRock.getBoredom() - 2);
                break;
            case 4:
                System.out.println("Your rock got hit by a car. Energy decreased!");
                myRock.setEnergy(myRock.getEnergy() - 2);
                break;
            case 5:
                System.out.println("Your rock can't find anything interesting to watch on YouTube. Boredom increased.");
                myRock.setMood("Bored");
                myRock.setBoredom(myRock.getBoredom() + 2);
                break;
        }
        myRock.checkStats();
    }

    private static void saveState(PetRock myRock) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rock.json"))) {
            writer.write("{\n");
            writer.write("  \"name\": \"" + myRock.getName() + "\",\n");
            writer.write("  \"mood\": \"" + myRock.getMood() + "\",\n");
            writer.write("  \"hunger\": " + myRock.getHunger() + ",\n");
            writer.write("  \"boredom\": " + myRock.getBoredom() + ",\n");
            writer.write("  \"energy\": " + myRock.getEnergy() + "\n");
            writer.write("}");
        } catch (IOException e) {
            System.out.println("Error saving rock state: " + e.getMessage());
        }
    }

    private static PetRock loadState() {
        File file = new File("rock.json");
        if (!file.exists()) {
            return null; // No save file, start fresh
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line.trim());
            }

            // Parse JSON manually
            String jsonString = json.toString();
            String name = extractValue(jsonString, "name");
            String mood = extractValue(jsonString, "mood");
            int hunger = Integer.parseInt(extractValue(jsonString, "hunger"));
            int boredom = Integer.parseInt(extractValue(jsonString, "boredom"));
            int energy = Integer.parseInt(extractValue(jsonString, "energy"));

            PetRock myRock = new PetRock(name);
            myRock.setMood(mood);
            myRock.setHunger(hunger);
            myRock.setBoredom(boredom);
            myRock.setEnergy(energy);
            return myRock;
        } catch (IOException e) {
            System.out.println("Error loading rock state: " + e.getMessage());
        }
        return null;
    }

    private static void deleteState() {
        File file = new File("rock.json");
        if (file.exists() && file.delete()) {
            System.out.println("Rock state deleted.");
        }
    }

    private static String extractValue(String json, String key) {
        String pattern = "\"" + key + "\":\\s*\"?([^\",}]*)\"?";
        return json.replaceAll(".*" + pattern + ".*", "$1");
    }

    public static void displayRock(PetRock myRock) {
        switch (myRock.getMood()) {
            case "Happy":
                System.out.println(
                        """
                                _________
                               /         \\
                              /           \\
                             |   ^   ^    |
                              \\  \\___/  /
                               \\________/
                            """);
                break;
            case "Sad":
                System.out.println(
                        """
                                _________
                               /         \\
                              /           \\
                             |   T   T    |
                              \\    ---   /
                               \\________/
                            """);
                break;
            case "Bored":
                System.out.println(
                        """
                                _________
                               /         \\
                              /           \\
                             |   -   -    |
                              \\    ---   /
                               \\________/
                            """);
                break;
            case "Tired":
                System.out.println(
                        """
                                _________
                               /         \\
                              /           \\
                             |   _   _    |
                              \\    zzz   /
                               \\________/
                            """);
                break;
            default: // Fallback for unhandled moods
                System.out.println(
                        """
                                _________
                               /         \\
                              /           \\
                             |   O   O    |
                              \\    ___   /
                               \\________/
                            """);
                break;
        }
    }


}
