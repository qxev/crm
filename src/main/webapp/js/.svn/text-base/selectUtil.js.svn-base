function changeProject(){
	var url = "jsonProject.action";
	var myAjax = new Ajax.Request(url,
	{
		method:'post',
		parameters:"clientId="+document.getElementById("clientId").value,
		onComplete:projcetProcessResponse,
		asynchronous:true
	});
}
	 
function projcetProcessResponse(request){
 	var res = JSON.parse(request.responseText);
	var target = document.getElementById("projectId");
	var size = target.length
	for (var i=0;i<size;i++){
		target.remove(0);
	}
	target.options.add(new Option("全部","0")); 
 	for (var box in res)
 	{
 		for (var project in res[box]){
 			if (res[box][project].id!=null){
 				target.options.add(new Option(res[box][project].name,res[box][project].id)); 
	 		}
 		}
	}
}

function changeChannel(){
	var url = "jsonChannel.action";
	var myAjax = new Ajax.Request(url,
	{
		method:'post',
		parameters:"mediaId="+document.getElementById("mediaId").value,
		onComplete:channelProcessResponse,
		asynchronous:true
	});
}

function changePm(){
	var url = "jsonPm.action";
	var myAjax = new Ajax.Request(url,
	{
		method:'post',
		parameters:"businessTypeId="+document.getElementById("businessTypeId").value,
		onComplete:pmProcessResponse,
		asynchronous:true
	});
}

function pmProcessResponse(request){
 	var res = JSON.parse(request.responseText);
	var target = document.getElementById("pmId");
	var size = target.length
	for (var i=0;i<size;i++){
		target.remove(0);
	}
	target.options.add(new Option("全部","0")); 
 	for (var box in res)
 	{
 		for (var pm in res[box]){
 			if (res[box][pm].id!=null){
 				target.options.add(new Option(res[box][pm].name,res[box][pm].id)); 
	 		}
 		}
	}
}
	 
function channelProcessResponse(request){
 	var res = JSON.parse(request.responseText);
	var target = document.getElementById("channelId");
	var size = target.length
	for (var i=0;i<size;i++){
		target.remove(0);
	}
	target.options.add(new Option("全部","0")); 
 	for (var box in res)
 	{
 		for (var channel in res[box]){
 			if (res[box][channel].id!=null){
 				target.options.add(new Option(res[box][channel].name,res[box][channel].id)); 
	 		}
 		}
	}
}

function addSrcToDestList(leftId,rightId)	{
	srcList = document.getElementById(leftId);
	destList = document.getElementById(rightId);
  	var len = destList.length;
  	for(var i = 0; i < srcList.length; i++) {
  		if ((srcList.options[i]!= null)&&(srcList.options[i].selected)){
			var found = false;
			for(var count = 0;count<len;count++){
				if (destList.options[count]!=null){	
					if (srcList.options[i].text == destList.options[count].text){
						found = true;
						break;   
	              	}   
		        }   
		  	}
		  	if (found != true){
		  		destList.options[len] = new Option(srcList.options[i].text,srcList.options[i].value);   
				len++;   
	        }   
    	}   
	}   
}   
    
function deleteFromDestList(rightId) {
	var destList = document.getElementById(rightId);
	var len = destList.options.length;
	for(var i = (len-1); i >= 0; i--) {
		if ((destList.options[i]!=null)&&(destList.options[i].selected==true)){
			destList.options[i]	= null;
		}
	}   
}   