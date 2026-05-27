package A1.CMPT213A1.src;

import java.util.ArrayList;

public class App {
    static ArrayList<LoanInformation> loanInformation = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        boolean done = false;
        TextMenu textMenu = new TextMenu("Armans super cool menu");
        textMenu.addMenuOption("List all items");
        textMenu.addMenuOption("Add an item");
        textMenu.addMenuOption("Remove an item");
        textMenu.addMenuOption("List overdue items ");
        textMenu.addMenuOption("List upcoming items");
        textMenu.addMenuOption("Exit");
        do {
            textMenu.displayMenuTitle();
            int userInput = textMenu.readUserInput();
            if(userInput == 6){
                System.out.println("Saving the list to ./list.json...\n \nThanks for using Loan Items Tracker!");
                done = true;
            }
        } while (!done); 
    }
}
