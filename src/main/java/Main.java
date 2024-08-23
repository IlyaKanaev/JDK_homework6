import java.util.HashMap;
import java.util.Random;

public class Main {
    static final int numberOfIterations = 1000;
    static final int doorsNumber = 3;

    public static void main(String[] args) {
        System.out.println("Статистика при отказе от изменения двери:");
        System.out.println("Доля выигрыша: " + calcTruePercent(gameStart(false)) + "%");
        System.out.println("Статистика при изменении двери:");
        System.out.println("Доля выигрыша: " + calcTruePercent(gameStart(true)) + "%");
    }

    static HashMap<Integer, Boolean> gameStart(boolean changeDoor) {
        HashMap<Integer, Boolean> result = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i < Main.numberOfIterations; i++) {
            int win = random.nextInt(doorsNumber);
            int playerFirstChoice = random.nextInt(doorsNumber);
            if (!changeDoor) result.put(i, playerFirstChoice == win);
            else result.put(i, win != playerFirstChoice);
        }
        return result;
    }

    static Integer calcTruePercent(HashMap<Integer, Boolean> resultMap) {
        int winStat = 0;
        for (HashMap.Entry<Integer, Boolean> entry : resultMap.entrySet()) {
            if (entry.getValue()) {
                winStat++;
            }
        }
        return winStat * 100 / resultMap.size();
    }
}
