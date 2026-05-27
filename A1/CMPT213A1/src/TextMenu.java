package A1.CMPT213A1.src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        for(int i = 0; i < menuTitle.length() + 4; i++){
            System.out.print("#");
        }
        System.out.print("\n");
        System.out.print("# " + menuTitle + " #");
        System.out.print("\n");
        for(int i = 0; i < menuTitle.length() + 4; i++){
            System.out.print("#");
        }
        System.out.print("\n");
        LocalDate date = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd", Locale.ENGLISH);

        String formatted = date.format(formatter);

        System.out.println("Today is: " + formatted);

        for(int i = 0; i < menuOptions.size(); i++){
            System.out.println((i+1) + ": " + menuOptions.get(i));
        }
        System.out.print("Choose an option by entering 1-6: ");
    }   

    private void displayMenuOptions(){
        for(int i = 0; i < menuOptions.size(); i++){
            System.out.println((i+1) + ": " + menuOptions.get(i));
        }
        System.out.print("Choose an option by entering 1-" + menuOptions.size() + ": ");
    }

    public int readUserInput(){
        int optionsLength = menuOptions.size();
        while(true){
            String userInput = scanner.nextLine();
            try {
                int value = Integer.parseInt(userInput.trim());
                if(value >= 1 && value <= optionsLength){
                    handleUserInput(value);
                    return value;
                }
                System.out.println("Invalid selection. Enter a number between 1 and " + optionsLength);
                displayMenuOptions();
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Enter a number between 1 and " + optionsLength);
                displayMenuOptions();
            }
        }
    }

    public void handleUserInput(int value){
        switch (value){
            case 1:
                listAllItems(App.loanInformation);
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

    public void listAllItems(ArrayList<LoanInformation> loanInformation){
        if(loanInformation.size() == 0){
            System.out.println("No items to show.\n");
        }
        
        for(int i = 0; i < loanInformation.size(); i++){
            System.out.println("\n#" + (i+1) + loanInformation.get(i).toString());
        }
        System.out.println("\n");
    }

    public void addItem(){
        System.out.print("Enter the name of the loan item: ");
        String loanItemName = scanner.nextLine();
        System.out.print("\n");

        int yearDone = -1;
        LocalDate actualDate = LocalDate.now();
        do {
            int dueDateYear = -1;
            do{ 
                System.out.print("Enter the year of the due date (e.g., 2026): ");
                dueDateYear = Integer.parseInt(scanner.nextLine());
                if (dueDateYear < 1900){
                    dueDateYear = -1;
                    System.out.println("Year must be at least 1900");
                }
            } while (dueDateYear == -1);
            System.out.print("\n");

            int dueDateMonth = -1;
            do {
                System.out.print("Enter the month of the due date (1-12): ");
                dueDateMonth = Integer.parseInt(scanner.nextLine());
                if (dueDateMonth < 1 || dueDateMonth > 12){
                    dueDateMonth = -1;
                    System.out.println("Month must be between 1 and 12");
                }
            } while (dueDateMonth == -1);
            System.out.print("\n");

            int dueDateDay = -1;
        
            do{
                System.out.print("Enter the day of the due date (1-28/29/30/31): ");
                dueDateDay = Integer.parseInt(scanner.nextLine());
                if(dueDateDay < 1 || dueDateDay > 31){
                    dueDateDay = -1;
                    System.out.println("Day must be bewtween 0 and 30");

                }
            } while(dueDateDay == -1);
            System.out.print("\n");
            try{
                actualDate = LocalDate.of(dueDateYear, dueDateMonth, dueDateDay);
                yearDone = 1;
            }catch(Exception DateTimeException){
                System.out.println("The date entered is invalid, please try again.");
                yearDone = -1;
            }
        } while (yearDone == -1);


        System.out.print("Enter the publisher of the loan item: "); // publisher can be empty
        String publisher = scanner.nextLine();
        System.out.print("\n");

        System.out.print("Enter the name to which the item is loaned: ");
        String nameItemLoaned = scanner.nextLine();
        System.out.print("\n");


        LoanInformation newItem = new LoanInformation(loanItemName, actualDate, publisher, nameItemLoaned);
        
        int i = 0;
        for(LoanInformation loanObject: App.loanInformation){
            if(newItem.dueDate.isBefore(loanObject.dueDate)){
                break;
            }
            i = i + 1;
        }
        App.loanInformation.add(i, newItem);

        System.out.println(loanItemName + " has been added to the list.\n");
    }

    public void removeItem(){
        listAllItems(App.loanInformation);
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
                    System.out.println("Exiting removal mode.");
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
        ArrayList<LoanInformation> filteredLoanInformation =
        App.loanInformation.stream()
            .filter(n -> n.dueDate.isBefore(LocalDate.now()))
            .collect(Collectors.toCollection(ArrayList::new));
        listAllItems(filteredLoanInformation);
    }

    public void listUpcomingItems(){
        ArrayList<LoanInformation> filteredLoanInformation =
            App.loanInformation.stream()
                .filter(n -> !n.dueDate.isBefore(LocalDate.now()))
                .collect(Collectors.toCollection(ArrayList::new));
        listAllItems(filteredLoanInformation);
    }
}
