package baseball;

import baseball.controller.GameController;
import baseball.view.OutputView;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        GameController gameController = GameController.getInstance();
        gameController.run();
        Console.close();
    }
}
