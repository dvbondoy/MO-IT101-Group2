public class App
{
    public static void main(String[] args) {
        Payroll.main_menu();
    }

    public static void main_menu()
    {
        System.out.println("");
        System.out.printf("+---------------------------------------------+\n");
        System.out.printf("| %-13s > %-12s < %-12s |%n","", "MPPI System","");
        System.out.printf("+---------------------------------------------+\n");
        System.out.println("[ 1 ] : Payroll");
        System.out.println("[ 2 ] : Inventory");
        System.out.println("[ 3 ] : Print");
        System.out.println("[ 4 ] : Exit\n");
        
        System.out.printf("Enter choice:$ ");
        String choice = Payroll.input.nextLine();

        switch(choice){
            case "1":
                clear_screen();
                Payroll.print_details();
                main_menu();
                break;
            case "2":
                clear_screen();
                System.out.println("\nInventory under construction!");
                main_menu();
                break;
            case "3":
                clear_screen();
                Payroll.out_text();
                System.out.println("Details saved! (out.txt)");
                main_menu();
                break;
            case "4":
                clear_screen();
                Payroll.input.close();
                System.out.println("\nGoodbye!");
                System.exit(0);
                break;
            default:
                clear_screen();
                System.out.println("Invalid entry");
                main_menu();
        }
    }
}
