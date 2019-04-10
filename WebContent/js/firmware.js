function onAnalysis() {
	var formData = new FormData(document.getElementById("uploadForm"));
	$.ajax({
	      type:"POST",
	      url:"/SecIoT/fw/analysis",
	      data:formData,
	      mimeType:"multipart/form-data",
	      contentType: false,
	      cache: false,
	      processData: false,
	      success: function (result) {
	    	 $("#loadingModel").modal("hide");
	    	 var result=JSON.parse(result);
	     	 if(result.status == 0) {
	     		tbody = "<tr>";
	     		tbody += "<td>1</td>";
	     		tbody += "<td>"+result.fw_info.fw_name+"</td>";
	     		tbody += "<td>"+result.fw_info.fw_filesystem+"</td>";
	     		tbody += "<td>"+result.third_lib_info[0].lib_avaliable+"</td>";
	     		tbody += "<td>"+result.third_lib_info[0].lib_path+"</td>";
	     		tbody += "<td>"+result.third_lib_info[0].lib_version+"</td>";
	     		tbody += "</tr>";
	     		$("#fw_result").html(tbody, function() {});
	     		$("#resultModalBody").html("针对 "+result.fw_info.fw_name+" 的分析完成", function() {});
	     		$("#resultModal").modal("show");
	     	 } else {
	     		$("#resultModalBody").html(result.reason, function() {});
	     		$("#resultModal").modal("show");
	     	 }
	      }
	});
	$("#loadingModel").modal("show");
}

