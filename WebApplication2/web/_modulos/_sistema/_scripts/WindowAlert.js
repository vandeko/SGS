function WindowAlert(message, txt_titulo, txt_sub_titulo, opt, focus){
    iniciaWindowAlert(message, txt_titulo, txt_sub_titulo, opt, focus);
}

iniciaWindowAlert = function(txt_message, txt_titulo, txt_sub_titulo, opt, focus) {

    var nmRoot = "blackscreenAlert";

    new BlackScreen(opt,nmRoot);
    
    getJanela(nmRoot);

    var windowContent = $("<div>").addClass("window_content");
    var linhaTop = $("<div>").addClass("window_linha_top");
    var midWindow = $("<div>").addClass("window_mid");
    var btnArea = $("<div>").addClass("window_btn_area");
    var linhaBot = $("<div>").addClass("window_linha_bot");

    var icone = $("<div>").addClass("icon_window").css({
        backgroundImage: 'url(/_modulos/_sistema/_images/icon_info.jpg)'
    });

    var textArea = getTextArea();

    btnArea.prepend(getBtnOk(nmRoot,focus));
    
    textArea.prepend(getMessageWindow(txt_message));
    if (txt_sub_titulo != "") {        
        textArea.prepend(getSubTituloWindow(txt_sub_titulo));
    }    
    textArea.prepend(getLinhaCinza());
    textArea.prepend(getTituloWindow(txt_titulo));
        
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