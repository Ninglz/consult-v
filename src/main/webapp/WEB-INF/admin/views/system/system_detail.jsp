<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>系统设置</title>
  <link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys.css" rel="stylesheet" type="text/css" />
  <%@ include file="/WEB-INF/admin/views/common/header.jsp"%>
</head>
<body>
<input type="hidden" id="activeMenu" value="system">
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
         <jsp:include page="/WEB-INF/admin/views/common/left.jsp" flush="true">
    	 <jsp:param name="activeMenu" value="consumer" />
  		</jsp:include> 
      </td>
      <td class="right" valign="top">
         <div class="right_top clearfix">
            <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         </div>
         <p class="main mt20"><a href="">平台管理</a> > 系统设置 > 系统配置</p>
         <div class="main">
            <form action="/admin/system/edit" method="post" id="system_form">
            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="id" value="${property.id}"/>
               <table cellspacing="0" cellpadding="0" width="100%" class="form_table">
                  <tr>
                     <td width="150" class="text_right">主机地址：</td>
                     <td>${property.systemHostUrl}</td>
                     
                     <td width="150" class="text_right">系统映射地址：</td>
                     <td>${property.websiteStaticUrl}</td>
                  </tr>
                  
                  <tr>
                     <td width="150" class="text_right">文件存放路径：</td>
                     <td>${property.storageFilePrefix}</td>
                     
                     <td width="150" class="text_right">安卓应用下载地址：</td>
                     <td>${property.andoridUrl}</td>
                  </tr>
                  
                  <tr>
                     <td width="150" class="text_right">IOS应用下载地址：</td>
                     <td>${property.iosUrl}</td>
                     
                     <td width="150" class="text_right">APP前缀：</th>
                     <td>${property.appPrefix}</td>
                  </tr>
                  
                  <tr>
                     <td width="150" class="text_right">新闻前缀：</td>
                     <td>${property.newsPrefix}</td>
                     
                     <td width="150" class="text_right">个人信息前缀：</td>
                     <td>${property.informationPrefix}</td>
                  </tr>
                  
                  <tr>
                     <td width="180" class="text_right">咨询前置免费时间(秒数)：</td>
                     <td><input type="text" name="freeTime" id="" class="fany_input ml10" value="${property.freeTime}"/></td>
                     
                     <td width="150" class="text_right">计费时间(秒数)：</td>
                     <td><input type="text" name="payTime" id="" class="fany_input ml10" value="${property.payTime}"/></td>
                  </tr>
                  
                  <tr>
                     <td width="150" class="text_right">注册积分：</td>
                     <td><input type="text" name="freeIntegral" id="" class="fany_input ml10" maxlength=7 value="${property.freeIntegral}"/></td>
                     
                     <td width="150" class="text_right">客服电话：</td>
                     <td><input type="text" name="cell" id="" class="fany_input ml10" value="${property.cell}"/></td>
                  </tr>
                  
                  <tr>
                     <td width="150" class="text_right f14">对咨询师：</td>
                     <td></td>
                  </tr>
                  <tr>
                     <td width="150" class="text_right" colspan="1">标准单价(元)：</td>
                     <td colspan="1"><input type="text" name="price" id="price" class="fany_input ml10" maxlength=7 value="${property.price}"/></td>
                     
                     <td width="150" class="text_right" colspan="1">单次咨询起价(元)：</td>
                     <td colspan="2"><input type="text" name="startPrice" id="startPrice" class="fany_input ml10" maxlength=7 value="${property.startPrice}"/></td>
                  </tr>
                  <tr>
                     <td width="150" class="text_right" colspan="1">临时设置：</td>
                     <td colspan="3">
                     	<input type="hidden" name="timeForm"/>
                     	<input type="text" name="" id="dateStr1"  onfocus="WdatePicker()" class="fany_input w100 ml10 mr10" value="${property.timeForm}"/>
                  <select name="" id="dateStr2" class="fany_input w60" >
                     <option>00</option>
                     <option>01</option>
                     <option>02</option>
                     <option>03</option>
                     <option>04</option>
                     <option >05</option>
                     <option>06</option>
                     <option>07</option>
                     <option>08</option>
                     <option>09</option>
                     <option>10</option>
                     <option>11</option>
                     <option>12</option>
                     <option>13</option>
                     <option>14</option>
                     <option>15</option>
                     <option>16</option>
                     <option>17</option>
                     <option>18</option>
                     <option>19</option>
                     <option>20</option>
                     <option>21</option>
                     <option>22</option>
                     <option>23</option>
                  </select> 时
                  <select name="" id="dateStr3" class="fany_input w60">
                     <option>00</option>
                     <option>01</option>
                     <option>02</option>
                     <option>03</option>
                     <option>04</option>
                     <option>05</option>
                     <option>06</option>
                     <option>07</option>
                     <option>08</option>
                     <option>09</option>
                     <option>10</option>
                     <option>11</option>
                     <option>12</option>
                     <option>13</option>
                     <option>14</option>
                     <option>15</option>
                     <option>16</option>
                     <option>17</option>
                     <option>18</option>
                     <option>19</option>
                     <option>20</option>
                     <option>21</option>
                     <option>22</option>
                     <option>23</option>
                     <option>24</option>
                     <option>25</option>
                     <option>26</option>
                     <option>27</option>
                     <option>28</option>
                     <option>29</option>
                     <option>30</option>
                     <option>31</option>
                     <option>32</option>
                     <option>33</option>
                     <option>34</option>
                     <option>35</option>
                     <option>36</option>
                     <option>37</option>
                     <option>38</option>
                     <option>39</option>
                     <option>40</option>
                     <option>41</option>
                     <option>42</option>
                     <option>43</option>
                     <option>44</option>
                     <option>45</option>
                     <option>46</option>
                     <option>47</option>
                     <option>48</option>
                     <option>49</option>
                     <option>50</option>
                     <option>51</option>
                     <option>52</option>
                     <option>53</option>
                     <option>54</option>
                     <option>55</option>
                     <option>56</option>
                     <option>57</option>
                     <option>58</option>
                     <option>59</option>
                  </select> 分
                  到               <input type="hidden" name="timeTo"/>
                  <input type="text" name="" id="dateStr4" onfocus="WdatePicker()" class="fany_input w100 ml10 mr10" value="${property.timeTo}"/>
                  <select name="" id="dateStr5" class="fany_input w60">
                     <option>00</option>
                     <option>01</option>
                     <option>02</option>
                     <option>03</option>
                     <option>04</option>
                     <option>05</option>
                     <option>06</option>
                     <option>07</option>
                     <option>08</option>
                     <option>09</option>
                     <option>10</option>
                     <option>11</option>
                     <option>12</option>
                     <option>13</option>
                     <option>14</option>
                     <option>15</option>
                     <option>16</option>
                     <option>17</option>
                     <option>18</option>
                     <option>19</option>
                     <option>20</option>
                     <option>21</option>
                     <option>22</option>
                     <option>23</option>
                  </select> 时
                  <select name="" id="dateStr6" class="fany_input w60">
                     <option>00</option>
                     <option>01</option>
                     <option>02</option>
                     <option>03</option>
                     <option>04</option>
                     <option>05</option>
                     <option>06</option>
                     <option>07</option>
                     <option>08</option>
                     <option>09</option>
                     <option>10</option>
                     <option>11</option>
                     <option>12</option>
                     <option>13</option>
                     <option>14</option>
                     <option>15</option>
                     <option>16</option>
                     <option>17</option>
                     <option>18</option>
                     <option>19</option>
                     <option>20</option>
                     <option>21</option>
                     <option>22</option>
                     <option>23</option>
                     <option>24</option>
                     <option>25</option>
                     <option>26</option>
                     <option>27</option>
                     <option>28</option>
                     <option>29</option>
                     <option>30</option>
                     <option>31</option>
                     <option>32</option>
                     <option>33</option>
                     <option>34</option>
                     <option>35</option>
                     <option>36</option>
                     <option>37</option>
                     <option>38</option>
                     <option>39</option>
                     <option>40</option>
                     <option>41</option>
                     <option>42</option>
                     <option>43</option>
                     <option>44</option>
                     <option>45</option>
                     <option>46</option>
                     <option>47</option>
                     <option>48</option>
                     <option>49</option>
                     <option>50</option>
                     <option>51</option>
                     <option>52</option>
                     <option>53</option>
                     <option>54</option>
                     <option>55</option>
                     <option>56</option>
                     <option>57</option>
                     <option>58</option>
                     <option>59</option>
                  </select> 分
                  的浮动比例<input type="text" name="proportion" id="exportScale" class="fany_input w100 ml10" value="${property.proportion}"/></td>
                  </tr>
                  <tr>
                     <td width="150" class="text_right f14" colspan="1">对咨询者：</td>
                     <td colspan="1"></td>
                  </tr>
                  <tr>
                     <td width="150" class="text_right" colspan="1">标准单价(元)：</td>
                     <td colspan="1"><input type="text" name="consumerPrice" id="consultPrice" maxlength=7 class="fany_input ml10" value="${property.consumerPrice}"/></td>
                     
                     <td width="150" class="text_right" colspan="1">单次咨询起价(元)：</td>
                     <td colspan="1"><input type="text" name="consumerStartPrice" id="consultSP" maxlength=7 class="fany_input ml10" value="${property.consumerStartPrice}"/></td>
                  </tr>
                  <tr>
                     <td width="150" class="text_right" colspan="1">临时设置：</td>
                     <td colspan="3">
                      <input type="hidden" name="consumerTimeForm"/>
                     <input type="text" name="" id="date1"  onfocus="WdatePicker()" class="fany_input w100 ml10 mr10" value="${property.consumerTimeForm}"/>
                  <select name="" id="date2" class="fany_input w60">
                     <option>00</option>
                     <option>01</option>
                     <option>02</option>
                     <option>03</option>
                     <option>04</option>
                     <option>05</option>
                     <option>06</option>
                     <option>07</option>
                     <option>08</option>
                     <option>09</option>
                     <option>10</option>
                     <option>11</option>
                     <option>12</option>
                     <option>13</option>
                     <option>14</option>
                     <option>15</option>
                     <option>16</option>
                     <option>17</option>
                     <option>18</option>
                     <option>19</option>
                     <option>20</option>
                     <option>21</option>
                     <option>22</option>
                     <option>23</option>
                  </select> 时
                  <select name="" id="date3" class="fany_input w60">
                     <option>00</option>
                     <option>01</option>
                     <option>02</option>
                     <option>03</option>
                     <option>04</option>
                     <option>05</option>
                     <option>06</option>
                     <option>07</option>
                     <option>08</option>
                     <option>09</option>
                     <option>10</option>
                     <option>11</option>
                     <option>12</option>
                     <option>13</option>
                     <option>14</option>
                     <option>15</option>
                     <option>16</option>
                     <option>17</option>
                     <option>18</option>
                     <option>19</option>
                     <option>20</option>
                     <option>21</option>
                     <option>22</option>
                     <option>23</option>
                     <option>24</option>
                     <option>25</option>
                     <option>26</option>
                     <option>27</option>
                     <option>28</option>
                     <option>29</option>
                     <option>30</option>
                     <option>31</option>
                     <option>32</option>
                     <option>33</option>
                     <option>34</option>
                     <option>35</option>
                     <option>36</option>
                     <option>37</option>
                     <option>38</option>
                     <option>39</option>
                     <option>40</option>
                     <option>41</option>
                     <option>42</option>
                     <option>43</option>
                     <option>44</option>
                     <option>45</option>
                     <option>46</option>
                     <option>47</option>
                     <option>48</option>
                     <option>49</option>
                     <option>50</option>
                     <option>51</option>
                     <option>52</option>
                     <option>53</option>
                     <option>54</option>
                     <option>55</option>
                     <option>56</option>
                     <option>57</option>
                     <option>58</option>
                     <option>59</option>
                  </select> 分
                  到              <input type="hidden" name="consumerTimeTo"/>
                  <input type="text" name="" id="date4"  onfocus="WdatePicker()" class="fany_input w100 ml10 mr10" value="${property.consumerTimeTo}"/>
                  <select name="" id="date5" class="fany_input w60">
                     <option>00</option>
                     <option>01</option>
                     <option>02</option>
                     <option>03</option>
                     <option>04</option>
                     <option>05</option>
                     <option>06</option>
                     <option>07</option>
                     <option>08</option>
                     <option>09</option>
                     <option>10</option>
                     <option>11</option>
                     <option>12</option>
                     <option>13</option>
                     <option>14</option>
                     <option>15</option>
                     <option>16</option>
                     <option>17</option>
                     <option>18</option>
                     <option>19</option>
                     <option>20</option>
                     <option>21</option>
                     <option>22</option>
                     <option>23</option>
                  </select> 时
                  <select name="" id="date6" class="fany_input w60">
                     <option>00</option>
                     <option>01</option>
                     <option>02</option>
                     <option>03</option>
                     <option>04</option>
                     <option>05</option>
                     <option>06</option>
                     <option>07</option>
                     <option>08</option>
                     <option>09</option>
                     <option>10</option>
                     <option>11</option>
                     <option>12</option>
                     <option>13</option>
                     <option>14</option>
                     <option>15</option>
                     <option>16</option>
                     <option>17</option>
                     <option>18</option>
                     <option>19</option>
                     <option>20</option>
                     <option>21</option>
                     <option>22</option>
                     <option>23</option>
                     <option>24</option>
                     <option>25</option>
                     <option>26</option>
                     <option>27</option>
                     <option>28</option>
                     <option>29</option>
                     <option>30</option>
                     <option>31</option>
                     <option>32</option>
                     <option>33</option>
                     <option>34</option>
                     <option>35</option>
                     <option>36</option>
                     <option>37</option>
                     <option>38</option>
                     <option>39</option>
                     <option>40</option>
                     <option>41</option>
                     <option>42</option>
                     <option>43</option>
                     <option>44</option>
                     <option>45</option>
                     <option>46</option>
                     <option>47</option>
                     <option>48</option>
                     <option>49</option>
                     <option>50</option>
                     <option>51</option>
                     <option>52</option>
                     <option>53</option>
                     <option>54</option>
                     <option>55</option>
                     <option>56</option>
                     <option>57</option>
                     <option>58</option>
                     <option>59</option>
                  </select> 分
                  的浮动比例<input type="text" name="consumerProportion" id="consultScale" class="fany_input w100 ml10" value="${property.consumerProportion}"/></td>
                  </tr>
                  <tr>
                     <td width="150" class="text_right"></td>
                     <td><a href="javascript:" class="fany_btn f14 color_ff ml10" onClick="submit()">设置</a></td>
                  </tr>
               </table>
            </form>
         </div>
        <div class="footer text_center">&copy;copyright 2015 问堂有限公司  版权所有</div>
      </td>
   </tr>
