package com.thinkgem.jeesite.modules.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.setting.entity.WeixinAutoresponse;
import com.thinkgem.jeesite.modules.setting.entity.WeixinExpandconfig;
import com.thinkgem.jeesite.modules.setting.entity.WeixinMenuentity;
import com.thinkgem.jeesite.modules.setting.entity.WeixinNewsitem;
import com.thinkgem.jeesite.modules.setting.entity.WeixinReceivetext;
import com.thinkgem.jeesite.modules.setting.entity.WeixinSubscribe;
import com.thinkgem.jeesite.modules.setting.entity.WeixinTexttemplate;
import com.thinkgem.jeesite.modules.setting.service.WeixinAccountService;
import com.thinkgem.jeesite.modules.setting.service.WeixinAutoresponseService;
import com.thinkgem.jeesite.modules.setting.service.WeixinExpandconfigService;
import com.thinkgem.jeesite.modules.setting.service.WeixinMenuentityService;
import com.thinkgem.jeesite.modules.setting.service.WeixinNewsitemService;
import com.thinkgem.jeesite.modules.setting.service.WeixinReceivetextService;
import com.thinkgem.jeesite.modules.setting.service.WeixinSubscribeService;
import com.thinkgem.jeesite.modules.setting.service.WeixinTexttemplateService;
import com.thinkgem.jeesite.modules.weixin.entity.Article;
import com.thinkgem.jeesite.modules.weixin.entity.NewsMessageResp;
import com.thinkgem.jeesite.modules.weixin.entity.TextMessageResp;
import com.thinkgem.jeesite.modules.weixin.utils.MessageUtil;



