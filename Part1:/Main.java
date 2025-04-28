public class Main {
    public static void main(String[] args) {
        // 1) Create your horses
        Horse h1 = new Horse('☽', "PIPPI LONGSTOCKING", 0.6);
        Horse h2 = new Horse('☃', "KOKOMO",           0.6);
        Horse h3 = new Horse('♞', "EL JEFE",           0.4);

        // 2) Create a race (e.g. 30 “units” long) and assign lanes
        Race race = new Race(30);
        race.addHorse(h1, 1);
        race.addHorse(h2, 2); 
        race.addHorse(h3, 3);

        // 3) Run it!
        race.startRace();
    }
}

