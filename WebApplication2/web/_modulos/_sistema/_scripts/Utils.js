function getElement(elementIdString){
    return document.getElementById(elementIdString);
}

function cleanElement(element){
	while(element.firstChild){
		var first = element.firstChild;
		first.parentNode.removeChild(first);
	}
}

function removeElement(element){
	element.parentNode.removeChild(element);
}

function changeBGColor(element, colorHexString){
	element.style.backgroundColor = colorHexString;
}

function show_hide(IDString){
	var element = getElement(IDString);
	
	if(element.style.display == "none"){
		element.style.display = "block";
	}else{
		element.style.display = "none";
	}
}

function swapImage(element, newSource){
	element.src = newSource;
}
//--------------------------------------------------//
function addEvent(object, eventType, callback) {//CROSS-BROWSER EVENT ADDING
	if(object.addEventListener){
		object.addEventListener(eventType, callback, false);
		return true;
		
	}else if (object.attachEvent){
		object["e" + eventType + callback] = callback;
		object[eventType + callback] = function() { object["e" + eventType + callback](window.event);}
		var r = object.attachEvent("on" + eventType, object[eventType + callback]);
		return r;
		
	}else{
		object["on" + eventType] = callback;
		return true;
	}
}

function removeEvent(object, eventType, callback, useCapture){
	if(object.removeEventListener){
		object.removeEventListener(eventType, callback, useCapture);
		return true;
	
	}else if(object.detachEvent){
		var r = object.detachEvent("on"+eventType, callback);
		return r;
		
	}else{
		alert("Handler could not be removed");
	}
}

function getTargetObject(evtObj){//CROSS-BROWSER EVENT.TARGET GETTING
	if(typeof(evtObj) == 'undefined'){
		var evtObj = window.event;
	}
	
	var target = evtObj.target ? evtObj.target:evtObj.srcElement;
	
	if(target.nodeType == 3){
		target = target.parentNode;
	}
	
	return target;
}

//--------------------------------------------------//
function getURLString(newSession, newSubSession, newScreenID, mode){
	var URLString = "newSession=" + newSession + "&newSubSession=" + newSubSession + "&newScreenID=" + newScreenID + "&mode=" + mode;
	return URLString;
}

function changeSession(URLString){	
	
	var xhr = new XHR();
	
	var url = "scripts/c_geral.php";
	
	xhr.getInstance().onreadystatechange =
	function (){
		if (xhr.getInstance().readyState == 4){
			if(xhr.getInstance().responseText == "true"){
				window.location.reload();
			}
		}
	};
	
	xhr.GO(url, URLString);
}

//--------------------------------------------------//
function cleanInputField(placeHolderValue, inputField){
	if(inputField.value == placeHolderValue){
		inputField.value = "";
	}
}

function fillInputField(placeHolderValue, inputField){
	if(inputField.value == ""){
		inputField.value = placeHolderValue;
	}
}

function getCheckBoxSequenceValues(prefix, length){
	
	var valuesString = "";
	
	for(var i = 0; i < length; i++){
		if(getElement(prefix + i).checked){
			valuesString += "1";
		}else{
			valuesString += "0";
		}
	}
	
	return valuesString;
}

function positionateComboBox(element, value){
	var options = element.childNodes;
	
	for(var i=0; i < options.length; i++){
		if(typeof(options.item(i).value) != "undefined"){
			if(options.item(i).value == value){
				options.item(i).selected = true;
				break;
			}
		}
	}
}

//--------------------------------------------------//
function preloadImages(){
	var pImg0 = new ImageInfo("assets/bsbg0.png", 10, 10);
	var pImg1 = new ImageInfo("assets/bsbg1.png", 10, 10);
	var pImg2 = new ImageInfo("assets/bsbg2.png", 10, 10);
	var pImg3 = new ImageInfo("assets/bsbg3.png", 10, 10);
	var pImg4 = new ImageInfo("assets/bsbg4.png", 10, 10);
	
	var imagesPreloader = new ImagesPreloader([pImg0, pImg1, pImg2, pImg3, pImg4]);
}
//--------------------------------------------------//
function logout(eventObject){
	var xhr = new XHR();
	
	var url = "scripts/c_geral.php";
	
	xhr.getInstance().onreadystatechange =
	function (){
		if(xhr.getInstance().readyState == 4){
			if(xhr.getInstance().responseText == "true"){
				document.location.reload();
			}else{
				var wFailure = new WindowFailure("Não foi possível encerrar a sessão.<br /><br />Tente novamente.");
			}
		}
	};
	
	xhr.GO(url, "mode=LOG_OUT");
}


function askService(URLString, url){
	var xhr = new XHR();
	
	//var url = "scripts/" + script;
        //var url = script;
	
	xhr.getInstance().onreadystatechange =
	function (){
		if(xhr.getInstance().readyState == 4){
			if(xhr.getInstance().responseText == "true"){
				//document.location.reload();
                                //document.getElementById('teste').style.display = "";
                                //location.href='login.php?op=teste';
                                alert('teste');
			}else{
                                var wFailure = new WindowFailure("Não foi possível realizar este procedimento.<br /><br />Tente novamente.");
			}
		}
	};
	
	xhr.GO(url, URLString);
}