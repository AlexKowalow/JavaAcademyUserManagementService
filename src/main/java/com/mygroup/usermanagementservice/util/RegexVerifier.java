package com.mygroup.usermanagementservice.util;

import java.util.regex.Pattern;

public class RegexVerifier {
    public static boolean verifyArgument(String dataToVerify, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(dataToVerify).matches();
    }
}
