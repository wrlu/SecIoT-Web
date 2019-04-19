function onAnalysis() {
	var formData = new FormData(document.getElementById("uploadForm"));
	$.ajax({
	      type:"POST",
	      url:"/SecIoT/traffic/analysis",
	      data:formData,
	      mimeType:"multipart/form-data",
	      contentType: false,
	      cache: false,
	      processData: false,
	      success: function (result) {
	    	 $("#loadingModel").modal("hide");
	    	 var result=JSON.parse(result);
	     	 if(result.status == 0) {
	     		var tfc_dev_conn_details = '<strong class="d-block text-gray-dark">IoT设备传输交互</strong>';
	     		for (var i = 0, len = result.tfc_dev_conn_details.pair_hosts.length; i < len; ++i) {
	     			var host = result.tfc_dev_conn_details.pair_hosts[i];
	     			var protocol = result.tfc_dev_conn_details.pair_connection_protocol[i];
	     			tfc_dev_conn_details += '目标：'+host+'; 协议：'+protocol+'<br/>';
	     		}
	     		$("#tfc_dev_conn_details").html(tfc_dev_conn_details);
	     		var tfc_mobile_conn_details = '<strong class="d-block text-gray-dark">移动设备传输交互</strong>';
	     		for (var i = 0, len = result.tfc_mobile_conn_details.pair_hosts.length; i < len; ++i) {
	     			var host = result.tfc_mobile_conn_details.pair_hosts[i];
	     			var protocol = result.tfc_mobile_conn_details.pair_connection_protocol[i];
	     			tfc_mobile_conn_details += '目标：'+host+'; 协议：'+protocol+'<br/>';
	     		}
	     		$("#tfc_mobile_conn_details").html(tfc_mobile_conn_details);
	     		$("#resultModalBody").html("针对此流量包的分析完成", function() {});
	     		$("#resultModal").modal("show");
	     	 } else {
	     		$("#resultModalBody").html(result.reason, function() {});
	     		$("#resultModal").modal("show");
	     	 }
	      }
	});
	$("#loadingModel").modal("show");
}

