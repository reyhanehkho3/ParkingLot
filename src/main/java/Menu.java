import java.util.Scanner;
public class Menu {
    Linker linker = new Linker();
    Scanner input = new Scanner(System.in);
    public void baseMenu(){
        System.out.println("Choose an action:" +
                "1. Enter Car" +
                "2. Exit Car" +
                "3. Move section" +
                "4. Order section");
        switch(input.nextInt()){
            case 1: enterMenu();
                break;
            case 2: exitMenu();
                break;
            case 3: moveSectionMenu();
                break;
            case 4: orderMenu();
                break;
            default:
        }
    }
    public void enterMenu() {
        System.out.println("Please choose:" +
                "1. Enter a certain section." +
                "2. Enter the first empty section.");
        switch (input.nextInt()) {
            case 1:
                enterCertainSectionMenu();
                break;
            case 2:
                enterNotCertainSectionMenu();
                break;
            default:
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
        linker.exitCar(id);
    }

    public void moveSectionMenu() {
        System.out.println("Enter first section number:");
        int stackNumber1 = input.nextInt();
        System.out.println("Enter second section number:");
        int stackNumber2 = input.nextInt();
        linker.moveStack(stackNumber1, stackNumber2);
    }

    public void orderMenu(){
        System.out.println("Enter section number:");
        int stackNumber = input.nextInt();
        Stack ordered = linker.orderStack(stackNumber);
        Stack.display(ordered);
        }
    }
}
