package api;

import SQL.SQLMethodsWithDbUtils;
import lombok.Value;

public class DataForAPI {

    @Value
    public static class LoginInfo {
        private String login;
        private String password;
    }

    public static LoginInfo getLoginInfo() {
        return new LoginInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationInfo {
        private String login;
        private String code;
    }

    public static VerificationInfo getVerificationInfo() {
        return new VerificationInfo("vasya", SQLMethodsWithDbUtils.getVerificationCodeFor());
    }

    @Value
    public static class TransferInfo {
        private String from;
        private String to;
        private String amount;
    }

    public static TransferInfo getTransferInfo() {
        return new TransferInfo("5559 0000 0000 0002", "5559 0000 0000 0001", "5000");
    }
}
