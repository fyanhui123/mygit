var d
//url :ajax 请求url ;name 页面一组 checkbox共同的名字(html元素);showid 指定页面显示dtree的html元素id
function makeCheckBoxDtrer(url, name, showid,jsEvt) {
	d = new dTreeNew('d');    
	
	$.ajax(
			   {
				   type: "post",
				   url: url,
				   //data: "name="+name,
				   async:false,
				   success: function(data)
				   			{ 
								   $.each(
										  data,function(commentIndex, comment) {  
													  var htmls='';
													  if(comment.pid!=-1){
															  htmls = "<input onclick='javascript:d.checkselect(" + comment.id + ",true)' type='checkbox'   name='" + name + "' ";
															  htmls += " id='cc" + comment.id + "' ";	
															  
															  if(comment.value!=null)
															  htmls += " value='" + comment.value + "' ";
															  
															  if(comment.jsEvt!=null)
																  jsEvt=comment.jsEvt;
															  
															  if(jsEvt&&''!=jsEvt)
															  htmls += " " + jsEvt + " ";
															  
															  htmls += " />"; 
													  }		  
		 
													  if(comment.info!=null){
														  htmls += "<a href='javascript: d.o(" + comment.id + ")'>";
														  htmls += comment.info;
														  htmls += "</a>";
													  } 
													  if(Number(comment.pid)<2){
													  		d.add(comment.id,comment.pid,htmls,'','',true);   
													  }else{
													  		d.add(comment.id,comment.pid,htmls);         
													  }                           
										  		}
										  ) 

				   			}
			   },
			   "json"
		  );  
	$('#'+showid).html(d.toString());
}

function makeCheckBoxDtree(url, name, showid, jsEvt) {
	d = new dTreeNew('d');

	$.ajax( {
		type : "post",
		url : url,
		// data: "name="+name,
		async : false,
		success : function(data) {
			$.each(data, function(commentIndex, comment) {
				var htmls = '';
				htmls = "<input type='checkbox'   name='"
						+ name + "' ";
				htmls += " id='cc" + comment.id + "' ";

				if (comment.value != null)
					htmls += " value='" + comment.value + "' ";

				if (comment.jsEvt != null)
					jsEvt = comment.jsEvt;

				if (jsEvt && '' != jsEvt)
					htmls += " " + jsEvt + " ";

				htmls += " />";

				if (comment.info != null) {
					htmls += "<a href='javascript: d.o(" + comment.id + ")'>";
					htmls += comment.info;
					htmls += "</a>";
				}
				if (Number(comment.pid) < 2) {
					d.add(comment.id, comment.pid, htmls, '', '', true);
				} else {
					d.add(comment.id, comment.pid, htmls);
				}
			})

		}
	}, "json");
	$('#' + showid).html(d.toString());
}