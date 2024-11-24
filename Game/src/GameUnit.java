// Импорт необходимых для работы программы библиотек
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.lang.String;
public class GameUnit {


    // Создание Scanner-объекта
    Scanner in = new Scanner(System.in);


    // Поля класса
    private String name;
    private int strength;
    private int healPoints;
    private int weight;
    private int height;
    private TypeUnit type;


    // Геттер и сеттер имени
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }


    // Геттер и сеттер силы
    public void setStrength(int strength){
        this.strength = strength;
    }
    public int getStrength(){
        return strength;
    }


    // Геттер и сеттер уровня здоровья
    public void setHealPoints(int healPoints){
        this.healPoints = healPoints;
    }
    public int getHealPoints() {
        return healPoints;
    }


    // Геттер и сеттер веса
    public void setWeight(int weight){
        this.weight = weight;
    }
    public int getWeight(){
        return weight;
    }


    // Геттер и сеттер роста
    public void setHeight(int height){
        this.height = height;
    }
    public int getHeight(){
        return height;
    }


    // Геттер и сеттер типа
    public void setType(String type){
        this.type = TypeUnit.defenitionOfType(type);
    }
    public TypeUnit getType(){
        return type;
    }


    // Конструктор класса
    public GameUnit(String name, int strength, int healPoints, int weight, int height, TypeUnit type){
        this.name = name;
        this.strength = strength;
        this.weight = weight;
        this.height = height;
        this.type = type;
        if (type == TypeUnit.Tank){
            healPoints += 600;
        }
        this.healPoints = healPoints;
    }


    // Метод определения урона (unit1 наносит урон по персонажу unit2)
    public static int attack(GameUnit unit1, GameUnit unit2){
        Random random = new Random();
        double percentage = random.nextDouble();
        int attackValue = unit1.getStrength() + (percentage <= TypeUnit.getProbability(unit1.getType()) ? TypeUnit.additionalDamage(unit1) : 0);
        Object objectValueOfUnit1 = unit1;
        String classValueOfUnit1 = objectValueOfUnit1.getClass().getName();
        Object objectValueOfUnit2 = unit2;
        String classValueOfUnit2 = objectValueOfUnit2.getClass().getName();
        switch (classValueOfUnit1) {
            case "FireUnit":
                switch (classValueOfUnit2){
                    case "FireUnit" -> attackValue *= 0;
                    case "WaterUnit" -> attackValue *= 1;
                    case "WindUnit" -> attackValue *= 3;
                    case "GroundUnit" -> attackValue *= 2;
                };
            case "WaterUnit":
                switch (classValueOfUnit2){
                    case "FireUnit" -> attackValue *= 4;
                    case "WaterUnit" -> attackValue *= 0;
                    case "WindUnit" -> attackValue *= 1;
                    case "GroundUnit" -> attackValue *= 2;
                };
            case "WindUnit":
                switch (classValueOfUnit2){
                    case "FireUnit", "WaterUnit", "GroundUnit" -> attackValue *= 3;
                    case "WindUnit" -> attackValue *= 0;
                };
            case "GroundUnit":
                switch (classValueOfUnit2){
                    case "FireUnit" -> attackValue *= 0.5;
                    case "WaterUnit" -> attackValue *= 1;
                    case "WindUnit" -> attackValue *= 5;
                    case "GroundUnit" -> attackValue *= 0;
                };
        }
        return attackValue;
    }


    // Метод нанесения урона (unit1 наносит урон по персонажу unit2)
    public void looseHealPoints(int attack, GameUnit unit1, GameUnit unit2){
        int looseHealPointsValue = unit2.getHealPoints() - attack;
        if (unit1.getType() == TypeUnit.Flang && unit2.getType() == TypeUnit.Healer){
            looseHealPointsValue = 2*looseHealPointsValue;
        }
        unit2.setHealPoints(looseHealPointsValue);
        if (unit1.getType() == TypeUnit.Flang){
            sayPhrase(unit1, unit2);
        }
    }


    // Метод создания персонажа
    public static GameUnit createAUnit(String familyOfUnit, String name,
                                int strength, int healPoints, int weight, int height, TypeUnit type){
        GameUnit returnValue;
        switch (familyOfUnit.toLowerCase()){
            case "fire" -> returnValue = new FireUnit(name, strength, healPoints, weight, height, type);
            case "water" -> returnValue = new WaterUnit(name, strength, healPoints, weight, height, type);
            case "ground" -> returnValue = new GroundUnit(name, strength, healPoints, weight, height, type);
            case "wind" -> returnValue = new WindUnit(name, strength, healPoints, weight, height, type);
            default -> returnValue = new GameUnit("", 0,
                    0, 0, 0, TypeUnit.Healer);
        }
        return returnValue;
    }


    //Метод произнесения фразы
    public void sayPhrase(GameUnit unit1, GameUnit unit2){
        Random random = new Random();
        String[] listOfPhrases = {"Тебе не победить!",
                "Сдавайся!",
                String.format("%s... Хах, какое смешное имя!", unit2.getName()),
                "Ты жалок, сдайся наконец!",
                String.format("%s выиграет у тебя любой ценой!", unit1.getName()),
                "Слабак, не получится!"};
        System.out.printf("%s: \"%s\"\n", unit1.getName(), listOfPhrases[random.nextInt(listOfPhrases.length)]);
    }


}