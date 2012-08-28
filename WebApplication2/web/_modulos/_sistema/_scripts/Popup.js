function Popup(file,ondeIr,width,height,tipo){
    iniciaPopup(file,ondeIr,width,height,tipo);
}

iniciaPopup = function(file,ondeIr,width,height,tipo) {

    var cont = "";

    if (file.lastIndexOf('.swf')>-1) {
        cont += "<object width='"+width+"' height='"+height+"' style='z-index:0;'>";
        cont += "<param name='wmode' value='transparent' />";
        cont += "<param name='movie' value='/_arquivos/_banners/"+file+"'>";
        cont += "<embed wmode='transparent' src='/_arquivos/_banners/"+file+"' width='"+width+"' height='"+height+"'></embed>";
        cont += "</object>";
    } else {
        cont += "<img src='/_arquivos/_banners/"+file+"' alt='' title='' width='"+width+"' height='"+height+"' style='z-index:1000;' />";
    }   

    var nmRoot = "blackscreenPopup";

    new BlackScreen('1',nmRoot);
    
    getJanelaPopup(nmRoot,width);

    var windowContent = $("<div  style='z-index:1000;'>").addClass("window_content");

    var filePopup = $("<div>").html(cont);

    windowContent.css({
        width: width+"px",
        height: height+"px",
        position: 'relative',
        marginTop: '10px'
    });

    filePopup.css({
        width: width+"px",
        height: height+"px"
    });

    filePopup.click(function(){
        if (tipo == '0') {
            abreConteudo('conteudoEsq','','',ondeIr,'');
            closeWindow(nmRoot,'');
        } else if (tipo == '1') {
            window.open(ondeIr,'LINK');
            closeWindow(nmRoot,'');
        }
    });

    windowContent.prepend(filePopup);
    windowContent.prepend(getBtnClosePopup(nmRoot, focus));

    $('#windowPopup').prepend(windowContent);
    $('#windowPopup').css('zIndex','1000');
    $('#'+nmRoot).fadeIn(300);
    scroll(0,0);

}