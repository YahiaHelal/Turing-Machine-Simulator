import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {


        System.out.println(
                "What is Turing machiene ?  \n\n----> an abstract computational model that performs computations by reading and writing to an infinite tape.\n");
        System.out.println("how does it work ? \n");
        System.out.println("simply you should enter the following data so TM can serve your request\n");
        System.out.println("1- Number of states\n");
        System.out.println("2- Number of string alphabet\n");
        System.out.println("3- String alphabet itself\n");
        System.out.println("4- Number of machiene alphabet\n");
        System.out.println("5- Machiene alphabet itself\n");
        System.out.println("6- Transition Function\n");
        System.out.println("7- The string you want to test on\n");
        System.out.println("8- Initial position of the head\n");
        System.out.println("The desired output should be like :- \n");
        System.out.println("Final string is ----> \"Output\" \n");
        System.out.println("Current head position is ----> Position\n");
        System.out.println("There are few symbols you have to keep eye on :-\n");
        System.out.println("Y  ----> Stands for Yes (Accept)\n");
        System.out.println("N  ----> Stands for No  (Reject)\n");
        System.out.println("R  ----> Move one Step to the Right\n");
        System.out.println("L  ----> Move one Step to the Left\n");
        System.out.println("#  ----> Blank\n");
        System.out.println("|<|----> Left Mark\n");
        System.out.println("\t\t\t\t\t         |---------------|");
        System.out.println("\t\t\t\t\t         |Turing Machiene|");
        System.out.println("\t\t\t\t\t         |---------------|");
        System.out.println("\t\t\t\t\t                 |");
        System.out.println("\t\t\t\t\t                 |");
        System.out.println("\t\t\t\t\t                 |");
        System.out.println("\t\t\t------------------------------------------------");
        System.out.println("           |                         |                      |");
        System.out.println("           |                         |                      |");
        System.out.println("           |                         |                      |");
        System.out.println("       |---------|             |------------|           |--------|");
        System.out.println("       |Start [1]|             |Simulate [2]|           |Exit [0]|");
        System.out.println("       |---------|             |------------|           |--------|");

        Scanner input = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = reader.readLine();
        if (!(isDigit(choice))) {
            System.out.println("The choice you entered is not valid");
        } else {
            int Entered = Integer.parseInt(choice);
            if (Entered > 2 || Entered < 0) {
                System.out.println("Input out of range");
            } else {
                if (Entered == 1) {
                    System.out.println("Note : ---> the transition should satisfy the form\n---> StateNumber(only one number)-CurrentCharacter-NextStateNumber-WrittenCharacter-Action\n");
                    System.out.println("Enter number of states :");
                    String NumberOfStates = reader.readLine();
                    int NOS = Validate(NumberOfStates, "states");
                    System.out.println("Enter number of String alphabet : ");
                    String NumberOfAlphabet = reader.readLine();
                    int NOA = Validate(NumberOfAlphabet, " ");
                    String givenWord;
                    char alphabet[] = new char[NOA];
                    for (int i = 0; i < NOA; i++) {
                        int counter = i + 1;
                        System.out.println("Enter the alphabet number " + counter);
                        givenWord = reader.readLine();
                        while (true) {
                            if (!(givenWord.length() == 1)) {
                                System.out.println(givenWord + " does not seem to be a character\n");
                                System.out.println("Enter the alphabet number " + counter);
                                givenWord = reader.readLine();
                            } else
                                break;
                        }
                        alphabet[i] = givenWord.charAt(0);
                    }
                    System.out.println("Enter the number of machiene alphabet : ");
                    String NumberOfMachieneAlphabet = reader.readLine();
                    int NOMA = Validate(NumberOfMachieneAlphabet, " Machiene ");
                    char Malphabet[] = new char[NOMA];
                    for (int i = 0; i < NOMA; i++) {
                        int counter = i + 1;
                        System.out.println("Enter the Machiene alphabet number " + counter);
                        givenWord = reader.readLine();
                        while (true) {
                            if (!(givenWord.length() == 1)) {
                                System.out.println(givenWord + " does not seem to be a character\n");
                                System.out.println("Enter the Machiene alphabet number " + counter);
                                givenWord = reader.readLine();
                            } else
                                break;
                        }
                        Malphabet[i] = givenWord.charAt(0);
                    }
                    String Transition[][] = new String[NOS][NOMA];
                    String ReadableTransition[] = new String[NOS * NOMA];
                    String Available = "LRYN";
                    int counter = 1;
                    String TransitionLine;
                    for (int i = 0; i < Transition.length; i++) {
                        for (int j = 0; j < Transition[0].length; j++) {
                            System.out.println("Enter the " + counter + " Transition: ");
                            TransitionLine = reader.readLine();
                            while(true) {
                                if(!(Available.contains(Character.toString(TransitionLine.charAt(8))) || !(isDigit(Character.toString(TransitionLine.charAt(0)))))) {
                                    System.out.println("Invalid Transition symbols please Enter valid transition: \n");
                                    TransitionLine = reader.readLine();
                                }else
                                    break;
                            }
                            Transition[i][j] = TransitionLine;
                            counter++;
                        }
                    }
                    String form = "";
                    int index = 0;
                    for (int i = 0; i < Transition.length; i++) {
                        for (int j = 0; j < Transition[0].length; j++) {
                            form = String.format("(%s,%s)|--->(%s,%s,%s)", Transition[i][j].charAt(0),
                                    Transition[i][j].charAt(2), Transition[i][j].charAt(4), Transition[i][j].charAt(6),
                                    Transition[i][j].charAt(8));
                            ReadableTransition[index] = form;
                            index++;
                        }
                    }

                    System.out.println("The transition function you entered is : \n");
                    for (String TransLine : ReadableTransition) {
                        System.out.println(TransLine + "\n");
                    }
                    System.out.println("Enter String : ");
                    String UserString = reader.readLine();
                    System.out.println("Enter Head position : ");
                    String Head = reader.readLine();
                    while (true) {
                        if (!(isDigit(Head))) {
                            System.out.println("the given input does not seem to be valid number please try again: ");
                            Head = reader.readLine();
                        } else if (Integer.parseInt(Head) < 0) {
                            System.out.println("Head position cannot be negative value please try again: ");
                            Head = reader.readLine();
                        } else
                            break;

                    }
                    int HeadPosition = Integer.parseInt(Head);
                    if (HeadPosition > UserString.length()) {
                        while (UserString.length() < HeadPosition) {
                            UserString += "#";
                        }
                    }

                    char CurrentChar = UserString.charAt(HeadPosition);
                    char CurrentState = Transition[0][0].charAt(0);
                    boolean End = false;
                    String Action = "";
                    while (true) {
                        if(Action == "Y" || Action == "N")
                            break;
                        for (int i = 0; i < Transition.length; i++) {
                            for (int j = 0; j < Transition[0].length; j++) {
                                if (Transition[i][j].charAt(0) == CurrentState
                                        && Transition[i][j].charAt(2) == CurrentChar) {
                                    CurrentState = Transition[i][j].charAt(4);
                                    UserString = UserString.replace(CurrentChar, Transition[i][j].charAt(6));
                                    if (Transition[i][j].charAt(8) == 'R') {
                                        HeadPosition++;
                                        Action = "R";
                                        if (HeadPosition >= UserString.length()) {
                                            UserString += "#";
                                        }
                                        CurrentChar = UserString.charAt(HeadPosition);
                                    } else if (Transition[i][j].charAt(8) == 'L') {
                                        HeadPosition--;
                                        Action = "L";
                                        if (HeadPosition < 0) {
                                            UserString = "<"+UserString;
                                            HeadPosition = 0;
                                        }
                                        CurrentChar = UserString.charAt(HeadPosition);

                                    } else if (Transition[i][j].charAt(8) == 'Y') {

                                        System.out.println("------> Accepted <------");
                                        UserString = UserString.replace("<","");
                                        System.out.println("Final String is :\n---> " + UserString + "\n");
                                        System.out.println("Head position is:\n---> " + HeadPosition);
                                        Action = "Y";
                                        char TapeShape[] = UserString.toCharArray();
                                        System.out.println("The Tape: ");
                                        int length = TapeShape.length;
                                        for (int a = 0; a < length+3; a++) {
                                            System.out.print("- ");
                                        }
                                        System.out.println();
                                        System.out.print("|<|");
                                        for (char ch : TapeShape) {
                                            System.out.print(ch + "|");
                                        }
                                        System.out.print("|");
                                        System.out.println();
                                        for (int a = 0; a < length+3; a++) {
                                            System.out.print("- ");
                                        }

                                        End = true;
                                        break;
                                    } else if (Transition[i][j].charAt(8) == 'N') {
                                        System.out.println("------> Rejected <------");
                                        UserString = UserString.replace("<","");
                                        System.out.println("Final String is :\n---> " + UserString + "\n");
                                        System.out.println("Head position is:\n---> " + HeadPosition);
                                        Action = "N";
                                        char TapeShape[] = UserString.toCharArray();
                                        System.out.println("The Tape: ");
                                        int length = TapeShape.length;
                                        for (int a = 0; a < length+3; a++) {
                                            System.out.print("- ");
                                        }
                                        System.out.println();
                                        System.out.print("|<|");
                                        for (char ch : TapeShape) {
                                            System.out.print(ch + "|");
                                        }
                                        System.out.println();
                                        for (int a = 0; a < length+3; a++) {
                                            System.out.print("- ");
                                        }
                                        End = true;
                                        break;
                                    }
                                }
                            }
                            if (End)
                                break;
                        }
                    }

                } else if (Entered == 2) {
                    System.out.println("Still working on it :\")");
                    // still working on it
                } else {
                    System.out.println("Thanks for your testing :\"D");
                }
            }
        }
    }

    public static boolean isDigit(String s) {
        return s.matches("[0-9]+") || s.matches("[0-9 ]+");
    }

    public static int Validate(String s, String holder) throws IOException {
        int Number;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            if (!(isDigit(s))) {
                if (holder == "states") {
                    System.out.println("Invalid number of states please try again\n");
                    System.out.println("Enter number of " + holder + " :\n");
                    s = reader.readLine();
                } else if(holder == " Machiene " || holder == " ") {
                    System.out.println("Invalid input please try again: \n");
                    System.out.println("Enter number of" + holder + "alphabet :\n");
                    s = reader.readLine();
                }else {
                    System.out.println(s + " does not seem to be a valid number \n");
                }
            } else {
                Number = Integer.parseInt(s);
                break;
            }
        }
        return Number;
    }

    public static String FormString(ArrayList<Character> tape) {
        String string = "";
        for (char word : tape) {
            string += word;
        }
        string = string.replace("<", "");
        return string;
    }


}
