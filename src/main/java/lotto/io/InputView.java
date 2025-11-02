package lotto.io;

import camp.nextstep.edu.missionutils.Console;
import lotto.WinningNumbers;
import lotto.exception.ExceptionHandler;
import lotto.exception.ioexception.InvalidNumberException;
import lotto.exception.ioexception.InvalidPurchaseAmountException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private final int amountWon;
    private final ExceptionHandler exceptionHandler;
    static final String defaultDelimiter = ",";

    public InputView(int amountWon) {
        this.amountWon = amountWon;
        this.exceptionHandler = new ExceptionHandler();
    }

    public int readPurchaseAmount() {
        System.out.println("구매 금액을 입력해 주세요.");

        try{
            int purchaseAmount = Integer.parseInt(Console.readLine());
            if (purchaseAmount % amountWon != 0) {
                throw new InvalidPurchaseAmountException(amountWon);
            }

            return purchaseAmount/amountWon;
        }catch(Exception e){
            throw new InvalidPurchaseAmountException(amountWon);
        }
    }

    public List<Integer> readWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");

        String winningLottoInput = Console.readLine();

        if(winningLottoInput.isEmpty() || !winningLottoInput.contains(defaultDelimiter)){
            throw new InvalidNumberException();
        }

        List<String> winningLotto = Arrays.asList(winningLottoInput.split(defaultDelimiter));

        if(winningLotto.size() != 6) {
            throw new InvalidNumberException();
        }

        List<Integer> lottoNumbers = new ArrayList<>();

        for(String winningLottoItem : winningLotto){
            lottoNumbers.add(convertStringToInteger(winningLottoItem));
        }

        return lottoNumbers;
    }

    public int readBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요");
        String bonusLottoInput = Console.readLine();

        if(bonusLottoInput.isEmpty()){
            throw new InvalidNumberException();
        }

        return convertStringToInteger(bonusLottoInput);
    }

    public int convertStringToInteger(String winningLottoItem) {
        try{
            int lottoNumber = Integer.parseInt(winningLottoItem);

            return lottoNumber;
        }catch (Exception e){
            throw new InvalidNumberException();
        }
    }

    public int inputPurchaseAmount() {
        return exceptionHandler.retry(this::readPurchaseAmount);
    }

    public WinningNumbers inputWinningAndBonusNumbers() {
        List<Integer> winningAndBonusNumbers = exceptionHandler.retry(this::readWinningNumbers);

        int bonusNumber = exceptionHandler.retry(this::readBonusNumber);

        return new WinningNumbers(winningAndBonusNumbers, bonusNumber);
    }


}
