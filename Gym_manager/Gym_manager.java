import java.util.*;
import static java.util.Calendar.*;

public class GymManager {
    final static int INITIAL_EMPLOYEES = 10, INITIAL_MEMBERS = 10;
    private Scanner sc;
    /**
      Initializing Employee and Member database.
     */
    private EmployeesDatabase employeesDatabase =
            new EmployeesDatabase(new Employee[INITIAL_EMPLOYEES]);
    private MembersDatabase membersDatabase =
            new MembersDatabase(new Standard[INITIAL_MEMBERS]);
    public class Date implements Comparable<Date> {
        private static final int DAYSOFMONTH_1 = 31, DAYSOFMONTH_2 = 30,
                LEAPYEARDAYSOFFEB = 29, NONLEAPYEARDAYSOFFEB = 28;
        public static final int QUADRENNIAL = 4, CENTENNIAL = 100,
                QUATERCENTENNIAL = 400;
        private int year;
        private int month;
        private int day;

        /**
         * Creates a Date object
         */
        public Date() {
            Calendar calendar = Calendar.getInstance();
            this.month = calendar.get(Calendar.MONTH);
            this.day = calendar.get(Calendar.DATE);
            this.year = calendar.get(Calendar.YEAR);
        }

        /**
         * Creates a Date object with provided params
         */
        public Date(String date) {
            StringTokenizer dateTokens = new StringTokenizer(date, "/");
            this.day = Integer.parseInt(dateTokens.nextToken());
            this.month = Integer.parseInt(dateTokens.nextToken()) - 1;
            this.year = Integer.parseInt(dateTokens.nextToken());
        }

        /**
         * Creates a Date object with provided params
         */
        public Date(int month, int day, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
        }

        /**
         * Compares two date objects
         */
        @Override
        public int compareTo(Date date) {
            Calendar calendar_1 = Calendar.getInstance();
            Calendar calendar_2 = Calendar.getInstance();
            calendar_1.set(this.year, this.month, this.day);
            if (date == null) {
                Date todayCalendar = new Date();
                calendar_2.set(todayCalendar.year, todayCalendar.month, todayCalendar.day);
            } else {
                calendar_2.set(date.year, date.month, date.day);
            }
            if (calendar_1.compareTo(calendar_2) > 0 || calendar_1.compareTo(calendar_2) == 0) return 1;
            return -1;
        }

        /**
         * Validate if the given date has valid date considering if it is leap
         * year or not.
         */
        public boolean isValid() { //TODO: Get rid of all magic numbers.
            if ((this.month == JANUARY || this.month == MARCH
                    || this.month == MAY || this.month == JULY
                    || this.month == AUGUST || this.month == OCTOBER
                    || this.month == DECEMBER) && (this.day <= DAYSOFMONTH_1))
                return true;
            if ((this.month == APRIL || this.month == JUNE
                    || this.month == SEPTEMBER || this.month == NOVEMBER)
                    && (this.day <= DAYSOFMONTH_2))
                return true;
            boolean leapYear = false;
            if (this.month == FEBRUARY) {
                if (this.year % QUADRENNIAL == 0) {
                    if (this.year % CENTENNIAL == 0) {
                        if (this.year % QUATERCENTENNIAL == 0) {
                            leapYear = true;
                        }
                    } else leapYear = true;
                }
                if (leapYear) {
                    return this.day <= LEAPYEARDAYSOFFEB;
                } else {
                    return this.day <= NONLEAPYEARDAYSOFFEB;
                }
            }
            return false;
        }
        /**
         * Prints the date in dd/mm/yyyy format
         */
        public String toString() {
            return this.day + "/" + (this.month + 1) + "/" + this.year;
        }
    }
    /**
      Person class is a parent class for the employee and Standard (Member)
      classes.
     */
    public class Person {
        private String fname;
        private String lname;
        private Date dob;
        /**
         * Creates a Person object
         */
        public Person(String fname, String lname, Date dob) {
            this.fname = fname;
            this.lname = lname;
            this.dob = dob;
        }
        /**
         * Provides a person's first name
         */
        public String getFname() {
            return this.fname;
        }
        /**
         * Provides a person's last name
         */
        public String getLname() {
            return this.lname;
        }
        /**
         * Provides a person's date of birth
         */
        public Date getDob() {
            return this.dob;
        }
        /**
         * Prints a Person's information.
         */
        @Override
        public String toString() {
            return "fname=" + fname + ", lname=" + lname + ", dob=" + dob;
        }
    }
    /**
     * Employee class inherits Person Class
     */
    public class Employee extends Person {
        private String employee_ID;
        /**
         * Creates a Person object with the given params
         */
        public Employee(String fname, String lname, Date dob, String employee_ID) {
            super(fname.toUpperCase(), lname.toUpperCase(), dob);
            this.employee_ID = employee_ID;
        }
        /**
         * Creates a Person object with the given params
         */
        public Employee(String fname, String lname, Date dob) {
            super(fname.toUpperCase(), lname.toUpperCase(), dob);
        }
        /**
         * Provides an employee's employee ID
         */
        public String getEmployee_ID() {
            return this.employee_ID;
        }
        /**
         * Sets an employee's employee ID
         */
        public void setEmployee_ID(String employee_ID) {
            this.employee_ID = employee_ID;
        }
        /**
         * Provides an employee object
         */
        public Employee getEmployee(){
            return this;
        }
        /**
         * Compares two employee objects
         */
        public int compareTo(Employee employee) {
            if ((this.getFname().compareTo(employee.getFname()) == 0)
                    && (this.getLname().compareTo(employee.getLname()) == 0)
                    && this.getDob().toString().equals(employee.getDob().toString())) {
                return 0;
            }
            return -1;
        }
        /**
         * Prints employee information
         */
        @Override
        public String toString() {
            return ">> Employee_ID: " + this.employee_ID + ", Employee_Name: "
                    + this.getFname() + " " + this.getLname() + "\n";
        }
    }
    /**
     * enum GymClasses defines the gym classes available
     */
    public enum GymClasses {

