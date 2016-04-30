package edu.gordon.cs.bibleanimals;

import java.util.ArrayList;
import java.util.Set;
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
    // and cross referenced with the animals listed here:
    // https://en.wikipedia.org/wiki/List_of_animals_in_the_Bible
    // The animals were also checked to see if they were in the LEB
    // Bible
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
        // This variable is to store animals when they have more than one name
        // So multiple keys in the HashMap will point to the same animal
        Animal tempAnimal;
        animalMap = new TreeMap<String, Animal>();
        animalMap.put("Adder", new Animal(new String[]{"Adder"}, 289378));
        animalMap.put("Ant", new Animal(new String[]{"Ant"}, 699));
        animalMap.put("Ape", new Animal(new String[]{"Ape"}, 1653));
        animalMap.put("Asp", new Animal(new String[]{"Asp"}, 35579));
        animalMap.put("Ass", new Animal(new String[]{"Ass"}, 4472382));
        animalMap.put("Baboon", new Animal(new String[]{"Baboon"}, 15084));
        animalMap.put("Badger", new Animal(new String[]{"Badger"}, 328046));
        animalMap.put("Bat", new Animal(new String[]{"Bat"}, 7631));
        animalMap.put("Bear", new Animal(new String[]{"Bear"}, 7664));
        animalMap.put("Bee", new Animal(new String[]{"Bee"}, 677));
        tempAnimal = new Animal(new String[]{"Bird", "Fowl"}, 695);
        animalMap.put("Bird", tempAnimal);
        animalMap.put("Fowl", tempAnimal);
        tempAnimal = new Animal(new String[]{"Bull", "Calf", "Cattle", "Cow", "Heifer",
                "Ox", "Oxen"}, 328699);
        animalMap.put("Bull", tempAnimal);
        animalMap.put("Calf", tempAnimal);
        animalMap.put("Cattle", tempAnimal);
        animalMap.put("Cow", tempAnimal);
        animalMap.put("Heifer", tempAnimal);
        animalMap.put("Ox", tempAnimal);
        animalMap.put("Oxen", tempAnimal);
        animalMap.put("Camel", new Animal(new String[]{"Camel"}, 309019));
        tempAnimal = new Animal(new String[]{"Caterpillar", "Moth"}, 747);
        animalMap.put("Caterpillar", tempAnimal);
        animalMap.put("Moth", tempAnimal);
        animalMap.put("Chameleon", new Animal(new String[]{"Chameleon"}, 1723));
        tempAnimal = new Animal(new String[]{"Coney", "Hare"}, 1689);
        animalMap.put("Coney", tempAnimal);
        animalMap.put("Hare", tempAnimal);
        tempAnimal = new Animal(new String[]{"Colt", "Horse", "Mare", "Stallion"}, 328648);
        animalMap.put("Colt", tempAnimal);
        animalMap.put("Horse", tempAnimal);
        animalMap.put("Mare", tempAnimal);
        animalMap.put("Stallion", tempAnimal);
        animalMap.put("Coral", new Animal(new String[]{"Coral"}, 1746));
        animalMap.put("Cormorant", new Animal(new String[]{"Cormorant"}, 7996));
        animalMap.put("Crane", new Animal(new String[]{"Crane"}, 7586));
        animalMap.put("Cricket", new Animal(new String[]{"Cricket"}, 996));
        animalMap.put("Crocodile", new Animal(new String[]{"Crocodile"}, 1739));
        tempAnimal = new Animal(new String[]{"Deer", "Fawn"}, 7685);
        animalMap.put("Deer", tempAnimal);
        animalMap.put("Fawn", tempAnimal);
        animalMap.put("Dog", new Animal(new String[]{"Dog"}, 1228387));
        animalMap.put("Donkey", new Animal(new String[]{"Donkey"}, 328647));
        animalMap.put("Dove", new Animal(new String[]{"Dove"}, 20038));
        animalMap.put("Eagle", new Animal(new String[]{"Eagle"}, 1049119));
        tempAnimal = new Animal(new String[]{"Ewe", "Lamb", "Ram", "Sheep"}, 311906);
        animalMap.put("Ewe", tempAnimal);
        animalMap.put("Lamb", tempAnimal);
        animalMap.put("Ram", tempAnimal);
        animalMap.put("Sheep", tempAnimal);
        animalMap.put("Falcon", new Animal(new String[]{"Falcon"}, 19166));
        animalMap.put("Fish", new Animal(new String[]{"Fish"}, 1905));
        animalMap.put("Flea", new Animal(new String[]{"Flea"}, 1062));
        animalMap.put("Fly", new Animal(new String[]{"Fly"}, 421));
        animalMap.put("Fox", new Animal(new String[]{"Fox"}, 19076));
        animalMap.put("Frog", new Animal(new String[]{"Frog"}, 1553));
        animalMap.put("Gazelle", new Animal(new String[]{"Gazelle"}, 15584));
        animalMap.put("Gecko", new Animal(new String[]{"Gecko"}, 1715));
        animalMap.put("Gnat", new Animal(new String[]{"Gnat"}, 740671));
        tempAnimal = new Animal(new String[]{"Goat", "Kid"}, 328660);
        animalMap.put("Goat", tempAnimal);
        animalMap.put("Kid", tempAnimal);
        animalMap.put("Grasshopper", new Animal(new String[]{"Grasshopper"}, 6580));
        animalMap.put("Hawk", new Animal(new String[]{"Hawk"}, 8016));
        animalMap.put("Hedgehog", new Animal(new String[]{"Hedgehog"}, 1693));
        animalMap.put("Hen", new Animal(new String[]{"Hen", "Rooster"}, 1049263));
        animalMap.put("Heron", new Animal(new String[]{"Heron"}, 8013));
        animalMap.put("Hippopotamus", new Animal(new String[]{"Hippopotamus"}, 311532));
        animalMap.put("Hoopoe", new Animal(new String[]{"Hoopoe"}, 8676));
        animalMap.put("Hornet", new Animal(new String[]{"Hornet"}, 5242));
        animalMap.put("Hyena", new Animal(new String[]{"Hyena"}, 311570));
        animalMap.put("Ibex", new Animal(new String[]{"Ibex"}, 328692));
        animalMap.put("Jackal", new Animal(new String[]{"Jackal"}, 328681));
        animalMap.put("Kite", new Animal(new String[]{"Kite"}, 92632));
        animalMap.put("Leech", new Animal(new String[]{"Leech"}, 2565059));
        animalMap.put("Leopard", new Animal(new String[]{"Leopard"}, 328673));
        animalMap.put("Lion", new Animal(new String[]{"Lion"}, 328672));
        animalMap.put("Lizard", new Animal(new String[]{"Lizard"}, 1704));
        animalMap.put("Locust", new Animal(new String[]{"Locust"}, 494417));
        animalMap.put("Mole", new Animal(new String[]{"Mole"}, 8715));
        animalMap.put("Mouse", new Animal(new String[]{"Mouse"}, 8698));
        animalMap.put("Mule", new Animal(new String[]{"Mule"}, 40106253));
        animalMap.put("Ostrich", new Animal(new String[]{"Ostrich"}, 1178371));
        animalMap.put("Owl", new Animal(new String[]{"Owl"}, 696));
        animalMap.put("Partridge", new Animal(new String[]{"Partridge"}, 7591));
        animalMap.put("Peacock", new Animal(new String[]{"Peacock"}, 18620));
        animalMap.put("Pigeon", new Animal(new String[]{"Pigeon"}, 7978));
        tempAnimal = new Animal(new String[]{"Pig", "Swine"}, 4445650);
        animalMap.put("Pig", tempAnimal);
        animalMap.put("Swine", tempAnimal);
        animalMap.put("Quail", new Animal(new String[]{"Quail"}, 914847));
        animalMap.put("Raven", new Animal(new String[]{"Raven"}, 33750));
        animalMap.put("Roebuck", new Animal(new String[]{"Roebuck"}, 308479));
        animalMap.put("Scorpion", new Animal(new String[]{"Scorpion"}, 8542));
        animalMap.put("Seagull", new Animal(new String[]{"Seagull"}, 8001));
        tempAnimal = new Animal(new String[]{"Serpent", "Snake"}, 2815988);
        animalMap.put("Serpent", tempAnimal);
        animalMap.put("Snake", tempAnimal);
        animalMap.put("Snail", new Animal(new String[]{"Snail"}, 2366));
        animalMap.put("Sparrow", new Animal(new String[]{"Sparrow"}, 922241));
        animalMap.put("Spider", new Animal(new String[]{"Spider"}, 166));
        animalMap.put("Sponge", new Animal(new String[]{"Sponge"}, 3142));
        animalMap.put("Stork", new Animal(new String[]{"Stork"}, 7981));
        animalMap.put("Swallow", new Animal(new String[]{"Swallow"}, 7544));
        animalMap.put("Viper", new Animal(new String[]{"Viper"}, 8116));
        animalMap.put("Vulture", new Animal(new String[]{"Vulture"}, 914578));
        animalMap.put("Weasel", new Animal(new String[]{"Weasel"}, 14116));
        animalMap.put("Wolf", new Animal(new String[]{"Wolf"}, 328607));
        animalMap.put("Worm", new Animal(new String[]{"Worm"}, 36));
    }

    /**
     * Get the list of animals
     * @return a TreeMap with animal names as keys and Animal objects as values
     */
    public TreeMap<String, Animal> get() {
        return animalMap;
    }

    /**
     * Get the list of keys
     * @return an ArrayList of Keys
     */
    public ArrayList<String> getKeys() {
        Set set = animalMap.keySet();
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(set);
        return list;
    }

    /**
     * Get the list of values
     * @return an ArrayList of Values
     */
    public ArrayList<Animal> getValues() {
        return new ArrayList<Animal>(animalMap.values());
    }

    /**
     * Get the terms for all the animals - one string per animal
     * @param separator the separator between animal's different names
     * @return the terms
     */
    public ArrayList<String> getTermsStrings(String separator) {
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayList<Animal> animals = this.getValues();
        for(int i = 0; i < animals.size(); i++) {
            arrayList.add(animals.get(i).getTermsString(separator));
        }
        return arrayList;
    }
}
