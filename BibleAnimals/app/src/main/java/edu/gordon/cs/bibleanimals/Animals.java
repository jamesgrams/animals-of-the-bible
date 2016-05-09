package edu.gordon.cs.bibleanimals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeMap;

/**
 * Collection of animals in the Bible
 * This class follows the Singleton pattern
 * Created by James on 4/13/2016.
 */
public class Animals {
    public static Animals instance = null;

    // This map contains a key (animal name) and an int
    // for the animals id on the Encyclopedia of Life
    // The list of animals in the Bible came from here:
    // http://www.christiananswers.net/dictionary/animals.html
    // and cross referenced with the animals listed here:
    // https://en.wikipedia.org/wiki/List_of_animals_in_the_Bible
    // The animals were also checked to see if they were in the LEB
    // Bible
    private TreeMap<String, Animal> animalMap;
    private TreeMap<String, TreeMap<String, Animal>> categoryMap;
    private TreeMap<String, TreeMap<String, Animal>> booksMap;
    private BibleBooks booksOfBible;


    /**
     * Get the instance of this class
     * @return the single instance of this class
     */
    public static Animals getInstance() {
        if(instance == null) {
            instance = new Animals();
        }
        return instance;
    }

    /**
     * Private constructor
      */
    private Animals() {
        this.booksOfBible = BibleBooks.getInstance();
        // This variable is to store animals when they have more than one name
        // So multiple keys in the HashMap will point to the same animal
        Animal tempAnimal;
        animalMap = new TreeMap<>();
        animalMap.put("Adder", new Animal(new String[]{"Adder"}, 289378, REPTILE));
        animalMap.put("Ant", new Animal(new String[]{"Ant"}, 699, CREEPY_CRAWLIES));
        animalMap.put("Ape", new Animal(new String[]{"Ape"}, 1653, MAMMAL));
        animalMap.put("Asp", new Animal(new String[]{"Asp"}, 458632, REPTILE));
        animalMap.put("Ass", new Animal(new String[]{"Ass"}, 4472382, MAMMAL));
        animalMap.put("Baboon", new Animal(new String[]{"Baboon"}, 15084, MAMMAL));
        animalMap.put("Badger", new Animal(new String[]{"Badger"}, 328046, MAMMAL));
        animalMap.put("Bat", new Animal(new String[]{"Bat"}, 7631, MAMMAL));
        animalMap.put("Bear", new Animal(new String[]{"Bear"}, 7664, MAMMAL));
        animalMap.put("Bee", new Animal(new String[]{"Bee"}, 677, CREEPY_CRAWLIES));
        tempAnimal = new Animal(new String[]{"Bird", "Fowl"}, 695, BIRD);
        animalMap.put("Bird", tempAnimal);
        animalMap.put("Fowl", tempAnimal);
        tempAnimal = new Animal(new String[]{"Bull", "Calf", "Cattle", "Cow", "Heifer",
                "Ox", "Oxen"}, 328699, MAMMAL);
        animalMap.put("Bull", tempAnimal);
        animalMap.put("Calf", tempAnimal);
        animalMap.put("Cattle", tempAnimal);
        animalMap.put("Cow", tempAnimal);
        animalMap.put("Heifer", tempAnimal);
        animalMap.put("Ox", tempAnimal);
        animalMap.put("Oxen", tempAnimal);
        animalMap.put("Camel", new Animal(new String[]{"Camel"}, 309019, MAMMAL));
        tempAnimal = new Animal(new String[]{"Caterpillar", "Moth"}, 747, CREEPY_CRAWLIES);
        animalMap.put("Caterpillar", tempAnimal);
        animalMap.put("Moth", tempAnimal);
        animalMap.put("Chameleon", new Animal(new String[]{"Chameleon"}, 791828, REPTILE));
        tempAnimal = new Animal(new String[]{"Coney", "Hare"}, 1689, MAMMAL);
        animalMap.put("Coney", tempAnimal);
        animalMap.put("Hare", tempAnimal);
        tempAnimal = new Animal(new String[]{"Colt", "Horse", "Mare", "Stallion"}, 328648, MAMMAL);
        animalMap.put("Colt", tempAnimal);
        animalMap.put("Horse", tempAnimal);
        animalMap.put("Mare", tempAnimal);
        animalMap.put("Stallion", tempAnimal);
        animalMap.put("Coral", new Animal(new String[]{"Coral"}, 1746, OTHER));
        animalMap.put("Cormorant", new Animal(new String[]{"Cormorant"}, 1048641, BIRD));
        animalMap.put("Crane", new Animal(new String[]{"Crane"}, 1049273, BIRD));
        animalMap.put("Cricket", new Animal(new String[]{"Cricket"}, 996, CREEPY_CRAWLIES));
        animalMap.put("Crocodile", new Animal(new String[]{"Crocodile"}, 795278, REPTILE));
        tempAnimal = new Animal(new String[]{"Deer", "Fawn"}, 7685, MAMMAL);
        animalMap.put("Deer", tempAnimal);
        animalMap.put("Fawn", tempAnimal);
        animalMap.put("Dog", new Animal(new String[]{"Dog"}, 1228387, MAMMAL));
        animalMap.put("Donkey", new Animal(new String[]{"Donkey"}, 328647, MAMMAL));
        animalMap.put("Dove", new Animal(new String[]{"Dove"}, 914622, BIRD));
        animalMap.put("Eagle", new Animal(new String[]{"Eagle"}, 1049119, BIRD));
        tempAnimal = new Animal(new String[]{"Ewe", "Lamb", "Ram", "Sheep"}, 311906, MAMMAL);
        animalMap.put("Ewe", tempAnimal);
        animalMap.put("Lamb", tempAnimal);
        animalMap.put("Ram", tempAnimal);
        animalMap.put("Sheep", tempAnimal);
        animalMap.put("Falcon", new Animal(new String[]{"Falcon"}, 1049164, BIRD));
        animalMap.put("Fish", new Animal(new String[]{"Fish"}, 1905, FISH));
        animalMap.put("Flea", new Animal(new String[]{"Flea"}, 1062, CREEPY_CRAWLIES));
        animalMap.put("Fly", new Animal(new String[]{"Fly"}, 421, CREEPY_CRAWLIES));
        animalMap.put("Fox", new Animal(new String[]{"Fox"}, 328609, MAMMAL));
        animalMap.put("Frog", new Animal(new String[]{"Frog"}, 1553, AMPHIBIAN));
        animalMap.put("Gazelle", new Animal(new String[]{"Gazelle"}, 129520, MAMMAL));
        animalMap.put("Gecko", new Animal(new String[]{"Gecko"}, 1055469, REPTILE));
        animalMap.put("Gnat", new Animal(new String[]{"Gnat"}, 740671, CREEPY_CRAWLIES));
        tempAnimal = new Animal(new String[]{"Goat", "Kid"}, 328660, MAMMAL);
        animalMap.put("Goat", tempAnimal);
        animalMap.put("Kid", tempAnimal);
        animalMap.put("Grasshopper", new Animal(new String[]{"Grasshopper"}, 6580, CREEPY_CRAWLIES));
        animalMap.put("Hawk", new Animal(new String[]{"Hawk"}, 8016, BIRD));
        animalMap.put("Hedgehog", new Animal(new String[]{"Hedgehog"}, 1178685, MAMMAL));
        animalMap.put("Hen", new Animal(new String[]{"Hen", "Rooster"}, 1049263, BIRD));
        animalMap.put("Heron", new Animal(new String[]{"Heron"}, 1048664, BIRD));
        animalMap.put("Hippopotamus", new Animal(new String[]{"Hippopotamus"}, 311532, MAMMAL));
        animalMap.put("Hoopoe", new Animal(new String[]{"Hoopoe"}, 917145, BIRD));
        animalMap.put("Hornet", new Animal(new String[]{"Hornet"}, 5242, CREEPY_CRAWLIES));
        animalMap.put("Hyena", new Animal(new String[]{"Hyena"}, 311570, MAMMAL));
        animalMap.put("Ibex", new Animal(new String[]{"Ibex"}, 328692, MAMMAL));
        animalMap.put("Jackal", new Animal(new String[]{"Jackal"}, 328681, MAMMAL));
        animalMap.put("Kite", new Animal(new String[]{"Kite"}, 1049133, BIRD));
        animalMap.put("Leech", new Animal(new String[]{"Leech"}, 2565059, CREEPY_CRAWLIES));
        animalMap.put("Leopard", new Animal(new String[]{"Leopard"}, 328673, MAMMAL));
        animalMap.put("Lion", new Animal(new String[]{"Lion"}, 328672, MAMMAL));
        animalMap.put("Lizard", new Animal(new String[]{"Lizard"}, 1704, REPTILE));
        animalMap.put("Locust", new Animal(new String[]{"Locust"}, 494417, CREEPY_CRAWLIES));
        animalMap.put("Mole", new Animal(new String[]{"Mole"}, 1178988, MAMMAL));
        animalMap.put("Mouse", new Animal(new String[]{"Mouse"}, 8698, MAMMAL));
        animalMap.put("Mule", new Animal(new String[]{"Mule"}, 15580, MAMMAL));
        animalMap.put("Ostrich", new Animal(new String[]{"Ostrich"}, 1178371, BIRD));
        animalMap.put("Owl", new Animal(new String[]{"Owl"}, 696, BIRD));
        animalMap.put("Partridge", new Animal(new String[]{"Partridge"}, 7591, BIRD));
        animalMap.put("Peacock", new Animal(new String[]{"Peacock"}, 1049264, BIRD));
        animalMap.put("Pigeon", new Animal(new String[]{"Pigeon"}, 7978, BIRD));
        tempAnimal = new Animal(new String[]{"Pig", "Swine"}, 328663, MAMMAL);
        animalMap.put("Pig", tempAnimal);
        animalMap.put("Swine", tempAnimal);
        animalMap.put("Quail", new Animal(new String[]{"Quail"}, 914847, BIRD));
        animalMap.put("Raven", new Animal(new String[]{"Raven"}, 33750, BIRD));
        animalMap.put("Roebuck", new Animal(new String[]{"Roebuck"}, 308479, MAMMAL));
        animalMap.put("Scorpion", new Animal(new String[]{"Scorpion"}, 8542, CREEPY_CRAWLIES));
        animalMap.put("Seagull", new Animal(new String[]{"Seagull"}, 8001, BIRD));
        tempAnimal = new Animal(new String[]{"Serpent", "Snake"}, 2815988, REPTILE);
        animalMap.put("Serpent", tempAnimal);
        animalMap.put("Snake", tempAnimal);
        animalMap.put("Snail", new Animal(new String[]{"Snail"}, 2366, CREEPY_CRAWLIES));
        animalMap.put("Sparrow", new Animal(new String[]{"Sparrow"}, 922241, BIRD));
        animalMap.put("Spider", new Animal(new String[]{"Spider"}, 166, CREEPY_CRAWLIES));
        animalMap.put("Sponge", new Animal(new String[]{"Sponge"}, 3142, OTHER));
        animalMap.put("Stork", new Animal(new String[]{"Stork"}, 1049144, BIRD));
        animalMap.put("Swallow", new Animal(new String[]{"Swallow"}, 7544, BIRD));
        animalMap.put("Viper", new Animal(new String[]{"Viper"}, 790158, REPTILE));
        animalMap.put("Vulture", new Animal(new String[]{"Vulture"}, 914578, BIRD));
        animalMap.put("Weasel", new Animal(new String[]{"Weasel"}, 14116, MAMMAL));
        animalMap.put("Wolf", new Animal(new String[]{"Wolf"}, 328607, MAMMAL));
        animalMap.put("Worm", new Animal(new String[]{"Worm"}, 36, CREEPY_CRAWLIES));

        animalMap.get("Adder").setBooks(new ArrayList<>(Arrays.asList(new String[]{"Proverbs",  "Jeremiah"})));
        animalMap.get("Ant").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Proverbs"})));
        animalMap.get("Ape").setBooks(new ArrayList<>(Arrays.asList(new String[] {"2 Chronicles", "1 Kings"})));
        animalMap.get("Asp").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Romans"})));
        animalMap.get("Ass").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Daniel", "Isaiah", "Jeremiah", "Job"})));
        animalMap.get("Baboon").setBooks(new ArrayList<>(Arrays.asList(new String[] {"1 Kings"})));
        animalMap.get("Badger").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Proverbs", "Psalm"})));
        animalMap.get("Bat").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "Isaiah", "Leviticus"})));
        animalMap.get("Bear").setBooks(new ArrayList<>(Arrays.asList(new String[] { "2 Samuel",  "2 Kings",  "Daniel",  "Job",  "Revelation",  "1 Samuel",  "Isaiah", "Hosea",  "Amos",  "Proverbs",  "Lamentations"})));
        animalMap.get("Bee").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Judges", "Deuteronomy", "Isaiah", "Psalm"})));
        animalMap.get("Bird").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Daniel", "Psalm", "1 Samuel", "Matthew", "Luke", "Leviticus", "Ecclesiastes", "Isaiah", "Revelation", "Hosea", "Amos", "Proverbs", "Nehemiah", "Genesis", "Romans", "Jeremiah", "Deuteronomy", "Job", "2 Samuel", "1 Kings", "Acts", "Lamentations", "Ezekiel", "Mark", "James", "1 Corinthians", "Zephaniah"})));
        animalMap.get("Bull").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Hosea", "2 Chronicles", "Psalm", "Micah", "1 Samuel", "Jeremiah", "Judges", "Malachi", "Isaiah", "Amos", "1 Chronicles", "Numbers", "Exodus", "Hebrews", "Leviticus", "Job", "Proverbs", "1 Kings", "Acts", "Ezekiel", "Ezra", "Genesis"})));
        animalMap.get("Calf").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Hosea", "2 Chronicles", "Psalm", "Micah", "1 Samuel", "Jeremiah", "Judges", "Malachi", "Isaiah", "Amos", "1 Chronicles", "Numbers", "Exodus", "Hebrews", "Leviticus", "Job", "Proverbs", "1 Kings", "Acts", "Ezekiel", "Ezra", "Genesis"})));
        animalMap.get("Camel").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Exodus", "2 Chronicles", "2 Kings", "Judges", "Jeremiah", "Leviticus", "Deuteronomy", "Ezra", "Isaiah", "Job", "1 Samuel", "Genesis", "Mark", "Zechariah", "1 Kings", "Matthew", "1 Chronicles", "Nehemiah", "Ezekiel", "Luke"})));
        animalMap.get("Caterpillar").setBooks(new ArrayList<>(Arrays.asList(new String[] {"2 Chronicles", "1 Kings"})));
        animalMap.get("Cattle").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Hosea", "2 Chronicles", "Psalm", "Micah", "1 Samuel", "Jeremiah", "Judges", "Malachi", "Isaiah", "Amos", "1 Chronicles", "Numbers", "Exodus", "Hebrews", "Leviticus", "Job", "Proverbs", "1 Kings", "Acts", "Ezekiel", "Ezra", "Genesis"})));
        animalMap.get("Chameleon").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Leviticus"})));
        animalMap.get("Colt").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Matthew", "Mark", "Luke", "Job", "Genesis"})));
        animalMap.get("Coney").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "Leviticus"})));
        animalMap.get("Coral").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Ezekiel", "Job"})));
        animalMap.get("Cormorant").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "Leviticus"})));
        animalMap.get("Cow").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Hosea", "2 Chronicles", "Psalm", "Micah", "1 Samuel", "Jeremiah", "Judges", "Malachi", "Isaiah", "Amos", "1 Chronicles", "Numbers", "Exodus", "Hebrews", "Leviticus", "Job", "Proverbs", "1 Kings", "Acts", "Ezekiel", "Ezra", "Genesis"})));
        animalMap.get("Crane").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Isaiah"})));
        animalMap.get("Cricket").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "Leviticus"})));
        animalMap.get("Crocodile").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Leviticus"})));
        animalMap.get("Deer").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "Psalm", "Job", "Isaiah", "Habakkuk", "1 Kings", "Acts", "Proverbs", "2 Samuel"})));
        animalMap.get("Dog").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Ecclesiastes", "Mark", "Judges", "Jeremiah", "Philippians", "Deuteronomy", "2 Kings", "Psalm", "2 Samuel", "Job", "1 Samuel", "Revelation", "Isaiah", "2 Peter", "1 Kings", "Matthew", "Proverbs", "Exodus", "Luke"})));
        animalMap.get("Donkey").setBooks(new ArrayList<>(Arrays.asList(new String[] {"2 Chronicles", "Psalm", "1 Samuel", "Matthew", "Luke", "Judges", "Isaiah", "Hosea", "Zechariah", "1 Chronicles", "Numbers", "Exodus", "Jeremiah", "Deuteronomy", "2 Kings", "Joshua", "2 Samuel", "Job", "1 Kings", "Proverbs", "John", "Ezekiel", "Nehemiah", "Ezra", "Genesis", "Mark", "2 Peter"})));
        animalMap.get("Dove").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Numbers", "Leviticus", "Mark", "2 Kings", "Psalm", "Isaiah", "Nahum", "Hosea", "Song of Solomon", "Luke", "Matthew", "John", "Genesis", "Ezekiel", "Jeremiah"})));
        animalMap.get("Eagle").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Daniel", "Isaiah", "Jeremiah", "Deuteronomy", "Exodus", "Psalm", "Micah", "Job", "Revelation", "2 Samuel", "Habakkuk", "Obadiah", "Proverbs", "Lamentations", "Ezekiel", "Leviticus"})));
        animalMap.get("Ewe").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Leviticus", "Psalm", "Genesis", "Song of Solomon", "Numbers", "2 Samuel"})));
        animalMap.get("Falcon").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "Job"})));
        animalMap.get("Fawn").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "Psalm", "Job", "Isaiah", "Habakkuk", "1 Kings", "Acts", "Proverbs", "2 Samuel"})));
        animalMap.get("Fish").setBooks(new ArrayList<>(Arrays.asList(new String[] {"2 Chronicles", "Psalm", "1 Samuel", "Matthew", "Luke", "Ecclesiastes", "Jonah", "Zephaniah", "Isaiah", "Hosea", "Amos", "Nehemiah", "Genesis", "Exodus", "Jeremiah", "1 Corinthians", "Job", "1 Kings", "Deuteronomy", "John", "Ezekiel", "Numbers", "Habakkuk", "Mark"})));
        animalMap.get("Flea").setBooks(new ArrayList<>(Arrays.asList(new String[] {"1 Samuel"})));
        animalMap.get("Fly").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Isaiah"})));
        animalMap.get("Fowl").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Daniel", "Psalm", "1 Samuel", "Matthew", "Luke", "Leviticus", "Ecclesiastes", "Isaiah", "Revelation", "Hosea", "Amos", "Proverbs", "Nehemiah", "Genesis", "Romans", "Jeremiah", "Deuteronomy", "Job", "2 Samuel", "1 Kings", "Acts", "Lamentations", "Ezekiel", "Mark", "James", "1 Corinthians", "Zephaniah"})));
        animalMap.get("Fox").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Nehemiah", "Judges", "Matthew", "Song of Solomon", "Luke", "Lamentations", "Ezekiel"})));
        animalMap.get("Frog").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Revelation", "Psalm", "Exodus"})));
        animalMap.get("Gazelle").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "Acts", "Isaiah", "Song of Solomon", "1 Kings", "1 Chronicles", "Proverbs", "2 Samuel"})));
        animalMap.get("Gecko").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Leviticus"})));
        animalMap.get("Gnat").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Matthew", "Isaiah", "Psalm", "Exodus"})));
        animalMap.get("Goat").setBooks(new ArrayList<>(Arrays.asList(new String[] {"2 Chronicles", "Psalm", "1 Samuel", "Matthew", "Song of Solomon", "Luke", "Jeremiah", "Judges", "Isaiah", "Amos", "Proverbs", "Numbers", "Exodus", "Hebrews", "Leviticus", "Deuteronomy", "Zechariah", "Job", "1 Kings", "Ezekiel", "Ezra", "Daniel", "Genesis"})));
        animalMap.get("Grasshopper").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Ecclesiastes", "Isaiah", "Numbers", "Nahum", "Leviticus"})));
        animalMap.get("Hare").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "Leviticus"})));
        animalMap.get("Hawk").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "Leviticus", "Job"})));
        animalMap.get("Hedgehog").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Isaiah"})));
        animalMap.get("Heifer").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Hosea", "2 Chronicles", "Psalm", "Micah", "1 Samuel", "Jeremiah", "Judges", "Malachi", "Isaiah", "Amos", "1 Chronicles", "Numbers", "Exodus", "Hebrews", "Leviticus", "Job", "Proverbs", "1 Kings", "Acts", "Ezekiel", "Ezra", "Genesis"})));
        animalMap.get("Hen").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Matthew", "Luke", "Zechariah"})));
        animalMap.get("Heron").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "Leviticus"})));
        animalMap.get("Hippopotamus").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Job"})));
        animalMap.get("Hoopoe").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "Leviticus"})));
        animalMap.get("Hornet").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "Joshua", "Exodus"})));
        animalMap.get("Horse").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Matthew", "Mark", "Luke", "Job", "Genesis"})));
        animalMap.get("Hyena").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Isaiah", "Jeremiah"})));
        animalMap.get("Ibex").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy"})));
        animalMap.get("Jackal").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Jeremiah", "Psalm", "Malachi", "Micah", "Job", "Isaiah", "Lamentations", "Ezekiel"})));
        animalMap.get("Kid").setBooks(new ArrayList<>(Arrays.asList(new String[] {"2 Chronicles", "Psalm", "1 Samuel", "Matthew", "Song of Solomon", "Luke", "Jeremiah", "Judges", "Isaiah", "Amos", "Proverbs", "Numbers", "Exodus", "Hebrews", "Leviticus", "Deuteronomy", "Zechariah", "Job", "1 Kings", "Ezekiel", "Ezra", "Daniel", "Genesis"})));
        animalMap.get("Kite").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "Leviticus", "Job"})));
        animalMap.get("Lamb").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Leviticus", "Psalm", "Genesis", "Song of Solomon", "Numbers", "2 Samuel"})));
        animalMap.get("Leech").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Proverbs"})));
        animalMap.get("Leopard").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Jeremiah", "Daniel", "Isaiah", "Habakkuk", "Revelation", "Hosea", "Song of Solomon"})));
        animalMap.get("Lion").setBooks(new ArrayList<>(Arrays.asList(new String[] {"2 Chronicles", "Psalm", "Micah", "1 Samuel", "Song of Solomon", "2 Timothy", "Ecclesiastes", "Judges", "Zephaniah", "1 Peter", "Isaiah", "Revelation", "Hosea", "Amos", "Zechariah", "1 Chronicles", "Numbers", "Hebrews", "Jeremiah", "Deuteronomy", "2 Kings", "Job", "2 Samuel", "Nahum", "1 Kings", "Proverbs", "Lamentations", "Ezekiel", "Joel", "Daniel", "Genesis"})));
        animalMap.get("Lizard").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Proverbs", "Leviticus"})));
        animalMap.get("Locust").setBooks(new ArrayList<>(Arrays.asList(new String[] {"2 Chronicles", "Mark", "Joel", "Jeremiah", "Judges", "Malachi", "Deuteronomy", "Psalm", "Nahum", "Revelation", "Isaiah", "Job", "Amos", "Matthew", "Proverbs", "1 Kings", "Exodus", "Leviticus"})));
        animalMap.get("Mare").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Matthew", "Mark", "Luke", "Job", "Genesis"})));
        animalMap.get("Mole").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Isaiah"})));
        animalMap.get("Moth").setBooks(new ArrayList<>(Arrays.asList(new String[] {"2 Chronicles", "1 Kings"})));
        animalMap.get("Mouse").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Leviticus"})));
        animalMap.get("Mule").setBooks(new ArrayList<>(Arrays.asList(new String[] {"2 Chronicles", "Ezra", "2 Kings", "Psalm", "Isaiah", "Zechariah", "2 Samuel", "1 Kings", "1 Chronicles", "Nehemiah", "Ezekiel"})));
        animalMap.get("Ostrich").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Leviticus", "Deuteronomy", "Micah", "Job", "Isaiah", "Lamentations", "Jeremiah"})));
        animalMap.get("Owl").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "Zephaniah", "Psalm", "Leviticus", "Isaiah"})));
        animalMap.get("Ox").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Hosea", "2 Chronicles", "Psalm", "Micah", "1 Samuel", "Jeremiah", "Judges", "Malachi", "Isaiah", "Amos", "1 Chronicles", "Numbers", "Exodus", "Hebrews", "Leviticus", "Job", "Proverbs", "1 Kings", "Acts", "Ezekiel", "Ezra", "Genesis"})));
        animalMap.get("Oxen").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Hosea", "2 Chronicles", "Psalm", "Micah", "1 Samuel", "Jeremiah", "Judges", "Malachi", "Isaiah", "Amos", "1 Chronicles", "Numbers", "Exodus", "Hebrews", "Leviticus", "Job", "Proverbs", "1 Kings", "Acts", "Ezekiel", "Ezra", "Genesis"})));
        animalMap.get("Partridge").setBooks(new ArrayList<>(Arrays.asList(new String[] {"1 Samuel", "Jeremiah"})));
        animalMap.get("Peacock").setBooks(new ArrayList<>(Arrays.asList(new String[] {"2 Chronicles"})));
        animalMap.get("Pig").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Mark", "Leviticus", "Deuteronomy", "Matthew", "Luke", "Proverbs"})));
        animalMap.get("Pigeon").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Genesis", "Numbers", "Luke"})));
        animalMap.get("Quail").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Numbers", "Psalm", "Exodus"})));
        animalMap.get("Ram").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Leviticus", "Psalm", "Genesis", "Song of Solomon", "Numbers", "2 Samuel"})));
        animalMap.get("Raven").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Jeremiah", "Deuteronomy", "Psalm", "Isaiah", "Zephaniah", "Song of Solomon", "Luke", "Matthew", "Proverbs", "Genesis"})));
        animalMap.get("Roebuck").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "1 Kings"})));
        animalMap.get("Scorpion").setBooks(new ArrayList<>(Arrays.asList(new String[] {"2 Chronicles", "Deuteronomy", "Revelation", "Luke", "1 Kings", "Numbers", "Ezekiel"})));
        animalMap.get("Seagull").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "Leviticus"})));
        animalMap.get("Serpent").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Numbers", "2 Kings", "Psalm", "Isaiah", "Micah", "Revelation", "Genesis", "Amos", "Acts", "Matthew", "Proverbs", "2 Corinthians"})));
        animalMap.get("Sheep").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Leviticus", "Psalm", "Genesis", "Song of Solomon", "Numbers", "2 Samuel"})));
        animalMap.get("Snail").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Psalm"})));
        animalMap.get("Snake").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Numbers", "2 Kings", "Psalm", "Isaiah", "Micah", "Revelation", "Genesis", "Amos", "Acts", "Matthew", "Proverbs", "2 Corinthians"})));
        animalMap.get("Sparrow").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Proverbs", "Matthew", "Luke"})));
        animalMap.get("Spider").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Isaiah", "Job"})));
        animalMap.get("Sponge").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Matthew", "John", "Mark"})));
        animalMap.get("Stallion").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Matthew", "Mark", "Luke", "Job", "Genesis"})));
        animalMap.get("Stork").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Leviticus", "Deuteronomy", "Psalm", "Job", "Zechariah", "Jeremiah"})));
        animalMap.get("Swallow").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Jeremiah", "Psalm", "Proverbs"})));
        animalMap.get("Swine").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Mark", "Leviticus", "Deuteronomy", "Matthew", "Luke", "Proverbs"})));
        animalMap.get("Viper").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Deuteronomy", "Psalm", "Isaiah", "Job", "Matthew", "Luke", "Acts", "Genesis"})));
        animalMap.get("Vulture").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Hosea", "Leviticus", "Deuteronomy", "Matthew", "Luke", "Proverbs"})));
        animalMap.get("Weasel").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Leviticus"})));
        animalMap.get("Wolf").setBooks(new ArrayList<>(Arrays.asList(new String[] {"John", "Isaiah", "Genesis", "Jeremiah"})));
        animalMap.get("Worm").setBooks(new ArrayList<>(Arrays.asList(new String[] {"Jonah", "Mark", "Leviticus", "Deuteronomy", "Psalm", "Job", "Isaiah", "Acts", "Exodus"})));
        populateCategoryMap();
    }

    /**
     * Populate the category map
     */
    public void populateCategoryMap() {
        categoryMap = new TreeMap<>();
        TreeMap<String, Animal> mammals = new TreeMap<>();
        TreeMap<String, Animal> reptiles = new TreeMap<>();
        TreeMap<String, Animal> amphibians = new TreeMap<>();
        TreeMap<String, Animal> birds = new TreeMap<>();
        TreeMap<String, Animal> creepyCrawlies = new TreeMap<>();
        TreeMap<String, Animal> fishs = new TreeMap<>();
        TreeMap<String, Animal> others = new TreeMap<>();

        ArrayList<String> keys = this.getKeys(this.animalMap);
        ArrayList<Animal> animals = this.getValues(this.animalMap);
        for(int i = 0; i < animals.size(); i++) {
            String cat = animals.get(i).getCategory();
            if (cat.equals(MAMMAL)) mammals.put(keys.get(i), animals.get(i));
            if (cat.equals(REPTILE)) reptiles.put(keys.get(i), animals.get(i));
            if (cat.equals(AMPHIBIAN)) amphibians.put(keys.get(i), animals.get(i));
            if (cat.equals(BIRD)) birds.put(keys.get(i), animals.get(i));
            if (cat.equals(CREEPY_CRAWLIES)) creepyCrawlies.put(keys.get(i), animals.get(i));
            if (cat.equals(FISH)) fishs.put(keys.get(i), animals.get(i));
            if (cat.equals(OTHER)) others.put(keys.get(i), animals.get(i));
        }

        categoryMap.put(AMPHIBIAN, amphibians);
        categoryMap.put(BIRD, birds);
        categoryMap.put(CREEPY_CRAWLIES, creepyCrawlies);
        categoryMap.put(FISH, fishs);
        categoryMap.put(MAMMAL, mammals);
        categoryMap.put(REPTILE, reptiles);
        categoryMap.put(OTHER, others);
    }

    /**
     * Populate the books map
     */
    public void populateBooksMap() {
        booksMap = new TreeMap<>();
        ArrayList<String> books = booksOfBible.getBooks();
        for(int i = 0; i < books.size(); i++) {
            booksMap.put(books.get(i), new TreeMap<String, Animal>());
        }
        ArrayList<String> keys = this.getKeys(this.animalMap);
        ArrayList<Animal> animals = this.getValues(this.animalMap);
        for(int i = 0; i < animals.size(); i++) {
            for(int j = 0; j < animals.get(i).getBooks().size(); j++) {
                booksMap.get(animals.get(i).getBooks().get(j)).put(keys.get(i), animals.get(i));
            }
        }
    }

    /**
     * Get the list of animals
     * @return a TreeMap with animal names as keys and Animal objects as values
     */
    public TreeMap<String, Animal> get() {
        return animalMap;
    }

    /**
     * Get the list of animal maps in books of Bible form
     * @return a TreeMap with Bible books as keys and a tree map of animal keys as names and
     * animals objects as values (essentially a submap of animalMap)
     */
    public TreeMap<String, TreeMap<String, Animal>> getBooksOfBible() {
        return booksMap;
    }

    /**
     * Get the list of animal maps in category form
     * @return a TreeMap with animal categories as keys and a tree map of animal keys as names and
     * animals objects as values (essentially a submap of animalMap)
     */
    public TreeMap<String, TreeMap<String, Animal>> getCategories() {
        return categoryMap;
    }

    /**
     * Get the list of keys for a TreeMap containing Strings and animals
     * @return an ArrayList of Keys
     */
    public ArrayList<String> getKeys(TreeMap<String, Animal> map) {
        Set<String> set = map.keySet();
        ArrayList<String> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }

    /**
     * Get the list of keys for categories
     * @return an ArrayList of Keys
     */
    public ArrayList<String> getKeysCategories() {
        Set<String> set = categoryMap.keySet();
        ArrayList<String> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }

    /**
     * Get the list of values for a TreeMap containing Strings and animals
     * @return an ArrayList of Values
     */
    public ArrayList<Animal> getValues(TreeMap<String, Animal> map) {
        return new ArrayList<>(map.values());
    }

    private static final String MAMMAL = "Mammals";
    private static final String REPTILE = "Reptiles";
    private static final String AMPHIBIAN = "Amphibians";
    private static final String BIRD = "Birds";
    private static final String CREEPY_CRAWLIES = "Creepy-Crawlies";
    private static final String FISH = "Fish";
    private static final String OTHER = "Other";

}
