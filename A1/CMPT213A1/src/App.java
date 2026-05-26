package A1.CMPT213A1.src;

public class App {
    public static void main(String[] args) throws Exception {
        boolean done = false;
        int counter = 0;
        TextMenu textMenu = new TextMenu("Armans super cool menu");
        textMenu.addMenuOption("List all items");
        textMenu.addMenuOption("Add an item");
        textMenu.addMenuOption("Remove an item");
        textMenu.addMenuOption("List overdue items ");
        textMenu.addMenuOption("List upcoming items");
        textMenu.addMenuOption("Exit");
        do {
            textMenu.displayMenuTitle();
            textMenu.readUserInput();
        } while (!done); 
    }
}
