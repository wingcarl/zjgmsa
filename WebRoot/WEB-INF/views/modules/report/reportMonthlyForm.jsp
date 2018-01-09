<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>海事业务月度通报管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/report/reportMonthly/">海事业务月度通报列表</a></li>
		<li class="active"><a href="${ctx}/report/reportMonthly/form?id=${reportMonthly.id}">海事业务月度通报<shiro:hasPermission name="report:reportMonthly:edit">${not empty reportMonthly.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="report:reportMonthly:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="reportMonthly" action="${ctx}/report/reportMonthly/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">巡航（艘次）：</label>
			<div class="controls">
				<form:input path="cruise" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">巡航时间（小时）：</label>
			<div class="controls">
				<form:input path="cruiseTotalTime" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">巡航里程（海里）：</label>
			<div class="controls">
				<form:input path="cruiseTotalDistance" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出动执法人员（人次）：</label>
			<div class="controls">
				<form:input path="cruisePeopleCount" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">夜航（次）：</label>
			<div class="controls">
				<form:input path="nightCruiseCount" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">海巡艇发现违章（艘次）：</label>
			<div class="controls">
				<form:input path="illegalCount" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">海巡艇发现违章处理起数：</label>
			<div class="controls">
				<form:input path="dealIllegalCount" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出动车辆（台次）：</label>
			<div class="controls">
				<form:input path="cruiseCar" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出动执法人员（人次）：</label>
			<div class="controls">
				<form:input path="luyuPeopleCount" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检查船舶（艘次）：</label>
			<div class="controls">
				<form:input path="shipCheck" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检查船舶（艘次）：</label>
			<div class="controls">
				<form:input path="luyuShipCheck" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发现违章（艘次）：</label>
			<div class="controls">
				<form:input path="luyuIllegalCount" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发现违章处理起数：</label>
			<div class="controls">
				<form:input path="luyuDealCount" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">接受船舶报告（艘次）：</label>
			<div class="controls">
				<form:input path="vtsShipReport" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实施船舶跟踪（艘次）：</label>
			<div class="controls">
				<form:input path="vtsShipMonitor" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">纠正违章（艘次）：</label>
			<div class="controls">
				<form:input path="vtsIllegal" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交通组织（艘次）：</label>
			<div class="controls">
				<form:input path="vtsTraffic" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提供信息服务（艘次）：</label>
			<div class="controls">
				<form:input path="vtsInfo" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支持联合行动（次）：</label>
			<div class="controls">
				<form:input path="vtsUnion" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重点监控船舶：</label>
			<div class="controls">
				<form:input path="vtsMonitor" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">突发事件起数（个）：</label>
			<div class="controls">
				<form:input path="dangerEmergencyCount" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">险情起数（个）：</label>
			<div class="controls">
				<form:input path="dangerCount" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">遇险人数（人）：</label>
			<div class="controls">
				<form:input path="dangerPeople" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">获救人数（人）：</label>
			<div class="controls">
				<form:input path="rescuePeople" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">遇险船舶（艘）：</label>
			<div class="controls">
				<form:input path="dangerShip" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">救助船舶（艘）：</label>
			<div class="controls">
				<form:input path="rescueShip" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">险情起数（个）：</label>
			<div class="controls">
				<form:input path="upDangerCount" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">遇险人数（人）：</label>
			<div class="controls">
				<form:input path="upDangerPeople" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">获救人数（人）：</label>
			<div class="controls">
				<form:input path="upRescuePeople" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">遇险船舶（艘）：</label>
			<div class="controls">
				<form:input path="upDangerShip" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">救助船舶（艘）：</label>
			<div class="controls">
				<form:input path="upRescueShip" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出动海巡艇（艘次）：</label>
			<div class="controls">
				<form:input path="shipOut" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出动社会船艇（艘次）：</label>
			<div class="controls">
				<form:input path="socialShipOut" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">人命救助成功率（%）：</label>
			<div class="controls">
				<form:input path="savePercent" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">事故件数（个）：</label>
			<div class="controls">
				<form:input path="accidentCount" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">沉船（艘）：</label>
			<div class="controls">
				<form:input path="tantCount" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经济损失（万元）：</label>
			<div class="controls">
				<form:input path="economicLoss" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">事故件数：</label>
			<div class="controls">
				<form:input path="levelAccidentCount" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">沉船（艘）：</label>
			<div class="controls">
				<form:input path="levelTantCount" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经济损失（万元）：</label>
			<div class="controls">
				<form:input path="levelEconomicLost" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">水工审批（件）：</label>
			<div class="controls">
				<form:input path="projectAudit" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">通航报备（件）：</label>
			<div class="controls">
				<form:input path="projectPreview" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">在建水工（件）：</label>
			<div class="controls">
				<form:input path="projectNow" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">航行通告（件）：</label>
			<div class="controls">
				<form:input path="navigatorNotice" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发布灾害性天气预警信息（期）：</label>
			<div class="controls">
				<form:input path="advisory" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">临时交通管制次数：</label>
			<div class="controls">
				<form:input path="tempTrafficControl" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实施时间（小时）：</label>
			<div class="controls">
				<form:input path="controlHours" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">水工作业/发生险情次数：</label>
			<div class="controls">
				<form:input path="controlDanger" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实施时间（小时）：</label>
			<div class="controls">
				<form:input path="controlDangerHours" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到港（艘次）：</label>
			<div class="controls">
				<form:input path="shipInPort" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">过境（艘次）：</label>
			<div class="controls">
				<form:input path="shipByPort" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">辖区安全隐患（个）：</label>
			<div class="controls">
				<form:input path="hiddenDanger" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发放隐患整改通知书（份）：</label>
			<div class="controls">
				<form:input path="hiddenDangerReport" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">已整改隐患（个）：</label>
			<div class="controls">
				<form:input path="reformHiddenDanger" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发现超载（艘次）：</label>
			<div class="controls">
				<form:input path="overWeight" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚超载（艘次）：</label>
			<div class="controls">
				<form:input path="penaltyOverWeight" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚超载金额（万元）：</label>
			<div class="controls">
				<form:input path="penaltyOverWeightMoney" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">船舶签证（不含定期签证）：</label>
			<div class="controls">
				<form:input path="shipReport" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">船舶签证（总艘次）：</label>
			<div class="controls">
				<form:input path="shipReportAll" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">海船电子签证率：</label>
			<div class="controls">
				<form:input path="seaShipReport" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">进口岸审批（艘次）：</label>
			<div class="controls">
				<form:input path="internalAudit" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单船艘次（单）：</label>
			<div class="controls">
				<form:input path="internalSingleCount" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">进出港船舶总艘次：</label>
			<div class="controls">
				<form:input path="intralShipCount" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">船舶登记：</label>
			<div class="controls">
				<form:input path="shipRegist" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内贸（万吨）：</label>
			<div class="controls">
				<form:input path="throughputInPort" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">外贸（万吨）：</label>
			<div class="controls">
				<form:input path="throughputOutPort" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总计（万吨）：</label>
			<div class="controls">
				<form:input path="throughputTotalPort" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内贸（万吨）：</label>
			<div class="controls">
				<form:input path="throughputInWharf" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">外贸（万吨）：</label>
			<div class="controls">
				<form:input path="throughputOutWharf" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总计（万吨）：</label>
			<div class="controls">
				<form:input path="throughputTotalWharf" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">船载货量（万吨）：</label>
			<div class="controls">
				<form:input path="shipBurden" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">psc艘次：</label>
			<div class="controls">
				<form:input path="pscCount" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">psc缺陷数：</label>
			<div class="controls">
				<form:input path="pscDefect" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">psc滞留数：</label>
			<div class="controls">
				<form:input path="pscRetention" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">sea艘次：</label>
			<div class="controls">
				<form:input path="seaCount" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">sea缺陷数：</label>
			<div class="controls">
				<form:input path="seaDefect" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">sea滞留数：</label>
			<div class="controls">
				<form:input path="seaRetention" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">riverboat艘次：</label>
			<div class="controls">
				<form:input path="riverboatCount" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">riverboat缺陷数：</label>
			<div class="controls">
				<form:input path="riverboatDefect" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">riverboat滞留数：</label>
			<div class="controls">
				<form:input path="riverboatRetention" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">渡运数量（航次）：</label>
			<div class="controls">
				<form:input path="portCount" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">渡运车辆（万车次）：</label>
			<div class="controls">
				<form:input path="portPeopleCount" htmlEscape="false" maxlength="11" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">渡运人员（万人次）：</label>
			<div class="controls">
				<form:input path="portCarCount" htmlEscape="false" maxlength="11" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">散化申报：</label>
			<div class="controls">
				<form:input path="sanhuaCount" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">包装申报：</label>
			<div class="controls">
				<form:input path="baozhuangCount" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">散固申报：</label>
			<div class="controls">
				<form:input path="sanguCount" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其中A组散固：</label>
			<div class="controls">
				<form:input path="aSanguCount" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">危货船舶总计：</label>
			<div class="controls">
				<form:input path="dangerTotalCount" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">散化：</label>
			<div class="controls">
				<form:input path="sanhuaCargoCount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">包装类：</label>
			<div class="controls">
				<form:input path="baozhuangCargoCount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">散固：</label>
			<div class="controls">
				<form:input path="sanguCargoCount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其中A组散固：</label>
			<div class="controls">
				<form:input path="aSanguCargoCount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">危货吞吐量总计：</label>
			<div class="controls">
				<form:input path="dangerCargoTotalCount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">残油、油泥、油污水接收量（吨）：</label>
			<div class="controls">
				<form:input path="canyouCount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">洗舱水作业量（吨）：</label>
			<div class="controls">
				<form:input path="xicangshuiCount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">压载水排放量（吨）：</label>
			<div class="controls">
				<form:input path="yazaishuiCount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">防污作业审批数（次）：</label>
			<div class="controls">
				<form:input path="fangwuzuoyeCount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检查箱数：</label>
			<div class="controls">
				<form:input path="boxCheckCount" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缺陷数：</label>
			<div class="controls">
				<form:input path="boxDefectCount" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">缺陷率：</label>
			<div class="controls">
				<form:input path="boxDefectPercent" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚件数：</label>
			<div class="controls">
				<form:input path="penaltyCount" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚金额（万元）：</label>
			<div class="controls">
				<form:input path="penaltyMoneyCount" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">未处罚予教育放行：</label>
			<div class="controls">
				<form:input path="penaltyNotice" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">money政务中心：</label>
			<div class="controls">
				<form:input path="moneyZwzx" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">money港区处：</label>
			<div class="controls">
				<form:input path="moneyGqc" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">money保税区处：</label>
			<div class="controls">
				<form:input path="moneyBsq" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">money锦丰处：</label>
			<div class="controls">
				<form:input path="moneyJfc" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">money南丰处：</label>
			<div class="controls">
				<form:input path="moneyNfc" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">money大桥办：</label>
			<div class="controls">
				<form:input path="moneyDqb" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">money代收单位：</label>
			<div class="controls">
				<form:input path="moneyDaishou" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">money合计：</label>
			<div class="controls">
				<form:input path="moneyTotal" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="report:reportMonthly:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>