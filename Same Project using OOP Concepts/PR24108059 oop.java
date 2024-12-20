import java.util.*;

class Orders{
    private  String orderId;
    private  String phoneNumber;
    private  String size;
    private  int qty;
    private  double amount;
    private int status;

    Orders(String orderId,String phoneNumber,String size,int qty,double amount,int status){
        this.orderId=orderId;
        this.phoneNumber=phoneNumber;
        this.size=size;
        this.qty=qty;
        this.amount=amount;
        this.status=status;


    }

    public void setOrderId(String orderId){
        this.orderId=orderId;

    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public void setSize(String size){
        this.size=size;
    }
    public void setQty(int qty){
        this.qty=qty;
    }
    public void setAmount(double amount){
        this.amount=amount;
    }
    public void setStatus(int status){
        this.status=status;
    }
    public String getOrderId(){
        return orderId;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getSize(){
        return size;
    }
    public int getQty(){
        return qty;
    }
    public double getAmount(){
        return amount;
    }
    public int getStatus(){
        return status;
    }


}

class Example {
    // ----------- CREATE ARRAYS ------------

    public static Orders[] ordersArray = new Orders[0];
    /*public static String[] orderIdArray = new String[0];
    public static String[] phoneNumberArray = new String[0];
    public static String[] sizeArray = new String[0];
    public static int[] qtyArray = new int[0];
    public static double[] amountArray = new double[0];
    public static int[] statusArray = new int[0];
*/
    // ----------- CONSTANTS ------------------
    public static final double XS = 600;
    public static final double S = 800;
    public static final double M = 900;
    public static final double L = 1000;
    public static final double XL = 1100;
    public static final double XXL = 1200;

    public static final int PROCESSING = 0;
    public static final int DELIVERING = 1;
    public static final int DELIVERED = 2;

    public static Scanner input = new Scanner(System.in);

    // -------------- MAIN METHOD -----------------
    public static void main(String[] args) {
        homePage();
    }

    // -------------- CLEAR CONSOLE -----------------
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c",
                        "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            // Handle any exceptions.
        }
    }

    // -------------- GENERATE ORDER ID -----------------
    public static String generateId() {
        int id1;
        if (ordersArray.length > 0) {
            id1 = Integer.parseInt(ordersArray[ordersArray.length - 1].getOrderId().split("[#]")[1]);
            id1++;
        } else {
            return "ODR#00001";
        }
        return String.format("ODR#%05d", id1);
    }

    // --------------- HOME PAGE ---------------
    public static void homePage() {
        System.out.println("================== FASHION SHOP ========================");
        System.out.println("\n[01] Place Order");
        System.out.println("\n[02] Search Customer");
        System.out.println("\n[03] Search Order");
        System.out.println("\n[04] View Reports");
        System.out.println("\n[05] Set Order Status");
        System.out.println("\n[06] Delete Order");
        System.out.print("\nEnter option : ");
        int option = input.nextInt();
        clearConsole();

        switch (option) {
            case 1:
                placeOrder();
                break;
            case 2:
                //searchCustomer();
                break;
            case 3:
                //searchOrder();
                break;
            case 4:
                //viewReports();
                break;
            case 5:
                //setOrderStatus();
                break;
            case 6:
                //deleteOrder();
                break;
            default:
                System.out.println("Invalid option...");
                homePage();
        }
    }

