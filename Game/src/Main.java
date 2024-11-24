// Импортирование необходимых библиотек
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.String;
import java.util.Random;


// Создание класса Main
public class Main{


    // Метод проведения дуэли
    public static GameUnit battle(GameUnit unit1, GameUnit unit2){
        if (unit1.getClass() == unit2.getClass()){
            System.out.printf("Они оба из одной и той же семьи... " +
                    "Выигрывает %s (Он заплатил).\n\n", unit2.getName());
            return unit2;
        }
        int healPointsOfUnit1 = unit1.getHealPoints();
        int healPointsOfUnit2 = unit2.getHealPoints();
        Random random = new Random();
        GameUnit whoHasWon = unit1;
        while (healPointsOfUnit1 > 0 && healPointsOfUnit2 > 0){
            unit1.looseHealPoints(GameUnit.attack(unit1, unit2), unit1, unit2);
            if (unit1.getHealPoints() <= 0){
                whoHasWon = unit2;
                break;
            }
            unit2.looseHealPoints(GameUnit.attack(unit2, unit1), unit2, unit1);
            if (unit2.getHealPoints() <= 0){
                break;
            }
            TypeUnit.heal(unit1);
            TypeUnit.heal(unit2);
        }
        System.out.printf("\nВ этом раунде выиграл %s\n\n\n", whoHasWon.getName());
        return whoHasWon;
    }


    // Метод main. Именно здесь и запускается программа от Вашего лица. Чтобы проверить мой проект, уберите "\*" и "*/",
    // а затем заполните параметры всех GameUnit'ов по следующему шаблону:
    // 1 - "fire", "water", "wind" или "ground". Это определит, к какому классу будет принадлежать GameUnit.
    // 2 - Любое имя.
    // 3 - Показатель силы. Любое положительное целое число.
    // 4 - Показатель очков здоровья. Любое положительное целое число.
    // 5 - Показатель веса. Любое положительное целое число.
    // 6 - Показатель роста. Любое положительное целое число.
    // 7 - TypeUnit.Healer, TypeUnit.Tank, TypeUnit.Flang, или TypeUnit.DamageDealer; это определит тип GameUnit'а
    // Ниже я "заполнил" всех GameUnit'ов, чтобы Вам было более понятно, что я имел в виду. Надеюсь, вам понравится. :D
    // (К слову, выздоравливайте. :D)
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        GameUnit unit1 = GameUnit.createAUnit("fire", "Bob", 30, 1000, 60, 150, TypeUnit.Healer);
        GameUnit unit2 = GameUnit.createAUnit("water", "Oleg", 35, 1200, 65, 160, TypeUnit.Flang);
        GameUnit unit3 = GameUnit.createAUnit("ground", "Vasya", 30, 1100, 70, 155, TypeUnit.Tank);
        GameUnit unit4 = GameUnit.createAUnit("wind", "Kolya", 25, 900, 55, 170, TypeUnit.DamageDealer);
        GameUnit unit5 = GameUnit.createAUnit("fire", "Svyatoslav", 999, 10000, 91, 179, TypeUnit.Tank);
        GameUnit unit6 = GameUnit.createAUnit("water", "Artyom", 40, 1000, 70, 175, TypeUnit.Flang);
        GameUnit unit7 = GameUnit.createAUnit("wind", "Boris", 35, 1100, 80, 160, TypeUnit.Healer);
        GameUnit unit8 = GameUnit.createAUnit("ground", "Bendy", 30, 1000, 75, 210, TypeUnit.DamageDealer);

        GameUnit winnerOfTheFirstRound1 = battle(unit1, unit2);
        GameUnit winnerOfTheFirstRound2 = battle(unit3, unit4);
        GameUnit winnerOfTheFirstRound3 = battle(unit5, unit6);
        GameUnit winnerOfTheFirstRound4 = battle(unit7, unit8);

        GameUnit winnerOfTheSecondRound1 = battle(winnerOfTheFirstRound1, winnerOfTheFirstRound2);
        GameUnit winnerOfTheSecondRound2 = battle(winnerOfTheFirstRound3, winnerOfTheFirstRound4);

        GameUnit winnerOfTheGame = battle(winnerOfTheSecondRound1, winnerOfTheSecondRound2);

        System.out.printf("\n\n\nПо результатам турнира выиграл %s!", winnerOfTheGame.getName());

    }


}