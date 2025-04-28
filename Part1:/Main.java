public class Main {
    public static void main(String[] args) {
        
        Horse h1 = new Horse('☽', "PIPPI LONGSTOCKING", 0.6);
        Horse h2 = new Horse('☃', "KOKOMO",           0.6);
        Horse h3 = new Horse('♞', "EL JEFE",           0.4);

        
        Race race = new Race(30);
        race.addHorse(h1, 1);
        race.addHorse(h2, 2); 
        race.addHorse(h3, 3);

       
        race.startRace();
    }
}

