function agent(v) { return(Math.max(navigator.userAgent.toLowerCase().indexOf(v),0)); }
function isset(v) { return((typeof(v)=='undefined' || v.length==0)?false:true); }
function XYwin(v) { var z=agent('msie')?Array(document.body.clientHeight,document.body.clientWidth):Array(window.innerHeight,window.innerWidth); return(isset(v)?z[v]:z); }
function sexyTOG() { document.onclick=function(){ document.getElementById('sexyBG').style.display='none'; 
					 document.getElementById('sexyBOX').style.display='none'; document.onclick=function(){}; }; }
function sexyBOX(t,v,b) { 
	document.getElementById('sexyBG').style.display='block'; 
	document.getElementById('sexyBOX').innerHTML="<div class=\"sexyT\">&nbsp;&nbsp;"+t+"<\/div>"+document.getElementById(v).innerHTML+"<\/div>"; 
	document.getElementById('sexyBOX').style.left=Math.round((XYwin(1)-b)/2)+'px'; 
	document.getElementById('sexyBOX').style.width=b+'px'; 
	document.getElementById('sexyBOX').style.display='block'; 
}
window.onload=function(){
	document.body.innerHTML="<div id=\"sexyBG\"></div><div id=\"sexyBOX\"></div>"+document.body.innerHTML;
}