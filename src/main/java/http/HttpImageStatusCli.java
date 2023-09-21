package http;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class HttpImageStatusCli {

    private static final Logger logger = Logger.getLogger(HttpImageStatusCli.class.getName());
    private Boolean flag = true;

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public void askStatus() {
        Scanner scanner = new Scanner(System.in);
        while (Boolean.TRUE.equals(flag)) {
            System.out.println("Enter \"Exit\" to close the program");
            System.out.println("Enter HTTP status code:");

            try {
                int code = scanner.nextInt();
                HttpStatusImageDownloader.downloadStatusImage(code);
            } catch (InputMismatchException e) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    setFlag(false);
                } else {
                    logger.warning("Please enter valid number");
                }
            }
        }
    }
}