        YOGA("Maya", "9:30 A.M"),
        BICYCLE("Jagdish", "10:00 A.M"),
        CARDIO("Kyamat", "6:00 P.M");
        private final String instructor;
        private final String time;
        /**
         * Creates GymClasses object with given params
         */
        GymClasses (String instructor, String time) {
            this.instructor = instructor;
            this.time = time;
        }
        /**
         * Provides a class's time
         */
        public String getTime() {
            return time;
        }
        /**
         * Prints gym class information
         */
        @Override
        public String toString() {
            return this.name() + " - " + this.instructor + " " + this.time;
        }
    }
    /**
     * Standard (Member) class inherits Person Class
     */
    public class Standard extends Person {
        private Date doe;
        private int classes_SignedUp = 0;
        private int guest_Pass = 0;
        private GymClasses[] gymClasses = new GymClasses[guest_Pass + 1];
        private int size = 0;
        /**
         * Creates a Standard (member) object with the given params
         */
        public Standard(String fname, String lname, Date dob) {
            super(fname.toUpperCase(), lname.toUpperCase(), dob);
        }
        /**
         * Creates a Standard (member) object with the given params
         */
        public Standard(String fname, String lname, Date dob, Date doe) {
            super(fname.toUpperCase(), lname.toUpperCase(), dob);
            this.doe = doe;
        }
        /**
         * Provides a member's guest pass count
         */
        public int getGuestPass() {
            return guest_Pass;
        }
        /**
         * Sets a member's guest pass count
         */
        public void setGuestPass(int guest_Pass) {
            this.guest_Pass = guest_Pass;
        }
        /**
         * Provides a member's signed up classes count
         */
        public int getClasses_SignedUp() {
            return this.classes_SignedUp;
        }
        /**
         * Creates a gymClasses object array with give size
         */
        public void setGymClasses(int size) {
            gymClasses = new GymClasses[size + 1];
        }
        /**
         * Adds gym class to member object
         */
        public void addGymClass(GymClasses gymClass) {
            this.gymClasses[this.size] = gymClass;
            this.size++;
            this.classes_SignedUp++;
            System.out.println(">>>> Member added to " + gymClass.name() +
                    " Class <<<<\n\n");
        }
        /**
         * Drops gym class from member object
         */
        public void dropGymClass(GymClasses gymClass) {
            GymClasses[] tmp_gymClasses =
                    new GymClasses[this.gymClasses.length];
            boolean is_Class_Removed = false;
            for (int i = 0; i < this.size; i++) {
                if (this.gymClasses[i] == null) continue;
                if (this.gymClasses[i].compareTo(gymClass) == 0) {
                    tmp_gymClasses[i] = null;
                    this.size--;
                    this.classes_SignedUp--;
                    is_Class_Removed = true;
                } else
                    tmp_gymClasses[i] = this.gymClasses[i];
            }
            this.gymClasses = tmp_gymClasses;
            if (is_Class_Removed) System.out.println(">> Member_Name: "
                    + this.getFname() + " " + this.getLname() + ", date of birth: "
                    + this.getDob() + " dropped from " + gymClass.name() +" class!" +
                    " <<<<\n\n");
            else System.out.println(">>>> Member is not registered to the class" +
                    " entered! <<<<\n\n");
        }
        /**
         * Compares two member objects
         */
        public int compareTo(Standard member) {
            if ((this.getFname().compareTo(member.getFname()) == 0)
                    && (this.getLname().compareTo(member.getLname()) == 0)
                    && this.getDob().toString().equals(member.getDob().toString())) {
                return 0;
            }
            return -1;
        }
        /**
         * Prints member's information with the gym classes they have signed up
         */
        @Override
        public String toString() {
            String membership_Type = "STANDARD";
            if (this instanceof Family) membership_Type = "FAMILY";
            if (this instanceof Premium) membership_Type = "PREMIUM";

            String classes_String = "";
            if (classes_SignedUp > 0) {
                for (int i = 0; i < this.size; i++) {
                    //TODO: improve printing
                    classes_String += ">>>>> " +this.gymClasses[i].toString() +
                            " - " + (i+1) + "\n";
                }
            }
            return ">> Member_Name: " + this.getFname() + " " + this.getLname() + ", " +
                    "date of birth: " + this.getDob() + ", date of membership's " +
                    "expiration: " + this.doe + ", membership type: " +
                    membership_Type + ", guest Pass available: " + guest_Pass +
                    "\n " + classes_String;
        }
    }
    /**
     * Family (Member) class inherits Standard (Member) class
     */
    public class Family extends Standard {
        private int guest_Pass = 1;
        /**
         * Creates a Family (member) object with the given params
         */
        public Family(String fname, String lname, Date dob, Date doe) {
            super(fname.toUpperCase(), lname.toUpperCase(), dob, doe);
            this.setGuestPass(guest_Pass);
            this.setGymClasses(guest_Pass);
        }
    }
    /**
     * Premium (Member) class inherits Family (Member) class
     */
    public class Premium extends Family {
        private int guest_Pass = 3;
        /**
         * Creates a Premium (member) object with the given params
         */
        public Premium(String fname, String lname, Date dob, Date doe) {
            super(fname.toUpperCase(), lname.toUpperCase(), dob, doe);
            this.setGuestPass(guest_Pass);
            this.setGymClasses(guest_Pass);
        }
    }
    /**
     * EmployeesDatabase class creates array-list of the employees
     */
    public class EmployeesDatabase {
        final static int INDEX = 0;
        final static int INITIAL_CAPACITY = 4;
        private Employee[] employee_List;
        private int size;
        /**
         * Creates a EmployeesDatabase object with given params
         */
        public EmployeesDatabase(Employee[] elist) {
            this.employee_List = elist;
            this.size = INDEX;
        }
        /**
         * This method checks if the employee ID exists in the array
         */
        public boolean login(String employee_ID) {
            for (int i = 0; i < this.size; i++) {
                if (this.employee_List[i] == null) continue;
                if (this.employee_List[i].getEmployee_ID().compareTo(employee_ID) == 0)
                    return true;
            }
            return false;
        }
        /**
         * This method checks if the employee exists in the array
         */
        public boolean employeeExist(Employee employee) {
            for (int i = 0; i < this.size; i++) {
                if (this.employee_List[i] == null) continue;
                if (this.employee_List[i].compareTo(employee) == 0)
                    return true;
            }
            return false;
        }
        /**
         * This method grows the array
         */
        private void grow() {
            Employee[] tmp_employee_List =
                    new Employee[this.employee_List.length + INITIAL_CAPACITY];
            for (int i = 0; i < this.size; i++) {
                if (this.employee_List[i] == null) continue;
                tmp_employee_List[i] =  this.employee_List[i];
            }
            this.employee_List = tmp_employee_List;
        }
        /**
         * This method enerates a random employee ID.
         */
        private String generateEmployeeID() {
            Random random = new Random();
            int employee_ID = 1000 + random.nextInt(INITIAL_EMPLOYEES);
            char character = (char)(random.nextInt(26) + 'A');
            for (int i = 0; i < this.size; i++) {
                if (this.employee_List[i].getEmployee_ID()
                        .compareTo(String.valueOf(employee_ID)) == 0) {
                    generateEmployeeID();
                }
            }
            return character + String.valueOf(employee_ID);
        }
        /**
         * This method adds an employee to the array
         */
        public String addEmployee(Object employee) {
            if (this.size >= this.employee_List.length)
                grow();
            String employee_ID = ((Employee) employee).getEmployee_ID();
            if (employee_ID == null) {
                employee_ID = generateEmployeeID();
                ((Employee) employee).setEmployee_ID(employee_ID);
            }
            this.employee_List[this.size] = (Employee) employee;
            this.size++;
            return employee_ID;
        }
        /**
         * Prints the whole employee database array
         */
        public void print() {
            if (this.size == 0)
                System.out.println("\n>>>> Employee Database empty! <<<<\n\n");
            else {
                System.out.println("\n\n> List of the Employees:\n");
                for (int i = 0; i < this.size; i++) {
                    if (this.employee_List[i] == null) continue;
                    System.out.println(this.employee_List[i].toString());
                }
                System.out.println("> End of the List\n\n");
            }
        }