</table>
<script src="/resources/admin/js/common/js/jquery-1.7.1.min.js"></script>
<script src="/resources/admin/js/common/js/jquery.longyuJs.js"></script>
<script src="/resources/admin/js/common/js/jquery.dialog.js"></script>
<script src="/resources/admin/js/common/js/My97DatePicker/WdatePicker.js"></script>
<script src="/resources/admin/js/nav.js"></script>
<script>
   $(function(){
	   var cw=$(window).height();
	   $(".warpper").css("min-height",cw-2);
	   
   var date1=$("#date1").val();
   date1=$.keyToTimeArray(date1);
   $("#date1").val(date1[0]);
   $("#date2").val(date1[1]);
   $("#date3").val(date1[2]);
   
   var date4=$("#date4").val();
   date4=$.keyToTimeArray(date4);
   $("#date4").val(date4[0]);
   $("#date5").val(date4[1]);
   $("#date6").val(date4[2]);
   
   var dateStr1=$("#dateStr1").val();
   dateStr1=$.keyToTimeArray(dateStr1);
   $("#dateStr1").val(dateStr1[0]);
   $("#dateStr2").val(dateStr1[1]);
   $("#dateStr3").val(dateStr1[2]);
   
   var dateStr4=$("#dateStr4").val();
   dateStr4=$.keyToTimeArray(dateStr4);
   $("#dateStr4").val(dateStr4[0]);
   $("#dateStr5").val(dateStr4[1]);
   $("#dateStr6").val(dateStr4[2]);
});
   
   
   function submit(){
	   var time1 = $("#date1").val() +" "+ $("#date2").val() +":"+ $("#date3").val() + ":00";
	   $('input[name="consumerTimeForm"]').attr('value',time1);  
	   
	   var time2 = $("#date4").val() +" "+ $("#date5").val() +":"+ $("#date6").val() + ":00";
	   $('input[name="consumerTimeTo"]').attr('value',time2);  
	   
	   var time3 = $("#dateStr1").val() +" "+ $("#dateStr2").val() +":"+ $("#dateStr3").val() + ":00";
	   $('input[name="timeForm"]').attr('value',time3);  
	   
	   var time4 = $("#dateStr4").val() +" "+ $("#dateStr5").val() +":"+ $("#dateStr6").val() + ":00";
	   $('input[name="timeTo"]').attr('value',time4);  
	   
		var timestr1=$("#date1").val();
		timestr1=new Date(timestr1);
		
		var timestr2=$("#date4").val();
		timestr2=new Date(timestr2);
		
		var timestr3=$("#dateStr1").val();
		timestr3=new Date(timestr3);
		
		var timestr4=$("#dateStr4").val();
		timestr4=new Date(timestr4);
		if( timestr1.getTime() >= timestr2.getTime()){
			$.dialog({type:2,text:"咨询者临时时间输入错误"});
			return false;
		}
		
		if( timestr3.getTime() >= timestr4.getTime()){
			$.dialog({type:2,text:"咨询师临时时间输入错误"});
			return false;
		}
		
		var zReg = /^\d+(\.\d{0,2})?$/;
		var yReg = /^\d+$/;
		var int=$.trim($("input[name='freeTime']").val());
		if(!yReg.test(int) || int>600){
			$.dialog({type:2,text:"请输入小于10分钟的免费时长"});
			return false;
		}
		
		var payTime=$.trim($("input[name='payTime']").val());
		if(!yReg.test(payTime) || payTime>60){
			$.dialog({type:2,text:"请输入小于60秒的计费时长"});
			return false;
		}
		
		var freeIntegral = $.trim($("input[name='freeIntegral']").val());
		if(!zReg.test(freeIntegral)){
			$.dialog({type:2,text:"注册积分格式格式不正确"});
			return false;
		}
		
		var price = $.trim($("input[name='price']").val());
		if(!zReg.test(price)){
			$.dialog({type:2,text:"咨询师标准单价格式输入不正确"});
			return false;
		}
		
		var startPrice = $.trim($("input[name='startPrice']").val());
		if(!zReg.test(price)){
			$.dialog({type:2,text:"咨询师单次咨询格式输入不正确"});
			return false;
		}
		var proportion = $.trim($("input[name='proportion']").val());
		if(!zReg.test(proportion)){
			$.dialog({type:2,text:"咨询师临时比例输入不正确"});
			return false;
		}
		
		var consumerPrice = $.trim($("input[name='consumerPrice']").val());
		if(!zReg.test(consumerPrice)){
			$.dialog({type:2,text:"咨询者标准单价格式输入不正确"});
			return false;
		}
		
		var consumerStartPrice = $.trim($("input[name='consumerStartPrice']").val());
		if(!zReg.test(consumerStartPrice)){
			$.dialog({type:2,text:"咨询者单次咨询格式输入不正确"});
			return false;
		}
		var consumerProportion = $.trim($("input[name='consumerProportion']").val());
		if(!zReg.test(consumerProportion)){
			$.dialog({type:2,text:"咨询者临时比例输入不正确"});
			return false;
		}
	   $("#system_form").submit();
   }
   
</script>
</body>
</html>
