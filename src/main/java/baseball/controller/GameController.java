package baseball.controller;

import baseball.model.ComputerNumber;
import baseball.model.UserNumber;
import baseball.service.GameResultService;
import baseball.model.PlayAgainInput;
import baseball.view.InputView;
import baseball.view.OutputView;

import java.util.List;

public class GameController {
    public static GameController instance = new GameController();

    private GameController() {
    }

    public static GameController getInstance() {
        return instance;
    }

    public void run() {
        boolean continuePlaying = true;
        while (continuePlaying) {
            continuePlaying = play();
        }
    }

    private boolean play() {
        OutputView.printStart();
        ComputerNumber computerNumber = createComputerNumber();

        boolean needsNextRound = true;
        while (needsNextRound) {
            needsNextRound = playRound(computerNumber);
        }
        return doRestart();
    }

    private ComputerNumber createComputerNumber() {
        return ComputerNumber.create();
    }

    private boolean playRound(ComputerNumber computerNumber) {
        UserNumber userNumber = readUserNumber();
        GameResultService gameResultService = new GameResultService(computerNumber, userNumber);
        OutputView.printResult(gameResultService.isNothing(), gameResultService.getBallCount(), gameResultService.getStrikeCount());
        return !gameResultService.isThreeStrike();
    }

    private static UserNumber readUserNumber() {
        List<Integer> numbers = InputView.readUserNumber();
        return UserNumber.from(numbers);
    }

    private static boolean doRestart() {
        OutputView.printEnd();
        String input = InputView.readPlayAgainInput();
        PlayAgainInput playAgainInput = new PlayAgainInput(input);
        return playAgainInput.getValue() == 1;
    }
}