        /**
         * This method removes an employee from the array
         */
        public void removeEmployee(String employee_ID) {
            Employee[] tmp_employee_List = new Employee[this.employee_List.length];
            boolean is_Employee_Removed = false;
            Employee employee = null;

            for (int i = 0; i < this.size; i++) {
                if (this.employee_List[i] == null) continue;
                if (this.employee_List[i].getEmployee_ID().compareTo(employee_ID) == 0) {
                    employee = this.employee_List[i].getEmployee();
                    tmp_employee_List[i] = null;
                    this.size--;
                    is_Employee_Removed = true;
                } else
                    tmp_employee_List[i] = this.employee_List[i];
            }
            this.employee_List = tmp_employee_List;

            if (is_Employee_Removed) System.out.println(">>>> Employee_ID: "
                    + employee_ID + ", Name: " + employee.getFname() + " "
                    + employee.getLname() + ", date of birth: "
                    + employee.getDob() + " removed from database!" +
                    " <<<<\n\n");
            else System.out.println(">>>> Member with given details doesn't " +
                    "exist! <<<<\n\n");

        }
    }
    /**
     * MembersDatabase class creates array-list of the members
     */
    public class MembersDatabase {
        final static int INDEX = 0;
        final static int INITIAL_CAPACITY = 4;
        private Standard[] member_List;
        private int size;

