function onAnalysis() {
	var formData = new FormData(document.getElementById("uploadForm"));
	$.ajax({
	      type:"POST",
	      url:"/SecIoT/android/analysis",
	      data:formData,
	      mimeType:"multipart/form-data",
	      contentType: false,
	      cache: false,
	      processData: false,
	      success: function (result) {
	    	 $("#loadingModel").modal("hide");
	    	 var result=JSON.parse(result);
	     	 if(result.status == 0) {
//	     		 填充APK基本信息
		     	apk_basic_info = '<strong class="d-block text-gray-dark">APK基本信息</strong>';
		     	apk_basic_info += '文件名：'+result.apk_info.apk_name+'<br/>';
		     	apk_basic_info += '文件大小：'+result.apk_info.apk_size+'字节<br/>';
		     	apk_basic_info += 'NDK支持的架构：'+result.apk_info.apk_ndk_lib_support_abis;
		     	$("#apk_basic_info").html(apk_basic_info, function() {});
//		     	填充权限信息
		     	apk_permission = '<strong class="d-block text-gray-dark">APK所需权限</strong>';
		     	for (var i = 0, len = result.apk_permission.length; i < len; ++i) {
		     		apk_permission += result.apk_permission[i]+'<br/>';
		     	}
		     	$("#apk_permission").html(apk_permission, function() {});
//				显示平台风险
		     	var apk_platform_risk = '<h6 class="border-bottom border-gray pb-2 mb-0">平台风险</h6>';
		     	for (var i = 0, len = result.apk_platform_risk.length; i < len; ++i) {
		     		var apk_per_platform_risk = '<div class="media text-muted pt-3"><p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">';
		     		apk_per_platform_risk += '<strong class="d-block text-gray-dark">'+result.apk_platform_risk[i].risk_name+'</strong>';
		     		apk_per_platform_risk += '风险是否存在：'+result.apk_platform_risk[i].risk_exists + '<br/>';
		     		apk_per_platform_risk += '风险描述：'+result.apk_platform_risk[i].risk_description + '<br/>';
		     		apk_per_platform_risk += '风险等级：'+result.apk_platform_risk[i].risk_level + '<br/>';
		     		apk_per_platform_risk += '风险适用平台：'+result.apk_platform_risk[i].risk_platform + '<br/>';
		     		apk_per_platform_risk += '风险技术细节（供专业人员参考）<br/>';
		     		for (var detail_name in result.apk_platform_risk[i].risk_details){
		     			apk_per_platform_risk += detail_name + '：<br/>';
		     			var risk_detail = result.apk_platform_risk[i].risk_details[detail_name];
		     			for (var j = 0, lenr = risk_detail.length; j < lenr; ++j) {
		     				apk_per_platform_risk += result.apk_platform_risk[i].risk_details[detail_name][j] + '<br/>';
		     			}
		     		}
		     		apk_per_platform_risk += '</p></div>';
		     		apk_platform_risk += apk_per_platform_risk;
		     	}
		     	$("#apk_platform_risk").html(apk_platform_risk, function() {});
	     		$("#resultModalBody").html("针对 "+result.apk_info.apk_name+" 的分析完成", function() {});
	     		$("#resultModal").modal("show");
	     	 } else {
	     		$("#resultModalBody").html(result.reason, function() {});
	     		$("#resultModal").modal("show");
	     	 }
	      }
	});
	$("#loadingModel").modal("show");
}

