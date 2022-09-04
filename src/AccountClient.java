
// ChiewHongKuang
import java.time.LocalDate;
import java.util.Scanner;

public class AccountClient {

    public static void accountModule(Account loginAccount) {

        FileHandler accountsFile = new FileHandler("Accounts.dat");
        Scanner sc = new Scanner(System.in);
        int selection;

        // Account Menu
        do {
            System.out.println("\nAccount Module");
            System.out.println("==============");

            if (loginAccount.isAdmin()) {
                System.out.println("1. Display Accounts Details");
            } else {
                System.out.println("1. Display Account Details");
            }

            System.out.println("2. Register New Account");
            System.out.println("3. Edit Account Details");
            System.out.println("4. Delete Account");
            System.out.print("Selection (-1 to exit) > ");
            selection = sc.nextInt();
            sc.nextLine();

            switch (selection) {
                case 1 ->
                    displayAccountDetails(accountsFile, loginAccount);
                case 2 ->
                    registerAccount(accountsFile, sc, loginAccount);
                case 3 ->
                    editAccountDetails(accountsFile, sc, loginAccount);
                case 4 ->
                    selection = deleteAccount(accountsFile, sc, loginAccount);
                case -1 -> {
                    // Exit
                }
                default ->
                    System.out.println("Invalid selection! Please try again.");
            }
        } while (selection != -1);

        System.out.println("Return to last stage.");
    }

    public static void displayAccountDetails(FileHandler accountsFile, Account loginAccount) {

        // Display Account Details
        Account.setAccountList((MapInterface) accountsFile.read());
        if (loginAccount.isAdmin()) {
            System.out.println("\nAccounts Details");
            System.out.println("================");

            if (Account.getAccountList().getSize() > 0) {
                String[] accountIDs = (String[]) Account.getAccountList().keySet();

                for (int i = 0; i < accountIDs.length; i++) {
                    System.out.println("Account " + (i + 1));
                    System.out.println(Account.getAccountList().getValue(accountIDs[i]).toString());
                }

                System.out.println("\nNumber of Admin: " + Account.getNoOfAdmin());
                System.out.println("Number of Customer: " + Account.getNoOfCustomer());
            } else {
                System.out.println("No Account Details.");
            }
        } else {
            System.out.println("\nAccount Details");
            System.out.println("===============");
            System.out.println(loginAccount.toString());
        }
    }

    public static void registerAccount(FileHandler accountsFile, Scanner sc, Account loginAccount) {

        // Register New Account
        char choice;
        do {
            System.out.println("\nAccount Registration");
            System.out.println("====================");
            System.out.print("Register an account? > (Y = yes, N = no) > ");
            choice = sc.next().charAt(0);
            sc.nextLine();

            if (Character.toUpperCase(choice) == 'Y') {
                System.out.print("Enter first name: ");
                String firstName = sc.nextLine();
                System.out.print("Enter last name: ");
                String lastName = sc.nextLine();

                boolean isGender = false;
                String gender;

                do {
                    System.out.print("Enter gender (Male / Female / Other): ");
                    gender = sc.nextLine();

                    if (gender.equals("Male") || gender.equals("Female") || gender.equals("Other")) {
                        isGender = true;
                    } else {
                        System.out.println("Wrong gender! Please try again.");
                    }
                } while (!isGender);

                System.out.print("Enter year of birth: ");
                int yob = sc.nextInt();
                System.out.print("Enter month of birth: ");
                int mob = sc.nextInt();
                System.out.print("Enter day of birth: ");
                int dayob = sc.nextInt();
                sc.nextLine();
                LocalDate dob = LocalDate.of(yob, mob, dayob);

                String accountType = "Customer";

                if (loginAccount.isAdmin()) {
                    boolean isAccountType = false;

                    do {
                        System.out.print("Enter account type (Admin / Customer): ");
                        accountType = sc.nextLine();

                        if (accountType.equals("Customer") || accountType.equals("Admin")) {
                            isAccountType = true;
                        } else {
                            System.out.println("Wrong account type! Please try again.");
                        }
                    } while (!isAccountType);
                }

                boolean isMatch = false;
                String password;

                do {
                    System.out.print("Enter password: ");
                    password = sc.nextLine();
                    System.out.print("Confirm password: ");
                    String password2 = sc.nextLine();

                    if (password.equals(password2)) {
                        isMatch = true;
                    } else {
                        System.out.println("Wrong password! Please try again.");
                    }
                } while (!isMatch);

                System.out.print("Confirm to register? (Y = yes, N = no) > ");
                choice = sc.next().charAt(0);
                sc.nextLine();

                if (Character.toUpperCase(choice) == 'Y') {
                    String accountID = String.format("AC%05d", Account.getIDCount());
                    Account registeredAccount = new Account(accountID, password, firstName, lastName, gender, dob, accountType);
                    registeredAccount.addAccount();
                    accountsFile.write(Account.getAccountList());

                    System.out.println("\nRegistered Account");
                    System.out.println("==================");
                    System.out.println(registeredAccount.toString());
                } else {
                    System.out.print("Registration discarded!");
                }
            }

            if (Character.toUpperCase(choice) == 'N') {
                System.out.println("Access denied.");
            }
        } while (Character.toUpperCase(choice) == 'Y');
    }

