import java.util.HashMap;
import java.util.Random;
/**
 * В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла
 * и наглядно убедиться в верности парадокса
 * (запустить игру в цикле на 1000 и вывести итоговый счет).
 * Необходимо:
 * Создать свой Java Maven или Gradle проект;
 * Подключить зависимость lombok.
 * Можно подумать о подключении какой-нибудь математической библиотеки для работы со статистикой
 * Самостоятельно реализовать прикладную задачу;
 * Сохранить результат в HashMap<Integer, Boolean>
 * Вывести на экран статистику по победам и поражениям
 */
public class Main {
    static final int numberOfIterations = 1000;
    static final int doorsNumber = 3;
    static HashMap<Integer, Boolean> resultsWithSameDoor;
    static HashMap<Integer, Boolean> resultsWithOtherDoor;
    public static void main(String[] args) {
        Game game = new Game(doorsNumber);
        resultsWithSameDoor = game.start(false);
        resultsWithOtherDoor = game.start(true);
        System.out.println("Статистика при отказе от изменения двери:");
        System.out.println("Доля выигрыша: " + game.calcTruePercent(resultsWithSameDoor) + "%");
        System.out.println("Статистика при изменении двери:");
        System.out.println("Доля выигрыша: " + game.calcTruePercent(resultsWithOtherDoor) + "%");
    }
}

class Game {
    Random random;
    int doorsNumber;

    public Game(int doorsNumber) {
        this.random = new Random();
        this.doorsNumber = doorsNumber;
    }

    /** Метод start() принимает выбор игрока либо всегда открывать первоначальную дверь,
    * либо всегда открывать дверь предложенную ведущим. Во втором случае ведущий удаляет
    * все двери с козами кроме одной (если игрок в первом выборе угадал дверь с автомобилем)
    * и предлагает игроку открыть эту дверь (или дверь с автомобилем, если в первом случае
    * игрок не угадал). Дальше, на основе большого количества попыток (numberOfIterations)
    * накапливвается статистика побед и поражений, которая возвращается
    * в виде HashMap<Integer, Boolean>
     */
    HashMap<Integer, Boolean> start(boolean changeDoor) {
        HashMap<Integer, Boolean> result = new HashMap<>();
        for (int i = 0; i < Main.numberOfIterations; i++) {
            int win = random.nextInt(this.doorsNumber);
            int playerFirstChoice = random.nextInt(this.doorsNumber);
            if (!changeDoor) result.put(i, playerFirstChoice == win);
            else result.put(i, win != playerFirstChoice);
        }
        return result;
    }

    /** Метод calcTruePercent() принимает HashMap<Integer, Boolean> и вычисляет долю
    * значанией true, помноженную на 100 (что-то похожее на %) от общего количества пар.
     * */
    Integer calcTruePercent(HashMap<Integer, Boolean> resultMap) {
        int winStat = 0;
        for (HashMap.Entry<Integer, Boolean> entry : resultMap.entrySet()) {
            if (entry.getValue()) {
                winStat++;
            }
        }
        return winStat * 100 / resultMap.size();
    }
}
