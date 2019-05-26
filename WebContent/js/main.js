$(function(){
	var args = getArgs();
	var mainPage = "/SecIoT/?page=main";
	if (typeof(args.page) == "undefined" || args.page == null) {
		window.location.href = mainPage;
	} else {
		$("#main").load("/SecIoT/pages/"+args.page+".html", function(response, status, xhr) {
			if (status == "success") {
				$(document).attr("title",$("#main_title").html() + " - SecIoT");
				$("#"+args.page+"_nav").addClass("active");
			} else {
				$(document).attr("title", "出错了 - SecIoT");
				var errorMsg = '<br/><div class="alert alert-danger" role="alert">温馨提示：很抱歉，此页无法加载，请尝试刷新或<a href="'+mainPage+'">返回首页</a></div>'
				$("#main").html(errorMsg);
			}
		});
		if (args.page == "android-dym") {
			onRefreshDeviceList();
		}
	}
});

function getArgs(){
    var args = {};
    var match = null;
    var search = decodeURIComponent(location.search.substring(1));
    var reg = /(?:([^&]+)=([^&]+))/g;
    while((match = reg.exec(search))!==null){
        args[match[1]] = match[2];
    }
    return args;
}

function onFwAnalysis() {
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
	     		$("#fw_basic_info").html(fw_basic_info);
//	     		填充文件系统信息
	     		fw_filesystem = '<strong class="d-block text-gray-dark">固件文件系统</strong>';
	     		fw_filesystem += '文件系统：'+result.fw_info.fw_filesystem+'<br/>';
	     		fw_filesystem += '文件系统根目录：'+result.fw_info.fw_root_directory;
	     		$("#fw_filesystem").html(fw_filesystem);
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
	     		$("#fw_third_library").html(fw_third_library);
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
	     		$("#fw_platform_risk").html(fw_platform_risk);
//	     		填充并显示结果提示模态框
	     		$("#resultModalBody").html("针对 "+result.fw_info.fw_name+" 的分析完成", function() {});
	     		$("#resultModal").modal("show");
	     	 } else {
//	     		 填充并显示结果提示模态框，提示错误
	     		$("#resultModalBody").html(result.reason);
	     		$("#resultModal").modal("show");
	     	 }
	      },
	      error: function(error){
//	    	     隐藏加载模态框
		    	$("#loadingModel").modal("hide");
//	     		 填充并显示结果提示模态框，提示错误
	     		$("#resultModalBody").html(error);
	     		$("#resultModal").modal("show");
	      }
	});
}

function onApkAnalysis() {
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
		     	$("#apk_basic_info").html(apk_basic_info);
//		     	填充权限信息
		     	apk_permission = '<strong class="d-block text-gray-dark">APK所需权限</strong>';
		     	for (var i = 0, len = result.apk_permission.length; i < len; ++i) {
		     		apk_permission += result.apk_permission[i]+'<br/>';
		     	}
		     	$("#apk_permission").html(apk_permission);
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
		     	$("#apk_platform_risk").html(apk_platform_risk);
	     		$("#resultModalBody").html("针对 "+result.apk_info.apk_name+" 的分析完成", function() {});
	     		$("#resultModal").modal("show");
	     	 } else {
	     		$("#resultModalBody").html(result.reason);
	     		$("#resultModal").modal("show");
	     	 }
	      }
	});
	$("#loadingModel").modal("show");
}

