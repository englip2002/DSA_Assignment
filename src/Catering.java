
import java.time.LocalDate;
import java.util.Scanner;

public class Catering {

    public static void main(String[] args) {

        // Login Account ID: AC00000, Password: admin
        FileHandler accountsFile = new FileHandler("Accounts.dat");
        MapInterface<String, Account> accountList = new HashMap<>();
        Account admin = new Account("AC00000", "admin", "EAT 99", "ADMIN", "Other", LocalDate.of(2022, 9, 1), "Admin");
        accountList.put(admin.getAccountID(), admin);
        accountsFile.write(accountList);
        accountList = (MapInterface) accountsFile.read();
        Scanner sc = new Scanner(System.in);
        Account loginAccount = null;
        int selection;

        String accountID = "";

        do {
            System.out.println("\nEAT 99 Catering System Login");
            System.out.println("============================");
            System.out.print("Account ID (-1 to exit): ");
            accountID = sc.nextLine();
            loginAccount = accountList.getValue(accountID);

            if (loginAccount != null) {
                System.out.print("Password: ");
                String inputPassword = sc.nextLine();

                if (loginAccount.validatePassword(inputPassword)) {
                    loginAccount.logIn();

                    do {
                        System.out.println("\nEAT 99 Catering System");
                        System.out.println("======================");
                        System.out.println("Current Session: " + loginAccount.getFullName() + " (" + loginAccount.getAccountID() + ")");
                        System.out.println("======================");
                        System.out.println("1. Account Module");
                        System.out.println("2. Menu Module");
                        System.out.println("3. Reservation Module");
                        System.out.println("4. Catering Module");
                        System.out.println("5. Payment Module");
                        System.out.print("Selection (-1 to exit) > ");
                        selection = sc.nextInt();
                        sc.nextLine();

                        switch (selection) {
                            case 1:
                                // Account Module
                                accountModule(loginAccount, accountList);

                                if (!accountList.contains(loginAccount.getAccountID())) {
                                    selection = -1;
                                }
                                break;
                            case 2:
                                // Menu Module
                                break;
                            case 3:
                                // Reservation Module
                                break;
                            case 4:
                                // Catering Module
                                break;
                            case 5:
                                // Payment Module
                                break;
                            case -1:
                                // Exit
                                break;
                            default:
                                System.out.println("Invalid selection! Please try again.");
                                break;
                        }
                    } while (selection != -1);

                    System.out.println("Logging out. Session terminated.");
                } else {
                    System.out.println("Invalid password! Please try again.");
                }
            } else if (accountID.matches("-1")) {
            } else {
                System.out.println("Invalid account id! Please try again.");
            }
        } while (!accountID.matches("-1"));

        System.out.println("System terminated. Thanks for your using!");
        sc.close();
    }

    public static void accountModule(Account loginAccount, MapInterface<String, Account> accountList) {

        FileHandler accountsFile = new FileHandler("Accounts.dat");
        Scanner sc = new Scanner(System.in);
        int selection;
        char choice;

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
                case 1:
                    // Display Account Details
                    accountList = (MapInterface) accountsFile.read();
                    if (loginAccount.isAdmin()) {
                        System.out.println("\nAccounts Details");
                        System.out.println("================");

                        if (accountList.getSize() > 0) {
                            String[] accountIDs = new String[accountList.getSize()];
                            ((HashMap) accountList).getKeys(accountIDs);

                            for (int i = 0; i < accountIDs.length; i++) {
                                System.out.println("Account " + (i + 1));
                                System.out.println(accountList.getValue(accountIDs[i]).toString());
                            }

                            System.out.println("\nNumber of Admin: " + loginAccount.getNoOfAdmin());
                            System.out.println("Number of Customer: " + loginAccount.getNoOfCustomer());
                        } else {
                            System.out.println("No Account Details.");
                        }
                    } else {
                        System.out.println("\nAccount Details");
                        System.out.println("===============");
                        System.out.println(loginAccount.toString());
                    }
                    break;
                case 2:
                    // Register New Account
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

                                if (gender.matches("Male") || gender.matches("Female") || gender.matches("Other")) {
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

                                    if (accountType.matches("Customer") || accountType.matches("Admin")) {
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

                                if (password.matches(password2)) {
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
                                accountList.put(registeredAccount.getAccountID(), registeredAccount);
                                accountsFile.write(accountList);

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
                    break;
                case 3:
                    // Edit Account Details
                    do {
                        System.out.println("\nEdit Account Details");
                        System.out.println("====================");
                        System.out.print("Edit an account? > (Y = yes, N = no) > ");
                        choice = sc.next().charAt(0);
                        sc.nextLine();

                        if (Character.toUpperCase(choice) == 'Y') {
                            System.out.print("Enter account id: ");
                            String accountID = sc.nextLine();
                            Account account = accountList.getValue(accountID);

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

                                        if (gender.matches("Male") || gender.matches("Female") || gender.matches("Other")) {
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

                                                if (accountType.matches("Customer") || accountType.matches("Admin")) {
                                                    isAccountType = true;
                                                    if (accountType.matches("Admin")) {
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

                                        if (password.matches(password2)) {
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
                                        accountList.put(editAccount.getAccountID(), editAccount);
                                        accountsFile.write(accountList);

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
                    break;
                case 4:
                    // Delete Account
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
                                account = accountList.getValue(accountID);
                            }

                            if (account != null) {
                                if (!account.getAccountID().matches("AC00000")) {
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
                                            Account removedAccount = accountList.remove(account.getAccountID());
                                            accountsFile.write(accountList);

                                            if (removedAccount.getAccountType().matches("Admin")) {
                                                Account.setNoOfAdmin(Account.getNoOfAdmin() - 1);
                                            } else {
                                                Account.setNoOfCustomer(Account.getNoOfCustomer() - 1);
                                            }

                                            System.out.println("\nAccount ID: " + removedAccount.getAccountID() + " was removed.");

                                            if (loginAccount.getAccountID().matches(removedAccount.getAccountID())) {
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
                    break;
                case -1:
                    // Exit
                    break;
                default:
                    System.out.println("Invalid selection! Please try again.");
                    break;
            }

        } while (selection != -1);

        System.out.println("Return to last stage.");
    }

}
