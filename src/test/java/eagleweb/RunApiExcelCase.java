package eagleweb;

import baseframework.AssertRequestValue;
import baseframework.GetRequestValue;
import baseframework.RequestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.BasicConfigurator;
import org.testng.annotations.Test;
import utils.PhoneUtils;

public class RunApiExcelCase {


    @Test//一个线程，并发执行5次
    public void runApiExcelCase() throws Exception {
        RestAssured.useRelaxedHTTPSValidation();
        BasicConfigurator.configure();
        RequestConfig requestConfig =  new RequestConfig();
        GetRequestValue getRequestValue = new GetRequestValue();
        AssertRequestValue assertRequestValue = new AssertRequestValue();
        assertRequestValue.setGetRequestValue(getRequestValue);


        for(int s=0;s<getRequestValue.getSheetNum();s++){
            /**
             * 获取excel的sheetName
             */

            getRequestValue.sheetName(s);

            /**
             *  循坏执行excel的接口测试用例
             *  传入参数sheetNum
             *
             */

            for(int i=0;i<getRequestValue.list(s).size();i++){
                //接口测试名i
                getRequestValue.caseName(i);
                //接口测试描述
                getRequestValue.caseDescription(i);
                //接口URI、端口
                requestConfig.setHttpURIandPortValue(443);
                //打印响应结果
                Response response= assertRequestValue.assertApiParams(i);
                System.out.println("\n\n");
               // response.prettyPrint();
                //判断状态码是不是200
              //  assertConfig.AssertStatus(i);
                System.out.println(i);
                System.out.println(PhoneUtils.getPhone());
            }

        }
    }
}

