import java.util.Scanner;
public class Menu {
    Linker linker = new Linker();
    Scanner input = new Scanner(System.in);
    public void baseMenu(){
        System.out.println("Choose an action:\n" +
                "1. Enter Car\n" +
                "2. Exit Car\n" +
                "3. Move section\n" +
                "4. Order section\n" +
                "5. Find car\n" +
                "6. Display section\n" +
                "7. Exit \n");
        switch(input.nextInt()){
            case 1: enterMenu();
                    baseMenu();
                    break;
            case 2: exitMenu();
                    baseMenu();
                    break;
            case 3: moveSectionMenu();
                    baseMenu();
                    break;
            case 4: orderMenu();
                    baseMenu();
                    break;
            case 5: findMenu();
                    baseMenu();
                    break;
            case 6: displayMenu();
                    baseMenu();
                    break;
            case 7: exit();
                    break;
            default:
                System.out.println("Please enter a number between 1 to 6.");
                baseMenu();
        }
    }
    public void enterMenu() {
        System.out.println("Please choose:\n" +
                "1. Enter a certain section.\n" +
                "2. Enter the first empty section.\n");
        switch (input.nextInt()) {
            case 1:
                enterCertainSectionMenu();
                break;
            case 2:
                enterNotCertainSectionMenu();
                break;
            default:
                System.out.println("Please enter a number between 1 and 2.");
                enterMenu();
        }
    }
    public void enterCertainSectionMenu(){
        System.out.println("Enter car ID: ");
        int id = input.nextInt();
        System.out.println("Enter section ID: ");
        int stackNumber = input.nextInt();
        linker.enterCertain(id, stackNumber);
    }
    public void enterNotCertainSectionMenu() {
        System.out.println("Enter car ID: ");
        int id = input.nextInt();
        linker.enter(id);
    }
    public void exitMenu(){
        System.out.println("Enter car ID:");
        int id = input.nextInt();
        if(Stack.pop(id) == null){
            baseMenu();
        }
        linker.exitCar();
    }

    public void moveSectionMenu() {
        System.out.println("Enter first section number (0-4):");
        int stackNumber1 = input.nextInt();
        if (stackNumber1 < 0 || stackNumber1 > 4) {
            System.out.println("Invalid section! Please enter 0-4:");
            stackNumber1 = input.nextInt();}
        System.out.println("Enter second section number (0-4):");
        int stackNumber2 = input.nextInt();
        if (stackNumber2 < 0 || stackNumber2 > 4) {
            System.out.println("Invalid section! Please enter 0-4:");
            stackNumber2 = input.nextInt();
        }
        linker.moveStack(stackNumber1, stackNumber2);
    }

    public void orderMenu() {
        System.out.println("Enter section number:");
        int stackNumber = input.nextInt();
        Stack ordered = linker.orderStack(stackNumber);
        System.out.println("The section is ordered.");
        Stack.display(ordered);
    }
    public void findMenu() {
        System.out.println("Enter car ID:");
        int id = input.nextInt();
        linker.findCar(id);
    }
    public void displayMenu() {
        int stackNumber;
        while (true) {
            System.out.println("Enter section number:");
            stackNumber = input.nextInt();
            if (stackNumber >= 0 && stackNumber <= 4) {
                break;
            }
            System.out.println("Please enter between 0 to 4.");
        }
            linker.displayStack(stackNumber);
    }
    public void exit(){
        System.exit(0);
    }
}