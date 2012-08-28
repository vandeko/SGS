function WindowError(message, opt){
    iniciaWindowError(message, opt);
}

iniciaWindowError = function(txt_message, opt) {

    var nmRoot = "blackscreenWinerror";

    new BlackScreen(opt,nmRoot);
    
    getJanela(nmRoot);

    var windowContent = $("<div>").addClass("window_content");
    var linhaTop = $("<div>").addClass("window_linha_top");
    var midWindow = $("<div>").addClass("window_mid");
    var btnArea = $("<div>").addClass("window_btn_area");
    var linhaBot = $("<div>").addClass("window_linha_bot");

    var icone = $("<div>").addClass("icon_window").css({
        backgroundImage: 'url(/_modulos/_sistema/_images/icon_error.jpg)'
    });

    var textArea = getTextArea();

    btnArea.prepend(getBtnOk(nmRoot,''));
    
    textArea.prepend(getMessageWindow(txt_message));    
    textArea.prepend(getLinhaCinza());
    textArea.prepend(getTituloWindow("Problema no processamento"));
        
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