    public static void editAccountDetails(FileHandler accountsFile, Scanner sc, Account loginAccount) {

        // Edit Account Details
        char choice;
        do {
            System.out.println("\nEdit Account Details");
            System.out.println("====================");
            System.out.print("Edit an account? > (Y = yes, N = no) > ");
            choice = sc.next().charAt(0);
            sc.nextLine();

            if (Character.toUpperCase(choice) == 'Y') {
                System.out.print("Enter account id: ");
                String accountID = sc.nextLine();
                Account account = Account.getAccountList().getValue(accountID);

                if (account != null) {
                    System.out.print("Enter password: ");
                    String inputPassword = sc.nextLine();

                    if (account.validatePassword(inputPassword)) {
                        System.out.print("Edit first name: ");
                        String firstName = sc.nextLine();
                        System.out.print("Edit last name: ");
                        String lastName = sc.nextLine();

                        boolean isGender = false;
                        String gender;

                        do {
                            System.out.print("Edit gender (Male / Female / Other): ");
                            gender = sc.nextLine();

                            if (gender.equals("Male") || gender.equals("Female") || gender.equals("Other")) {
                                isGender = true;
                            } else {
                                System.out.println("Wrong gender! Please try again.");
                            }
                        } while (!isGender);

                        System.out.print("Edit year of birth: ");
                        int yob = sc.nextInt();
                        System.out.print("Edit month of birth: ");
                        int mob = sc.nextInt();
                        System.out.print("Edit day of birth: ");
                        int dayob = sc.nextInt();
                        sc.nextLine();
                        LocalDate dob = LocalDate.of(yob, mob, dayob);

                        String accountType = "Customer";

                        if (loginAccount.isAdmin()) {
                            if (!account.isAdmin()) {
                                boolean isAccountType = false;

                                do {
                                    System.out.print("Reset account type (Admin / Customer): ");
                                    accountType = sc.nextLine();

                                    if (accountType.equals("Customer") || accountType.equals("Admin")) {
                                        isAccountType = true;
                                        if (accountType.equals("Admin")) {
                                            Account.setNoOfAdmin(Account.getNoOfAdmin() + 1);
                                            Account.setNoOfCustomer(Account.getNoOfCustomer() - 1);
                                        }
                                    } else {
                                        System.out.println("Wrong account type! Please try again.");
                                    }
                                } while (!isAccountType);
                            } else {
                                accountType = "Admin";
                            }
                        }

                        boolean isMatch = false;
                        String password;

                        do {
                            System.out.print("Reset password: ");
                            password = sc.nextLine();
                            System.out.print("Confirm password: ");
                            String password2 = sc.nextLine();

                            if (password.equals(password2)) {
                                isMatch = true;
                            } else {
                                System.out.println("Wrong password! Please try again.");
                            }
                        } while (!isMatch);

                        System.out.print("Confirm to edit? (Y = yes, N = no) > ");
                        choice = sc.next().charAt(0);
                        sc.nextLine();

                        if (Character.toUpperCase(choice) == 'Y') {
                            System.out.println("\nAccount Details (Before Edit)");
                            System.out.println("=============================");
                            System.out.println(account.toString());

                            Account editAccount = new Account(accountID, password, firstName, lastName, gender, dob, accountType, account.getIsLogin(), account.getReservationList());
                            editAccount.addAccount();
                            accountsFile.write(Account.getAccountList());

                            System.out.println("\nAccount Details (After Edit)");
                            System.out.println("============================");
                            System.out.println(editAccount.toString());
                        } else {
                            System.out.print("Edit discarded! ");
                        }
                    } else {
                        System.out.print("Invalid password! ");
                        choice = 'N';
                    }
                } else {
                    System.out.print("Invalid account id! ");
                    choice = 'N';
                }
            }

            if (Character.toUpperCase(choice) == 'N') {
                System.out.println("Access denied.");
            }
        } while (Character.toUpperCase(choice) == 'Y');
    }

