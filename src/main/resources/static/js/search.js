'use strict';

$(document).ready(function() {
	// 一覧表示時のajax処理
	$(document).on('click', '.ajax_allList', function() {
		$.ajax({
			url : "/allListResult",
			type : "GET",
			dataType : "html"
		})
		.done(function(data) {
			$('#empTable').html(data);
		})
		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
			console.log('fail');
			console.log("XMLHttpRequest : " + XMLHttpRequest.status);
			console.log("textStatus     : " + textStatus);
			console.log("errorThrown    : " + errorThrown.message);
		});
	});
	
	// 削除時のajax処理
	$(document).on('click', '.ajax_delete', function() {
		// サーバーに送る連想配列
		var sendList = {};
		// 削除対象テーブルのkeyを取得し、sendListに格納
		var key = $(this).attr('name');
		sendList["key"] = key;
		// ループ処理でテーブルからデータを取得し、sendListに格納
		var tableData = $('#empTable .empList');
		tableData.each(function(i) {
			var $elm = $(this);
			sendList["empList[" + i + "].id"] = $elm.find(".id").text();
			sendList["empList[" + i + "].name"] = $elm.find(".name").text();
			sendList["empList[" + i + "].birthdate"] = $elm.find(".birthdate").text();
			sendList["empList[" + i + "].division"] = $elm.find(".division").text();
		});
	
		$.ajax({
			url : "/deleteResult",
			type : "POST",
			data : sendList,
			dataType : "html"
		})
		.done(function(data) {
			$('#empTable').html(data);
		})
		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
			console.log('fail');
			console.log("XMLHttpRequest : " + XMLHttpRequest.status);
			console.log("textStatus     : " + textStatus);
			console.log("errorThrown    : " + errorThrown.message);
		});
	});
});