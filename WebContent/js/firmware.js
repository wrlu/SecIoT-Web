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
	     		fw_third_library = '<h6 class="border-bottom border-gray pb-2 mb-0">第三方库风险</h6>';
	     		var i = 0;
	     		while(i < result.third_lib_info_size) {
	     			fw_per_third_library = '<div class="media text-muted pt-3"><p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">';
	     			fw_per_third_library += '<strong class="d-block text-gray-dark">'+result.third_lib_info[i].lib_name+'</strong>';
	     			fw_per_third_library += '库位置：'+result.third_lib_info[i].lib_avaliable+'<br/>';
	     			fw_per_third_library += '库位置：'+result.third_lib_info[i].lib_path+'<br/>';
	     			fw_per_third_library += '库版本：'+result.third_lib_info[i].lib_version;
	     			fw_per_third_library += '</p></div>';
	     			fw_third_library += fw_per_third_library;
	     			++i;
	     		}
	     		$("#fw_third_library").html(fw_third_library, function() {});
//	     		填充并显示结果提示模态框
	     		$("#resultModalBody").html("针对 "+result.fw_info.fw_name+" 的分析完成", function() {});
	     		$("#resultModal").modal("show");
	     	 } else {
//	     		 填充并显示结果提示模态框，提示错误
	     		$("#resultModalBody").html(result.reason, function() {});
	     		$("#resultModal").modal("show");
	     	 }
	      }
	});
//	显示加载模态框
	$("#loadingModel").modal("show");
}
