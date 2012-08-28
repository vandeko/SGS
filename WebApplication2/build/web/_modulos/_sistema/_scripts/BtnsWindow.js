closeWindow = function(nmRoot,focus) {
    $('#'+nmRoot).fadeOut(300, function() {
        $('#'+nmRoot).remove();
        if (focus != '') {
            $('#'+focus).focus();
        }
    });
}

continuarAcao = function(nmRoot,ondeIr,container) {
    loadPage(container,ondeIr);
    //closeWindow(nmRoot, focus);
}

getBtnOk = function(nmRoot,focus) {
    var btn_ok = $("<div>").addClass("btn_ok_window");
    btn_ok.click(function() {
        closeWindow(nmRoot,focus);
    });
    btn_ok.mouseover(function() {
        btn_ok.animate({
            opacity: 0.7
        }, 200);
    });
    btn_ok.mouseout(function() {
        btn_ok.animate({
            opacity: 1.0
        }, 200);
    });
    return btn_ok;
}

getBtnContinuar = function(nmRoot,ondeIr,container,obj) {
    var btn = $("<div>").addClass("btn_continuar_window");
    btn.click(function() {
        if(obj){
            obj.fadeOut('slow', function(){
              obj.remove();
            });
        }
        if(container != ''){
            continuarAcao(nmRoot,ondeIr,container);
        } else {
            window.open (ondeIr, 'LinkExterno');
            top.closeWindow('blackscreenWinconfirm');
        }

//        top.closeWindow('blackscreenWinconfirm');
//        top.closeWindow('blackscreenWinload');
    });
    btn.mouseover(function() {
        btn.animate({
            opacity: 0.7
        }, 200);
    });
    btn.mouseout(function() {
        btn.animate({
            opacity: 1.0
        }, 200);
    });
    return btn;
}

getBtnCancelar = function(nmRoot,focus) {
    var btn = $("<div>").addClass("btn_cancelar_window");
    btn.click(function() {
        closeWindow(nmRoot,focus);
    });
    return btn;
}

getBtnClose = function(nmRoot,focus) {
    var btn = $("<div>").addClass("window_btn_close");
    btn.click(function() {
        closeWindow(nmRoot,focus);
    });
    return btn;
}


getBtnClosePopup = function(nmRoot) {
    var btn = $("<div>").addClass("btn_close_popup");
    btn.click(function() {
        closeWindow(nmRoot,'');
    });
    btn.mouseover(function() {
        btn.animate({
            opacity: 0.7
        }, 200);
    });
    btn.mouseout(function() {
        btn.animate({
            opacity: 1.0
        }, 200);
    });
    return btn;
}