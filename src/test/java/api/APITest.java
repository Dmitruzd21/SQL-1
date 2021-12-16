package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class APITest {


    @Test
    public void shouldTransferMoneyFrom2To1CardInRightWay() {
        APImethods apiMethods = new APImethods();
        apiMethods.login();
        apiMethods.verifyAndExtractToken();
        int initialBalanceOfCard1 = apiMethods.extractCardBalance(1);
        int initialBalanceOfCard2 = apiMethods.extractCardBalance(2);
        apiMethods.transferMoneyFrom2To1Card();
        int actualFinalBalanceOfCard1 = apiMethods.extractCardBalance(1);
        int actualFinalBalanceOfCard2 = apiMethods.extractCardBalance(2);
        int expectedFinalBalanceOfCard1 = initialBalanceOfCard1 - Integer.parseInt(DataForAPI.getTransferInfo().getAmount());
        int expectedFinalBalanceOfCard2 = initialBalanceOfCard2 + Integer.parseInt(DataForAPI.getTransferInfo().getAmount());
        assertEquals(expectedFinalBalanceOfCard1, actualFinalBalanceOfCard1);
        assertEquals(expectedFinalBalanceOfCard2, actualFinalBalanceOfCard2);
    }

}