        /**
         * Creates a MembersDatabase object with given params
         */
        public MembersDatabase(Standard[] mlist) {
            this.member_List = mlist;
            this.size = INDEX;
        }

        /**
         * This method checks if the member exists in the array
         */
        public boolean memberExist(Standard member) {
            for (int i = 0; i < this.size; i++) {
                if (this.member_List[i] == null) continue;
                if (this.member_List[i].compareTo(member) == 0)
                    return true;
            }
            return false;
        }

        /**
         * This method grows the array
         */
        private void grow() {
            Standard[] tmp_Member_List =
                    new Standard[this.member_List.length + INITIAL_CAPACITY];
            for (int i = 0; i < this.size; i++) {
                if (this.member_List[i] == null) continue;
                tmp_Member_List[i] =  this.member_List[i];
            }
            this.member_List = tmp_Member_List;
        }

        /**
         * This method adds a member to the array
         */
        public void addMember(Object member) {
            if (this.size >= this.member_List.length)
                grow();

            if (member instanceof Family)
                this.member_List[this.size] = (Family) member;
            else if (member instanceof Premium)
                this.member_List[this.size] = (Premium) member;
            else
                this.member_List[this.size] = (Standard) member;

            this.size++;
        }

        /**
         * Prints the whole member database array
         */
        public void print(Standard member) {
            if (this.size == 0)
                System.out.println("\n>>>> Member Database empty! <<<<\n\n");
            else {
                System.out.println("\n\n> List of the Members:\n");
                for (int i = 0; i < this.size; i++) {
                    if (this.member_List[i] == null) continue;
                    if (this.member_List[i].compareTo(member) == 0)
                        System.out.println(this.member_List[i].toString());
                }
                System.out.println("> End of the List\n\n");
            }
        }

        /**
         * Prints the whole member database array
         */
        public void print() {
            if (this.size == 0)
                System.out.println("\n>>>> Member Database empty! <<<<\n\n");
            else {
                System.out.println("\n\n> List of the Members:\n");
                for (int i = 0; i < this.size; i++) {
                    if (this.member_List[i] == null) continue;
                    System.out.println(this.member_List[i].toString());
                }
                System.out.println("> End of the List\n\n");
            }
        }

        /**
         * This method removes a member from the array
         */
        public void removeMember(Standard member) {
            Standard[] tmp_member_List = new Standard[this.member_List.length];
            boolean is_Member_Removed = false;

            for (int i = 0; i < this.size; i++) {
                if (this.member_List[i] == null) continue;
                if (this.member_List[i].compareTo(member) == 0) {
                    tmp_member_List[i] = null;
                    this.size--;
                    is_Member_Removed = true;
                } else
                    tmp_member_List[i] = this.member_List[i];
            }
            this.member_List = tmp_member_List;
            if (is_Member_Removed) System.out.println(">> Member_Name: "
                    + member.getFname() + " " + member.getLname()
                    + ", date of birth: " + member.getDob() + " removed from database!" +
                    " <<<<\n\n");
            else System.out.println(">>>> Member with given details doesn't " +
                    "exist! <<<<\n\n");

        }

        /**
         * This method prints the classes available
         */
        public void listClasses() {
            System.out.println();
            for (GymClasses classes : GymClasses.values()) {
                System.out.println(classes.toString());
            }
            System.out.println("\n");
        }

        /**
         * This method adds a gym class to the member object
         */
        public void addMemberToClass(Standard member, GymClasses gymClass) {
            for (int i = 0; i < this.size; i++) {
                if (this.member_List[i] == null) continue;
                if (this.member_List[i].compareTo(member) == 0) {

                    if (this.member_List[i].getClasses_SignedUp() <
                            (this.member_List[i].getGuestPass() + 1)) {
                        this.member_List[i].addGymClass(gymClass);
                    } else {
                        System.out.println(">>>> Classes registration limit " +
                                "exceeded <<<<\n\n");
                    }
                    break;
                }
            }
        }

