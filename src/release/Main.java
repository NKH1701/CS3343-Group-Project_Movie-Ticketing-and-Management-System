package release;

import release.database.DefaultData;
import release.exception.ExInvalidOption;
import release.user.UserCenter;

import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        //load default data
        final DefaultData dd = DefaultData.getInstance();
        dd.setUpDB();
//        dd.loadDefaultMoviesToDB();

        Scanner scanner = new Scanner(System.in);
        String input = "";
        final UserCenter uc = UserCenter.getInstance();

        // Welcome message and list all options
        String cityUCinemaLogo = """
                
                  ______  __  .___________.____    ____  __    __       ______  __  .__   __.  _______ .___  ___.      ___     \s
                 /       |  | |           |\\   \\  /   / |  |  |  |     /      ||  | |  \\ |  | |   ____||   \\/   |     /   \\    \s
                |  ,----'|  | `---|  |----` \\   \\/   /  |  |  |  |    |  ,----'|  | |   \\|  | |  |__   |  \\  /  |    /  ^  \\   \s
                |  |     |  |     |  |       \\_    _/   |  |  |  |    |  |     |  | |  . `  | |   __|  |  |\\/|  |   /  /_\\  \\  \s
                |  `----.|  |     |  |         |  |     |  `--'  |    |  `----.|  | |  |\\   | |  |____ |  |  |  |  /  _____  \\ \s
                 \\______||__|     |__|         |__|      \\______/      \\______||__| |__| \\__| |_______||__|  |__| /__/     \\__\\\s
                                                                                                                               \s
                
                                           -- Welcome to CityU Cinema Online Ticketing System ^.^
                """;

        System.out.println(cityUCinemaLogo);
        uc.getCurrentUser().listOptions();
        System.out.println();

        while (true) {
            try {
                // Prompt the user to select option
                System.out.print("Please enter the ID or NAME to select an option (enter \"list\" or \"l\" to view options) \n> ");

                // Read the input string
                input = scanner.nextLine().toLowerCase().trim();
                System.out.println();

                // If enter "l", list options available for current user, then receive next input
                if (input.equals("l") || input.equals("list")) {
                    uc.getCurrentUser().listOptions();
                    System.out.println();
                    continue;
                }

                // Try to get an option and execute the corresponding command
                if (input.matches("-?\\d+"))
                    uc.getCurrentUser().getOption(Integer.parseInt(input))
                            .getCmdClass().getDeclaredConstructor().newInstance().execute(scanner);
                else
                    uc.getCurrentUser().getOption(input)
                            .getCmdClass().getDeclaredConstructor().newInstance().execute(scanner);

                // Exit the system based on input
                if (input.equals("exit") || input.equals("0"))
                    break;
            } catch(NumberFormatException e){   //To handle beyond Integer.MAX_VALUE and Integer.MIN_VALUE
                System.out.println(new ExInvalidOption().getMessage() + "\n");
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
            }
        }

        scanner.close();
    }
}
