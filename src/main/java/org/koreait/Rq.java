package org.koreait;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    // 명령어를 가공해서 쓸수 있게 만들어주는
    // 해당 요청을 정리해서 전달해주는 클래스 (요청에 대한 처리)
    // 문자열을 잘라주는 전문가
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
        // new Rq 할때 실행되는 함수
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