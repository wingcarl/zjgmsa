<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>周巡航计划管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/layer/layer.js"></script>
	<script src="${ctxStatic}/layer/lay/dest/layui.all.js"></script>
	
	<script src="${ctxStatic}/layer/layui.js"></script>
	<link href="${ctxStatic}/layer/css/layui.css" rel="stylesheet">
	<script type="text/javascript">
		$(document).ready(function() {		
			$('#loadData').click(function(){
				var id = $('#officeId').val();
				if(id=='') alert('请选择海巡艇！');
				else {
					 $.ajax({
			    	        type: "get",
			    	        dataType: "json",
			    	        url: '${ctx}/cruise/cruiseWeekPlan/loadPlanData?id='+id,
			    	        success: function (data) {
			    	        	if(data!=null){
			    	        		$('#grid').val(data.grid);
			    	        		$('#content').val(data.content);
			    	        		$('#pos').val(data.pos);
			    	        		$('#time').val(data.time);
			    	        	}
			    	        	
			    	        }	
			    	        });
				}
			})
			
			var week = ['sun','mon','tue','wed','thu','fri','sat'];
			for(var i=0;i<7;i++){
				var template = '<tr id="'+week[i]+'">'+
			    '<input name="cruiseWeekPlanDetailsList['+i+'].id" type="hidden" value="" class="id"/>'+
			    '<td><input id="arrangeDate" name="cruiseWeekPlanDetailsList['+i+'].arrangeDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate arrangeDate" '+
				 'onclick="WdatePicker({dateFmt:\'yyyy-MM-dd\',isShowClear:false});"/></td>'+
					'<td>'+
						'<input id="dayCruisePeople"  type="button" value="选择" maxlength="255" class="input-small layui-btn dayCruisePeople"/>'+
						'<input name="cruiseWeekPlanDetailsList['+i+'].dayCruisePeople" type="hidden" value="" class="dayCruisePeople" />'+					
					'</td>'+
					'<td>'+
						'<input id="cruiseCaptain" ct="cruiseCaptain" type="button" value="选择" maxlength="255" class="input-small layui-btn cruiseCaptain"/>'+
						'<input name="cruiseWeekPlanDetailsList['+i+'].cruiseCaptain" type="hidden" value="" class="cruiseCaptain" />'+					
						'</td>'+
					'<td>'+
						'<input type="checkbox" name="cruiseWeekPlanDetailsList['+i+'].isNightCruise" lay-skin="switch" lay-text="是|否" class="isNightCruise">'+
					'</td>'+
					'<td>'+
						'<input id="nightCruiseGrid" name="cruiseWeekPlanDetailsList['+i+'].nightCruiseGrid" type="text"  maxlength="255" class="input-small nightCruiseGrid"/></td>'+
					'<td>'+
						'<input id="nightCruisePeople"  type="button" maxlength="255" value="选择" class="input-small layui-btn nightCruisePeople"/>'+
						'<input name="cruiseWeekPlanDetailsList['+i+'].nightCruisePeople" type="hidden" value="" class="nightCruisePeople" />'+					
						'</td>'+
					'</tr>';
				$('#cruiseWeekPlanDetailsList').append(template);
			}
			layui.use('form', function(){
				  var form = layui.form();
				  
				  //监听提交
				  form.on('submit(formDemo)', function(data){
				    layer.msg(JSON.stringify(data.field));
				    return false;
				  });
				  
				});
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
			$('.layui-btn').click(function(){
				var type = $(this).attr('ct');
				var com ;
				if(type == 'cruiseCaptain'){
					com = '#selectCaptain';
				}else{
					com = '#selectPeople';
				}
				var oThis = $(this);
				var oUsers = [];
				oUsers = oThis.val().split(",");
				$(com + ' input[name="cruiseUsers"]').each(function(){
					if($.inArray($(this).val(),oUsers)>=0){
						$(this).attr('checked','checked');
						layui.form().render('checkbox');
					}else{
						$(this).removeAttr('checked');
						layui.form().render('checkbox');
					}
				})
				var users = [];
				layer.open({
					  type: 1,
					  content: $(com),
					  title:"选择人员",
					  btn: ['保存', '取消'],
					  area : ['50%' , '50%'],
					  yes:function(index,layero){
						  
						  $(com+' input[name="cruiseUsers"]:checked').each(function(){
				                var user=$(this).val();
				                users.push(user);
					          	  
				           });
						  layer.msg('添加成功');
						  layer.close(index);
						  oThis.val(users.join(','));
						  oThis.next().val(users.join(','));
						
						  
					  }
				});

				
				
			})
			
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
			
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cruise/cruiseWeekPlan/">周巡航计划列表</a></li>
		<li class="active"><a href="${ctx}/cruise/cruiseWeekPlan/form?id=${cruiseWeekPlan.id}">周巡航计划<shiro:hasPermission name="cruise:cruiseWeekPlan:edit">${not empty cruiseWeekPlan.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cruise:cruiseWeekPlan:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm"  modelAttribute="cruiseWeekPlan" action="${ctx}/cruise/cruiseWeekPlan/save" method="post" class="form-horizontal layui-form">
		<form:hidden id="planId" path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">开始日期：</label>
			<div class="controls">
				<input id="startDate" name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cruiseWeekPlan.startDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" onFocus="WdatePicker({onchanging:datechanged()})" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束日期：</label>
			<div class="controls">
				<input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cruiseWeekPlan.endDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">海巡艇：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${cruiseWeekPlan.office.id}" labelName="office.name" labelValue="${cruiseWeekPlan.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				<input type="button" id="loadData" class="btn btn-primary" value="加载上一次数据"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">巡航网格：</label>
			<div class="controls">
				<form:input path="cruiseGrid" id="grid" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">巡航时间：</label>
			<div class="controls">
				<form:input path="cruiseTime" id="time" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重点部件：</label>
			<div class="controls">
				<form:textarea path="importantPos" id="pos" htmlEscape="false" rows="4" maxlength="512" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">巡航工作重点：</label>
			<div class="controls">
				<form:textarea path="importantContent" id="content" htmlEscape="false" rows="4" maxlength="1024" class="input-xxlarge "/>
				
			</div>
		</div>
		
				
			<div class="control-group">
				<label class="control-label">巡航周计划日安排表：</label>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>计划日期</th>
								<th>白天随艇执法人员</th>
								<th>海巡艇当班驾驶员</th>
								<th>是否夜航艇</th>
								<th>夜航网格</th>
								<th>夜航随艇执法人员</th>
								<shiro:hasPermission name="cruise:cruiseWeekPlan:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="cruiseWeekPlanDetailsList">
							
							
							
						</tbody>
						
					</table>
					<script type="text/template" id="cruiseWeekPlanDetailsTpl">//<!--
						<tr id="cruiseWeekPlanDetailsList{{idx}}">
							<td class="hide">
								<input id="cruiseWeekPlanDetailsList{{idx}}_id" name="cruiseWeekPlanDetailsList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="cruiseWeekPlanDetailsList{{idx}}_delFlag" name="cruiseWeekPlanDetailsList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="cruiseWeekPlanDetailsList{{idx}}_arrangeDate" name="cruiseWeekPlanDetailsList[{{idx}}].arrangeDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.arrangeDate}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<td>
								<input id="cruiseWeekPlanDetailsList{{idx}}_dayCruisePeople" name="cruiseWeekPlanDetailsList[{{idx}}].dayCruisePeople" type="text" value="{{row.dayCruisePeople}}" maxlength="255" class="input-small peopleSelect"/>
							</td>
							<td>
								<input id="cruiseWeekPlanDetailsList{{idx}}_cruiseCaptain" name="cruiseWeekPlanDetailsList[{{idx}}].cruiseCaptain" type="text" value="{{row.cruiseCaptain}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<select id="cruiseWeekPlanDetailsList{{idx}}_isNightCruise" name="cruiseWeekPlanDetailsList[{{idx}}].isNightCruise"  value="{{row.isNightCruise}}" maxlength="11" class="input-small ">
										<option value="0" label=""/>
										<option value="1" label="是"/>
										<option value="2" label="否"/>

								</select>
							</td>
							<td>
								<input id="cruiseWeekPlanDetailsList{{idx}}_nightCruiseGrid" name="cruiseWeekPlanDetailsList[{{idx}}].nightCruiseGrid" type="text" value="{{row.nightCruiseGrid}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="cruiseWeekPlanDetailsList{{idx}}_nightCruisePeople" name="cruiseWeekPlanDetailsList[{{idx}}].nightCruisePeople" type="text" value="{{row.nightCruisePeople}}" maxlength="255" class="input-small "/>
							</td>
							<shiro:hasPermission name="cruise:cruiseWeekPlan:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#cruiseWeekPlanDetailsList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						function addDate(date,days){
							var d = new Date(date);
							
							d.setDate(d.getDate()+days);
						
							var month = d.getMonth()+1;
							if(month<10)
								month = "0"+month;
							var day = d.getDate();
							if(day<10)
								day = "0"+day;
							var v = d.getFullYear()+"-"+month+"-"+day
							return v;
						}
						function datechanged(){
							var dp = $dp.cal;
							if(dp!=undefined){
								var oldDate = dp.oldValue;
								var newDate = $dp.cal.getNewDateStr();
								if(oldDate!=newDate){
									var d = addDate(newDate,6);
									
									$('#endDate').val(d);
									for (var i=0; i<7; i++){
										
										$($(".arrangeDate")[i]).val(addDate(newDate,i));
									}
								}
							}
							
						}
						var cruiseWeekPlanDetailsRowIdx = 0, cruiseWeekPlanDetailsTpl = $("#cruiseWeekPlanDetailsTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
						
							var data = ${fns:toJson(cruiseWeekPlan.cruiseWeekPlanDetailsList)};
							if(data.length==0){
								for (var i=0; i<7; i++){
									var startDate = $("#startDate").val();
									$($(".arrangeDate")[i]).val(addDate(startDate,i));
								}
							}else{
								for (var i=0; i<data.length; i++){	
									$($(".id")[i]).val(data[i].id);
									$($(".arrangeDate")[i]).val(data[i].arrangeDate);
									if(data[i].isNightCruise=='on'){
										$($(".isNightCruise")[i]).attr('checked','checked');
									}
									
									$($(".nightCruiseGrid")[i]).val(data[i].nightCruiseGrid);
								}
								j=0;
								console.log(data);
								for (var i=0; i<data.length; i++){
									$($(".dayCruisePeople")[j++]).val(data[i].dayCruisePeople);
									$($(".dayCruisePeople")[j++]).val(data[i].dayCruisePeople);
								}
								j=0;
								for (var i=0; i<data.length; i++){
									$($(".cruiseCaptain")[j++]).val(data[i].cruiseCaptain);
									$($(".cruiseCaptain")[j++]).val(data[i].cruiseCaptain);
								}
								j=0;
								for (var i=0; i<data.length; i++){
									$($(".nightCruisePeople")[j++]).val(data[i].nightCruisePeople);
									$($(".nightCruisePeople")[j++]).val(data[i].nightCruisePeople);
								}
							}
							
							
						});
					</script>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="cruise:cruiseWeekPlan:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<div id="selectPeople" style="display:none;margin-top:10px;margin-left:10px">
		<form class="layui-form" action="">
		<div class="layui-form-item">
			<ul>
					<c:forEach items="${cUsers}" var="user">
							<li style="float:left"> 
							<input type="checkbox" name="cruiseUsers" title="${user.name}" value="${user.name}">
							</li>
					</c:forEach>			
			</ul>
			</div>	
		</form>
	</div>
	<div id="selectCaptain" style="display:none;margin-top:10px;margin-left:10px">
		<form class="layui-form" action="">
		<div class="layui-form-item">
			<ul>
					<c:forEach items="${cCaptain}" var="user">
							<li style="float:left"> 
							<input type="checkbox" name="cruiseUsers" title="${user.name}" value="${user.name},${user.phone}">
							</li>
					</c:forEach>			
			</ul>
			</div>	
		</form>
	</div>
</body>
</html>