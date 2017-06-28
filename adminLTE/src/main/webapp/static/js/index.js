// JavaScript Document
function altRows(id){
	if(document.getElementsByTagName){
		
		var table = document.getElementById(id);
		if(table){
            var rows = table.getElementsByTagName("tr");
            for(i = 0; i < rows.length; i++){
                if(i % 2 == 0){
                    rows[i].className = "evenrowcolor";
                }else{
                    rows[i].className = "oddrowcolor";
                }
            }
		}
	}
}

window.onload=function(){
	altRows('dbTable');
}


function setContentTab(name, curr, n) {
    for (i = 1; i <= n; i++) {
        var menu = document.getElementById(name + i);
        //var cont = document.getElementById("con_" + name + "_" + i);
		if(menu){
            menu.className = i == curr ? "up" : "";
            if (i == curr) {
                //cont.style.display = "block";
                $('#'+"con_" + name + "_" + i).show();
            } else {
                //cont.style.display = "none";
                $('#'+"con_" + name + "_" + i).hide();
            }
		}
    }
}