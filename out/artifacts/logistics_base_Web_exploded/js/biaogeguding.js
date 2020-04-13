// JavaScript Document

$(document).ready(function() {
	 var left=$(".leftTable tr");
	 var right=$(".rightTable tr");
	 for(i = 0; i < left.length; i++){
		 var leftHeight = left[i].offsetHeight;
		 var rightHeight = right[i].offsetHeight;
		 if(leftHeight > rightHeight){
			 right[i].style.height = leftHeight + "px";
			 left[i].style.height = leftHeight + "px";
		 } else {
			 left[i].style.height = rightHeight + "px";
			 right[i].style.height = rightHeight + "px";
		 }
	 }
	
	$("#rightDiv").scroll(function() {
		var scrollTop=$("#rightDiv").scrollTop();
        var scrollLeft=$("#rightDiv").scrollLeft();
		$("#leftDiv").scrollTop(scrollTop);
		$("#rightTopDiv").scrollLeft(scrollLeft);
    });
	
	$(".tdphcs").css("width",60);
	
	var material_names = $("#material_names").children();
	var tdNameNum = $("#tdNameNum").children();
	for(var i = 0; i < material_names.length; i++){
		var myWidth = material_names[i].offsetWidth;
		var tdNameWidth = tdNameNum[2 + i].offsetWidth;
		if(myWidth > tdNameWidth){
			tdNameNum[2 + i].style.width = myWidth + "px";
			material_names[i].style.width = myWidth + "px";
		} else{
			material_names[i].style.width = tdNameWidth + "px";
			tdNameNum[2 + i].style.width = tdNameWidth + "px";
		}
	}
});









