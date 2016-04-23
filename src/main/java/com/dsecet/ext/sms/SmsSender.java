package com.dsecet.ext.sms;

import java.util.List;

/**
 * @author: lxl
 */
public interface SmsSender {

    boolean send(String content, List<String> cells);
}
