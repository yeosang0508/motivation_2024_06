package org.koreait;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private String actionMethod;
    private Map<String, String> params;

    //Rq == Request
    public Rq(String cmd) {
        // parsing
        String[] cmdBits = cmd.split("\\?", 2);

        actionMethod = cmdBits[0];

        params = new HashMap<>();

        String[] paramBits;

        try {
            paramBits = cmdBits[1].split("&");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("명령어 확인해");
            return;
        }

        // 다른 내용이 더 많을 경우 &로 엮어서 작성하도록 설정했는데 만약 없을 경우 catch로 잡아내어
        // 에러가 발생하지않고 실행시킬수있도록 코드를 짰다.

        for (String paramStr : paramBits) {
            String[] paramStrBits = paramStr.split("=", 2);
            // 만약 delete? id = 2 이런 형태면 delete가 cmdBits[0] , ? 뒤의 내용이 cmdBits[1]에 들어가게 되고
            // 따라서 =을 기준으로 자른 형태

            String key = paramStrBits[0];
            //map key 에 id, source, motivation을 넣어두도록 만들었고
            String value = paramStrBits[1];
            //map value에 그에 관한 내용이 들어갈 수 있도록 만들었다.
            params.put(key, value);
        }
    }

    public String getActionMethod() {
        return actionMethod;

        // 다른 클래스에서 이 영역에서 actionMethod에 들어갈 delete인지 나중에 만들 Update, detail.. 중
        // 어떤 것일지 구분하기 위해 꺼내올 수 있도록 get을 만들었다.
    }

    public String getParams(String paramName) {
        return params.get(paramName);

        //  getParams는 Map에 있는 내용을 가져오기 위해 만들었고,  String 하나만 사용한 이유는
        // 맵 중 하나를 가져오기 위해 파라미터에 key에 들어갈 내용을 입력하게 하여
        // 그와 같이 넣어둔 value를 가져갈 수 있도록 get을 이용하였다.


    }

}