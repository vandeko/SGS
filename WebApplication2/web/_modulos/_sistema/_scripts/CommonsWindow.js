getJanela = function(nmRoot) {
    var janela = $("<div id='windowAlert'>").addClass("window_area");
    $('#'+nmRoot).prepend(janela);
    janela.css({
        left: (jQuery(window).width()-640)/2+"px"
    });
    return janela;
}

getJanelaLoad = function(nmRoot,w,h) {
    var janela = $("<div id='windowLoad'>").addClass("window_area");
    $('#'+nmRoot).prepend(janela);
    var maxH = '500';
    var h = (jQuery(window).height()-h)/2;  
    janela.css({        
        left: (jQuery(window).width()-w)/2+"px",
        top: h+'px',
        height: maxH
    });
    return janela;
}

getTituloWindow = function(txt_titulo) {
    var titulo = $("<div>").addClass("titulo_window");
    titulo.html(txt_titulo);
    return titulo;
}

getJanelaPopup = function(nmRoot,width) {
    var janela = $("<div id='windowPopup'>").addClass("window_area");
    $('#'+nmRoot).prepend(janela);
    janela.css({
        top: '10px',
        left: (jQuery(window).width()-width)/2+"px"
    });
    return janela;
}

getLinhaCinza = function() {
    return linhaCinza = $("<div style='position: relative; width: 450px; height: 1px; background-color: #ccc;'>");
}

getSubTituloWindow = function(txt_sub_titulo) {
    var sub_titulo = $("<div>").addClass("sub_titulo_window");
    sub_titulo.html(txt_sub_titulo);
    return sub_titulo;
}

getTextArea = function() {
    var textArea = $("<div>").addClass("window_text_area");
    textArea.className = "window_text_area";
    return textArea;
}

getMessageWindow = function(txt_message) {
    var message = $("<div>").addClass("message_window");
    message.html(txt_message);
    return message;
}

getDivLoad = function() {
    var divLoad = $("<div id='load'>");
    return divLoad;
}

