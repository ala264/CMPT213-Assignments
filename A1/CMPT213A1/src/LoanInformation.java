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
        String overdueText = "";

        if (dueDate.isBefore(LocalDate.now())) {
            long daysOverdue = LocalDate.now().toEpochDay() - dueDate.toEpochDay();
            overdueText = " (overdue by " + daysOverdue + " day(s))";
        }

        return "\n" + name +
           "\n- published by: " + publisher +
           "\n- loaned to: " + loanedTo +
           "\n- due on: " + dueDate + overdueText;
    }

}
