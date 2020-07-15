<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/base.jsp" %>
<tiles:insertDefinition name="listTemplate">
<tiles:putAttribute name="queryBody">
	
</tiles:putAttribute>
<tiles:putAttribute name="afterForm">
<style>
	#print{
		display:none;
		padding:30px 50px;
	}
	.print-table{
		width:100%;
		border:1px solid #e6e6e6;
		table-layout:fixed
	}
	.print-table td{
		border:1px solid #e6e6e6;
		padding:10px 8px;
	}
	.table-fir-reason,.table-sec-reason{
		font-size:12px;
	}
	.table-item{
		color:#666;
	}
	.print-title{
		font-weight:700;
		font-size:18px;
		text-align:center;
	}
	.print-title{
		margin-bottom:10px;
	}
	.print-area{
		float:right;
		width:200px;
		text-align:right;
	}
	.cf{
		clear:both;
	}
	.print-ercode{
		height:45px;
		margin-bottom:3px;
	}
	.student-id{
		line-height:25px;
	}
	 @page {
      size: auto;  /* auto is the initial value */
      margin: 0mm; /* this affects the margin in the printer settings */
    }
	
	@media print{
		#print{display:block;}
		#app{display:none;}
	}
</style>
<div id="print">
	<div class="print-title">科目二考试成绩单</div>
	<div class="print-area">
		<img class="print-ercode" src="" >
	</div>
	<div class="cf"></div>
	<table cellspacing="0" cellpadding="0" border="0" class="print-table" width="100%">
		<tr>
			<td>姓名</td>
			<td colspan="2"  class="table-item table-name"></td>
			<td>准考证号</td>
			<td  class="table-item table-no"></td>
			<td rowspan="4"></td>
		</tr>
		<tr>
			<td>身份证号码</td>
			<td colspan="2"  class="table-item table-id"></td>
			<td>报考车型</td>
			<td  class="table-item table-car"></td>
		</tr>
		<tr>
			<td>业务类型</td>
			<td colspan="4"   class="table-item table-yewu-type"></td>
		</tr>
		<tr>
			<td>考试日期</td>
			<td colspan="2"  class="table-item table-exam-date"></td>
			<td>预约次数</td>
			<td  class="table-item table-num"></td>
		</tr>
		<tr>
			<td colspan="6"  align="center">科目二考试</td>
		</tr>
		<tr>
			<td>考试时间</td>
			<td colspan="2"   class="table-item table-fir-time"></td>
			<td>考试成绩</td>
			<td colspan="2"   class="table-item table-fir-score"></td>
		</tr>
		<tr>
			<td height="110">扣分项</td>
			<td colspan="5"  height="110"   class="table-item table-fir-reason"></td>
		</tr>
		<tr>
			<td>考试员签名</td>
			<td colspan="2"></td>
			<td>考生签名</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td height="150">考试视频照片（3张）</td>
			<td colspan="5" height="150" class="fir-img"></td>
		</tr>
		
		<tr>
			<td colspan="6" align="center">科目二补考</td>
		</tr>
		<tr>
			<td>考试时间</td>
			<td colspan="2" class="table-item table-sec-time"></td>
			<td>考试成绩</td>
			<td colspan="2" class="table-item table-sec-score"></td>
		</tr>
		<tr>
			<td height="110">扣分项</td>
			<td colspan="5" height="110" class="table-item table-sec-reason"></td>
		</tr>
		<tr>
			<td>考试员签名</td>
			<td colspan="2"></td>
			<td>考生签名</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td height="150">考试视频照片（3张）</td>
			<td colspan="5" height="150" class="sec-img">
			<img src="" width="100px" height="auto" style="margin-right:10px;">
			<img src="" width="100px" height="auto">
			</td>
		</tr>
		</table>
