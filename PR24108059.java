import java.util.*;

public class myWork {

    static Scanner input=new Scanner(System.in);

    final static double XS = 600;
    final static double S= 800;
    final static double M = 900;
    final static double L = 1000;
    final static double XL = 1100;
    final static double XXL = 1200;

    static final int PROCESSING = 0;
    static final int DELIVERING = 1;
    static final int DELIVERED = 2;

    static String[] orderIdArray = new String[0];
    static String[] phoneNumberArray = new String[0];
    static String[] sizeArray = new String[0];
    static int[] qtyArray = new int[0];
    static double[] amountArray = new double[0];
    static int[] orderStatusArray = new int[0];

    public static void extendArrays(){
        String[] tempOrderIdArray = new String[orderIdArray.length+1];
        String[] tempPhoneNumberArray = new String[orderIdArray.length+1];
        String[] tempSizeArray = new String[orderIdArray.length+1];
        int[] tempQtyArray = new int[orderIdArray.length+1];
        double[] tempAmountArray = new double[orderIdArray.length+1];
        int[] tempStatusArray = new int[orderIdArray.length+1];

        for (int i = 0; i < orderIdArray.length; i++) {
            tempOrderIdArray[i] = orderIdArray[i];
            tempPhoneNumberArray[i] = phoneNumberArray[i];
            tempSizeArray[i] = sizeArray[i];
            tempQtyArray[i] = qtyArray[i];
            tempAmountArray[i] = amountArray[i];
            tempStatusArray[i] = orderStatusArray[i];


        }

        orderIdArray = tempOrderIdArray;
        phoneNumberArray = tempPhoneNumberArray;
        sizeArray = tempSizeArray;
        qtyArray = tempQtyArray;
        amountArray = tempAmountArray;
        orderStatusArray = tempStatusArray;



    }


    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
// Handle any exceptions.
        }
    }
    /* // Move the cursor up five lines System.out.print("\033[5A");
// Clear the lines
System.out.print("\033[0J"); */

    public static String generateId() {
        int id1;
        if (orderIdArray.length > 0) {
            id1 = Integer.parseInt(orderIdArray[orderIdArray.length - 1].split("[#]")[1]);
            id1++;
        } else {
            return "ODR#00001";
        }
        return String.format("ODR#%05d", id1);
    }

    public static boolean validateNumber(String phoneNumber) {
        if (phoneNumber.length() == 10 && phoneNumber.charAt(0) == '0') {
            return true;
        }
        return false;
    }

    public static boolean validateQty(int qty) {
        if (qty > 0 ) {
            return true;
        }
        return false;
    }

    public static void placeOrder() {
        l1:do {
            System.out.println("\t\tPlace Order");
            System.out.println("-".repeat(60));
            System.out.println("\n\n");
            System.out.println("Enter Order ID : " + generateId());
            String phoneNumber;
            l2:do {
                System.out.print("Enter Customer Phone Number : ");
                phoneNumber = input.next();
                boolean isValidNumber = validateNumber(phoneNumber);
                if (isValidNumber) {
                    break;
                }
                do {

                    System.out.println("invalid Phone Number ");
                    System.out.println("Do you want to enter phone number again : ");
                    char response = input.next().charAt(0);
                    if (response == 'y' || response == 'Y') {
                        continue l2;
                    } else if (response == 'n' || response == 'N') {
                        clearConsole();
                        homePage();

                    }
                    System.out.println("Enter Y or N : ");

                }while(true);

            } while (true);

            System.out.print("Enter T-Shirt Size (XS/S/M/L/XL/XXL) : ");
            String size = input.next();

            int qty;
            l3:do {
                System.out.println("Enter Qty : ");
                qty = input.nextInt();
                boolean isValidQty = validateQty(qty);
                if (isValidQty) {
                    break;
                }
                do {

                    System.out.println("invalid Qty ");
                    System.out.println("Do you want to enter Qty again : ");
                    char response = input.next().charAt(0);
                    if (response == 'y' || response == 'Y') {
                        continue l3;
                    } else if (response == 'n' || response == 'N') {
                        clearConsole();
                        homePage();

                    }
                    System.out.println("Enter Y or N : ");

                }while(true);

            } while (true);

            double amount;
            switch (size) {
                case "XS":
                    amount = XS * qty;
                    break;
                case "S":
                    amount = S * qty;
                    break;
                case "M":
                    amount = M * qty;
                    break;
                case "L":
                    amount = L * qty;
                    break;
                case "XL":
                    amount = XL * qty;
                    break;
                case "XXL":
                    amount = XXL * qty;
                    break;
                default:
                    amount = 0;

                    break;
            }

            System.out.println("amount : " + amount);

            System.out.print("DO you want to place this order ? (y/n) : ");
            char response = input.next().charAt(0);
            if (response == 'y' || response == 'Y') {
                extendArrays();
                orderIdArray[orderIdArray.length - 1] = generateId();
                phoneNumberArray[phoneNumberArray.length - 1] = phoneNumber;
                sizeArray[sizeArray.length - 1] = size;
                qtyArray[qtyArray.length - 1] = qty;
                amountArray[amountArray.length - 1] = amount;
                orderStatusArray[orderStatusArray.length - 1] = PROCESSING;
                System.out.println("Order Placed");
            }
            do {
                System.out.println("Do you want to Place Another Order ? (y/n) : ");
                response = input.next().charAt(0);
                if (response == 'y' || response == 'Y') {
                    continue l1;
                } else if (response == 'n' || response == 'N') {
                    clearConsole();
                    homePage();
                }
                System.out.println("Enter Y or N");
            }while(true);






        }while(true);


    }

    public static int searchCustomerByPhoneNumber(String phoneNumber) {
        for (int i = 0; i < orderIdArray.length; i++) {
            if (phoneNumberArray[i].equals(phoneNumber)) {
                return i;
            }
        }
        return -1;
    }

    public static void getCustomerDetails(String phoneNumber) {
        String[] tempSizeArray = new String[6];
        int[] tempQtyArray = new int[6];
        double[] tempAmountArray = new double[6];

        tempSizeArray[0] = "XS";
        tempSizeArray[1] = "S";
        tempSizeArray[2] = "M";
        tempSizeArray[3] = "L";
        tempSizeArray[4] = "XL";
        tempSizeArray[5] = "XXL";

        for (int i = 0; i < orderIdArray.length; i++) {
            if (phoneNumberArray[i].equals(phoneNumber)) {
                if(sizeArray[i].equals("XS")) {
                    tempQtyArray[0] += qtyArray[i];
                    tempAmountArray[0] += amountArray[i];

                }else if(sizeArray[i].equals("S")) {
                    tempQtyArray[1] += qtyArray[i];
                    tempAmountArray[1] += amountArray[i];

                }else if(sizeArray[i].equals("M")) {
                    tempQtyArray[2] += qtyArray[i];
                    tempAmountArray[2] += amountArray[i];

                }else if(sizeArray[i].equals("L")) {
                    tempQtyArray[3] += qtyArray[i];
                    tempAmountArray[3] += amountArray[i];

                }else if(sizeArray[i].equals("XL")) {
                    tempQtyArray[4] += qtyArray[i];
                    tempAmountArray[4] += amountArray[i];
                }else if(sizeArray[5].equals("XXL")) {
                    tempQtyArray[5] += qtyArray[i];
                    tempAmountArray[5] += amountArray[i];
                }else{
                    continue;
                }
            }
        }
        double totalAmount = 0;
        for (int i = 0; i < 6; i++) {
            totalAmount += tempAmountArray[i];

        }

        String[] tempSizeSoringArray = new String[6];
        int[] tempQtySoringArray = new int[6];
        double[] tempAmountSoringArray = new double[6];

        for (int i = 0; i < 6; i++) {
            tempSizeSoringArray[i] = tempSizeArray[i];
            tempQtySoringArray[i] = tempQtyArray[i];
            tempAmountSoringArray[i] = tempAmountArray[i];
        }
        for (int i = 0; i < tempSizeSoringArray.length-1; i++) {
            for (int j = 0; j < tempSizeSoringArray.length-1; j++) {
                if (tempAmountSoringArray[j] < tempAmountSoringArray[j+1]) {
                    String tempSize = tempSizeSoringArray[j];
                    tempSizeSoringArray[j] = tempSizeSoringArray[j+1];
                    tempSizeSoringArray[j+1] = tempSize;

                    double tempAmount = tempAmountSoringArray[j];
                    tempAmountSoringArray[j] = tempAmountSoringArray[j+1];
                    tempAmountSoringArray[j+1] = tempAmount;

                    int qty = tempQtySoringArray[j];
                    tempQtySoringArray[j] = tempQtySoringArray[j+1];
                    tempQtySoringArray[j+1] = qty;


                }

            }

        }

        System.out.printf(" + %-20s + %-20s + %40s +","-".repeat(20),"-".repeat(20),"-".repeat(40));
        System.out.printf(" + %-20s + %-20s + %40s +","\t Size","\t Qty","\t\t Amount");
        System.out.printf(" + %-20s + %-20s + %40s +","-".repeat(20),"-".repeat(20),"-".repeat(40));

        for (int i = 0; i < tempAmountArray.length; i++) {
            System.out.printf("| %-20s + %-20d + %38.2f |",tempSizeSoringArray[i],tempQtySoringArray[i],tempAmountSoringArray[i]);

        }

        System.out.printf(" + %-20s + %-20s + %40s +","-".repeat(20),"-".repeat(20),"-".repeat(40));
        System.out.printf(" | %-60s | %20s |","\t Total Amount", totalAmount);
        System.out.printf(" + %-20s + %-20s + %40s +","-".repeat(20),"-".repeat(20),"-".repeat(40));





    }

    public static void searchCustomer(){
        l5:do {
            System.out.println("Search Customer");
            System.out.println("-".repeat(60));
            System.out.print("Enter Customer Phone Number : ");
            String phoneNumber = input.next();
            int index = searchCustomerByPhoneNumber(phoneNumber);
            if (index != -1) {
                System.out.println("invalid Phone Number ");
            }
            else{
                getCustomerDetails(phoneNumber);
            }
            do {

                System.out.print("Do you want to search another customer report ? : (y/n) ");
                char response = input.next().charAt(0);
                if (response == 'y' || response == 'Y') {
                    continue l5;
                } else if (response == 'n' || response == 'N') {
                    clearConsole();
                    homePage();

                }
                System.out.println("Enter y or n only");

            }while(true);




        }while(true);

    }

    public static int validateOrderId(String orderId) {
        for (int i = 0; i < orderIdArray.length; i++) {
            if (orderId.equals(orderIdArray[i])) {
                return i;

            }

        }
        return -1;
    }

    public static void printOrderDetails(int index){
        System.out.println("phone number : " + phoneNumberArray[index]);
        System.out.println("size : " + sizeArray[index]);
        System.out.println("qty : " + qtyArray[index]);
        System.out.println("amount : " + amountArray[index]);
        if (orderStatusArray[index] == 0) {
            System.out.println("Status : Processing");
        } else if (orderStatusArray[index] == 1) {
            System.out.println("Status : Delivering");

        }else {
            System.out.println("Status : Delivered");
        }

    }

    public static void searchOrder() {
        l4:do {
            System.out.println("Search Order");
            System.out.println("-".repeat(60));
            System.out.println("Enter Order ID : ");
            String orderId = input.next();
            int index = validateOrderId(orderId);
            if (index == -1) {
                System.out.println("Invalid Order ID");
            }else{
                printOrderDetails(index);
            }
            do {

                System.out.println("Do you want to search another order ? (y/n) : ");
                char response = input.next().charAt(0);
                if (response == 'y' || response == 'Y') {
                    continue l4;
                }else if (response == 'n' || response == 'N') {
                    clearConsole();
                    homePage();
                }
                System.out.println("Enter Y or N ");
            }while(true);
        }while(true);
    }

    public static void viewReports(){
        System.out.println("\t\tReports");
        System.out.println("-".repeat(60));
        System.out.println("\n\n");
        System.out.println("\t[1] Customer Reports");
        System.out.println("\t[2] Item Reports");
        System.out.println("\t[3] Orders Reports");
        System.out.print("input an option : ");
        int option=input.nextInt();
        switch (option) {
            case 1:
                customerReport();
                break;
                case 2:
                    itemReport();
                    break;
                    case 3:
                        ordersReport();
                        break;
                        default:
                            System.out.println("Invalid option");
                            break;
        }

    }

    public static void customerReport(){
        System.out.println("-----------------Customer Report-------------------");
        System.out.println("\n\n");
        System.out.println("\t[1] Best in Customers");
        System.out.println("\t[2] View Customers");
        System.out.println("\t[3] All Customer Report");
        System.out.print("Enter Option : ");
        int option=input.nextInt();
        switch (option) {
            case 1:
                bestInCustomer();
                break;
                case 2:
                    viewCustomers();
                    break;
                    case 3:
                        allCustomerReports();
                        break;
                        default:
                            System.out.println("Invalid Option");
                            break;
        }

    }

    public static void bestInCustomer(){
        do {
            System.out.println("+++++ Best In Customers +++++");
            System.out.println("-".repeat(60));
            bestInCustomerByAmount();
            System.out.println("To access the Main Menu , Please enter 0 :");
            int option=input.nextInt();
            clearConsole();
            if (option == 0) {
                homePage();

            }else {
                System.out.println("invalid option");
                continue;
            }




        }while(true);


    }

    public static void bestInCustomerByAmount(){
        String[] tempPhoneNumberArray = new String[0];
        int[] tempQtyArray = new int[0];
        double[] tempAmountArray = new double[0];

        l1:for (int i = 0; i < phoneNumberArray.length; i++) {
            for (int j = 0; j < tempPhoneNumberArray.length; j++) {
                if (phoneNumberArray[i].equals(tempPhoneNumberArray[j])) {
                    tempQtyArray[j] += qtyArray[i];
                    tempAmountArray[j] += amountArray[i];
                    continue l1;
                }
            }

            String[] tempPhoneNumberArray1 = new String[phoneNumberArray.length+1];
            int[] tempQtyArray1 = new int[phoneNumberArray.length+1];
            double[] tempAmountArray1 = new double[phoneNumberArray.length+1];

            for (int k = 0; k < tempPhoneNumberArray.length; k++) {
                tempPhoneNumberArray1[k] = tempPhoneNumberArray[k];
                tempQtyArray1[k] = tempQtyArray[k];
                tempAmountArray1[k] = tempAmountArray[k];

            }
            tempPhoneNumberArray = tempPhoneNumberArray1;
            tempQtyArray = tempQtyArray1;
            tempAmountArray = tempAmountArray1;

            tempPhoneNumberArray[tempPhoneNumberArray.length-1] = phoneNumberArray[i];
            tempQtyArray[tempQtyArray.length-1] = qtyArray[i];
            tempAmountArray[tempAmountArray.length-1] = amountArray[i];



        }

        for (int i = 0; i < tempPhoneNumberArray.length-1; i++) {
            for (int j = 0; j < tempPhoneNumberArray.length-1; j++) {
                if(tempAmountArray[j]<tempAmountArray[j+1]){
                    double tempAmount = tempAmountArray[j];
                    tempAmountArray[j] = tempAmountArray[j+1];
                    tempAmountArray[j+1] = tempAmount;

                    int tempQty = tempQtyArray[j];
                    tempQtyArray[j] = tempQtyArray[j+1];
                    tempQtyArray[j+1] = tempQty;


                    String tempPhoneNumber = tempPhoneNumberArray[j];
                    tempPhoneNumberArray[j] = tempPhoneNumberArray[j+1];
                    tempPhoneNumberArray[j+1] = tempPhoneNumber;



                }
            }
        }

        System.out.println("+---------------+----------------+-----------------+");
        System.out.println("|  Customer ID  |      QTY       |   Total Amount  |");
        System.out.println("+---------------+----------------+-----------------+");

        for (int i = 0; i < tempPhoneNumberArray.length; i++) {
            System.out.printf("| %-13s | %-14s | %15.2f |\n", tempPhoneNumberArray[i], tempQtyArray[i],tempAmountArray[i]);
        }
        System.out.println("+---------------+----------------+-----------------+");

    }

    public static void viewCustomers(){
        do {
            System.out.println("++++ View Customers ++++");
            System.out.println("-".repeat(60));
            viewCustomer();
            System.out.println("To access the Main Menu , Please enter 0 :");
            int option=input.nextInt();
            clearConsole();
            if (option == 0) {
                homePage();

            }else {
                System.out.println("invalid option");
                continue;
            }

        }while (true);
    }

    public static void viewCustomer(){
        String[] tempPhoneNumberArray = new String[0];
        int[] tempQtyArray = new int[0];
        double[] tempAmountArray = new double[0];

        l1:for (int i = 0; i < phoneNumberArray.length; i++) {
            for (int j = 0; j < tempPhoneNumberArray.length; j++) {
                if (phoneNumberArray[i].equals(tempPhoneNumberArray[j])) {
                    tempQtyArray[j] += qtyArray[i];
                    tempAmountArray[j] += amountArray[i];
                    continue l1;
                }
            }

            String[] tempPhoneNumberArray1 = new String[phoneNumberArray.length+1];
            int[] tempQtyArray1 = new int[phoneNumberArray.length+1];
            double[] tempAmountArray1 = new double[phoneNumberArray.length+1];

            for (int k = 0; k < tempPhoneNumberArray.length; k++) {
                tempPhoneNumberArray1[k] = tempPhoneNumberArray[k];
                tempQtyArray1[k] = tempQtyArray[k];
                tempAmountArray1[k] = tempAmountArray[k];

            }
            tempPhoneNumberArray = tempPhoneNumberArray1;
            tempQtyArray = tempQtyArray1;
            tempAmountArray = tempAmountArray1;

            tempPhoneNumberArray[tempPhoneNumberArray.length-1] = phoneNumberArray[i];
            tempQtyArray[tempQtyArray.length-1] = qtyArray[i];
            tempAmountArray[tempAmountArray.length-1] = amountArray[i];



        }

        for (int i = 0; i < tempPhoneNumberArray.length; i++) {
            System.out.printf("| %-13s | %-14s | %15.2f |\n", tempPhoneNumberArray[i], tempQtyArray[i],tempAmountArray[i]);
        }
        System.out.println("+---------------+----------------+-----------------+");
    }

   public static void allCustomerReports(){
       do {


           System.out.println("++++ All Customer Reports ++++");
           System.out.println("-".repeat(60));
           viewAllCustomerReports();
           System.out.println("To access the Main Menu , Please enter 0 :");
           int option=input.nextInt();
           clearConsole();
           if (option == 0) {
               homePage();

           }else {
               System.out.println("invalid option");
               continue;
           }




       }while(true);
       }


    public static void viewAllCustomerReports() {
        String[] tempPhoneNumberArray = new String[0];
        if (phoneNumberArray.length > 0) {
            L1: for (int i = 0; i < phoneNumberArray.length; i++) {
                for (int j = 0; j < tempPhoneNumberArray.length; j++) {
                    if (phoneNumberArray[i].equals(tempPhoneNumberArray[j])) {
                        continue L1;
                    }
                }
                String[] tempPhoneNumberArray1 = new String[tempPhoneNumberArray.length + 1];
                for (int k = 0; k < tempPhoneNumberArray.length; k++) {
                    tempPhoneNumberArray1[k] = tempPhoneNumberArray[k];
                }
                tempPhoneNumberArray = tempPhoneNumberArray1;
                tempPhoneNumberArray[tempPhoneNumberArray.length - 1] = phoneNumberArray[i];
            }
        }

        System.out.println("+-----------------+---------+---------+---------+---------+---------+---------+-----------------+");
        System.out.printf("| %-15s | %-7s | %-7s | %-7s | %-7s | %-7s | %-7s | %-15s |\n", "Phone Number","XS", "S", "M",
                "L", "XL", "XXL", "Total Amount");
        System.out.println("+-----------------+---------+---------+---------+---------+---------+---------+-----------------+");

        for (int i = 0; i < tempPhoneNumberArray.length; i++) {
            String[] tempSizeArray = { "XS", "S", "M", "L", "XL", "XXL" };
            int[] tempQtyArray = new int[6];

            for (int j = 0; j < phoneNumberArray.length; j++) {
                if (tempPhoneNumberArray[i].equals(phoneNumberArray[j])) {
                    switch (sizeArray[j]) {
                        case "XS":
                            tempQtyArray[0] += qtyArray[j];
                            break;
                        case "S":
                            tempQtyArray[1] += qtyArray[j];
                            break;
                        case "M":
                            tempQtyArray[2] += qtyArray[j];
                            break;
                        case "L":
                            tempQtyArray[3] += qtyArray[j];
                            break;
                        case "XL":
                            tempQtyArray[4] += qtyArray[j];
                            break;
                        case "XXL":
                            tempQtyArray[5] += qtyArray[j];
                            break;
                    }
                }
            }

            double total = 0;
            for (int j = 0; j < tempQtyArray.length; j++) {
                total += tempQtyArray[j] * (tempSizeArray[j].equals("XS") ? XS
                        : tempSizeArray[j].equals("S") ? S
                        : tempSizeArray[j].equals("M") ? M
                        : tempSizeArray[j].equals("L") ? L : tempSizeArray[j].equals("XL") ? XL : XXL);
            }
            System.out.printf("| %-15s | %-7s | %-7s | %-7s | %-7s | %-7s | %-7s | %15.2f |\n",tempPhoneNumberArray[i],tempQtyArray[0],tempQtyArray[1],tempQtyArray[2],tempQtyArray[3],tempQtyArray[4],tempQtyArray[5],total);

        }
        System.out.println("+-----------------+---------+---------+---------+---------+---------+---------+-----------------+");

    }

    public static void itemReport(){
        System.out.println("+++ Item Reports +++ ");
        System.out.println("-".repeat(60));
        System.out.println("\t[1] Best Selling Categories Sorted By QTY ");
        System.out.println("\t[2] Best Selling Categories Sorted By Amount ");
        System.out.print("Enter Option : ");
        int option=input.nextInt();
        clearConsole();
        switch (option) {
            case 1:
                bestSellingCategoriesSortedByQty();
                break;
                case 2:
                    bestSellingCategoriesSortedByAmount();
                    break;
                    default:
                        System.out.println("Invalid Option");
        }

    }

    public static void bestSellingCategoriesSortedByQty(){
        do {
            System.out.println("+++ Sorted By QTY +++ ");
            System.out.println("-".repeat(60));
            sortedByQty();
            System.out.println("To access the Main Menu , Please enter 0 :");
            int option=input.nextInt();
            clearConsole();
            if (option == 0) {
                homePage();

            }else {
                System.out.println("invalid option");
                continue;
            }


        }while(true);

    }

    public static void sortedByQty(){
        String[] tempSizeArray = { "XS", "S", "M", "L", "XL", "XXL" };
        int[] tempQtyArray = new int[6];
        double[] tempAmountArray = new double[6];

        for (int i = 0; i < orderIdArray.length; i++) {
            if (sizeArray[i].equals("XS")) {
                tempQtyArray[0] += qtyArray[i];
                tempAmountArray[0] += qtyArray[i];
            } else if (sizeArray[i].equals("S")) {
                tempQtyArray[1] += qtyArray[i];
                tempAmountArray[1] += qtyArray[i];

            } else if (sizeArray[i].equals("M")) {
                tempQtyArray[2] += qtyArray[i];
                tempAmountArray[2] += qtyArray[i];
            } else if (sizeArray[i].equals("L")) {
                tempQtyArray[3] += qtyArray[i];
                tempAmountArray[3] += qtyArray[i];

            } else if (sizeArray[i].equals("XL")) {
                tempQtyArray[4] += qtyArray[i];
                tempAmountArray[4] += qtyArray[i];

            } else if (sizeArray[i].equals("XXL")) {
                tempQtyArray[5] += qtyArray[i];
                tempAmountArray[5] += qtyArray[i];

            } else
                continue;

        }
            int[] tempQtySortingArray = new int[6];
            double[] tempAmountSortingArray = new double[6];
            String[] tempSizeSortingArray = new String[6];

            for (int k = 0; k < 6; k++) {
                tempQtySortingArray[k] = tempQtyArray[k];
                tempAmountSortingArray[k] = tempAmountArray[k];
                tempSizeSortingArray[k] = tempSizeArray[k];



            }
            for (int i = 0; i < tempQtyArray.length-1; i++) {
                for (int j = 0; j < tempQtyArray.length-1; j++) {
                    if (tempQtySortingArray[j] < tempQtySortingArray[j+1]) {
                        int tempQty = tempQtySortingArray[j];
                        tempQtySortingArray[j] = tempQtySortingArray[j+1];
                        tempQtySortingArray[j+1] = tempQty;

                        double tempAmount = tempAmountSortingArray[j];
                        tempAmountSortingArray[j] = tempAmountSortingArray[j+1];
                        tempAmountSortingArray[j+1] = tempAmount;

                        String tempSize = tempSizeSortingArray[j];
                        tempSizeSortingArray[j] = tempSizeSortingArray[j+1];
                        tempSizeSortingArray[j+1] = tempSizeSortingArray[j];




                }

            }


        }
        System.out.println("+---------------+----------------+-----------------+");
        System.out.println("|      Size     |      QTY       |   Total Amount  |");
        System.out.println("+---------------+----------------+-----------------+");

        for (int i = 0; i < tempQtyArray.length; i++) {
            System.out.printf("| %-13s | %-14s | %15.2f |\n", tempSizeSortingArray[i], tempQtySortingArray[i],
                    tempAmountSortingArray[i]);
        }
        System.out.println("+---------------+----------------+-----------------+");



    }

    public static void bestSellingCategoriesSortedByAmount(){
        do {


            System.out.println("+++ Sorted By Amount +++ ");
            System.out.println("-".repeat(60));
            sortedByAmount();
            System.out.println("To access the Main Menu , Please enter 0 :");
            int option = input.nextInt();
            clearConsole();
            if (option == 0) {
                homePage();
            } else {
                System.out.println("invalid option");
            }
        } while (true);
    }

    public static void sortedByAmount(){
        String[] tempSizeArray = { "XS", "S", "M", "L", "XL" , "XXL" };
        int[] tempQtyArray = new int[6];
        double[] tempAmountArray = new double[6];

        for (int i = 0; i < orderIdArray.length; i++) {
            if (sizeArray[i].equals("XS")) {
                tempQtyArray[0] += qtyArray[i];
                tempAmountArray[0] += qtyArray[i];
            }else if (sizeArray[i].equals("S")) {
                tempQtyArray[1] += qtyArray[i];
                tempAmountArray[1] += qtyArray[i];
            }else if (sizeArray[i].equals("M")) {
                tempQtyArray[2] += qtyArray[i];
                tempAmountArray[2] += qtyArray[i];
            }else if (sizeArray[i].equals("L")) {
                tempQtyArray[3] += qtyArray[i];
                tempAmountArray[3] += qtyArray[i];
            }else if (sizeArray[i].equals("XL")) {
                tempQtyArray[4] += qtyArray[i];
                tempAmountArray[4] += qtyArray[i];

            }else if (sizeArray[i].equals("XXL")) {
                tempQtyArray[5] += qtyArray[i];
                tempAmountArray[5] += qtyArray[i];

            }
        }

        int[] tempQtySortingArray = new int[6];
        double[] tempAmountSortingArray = new double[6];
        String[] tempSizeSortingArray = new String[6];

        for (int i = 0; i < orderIdArray.length-1; i++) {
            for (int j = 0; j < orderIdArray.length-1; j++) {
                if (tempAmountSortingArray[j] < tempAmountSortingArray[j+1]) {
                    double tempAmount = tempAmountSortingArray[j];
                    tempAmountSortingArray[j] = tempAmountSortingArray[j+1];
                    tempAmountSortingArray[j+1] = tempAmount;

                    String tempSize = tempSizeSortingArray[j];
                    tempSizeSortingArray[j] = tempSizeSortingArray[j+1];
                    tempSizeSortingArray[j+1] = tempSize;

                    int tempQty = tempQtySortingArray[j];
                    tempQtySortingArray[j] = tempQtySortingArray[j+1];
                    tempQtySortingArray[j+1] = tempQty;


                }

            }

        }

        System.out.println("+---------------+----------------+-----------------+");
        System.out.println("|      Size     |      QTY       |  Total Amount   |");
        System.out.println("+---------------+----------------+-----------------+");

        for (int i = 0; i < tempQtyArray.length; i++) {
            System.out.printf("| %-13s | %-14s | %15.2f |\n", tempSizeSortingArray[i], tempQtySortingArray[i],
                    tempAmountSortingArray[i]);
        }
        System.out.println("+---------------+----------------+-----------------+");
    }

    public static void ordersReport(){
        System.out.println("+++ Orders Report +++ ");
        System.out.println("-".repeat(60));
        System.out.println("\t[1] All Orders ");
        System.out.println("\t[2] Orders By Amount ");
        System.out.println("Enter Option : ");
        int option = input.nextInt();
        clearConsole();
        switch (option) {
            case 1:
                allOrders();
                break;
                case 2:
                    ordersByAmount();
                    break;
                    default:
                        System.out.println("Invalid Option");
                        break;

        }
    }

    public static void allOrders(){
        do {
            System.out.println("+++ All Orders +++ ");
            System.out.println("-".repeat(60));
            ordersSortedByOrderId();
            System.out.println("To access the Main Menu , Please enter 0 :");
            int option = input.nextInt();
            clearConsole();
            if (option == 0) {
                homePage();
            }else{
                System.out.println("invalid option");
                continue;
            }

        }while(true);


    }

    public static void ordersSortedByOrderId(){
        String[] tempSizeArray = new String[sizeArray.length];
        int[] tempQtyArray = new int[qtyArray.length];
        double[] tempAmountArray = new double[amountArray.length];
        String[] tempOrderIdArray = new String[orderIdArray.length];
        String[] tempPhoneNumberArray = new String[phoneNumberArray.length];
        int[] tempOrderStatusArray = new int[orderStatusArray.length];

        for (int i = 0; i < orderIdArray.length; i++) {
            tempOrderIdArray[i] = orderIdArray[i];
            tempPhoneNumberArray[i] = phoneNumberArray[i];
            tempOrderStatusArray[i] = orderStatusArray[i];
            tempSizeArray[i] = sizeArray[i];
            tempQtyArray[i] = qtyArray[i];
            tempAmountArray[i] = amountArray[i];

        }

        for (int i = 0; i < orderIdArray.length-1; i++) {
            for (int j = 0; j < orderIdArray.length-1; j++) {
                if (tempOrderIdArray[j].compareTo(tempOrderIdArray[j + 1]) < 0){
                    String tempOrderId = tempOrderIdArray[j];
                    tempOrderIdArray[j] = tempOrderIdArray[j+1];
                    tempOrderIdArray[j+1] = tempOrderId;

                    String tempPhoneNumber = tempPhoneNumberArray[j];
                    tempPhoneNumberArray[j] = tempPhoneNumberArray[j+1];
                    tempPhoneNumberArray[j+1] = tempPhoneNumber;

                    int tempQty = tempQtyArray[j];
                    tempQtyArray[j] = tempQtyArray[j+1];
                    tempQtyArray[j+1] = tempQty;

                    String tempSize = tempSizeArray[j];
                    tempSizeArray[j] = tempSizeArray[j+1];
                    tempSizeArray[j+1] = tempSize;

                    double tempAmount = tempAmountArray[j];
                    tempAmountArray[j] = tempAmountArray[j+1];
                    tempAmountArray[j+1] = tempAmount;

                    int tempStatus = tempOrderStatusArray[j];
                    tempOrderStatusArray[j] = tempOrderStatusArray[j+1];
                    tempOrderStatusArray[j+1] = tempStatus;



                }
            }
        }

        System.out.println(
                "+----------------+------------------+-----------+-----------+-----------------+-----------------+");
        System.out.println(
                "|    Order ID    |    Customer Id   |    Size   |    QTY    |     Amount      |      Status     |");
        System.out.println(
                "+----------------+------------------+-----------+-----------+-----------------+-----------------+");

        for (int i = 0; i < tempOrderIdArray.length; i++) {
            System.out.printf("| %-14s | %-16s | %-9s | %-9d | %15.2f | %-15s |\n", tempOrderIdArray[i],
                    tempPhoneNumberArray[i], tempSizeArray[i], tempQtyArray[i], tempAmountArray[i],
                    (tempOrderStatusArray[i] == 0) ? "Processing" : (tempOrderStatusArray[i] == 1) ? "Delivering" : "Delivered");
        }
        System.out.println(
                "+----------------+------------------+-----------+-----------+-----------------+-----------------+");
    }

    public static void ordersByAmount(){
        do {
            System.out.println("+++ Orders By Amount +++ ");
            System.out.println("-".repeat(60));
            ordersSortedByAmount();
            System.out.println("To access the Main Menu , Please enter 0 :");
            int option = input.nextInt();
            clearConsole();
            if (option == 0) {
                homePage();

            }else{
                System.out.println("invalid option");
                continue;
            }

        }while(true);
    }

    public static void ordersSortedByAmount(){
        String[] tempSizeArray = new String[sizeArray.length];
        int[] tempQtyArray = new int[qtyArray.length];
        double[] tempAmountArray = new double[amountArray.length];
        String[] tempOrderIdArray = new String[orderIdArray.length];
        String[] tempPhoneNumberArray = new String[phoneNumberArray.length];
        int[] tempOrderStatusArray = new int[orderStatusArray.length];

        for (int i = 0; i < orderIdArray.length; i++) {
            tempOrderIdArray[i] = orderIdArray[i];
            tempPhoneNumberArray[i] = phoneNumberArray[i];
            tempOrderStatusArray[i] = orderStatusArray[i];
            tempSizeArray[i] = sizeArray[i];
            tempQtyArray[i] = qtyArray[i];
            tempAmountArray[i] = amountArray[i];

        }

        for (int i = 0; i < orderIdArray.length-1; i++) {
            for (int j = 0; j < orderIdArray.length-1; j++) {
                if (tempAmountArray[j] < tempAmountArray[j+1]) {
                    String tempOrderId = tempOrderIdArray[j];
                    tempOrderIdArray[j] = tempOrderIdArray[j+1];
                    tempOrderIdArray[j+1] = tempOrderId;

                    String tempPhoneNumber = tempPhoneNumberArray[j];
                    tempPhoneNumberArray[j] = tempPhoneNumberArray[j+1];
                    tempPhoneNumberArray[j+1] = tempPhoneNumber;

                    int tempQty = tempQtyArray[j];
                    tempQtyArray[j] = tempQtyArray[j+1];
                    tempQtyArray[j+1] = tempQty;

                    String tempSize = tempSizeArray[j];
                    tempSizeArray[j] = tempSizeArray[j+1];
                    tempSizeArray[j+1] = tempSize;

                    double tempAmount = tempAmountArray[j];
                    tempAmountArray[j] = tempAmountArray[j+1];
                    tempAmountArray[j+1] = tempAmount;

                    int tempStatus = tempOrderStatusArray[j];
                    tempOrderStatusArray[j] = tempOrderStatusArray[j+1];
                    tempOrderStatusArray[j+1] = tempStatus;



                }
            }
        }

        System.out.println(
                "+----------------+------------------+-----------+-----------+-----------------+-----------------+");
        System.out.println(
                "|    Order ID    |    Customer Id   |    Size   |    QTY    |     Amount      |      Status     |");
        System.out.println(
                "+----------------+------------------+-----------+-----------+-----------------+-----------------+");

        for (int i = 0; i < tempOrderIdArray.length; i++) {
            System.out.printf("| %-14s | %-16s | %-9s | %-9d | %15.2f | %-15s |\n", tempOrderIdArray[i],
                    tempPhoneNumberArray[i], tempSizeArray[i], tempQtyArray[i], tempAmountArray[i],
                    (tempOrderStatusArray[i] == 0) ? "Processing" : (tempOrderStatusArray[i] == 1) ? "Delivering" : "Delivered");
        }
        System.out.println(
                "+----------------+------------------+-----------+-----------+-----------------+-----------------+");
    }





    public static void setOrderStatus(){
        l1:do {
            System.out.println("\t\tOrder Status");
            System.out.println("-".repeat(60));
            System.out.println("\n\n");
            System.out.println("Enter Order ID : ");
            String orderId = input.next();
            int index = validateOrderId(orderId);
            if (index == -1) {
                System.out.println("Invalid Order ID");
            }else{
                printOrderDetails(index);
                System.out.println("Do you want to change this order Status (y/n) : ");
                char response = input.next().charAt(0);
                if (response == 'y' || response == 'Y') {
                    if (orderStatusArray[index] == 0) {
                        System.out.println("[1] Order Delivering ");
                        System.out.println("[2] Order Delivered ");
                        l2:do {
                            System.out.println("Enter an option : ");
                            int option = input.nextInt();
                            switch (option) {
                                case 1:
                                    orderStatusArray[index] = 1;
                                    break l2;
                                    case 2:
                                        orderStatusArray[index] = 2;
                                        break l2;
                                        default:
                                            System.out.println("Invalid option");
                            }

                        }while(true);
                    } else if (orderStatusArray[index] == 1) {
                        System.out.println("[1] Order Delivered ");
                        l3:do {
                            System.out.println("Enter an option : ");
                            int option = input.nextInt();
                            switch (option) {
                                case 1:
                                    orderStatusArray[index] = 2;
                                    break l3;
                                    default:
                                        System.out.println("Invalid option");

                            }


                        }while(true);

                    }
                    else {
                        System.out.println("can't change this order Status , Order already delivered . ");
                    }
                }

                System.out.print("Do you want to change another order Status (y/n) : ");
                response = input.next().charAt(0);
                if (response == 'y' || response == 'Y') {
                    continue l1;
                }else if (response == 'n' || response == 'N') {
                    clearConsole();
                    homePage();
                }else {
                    System.out.println("invalid option");
                }

            }



        }while(true);

    }

    public static void removeOrder(int index){
        String[] tempOrderIdArray = new String[orderIdArray.length-1];
        String[] tempPhoneNumberArray = new String[phoneNumberArray.length-1];
        String[] tempSizeArray = new String[sizeArray.length-1];
        int[] tempQtyArray = new int[qtyArray.length-1];
        double[] tempAmountArray = new double[amountArray.length-1];
        int[] tempOrderStatusArray = new int[orderStatusArray.length-1];

        for (int i = index ; i < orderIdArray.length-1 ; i++) {

            orderIdArray[i] = orderIdArray[i+1];
            phoneNumberArray[i] = phoneNumberArray[i+1];
            sizeArray[i] = sizeArray[i+1];
            qtyArray[i] = qtyArray[i+1];
            amountArray[i] = amountArray[i+1];
            orderStatusArray[i] = orderStatusArray[i+1];


        }

        for (int i = 0; i < tempOrderIdArray.length; i++) {
            tempOrderIdArray[i] = orderIdArray[i];
            tempPhoneNumberArray[i] = phoneNumberArray[i];
            tempSizeArray[i] = sizeArray[i];
            tempQtyArray[i] = qtyArray[i];
            tempAmountArray[i] = amountArray[i];
            tempOrderStatusArray[i] = orderStatusArray[i];

        }

        orderIdArray = tempOrderIdArray;
        phoneNumberArray = tempPhoneNumberArray;
        sizeArray = tempSizeArray;
        qtyArray = tempQtyArray;
        amountArray = tempAmountArray;
        orderStatusArray = tempOrderStatusArray;


    }

    public static void deleteOrder(){
        l1:do {
            System.out.println("\t\tDelete Order");
            System.out.println("-".repeat(60));
            System.out.print("Enter Order ID : ");
            String orderId = input.next();
            int index = validateOrderId(orderId);
            if (index == -1) {
                System.out.println("Invalid Order ID");

            }else{
                printOrderDetails(index);
                System.out.print("Do you want to delete this Order ? (y/n) : ");
                char response = input.next().charAt(0);
                if (response == 'y' || response == 'Y') {
                    removeOrder(index);
                    System.out.println("Order Deleted");
                }
            }
            do {


                System.out.print("Do you want to Delete Another Order ? (y/n) : ");
                char response = input.next().charAt(0);
                if (response == 'y' || response == 'Y') {
                    continue l1;
                } else if (response == 'n' || response == 'N') {
                    clearConsole();
                    homePage();
                }
                System.out.println("Enter Y or N ");
            }while (true);

        }while(true);


    }







    public static void homePage(){
        System.out.println("-".repeat(75));
        System.out.println("\t\tFashion Shop");
        System.out.println("-".repeat(75));
        System.out.println("[1] Place Order              [2] Search Customer");
        System.out.println("[3] Search Order             [4] View Reports  ");
        System.out.println("[5] Set Order Status         [6] Delete Order\n\n");
        System.out.println("Input Option : ");
        int option=input.nextInt();
        switch(option){
            case 1:
                placeOrder();
                break;
                case 2:
                    searchCustomer();
                    break;
                    case 3:
                        searchOrder();
                        break;
                        case 4:
                            viewReports();
                            break;
                            case 5:
                                setOrderStatus();
                                break;
                                case 6:
                                    deleteOrder();
                                    break;
                                    default:
                                        System.out.println("Invalid Option");

        }







    }


    public static void main(String[] args) {
        homePage();
    }






}
