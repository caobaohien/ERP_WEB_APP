//Công thức quy đổi cho dvkd nhôm, dvdl cuộn
function _quyDoi_Nhom_Cuon(dai, rong, dinhluong, slchuan) {
	if(isNaN(dai) || isNaN(rong) || isNaN(dinhluong)) return 0;
	return slchuan * dai * rong * dinhluong * 0.000001;
}

//Công thức quy đổi cho dvkd nhôm, dvdl ream
function _quyDoi_Nhom_Ream(dai, rong, dinhluong, slchuan) {
	if(isNaN(dai) || isNaN(rong) || isNaN(dinhluong)) return 0;
	return slchuan * dai * rong * dinhluong / 1000000000 * 500;
}

//Công thức quy đổi cho dvkd nhôm, dvdl bộ
function _quyDoi_Nhom_Bo(dai, rong, dinhluong, slchuan) {
	if(isNaN(dai) || isNaN(rong) || isNaN(dinhluong)) return 0;
	return slchuan * dai * rong * dinhluong / 1000000000 * 1000;
}

//Công thức quy đổi cho core
function _quyDoi_Loi_Core(phi, day, dai, slchuan) {
	if( isNaN(phi) || isNaN(day) || isNaN(dai) || isNaN(slchuan) ) return 0;
	return slchuan * (phi+day)*Math.PI*dai*day*730/(1000000000);
}

//Tính tự động trọng lượng sản phẩm
function quyDoiTuDong() {
	
	var dvkdId = $('#dvkdId').val();
	var clId = $('#clId').val();
	
	var dvkdIds = ['100000', '100004']; //Nhôm, Lõi
	if( dvkdIds.indexOf(dvkdId) < 0 ) {
		return;
	}
	
	var $e = $(this);
	var index = -1;
	try { index = parseInt($e.attr('index')); } catch(e) { }
	if(isNaN(index) || index < 0) {
		return;
	}
	var $slquydoi = $('#slquydoi_' + index); 
	var dvdlId = $('#dvdl_' + index).val();
	
	//LOI - CORE
	if(dvkdId == "100004") 
	{
		//Chủng loại CORE
		if(clId == "100031") {
			var phi = parseFloat($('#duongkinhtrong_' + index).val()); if(isNaN(phi)) phi = 0;
			var dai = parseFloat($('#dai_' + index).val()); if(isNaN(dai)) dai = 0;
			var day = parseFloat($('#day_' + index).val()); if(isNaN(day)) day = 0;
			var slchuan = parseFloat($('#slchuan_' + index).val()); if(isNaN(slchuan)) slchuan = 0;
			//alert(phi + ", " + dai + ", " + day + ", " + slchuan);
			$slquydoi.val( parseFloat(_quyDoi_Loi_Core(phi, day, dai, slchuan).toFixed(5)).toFixed(4) );
			return;
		}
	} 
	//NHÔM
	else if(dvkdId == "100000") 
	{
		var dvdlIds = ['100007', '100000', '100008'];
		if( dvdlIds.indexOf(dvdlId) < 0 ) {
			return;
		}
		
		var dai = parseFloat($('#dai_' + index).val()); if(isNaN(dai)) dai = 0;
		var rong = parseFloat($('#rong_' + index).val()); if(isNaN(rong)) rong = 0;
		var dinhluong = parseFloat($('#dinhluong_' + index).val()); if(isNaN(dinhluong)) dinhluong = 0;
		var slchuan = parseFloat($('#slchuan_' + index).val()); if(isNaN(slchuan)) slchuan = 0;
		
		//Cuộn
		if(dvdlId == '100000') {
			$slquydoi.val( parseFloat(_quyDoi_Nhom_Cuon(dai, rong, dinhluong, slchuan).toFixed(3)).toFixed(2) );
			return;
		}
		else if(dvdlId == '100007') {
			$slquydoi.val( parseFloat(_quyDoi_Nhom_Ream(dai, rong, dinhluong, slchuan).toFixed(3)).toFixed(2) );
			return;
		}
		else if(dvdlId == '100008') {
			$slquydoi.val( parseFloat(_quyDoi_Nhom_Bo(dai, rong, dinhluong, slchuan).toFixed(3)).toFixed(2) );
			return;
		} else {
			
		}
	}
}