package A1.CMPT213A1.src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextMenu {
    public String menuTitle;
    public List<String> menuOptions;
    
    public TextMenu(String menuTitle){
        this.menuTitle = menuTitle;
        this.menuOptions = new ArrayList<>();
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
        Scanner scanner = new Scanner(System.in);
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

    }

    public void addItem(){

    }

    public void removeItem(){

    }

    public void listOverdueItems(){

    }

    public void listUpcomingItems(){

    }
}
