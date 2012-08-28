function WindowConfirm(message, ondeIr, container, opt, obj){
    iniciaWindowConfirm(message, ondeIr, container, opt, obj);
}

iniciaWindowConfirm = function(txt_message, ondeIr, container, opt, obj) {
    var nmRoot = "blackscreenWinconfirm";

    new BlackScreen(opt,nmRoot);

    var titulo = $("<div>").addClass("titulo_window");
    titulo.html("Confirmação");

    var linhaCinza = $("<div style='position: relative; width: 450px; height: 1px; background-color: #ccc;'>");

    var janela = $("<div id='windowAlert'>").addClass("window_area");
    $('#'+nmRoot).prepend(janela);
    janela.css({
        left: (jQuery(window).width()-640)/2+"px"
    });

    var windowContent = $("<div>").addClass("window_content");
    var linhaTop = $("<div>").addClass("window_linha_top");
    var midWindow = $("<div>").addClass("window_mid");
    var btnArea = $("<div>").addClass("window_btn_area");
    var linhaBot = $("<div>").addClass("window_linha_bot");

    var icone = $("<div>").addClass("icon_window").css({
        backgroundImage: 'url(/_modulos/_sistema/_images/icon_confirm.jpg)'
    });

    var textArea = $("<div>").addClass("window_text_area");
    textArea.className = "window_text_area";

    btnArea.prepend(getBtnCancelar(nmRoot,''));
    btnArea.prepend(getBtnContinuar(nmRoot,ondeIr,container,obj));

    var message = $("<div>").addClass("message_window");
    message.html(txt_message);
    textArea.prepend(message);
    
    textArea.prepend(linhaCinza);
    textArea.prepend(titulo);

    midWindow.prepend(textArea);
    midWindow.prepend(icone);

    windowContent.prepend(linhaBot);
    windowContent.prepend(btnArea);
    windowContent.prepend(midWindow);
    windowContent.prepend(linhaTop);

    $('#windowAlert').prepend(windowContent);
    
    $('#'+nmRoot).fadeIn(300);
    scroll(0,0);
    
}

/*
WindowConfirm.prototype = {
    constructor: WindowConfirm,
	
	hiLiteButton: function(eventObject){
		getTargetObject(eventObject).className = "window_btn_hiLite2";
	},
	
	loLiteButton: function(eventObject){
		getTargetObject(eventObject).className = "window_btn_loLite2";
	},
			
	init: function(){
		var blackscreen = new BlackScreen();
		
		var message = this.message;
		var hiLiteButton = this.hiLiteButton;
		var loLiteButton = this.loLiteButton;
		
		var cancelWindow = function(){
			cleanElement(blackscreen.getRoot());
			removeElement(blackscreen.getRoot());
			
			WindowConfirm.prototype.getAnswer = function(){return false};
			
			WindowConfirm.prototype.windowClosed();
		};
		
		var confirmWindow = function(){
			cleanElement(blackscreen.getRoot());
			removeElement(blackscreen.getRoot());
			
			WindowConfirm.prototype.getAnswer = function(){return true};
			
			WindowConfirm.prototype.windowClosed();
		};
		
		function buildMessage(){
			var icon = document.createElement("div");
			icon.className = "icon_confirm";
			blackscreen.getRoot().appendChild(icon);
			
			var janela = document.createElement("div");
			janela.align = "center";
			janela.className = "window_confirm";
			blackscreen.getRoot().appendChild(janela);
			
			var windowContent = document.createElement("div");
			windowContent.className = "window_content2";
			janela.appendChild(windowContent);
			
			var titleBox = document.createElement("div");
			titleBox.align = "center";
			titleBox.className = "window_title";
			titleBox.innerHTML = "- CONFIRMAÇÃO -";
			windowContent.appendChild(titleBox);
			
			var textArea = document.createElement("div");
			textArea.align = "justify";
			textArea.className  = "window_text_area2";
			textArea.innerHTML = message;
			windowContent.appendChild(textArea);
			
			var btnArea = document.createElement("div");
			btnArea.className = "window_btn_area2";
			windowContent.appendChild(btnArea);
			
			var okBtn = document.createElement("div");
			okBtn.align = "center";
			okBtn.className = "window_btn_loLite2";
			okBtn.innerHTML = "OK";
			btnArea.appendChild(okBtn);
			
			var cancelBtn = document.createElement("div");
			cancelBtn.align = "center";
			cancelBtn.className = "window_btn_loLite2";
			cancelBtn.innerHTML = "Cancela";
			btnArea.appendChild(cancelBtn);
			
			addEvent(okBtn, "mouseover", hiLiteButton);
			addEvent(okBtn, "mouseout", loLiteButton);
			addEvent(okBtn, "click", confirmWindow);
			
			addEvent(cancelBtn, "mouseover", hiLiteButton);
			addEvent(cancelBtn, "mouseout", loLiteButton);
			addEvent(cancelBtn, "click", cancelWindow);
			
		};
		
		setTimeout(buildMessage, 500);
	},
	
	getEventHandler: function(){
		return this.eventHandler;
	},
	
	getAnswer: function(){	}
}
*/