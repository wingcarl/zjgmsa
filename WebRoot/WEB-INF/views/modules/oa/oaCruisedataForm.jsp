<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡航数据管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/jquery-easyui/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery-easyui/themes/icon.css">
	<link href="${ctxStatic}/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<script src="${ctxStatic}/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script src="${ctxStatic}/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="${ctxStatic}/layer/layer.js"></script>
	<script src="${ctxStatic}/layer/lay/dest/layui.all.js"></script>
	
	<script src="${ctxStatic}/layer/layui.js"></script>
	<link href="${ctxStatic}/layer/css/layui.css" rel="stylesheet">
	
	
	
	<style>
		ul { list-style-type: none;}
		li { display: inline-block;}
		li { margin: 10px 0;}
		
	</style>
	<script type="text/javascript">
		var myArray=new Array();
		var cruiseArray = new Array();
		$(document).ready(function() {
			/*$(":checkbox").labelauty();*/
			//$("#name").focus();
			$('#btnSubmit').click(function(){
				var start = [];
				var end = [];
				for(var i=0;i<10;i++){
					var startTime =  $('#oaCruisetimeList'+i+'_startTime');
					var endTime = $('#oaCruisetimeList'+i+'_endTime');
					if(startTime != null && endTime != null){
						start[i] = startTime.val();
						end[i] = endTime.val();
					}else{
						break;
					}	
				}
				var totalCruiseTime = 0;
				var yehangTime = 0;
				var xhsc = 0;
				var yhsc = 0;
				for(var i=0;i<10;i++){
					var startd = new Date(start[i]);
					var endd = new Date(end[i]);
					
					var second = endd.getTime()-startd.getTime();
					if(!isNaN(second)){
						xhsc++;
						totalCruiseTime = totalCruiseTime + (second/(3600*1000));
						
						if((startd.getHours()<6 || startd.getHours()>=18) && (endd.getHours()<6 || endd.getHours()>=18)){
							var se = endd.getTime()-startd.getTime();
							yehangTime += (se/(3600*1000));
							yhsc++;
						}else if((startd.getHours()<6 || startd.getHours()>=18)){
							var year = endd.getFullYear();
							var month = endd.getMonth()+1;
							var day = endd.getDate();
							yhsc++;
							var se = new Date(year+'-'+month+'-'+day+' 06:00').getTime()-startd.getTime();
							yehangTime += (se/(3600*1000));
						}else if((endd.getHours()<6 || endd.getHours()>=18)){
							var year = startd.getFullYear();
							var month = startd.getMonth()+1;
							var day = startd.getDate();
							yhsc++;
							var se = endd.getTime()-new Date(year+'-'+month+'-'+day+' 18:00').getTime();
							yehangTime += (se/(3600*1000));
						}else if((second/(3600*1000)) >= 12){
							yhsc++;
							yehangTime += 12;
						}
					}
					
				}
				
				//询问框
				var msg = '您当天累计巡航'+totalCruiseTime.toFixed(1)+'小时，巡航'+xhsc+'艘次，'+'累计夜航'+yehangTime.toFixed(1)+'小时，夜航'+yhsc+'艘次。请确认相关信息！'
				layer.confirm(msg, {
				  btn: ['确认','重填'] //按钮
				}, function(){
					$('#inputForm').submit();
				  layer.msg('提交中', {icon:3});
				});
			
			});
			function setIllegalTypeNumber(illegalType,dealWay){
				if(illegalType == 1){
					var num = parseInt($('#wfhxgd').val());
					$('#wfhxgd').val(num+1);
				}else if(illegalType == 2){
					var num = parseInt($('#wfpf').val());
					$('#wfpf').val(num+1);
				}else if(illegalType == 3){
					var num = parseInt($('#wfyzcs').val());
					$('#wfyzcs').val(num+1);
				}else if(illegalType == 4){
					var num = parseInt($('#wfsgzy').val());
					$('#wfsgzy').val(num+1);
				}else if(illegalType == 5){
					var num = parseInt($('#qtwfxw').val());
					$('#qtwfxw').val(num+1);
				}
				var fxwz = parseInt($('#fxwz').val());
				$('#fxwz').val(fxwz+1);
				if(dealWay == '2'){
					var clwz = parseInt($('#clwz').val());
					$('#clwz').val(clwz+1);
				}
			}
			
			$('input[type="text"]').focus(function(){
				$(this).val('');
			})
			layui.use('form', function(){
				  var form = layui.form();
				  
				  //监听提交
				  form.on('submit(formDemo)', function(data){
				    layer.msg(JSON.stringify(data.field));
				    return false;
				  });
				  
				});
			$('#jtzz').change(function(){
				layer.open({
					  type: 1,
					  title:"交通组织船名",
					  content: $('#jtzzdetails'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
					  btn: ['保存', '取消'],
					  area : ['400px' , '250px'],
					  yes:function(index,layero){
						 var input = $('#jtzznames').val();
						  if(input!='' && input!=null){
							  var d = "对"+input+"等船实施交通组织。";
							  var oldSolve = $('#solveProblem').val()+'\n'+d;
							  $('#solveProblem').val(oldSolve);
						  }
						  layer.msg('添加成功');
						  layer.close(index);
						 
					  }
				});
			});
			
			$('#cruiseRange').click(function(){
				 $.ajax({
		    	        type: "post",
		    	        dataType: "json",
		    	        url: '${ctx}/oa/oaCruisearea/getCruiseArea',
		    	        success: function (data) {
		    	           var dom = [
		    	        		   $('#zyhlform'),
		    	        		   $('#mdtbqform'),
		    	        		   $('#sgzyqform'),
		    	        		   $('#kdmtform'),
		    	        		   $('#pnqform'),
		    	        		   $('#wxpform'),
		    	        		   $('#qbqform'),
		    	        		   $('#qskform'),
		    	        		   $('#otherform')
		    	           ];
		    	           
		    	          setDomData(data,dom);
		    	          
		    	           var form = layui.form();
		    	           
		    	           form.render(); 	
		    	           form.on('switch(test)', function(data){
			 					  console.log(data.elem); //得到checkbox原始DOM对象
			 					  console.log(data.elem.checked); //开关是否开启，true或者false
			 					  console.log(data.value); //开关value值，也可以通过data.elem.value得到
			 					  console.log(data.othis); //得到美化后的DOM对象
			 					  var innormalArea = $(data.elem).parent().parent().find('label').html()
			 					  var oldVal = $('#inormal').val();
			 					  if(oldVal != '' && oldVal != null){
			 							oldVal = oldVal + '\n';
			 					  }
			 					  var details = oldVal + innormalArea + '异常情况:';
			 					  $('#inormal').val(details);

			 					});  
		    	           $('.layui-input').focus(function(){

		    					$(this).val('');
		    				})
		    	        }
		    	       
		    	    });
				
				layer.open({
					  type: 1,
					  title:"巡航范围",
					  content: $('#cruiseAreaSelected'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
					  btn: ['保存', '取消'],
					  area : ['600px' , '90%'],
					  yes:function(index,layero){

						  setCount();
						  layer.msg('添加成功');
						  layer.close(index);
						 
					  }
			});
			});
			$('#btnAddIllegal').click(function(){
				 layer.open({
					  type: 2,
					  title: '违法行为添加',
					  maxmin: true,
					  shadeClose: true, //点击遮罩关闭层
					  area : ['600px' , '90%'],
					  btn: ['保存','取消'],
					  content: '${adminPath}/zjgmsa/a/oa/oaIllegal/formInline',
					  yes:function(index, layero){
						  var body = layer.getChildFrame('body', index);
							var input = $(body.find('input'));
							var illegalDetailsVal = $(body.find('select')[0]).find("option:selected").val();
							var illegalTypeVal = $(body.find('select')[1]).find("option:selected").val();
							var dealwaysVal = $(body.find('select')[2]).find("option:selected").val();
							var illegalDetailsText = $(body.find('select')[0]).find("option:selected").text();
							var illegalTypeText = $(body.find('select')[1]).find("option:selected").text();
							var dealwaysText = $(body.find('select')[2]).find("option:selected").text();
							var happenLocation = $(body.find('input')[2]).val();
							var shipName = $(body.find('input')[1]).val();
							var beizhu = $(body.find('#beizhu')).val();
							var qtwf = $(body.find('#qtwf')).val();
							var illegal = new Object();
							var jsdata = new Object();
							illegal.illegalDetailsVal = illegalDetailsVal;
							illegal.dealwaysVal = dealwaysVal;
							illegal.illegalDetailsText = illegalDetailsText;
							illegal.dealwaysText = dealwaysText;
							illegal.happenLocation = happenLocation;
							illegal.shipName = shipName;
							illegal.illegalTypeVal = illegalTypeVal;
							illegal.illegalTypeText = illegalTypeText;
							illegal.beizhu = beizhu;
							illegal.qtwf = qtwf;
							myArray.push(illegal);
							layer.msg('添加成功');
							setIllegalTypeNumber(illegalTypeVal,dealwaysVal);
							var solveProblem = '在'+happenLocation+'发现"'+shipName+'"'+illegalDetailsText+dealwaysText+"。";
							if($('#solveProblem').val()!=''){
								var oldSolve = $('#solveProblem').val()+'\n'+solveProblem;
								$('#solveProblem').val(oldSolve);
							}else {
								$('#solveProblem').val(solveProblem);
							}
							
							
							jsdata.total = myArray.length;
							jsdata.rows = myArray;
							$('#tests').datagrid({
								collapsed:false
							});
							$('#tests').datagrid("loadData",jsdata);//将数据绑定到DataGrid中  

							layer.close(index);
							
					  },
					  end:function(){
						  
					  }
					  });
			});
			
		      
		  
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					$("#wzcblist").val(JSON.stringify(myArray));
					$("#cruisearealist").val(JSON.stringify(cruiseArray))
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
			
			/*var a = $(".cp")[0];
			alert(a);
			alert($(".cp").val());*/

		     /*   var ck_val = $("input[name='cp']").val().split(",");
		        $.each(ck_val, function(index, val) {
		            $(":checkbox[name='cp]'][value="+val+"]").prop("checked",true);
		        });*/
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
			 var date = new Date();
			 date.setDate(date.getDate()+1);
			 var today = timeStamp2String(date);
			 date.setDate(date.getDate() - 2);
			 var yesterday = timeStamp2String(date);
			
			 $('.form_datetime_start'+idx).datetimepicker({
			        language:  'zh-CN',
			        weekStart: 1,
			        todayBtn:  1,
					autoclose: 1,
					todayHighlight: 1,
					startView: 2,
					forceParse: 0,
					startDate:yesterday,
					endDate:today,
					format:"yyyy-mm-dd hh:ii"
			    }).on('changeDate',function(ev){
			    	var start_date = $('#oaCruisetimeList'+idx+'_startTime').val();
			    	$('.form_datetime_end'+idx).datetimepicker('setStartDate',start_date);
			    });
			  $('.form_datetime_end'+idx).datetimepicker({
			        language:  'zh-CN',
			        weekStart: 1,
			        todayBtn:  1,
					autoclose: 1,
					todayHighlight: 1,
					startView: 2,
					forceParse: 0,
					startDate:yesterday,
					endDate:today,
					format:"yyyy-mm-dd hh:ii"
			    }).on('changeDate',function(ev){
			    	var end_date = $('#oaCruisetimeList'+idx+'_endTime').val();
			    	$('.form_datetime_start'+idx).datetimepicker('setEndDate',end_date);
			    });
		}
		function timeStamp2String(time){  
		    var datetime = new Date();  
		    datetime.setTime(time);  
		    var year = datetime.getFullYear();  
		    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;  
		    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();  
		    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();  
		    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();  
		    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();  
		    return year + "-" + month + "-" + date;  
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
		var toolbar = [{
            text:'删除',
            iconCls:'icon-cut',
            handler:function(){
            	var row = $('#tests').datagrid('getSelected');
            	if (row){
            		var rowIndex=$('#tests').datagrid('getRowIndex',row)
            		myArray.splice(rowIndex,1); 
            		var jsdata = new Object();
            		jsdata.total = myArray.length;
					jsdata.rows = myArray;
					$('#tests').datagrid("loadData",jsdata);//将数据绑定到DataGrid中 
            	}
            }
        }];
		
		function getContent(i,j,k){
			var content = '<div class="layui-form-item">'+
		    	'<label style="width:120px;text-align:center" class="layui-form-label">'+i+'</label>'+
		    '<div class="layui-input-block">'+
			  '<input type="hidden" name="aretype[]" value="'+j+'">'+  
		    '<input checked type="checkbox" lay-filter="test" name="switch[]" lay-skin="switch" lay-text="正常|异常" value="'+k+'">'+
		    '<div class="layui-form-mid">'+
			  '<input type="text" value="0" name="numbers[]" style="width:100px" lay-verify="number" class="layui-input" placeholder="本日巡查次数">'+
		    '</div>'+
			  '<input type="hidden" name="appIds[]" value="'+k+'">'+
			  '<input type="hidden" name="areaContent[]" value="'+i+'">'+
	   		  '</div>'+
	   		'</div>';
	   		return content;
			
		}
		
		function setDomData(data,dom){
	        for(var i=0;i<9;i++){
	        	if(data[i+1]!=null){
		        	   dom[i].empty();
		        	   dom[i].parent().show();
		        	   for(var j=0;j<data[i+1].length;j++){
		        			dom[i].append(
		        		  				getContent(data[i+1][j].content,i+1,data[i+1][j].id)
		    	   					)
		        	   }
		           }else{
		        	   dom[i].parent().hide();
		           }
	        }  
			
		}
		
		function setCount(){
			 var zyhlcs = 0;
			 var mdtbqcs = 0;
			 var sgzyqcs = 0;
			 var kymtcs = 0;
			 var wxpmtcs = 0;
			 var pnqcs = 0;
			 var qskcs = 0;
			 var qbqcs = 0;
			 var qtcs = 0;
			  var a = $("#rangeform").serializeArray();
			  /* Because serializeArray() ignores unset checkboxes and radio buttons: */
			  a = a.concat(
			          jQuery('#rangeform input[type=checkbox]:not(:checked)').map(
			                  function() {
			                      return {"name": this.name, "value": 'off'}
			                  }).get());
			  var switchArr = new Object();
			  var count=0;
			  for(var i=0;i<a.length;i++){
				  if(a[i].name == 'switch[]'){
					  if(a[i] != 'off'){
						  switchArr[a[i].value] = 'on';
					  }else{
						  switchArr[a[i].value] = 'off';
					  }
					 
					  a[i] = 'deleted';
				  }
			  }
			  
			$.each(a,function(index,item){  

		            // index是索引值（即下标）   item是每次遍历得到的值；

		            if(item=='deleted'){
		           	        a.splice(index,1);

		        	    }

		     });
	           var areaArr = new Array();
	           for(var m=0;m<a.length-1;m=m+4){
	        	  if(a[m]!="deleted"){
	        		  var area = new Object();
		        	   area.type = a[m].value;
		        	   area.num = a[m+1].value;
		        	   area.appIds = a[m+2].value;
		        	   area.content = a[m+3].value;
		        	   area.normal = switchArr[a[m+2].value];
		        	   areaArr.push(area);
	        	  }
	        	  
	           }
	           
	           setContentDetails(areaArr);
	           for(var n=0;n<areaArr.length;n++){
	        	   var ty = areaArr[n].type;
	        	   switch(ty){
	        	   		case("1"):
	        	   			zyhlcs=zyhlcs+parseInt(areaArr[n].num);
	        	   			break;
	        	   		case("2"):
	        	   			mdtbqcs=mdtbqcs+parseInt(areaArr[n].num);
	        	   			break;
	        	   		case("3"):
	        	   			sgzyqcs=sgzyqcs+parseInt(areaArr[n].num);
	        	   			break;
	        	   		case("4"):
	        	   			kymtcs=kymtcs+parseInt(areaArr[n].num);
	        	   			break;
	        	   		case("5"):
	        	   			pnqcs=pnqcs+parseInt(areaArr[n].num);
	        	   			break;
	        	   		case("6"):
	        	   			wxpmtcs=wxpmtcs+parseInt(areaArr[n].num);
	        	   			break;
	        	   		case("7"):
	        	   			qbqcs=qbqcs+parseInt(areaArr[n].num);
	        	   			break;
	        	   		case("8"):
	        	   			qskcs=qskcs+parseInt(areaArr[n].num);
	        	   			break;
	        	   		case("9"):
	        	   			qtcs=qtcs+parseInt(areaArr[n].num);
	        	   			break;
	        	   		default:
	        	   			break;
	        	   }
	           }
	          $('#zyhl').val(zyhlcs);
	          $('#mdtbq').val(mdtbqcs);
	          $('#sgzyq').val(sgzyqcs);
	          $('#kymt').val(kymtcs);
	          $('#dangerPort').val(wxpmtcs);
	          $('#pnq').val(pnqcs);
	          $('#qsk').val(qskcs);
	          $('#qbq').val(qbqcs);
	          $('#otherArea').val(qtcs);
	          cruiseArray = areaArr;
		}
		
		function setContentDetails(areaArr){
			var oldVal = $('#solveProblem').val();
			var normalArea = '';
			var inormalArea = $('#inormal').val();
			for(var i=0;i<areaArr.length;i++){
				if(areaArr[i].normal == 'on' && areaArr[i].num > 0){
					normalArea = normalArea + areaArr[i].content+','
				}
			}
			if(normalArea != ''){
				if(oldVal != '' && oldVal != null){
					oldVal = oldVal + '\n';
				}
				var details = oldVal + '巡查' + normalArea.substring(0,normalArea.length-1) + '等水域作业正常。';
				$('#solveProblem').val(details);
			}
			if(inormalArea!=''){
				var o = $('#solveProblem').val();
				$('#solveProblem').val(o+'\n'+$('#inormal').val());
			}
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/oaCruisedata/">巡航数据列表</a></li>
		<li class="active"><a href="${ctx}/oa/oaCruisedata/form?id=${oaCruisedata.id}">巡航数据<shiro:hasPermission name="oa:oaCruisedata:edit">${not empty oaCruisedata.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:oaCruisedata:edit">查看</shiro:lacksPermission></a></li>
		<shiro:hasPermission name="oa:oaCruisedata:edit"><li><a href="${ctx}/oa/oaCruisedata/stat">巡航数据分类统计</a></li></shiro:hasPermission>
		
	</ul><br/>
	
	
	<form:form  id="inputForm" modelAttribute="oaCruisedata" action="${ctx}/oa/oaCruisedata/save" method="post" class="form-horizontal layui-form">
		<form:hidden path="id"/>
		<input id="wzcblist" name="wzcblist" type="hidden"/>
		<input id="cruisearealist" name="cruisearealist" type="hidden"/>
		
		<sys:message content="${message}"/>	
		<table class="table-form">
			<tr>
			<td colspan="8"><strong>巡航范围添加:</strong>
				<input id="cruiseRange" class="btn btn-primary" type="button" value="添加"/></td>
			</td>
			</tr>
			<tr>
					<td class="tit">重要航路：</td>
					<td style="width:10%">
						<form:input id="zyhl" path="zyhl" htmlEscape="false" maxlength="11" class="digits input-block-level"/>
					</td>
					<td class="tit">锚地停泊区：</td>
					<td style="width:10%">
						<form:input id="mdtbq" path="mdtbq" htmlEscape="false" maxlength="11" class="digits input-block-level"/>
					</td>
					<td class="tit">施工作业区：</td>
					<td style="width:10%">
						<form:input path="sgzyq" htmlEscape="false" maxlength="11" class="digits input-block-level"/>
					</td>
					<td class="tit">客运码头、渡口：</td>
					<td style="width:10%">
						<form:input path="kymt" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					
			</tr>
			<tr>
					<td class="tit">危险品码头、装卸作业区：</td>
					<td>
						<form:input path="dangerPort" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					<td class="tit">抛泥区：</td>
					<td>
						<form:input path="pnq" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					<td class="tit">取水口：</td>
					<td>
						<form:input path="qsk" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					<td class="tit">桥（坝）区：</td>
					<td>
						<form:input path="qbq" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
			</tr>
			
			<tr>
					<td class="tit">交通组织、护航：</td>
					<td>
						<form:input id="jtzz" path="jtzz" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					<td class="tit">发放宣传资料（份）：</td>
					<td>
						<form:input path="ffxczl" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>	
					<td class="tit">参与救助(次)：</td>
					<td>
						<form:input path="cyjz" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					<td class="tit">救助船舶(次)：</td>
					<td>
						<form:input path="jzcb" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					
			</tr>
			<tr>
					<td class="tit">救助人员(次)：</td>
					<td>
						<form:input path="jzry" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					<td class="tit">检查船舶（艘次）：</td>
					<td>
						<form:input path="psc" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
						<td class="tit">发现助航标志异常：</td>
					<td>
						<form:input path="zhbzyc" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					<td class="tit">其他必查水域：</td>
					<td>
						<form:input path="otherArea" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
			</tr>
			<tr>
				
					<td class="tit">备注：</td>
					<td colspan="7">
						<form:input path="remarks" htmlEscape="false" maxlength="11" class="input-block-level"/>
					</td>
			</tr>
			
			<tr>
				<td colspan="8">
					<strong>发现违章行为添加：</strong>				
					<input id="btnAddIllegal" class="btn btn-primary" type="button" value="添加"/></td>
				</td>
			</tr>
			</table>
			<table id="tests" title="违章船舶清单" class="easyui-datagrid" 
            data-options="singleSelect:true,toolbar:toolbar,collapsed:true,collapsible:true">
							<thead>
								<tr>
									<th data-options="field:'shipName',width:80">船名</th>
									<th data-options="field:'happenLocation',width:150">发生地点</th>
									<th data-options="field:'illegalTypeText',width:150">违法行为种类</th>
									<th data-options="field:'illegalDetailsText',width:250">具体违法行为</th>
									<th data-options="field:'dealwaysText',width:150">处置结果</th>
									<th data-options="field:'beizhu',width:150">备注</th>	
								</tr>
							</thead>
				</table>
			<table class="table-form">
			<tr>
					<td class="tit">违反航行规定行为：</td>
					<td style="width:10%">
						<form:input id="wfhxgd" path="wfhxgd" htmlEscape="false" maxlength="11" class="digits input-block-level"/>
					</td>
					<td class="tit">发现违法排放行为：</td>
					<td style="width:10%">
						<form:input id="wfpf" path="wfpf" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					
					<td class="tit">违法养殖、采砂行为：</td>
					<td style="width:10%">
						<form:input id="wfyzcs" path="wfyzcs" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					
					<td class="tit">其他违法行为：</td>
					<td style="width:10%">
						<form:input id="qtwfxw" path="qtwfxw" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
			</tr>
			
			<tr>
					<td class="tit">海巡艇发现违章：</td>
					<td>
						<form:input id="fxwz" path="fxwz" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					<td class="tit">海巡艇违章处理起数：</td>
					<td>
						<form:input id="clwz" path="clwz" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					
					<td class="tit">违法施工作业行为：</td>
					<td>
						<form:input id="wfsgzy" path="wfsgzy" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					
			</tr>
			
			<tr>
			<td class="tit">巡航水域：</td>
			<td colspan="7">
				<form:textarea path="cruiseArea" maxlength="1024" class="input-block-level required" rows="3" />
			</td>
			</tr>
			<tr>
					<td class="tit">随艇人员：</td>
					<td colspan="7">
						<ul class="dowebok">
						<c:forEach items="${cUsersSelected}" var="user">
							<li> <form:checkbox path="cruisePeople" checked="checked" title="${user.name}"  value="${user.name}" /></li>						</c:forEach>
						<c:forEach items="${cUsers}" var="user">
							<li> <form:checkbox path="cruisePeople"  title="${user.name}"  value="${user.name}" /></li>
						</c:forEach>
							
						</ul>
					</td>
					</tr>
				<tr>
					<td class="tit" colspan="3">
						其他补充人员(逗号作为分隔符)
					</td>
					<td colspan="3">
						<form:input path="qtry" htmlEscape="false" maxlength="255" class="input-block-level"/>					
					</td>	
					<td class="tit">
						共计出动执法人员次数：
					</td>
					<td colspan="1">
						<form:input path="cdzfrycs" htmlEscape="false" maxlength="255" class="digital input-block-level"/>					
					</td>						
				</tr>
				<tr>
					<td class="tit">发现解决的问题：</td>
					<td colspan="7">
						<form:textarea id="solveProblem" path="solveProblem" maxlength="1024" class="input-block-level required" rows="6" />
					</td>
				</tr>
				<tr>
				<td colspan="8">
				<strong>巡航时间段数据</strong>
				</td>：
				</tr>
				<tr>
				<td colspan="8">
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th style="text-decoration:none;text-align:center">巡航开始时间	</th>
								<th style="text-decoration:none;text-align:center">巡航结束时间</th>
								<shiro:hasPermission name="oa:oaCruisedata:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="oaCruisetimeList">
						</tbody>
						<shiro:hasPermission name="oa:oaCruisedata:edit"><tfoot>
							<tr><td colspan="5"><a href="javascript:" onclick="addRow('#oaCruisetimeList', oaCruisetimeRowIdx, oaCruisetimeTpl);oaCruisetimeRowIdx = oaCruisetimeRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
				</td>
				</tr>
				
			</table>
					<script type="text/template" id="oaCruisetimeTpl">//<!--
						
					<tr id="oaCruisetimeList{{idx}}">
							<td class="hide">
								<input id="oaCruisetimeList{{idx}}_id" name="oaCruisetimeList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="oaCruisetimeList{{idx}}_delFlag" name="oaCruisetimeList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td style="text-align:center">	
                				<div class="input-append date form_datetime_start{{idx}}"   data-link-field="oaCruisetimeList{{idx}}_startTime">
                   			 		<input size="12" type="text" value="{{row.startTime}}" readonly>
                    				<span class="add-on"><i class="icon-remove"></i></span>
									<span class="add-on"><i class="icon-th"></i></span>
                				</div>
								<input type="hidden" id="oaCruisetimeList{{idx}}_startTime" name="oaCruisetimeList[{{idx}}].startTime" value="{{row.startTime}}" />
								
							</td>
							<td style="text-align:center">
								<div  class="input-append date form_datetime_end{{idx}}"   data-link-field="oaCruisetimeList{{idx}}_endTime">
                   			 		<input  size="12" type="text" value="{{row.endTime}}"" readonly>
                    				<span class="add-on"><i class="icon-remove"></i></span>
									<span class="add-on"><i class="icon-th"></i></span>
                				</div>
								<input type="hidden" id="oaCruisetimeList{{idx}}_endTime" name="oaCruisetimeList[{{idx}}].endTime" value="{{row.endTime}}" />
							</td>
							<shiro:hasPermission name="oa:oaCruisedata:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#oaCruisetimeList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var oaCruisetimeRowIdx = 0, oaCruisetimeTpl = $("#oaCruisetimeTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(oaCruisedata.oaCruisetimeList)};
							for (var i=0; i<data.length; i++){
								addRow('#oaCruisetimeList', oaCruisetimeRowIdx, oaCruisetimeTpl, data[i]);
								oaCruisetimeRowIdx = oaCruisetimeRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>	
			
		<div class="form-actions">
			<shiro:hasPermission name="oa:oaCruisedata:edit"><c:if test="${oaCruisedata.editable}"><input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/></c:if>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<div style="display:none" id="jtzzdetails">
		<form class="layui-form layui-form-pane" action="">
			<div class="layui-form-item" pane>
	    		<label class="layui-form-label">交通组织船名</label>
				<textarea name="" id="jtzznames" required lay-verify="required" placeholder="请输入" class="layui-textarea"></textarea>
			</div>
		</form>
	</div>
	<div id="cruiseAreaSelected" style="display:none" >
		<form id="rangeform" class="layui-form" action="">
		<fieldset class="layui-elem-field">
		  <legend>重要航路</legend>
		  <div id="zyhlform" class="layui-field-box">
		  </div>
		</fieldset>
		<fieldset class="layui-elem-field">
		  <legend> 危险品码头、装卸作业区      </legend>
		  <div id="wxpform" class="layui-field-box">
		  </div>
		</fieldset>
		<fieldset class="layui-elem-field">
		  <legend>     施工作业区       </legend>
		  <div id="sgzyqform" class="layui-field-box">  	
		  </div>
		</fieldset>
		<fieldset class="layui-elem-field">
		  <legend>        取水口       </legend>
		  <div id="qskform" class="layui-field-box">	    	
		  </div>
		</fieldset>
		<fieldset class="layui-elem-field">
		  <legend> 锚地停泊区</legend>
		  <div id="mdtbqform" class="layui-field-box">
		  </div>
		</fieldset>
		<fieldset class="layui-elem-field">
		  <legend>客运码头、渡口</legend>
		  <div id="kdmtform" class="layui-field-box">
		  </div>
		</fieldset>
		<fieldset class="layui-elem-field">
		  <legend>其他每天必查水域  </legend>
		  <div id="otherform" class="layui-field-box">
		  </div>
		</fieldset>
		<fieldset class="layui-elem-field">
		  <legend>抛泥区  </legend>
		  <div id="pnqform" class="layui-field-box">
		  </div>
		</fieldset>
		<fieldset class="layui-elem-field">
		  <legend>桥(坝)区  </legend>
		  <div id="qbqform" class="layui-field-box">
		  </div>
		</fieldset>
		<fieldset class="layui-elem-field">
		  <legend>异常情况补充  </legend>
		  <div class="layui-field-box">
			<textarea name="inormal" id="inormal" placeholder="请输入异常" class="layui-textarea"></textarea>
					
					
		  </div>
		</fieldset>
		</form>
    </div>
  </div>	
	</div>
	</body>

</html>