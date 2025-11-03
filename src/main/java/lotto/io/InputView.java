package lotto.io;

import camp.nextstep.edu.missionutils.Console;
import lotto.WinningNumbersAndBonusNumber;
import lotto.exception.ExceptionHandler;
import lotto.exception.ioexception.DuplicateNumberException;
import lotto.exception.ioexception.InvalidNumberRangeException;
import lotto.exception.ioexception.InvalidNumberTypeException;
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
        }catch(IllegalArgumentException e){
            throw new InvalidPurchaseAmountException(amountWon);
        }
    }

    public List<Integer> readWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");

        String winningLottoInput = Console.readLine();

        if(winningLottoInput.isEmpty() || !winningLottoInput.contains(defaultDelimiter)){
            throw new InvalidNumberTypeException();
        }

        List<String> winningLotto = Arrays.asList(winningLottoInput.split(defaultDelimiter));

        if(winningLotto.size() != 6) {
            throw new InvalidNumberTypeException();
        }

        List<Integer> lottoNumbers = new ArrayList<>();

        for(String winningLottoItem : winningLotto){
            lottoNumbers.add(convertAndValidate(winningLottoItem));
        }

        long duplicateCount = lottoNumbers.stream().distinct().count();
        if(duplicateCount != lottoNumbers.size()){
            throw new DuplicateNumberException();
        }

        return lottoNumbers;
    }

    public int readBonusNumber(List<Integer> winningNumbers) {
        System.out.println("보너스 번호를 입력해 주세요");
        String bonusLottoInput = Console.readLine();

        if(bonusLottoInput.isEmpty()){
            throw new InvalidNumberTypeException();
        }

        int bonusNumber = convertAndValidate(bonusLottoInput);

        if (winningNumbers.contains(bonusNumber)) {
            throw new DuplicateNumberException();
        }

        return bonusNumber;
    }

    public int convertAndValidate(String winningLottoItem) {
        try{
            int lottoNumber = Integer.parseInt(winningLottoItem);

            if(lottoNumber > 45 || lottoNumber < 1){
                throw new InvalidNumberRangeException();
            }
            return lottoNumber;

        }catch (NumberFormatException e){
            throw new InvalidNumberTypeException();
        }
    }

    public int inputPurchaseAmount() {
        return exceptionHandler.retry(this::readPurchaseAmount);
    }

    public WinningNumbersAndBonusNumber inputWinningAndBonusNumbers() {
        List<Integer> winningAndBonusNumbers = exceptionHandler.retry(this::readWinningNumbers);

        int bonusNumber = exceptionHandler.retry(() -> readBonusNumber(winningAndBonusNumbers));

        return new WinningNumbersAndBonusNumber(winningAndBonusNumbers, bonusNumber);
    }


}
