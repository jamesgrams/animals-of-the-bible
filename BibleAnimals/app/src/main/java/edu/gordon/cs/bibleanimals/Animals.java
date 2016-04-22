package edu.gordon.cs.bibleanimals;

import java.util.TreeMap;

/**
 * Created by James on 4/13/2016.
 * Collection of animals in the Bible
 * This class follows the Singleton pattern
 */
public class Animals {
    // This map contains a key (animal name) and an int
    // for the animals id on the Encyclopedia of Life
    // The list of animals in the Bible came from here:
    // http://www.christiananswers.net/dictionary/animals.html
    public static Animals instance = null;
    private TreeMap<String, Animal> animalMap;

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
        animalMap = new TreeMap<String, Animal>();
        animalMap.put("Adder", new Animal(new String[]{"Adder"}, 289378, "white", "black"));
        animalMap.put("Ant", new Animal(new String[]{"Ant"}, 699, "white", "black"));
        animalMap.put("Ape", new Animal(new String[]{"Ape"}, 1653, "white", "black"));
        animalMap.put("Asp", new Animal(new String[]{"Asp"}, 35579, "white", "black"));
        animalMap.put("Ass", new Animal(new String[]{"Ass"}, 4472382, "white", "black"));
        animalMap.put("Baboon", new Animal(new String[]{"Baboon"}, 15084, "white", "black"));
        animalMap.put("Badger", new Animal(new String[]{"Badger"}, 328046, "white", "black"));
        animalMap.put("Bat", new Animal(new String[]{"Bat"}, 7631, "white", "black"));
        animalMap.put("Bear", new Animal(new String[]{"Bear"}, 7664, "white", "black"));
        animalMap.put("Bee", new Animal(new String[]{"Bee"}, 677, "white", "black"));
        animalMap.put("Bird", new Animal(new String[]{"Bird", "Fowl"}, 695, "white", "black"));
        animalMap.put("Bull", new Animal(new String[]{"Bull", "Cow", "Calf", "Cattle"}, 328699, "white", "black"));
        animalMap.put("Camel", new Animal(new String[]{"Camel"}, 309019, "white", "black"));
        animalMap.put("Caterpillar", new Animal(new String[]{"Caterpillar"}, 747, "white", "black"));
        animalMap.put("Chameleon", new Animal(new String[]{"Chameleon"}, 1723, "white", "black"));
        animalMap.put("Coney", new Animal(new String[]{"Coney", "Hare"}, 1689, "white", "black"));
        animalMap.put("Colt", new Animal(new String[]{"Colt", "Horse"}, 328648, "white", "black"));
        animalMap.put("Coral", new Animal(new String[]{"Coral"}, 1746, "white", "black"));
        animalMap.put("Cormorant", new Animal(new String[]{"Cormorant"}, 7996, "white", "black"));
        animalMap.put("Crane", new Animal(new String[]{"Crane"}, 7586, "white", "black"));
        animalMap.put("Cricket", new Animal(new String[]{"Cricket"}, 996, "white", "black"));
        animalMap.put("Crocodile", new Animal(new String[]{"Crocodile"}, 1739, "white", "black"));
        animalMap.put("Deer", new Animal(new String[]{"Deer", "Fawn"}, 7685, "white", "black"));
        animalMap.put("Dog", new Animal(new String[]{"Dog"}, 1228387, "white", "black"));
        animalMap.put("Donkey", new Animal(new String[]{"Donkey"}, 328647, "white", "black"));
        animalMap.put("Dove", new Animal(new String[]{"Dove"}, 20038, "white", "black"));
        animalMap.put("Eagle", new Animal(new String[]{"Eagle"}, 1049119, "white", "black"));
        animalMap.put("Ewe", new Animal(new String[]{"Ewe", "Sheep", "Lamb"}, 311906, "white", "black"));
        animalMap.put("Falcon", new Animal(new String[]{"Falcon"}, 19166, "white", "black"));
        animalMap.put("Fish", new Animal(new String[]{"Fish"}, 1905, "white", "black"));
        animalMap.put("Flea", new Animal(new String[]{"Flea"}, 1062, "white", "black"));
        animalMap.put("Fly", new Animal(new String[]{"Fly"}, 421, "white", "black"));
        animalMap.put("Fox", new Animal(new String[]{"Fox"}, 19076, "white", "black"));
        animalMap.put("Frog", new Animal(new String[]{"Frog"}, 1553, "white", "black"));
        animalMap.put("Gazelle", new Animal(new String[]{"Gazelle"}, 15584, "white", "black"));
        //animalMap.put("Gecko", new Animal(new String[]{"Gecko"}, 15584, "white", "black"));


        animalMap.put("Locust", new Animal(new String[]{"Locust"}, 494417, "white", "black"));
    }

    /**
     * Get the list of animals
     * @return a TreeMap with animal names as keys and Animal objects as values
     */
    public TreeMap<String, Animal> get() {
        return animalMap;
    }
}
