package com.dsecet.util;


import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.APNTemplate;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 
public class PushHelper {
    //首先定义一些常量, 修改成开发者平台获得的值
    private static String appId = "Lhkz4Mce4d8VEPn1uUhjG4";
    private static String appKey = "X8lftgIKXt7prTKBN660Y6";
    private static String masterSecret = "Jig91oETmV8gufVmSXpLg7";
    private static String url = "http://sdk.open.api.igexin.com/serviceex";
    static String devicetoken = "ca8e5865b9688ca1e0f34e84bbd3d23ef1e5b10904f33da9b281ca7d8393be7a";//iOS设备唯一标识，由苹果那边生成
    public static void main(String[] args) throws IOException {
        // 新建一个IGtPush实例，传入调用接口网址，appkey和masterSecret
//        IGtPush push = new IGtPush(url, appKey, masterSecret);
//        push.connect();
//        // 新建一个消息类型，根据app推送的话要使用AppMessage
//        AppMessage message = new AppMessage();
//        // 新建一个推送模版, 以链接模板为例子，就是说在通知栏显示一条含图标、标题等的通知，用户点击可打开您指定的网页
////        LinkTemplate template = new LinkTemplate();
////        template.setAppId(appId);
////        template.setAppkey(appKey);
////        template.setTitle("欢迎使用个推!");
////        template.setText("这是一条推送消息~");
////        template.setUrl("http://getui.com");
//        List<String> appIds = new ArrayList<String>();
//        appIds.add(appId);
//        // 模板设置好后塞进message里并设置，同时设置推送的app id,还可以配置这条message是否支持离线，以及过期时间等
//        message.setData(transmissionTemplateDemo());
//        message.setAppIdList(appIds);
//        message.setOffline(true);
//        message.setOfflineExpireTime(1000 * 600);
//        // 调用IGtPush实例的pushMessageToApp接口，参数就是上面我们配置的message
//        IPushResult ret = push.pushMessageToApp(message);
//        System.out.println(ret.getResponse().toString());
    	try {
			//apnpush();
    		//apnBatchPush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    /**
     * <p>Description:对指定iOS用户列表简化推送</p>
     * @author:宁良竹
     * @update: 2016年4月5日
     * @throws Exception
     */
    public static void batchPushIOS(String content,List<String> list,int type) throws Exception {
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        Set<String> set = new HashSet<>();
        set.addAll(list);
        APNTemplate t = new APNTemplate();
        APNPayload apnpayload = new APNPayload();
        apnpayload.setSound("");
        apnpayload.addCustomMsg("type",type);
        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
        alertMsg.setTitle("咨询汇");
        alertMsg.setBody(content);
        apnpayload.setAlertMsg(alertMsg);
    
        t.setAPNInfo(apnpayload);  
        ListMessage message = new ListMessage();
        message.setData(t);
        String contentId = push.getAPNContentId(appId, message);
        System.out.println(contentId);
        List<String> dtl = new ArrayList<String>();
        for (String str : set) {
        	 dtl.add(str);
		}
//        dtl.add(devicetoken);
//        dtl.add("f60af1a762909d2a8281da55a1495f6bcacffec0ca56d398a1fec9c7234c1a45");
        System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
        IPushResult ret = push.pushAPNMessageToList(appId, contentId, dtl);
        System.out.println(ret.getResponse());
     }
    
    
    /**
     * <p>Description:对指定ANDROID用户列表简化推送</p>
     * @author:宁良竹
     * @update: 2016年4月8日
     * @throws Exception
     */
    public static void batchPushANDORID(String content,List<String> list,int type) throws Exception {
        //配置返回每个用户返回用户状态，可选
        System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        Set<String> set = new HashSet<>();
        set.addAll(list);
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent(content);
        ListMessage message = new ListMessage();
        message.setData(template);
        //设置消息离线，并设置离线时间
        message.setOffline(true);
        //离线有效时间，单位为毫秒，可选
        //message.setOfflineExpireTime(24*1000*3600);
    
        //配置推送目标
        List targets = new ArrayList();
        for (String str : set) {
        	Target target = new Target();
        	target.setAppId(appId);
        	target.setClientId(str);
        	targets.add(target);
		}
        
        //获取taskID
        String taskId = push.getContentId(message);
        //使用taskID对目标进行推送
        IPushResult ret = push.pushMessageToList(taskId, targets);
        //打印服务器返回信息
        System.out.println(ret.getResponse().toString());
    }
    
    /**
     * <p>Description:通知模板</p>
     * @author:宁良竹
     * @update: 2016年4月8日
     * @return
     */
    public static NotificationTemplate notificationTemplate() {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 设置通知栏标题与内容
        template.setTitle("请输入通知栏标题");
       template.setText("请输入通知栏内容");
        // 配置通知栏图标
        template.setLogo("icon.png");
        // 配置通知栏网络图标
        template.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent("大波！~！！！");
        return template;
}
    
    
/**
 * <p>Description:穿透模板</p>
 * @author:宁良竹
 * @update: 2016年4月8日
 * @return
 */
public static TransmissionTemplate transmissionTemplate() {
    TransmissionTemplate template = new TransmissionTemplate();
    template.setAppId(appId);
    template.setAppkey(appKey);
    // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
    template.setTransmissionType(2);
    template.setTransmissionContent("大波~~~~~~~");
    // 设置定时展示时间
    // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
    return template;
}
    
    
    /**
     * <p>Description:单个iOS用户简化推送</p>
     * @author:宁良竹
     * @update: 2016年4月5日
     * @throws Exception
     */
    public static void apnpush() throws Exception {
        IGtPush push = new IGtPush(url, appKey, masterSecret);  
        APNTemplate t = new APNTemplate();
        APNPayload apnpayload = new APNPayload();
        apnpayload.setSound("");
        apnpayload.addCustomMsg("type", "SB");
        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
        alertMsg.setTitle("咨询汇");
        alertMsg.setBody("月亮的子孙是谁？");
        apnpayload.setAlertMsg(alertMsg);
        
        t.setAPNInfo(apnpayload);
        SingleMessage sm = new SingleMessage();
        sm.setData(t);
        IPushResult ret0 = push.pushAPNMessageToSingle(appId, devicetoken, sm);
        System.out.println(ret0.getResponse());
        
 }
    

    
    
}
