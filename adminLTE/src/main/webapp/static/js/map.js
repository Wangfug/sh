/**
 * 工具类hashMap
 * 张欢
 */
function HashMap() {

	var length = 0;
	var obj = new Object();
	
	this.isEmpty = function(){
		return length == 0;
	}

	this.containsKey = function(key) {
		return (key in obj);
	};

	this.containsValue = function (value){
		for(var key in obj){
			if(obj[key] == value){
				return true;
			}
		}
		return false;
	};
	
	this.put = function(key, value) {
		if(!this.containsKey(key)){
			length ++;
		}
		obj[key]  = value;
	};
	
	this.get = function(key) {
		return this.containsKey(key) ? obj[key]: null;
	};

	
	this.remove = function(key) {
		if(this.containsKey(key) && delete obj[key]){
			length --;
		}
	};

	this.values = function(){
		var _values = new Array();
		for(var key in obj){
			_values.push(obj[key]);
		}
		return _values;
	};
	
	this.keySet = function(){
		var _keys = new Array();
		for(var key in obj){
			_keys.push(key);
		}
		return _keys;
	}
	
	this.clear = function() {
		obj = new Object();
		length = 0;
	};
	
	this.size = function(){
		return length;
	};
	
	this.getObj =  function(){
		return obj;
	};
}