    // --------------- VALIDATE PHONE NUMBER ---------------
    public static boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 10 && phoneNumber.charAt(0) == '0') {
            return true;
        }
        return false;
    }

    // --------------- VALIDATE QTY ---------------
    public static boolean validateQty(int qty) {
        if (qty > 0) {
            return true;
        }
        return false;
    }

    // --------------- EXTEND ARRAYS ---------------
    public static void extendArrays() {

        Orders[] tempOrdersArray = new Orders[ordersArray.length + 1];

        /*String[] tempOrderIdArray = new String[orderIdArray.length + 1];
        String[] tempPhoneNumberArray = new String[orderIdArray.length + 1];
        String[] tempSizeArray = new String[orderIdArray.length + 1];
        int[] tempQtyArray = new int[orderIdArray.length + 1];
        double[] tempAmountArray = new double[orderIdArray.length + 1];
        int[] tempStatusArray = new int[orderIdArray.length + 1];
*/
        for (int i = 0; i < ordersArray.length; i++) {
            tempOrdersArray[i] = ordersArray[i];
            /*tempOrderIdArray[i] = orderIdArray[i];
            tempPhoneNumberArray[i] = phoneNumberArray[i];
            tempSizeArray[i] = sizeArray[i];
            tempQtyArray[i] = qtyArray[i];
            tempAmountArray[i] = amountArray[i];
            tempStatusArray[i] = statusArray[i];
        */
        }

        ordersArray = tempOrdersArray;
        /*


        orderIdArray = tempOrderIdArray;
        phoneNumberArray = tempPhoneNumberArray;
        sizeArray = tempSizeArray;
        qtyArray = tempQtyArray;
        amountArray = tempAmountArray;
        statusArray = tempStatusArray;
  */
    }

    // --------------- PLACE ORDER -----------------
    public static void placeOrder() {
        L1: do {
            System.out.println("================ PLACE ORDER =================");
            String orderId = generateId();
            System.out.println("\nEnter order Id : " + orderId);
            String phoneNumber;

            L2: do {
                System.out.print("\nEnter Customer phone number : ");
                phoneNumber = input.next();
                boolean isValidPhoneNumber = validatePhoneNumber(phoneNumber);

                if (isValidPhoneNumber) {
                    break L2;
                }
                System.out.println("\n\tInvalid phone number...Try again");
                System.out.print("\nDo you want to enter phone number again : ");
                char ch = input.next().charAt(0);
                if (ch == 'y' || ch == 'y') {
                    // Move the cursor up five lines
                    System.out.print("\033[6A");
                    // Clear the lines
                    System.out.print("\033[0J");
                    continue L2;

                } else if (ch == 'n' || ch == 'N') {
                    clearConsole();
                    homePage();
                }
            } while (true);

            System.out.print("\nEnter T-Shirt size : ");
            String size = input.next();

            int qty;

            L3: do {
                System.out.print("\nEnter QTY : ");
                qty = input.nextInt();

                boolean isValidQty = validateQty(qty);
                if (isValidQty) {
                    break L3;
                }
                System.out.println("\n\tInvalid qty...try again");
                System.out.print("\nDo you want to enter QTY again : ");
                char ch = input.next().charAt(0);
                if (ch == 'y' || ch == 'y') {
                    // Move the cursor up five lines
                    System.out.print("\033[6A");
                    // Clear the lines
                    System.out.print("\033[0J");
                    continue L3;

                } else if (ch == 'n' || ch == 'N') {
                    // clearConsole();
                    // homePage();
                }
            } while (true);

            double amount = 0;
            switch (size.toUpperCase()) {
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
            }

            System.out.println("\nAmount : " + amount);
            System.out.print("\nDo you want to place this order ? ");
            char ch = input.next().charAt(0);
            if (ch == 'Y' || ch == 'y') {
                extendArrays();
                Orders orders = new Orders(orderId,phoneNumber,size,qty,amount,PROCESSING);
                ordersArray[ordersArray.length - 1] = orders;
                /*
                orderIdArray[orderIdArray.length - 1] = orderId;
                phoneNumberArray[phoneNumberArray.length - 1] = phoneNumber;
                sizeArray[sizeArray.length - 1] = size;
                qtyArray[qtyArray.length - 1] = qty;
                amountArray[amountArray.length - 1] = amount;
                statusArray[statusArray.length - 1] = PROCESSING;
                */
                System.out.println("\n\tOrder Placed...");

            }
            System.out.print("\nDo you want to place another order ? ");
            char op = input.next().charAt(0);
            clearConsole();
            if (op == 'y' || op == 'Y') {
                continue L1;
            } else if (op == 'n' || op == 'N') {
                homePage();
            }

        } while (true);
    }

    // --------------- SEARCH ORDER BY ORDER ID ----------------
    public static int searchOrderByOrderId(String orderId) {
        for (int i = 0; i < ordersArray.length; i++) {
            if (ordersArray[i].getOrderId().equals(orderId)) {
                return i;
            }
        }
        return -1;
    }

    // --------------- SEARCH CUSTOMER BY PHONE NUMBER ----------------
    public static int searchCustomerByPhoneNumber(String phoneNumber) {
        for (int i = 0; i < ordersArray.length; i++) {
            if (ordersArray[i].getPhoneNumber().equals(phoneNumber)) {
                return i;
            }
        }
        return -1;
    }

    // --------------- GET CUSTOMER DETAILS -----------------
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

        for (int i = 0; i < ordersArray.length; i++) {
            if (ordersArray[i].getPhoneNumber().equals(phoneNumber)) {
                if (ordersArray[i].getSize().equals("XS")) {
                    tempQtyArray[0] += ordersArray[i].getQty();
                    tempAmountArray[0] += ordersArray[i].getAmount();
                } else if (ordersArray[i].getSize().equals("S")) {
                    tempQtyArray[1] += ordersArray[i].getQty();
                    tempAmountArray[1] += ordersArray[i].getAmount();
                } else if (ordersArray[i].getSize().equals("M")) {
                    tempQtyArray[2] += ordersArray[i].getQty();
                    tempAmountArray[2] += ordersArray[i].getAmount();
                } else if (ordersArray[i].getSize().equals("L")) {
                    tempQtyArray[3] += ordersArray[i].getQty();
                    tempAmountArray[3] += ordersArray[i].getAmount();
                } else if (ordersArray[i].getSize().equals("XL")) {
                    tempQtyArray[4] += ordersArray[i].getQty();
                    tempAmountArray[4] += ordersArray[i].getAmount();
                } else if (ordersArray[i].getSize().equals("XXL")) {
                    tempQtyArray[5] += ordersArray[i].getQty();
                    tempAmountArray[5] += ordersArray[i].getAmount();
                } else {
                    continue;
                }
            }
        }

        double totalAmount = 0;
        for (int i = 0; i < 6; i++) {
            totalAmount += tempAmountArray[i];
        }

        String[] tempSizeSortingArray = new String[6];
        int[] tempQtySortingArray = new int[6];
        double[] tempAmountSortingArray = new double[6];

        for (int i = 0; i < 6; i++) {
            tempSizeSortingArray[i] = tempSizeArray[i];
            tempQtySortingArray[i] = tempQtyArray[i];
            tempAmountSortingArray[i] = tempAmountArray[i];
        }

        for (int j = 1; j < tempAmountArray.length; j++) {
            for (int i = 0; i < tempAmountArray.length - j; i++) {
                if (tempAmountSortingArray[i] < tempAmountSortingArray[i + 1]) {

                    double tempAmount = tempAmountSortingArray[i];
                    tempAmountSortingArray[i] = tempAmountSortingArray[i + 1];
                    tempAmountSortingArray[i + 1] = tempAmount;

                    String tempSize = tempSizeSortingArray[i];
                    tempSizeSortingArray[i] = tempSizeSortingArray[i + 1];
                    tempSizeSortingArray[i + 1] = tempSize;

                    int tempQty = tempQtySortingArray[i];
                    tempQtySortingArray[i] = tempQtySortingArray[i + 1];
                    tempQtySortingArray[i + 1] = tempQty;
                }
            }
        }

        System.out.println("+---------------+---------------+-----------------+");
        System.out.println("|     Size      |      QTY      |      Amount     |");
        System.out.println("+---------------+---------------+-----------------+");

        for (int i = 0; i < tempAmountArray.length; i++) {
            System.out.printf("| %-13s | %-13s | %15.2f |\n", tempSizeSortingArray[i], tempQtySortingArray[i],
                    tempAmountSortingArray[i]);
        }

        System.out.println("+---------------+---------------+-----------------+");
        System.out.printf("| %-30s| %15.2f |\n", "Total Amount", totalAmount);
        System.out.println("+-------------------------------------------------+");

    }

    // ---------------- SEARCH CUSTOMER -----------------
    public static void searchCustomer() {
        do {
            System.out.println("==================== SEARCH CUSTOMER ====================");
            System.out.print("\nEnter customer phone number : ");
            String phoneNumber = input.next();

            int index = searchCustomerByPhoneNumber(phoneNumber);

            if (index == -1) {
                System.out.println("\n\tInvalid phone number...");
            } else {
                getCustomerDetails(phoneNumber);
            }
            System.out.print("\nDo you want to search another customer report ? ");
            char ch = input.next().charAt(0);
            clearConsole();
            if (ch == 'y' || ch == 'Y') {
                continue;
            } else if (ch == 'n' || ch == 'N') {
                homePage();
            }
        } while (true);

    }

    // ------------------ PRINT ORDER DETAILS --------------------
    public static void printOrderDetails(int index) {
        System.out.println("\nPhone Number : " + ordersArray[index].getPhoneNumber());
        System.out.println("Size         : " + ordersArray[index].getSize());
        System.out.println("QTY          : " + ordersArray[index].getQty());
        System.out.println("Amount       : " + ordersArray[index].getAmount());
        if (ordersArray[index].getStatus() == 0) {
            System.out.println("Status       : Processing");
        } else if (ordersArray[index].getStatus() == 1) {
            System.out.println("Status       : Delivering");
        } else {
            System.out.println("Status       : Delivered");
        }
    }

    // ------------------ SEARCH ORDER -----------------
    public static void searchOrder() {
        do {
            System.out.println("================ SEARCH ORDER ================");
            System.out.print("\nEnter order ID : ");
            String orderId = input.next();

            int index = searchOrderByOrderId(orderId);

            if (index == -1) {
                System.out.println("\n\tInvalid order ID...");
            } else {
                printOrderDetails(index);
            }
            System.out.print("\nDo you want to search another order ? ");
            char ch = input.next().charAt(0);
            clearConsole();
            if (ch == 'y' || ch == 'Y') {
                continue;
            } else if (ch == 'n' || ch == 'N') {
                homePage();
            }
        } while (true);
    }

    // ------------------ VIEW REPORTS -----------------
    public static void viewReports() {
        System.out.println("=============== REPORTS ================");
        System.out.println("\n[01] Customer Report");
        System.out.println("\n[02] Item Report");
        System.out.println("\n[03] Order Report");
        System.out.print("\nEnter Option : ");
        int option = input.nextInt();
        clearConsole();
        switch (option) {
            case 1:
                customerReport();
                break;
            case 2:
                itemReport();
                break;
            case 3:
                orderReport();
                break;
            default:
                System.out.println("\nInvalid Option...");
        }

    }

    // ------------------- CUSTOMER REPORTS ------------------
    public static void customerReport() {
        System.out.println("=============== CUSTOMER REPORTS ================");
        System.out.println("\n[01] Best in Customers");
        System.out.println("\n[02] View Customers");
        System.out.println("\n[03] All Customer Report");
        System.out.print("\nEnter Option : ");
        int option = input.nextInt();
        clearConsole();
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
                System.out.println("\nInvalid Option...");
        }
    }

    // ------------------- BEST IN CUSTOMER ------------------
    public static void bestInCustomer() {
        do {
            System.out.println("================== BEST IN CUSTOMERS =================");
            bestInCustomersByAmount();

            System.out.print("\nTo access the Main Menu, please enter 0 : ");
            int choice = input.nextInt();
            clearConsole();
            if (choice == 0) {
                homePage();
            } else {
                System.out.println("\nInvalid Option...");
                continue;
            }
        } while (true);
    }

    //----------------- BEST IN CUSTOMERS BY AMOUNT --------------------
    public static void bestInCustomersByAmount() {
        String[] tempPhoneNumberArray = new String[0];
        int[] tempQtyArray = new int[0];
        double[] tempAmountArray = new double[0];

        L1: for (int i = 0; i < ordersArray.length; i++) {
            for (int j = 0; j < tempPhoneNumberArray.length; j++) {
                if (ordersArray[i].getPhoneNumber().equals(tempPhoneNumberArray[j])) {
                    tempQtyArray[j] += ordersArray[i].getQty();
                    tempAmountArray[j] += ordersArray[i].getAmount();
                    continue L1;
                }
            }
            String[] tempPhoneNumberArray1 = new String[tempPhoneNumberArray.length + 1];
            int[] tempQtyArray1 = new int[tempQtyArray.length + 1];
            double[] tempAmountArray1 = new double[tempAmountArray.length + 1];

            for (int k = 0; k < tempPhoneNumberArray.length; k++) {
                tempPhoneNumberArray1[k] = tempPhoneNumberArray[k];
                tempQtyArray1[k] = tempQtyArray[k];
                tempAmountArray1[k] = tempAmountArray[k];
            }
            tempPhoneNumberArray = tempPhoneNumberArray1;
            tempQtyArray = tempQtyArray1;
            tempAmountArray = tempAmountArray1;

            tempPhoneNumberArray[tempPhoneNumberArray.length - 1] = ordersArray[i].getPhoneNumber();
            tempQtyArray[tempQtyArray.length - 1] = ordersArray[i].getQty();
            tempAmountArray[tempAmountArray.length - 1] = ordersArray[i].getAmount();
        }

        for (int j = 1; j < tempPhoneNumberArray.length; j++) {
            for (int i = 0; i < tempPhoneNumberArray.length - j; i++) {
                if (tempAmountArray[i] < tempAmountArray[i + 1]) {
                    double tempAmount = tempAmountArray[i];
                    tempAmountArray[i] = tempAmountArray[i + 1];
                    tempAmountArray[i + 1] = tempAmount;

                    String tempPhoneNumber = tempPhoneNumberArray[i];
                    tempPhoneNumberArray[i] = tempPhoneNumberArray[i + 1];
                    tempPhoneNumberArray[i + 1] = tempPhoneNumber;

                    int tempQty = tempQtyArray[i];
                    tempQtyArray[i] = tempQtyArray[i + 1];
                    tempQtyArray[i + 1] = tempQty;
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

    // ------------------- VIEW CUSTOMERS ------------------
    public static void viewCustomers() {
        do {
            System.out.println("================== VIEW CUSTOMERS =================");
            viewCustomer();

            System.out.print("\nTo access the Main Menu, please enter 0 : ");
            int choice = input.nextInt();
            clearConsole();
            if (choice == 0) {
                homePage();
            } else {
                System.out.println("\nInvalid Option...");
                continue;
            }
        } while (true);
    }

    public static void viewCustomer() {
        String[] tempPhoneNumberArray = new String[0];
        int[] tempQtyArray = new int[0];
        double[] tempAmountArray = new double[0];

        L1: for (int i = 0; i < ordersArray.length; i++) {
            for (int j = 0; j < tempPhoneNumberArray.length; j++) {
                if (ordersArray[i].getPhoneNumber().equals(tempPhoneNumberArray[j])) {
                    tempQtyArray[j] += ordersArray[i].getQty();
                    tempAmountArray[j] += ordersArray[i].getAmount();
                    continue L1;
                }
            }
            String[] tempPhoneNumberArray1 = new String[tempPhoneNumberArray.length + 1];
            int[] tempQtyArray1 = new int[tempQtyArray.length + 1];
            double[] tempAmountArray1 = new double[tempAmountArray.length + 1];

            for (int k = 0; k < tempPhoneNumberArray.length; k++) {
                tempPhoneNumberArray1[k] = tempPhoneNumberArray[k];
                tempQtyArray1[k] = tempQtyArray[k];
                tempAmountArray1[k] = tempAmountArray[k];
            }
            tempPhoneNumberArray = tempPhoneNumberArray1;
            tempQtyArray = tempQtyArray1;
            tempAmountArray = tempAmountArray1;

            tempPhoneNumberArray[tempPhoneNumberArray.length - 1] = ordersArray[i].getPhoneNumber();
            tempQtyArray[tempQtyArray.length - 1] = ordersArray[i].getQty();
            tempAmountArray[tempAmountArray.length - 1] = ordersArray[i].getAmount();
        }

        System.out.println("+---------------+----------------+-----------------+");
        System.out.println("|  Customer ID  |      QTY       |   Total Amount  |");
        System.out.println("+---------------+----------------+-----------------+");

        for (int i = 0; i < tempPhoneNumberArray.length; i++) {
            System.out.printf("| %-13s | %-14s | %15.2f |\n", tempPhoneNumberArray[i], tempQtyArray[i],
                    tempAmountArray[i]);
        }
        System.out.println("+---------------+----------------+-----------------+");
    }

    // ------------------- ALL CUSTOMER REPORTS ------------------
    public static void allCustomerReports() {
        do {
            System.out.println("================== All CUSTOMER REPORTS =================");
            viewAllCustomerReports();

            System.out.print("\nTo access the Main Menu, please enter 0 : ");
            int choice = input.nextInt();
            clearConsole();
            if (choice == 0) {
                homePage();
            } else {
                System.out.println("\nInvalid Option...");
                continue;
            }
        } while (true);
    }

    public static void viewAllCustomerReports() {
        String[] tempPhoneNumberArray = new String[0];
        if (ordersArray.length > 0) {
            L1: for (int i = 0; i < ordersArray.length; i++) {
                for (int j = 0; j < tempPhoneNumberArray.length; j++) {
                    if (ordersArray[i].getPhoneNumber().equals(tempPhoneNumberArray[j])) {
                        continue L1;
                    }
                }
                String[] tempPhoneNumberArray1 = new String[tempPhoneNumberArray.length + 1];
                for (int k = 0; k < tempPhoneNumberArray.length; k++) {
                    tempPhoneNumberArray1[k] = tempPhoneNumberArray[k];
                }
                tempPhoneNumberArray = tempPhoneNumberArray1;
                tempPhoneNumberArray[tempPhoneNumberArray.length - 1] = ordersArray[i].getPhoneNumber();
            }
        }

        System.out.println("+-----------------+---------+---------+---------+---------+---------+---------+-----------------+");
        System.out.printf("| %-15s | %-7s | %-7s | %-7s | %-7s | %-7s | %-7s | %-15s |\n", "Phone Number","XS", "S", "M",
                "L", "XL", "XXL", "Total Amount");
        System.out.println("+-----------------+---------+---------+---------+---------+---------+---------+-----------------+");

        for (int i = 0; i < tempPhoneNumberArray.length; i++) {
            String[] tempSizeArray = { "XS", "S", "M", "L", "XL", "XXL" };
            int[] tempQtyArray = new int[6];

            for (int j = 0; j < ordersArray.length; j++) {
                if (tempPhoneNumberArray[i].equals(ordersArray[j].getPhoneNumber())) {
                    switch (ordersArray[j].getSize()) {
                        case "XS":
                            tempQtyArray[0] += ordersArray[j].getQty();
                            break;
                        case "S":
                            tempQtyArray[1] += ordersArray[j].getQty();
                            break;
                        case "M":
                            tempQtyArray[2] += ordersArray[j].getQty();
                            break;
                        case "L":
                            tempQtyArray[3] += ordersArray[j].getQty();
                            break;
                        case "XL":
                            tempQtyArray[4] += ordersArray[j].getQty();
                            break;
                        case "XXL":
                            tempQtyArray[5] += ordersArray[j].getQty();
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

    // ------------------- ITEM REPORTS ------------------
    public static void itemReport() {
        System.out.println("=============== ITEM REPORTS ================");
        System.out.println("\n[01] Best selling Categories Sorted by QTY");
        System.out.println("\n[02] Best selling Categories Sorted by Amount");
        System.out.print("\nEnter Option : ");
        int option = input.nextInt();
        clearConsole();
        switch (option) {
            case 1:
                bestSellingCategoriesSortedByQty();
                break;
            case 2:
                bestSellingCategoriesSortedByAmount();
                break;
            default:
                System.out.println("\nInvalid Option...");
        }
    }

    // ------------------- BEST SELLING CATEGORIES SORTED BY qty -----------------------
    public static void bestSellingCategoriesSortedByQty() {
        do {
            System.out.println("================ SORTED BY QTY =================");
            sortedByQty();

            System.out.print("\nTo access the Main Menu, please enter 0 : ");
            int choice = input.nextInt();
            clearConsole();
            if (choice == 0) {
                homePage();
            } else {
                System.out.println("\nInvalid Option...");
                continue;
            }
        } while (true);
    }

    public static void sortedByQty() {
        String[] tempSizeArray = new String[6];
        int[] tempQtyArray = new int[6];
        double[] tempAmountArray = new double[6];

        tempSizeArray[0] = "XS";
        tempSizeArray[1] = "S";
        tempSizeArray[2] = "M";
        tempSizeArray[3] = "L";
        tempSizeArray[4] = "XL";
        tempSizeArray[5] = "XXL";

        for (int i = 0; i < ordersArray.length; i++) {
            if (ordersArray[i].getSize().equals("XS")) {
                tempQtyArray[0] += ordersArray[i].getQty();
                tempAmountArray[0] += ordersArray[i].getAmount();
            } else if (ordersArray[i].getSize().equals("S")) {
                tempQtyArray[1] += ordersArray[i].getQty();
                tempAmountArray[1] += ordersArray[i].getAmount();
            } else if (ordersArray[i].getSize().equals("M")) {
                tempQtyArray[2] += ordersArray[i].getQty();
                tempAmountArray[2] += ordersArray[i].getAmount();
            } else if (ordersArray[i].getSize().equals("L")) {
                tempQtyArray[3] += ordersArray[i].getQty();
                tempAmountArray[3] += ordersArray[i].getAmount();
            } else if (ordersArray[i].getSize().equals("XL")) {
                tempQtyArray[4] += ordersArray[i].getQty();
                tempAmountArray[4] += ordersArray[i].getAmount();
            } else if (ordersArray[i].getSize().equals("XXL")) {
                tempQtyArray[5] += ordersArray[i].getQty();
                tempAmountArray[5] += ordersArray[i].getAmount();
            } else {
                continue;
            }
        }

        int[] tempQtySortingArray = new int[6];
        double[] tempAmountSortingArray = new double[6];
        String[] tempSizeSortingArray = new String[6];

        for (int i = 0; i < 6; i++) {
            tempQtySortingArray[i] = tempQtyArray[i];
            tempAmountSortingArray[i] = tempAmountArray[i];
            tempSizeSortingArray[i] = tempSizeArray[i];
        }

        for (int j = 1; j < tempQtyArray.length; j++) {
            for (int i = 0; i < tempQtyArray.length - j; i++) {
                if (tempQtySortingArray[i] < tempQtySortingArray[i + 1]) {

                    int tempQty = tempQtySortingArray[i];
                    tempQtySortingArray[i] = tempQtySortingArray[i + 1];
                    tempQtySortingArray[i + 1] = tempQty;

                    double tempAmount = tempAmountSortingArray[i];
                    tempAmountSortingArray[i] = tempAmountSortingArray[i + 1];
                    tempAmountSortingArray[i + 1] = tempAmount;

                    String tempSize = tempSizeSortingArray[i];
                    tempSizeSortingArray[i] = tempSizeSortingArray[i + 1];
                    tempSizeSortingArray[i + 1] = tempSize;
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

    // ------------------- BEST SELLING CATEGORIES SORTED BY AMOUNT ---------------------
    public static void bestSellingCategoriesSortedByAmount() {
        do {
            System.out.println("================ SORTED BY AMOUNT =================");
            sortedByAmount();

            System.out.print("\nTo access the Main Menu, please enter 0 : ");
            int choice = input.nextInt();
            clearConsole();
            if (choice == 0) {
                homePage();
            } else {
                System.out.println("\nInvalid Option...");
                continue;
            }
        } while (true);

    }

    public static void sortedByAmount() {
        String[] tempSizeArray = new String[6];
        int[] tempQtyArray = new int[6];
        double[] tempAmountArray = new double[6];

        tempSizeArray[0] = "XS";
        tempSizeArray[1] = "S";
        tempSizeArray[2] = "M";
        tempSizeArray[3] = "L";
        tempSizeArray[4] = "XL";
        tempSizeArray[5] = "XXL";

        for (int i = 0; i < ordersArray.length; i++) {
            if (ordersArray[i].getSize().equals("XS")) {
                tempQtyArray[0] += ordersArray[i].getQty();
                tempAmountArray[0] += ordersArray[i].getAmount();
            } else if (ordersArray[i].getSize().equals("S")) {
                tempQtyArray[1] += ordersArray[i].getQty();
                tempAmountArray[1] += ordersArray[i].getAmount();
            } else if (ordersArray[i].getSize().equals("M")) {
                tempQtyArray[2] += ordersArray[i].getQty();
                tempAmountArray[2] += ordersArray[i].getAmount();
            } else if (ordersArray[i].getSize().equals("L")) {
                tempQtyArray[3] += ordersArray[i].getQty();
                tempAmountArray[3] += ordersArray[i].getAmount();
            } else if (ordersArray[i].getSize().equals("XL")) {
                tempQtyArray[4] += ordersArray[i].getQty();
                tempAmountArray[4] += ordersArray[i].getAmount();
            } else if (ordersArray[i].getSize().equals("XXL")) {
                tempQtyArray[5] += ordersArray[i].getQty();
                tempAmountArray[5] += ordersArray[i].getAmount();
            } else {
                continue;
            }
        }

        int[] tempQtySortingArray = new int[6];
        double[] tempAmountSortingArray = new double[6];
        String[] tempSizeSortingArray = new String[6];

        for (int i = 0; i < 6; i++) {
            tempQtySortingArray[i] = tempQtyArray[i];
            tempAmountSortingArray[i] = tempAmountArray[i];
            tempSizeSortingArray[i] = tempSizeArray[i];
        }

        for (int j = 1; j < tempQtyArray.length; j++) {
            for (int i = 0; i < tempQtyArray.length - j; i++) {
                if (tempAmountSortingArray[i] < tempAmountSortingArray[i + 1]) {

                    int tempQty = tempQtySortingArray[i];
                    tempQtySortingArray[i] = tempQtySortingArray[i + 1];
                    tempQtySortingArray[i + 1] = tempQty;

                    double tempAmount = tempAmountSortingArray[i];
                    tempAmountSortingArray[i] = tempAmountSortingArray[i + 1];
                    tempAmountSortingArray[i + 1] = tempAmount;

                    String tempSize = tempSizeSortingArray[i];
                    tempSizeSortingArray[i] = tempSizeSortingArray[i + 1];
                    tempSizeSortingArray[i + 1] = tempSize;
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

    //------------------- ORDER REPORTS -------------------
    public static void orderReport() {
        System.out.println("=============== ORDER REPORTS ================");
        System.out.println("\n[01] All Orders");
        System.out.println("\n[02] Orders By Amount");
        System.out.print("\nEnter Option : ");
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
                System.out.println("\nInvalid Option...");
        }
    }

    //----------------------- ALL ORDERS -----------------------
    public static void allOrders() {
        do {
            System.out.println("============== VIEW ORDERS =================");
            ordersSortByOrderId();

            System.out.print("\nTo access the Main Menu, please enter 0 : ");
            int choice = input.nextInt();
            clearConsole();
            if (choice == 0) {
                homePage();
            } else {
                System.out.println("\nInvalid Option...");
                continue;
            }
        } while (true);
    }

    public static void ordersSortByOrderId() {
        String[] tempOrderIdArray = new String[ordersArray.length];
        String[] tempPhoneNumberArray = new String[ordersArray.length];
        String[] tempSizeArray = new String[ordersArray.length];
        int[] tempQtyArray = new int[ordersArray.length];
        double[] tempAmountArray = new double[ordersArray.length];
        int[] tempStatusArray = new int[ordersArray.length];

        for (int i = 0; i < ordersArray.length; i++) {
            tempOrderIdArray[i] = ordersArray[i].getOrderId();
            tempPhoneNumberArray[i] = ordersArray[i].getPhoneNumber();
            tempSizeArray[i] = ordersArray[i].getSize();
            tempQtyArray[i] = ordersArray[i].getQty();
            tempAmountArray[i] = ordersArray[i].getAmount();
            tempStatusArray[i] = ordersArray[i].getStatus();
        }
        for (int j = 1; j < ordersArray.length; j++) {
            for (int i = 0; i < ordersArray.length - j; i++) {
                if (tempOrderIdArray[i].compareTo(tempOrderIdArray[i + 1]) < 0) {

                    String tempOrderId = tempOrderIdArray[i];
                    tempOrderIdArray[i] = tempOrderIdArray[i + 1];
                    tempOrderIdArray[i + 1] = tempOrderId;

                    double tempAmount = tempAmountArray[i];
                    tempAmountArray[i] = tempAmountArray[i + 1];
                    tempAmountArray[i + 1] = tempAmount;

                    String tempPhoneNumber = tempPhoneNumberArray[i];
                    tempPhoneNumberArray[i] = tempPhoneNumberArray[i + 1];
                    tempPhoneNumberArray[i + 1] = tempPhoneNumber;

                    String tempSize = tempSizeArray[i];
                    tempSizeArray[i] = tempSizeArray[i + 1];
                    tempSizeArray[i + 1] = tempSize;

                    int tempQty = tempQtyArray[i];
                    tempQtyArray[i] = tempQtyArray[i + 1];
                    tempQtyArray[i + 1] = tempQty;

                    int tempStatus = tempStatusArray[i];
                    tempStatusArray[i] = tempStatusArray[i + 1];
                    tempStatusArray[i + 1] = tempStatus;
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
                    (tempStatusArray[i] == 0) ? "Processing" : (tempStatusArray[i] == 1) ? "Delivering" : "Delivered");
        }
        System.out.println(
                "+----------------+------------------+-----------+-----------+-----------------+-----------------+");
    }

    public static void ordersByAmount() {
        do {
            System.out.println("=================== ORDERS BY AMOUNT =================");
            ordersSortByAmount();

            System.out.print("\nTo access the Main Menu, please enter 0 : ");
            int choice = input.nextInt();
            clearConsole();
            if (choice == 0) {
                homePage();
            } else {
                System.out.println("\nInvalid Option...");
                continue;
            }
        } while (true);
    }

    public static void ordersSortByAmount() {
        String[] tempOrderIdArray = new String[ordersArray.length];
        String[] tempPhoneNumberArray = new String[ordersArray.length];
        String[] tempSizeArray = new String[ordersArray.length];
        int[] tempQtyArray = new int[ordersArray.length];
        double[] tempAmountArray = new double[ordersArray.length];
        int[] tempStatusArray = new int[ordersArray.length];

        for (int i = 0; i < ordersArray.length; i++) {
            tempOrderIdArray[i] = ordersArray[i].getOrderId();
            tempPhoneNumberArray[i] = ordersArray[i].getPhoneNumber();
            tempSizeArray[i] = ordersArray[i].getSize();
            tempQtyArray[i] = ordersArray[i].getQty();
            tempAmountArray[i] = ordersArray[i].getAmount();
            tempStatusArray[i] = ordersArray[i].getStatus();
        }

        for (int j = 1; j < ordersArray.length; j++) {
            for (int i = 0; i < ordersArray.length - j; i++) {
                if (tempAmountArray[i] < tempAmountArray[i + 1]) {

                    double tempAmount = tempAmountArray[i];
                    tempAmountArray[i] = tempAmountArray[i + 1];
                    tempAmountArray[i + 1] = tempAmount;

                    String tempOrderId = tempOrderIdArray[i];
                    tempOrderIdArray[i] = tempOrderIdArray[i + 1];
                    tempOrderIdArray[i + 1] = tempOrderId;

                    String tempPhoneNumber = tempPhoneNumberArray[i];
                    tempPhoneNumberArray[i] = tempPhoneNumberArray[i + 1];
                    tempPhoneNumberArray[i + 1] = tempPhoneNumber;

                    String tempSize = tempSizeArray[i];
                    tempSizeArray[i] = tempSizeArray[i + 1];
                    tempSizeArray[i + 1] = tempSize;

                    int tempQty = tempQtyArray[i];
                    tempQtyArray[i] = tempQtyArray[i + 1];
                    tempQtyArray[i + 1] = tempQty;

                    int tempStatus = tempStatusArray[i];
                    tempStatusArray[i] = tempStatusArray[i + 1];
                    tempStatusArray[i + 1] = tempStatus;
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
                    (tempStatusArray[i] == 0) ? "Processing" : (tempStatusArray[i] == 1) ? "Delivering" : "Delivered");
        }
        System.out.println(
                "+----------------+------------------+-----------+-----------+-----------------+-----------------+");
    }

    // ------------------ CHANGE ORDER STATUS --------------------
    public static void setOrderStatus() {
        L1: do {
            System.out.println("================= ORDER STATUS =================");
            System.out.print("\nEnter order ID : ");
            String orderId = input.next();

            int index = searchOrderByOrderId(orderId);

            if (index == -1) {
                System.out.println("\n\tInvalid order ID...");
            } else {
                printOrderDetails(index);
                System.out.print("\nDo you want to change this order status ? ");
                char ch = input.next().charAt(0);
                if (ch == 'y' || ch == 'Y') {
                    if (ordersArray[index].getStatus() == 0) {
                        System.out.println("\n\t[01] Order Delivering");
                        System.out.println("\n\t[02] Order Delivered");
                        L2: do {
                            System.out.print("\nEnter Option : ");
                            int option = input.nextInt();

                            switch (option) {
                                case 1:
                                    ordersArray[index].setStatus(DELIVERING);
                                    System.out.println("\nStatus Updated !");
                                    break L2;
                                case 2:
                                    ordersArray[index].setStatus(DELIVERED);
                                    System.out.println("\nStatus Updated !");
                                    break L2;
                                default:
                                    System.out.println("\nInvalid Option...");
                                    continue L2;
                            }
                        } while (true);
                    } else if (ordersArray[index].getStatus() == 1) {
                        System.out.println("\n\t[01] Order Delivered");
                        L3: do {
                            System.out.print("\nEnter Option : ");
                            int option = input.nextInt();
                            switch (option) {
                                case 1:
                                    ordersArray[index].setStatus(DELIVERED);
                                    System.out.println("\nStatus Updated !");
                                    break L3;
                                default:
                                    System.out.println("\nInvalid Option...");
                                    continue L3;
                            }
                        } while (true);
                    } else {
                        System.out.println("\n\tCan't change this Order status, Order already Delivered...!");
                    }
                }

            }
            System.out.print("\nDo you want to change another Order status ? ");
            char ch = input.next().charAt(0);
            clearConsole();
            if (ch == 'y' || ch == 'Y') {
                continue L1;
            } else if (ch == 'n' || ch == 'N') {
                homePage();
            }

        } while (true);
    }

    // ----------------- REMOVE ORDER ------------------
    public static void removeOrder(int index) {
        Orders[] tempOrdersArray = new Orders[ordersArray.length - 1];
        /* String[] tempOrderIdArray = new String[orderIdArray.length - 1];
        String[] tempPhoneNumberArray = new String[orderIdArray.length - 1];
        String[] tempSizeArray = new String[orderIdArray.length - 1];
        int[] tempQtyArray = new int[orderIdArray.length - 1];
        double[] tempAmountArray = new double[orderIdArray.length - 1];
        int[] tempStatusArray = new int[orderIdArray.length - 1];*/

        for (int i = index; i < ordersArray.length - 1; i++) {
            ordersArray[i] = ordersArray[i + 1];
            /*orderIdArray[i] = orderIdArray[i + 1];
            phoneNumberArray[i] = phoneNumberArray[i + 1];
            sizeArray[i] = sizeArray[i + 1];
            qtyArray[i] = qtyArray[i + 1];
            amountArray[i] = amountArray[i + 1];
            statusArray[i] = statusArray[i + 1];*/
        }
        for (int i = 0; i < ordersArray.length - 1; i++) {
            tempOrdersArray[i] = ordersArray[i];
            /*tempOrderIdArray[i] = orderIdArray[i];
            tempPhoneNumberArray[i] = phoneNumberArray[i];
            tempSizeArray[i] = sizeArray[i];
            tempQtyArray[i] = qtyArray[i];
            tempAmountArray[i] = amountArray[i];
            tempStatusArray[i] = statusArray[i];*/
        }
        ordersArray = tempOrdersArray;
        /*orderIdArray = tempOrderIdArray;
        phoneNumberArray = tempPhoneNumberArray;
        sizeArray = tempSizeArray;
        qtyArray = tempQtyArray;
        amountArray = tempAmountArray;
        statusArray = tempStatusArray;*/

    }

    // ----------------- DELETE ORDER ---------------
    public static void deleteOrder() {
        do {
            System.out.println("==================== DELETE ORDER =================");
            System.out.print("\nEnter order ID : ");
            String orderId = input.next();

            int index = searchOrderByOrderId(orderId);

            if (index == -1) {
                System.out.println("\n\tInvalid order ID...");
            } else {
                printOrderDetails(index);
                System.out.print("\nDo you want to delete this order ? ");
                char ch = input.next().charAt(0);
                if (ch == 'y' || ch == 'Y') {
                    removeOrder(index);
                    System.out.println("\n\tOrder deleted...!");
                }
            }
            System.out.print("\nDo yo want to delete another order ? ");
            char ch = input.next().charAt(0);
            clearConsole();
            if (ch == 'Y' || ch == 'y') {
                continue;
            } else if (ch == 'n' || ch == 'N') {
                homePage();
            }
        } while (true);
    }
}