@Service("wechatService")
@Transactional(readOnly = true)
public class WechatService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private WeixinAccountService weixinAccountService;
	
	@Autowired
	private WeixinReceivetextService weixinReceivetextService;
	
	@Autowired
	private WeixinAutoresponseService weixinAutoresponseService;
	
	@Autowired
	private WeixinTexttemplateService weixinTexttemplateService;
	
	@Autowired
	private WeixinNewsitemService weixinNewsitemService;
	
	@Autowired
	private WeixinExpandconfigService weixinExpandconfigService;
	
	@Autowired
	private WeixinSubscribeService weixinSubscribeService;
	
	@Autowired
	private WeixinMenuentityService weixinMenuentityService;
	
	@Transactional(readOnly = false)
	public String coreService(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			String msgId = requestMap.get("MsgId");
			//消息内容
			String content = requestMap.get("Content");
			logger.info("------------微信客户端发送请求---------------------   |   fromUserName:"+fromUserName+"   |   ToUserName:"+toUserName+"   |   msgType:"+msgType+"   |   msgId:"+msgId+"   |   content:"+content);
			//根据微信ID,获取配置的全局的数据权限ID
			String sys_accountId = weixinAccountService.findByToUsername(toUserName).getId();
			ResourceBundle bundler = ResourceBundle.getBundle("jeesite");
			// 默认回复此文本消息
			TextMessageResp textMessage = new TextMessageResp();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setContent("欢迎使用锦绣微商系统");
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);
			//【微信触发类型】文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				logger.info("------------微信客户端发送请求------------------【微信触发类型】文本消息---");
				respMessage = doTextResponse(content,toUserName,textMessage,bundler,
						sys_accountId,respMessage,fromUserName,request,msgId,msgType);
			}
			//【微信触发类型】图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			//【微信触发类型】地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			//【微信触发类型】链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			//【微信触发类型】音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			//【微信触发类型】事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				logger.info("------------微信客户端发送请求------------------【微信触发类型】事件推送---");
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respMessage = doDingYueEventResponse(requestMap, textMessage, bundler, respMessage, toUserName, fromUserName, respContent, sys_accountId);
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					respMessage = doMyMenuEvent(requestMap, textMessage, bundler, respMessage, toUserName, fromUserName, respContent, sys_accountId, request);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
	
	/**
	 * 针对文本消息
	 * @param content
	 * @param toUserName
	 * @param textMessage
	 * @param bundler
	 * @param sys_accountId
	 * @param respMessage
	 * @param fromUserName
	 * @param request
	 * @throws Exception 
	 */
	@Transactional(readOnly = false)
	public String doTextResponse(String content,String toUserName,TextMessageResp textMessage,ResourceBundle bundler,
			String sys_accountId,String respMessage,String fromUserName,HttpServletRequest request,String msgId,String msgType) throws Exception{
		//=================================================================================================================
		// 保存接收到的信息
		WeixinReceivetext receiveText = new WeixinReceivetext();
		receiveText.setContent(content);
		receiveText.setCreatetime(new Date());
		receiveText.setFromusername(fromUserName);
		receiveText.setTousername(toUserName);
		receiveText.setMsgid(msgId);
		receiveText.setMsgtype(msgType);
		receiveText.setResponse("0");
		receiveText.setAccountid(toUserName);
		weixinReceivetextService.save(receiveText);
		//=================================================================================================================
		//Step.1 判断关键字信息中是否管理该文本内容。有的话优先采用数据库中的回复
		logger.info("------------微信客户端发送请求--------------Step.1 判断关键字信息中是否管理该文本内容。有的话优先采用数据库中的回复---");
		WeixinAutoresponse autoResponse = findKey(content, toUserName);
		// 根据系统配置的关键字信息，返回对应的消息
		if (autoResponse != null) {
			String resMsgType = autoResponse.getMsgtype();
			if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(resMsgType)) {
				//根据返回消息key，获取对应的文本消息返回给微信客户端
				WeixinTexttemplate textTemplate = weixinTexttemplateService.getTextTemplateByName(sys_accountId, autoResponse.getTemplatename());
				textMessage.setContent(textTemplate.getContent());
				respMessage = MessageUtil.textMessageToXml(textMessage);
			} else if (MessageUtil.RESP_MESSAGE_TYPE_NEWS.equals(resMsgType)) {
				List<WeixinNewsitem> newsList = weixinNewsitemService.findListByTempleId(autoResponse.getRescontent());
				List<Article> articleList = new ArrayList<Article>();
				for (WeixinNewsitem news : newsList) {
					Article article = new Article();
					article.setTitle(news.getTitle());
					article.setPicUrl(bundler.getString("domain") + "/"+ news.getImagepath());
					String url=request.getRequestURI();
					if (StringUtils.isEmpty(news.getUrl())) {
						url = bundler.getString("domain")+ "/newsItemController.do?newscontent&id="+ news.getId();
					} else {
						url = news.getUrl();
					}
					article.setUrl(url);
					article.setDescription(news.getDescription());
					articleList.add(article);
				}
				NewsMessageResp newsResp = new NewsMessageResp();
				newsResp.setCreateTime(new Date().getTime());
				newsResp.setFromUserName(toUserName);
				newsResp.setToUserName(fromUserName);
				newsResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsResp.setArticleCount(newsList.size());
				newsResp.setArticles(articleList);
				respMessage = MessageUtil.newsMessageToXml(newsResp);
			}
		} else {
			// Step.2  通过微信扩展接口（支持二次开发，例如：翻译，天气）
			logger.info("------------微信客户端发送请求--------------Step.2  通过微信扩展接口（支持二次开发，例如：翻译，天气）---");
			WeixinExpandconfig weixinExpandconfig=new WeixinExpandconfig();
			List<WeixinExpandconfig> weixinExpandconfigLst = weixinExpandconfigService.findList(weixinExpandconfig);
			if (weixinExpandconfigLst.size() != 0) {
				boolean findflag = false;// 是否找到关键字信息
				for (WeixinExpandconfig wec : weixinExpandconfigLst) {					
					// 如果已经找到关键字并处理业务，结束循环。
					if (findflag) {
						break;// 如果找到结束循环
					}
					String[] keys = wec.getKeyword().split(",");
					for (String k : keys) {
						if (content.indexOf(k) != -1) {
							String className = wec.getClassname();
							KeyServiceI keyService = (KeyServiceI) Class.forName(className).newInstance();
							respMessage = keyService.excute(content,textMessage, request);
							findflag = true;// 改变标识，已经找到关键字并处理业务，结束循环。
							break;// 当前关键字信息处理完毕，结束当前循环
						}
					}
				}
			}

		}
		return respMessage;
	}
	
	/**
	 * 遍历关键字管理中是否存在用户输入的关键字信息
	 * 
	 * @param content
	 * @return
	 */
	private WeixinAutoresponse findKey(String content, String toUsername) {
		logger.info("---------sys_accountId--------"+toUsername+"|");
		//获取全局的数据权限ID
		String sys_accountId = weixinAccountService.findByToUsername(toUsername).getId();
		logger.info("---------sys_accountId--------"+sys_accountId);
		// 获取关键字管理的列表，匹配后返回信息
		WeixinAutoresponse weixinAutoresponse=new WeixinAutoresponse();
		weixinAutoresponse.setAccountid(sys_accountId);
		List<WeixinAutoresponse> autoResponses = weixinAutoresponseService.findList(weixinAutoresponse);
		for (WeixinAutoresponse r : autoResponses) {
			// 如果包含关键字
			String kw = r.getKeyword();
			String[] allkw = kw.split(",");
			for (String k : allkw) {
				if (k.equals(content)) {
					logger.info("---------sys_accountId----查询结果----"+r);
					return r;
				}
			}
		}
		return null;
	}
	
	/**
	 * 自定义菜单事件
	 * @param requestMap
	 * @param textMessage
	 * @param bundler
	 * @param respMessage
	 * @param toUserName
	 * @param fromUserName
	 * @param respContent
	 * @param sys_accountId
	 * @param request
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	String doMyMenuEvent(Map<String, String> requestMap,TextMessageResp textMessage ,ResourceBundle bundler,String respMessage
			,String toUserName,String fromUserName,String respContent,String sys_accountId,HttpServletRequest request) throws Exception{
		String key = requestMap.get("EventKey");
		//自定义菜单CLICK类型
		WeixinMenuentity menuEntity = weixinMenuentityService.getMenuByKey(key);
		if (menuEntity != null&& StringUtils.isNotEmpty(menuEntity.getTemplateid())) {
			String type = menuEntity.getMsgtype();
			if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(type)) {
				WeixinTexttemplate textTemplate = weixinTexttemplateService.getTextTemplateById(sys_accountId, menuEntity.getTemplateid());
				String content = textTemplate.getContent();
				textMessage.setContent(content);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			} else if (MessageUtil.RESP_MESSAGE_TYPE_NEWS.equals(type)) {
				List<WeixinNewsitem> newsList = weixinNewsitemService.findListByTempleId(menuEntity.getTemplateid());
				List<Article> articleList = new ArrayList<Article>();
				for (WeixinNewsitem news : newsList) {
					Article article = new Article();
					article.setTitle(news.getTitle());
					article.setPicUrl(bundler.getString("domain") + "/"+ news.getImagepath());
					String url=request.getRequestURI();
					if (StringUtils.isEmpty(news.getUrl())) {
						url = bundler.getString("domain")+ "/newsItemController.do?newscontent&id="+ news.getId();
					} else {
						url = news.getUrl();
					}
					article.setUrl(url);
					article.setDescription(news.getDescription());
					articleList.add(article);
				}
				NewsMessageResp newsResp = new NewsMessageResp();
				newsResp.setCreateTime(new Date().getTime());
				newsResp.setFromUserName(toUserName);
				newsResp.setToUserName(fromUserName);
				newsResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsResp.setArticleCount(newsList.size());
				newsResp.setArticles(articleList);
				respMessage = MessageUtil
						.newsMessageToXml(newsResp);
			} else if ("expand".equals(type)) {
				WeixinExpandconfig expandconfigEntity = weixinExpandconfigService.get(menuEntity.getTemplateid());
				String className = expandconfigEntity.getClassname();
				KeyServiceI keyService = (KeyServiceI) Class.forName(className).newInstance();
				respMessage = keyService.excute("", textMessage,request);
			}
		}
		return respMessage;
	}
	
	/**
	 * 针对事件消息
	 * @param requestMap
	 * @param textMessage
	 * @param bundler
	 * @param respMessage
	 * @param toUserName
	 * @param fromUserName
	 */
	String doDingYueEventResponse(Map<String, String> requestMap,TextMessageResp textMessage ,ResourceBundle bundler,String respMessage
			,String toUserName,String fromUserName,String respContent,String sys_accountId){
		respContent = "谢谢您的关注！回复\"?\"进入主菜单。";
		List<WeixinSubscribe> lst = weixinSubscribeService.findListByAccountId(sys_accountId);
		if (lst.size() != 0) {
			WeixinSubscribe subscribe = lst.get(0);
			String type = subscribe.getMsgtype();
			if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(type)) {
				WeixinTexttemplate textTemplate = weixinTexttemplateService.getTextTemplateByName(sys_accountId, subscribe.getTemplatename());
				String content = textTemplate.getContent();
				textMessage.setContent(content);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			} else if (MessageUtil.RESP_MESSAGE_TYPE_NEWS.equals(type)) {
				List<WeixinNewsitem> newsList = weixinNewsitemService.findListByTempleId(subscribe.getTemplateid());
				List<Article> articleList = new ArrayList<Article>();
				for (WeixinNewsitem news : newsList) {
					Article article = new Article();
					article.setTitle(news.getTitle());
					article.setPicUrl(bundler.getString("domain")+ "/" + news.getImagepath());
					String url = "";
					if (StringUtils.isEmpty(news.getUrl())) {
						url = bundler.getString("domain")+ "/newsItemController.do?newscontent&id="+ news.getId();
					} else {
						url = news.getUrl();
					}
					article.setUrl(url);
					article.setDescription(news.getContent());
					articleList.add(article);
				}
				NewsMessageResp newsResp = new NewsMessageResp();
				newsResp.setCreateTime(new Date().getTime());
				newsResp.setFromUserName(toUserName);
				newsResp.setToUserName(fromUserName);
				newsResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsResp.setArticleCount(newsList.size());
				newsResp.setArticles(articleList);
				respMessage = MessageUtil.newsMessageToXml(newsResp);
			}
		}
		return respMessage;
	}


	/**
	 * Q译通使用指南
	 * 
	 * @return
	 */
	public static String getTranslateUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("微译使用指南").append("\n\n");
		buffer.append("微译为用户提供专业的多语言翻译服务，目前支持以下翻译方向：").append("\n");
		buffer.append("    中 -> 英").append("\n");
		buffer.append("    英 -> 中").append("\n");
		buffer.append("    日 -> 中").append("\n\n");
		buffer.append("使用示例：").append("\n");
		buffer.append("    翻译我是中国人").append("\n");
		buffer.append("    翻译dream").append("\n");
		buffer.append("    翻译さようなら").append("\n\n");
		buffer.append("回复“?”显示主菜单");
		return buffer.toString();
	}




}
