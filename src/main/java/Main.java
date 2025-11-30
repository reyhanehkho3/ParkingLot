import java.util.Objects;
public class Main {
    public static void main(String[] args){

            System.out.println("Parking the car with carID :" + c.getCarID() +
                    "\n Enter YES you want to park your car in a specific section.\n" +
                    " If you don't have a specific place in mind type NO.");
            if (Objects.equals(input.next(), "YES")) {
                System.out.println("Enter section (0-4):");
                int section = input.nextInt();
                if (section < 0 || section > 4) {
                    System.out.println("Invalid section! Please enter 0-4:");
                    section = input.nextInt();
                }
                queue.dequeueN(section);
            } else {
                queue.dequeue();
            }
        }
        }

        }


