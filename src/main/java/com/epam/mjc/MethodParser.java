package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {

        String newStr = signatureString.substring(0, signatureString.indexOf("("));

        StringTokenizer st = new StringTokenizer(newStr, " ");
        int countOfTokens = st.countTokens();

        String accessModifier = "";
        if (countOfTokens == 3) {
            accessModifier = st.nextToken();
        }

        String returnType = st.nextToken();
        String methodName = st.nextToken();

        String argsStr = signatureString.substring(signatureString.indexOf("(") + 1, signatureString.length() - 1);
        StringTokenizer st1 = new StringTokenizer(argsStr, ",");

        List<MethodSignature.Argument> listOfArgs = new ArrayList<>();
        while (st1.hasMoreTokens()) {
            StringTokenizer st2 = new StringTokenizer(st1.nextToken());
            listOfArgs.add(new MethodSignature.Argument(st2.nextToken(), st2.nextToken()));
        }

        MethodSignature mp = new MethodSignature(methodName, listOfArgs);

        mp.setReturnType(returnType);
        if (countOfTokens == 3) mp.setAccessModifier(accessModifier);

        return mp;
    }
}
