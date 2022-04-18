package cmsc256;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class DogNamesLab {
    private static String promptForFileName() {
        System.out.println("Enter the file name: ");
        @SuppressWarnings("resource")
        Scanner keyIn = new Scanner(System.in);
        return keyIn.next();
    }

    private static Scanner openFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        while (!file.exists()) {
            file = new File(promptForFileName());
        }
        return new Scanner(file);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Read data file to build data structure
        ArrayList<Dog> doglist = new ArrayList<>();

        try {
            // verify file and create file Scanner
            Scanner fileReader = openFile("Dog_Names.csv");

            //  Discard header line
            fileReader.nextLine();

            while(fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                int commaIndex = line.indexOf(',');
                String name = line.substring(0, commaIndex).trim();
                int count = Integer.parseInt(line.substring(commaIndex+1).trim());
                doglist.add(new Dog(name, count));
            }
            fileReader.close();
        }
        catch(FileNotFoundException noFile){
            System.out.println("There was an error opening or reading from the file.");
            System.exit(0);
        }

        Scanner readInput = new Scanner(System.in);
        String prompt = "\nWhat do you want to do?\n"
                + "\t1. Check a dog name\n" + "\t2. See all the dog names\n"
                + "\t3. Play a game\n" + "\t4. Exit"
                + ".\n"
                + "Enter the number corresponding to your choice.";

        System.out.println(prompt);
        int option = readInput.nextInt();

        switch(option) {
            case 1:
                System.out.println("Enter a dog’s name?");
                String name = in.nextLine();
                int nameCount = getCountForDog(doglist, name);
                System.out.println(name + " is registered " + nameCount + " times.");
                break;
            case 2:
                System.out.println(getDogNamesAlphabetically(doglist));
                break;
            case 3:
                playGuessingGame(doglist, in);
                break;
            default: System.out.println("Invalid option.");
        }
        in.close();
    }

    public static int getCountForDog(ArrayList<Dog> dogs, String name) {
        // TODO:
        // search the list for the Dog named name
        // display dogs name and the number of registrations for that name
        for(int i = 0; i < dogs.size(); i++){
            if(dogs.get(i).getDogName().equals(name)){
                return dogs.get(i).getCount();
            }
        }
        return 0;
    }

    public static String getDogNamesAlphabetically(ArrayList<Dog> dogs) {
        // TODO Sort the list of Dog by name return
        String result = "";
        for(Dog dog: dogs){
            result += dog.getDogName() + "\n";
        }
        return result;
    }

    public static void playGuessingGame(ArrayList<Dog> dogs, Scanner readIn) {
        // TODO: implement the guessing game.
        // while not done playing
        // pull two random Dogs from the list
        // display the names and prompt player to pick the more popular name
        // read player input
        // increment total number of guesses
        // check registration counts to determine if player is correct
        // if correct, respond and increment number of correct answers
        // if wrong, respond
        // ask user if they want to quit
        // if yes, display number of correct out of total number of guesses
        // if no, continue
        String userPlay = "";
        int timePlay = 0;
        int numCorrect = 0;
        int range = dogs.size();
        do {
            Dog dog1 = dogs.get((int) (Math.random() * range));
            Dog dog2 = dogs.get((int) (Math.random() * range));

            System.out.println("Which name is more popular for Anchorage dogs? (Type 1 or 2)");
            System.out.println("1. " + dog1.getDogName() + "\n2. " + dog2.getDogName());
            int userPick = Integer.parseInt(readIn.nextLine());
            timePlay++;
            int rightAnswer = 0;
            String answer = "";
            if (dog1.getCount() < dog2.getCount()) {
                rightAnswer = 2;
                answer = dog2.getDogName();
            } else if (dog2.getCount() < dog1.getCount()) {
                rightAnswer = 1;
                answer = dog1.getDogName();
            } else{
                rightAnswer = 3;
            }

            if (userPick == rightAnswer) {
                numCorrect++;
                System.out.println("You are correct, the popular dog name is " + answer);
            } else {
                if(rightAnswer == 3){
                    System.out.println("Both dog names have same popularity");
                    numCorrect++;
                }
                else {
                    System.out.println("Nope, the more popular dog name is " + answer);
                }
            }
            System.out.println("Do you want to play again? (Y/N)");
            userPlay = readIn.nextLine();
        }while(userPlay.equals("Y"));
        System.out.println("You guessed correctly " + numCorrect + " out of " + timePlay + " times.");
    }

}