</div>
<script>
	layui.use('laydate', function(){
		var laydate = layui.laydate;
		  laydate.render({
			    elem: '#beginTime' //开始时间
		  });
		  laydate.render({
			    elem: '#endTime' //结束时间
		  });
	});
	
	//增加下级菜单
	function getFlow(obj){
		layui.use(['layer','table'], function(){
			var table = layui.table;
			var data = obj.data;
			layer.open({
		    	title:'考试明细:'+'<span class="layui-tx-green">'+data.col1+'-'+data.col2+'('+data.col11+')</span>',
		    	type: 2,
		    	closeBtn:1,
		        area: ['800px', '500px'],
		        offset: 't',
		        fixed: false, //不固定
		        maxmin: true,
		        scrollbar:false,
		        // moveOut :true,
	            anim: 5, //0-6的动画形式，-1不开启
	            content: '/w2/records/flow?ksbh='+data.col1+'&ksrq='+data.col11,
	            end:function(){
		        	if(window.tools.refresh){
		        		initFunc(true);
		        	}
		        }
		    });
		});
	}
	
	function printPage(obj){
		var data = radioData.data;
		if(data == undefined){
			layer.msg("请选择需打印的考试记录");
			return;
		}
	    $.ajax({
	        url:"/w2/records/getPrintImages",
	        type:'POST',
	        data: {id:data.col0,code:data.col3},//表单数据
	        beforeSend:function(){
	        	layer.load(2);
	        },
	        complete:function(){
	        	layer.closeAll('loading');
	        },
	        success:function(d){
	        	$(".print-ercode").attr('src','/static/code/'+data.col3+'.png')
	        	if(d.code=='0'){
	        		console.log(d.data)
		        	if(d.data[1] != undefined){
		        		var img1 = "";
		        		$.each(d.data[1],function(i,item){
		        			img1 += '<img src="/w2/records/showImage?id='+item+'" width="120" height="auto" class="img-item">'
		        		})
		        		$(".fir-img").html(img1);
		        	}
		        	if(d.data[2] != undefined){
		        		var img2 = "";
		        		$.each(d.data[2],function(i,item){
		        			img1 += '<img src="/w2/records/showImage?id='+item+'" width="120" height="auto" class="img-item">'
		        		})
		        		$(".sec-img").html(img2);
		        	}
	        	}else{
	        		//提交出错，记录错误日志
	        	}
	        	renderPrint(data);
	        	console.log("okokok");
	        	setTimeout(function(){
		        	window.print();
	        	},100)
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown){
	        	layer.msg("网络异常",{offset:offsetTop});
	        }
	    });
		
		
		
		
		/* window.location.href="/w2/records/print" */
	}
	
	function renderPrint(data){
		$("#print .table-name").text(data.col2)
		//$("#print .student-id").text(data.col3)
		$("#print .table-id").text(data.col3)
		$("#print .table-no").text(data.col4)
		$("#print .table-car").text(data.col7)
		$("#print .table-yewu-type").text(data.col8+'('+data.col24+')')
		$("#print .table-exam-date").text(data.col11)
		$("#print .table-num").text(data.col10+"次")
		if(data.col5 == "<span style='color:#999999'>未考试</span>"){
		
		}else{
			$("#print .table-fir-time").text(data.col12+'~'+data.col13)
			console.log(data.col14)
			if(data.col14 != ''){
				var fir_reason = data.col14.replace(/;/g,"</p>")
				$("#print .table-fir-reason").html('<p>'+fir_reason);
			}
			if(data.col5 == "<span style='color:#009688'>第一次考试合格</span>"){
				$("#print .table-fir-score").text("合格"+'('+data.col15+'分)');
			}else{
				if(data.col5 == "<span style='color:#009688'>第二次考试合格</span>"){
					$("#print .table-fir-score").text("不合格"+'('+data.col15+'分)');
					
					$("#print .table-sec-score").text("合格"+'('+data.col20+'分)');
				}else if(data.col5 == "<span style='color:#ff5722'>第二次考试不合格</span>"){
					$("#print .table-fir-score").text("不合格"+'('+data.col15+'分)');
					
					$("#print .table-sec-score").text("不合格"+'('+data.col20+'分)');
				}else if(data.col5 == "<span style='color:#ff5722'>第一次考试不合格</span>"){
					$("#print .table-fir-score").text("不合格"+'('+data.col15+'分)');
					
					$("#print .table-sec-score").text("不合格"+'('+data.col20+'分)');
				}
				if(data.col17 != ""){
					$("#print .table-sec-time").text(data.col17+'~'+data.col18)
				}
				if(data.col19 != ''){
					var sec_reason = data.col19.replace(/;/g,"</p>")
					$("#print .table-sec-reason").html('<p>'+sec_reason);
				}
			}
		}
	}
</script>
</tiles:putAttribute>

</tiles:insertDefinition>
