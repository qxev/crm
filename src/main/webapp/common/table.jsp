<%@ page contentType="text/html;charset=UTF-8"%>
<script type="text/javascript" src="${ctx}/js/prototype-1.6.0.3.js"></script>
<script type="text/javascript" src="${ctx}/js/selectUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/json2.js"></script>
<script type="text/javascript">
function userSubmit(action){
	document.getElementById(action).submit();
}
function goto(action){
	var go = document.getElementsByName("go")[0].value;
	if (go==''){
		go = document.getElementsByName("go")[1].value;
	}
    document.location = action + '?page.pageNo='+go+'&page.filter=${page.filter}&pageSize=${pageSize}&page.order=${page.order}&page.orderBy=${page.orderBy}';
}
function gotoB(action){
	var go = document.getElementsByName("go")[0].value;
	if (go==''){
		go = document.getElementsByName("go")[1].value;
	}
    document.location = action + '?page.pageNo='+go+'&page.filter=${page.filter}&pageSize=${pageSize}&page.order=${page.order}&page.orderBy=${page.orderBy}&businessTypeId=${businessTypeId}';
}
function excelOutput(action){
	var chbx = $$('.chbx');
	var columns= '';
	var hasCheck = false;
	chbx.each(
		function(node) {
			if(!node.checked) {
	 			columns += node.value + ',';
	 		} else {
	 			hasCheck = true;
	 		}
	 	}
	);
	columns = columns.lastIndexOf(',') == columns.length - 1 ? columns.substring(0,columns.length - 1) : columns;
	document.location = action + '?page.filter=${page.filter}&pageSize=${pageSize}&page.order=${page.order}&page.orderBy=${page.orderBy}&page.columns='+columns;
}
</script>