    public static int deleteAccount(FileHandler accountsFile, Scanner sc, Account loginAccount) {

        // Delete Account
        int selection = 0;
        char choice;
        do {
            System.out.println("\nDelete Account");
            System.out.println("==============");

            if (!loginAccount.isAdmin()) {
                System.out.print("Delete your account? > (Y = yes, N = no) > ");
            } else {
                System.out.print("Delete an account? > (Y = yes, N = no) > ");
            }

            choice = sc.next().charAt(0);
            sc.nextLine();

            if (Character.toUpperCase(choice) == 'Y') {
                Account account;

                if (!loginAccount.isAdmin()) {
                    account = loginAccount;
                    System.out.println("Account ID: " + account.getAccountID());
                } else {
                    System.out.print("Enter account id: ");
                    String accountID = sc.nextLine();
                    account = Account.getAccountList().getValue(accountID);
                }

                if (account != null) {
                    if (!account.getAccountID().equals("AC00000")) {
                        System.out.print("Enter password: ");
                        String inputPassword = sc.nextLine();

                        if (account.validatePassword(inputPassword)) {
                            System.out.println("\nAccount Details");
                            System.out.println("===============");
                            System.out.println(account.toString());

                            System.out.print("Confirm to delete? (Y = yes, N = no) > ");
                            choice = sc.next().charAt(0);
                            sc.nextLine();

                            if (Character.toUpperCase(choice) == 'Y') {
                                Account removedAccount = account.removeAccount();
                                accountsFile.write(Account.getAccountList());

                                if (removedAccount.getAccountType().equals("Admin")) {
                                    Account.setNoOfAdmin(Account.getNoOfAdmin() - 1);
                                } else {
                                    Account.setNoOfCustomer(Account.getNoOfCustomer() - 1);
                                }

                                System.out.println("\nAccount ID: " + removedAccount.getAccountID() + " was removed.");

                                if (loginAccount.getAccountID().equals(removedAccount.getAccountID())) {
                                    loginAccount.logOut();
                                    selection = -1;
                                    choice = 'N';
                                }
                            } else {
                                System.out.print("\nDeletion discarded! ");
                            }
                        } else {
                            System.out.print("Invalid password! ");
                            choice = 'N';
                        }
                    } else {
                        System.out.print("Account ID: AC00000 is not allowed to delete! ");
                        choice = 'N';
                    }
                } else {
                    System.out.print("Invalid account id! ");
                    choice = 'N';
                }
            }

            if (Character.toUpperCase(choice) == 'N') {
                System.out.println("Access denied.");
            }
        } while (Character.toUpperCase(choice) == 'Y');
        return selection;
    }
}
