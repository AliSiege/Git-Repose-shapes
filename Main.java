// Soheil Farangizade

import java.util.Random;


public class Main {
    public static int dayCounter=1;
    public static int nightCounter=1;

    //God god = new God();
    public static void main(String[] args) {
        System.out.println("Hello World!");

        God god = new God();
        god.start();

}

static class God {
    int num = 10;
    Random rand = new Random();
    Person[] persons = new Person[num];
    String players[] ;
    boolean[] roled = new boolean[num];
    int random;

    God() {
        players = new String[]{"ali", "akbar", "nader", "qasem", "reza" ,
                "arash", "ahmad", "mehdi", "amir", "ehsan"};
    }
// the game will start from here
    void start() {

        for (int i = 0; i < 3; i++) {
            do {///////////////mafia////////
                random = rand.nextInt(10);
            } while (roled[random]);
            persons[i] = new Person(players[random]);
            persons[i].role = "mafia";
            roled[random] = true;
        }

        do {/////////detective/////////
            random = rand.nextInt(num);
        } while (roled[random]);
        persons[3] = new Person(players[random]);
        persons[3].role = "detective";
        roled[random] = true;

        do {//////////doctor///////////
            random = rand.nextInt(10);
        } while (roled[random]);
        persons[4] = new Person(players[random]);
        persons[4].role = "doctor";
        roled[random] = true;

        for (int i = 5; i < 10; i++){
            do {///////////////police////////
                random = rand.nextInt(num);
            } while (roled[random]);
            persons[i] = new Person(players[random]);
            persons[i].role = "police";
            roled[random] = true;
        }

        while (true) {
            System.out.print("Alives: ");
            for (String name: players) {
                for (Person person: persons) {
                    if (person.name.equals(name) && person.alive)
                        System.out.print(" "+person.name);
                }
            }

            System.out.println("\nDay :" + dayCounter++);
            day();
            winningCheck();
            System.out.println("Night :" + nightCounter++ );
            night();
            System.out.println();
            winningCheck();
        }
    }
 // this method will show us who is winner
    void winningCheck(){

        int policeAlives =0;
        int mafiaAlives =0;
        for (int i=0; i<3; i++)
            if (persons[i].alive)
                mafiaAlives++;

        for (int i=3; i<num; i++)
            if(persons[i].alive)
                policeAlives++;

        if (mafiaAlives==0){
            System.out.println("\nPolices Wins!\n");
            for(int i=0; i<10; i++)
                System.out.println(persons[i].name+ " "+persons[i].role);
            System.exit(0);
        }
        if (policeAlives==mafiaAlives){
            System.out.println("\nMafia Wins!\n");
            for(int i=0; i<num; i++)
                System.out.println(persons[i].name+ " :"+persons[i].role);
            System.exit(0);
        }
    }
 // this method tell us the day satart with who
    void day() {
        int selectedPerson = 0;
        int[] election = new int[10];
        for (int i = 0; i < num; i++) {
            int select;
            if (!persons[i].alive)
                continue;
            do {
                select = persons[i].voating();
                for (int j = 0; j < num; j++)
                    if (persons[j].name.equals(players[select]))
                        selectedPerson = j;
            } while (!persons[selectedPerson].alive);
            election[select]++;
        }

        int executeId = 0, max = 0;
        for (int i = 0; i < num; i++)
            if (election[i] > max) {
                max = election[i];
                executeId = i;
            }

        for (int i = 0; i < num; i++)
            if (persons[i].name.equals(players[executeId])) {
                persons[i].alive = false;
                System.out.println(persons[i].name + " Died.");
            }


    }
 // what will happend in the night
    void night(){
        int selectedPerson = 0;

        int selectOfDetective;/////////////detective Guessing////////////
        if (persons[3].alive) {
            do {
                selectOfDetective = persons[3].voating();
                for (int j = 0; j < num; j++)
                    if (persons[j].name.equals(players[selectOfDetective]))
                        selectedPerson = j;
            } while (!persons[selectedPerson].alive);
            //if (persons[selectedPerson].role.equals("mafia")
            //    persons[3].mafiaList.append(persons[selectedPerson].name);
        }

        int selectOfDoctor = 0;/////////////detective Guessing////////////
        if (persons[4].alive) {
            do {
                selectOfDoctor = persons[4].voating();
                for (int j = 0; j < num; j++)
                    if (persons[j].name.equals(players[selectOfDoctor]))
                        selectedPerson = j;
            } while (!persons[selectedPerson].alive);
            selectOfDoctor = selectedPerson;
            System.out.println("Doctor attempt for: "+persons[selectOfDoctor].name);
        }


        int[] election = new int[num];
        for (int i = 0; i < 3; i++) {
            int select;
            if (!persons[i].alive)
                continue;
            do {
                select = persons[i].voating();
                for (int j = 0; j < num; j++)
                    if (persons[j].name.equals(players[select]))
                        selectedPerson = j;
            } while (!persons[selectedPerson].alive || persons[selectedPerson].role.equals("mafia"));
            election[select]++;
        }

        ////////////finding election choice(index of playersName array//////
        int executeId = 0, max = 0;
        for (int i = 0; i < num; i++)
            if (election[i] > max) {
                max = election[i];
                executeId = i;
            }

        boolean saveFlag =true;
        for (int i = 0; i < num; i++)
            if (persons[i].name.equals(players[executeId])&& i!=selectOfDoctor) {
                persons[i].alive = false;
                saveFlag = false;
                System.out.println(persons[i].name + " Died.");
                break;
            }
        if (saveFlag)
            System.out.println(persons[selectOfDoctor].name+ " saved!!!");
    }
}

static class Person{
    String name;
    Random rand = new Random();
    Boolean alive = true;
    String role;
    String[] mafiaList;

    int voating(){
        return rand.nextInt(10);
    }
    Person(String myName){
        name = myName;

    }
}
}
