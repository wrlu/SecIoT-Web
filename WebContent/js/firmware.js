function onAnalysis() {
	var formData = new FormData(document.getElementById("uploadForm"));
//	显示加载模态框
	$("#loadingModel").modal("show");
	$.ajax({
	      type:"POST",
	      url:"/SecIoT/fw/analysis",
	      data:formData,
	      mimeType:"multipart/form-data",
	      contentType: false,
	      cache: false,
	      processData: false,
	      success: function (result) {
//	    	    隐藏加载模态框
	    	 $("#loadingModel").modal("hide");
	    	 var result=JSON.parse(result);
	     	 if(result.status == 0) {
//	     		 填充固件基本信息
	     		fw_basic_info = '<strong class="d-block text-gray-dark">固件基本信息</strong>';
	     		fw_basic_info += '文件名：'+result.fw_info.fw_name+'<br/>';
	     		fw_basic_info += '文件大小：'+result.fw_info.fw_size+'字节';
	     		$("#fw_basic_info").html(fw_basic_info, function() {});
//	     		填充文件系统信息
	     		fw_filesystem = '<strong class="d-block text-gray-dark">固件文件系统</strong>';
	     		fw_filesystem += '文件系统：'+result.fw_info.fw_filesystem+'<br/>';
	     		fw_filesystem += '文件系统根目录：'+result.fw_info.fw_root_directory;
	     		$("#fw_filesystem").html(fw_filesystem, function() {});
//	     		填充第三方库信息
	     		var fw_third_library = '<h6 class="border-bottom border-gray pb-2 mb-0">第三方库风险</h6>';
	     		for (var i = 0, len = result.fw_lib.length; i < len; ++i) {
	     			var fw_per_third_library = '<div class="media text-muted pt-3"><p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">';
	     			fw_per_third_library += '<strong class="d-block text-gray-dark">'+result.fw_lib[i].lib_name+'</strong>';
	     			fw_per_third_library += '库位置：'+result.fw_lib[i].lib_path + '<br/>';
     				fw_per_third_library += '库版本：'+result.fw_lib[i].lib_version + '<br/>';
     				var fw_per_risk = result.fw_lib_risk[result.fw_lib[i].lib_name]
     				fw_per_third_library += '此版本的库存在的漏洞：<br/>';
	     			for (var j = 0, lenr = fw_per_risk.length; j < lenr; ++j ){
	     				fw_per_third_library += fw_per_risk[j].cve_num + '<br/>';
	     			}
     				fw_per_third_library += '</p></div>';
     				fw_third_library += fw_per_third_library;
	     		}
	     		$("#fw_third_library").html(fw_third_library, function() {});
//				显示平台风险
	     		var fw_platform_risk = '<h6 class="border-bottom border-gray pb-2 mb-0">平台风险</h6>';
	     		for (var i = 0, len = result.fw_platform_risk.length; i < len; ++i) {
	     			var fw_per_platform_risk = '<div class="media text-muted pt-3"><p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">';
	     			fw_per_platform_risk += '<strong class="d-block text-gray-dark">'+result.fw_platform_risk[i].risk_name+'</strong>';
	     			fw_per_platform_risk += '风险是否存在：'+result.fw_platform_risk[i].risk_exists + '<br/>';
	     			fw_per_platform_risk += '风险描述：'+result.fw_platform_risk[i].risk_description + '<br/>';
	     			fw_per_platform_risk += '风险等级：'+result.fw_platform_risk[i].risk_level + '<br/>';
	     			fw_per_platform_risk += '风险适用平台：'+result.fw_platform_risk[i].risk_platform + '<br/>';
	     			fw_per_platform_risk += '风险技术细节（供专业人员参考）：<br/>';
	     			for (var detail_name in result.fw_platform_risk[i].risk_details){
	     				fw_per_platform_risk += detail_name + '：<br/>';
	     				var risk_detail = result.fw_platform_risk[i].risk_details[detail_name];
	     				for (var j = 0, lenr = risk_detail.length; j < lenr; ++j) {
	     					fw_per_platform_risk += result.fw_platform_risk[i].risk_details[detail_name][j] + '<br/>';
	     				}
	     			}
	     			fw_per_platform_risk += '</p></div>';
	     			fw_platform_risk += fw_per_platform_risk;
	     		}
	     		$("#fw_platform_risk").html(fw_platform_risk, function() {});
//	     		填充并显示结果提示模态框
	     		$("#resultModalBody").html("针对 "+result.fw_info.fw_name+" 的分析完成", function() {});
	     		$("#resultModal").modal("show");
	     	 } else {
//	     		 填充并显示结果提示模态框，提示错误
	     		$("#resultModalBody").html(result.reason, function() {});
	     		$("#resultModal").modal("show");
	     	 }
	      },
	      error: function(error){
//	    	     隐藏加载模态框
		    	$("#loadingModel").modal("hide");
//	     		 填充并显示结果提示模态框，提示错误
	     		$("#resultModalBody").html(error, function() {});
	     		$("#resultModal").modal("show");
	      }
	});

}