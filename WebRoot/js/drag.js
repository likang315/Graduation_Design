/* 
 * drag 1.0
 * create by tony@jentian.com
 * date 2015-08-18
 * 拖动滑块
 */
var slideEndFun ;//滑块触发事件
(function($){
    $.fn.drag = function(options){
    	try{
			if(options.slideEndFun != undefined)
				slideEndFun = options.slideEndFun;
		}catch(e){}
		
        var x, drag = this, isMove = false, defaults = {
        };
        var options = $.extend(defaults, options);
        //添加背景，文字，滑块
        var html = '<div class="drag_bg" id="drag_bg"></div>'+
                    '<div class="drag_text" onselectstart="return false;" unselectable="on">拖动滑块验证登陆</div>'+
                    '<div class="handler handler_bg" id="handler" ></div>';
        this.append(html);
        
        var handler = drag.find('.handler');
        var drag_bg = drag.find('.drag_bg');
        var text = drag.find('.drag_text');
        var maxWidth = drag.width() - handler.width();  //能滑动的最大间距
        
        
        
      
	   	if (screen.width >768){
	   	//=======================================================使用于电脑开始==============================================================//

	        //鼠标按下时候的x轴的位置
	        handler.mousedown(function(e){
	            isMove = true;
	            x = e.pageX - parseInt(handler.css('left'), 10);
	        });
	        
	        //鼠标指针在上下文移动时，移动距离大于0小于最大间距，滑块x轴位置等于鼠标移动距离
	        $(document).mousemove(function(e){
	            var _x = e.pageX - x;
	            if(isMove){
	                if(_x > 0 && _x <= maxWidth){
	                    handler.css({'left': _x});
	                    drag_bg.css({'width': _x});
	                }else if(_x > maxWidth){  //鼠标指针移动距离达到最大时清空事件
	                    dragOk();
	                    if(!slideEndFun()){
			            	   obj.style.left = "0px";
			            	   drag_bg.style.width = '0px';
			            	   handler.removeClass('handler_ok_bg').addClass('handler_bg');
			            	   drag.css({'color': '#000'});
			            	   text.text('拖动滑块验证登陆');
			            	   obj.addEventListener('touchend',handlerUP,true);
			            	   obj.addEventListener('touchmove',handlerMove,false);
			               };
	                }
	            }
	        }).mouseup(function(e){
	            isMove = false;
	            var _x = e.pageX - x;
	            if(_x < maxWidth){ //鼠标松开时，如果没有达到最大距离位置，滑块就返回初始位置
	                handler.css({'left': 0});
	                drag_bg.css({'width': 0});
	            }
	        });
	        
	        //清空事件
	        function dragOk(){
	            handler.removeClass('handler_bg').addClass('handler_ok_bg');
	            text.text('验证通过');
	            drag.css({'color': '#fff'});
	            handler.unbind('mousedown');
	            $(document).unbind('mousemove');
	            $(document).unbind('mouseup');
	        }
	        
	        //=======================================================使用于电脑结束==============================================================//
	        
	   		
	   		
	    }else{
	    	//=======================================================使用于手机开始==============================================================//
	        
	        function getById(e){
	        	return window.document.getElementById(e);
	        }
	        var obj = document.getElementById('handler');
	        var drag_bg = document.getElementById('drag_bg');
	        obj.addEventListener('touchmove',handlerMove , false);
	        
	        function handlerMove(event) {
	            // 如果这个元素的位置内只有一个手指的话
	           if (event.targetTouches.length == 1) {
	        	   event.preventDefault();// 阻止浏览器默认事件，重要 
	               var touch = event.targetTouches[0];
	               // 把元素放在手指所在的位置
	               obj.style.left = touch.pageX-50 + 'px';
	               drag_bg.style.width = touch.pageX-50 + 'px';
	               //obj.style.top = touch.pageY-50 + 'px';
	               console.log(obj.style.left)
	               if(obj.style.left.split('px')[0] <0){
	            	   obj.style.left = "0px";
	            	   drag_bg.style.width = '0px';
	               }
	               if(obj.style.left.split('px')[0] > 259){
	            	   obj.style.left = "260px";
		               handler.removeClass('handler_bg').addClass('handler_ok_bg');
		               text.text('登陆验证中。。。');
		               drag.css({'color': '#fff'});
		               obj.removeEventListener('touchend',handlerUP,true);
		               obj.removeEventListener('touchmove',handlerMove,false);
		               if(!slideEndFun()){
		            	   obj.style.left = "0px";
		            	   drag_bg.style.width = '0px';
		            	   handler.removeClass('handler_ok_bg').addClass('handler_bg');
		            	   drag.css({'color': '#000'});
		            	   text.text('拖动滑块验证登陆');
		            	   obj.addEventListener('touchend',handlerUP,true);
		            	   obj.addEventListener('touchmove',handlerMove,false);
		               };
	               }
	           }
	       }
	        
	       obj.addEventListener('touchend',handlerUP,false )
	        
	        function handlerUP(event) {
	        	 if( obj.style.left.split('px')[0] < 260 ){
	        		 obj.style.left = 0 + 'px';
	        		 drag_bg.style.width = '0px';
	        	 }
	        }
	     
	        
	        //=======================================================使用于手机结束==============================================================//
	        
	   		
	   	}
	   	
        
        
        
        
        
        //=======================================================使用于电脑开始==============================================================//

       /* //鼠标按下时候的x轴的位置
        handler.mousedown(function(e){
            isMove = true;
            x = e.pageX - parseInt(handler.css('left'), 10);
        });
        
        //鼠标指针在上下文移动时，移动距离大于0小于最大间距，滑块x轴位置等于鼠标移动距离
        $(document).mousemove(function(e){
            var _x = e.pageX - x;
            if(isMove){
                if(_x > 0 && _x <= maxWidth){
                    handler.css({'left': _x});
                    drag_bg.css({'width': _x});
                }else if(_x > maxWidth){  //鼠标指针移动距离达到最大时清空事件
                    dragOk();
					API.setStore("yanzheng",true);
					$("#hint1").hide();
                }
            }
        }).mouseup(function(e){
            isMove = false;
            var _x = e.pageX - x;
            if(_x < maxWidth){ //鼠标松开时，如果没有达到最大距离位置，滑块就返回初始位置
                handler.css({'left': 0});
                drag_bg.css({'width': 0});
            }
        });
        
        //清空事件
        function dragOk(){
            handler.removeClass('handler_bg').addClass('handler_ok_bg');
            text.text('验证通过');
            drag.css({'color': '#fff'});
            handler.unbind('mousedown');
            $(document).unbind('mousemove');
            $(document).unbind('mouseup');
        }*/
        
        //=======================================================使用于电脑结束==============================================================//
        
        //=======================================================使用于手机开始==============================================================//
        
       /* function getById(e){
        	return window.document.getElementById(e);
        }
        var obj = document.getElementById('handler');
        var drag_bg = document.getElementById('drag_bg');
        obj.addEventListener('touchmove',handlerMove , false);
        
        function handlerMove(event) {
            // 如果这个元素的位置内只有一个手指的话
           if (event.targetTouches.length == 1) {
        	   event.preventDefault();// 阻止浏览器默认事件，重要 
               var touch = event.targetTouches[0];
               // 把元素放在手指所在的位置
               obj.style.left = touch.pageX-50 + 'px';
               drag_bg.style.width = touch.pageX-50 + 'px';
               //obj.style.top = touch.pageY-50 + 'px';
               console.log(obj.style.left)
               if(obj.style.left.split('px')[0] <0){
            	   obj.style.left = "0px";
            	   drag_bg.style.width = '0px';
               }
               if(obj.style.left.split('px')[0] > 259){
            	   obj.style.left = "260px";
	               handler.removeClass('handler_bg').addClass('handler_ok_bg');
	               text.text('验证通过');
	               drag.css({'color': '#fff'});
	               obj.removeEventListener('touchend',handlerUP,true);
	               obj.removeEventListener('touchmove',handlerMove,false);
	               API.setStore("yanzheng",true); 
               }
           }
       }
        
       obj.addEventListener('touchend',handlerUP,false )
        
        function handlerUP(event) {
        	 if( obj.style.left.split('px')[0] < 260 ){
        		 obj.style.left = 0 + 'px';
        		 drag_bg.style.width = '0px';
        	 }
        }
     */
        
        //=======================================================使用于手机结束==============================================================//
        
    };
})(jQuery);


