package A1.CMPT213A1.src;

import java.time.LocalDate;

public class LoanInformation {
    public String name;
    public LocalDate dueDate;
    public String publisher;
    public String loanedTo;

    public LoanInformation(String name, LocalDate dueDate, String publisher, String loanedTo){
        this.name = name;
        this.dueDate = dueDate;
        this.publisher = publisher;
        this.loanedTo = loanedTo;
    }

    public LoanInformation(String name, LocalDate dueDate, String loanedTo){
        this.name = name;
        this.dueDate = dueDate;
        this.publisher = "";
        this.loanedTo = loanedTo;
    }

    @Override
    public String toString() {
        return "\n" + name + "\npublished by: " + publisher + "\nloanedTo: " + loanedTo + "\ndue on: " + dueDate;
    }

}
