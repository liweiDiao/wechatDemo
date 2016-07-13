//字符串校验长度
function charLength(str,length){
	var w = 0;//记录str的长度
	if(str != undefined && str != ""){
		for(var i = 0;i<str.length;i++){
			var charCode = str.charCodeAt(i);
			//单字节加1
			if((charCode >= 0x001 && charCode <= 0x007e) || (0xff60 <= charCode && charCode <= 0xff9f)){
				w ++;
			}else {
				w +=2;
			}
		}
	}
	if(w >length){
		return false;
	}
	return true;
}

//检查非空
function checkNull(id){
	var value = $("#"+id).val().trim();
	if(value == null || value == undefined || value == ""){
		return true;
	}
	return false;
}