        /**
         * This method removes a gym class from the member object
         */
        public void removeMemberFromClass(Standard member, GymClasses gymClass){
            for (int i = 0; i < this.size; i++) {
                if (this.member_List[i] == null) continue;
                if (this.member_List[i].compareTo(member) == 0) {
                    this.member_List[i].dropGymClass(gymClass);
                }
            }
        }
    }
    /**
     * This method checks if the entered gym class is available.
     */
    private GymClasses matchClass(String gym_Class) {
        for (GymClasses gymClass : GymClasses.values()) {
            if (gymClass.name().compareTo(gym_Class.toUpperCase()) == 0) {
                return gymClass;
            }
        }
        System.out.println(">>>> Invalid class entered! <<<<\n\n");
        return null;
    }
    /**
     * This method drops a member from the class he/she has registered to.
     */
    private void dropGymClasses(String fname, String lname, String dob) {
        if (correctDate(dob, new Date(dob), "DOB")) {
            if (membersDatabase.memberExist(new Standard(fname, lname,
                    new Date(dob)))) {
                sc = new Scanner(System.in);
                System.out.println("> Choose class to drop " +
                        "(Yoga/ Bicycle/ Cardio):");
                String gym_Class = sc.nextLine();
                GymClasses tmp_gymClass = matchClass(gym_Class);
                if (tmp_gymClass != null) {
                    membersDatabase.removeMemberFromClass(new Standard(fname,
                            lname, new Date(dob)), tmp_gymClass);
                }
            } else {
                System.out.println(">>>> Member Doesn't Exist! <<<<\n\n");
            }
        }
    }
    /**
     * This method adds an existing member to the gym class provided.
     */
    private void addGymClasses(String fname, String lname, String dob) {
        if (correctDate(dob, new Date(dob), "DOB")) {
            if (membersDatabase.memberExist(new Standard(fname, lname,
                    new Date(dob)))) {
                sc = new Scanner(System.in);
                System.out.println("> Choose a gym class to signUp for " +
                        "(Yoga/ Bicycle/ Cardio):");
                String gym_Class = sc.nextLine();
                GymClasses tmp_gymClass = matchClass(gym_Class);
                if (tmp_gymClass != null) {
                    membersDatabase.addMemberToClass(new Standard(fname, lname,
                            new Date(dob)), tmp_gymClass);
                }
            } else {
                System.out.println(">>>> Member Doesn't Exist! <<<<\n\n");
            }
        }
    }
    /**
     * This method provide membership types for member to choose from.
     */
    private String chooseMembershipType() {
        //TODO: show membership type description
        sc = new Scanner(System.in);
        System.out.println("> Choose a membership type (Standard/ Family/ " +
                "Premium):");
        return sc.nextLine();
    }

