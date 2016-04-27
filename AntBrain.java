import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author oab24
 */
public class AntBrain {
    private String[] finiteStateMachine;

    public String[] getFiniteStateMachine() {
        return finiteStateMachine;
    }    
    
    public AntBrain(File antBrainFile) throws FileNotFoundException {
        //FileReader fileReader = new FileReader(antBrainFile);
        try {           
            FileReader fileReader;
            BufferedReader br = new BufferedReader( fileReader = new FileReader(antBrainFile));
            String line = br.readLine();
            String overall_line = "";
            while (line != null) { // null signals end of stream
                // process line
                if (line != null){
                    overall_line = overall_line + line + "\n";
                }
                line = br.readLine();                
            }
            br.close();             

            if (!checkBrain(overall_line)) {
                System.out.println("Ant brain not compatible. REJECTED");
            }
        } catch (IOException exception) {
            System.out.println("File incorrect. Can't load data");
        }
    }
    
    public AntBrain(String antBrainString) {
        if (!checkBrain(antBrainString)){
            System.out.println("Ant brain not compatible. REJECTED");
        }           
    }
    
    // MUST be set to private once testing concluded
    private boolean checkBrain (String checkString){
        String[] splitFile = checkString.split("\n");
        boolean whileChecker = true;
        int i = 0;
        while ( (i < splitFile.length) && whileChecker){
            whileChecker = checkLine(splitFile[i]);
            i++;
        }
        if (whileChecker){
            finiteStateMachine = splitFile;
        }
        return whileChecker;
    }
    
    // MUST be set to private once testing concluded
    private boolean checkLine(String inString){
        // check if the instruction is of the right format
        if (!inString.matches("([a-zA-Z]+(\\s.*)*\\n?)")){
            System.out.println ("incorrect format for instruction: "+ inString);
            return false;
        } else {
            String inStrings[] = inString.split("(\\s)");
            Boolean checkInstructionParameters = false;
            // case all the instructions.
            try {
                switch (inStrings[0].toLowerCase()) {
                        case "move": // state, state
                                boolean b = checkNumbers(inStrings[1],0,9999);
                                boolean c = checkNumbers(inStrings[2], 0, 9999);
                                checkInstructionParameters = b && c;
                                break;
                        case "sense": // direction, state, state, type, (marker number)
                                checkInstructionParameters = checkNumbers(inStrings[2], 0, 9999) && checkNumbers(inStrings[3], 0, 9999);
                                checkInstructionParameters = checkInstructionParameters && checkDirection(inStrings[1]) && checkConditions(inStrings[4]);
                                if (inStrings[4].toLowerCase().matches("marker")) { // check if there is a marker
                                     checkInstructionParameters = checkInstructionParameters && checkNumbers(inStrings[5], 0, 5) && (inStrings.length == 6);
                                }
                                break;
                        case "mark": //marker number, state
                                checkInstructionParameters = checkNumbers(inStrings[1], 0, 5) && checkNumbers(inStrings[2], 0, 9999);
                                break;
                        case "unmark": //marker number, state
                                checkInstructionParameters = checkNumbers(inStrings[1], 0, 5) && checkNumbers(inStrings[2], 0, 9999);
                                break;
                        case "pickup": // state, state
                                checkInstructionParameters = checkNumbers(inStrings[1], 0, 9999) && checkNumbers(inStrings[2], 0, 9999);
                                break;
                        case "drop": // state
                                checkInstructionParameters = checkNumbers(inStrings[1], 0, 9999);
                                break;
                        case "turn": // left/right, state
                                checkInstructionParameters = checkNumbers(inStrings[2], 0, 9999)&& inStrings[1].toLowerCase().matches("(left|right)");
                                break;
                        case "flip": // any integer, state, state
                                checkInstructionParameters = checkNumbers(inStrings[2], 0, 9999) && checkNumbers(inStrings[3], 0, 9999);
                                checkInstructionParameters = checkInstructionParameters && inStrings[1].matches("[0-9]+");
                                break;
                        default: 
                                System.out.println("Instruction not a valid instruction to use: "+ inString);
                                b = false;
                                break;
                }                            
            } catch (ArrayIndexOutOfBoundsException exception) {
                checkInstructionParameters = false;
                System.out.println("not enough inputs for line: " + inString);
            }
            return checkInstructionParameters;
        }
    }

    private boolean checkConditions(String conditionToCheck){
        boolean validWord = false;
        // case the valid directions
        switch (conditionToCheck.toLowerCase()) {
            case "friend":
                validWord = true;
                break;
            case "foe":
                validWord = true;
                break;
            case "friendwithfood":
                validWord = true;
                break;
            case "foewithfood":
                validWord = true;
                break;
            case "food":
                validWord = true;
                break;
            case "rock":
                validWord = true;
                break;
            case "marker":
                validWord = true;
                break;
            case "foemarker":
                validWord = true;
                break;
            case "home":
                validWord = true;
                break;
            case "foehome":
                validWord = true;
                break;
            default: 
                validWord = false;
                break;
        }
        return validWord;
    }
    
    // used to find if the direction entered is valid
    private boolean checkDirection(String wordToCheck){
        boolean validWord = false;
        // case the valid directions
        switch (wordToCheck.toLowerCase()) {
            case "here":
                validWord = true;
                break;
            case "ahead":
                validWord = true;
                break;
            case "leftahead":
                validWord = true;
                break;
            case "rightahead":
                validWord = true;
                break;
            default: 
                validWord = false;
                break;
        }
        return validWord;
    }
        
    // state passed in is now in pieces, need to check what the states after were
    private boolean checkNumbers(String numToCheck, int rangeLower, int rangeUpper){
        Boolean checkBool = true;       
        // check string number
        if (!numToCheck.matches("[0-9]+")) // 0-9999 states, therefore can repeat numbers up to 4 times
            checkBool = false;
        else {
            // string is a number, check its range
            int checkInt = new Integer(numToCheck);
            if ( !( (checkInt >= rangeLower) && (checkInt <= rangeUpper) ))// check number is in the right range
                checkBool = false;
        }
        return checkBool;
    }
        
    
}
