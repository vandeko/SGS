function WindowLoad(arquivo,txt_titulo,btnOk,btnCancelar,opt,focus,params,modulo,componente,w,h){
    iniciaWindowLoad(arquivo,txt_titulo,btnOk,btnCancelar,opt,focus,params,modulo,componente,w,h);
}

iniciaWindowLoad = function(arquivo,txt_titulo,btnOk,btnCancelar,opt,focus,params,modulo,componente,w,h) {

    var nmRoot = "blackscreenWinload";
    if(!$('#'+nmRoot).height()){
        new BlackScreen(opt,nmRoot);
        if (w==null || w == '') {
            w = 640;
        }
        if (h==null || h == '') {
            h = 500;
        }
        
        getJanelaLoad(nmRoot,w,h);

        var windowContent = $("<div>").addClass("window_content");
        var btnWrap = $("<div>").addClass("window_btn_wrap");
        //var linhaTop = $("<div>").addClass("window_linha_top");
        var midWindow = $("<div>").addClass("window_mid");
        var btnArea = $("<div>").addClass("window_btn_area");
        var linhaBot = $("<div>").addClass("window_linha_bot");
        
        var textArea = getTextArea();
        
        if (w!='') { 
            windowContent.css({
                width: w+'px'
            });
        }
        
        if (h!='') { 
            windowContent.css({
                height: h+'px'
            });
        }
               
        textArea.css({
            width: (w-40)+'px',
            height: h+'px'
        });

        if (btnCancelar) {
            btnArea.prepend();
        }
        if (btnOk) {
            btnArea.prepend(getBtnOk(nmRoot,focus));
        }

        textArea.prepend(getDivLoad());
        textArea.prepend(getLinhaCinza().css({
            width: (w-40)+'px'
        }));
        textArea.prepend(getTituloWindow(txt_titulo).css({
            width: (w-40)+'px'
        }));

        midWindow.prepend(textArea);

        windowContent.prepend(linhaBot);
        if (btnOk != '' || btnCancelar != '' ) {
            windowContent.prepend(btnArea);
        }    
        windowContent.prepend(midWindow);
        //windowContent.prepend(linhaTop);
        windowContent.prepend(btnWrap);
        btnWrap.prepend(getBtnClose(nmRoot,focus));

        $('#windowLoad').prepend(windowContent);
        abreConteudo('load',modulo,componente,arquivo,params);
        $('#'+nmRoot).fadeIn(300);
    }else{
        var titulo = $(".titulo_window").html(txt_titulo);
        abreConteudo('load',modulo,componente,arquivo,params);
    }
    scroll(0,0);

}