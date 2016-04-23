package com.dsecet.util.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088221624446984";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;
	
	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	//public static String private_key = "f4fvsn4pkqzcuhfeor5e1vrnecx8rfyb";
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALuVMzy7B+42BZcT"+
			 "errnbBiN7f69uC5JaWdlmPy0dPZw7bf+/PNZWwWBf0M6bhgbmA0i5rESTgYPbuSz"+
			 "AGxWTWc9vEzpUlIgNgqtPDMKPi3q8+EGNwhjxyc/qkdU3E+BzbWOYdcg5y57Y3pT"+
			 "eBpu7H/ayDL5seH3tFhAqNX0Zgp7AgMBAAECgYAd5K0BpJyi7aD+bnBEkNS9JQEk"+
			 "c3+CgNh3Tc2PmAJ7s4tuowgUj8zdl8F8KTeaR+t3THSPC2e7t4HomE4c9bPn1ip3"+
			 "tq3sCLAJpodW6XSBVoK6oofb8J7k2Ny1G4JHOIIoJnq3t70SBxseCcFiWoXhjSNj"+
			 "1wEkE9RWmNAfx1AtAQJBANvBfQzA8wr3NTW2L0w8aLYvAn8d5FgQ408ymjhTLpUB"+
			 "ndJGG7WL63FhxxJ9E3Huj2H00eYkiLK+SDtfXghNk1sCQQDahU5QUimufZ/e1yl4"+
			 "gY4HqndzIjLWy5qFQ6nFx4R430sxmJb726jnwqDMthkH96mbtQ8M9X8wjqlvgMCW"+
			 "sq9hAkEA0TGAzApFkxO/SHE01zrOZ1ea5c1b2OXCrVq7apDs0BXo2gm5SPp4V8e/"+
			 "jP8Z6qQxRAudYJs4sgbnlfVz+y117wJAEIWBrUr5ActXugZ0iTrqXSQDUf4k796G"+
			 "+UprXJvAJU9kpQRPbg1mkNdDRxcBf3u3JCGlWKp7ClXO87I9XWd3IQJBALORCfZI"+
			 "HHuG8+m/U0w/U39XfB4RjU7LbY0PkkjOfOeFOyFuKrd+4eczJRDNE4TZvOijMkTz"+
			 "wA+RSKrHDGV8gzo=";
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://vb14968980.iask.in:23694/alipay/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://vb14968980.iask.in:23694/alipay/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "D:\\";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "create_direct_pay_by_user";
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
	//↓↓↓↓↓↓↓↓↓↓ 请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	// 防钓鱼时间戳  若要使用请调用类文件submit中的query_timestamp函数
	public static String anti_phishing_key = "";
	
	// 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
	public static String exter_invoke_ip = "";
			
	//↑↑↑↑↑↑↑↑↑↑请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
		

	

}