    /**
     * This method provides data of membership expiration for the member.
     */
    private Date dateOfExpiration(String membership_Type) {
        int membership_Duration = 3;
        if (membership_Type.compareTo("PREMIUM") == 0)
            membership_Duration = 12;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, membership_Duration);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        int year = calendar.get(Calendar.YEAR);
        return new Date(month, day, year);
    }

    /**
     * This method adds a member to the member database.
     */
    private void addMember(String fname, String lname, String dob) {
        if (correctDate(dob, new Date(dob), "DOB")) {
            if (membersDatabase.memberExist(new Standard(fname, lname,
                    new Date(dob)))) {
                System.out.println(">>>> Member already Exist! <<<<\n\n");
            } else {
                boolean member_added = false;
                String membership_Type = chooseMembershipType();
                switch (membership_Type.toUpperCase()) {
                    case "STANDARD":
                        membersDatabase.addMember(new Standard(fname, lname,
                                new Date(dob), dateOfExpiration("STANDARD")));
                        member_added = true;
                        break;

                    case "FAMILY":
                        membersDatabase.addMember(new Family(fname, lname,
                                new Date(dob), dateOfExpiration("FAMILY")));
                        member_added = true;
                        break;

                    case "PREMIUM":
                        membersDatabase.addMember(new Premium(fname, lname,
                                new Date(dob), dateOfExpiration("PREMIUM")));
                        member_added = true;
                        break;

                    default:
                        System.out.println(">>>> Invalid choice! <<<<\n\n");
                        break;
                }
                if (member_added)
                    System.out.println(">>>> Member: " + fname.toUpperCase() + " "
                            + lname.toUpperCase() + " added! <<<<\n\n");
            }
        }
    }

    /**
     * This method checks if the date entered is in the correct format and
     * if the age of the member is or above 18.
     */
    private boolean correctDate(String dateString, Date date, String flag) {
        StringTokenizer dateTokens =
                new StringTokenizer(dateString, "/");
        int day = Integer.parseInt(dateTokens.nextToken());
        int month = Integer.parseInt(dateTokens.nextToken()) - 1;
        int year = Integer.parseInt(dateTokens.nextToken());
        Calendar dateCal = Calendar.getInstance();
        Calendar currentCal = Calendar.getInstance();
        dateCal.set(year, month, day);
        if (!date.isValid()) {
            System.out.println(">>>> Invalid " + dateString + ": "
                    + "calendar date! <<<<\n\n");
            return false;
        }
        if (flag == "DOB") {
            if (dateCal.equals(currentCal) || dateCal.after(currentCal)) {
                System.out.println(">>>> Invalid " + dateString + ": "
                        + "cannot be today or a future date! <<<<\n\n");
                return false;
            }
            Calendar ageCalendar = Calendar.getInstance(); // Logic for age
            ageCalendar.set(Calendar.HOUR_OF_DAY, 0);
            ageCalendar.set(Calendar.MINUTE, 0);
            ageCalendar.set(Calendar.SECOND, 0);
            ageCalendar.set(Calendar.MILLISECOND, 0);
            ageCalendar.setTimeInMillis((currentCal.getTimeInMillis()
                    - dateCal.getTimeInMillis()));
            float age = ageCalendar.get(Calendar.YEAR) - 1970;
            age += (float) ageCalendar.get(Calendar.MONTH) / (float) 12;
            if (age < 18) {
                System.out.println(">>>> Invalid " + dateString + ": "
                        + "Age must be 18 or older to join! <<<<\n\n");
                return false;
            }
        }
        return true;
    }

    /**
     * This method adds an employee to the employee database.
     */
    private void addEmployee(String fname, String lname, String dob) {
        if (correctDate(dob, new Date(dob), "DOB")) {
            if (employeesDatabase.employeeExist(new Employee(fname,
                    lname, new Date(dob)))) {
                System.out.println(">>>> Employee already Exist! <<<<\n\n");
            } else {
                String employee_ID =
                        employeesDatabase.addEmployee(new Employee(fname.toUpperCase(),
                        lname.toUpperCase(), new Date(dob)));
                System.out.println(">>>> Employee:" + fname.toUpperCase() + " "
                        + lname.toUpperCase() + " added with Employee ID: "
                        + employee_ID + " <<<<\n\n");
            }
        }
    }

    /**
     * This method processes the options the members choose.
     */
    private void processMemberOptions() {
        sc = new Scanner(System.in);
        System.out.println("> Enter your choice:");
        // Tries the below segment and catches any error
        try {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    membersDatabase.listClasses();
                    processMemberOptions();
                    break;
                case 2:
                    System.out.println("\n\nSignUp a member for the classes:\n");
                    sc = new Scanner(System.in);
                    System.out.println("> Enter First Name:");
                    String fname = sc.nextLine();
                    sc = new Scanner(System.in);
                    System.out.println("> Enter Last Name:");
                    String lname = sc.nextLine();
                    sc = new Scanner(System.in);
                    System.out.println("> Enter Date of Birth (dd/mm/yyyy):");
                    String dob = sc.nextLine();
                    addGymClasses(fname, lname, dob);
                    processMemberOptions();
                    break;
                case 3:
                    System.out.println("\n\nDrop classes for a member:\n");
                    sc = new Scanner(System.in);
                    System.out.println("> Enter First Name:");
                    fname = sc.nextLine();
                    sc = new Scanner(System.in);
                    System.out.println("> Enter Last Name:");
                    lname = sc.nextLine();
                    sc = new Scanner(System.in);
                    System.out.println("> Enter Date of Birth (dd/mm/yyyy):");
                    dob = sc.nextLine();
                    dropGymClasses(fname, lname, dob);
                    processMemberOptions();
                    break;
                case 4:
                    System.out.println("\n\nPrint member details:\n");
                    sc = new Scanner(System.in);
                    System.out.println("> Enter First Name:");
                    fname = sc.nextLine();
                    sc = new Scanner(System.in);
                    System.out.println("> Enter Last Name:");
                    lname = sc.nextLine();
                    sc = new Scanner(System.in);
                    System.out.println("> Enter Date of Birth (dd/mm/yyyy):");
                    dob = sc.nextLine();
                    membersDatabase.print(new Standard(fname, lname,
                            new Date(dob)));
                    processMemberOptions();
                    break;
                case 5:
                    System.out.println("> Quiting Gym Manager.......");
                    break;
                default:
                    System.out.println(">>>> No such option available, try " +
                            "again! <<<<\n\n");
                    sc = new Scanner(System.in);
                    System.out.println("> Do you want to view the options " +
                            "again? Y/N:");
                    if (sc.nextLine().toUpperCase().compareTo("Y") == 0) memberOptions();
                    processMemberOptions();
                    break;
            }
        } catch (Exception e) {
            System.out.println(">>>> Invalid option entered, try again! " +
                    "<<<<\n\n");
            processMemberOptions();
        }
    }
    /**
     * This method processes the options employee choose.
     */
    private void processEmployeeOptions() {
        sc = new Scanner(System.in);
        System.out.println("> Enter your choice:");
        // Tries the below segment and catches any error
        try {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\n\nAdd a new Employee:\n");
                    sc = new Scanner(System.in);
                    System.out.println("> Enter First Name:");
                    String fname = sc.nextLine();
                    sc = new Scanner(System.in);
                    System.out.println("> Enter Last Name:");
                    String lname = sc.nextLine();
                    sc = new Scanner(System.in);
                    System.out.println("> Enter Date of Birth (dd/mm/yyyy):");
                    String dob = sc.nextLine();
                    addEmployee(fname, lname, dob);
                    processEmployeeOptions();
                    break;

                case 2:
                    System.out.println("\n\nList of the Employees:\n");
                    employeesDatabase.print();
                    processEmployeeOptions();
                    break;

                case 3:
                    System.out.println("\n\nRemove an existing Employee:\n");
                    sc = new Scanner(System.in);
                    System.out.println("> Enter Employee ID:");
                    String employee_ID = sc.nextLine();
                    employeesDatabase.removeEmployee(employee_ID.toUpperCase());
                    processEmployeeOptions();
                    break;

                case 4:
                    System.out.println("\n\nSignUp a new member:\n");
                    sc = new Scanner(System.in);
                    System.out.println("> Enter First Name:");
                    fname = sc.nextLine();
                    sc = new Scanner(System.in);
                    System.out.println("> Enter Last Name:");
                    lname = sc.nextLine();
                    sc = new Scanner(System.in);
                    System.out.println("> Enter Date of Birth (dd/mm/yyyy):");
                    dob = sc.nextLine();
                    addMember(fname, lname, dob);
                    processEmployeeOptions();
                    break;

                case 5:
                    membersDatabase.print();
                    processEmployeeOptions();
                    break;

                case 6:
                    System.out.println("\n\nRemove an existing members:\n");
                    sc = new Scanner(System.in);
                    System.out.println("> Enter First Name:");
                    fname = sc.nextLine();
                    sc = new Scanner(System.in);
                    System.out.println("> Enter Last Name:");
                    lname = sc.nextLine();
                    sc = new Scanner(System.in);
                    System.out.println("> Enter Date of Birth (dd/mm/yyyy):");
                    dob = sc.nextLine();
                    membersDatabase.removeMember(new Standard(fname, lname,
                            new Date(dob)));
                    processEmployeeOptions();
                    break;

                case 7:
                    membersDatabase.listClasses();
                    processEmployeeOptions();
                    break;

                case 8:
                    System.out.println("\n\nSignUp a member for the classes:\n");
                    sc = new Scanner(System.in);
                    System.out.println("> Enter First Name:");
                    fname = sc.nextLine();
                    sc = new Scanner(System.in);
                    System.out.println("> Enter Last Name:");
                    lname = sc.nextLine();
                    sc = new Scanner(System.in);
                    System.out.println("> Enter Date of Birth (dd/mm/yyyy):");
                    dob = sc.nextLine();
                    addGymClasses(fname, lname, dob);
                    processEmployeeOptions();
                    break;

                case 9:
                    System.out.println("\n\nDrop classes for a member:\n");
                    sc = new Scanner(System.in);
                    System.out.println("> Enter First Name:");
                    fname = sc.nextLine();
                    sc = new Scanner(System.in);
                    System.out.println("> Enter Last Name:");
                    lname = sc.nextLine();
                    sc = new Scanner(System.in);
                    System.out.println("> Enter Date of Birth (dd/mm/yyyy):");
                    dob = sc.nextLine();
                    dropGymClasses(fname, lname, dob);
                    processEmployeeOptions();
                    break;

                case 10:
                    System.out.println("> Quiting Gym Manager.......");
                    break;

                default:
                    System.out.println(">>>> No such option available, try " +
                            "again! <<<<\n\n");
                    sc = new Scanner(System.in);
                    System.out.println("> Do you want to view the options " +
                            "again? Y/N:");
                    if (sc.nextLine().toUpperCase().compareTo("Y") == 0) employeeOptions();
                    processEmployeeOptions();
                    break;
            }
        } catch (Exception e) {
            System.out.println(">>>> Invalid option entered, try again! " +
                    "<<<<\n\n");
            processEmployeeOptions();
        }
    }

    /**
     * This method lists the options the members can choose from.
     */
    private void memberOptions() {
        System.out.println("\n\n> Choose a process from the options " +
                "below:\n");
        System.out.println(">> 1) List gym classes available.");
        System.out.println(">> 2) SignUp a member for the classes.");
        System.out.println(">> 3) Drop classes for a member.\n");
        System.out.println(">> 4) Print member details.\n");
        System.out.println(">> 5) Quit GymManager.\n");
    }

    /**
     * This method lists the options the employees can choose from.
     */
    private void employeeOptions() {
        System.out.println("\n\n> Choose a process from the options " +
                "below:\n");
        System.out.println(">> 1) Add a new employee.");
        System.out.println(">> 2) List all the employees.");
        System.out.println(">> 3) Remove an existing employee.\n");
        System.out.println(">> 4) SignUp a new member.");
        System.out.println(">> 5) List all the members.");
        System.out.println(">> 6) Remove an existing members.\n");
        System.out.println(">> 7) List gym classes available.");
        System.out.println(">> 8) SignUp a member for the classes.");
        System.out.println(">> 9) Drop classes for a member.\n");
        System.out.println(">> 10) Quit GymManager.\n");
    }

    /**
     * This method lets a memeber login with the first & last name & date
     * of birth.
     */
    private boolean memberLogin() {
        System.out.println("\n> LogIn to GymManager Using your member info:");
        sc = new Scanner(System.in);
        System.out.println("> Enter First Name:");
        String fname = sc.nextLine();
        sc = new Scanner(System.in);
        System.out.println("> Enter Last Name:");
        String lname = sc.nextLine();
        sc = new Scanner(System.in);
        System.out.println("> Enter Date of Birth (dd/mm/yyyy):");
        String dob = sc.nextLine();
        boolean is_Login = membersDatabase.memberExist(new Standard(fname, lname,
                new Date(dob)));
        while (!is_Login) {
            System.out.println(">>>> Incorrect member info, logIn failed! " +
                    "Try again. <<<<\n\n");
            // Recursive Call
            is_Login = memberLogin();
        }
        return is_Login;
    }
    /**
     * This method lets an employee login with the login ID.
     */
    private boolean employeeLogin() {
        sc = new Scanner(System.in);
        System.out.println("\n> LogIn to GymManager Using your employee ID:");
        String employee_ID = sc.nextLine();
        boolean is_Login = employeesDatabase.login(employee_ID.toUpperCase());
        while (!is_Login) {
            System.out.println(">>>> ID incorrect, logIn failed! Try " +
                    "again. <<<<\n\n");
            // Recursive call  //
            is_Login = employeeLogin();
        }
        return is_Login;
    }
    /**
     * This method lets you choose a role between an employee and member.
     * Calls login methods, options methods and processOptions methods.
     */
    private void chooseRole() {
        sc = new Scanner(System.in);
        System.out.println("> Choose role to log in (Employee/ Member):");
        String role = sc.nextLine().toUpperCase();
        switch (role) {
            case "EMPLOYEE":
                employeeLogin();
                System.out.println(">>>> LogIn successful! <<<<");
                employeeOptions();
                processEmployeeOptions();
                break;
            case "MEMBER":
                memberLogin();
                System.out.println(">>>> LogIn successful! <<<<");
                memberOptions();
                processMemberOptions();
                break;
            default:
                System.out.println(">>>> Invalid option entered, try again! " +
                        "<<<<\n\n");
                chooseRole();
                break;
        }
    }
    /**
     * creates dummy data for employee and members
     * and call chooseRole method.
     */
    public void run() {
        System.out.println("> Loading Data.......\n");
        // ADDING EMPLOYEES
        employeesDatabase.addEmployee(new Employee("PRIT",
                "PATEL", new Date("31/01/2000"), "P1234"));
        employeesDatabase.addEmployee(new Employee("VIVEK",
                "DOBARIYA", new Date("31/01/2000"), "V1234"));
        employeesDatabase.addEmployee(new Employee("HARSH",
                "VAGHASIYA", new Date("31/01/2000"), "H1234"));
        employeesDatabase.addEmployee(new Employee("VIRAJ",
                "GEVARIYA", new Date("31/01/2000"), "G1234"));
        // ADDING MEMBERS
        membersDatabase.addMember(new Standard("PRIT", "PATEL",
                new Date("31/01/2000"), dateOfExpiration("STANDARD")));
        membersDatabase.addMember(new Standard("VIVEK", "DOBARIYA",
                new Date("31/01/2000"), dateOfExpiration("STANDARD")));
        membersDatabase.addMember(new Standard("HARSH", "VAGHASIYA",
                new Date("31/01/2000"), dateOfExpiration("STANDARD")));
        membersDatabase.addMember(new Standard("VIRAJ", "GEVARIYA",
                new Date("31/01/2000"), dateOfExpiration("STANDARD")));
        chooseRole();
    }
    /**
     * calls GymManager class's run method.
     */
    public static void main(String[] args) {
        System.out.println("> Hello, Welcome to the Gym Management " +
                "project!\n");
        new GymManager().run();
    }
}
