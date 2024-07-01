package org.koreait;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private String actionMethod;
    private Map<String, String> params;
    private String errMsg = "";

    @Override
    public String toString() {
        return "Rq{" +
                "actionMethod='" + actionMethod + '\'' +
                ", params=" + params +
                '}';
    }

    //Rq == Request
    public Rq(String cmd) {
        // parsing
        String[] cmdBits = cmd.split("\\?", 2);

        actionMethod = cmdBits[0];

        params = new HashMap<>();

        if (cmdBits.length == 1) {
            return;
        }

        String[] paramBits;

        try {
            paramBits = cmdBits[1].split("&");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("명령어 확인해");
            return;
        }

        for (String paramStr : paramBits) {
            String[] paramStrBits = paramStr.split("=", 2);

            String key = paramStrBits[0];
            if (key.equals("id") == false) {
                System.out.println("오타 있음(id)");
                errMsg = "오타 있음(id)";

                // 명령어를 작성할 때 오류 발생시 걸러내기 위해 errMsg String을 위에 만들고 get으로 만들어
                // errMsg만 꺼내어 return 시킬 수 있게끔
            }
            String value = paramStrBits[1];
            params.put(key, value);
        }
    }

    public String getActionMethod() {
        return actionMethod;
    }

    public String getParams(String paramName) {
        return params.get(paramName);
    }

    public String getErrMsg() {
        return errMsg;
    }
}