package A1.CMPT213A1.src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextMenu {
    public String menuTitle;
    public List<String> menuOptions;
    Scanner scanner;

    
    public TextMenu(String menuTitle){
        this.menuTitle = menuTitle;
        this.menuOptions = new ArrayList<>();
        this.scanner = new Scanner(System.in);

    }

    public void addMenuOption(String option){
        menuOptions.add(option);
    }

    public void displayMenuTitle(){
        for(int i = 0; i < menuTitle.length() + 2; i++){
            System.out.print("#");
        }
        System.out.print("\n");
        System.out.print("#" + menuTitle + "#");
        System.out.print("\n");
        for(int i = 0; i < menuTitle.length() + 2; i++){
            System.out.print("#");
        }
        System.out.print("\n");
        System.out.println("Date: " + LocalDate.now());

        for(int i = 0; i < menuOptions.size(); i++){
            System.out.println((i+1) + ": " + menuOptions.get(i));
        }
        System.out.println("Choose an option by entering 1-6:");
    }   

    public int readUserInput(){
        String userInput = scanner.nextLine();

        int optionsLength = menuOptions.size();
        try {
            int value = Integer.parseInt(userInput.trim()); // for leading or trailing spaces
            if(value >= 1 && value <= optionsLength){
                handleUserInput(value);
                return value;
            }
            else{
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void handleUserInput(int value){
        switch (value){
            case 1:
                listAllItems();
                break;
            case 2:
                addItem();
                break;
            case 3:
                removeItem();
                break;
            case 4:
                listOverdueItems();
                break;
            case 5:
                listUpcomingItems();
                break;
            case 6:
                break;
        }
    }

    public void listAllItems(){
        if(App.loanInformation.size() == 0){
            System.out.println("No items to show.");
        }
        
        for(int i = 0; i < App.loanInformation.size(); i++){
            System.out.println("#" + (i+1) + App.loanInformation.get(i).toString());
        }
    }

    public void addItem(){
        System.out.print("Enter the name of the loan item: ");
        String loanItemName = scanner.nextLine();
        System.out.print("\n");

        System.out.print("Enter the year of the due date (e.g., 2026): ");
        String dueDateYear = scanner.nextLine();
        System.out.print("\n");

        System.out.print("Enter the month of the due date (1-12): ");
        String dueDateMonth = scanner.nextLine();
        System.out.print("\n");

        System.out.print("Enter the day of the due date (1-28/29/30/31): ");
        String dueDateDay = scanner.nextLine();
        System.out.print("\n");

        System.out.print("Enter the publisher of the loan item: "); // publisher can be empty
        String publisher = scanner.nextLine();
        System.out.print("\n");

        System.out.print("Enter the name to which the item is loaned: ");
        String nameItemLoaned = scanner.nextLine();
        System.out.print("\n");

        LoanInformation newItem = new LoanInformation(loanItemName, LocalDate.now(), publisher, nameItemLoaned);
        
        App.loanInformation.add(newItem);

        System.out.println(loanItemName + " has been added to the list.");
    }

    public void removeItem(){
        listAllItems();
        if(App.loanInformation.size() == 0){
            return;
        }
        int value = -1;

        do {
            System.out.println("Enter the item number you want to remove (0 to cancel):");
            String userInput = scanner.nextLine();
            try{
                value = Integer.parseInt(userInput.trim());
                if(value == 0){
                    return;
                }
                if(value < 0 || value > App.loanInformation.size()){
                    System.out.println("Invalid selection. Enter a number between: 0 and " + App.loanInformation.size());
                    value = -1;
                }
            } catch(NumberFormatException e){
                System.out.println("Invalid selection. Enter a number between: 0 and " + App.loanInformation.size());
                value = -1;
            }
        } while (value == -1);

        LoanInformation itemRemoved = App.loanInformation.remove(value - 1);

        System.out.println(itemRemoved.name + " has been removed from the list.");
    }

    public void listOverdueItems(){

    }

    public void listUpcomingItems(){

    }
}