function onIpaAnalysis() {
	var formData = new FormData(document.getElementById("uploadForm"));
	$.ajax({
	      type:"POST",
	      url:"/SecIoT/appleios/analysis",
	      data:formData,
	      mimeType:"multipart/form-data",
	      contentType: false,
	      cache: false,
	      processData: false,
	      success: function (result) {
	    	 $("#loadingModel").modal("hide");
	    	 var result=JSON.parse(result);
	     	 if(result.status == 0) {
//	     		 填充IPA基本信息
		     	ipa_basic_info = '<strong class="d-block text-gray-dark">IPA基本信息</strong>';
		     	ipa_basic_info += '文件名：'+result.ipa_info.ipa_name+'<br/>';
		     	ipa_basic_info += '文件大小：'+result.ipa_info.ipa_size+'字节';
		     	$("#ipa_basic_info").html(ipa_basic_info);
//		     	填充权限信息
		     	ipa_permission = '<strong class="d-block text-gray-dark">IPA所需权限</strong>';
		     	for (var i = 0, len = result.ipa_permission.length; i < len; ++i) {
		     		ipa_permission += result.ipa_permission[i]+'<br/>';
		     	}
		     	$("#ipa_permission").html(ipa_permission);
//				显示平台风险
		     	var ipa_platform_risk = '<h6 class="border-bottom border-gray pb-2 mb-0">平台风险</h6>';
		     	for (var i = 0, len = result.ipa_platform_risk.length; i < len; ++i) {
		     		var ipa_per_platform_risk = '<div class="media text-muted pt-3"><p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">';
		     		ipa_per_platform_risk += '<strong class="d-block text-gray-dark">'+result.ipa_platform_risk[i].risk_name+'</strong>';
		     		ipa_per_platform_risk += '风险是否存在：'+result.ipa_platform_risk[i].risk_exists + '<br/>';
		     		ipa_per_platform_risk += '风险描述：'+result.ipa_platform_risk[i].risk_description + '<br/>';
		     		ipa_per_platform_risk += '风险等级：'+result.ipa_platform_risk[i].risk_level + '<br/>';
		     		ipa_per_platform_risk += '风险适用平台：'+result.ipa_platform_risk[i].risk_platform + '<br/>';
		     		ipa_per_platform_risk += '风险技术细节（供专业人员参考）<br/>';
		     		for (var detail_name in result.ipa_platform_risk[i].risk_details){
		     			ipa_per_platform_risk += detail_name + '：<br/>';
		     			var risk_detail = result.ipa_platform_risk[i].risk_details[detail_name];
		     			for (var j = 0, lenr = risk_detail.length; j < lenr; ++j) {
		     				ipa_per_platform_risk += result.ipa_platform_risk[i].risk_details[detail_name][j] + '<br/>';
		     			}
		     		}
		     		ipa_per_platform_risk += '</p></div>';
		     		ipa_platform_risk += ipa_per_platform_risk;
		     	}
		     	$("#ipa_platform_risk").html(ipa_platform_risk);
	     		$("#resultModalBody").html("针对 "+result.ipa_info.ipa_name+" 的分析完成", function() {});
	     		$("#resultModal").modal("show");
	     	 } else {
	     		$("#resultModalBody").html(result.reason);
	     		$("#resultModal").modal("show");
	     	 }
	      }
	});
	$("#loadingModel").modal("show");
}

function onRefreshDeviceList() {
	$.post("/SecIoT/device/getOnlineDevice", {}, function(result) {
		if(result.status == 0) {
			var devices = "";
			for (var i = 0, len = result.devices.length; i < len; ++i) {
				devices += "<tr>";
				devices += "<td>"+(i+1)+"</td>";
				devices += "<td>"+result.devices[i].clientid+"</td>";
				devices += "<td>"+result.devices[i].devicename+"</td>";
				devices += "<td>Android "+result.devices[i].version+" ( API "+result.devices[i].apilevel+" )</td>";
				devices += "<td>"+result.devices[i].agentver+"</td>";
				devices += "<td>"+result.devices[i].port+"</td>";
				devices += "<td>";
				devices += '<button class="btn btn-sm btn-info" data-toggle="modal" data-target="#showDeviceInfo" title="查看远程设备的基本信息">查看</button>';
				devices += '<button class="btn btn-sm btn-success" onclick="getProcessList('+result.devices[i].port+')" title="检测远程设备上的特定应用的安全风险">检测</button>';
				devices += '<button class="btn btn-sm btn-danger" data-toggle="modal" data-target="#customInjectionWarning" title="向远程设备上的特定应用注入代码(高级功能)">注入</button>';
				devices += "</td>";
				devices += "</tr>";
			}
			$("#deviceBody").html(devices);
			if (result.devices.length == 0) {
				$("#resultModalBody").html("未发现在线的测试设备，请您检查测试设备状态。<br/><br/>" +
						"1. 是否已在测试设备上安装SecIoT Agent应用？<br/>" +
						"2. SecIoT Agent模块是否已经安装（需要取得测试设备的root权限）？<br/>" +
						"3. SecIoT Agent模块是否已经开启？（需要取得测试设备的root权限）<br/>" +
						"4. 检查测试设备的网络连接是否正常？<br/>" +
						"5. 检查frps控制台是否存在测试设备连接？");
	     		$("#resultModal").modal("show");
			}
		} else {
			$("#resultModalBody").html(result.reason);
     		$("#resultModal").modal("show");
		}
	});
}
//configureDetection
function getProcessList(port) {
	$.get("/SecIoT/android/getProcessList", {
		port: port
	}, function(result) {
		if(result.status == 0) {
			var processes = "";
			for (var i = 0, len = result.processes.length; i < len; ++i) {
				processes += "<option>";
				processes += result.processes[i];
				processes += "</option>";
			}
			$("#processSelect").html(processes);
			$("#configureDetection").modal("show");
		} else {
			$("#resultModalBody").html(result.reason);
     		$("#resultModal").modal("show");
		}
	});
}
