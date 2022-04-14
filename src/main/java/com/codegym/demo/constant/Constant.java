package com.codegym.demo.constant;

public class Constant {
    public static String IMAGE_USER_DEFAULT = "https://firebasestorage.googleapis.com/v0/b/zingmp3-project.appspot.com/o/user_default.png?alt=media&token=ed94ea59-cd93-4171-8aa7-bf06227fa016";
    public static String IMAGE_COMPANY_DEFAULT = "https://firebasestorage.googleapis.com/v0/b/zingmp3-project.appspot.com/o/user_default.png?alt=media&token=ed94ea59-cd93-4171-8aa7-bf06227fa016";

    public enum UserStatus {
        ACTIVATE,
        INACTIVATE
    }
    public enum MerchantStatus {
        ACTIVATE,
        INACTIVATE
    }

    public enum FoodStatus {
        SERVING,
        STOP,
    }

    public enum TypeName {
        ADMIN,
        COMPANY,
        USER,
    }
}
