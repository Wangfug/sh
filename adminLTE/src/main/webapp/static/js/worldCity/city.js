function init(country1,pro,city1){
    var country = '';
    for (var a=0;a<=_areaList.length-1;a++) {
        var objContry = _areaList[a];
        if(country1 == objContry){
            country += '<option value="'+objContry+'" a="'+(a+1)+'" selected>'+objContry+'</option>';
		}else{
            country += '<option value="'+objContry+'" a="'+(a+1)+'">'+objContry+'</option>';
		}
    }
    $("#country").html(country).chosen().change(function(){
        var a = $("#country").find("option[value='"+$("#country").val()+"']").attr("a");
        var _province = areaObj[a];
        var province = '';
        for (var b in _province) {
            var objProvince = _province[b];
            if (objProvince.n) {
            	if(pro == objProvince.n){
                    province += '<option value="'+objProvince.n+'" b="'+b+'" selected>'+objProvince.n+'</option>';
				}else{
                    province += '<option value="'+objProvince.n+'" b="'+b+'">'+objProvince.n+'</option>';
				}
            }
        }
        if (!province) {
            province = '<option value="0" b="0">请选择</option>';
        }
        $("#province").html(province).chosen().change(function(){
            var b = $("#province").find("option[value='"+$("#province").val()+"']").attr("b");
            var _city = areaObj[a][b];
            var city = '';
            for (var c in _city) {
                var objCity = _city[c];
                if (objCity.n) {
                	if(city1 == objCity.n){
                        city += '<option value="'+objCity.n+'" selected>'+objCity.n+'</option>';
					}else{
                        city += '<option value="'+objCity.n+'">'+objCity.n+'</option>';
					}

                }
            }
            if (!city) {
                var city = '<option value="0">请选择</option>';
            }
            $("#city").html(city).chosen().change();
            $(".dept_select").trigger("chosen:updated");
        });
        $("#province").change();
        $(".dept_select").trigger("chosen:updated");
    });
    $("#country").change();
}
	$("button").click(function(){
		alert($("#country").val()+$("#province").val()+$("#city").val());
	});
