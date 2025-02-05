import java.io.*;

public class FileManagement {

    public static void saveState(PetRock myRock) {
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

    public static PetRock loadState() {
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

    public static void deleteState() {
        File file = new File("rock.json");
        if (file.exists() && file.delete()) {
            System.out.println("Rock state deleted.");
        }
    }

    public static String extractValue(String json, String key) {
        String pattern = "\"" + key + "\":\\s*\"?([^\",}]*)\"?";
        return json.replaceAll(".*" + pattern + ".*", "$1");
    }


}
