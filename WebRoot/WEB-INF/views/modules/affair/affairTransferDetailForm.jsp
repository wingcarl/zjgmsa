<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>二程船详细信息表管理</title>
	<meta name="decorator" content="default"/>
	<style>
			#container{width:800px;min-height:1000px;border:1px solid black;margin:0 auto;}
			#title_container{width:800px;height:50px;border-bottom:2px solid black;font-size:26px;text-align:center;margin-top:20px}
			.apply_date{float:left;margin-left:20px;font-size:14px;}
			.apply_no{float:right;margin-right:20px;font-size:14px;}
			#hxt{width:150px;height:40px;margin:0 auto;text-align:center; margin-top:10px;font:bold 26px arial,sans-serif;float:left;}
			#report_date{width:180px;height:40px;margin:0 auto;text-align:center; margin-top:14px;font:20px arial,sans-serif;float:left;}
			#dep{width:200px;height:40px;margin:0 auto;text-align:center;margin-top:10px;margin-left:20px;font:bold 26px arial,sans-serif;float:left;}
			
			#cruise_range{width:800px;min-height:400px;border-bottom:2px solid red;border-top:1px solid red;}
			#range_title{width:790px;height:22px;font-size:16px;font-weight:bold;float:left;padding-left:10px;padding-top:2px;border-bottom:1px solid black}
			.range_row{width:800px;height:22px;border-bottom:1px solid black;float:left;}
			.rt{width:135px;height:22px;border-left:1px solid black;float:left;text-align:center}
			.rc{width:60px;height:22px;border-left:1px solid black;float:left;text-align:center}
			.rcl{min-width:160px;padding:0px 5px;min-height:22px;border-left:1px solid black;float:left;text-align:center}
			.range_textarea{width:800px;min-height:22px;border-bottom:1px solid black;}
			td{text-align:center;}
	</style>
	<style media="print">
		.noprint{
			display:none;
		}
		
	</style>
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
	<div id="container">
			<div id="title_container">
				<div id="title">水水中转货物核销登记簿</div>
			</div>
			<div class="range_row">
				<div class="apply_date"><fmt:formatDate value="${affairTransferInfo.createDate}" pattern="yyyy年M月d日"/></div>    
				<div class="apply_no">编号:${affairTransferInfo.number}</div>
			</div>
			<div>
				<table border="1" width="100%">
					<tr>
						<th rowspan="2" width="10%">一程船信息</th>
						<th>船名</th>
						<th>始发港</th>
						<th>航次</th>
						<th>货物名称</th>
						<th>实际载货量</th>
						<th>卸货码头</th>
					</tr>
					
					<tr>
						<td>${affairTransferInfo.firstShipName}</td>
						<td>${affairTransferInfo.firstShipDeparture}</td>
						<td>${affairTransferInfo.firstMoveNum}</td>
						<td>${affairTransferInfo.firstCargo}</td>
						<td>${affairTransferInfo.firstLoad}</td>
						<td>${affairTransferInfo.firstUnloadPort}</td>
					</tr>
					
					
				</table>
			</div>
			<div>
				<table border="1" width="100%">
					<tr>
						<th rowspan="10" width="10%">二程船信息</th>
						<th>收货人</th>
						<td>${second.receiver}</td>
						<th>目的港</th>
						<td>${second.destination}</td>
						<th>预报数量</th>
						<td>${second.number}</td>
					</tr>
					
					<tr>
						<th>二程船名</th>
						<th>运单号</th>
						<th>实际数量</th>
						<th>累计数量</th>
						<th>签  名</th>
						<th>日期/审核章</th>
						<th class="noprint">舱单</th>
					</tr>
					<c:forEach items="${second.affairTransferDetailList}" var="detail">
						<tr>
							<td>${detail.shipName}</td>
							<td>${detail.no}</td>
							<td>${detail.count}</td>
							<td>${detail.totalCount}</td>
							<td>${detail.createBy.name}</td>
							<td><fmt:formatDate value="${detail.auditDate}" pattern="yyyy-MM-dd"/></td>
							
							<td class="noprint">
								<a href="${detail.imgSrc}" target="_blank">
								查看舱单
								</a>
							</td>
						</tr>
					</c:forEach>
					
				</table>
			</div>
			
			<div class="range_row">
				<div class="apply_date">联系人:${affairTransferInfo.contactor}</div>    
				<div class="apply_no">联系电话：${affairTransferInfo.telephone}</div>
			</div>
		</div>
	
</body>
</html>