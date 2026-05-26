package A1.CMPT213A1.src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println(i + ": " + menuOptions.get(i));
        }
    }   
}
