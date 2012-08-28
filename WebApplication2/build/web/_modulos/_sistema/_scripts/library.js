configuraImagemLoading = function() {
    $('#divPreload').ajaxStart(function(){
        $(this).show();
    });
    $('#divPreload').ajaxStop(function(){
        $(this).hide();
    });
}

cleanInputField = function (placeHolderValue, inputField) {
    if(inputField.value == placeHolderValue){
        inputField.value = "";
    }
}

fillInputField = function (placeHolderValue, inputField){
    if(inputField.value == ""){
        inputField.value = placeHolderValue;
    }
}

stylesFieldsForm = function(mask) {

    $(':input[type=text]').focus(function(){
        $(this).select();
    });

    $(':input[type=text]').blur(function(){
        if ($(this).attr('class') == 'inputProcesso' && $(this).attr('name') != 'email' && $(this).attr('name') != 'emailC' ) {
            $(this).val($(this).val().toUpperCase());
        }
    });

    $(':input').focus(function(){
        $(this).css({
            backgroundColor:"#ffefef",
            border:"1px solid #900"
        });
    });
    $(':input').blur(function(){
        $(this).css({
            backgroundColor:"#efefef",
            border:"1px solid #aaa"
        });
    });

    $('.inputProcesso, .inputAdm, .textareaAdm, .inputContact, .textareaContact').focus(function(){
        $(this).css({
            backgroundColor:"#e9f7fd",
            border:"1px solid #00315a"
        });
    });

    $('.inputProcesso, .inputAdm, .textareaAdm, .inputContact, .textareaContact').blur(function(){
        $(this).css({
            backgroundColor:"#efefef",
            border:"1px solid #aaa"
        });
    });

    if (mask == "true") {

        $('input[name="dt1"]').setMask('date');
        $('input[name="dt2"]').setMask('date');
        $('input[name="notaCorte"]').setMask('decimal');

        $('input[name="data"]').setMask('date');
        $('input[name="hora"]').setMask('time');

        $('input[name="telefone"]').setMask('phone');
        $('input[name="celular"]').setMask('phone');
        //$('input[name="telefone1"]').setMask('phone');
        //$('input[name="telefone2"]').setMask('phone');
        //$('input[name="telefone3"]').setMask('phone');

        $('input[name="cpf"]').setMask('cpf');

        $('input[name="cnpj"]').setMask('cnpj');

        $('input[name="cep"]').setMask('cep');

        $('input[name="numero"]').setMask('integer');
        
        $('input[name="dia"]').setMask('99');
        $('input[name="mes"]').setMask('99');
        $('input[name="ano"]').setMask('9999');
        
        $('input[name="inputGridBanner"]').setMask('9');

        $('input[name="prova"]').setMask('decimal');
        $('input[name="redacao"]').setMask('decimal');
        $('input[name="enem"]').setMask('decimal');

        $('input[name="valor"]').setMask('decimal');
        
        $('input[name="porcentagem"]').setMask('decimal');
        
        $('input[name="taxaInstalacao"]').setMask('decimal');
        $('input[name="limiteKey"]').setMask('integer');
        $('input[name="limitePort"]').setMask('integer');
        $('input[name="valorMensal"]').setMask('decimal');
        $('input[name="valorExtra"]').setMask('decimal');

    }
    
}

loadPage = function(idContainer, arquivo) {
    $(idContainer).load(arquivo, function() {        
        scripts();
        $(idContainer).animate({
            opacity: 1
        }, 200)
    });
}

loadPageParams = function(idContainer, arquivo, params) {
    $(idContainer).load(arquivo+params, function() {
        scripts();
    });
}

alerta = function (msg,titulo,subtitulo,opt,focus) {
    new WindowAlert(msg,titulo,subtitulo,opt,focus);
}

confirma = function (msg,ondeIr,container,opt) {
    new WindowConfirm(msg,ondeIr,container,opt);
}

alertError = function (msg,opt) {
    new WindowError(msg,opt);
}

apagar = function(id, arq, texto, container, opt, param) {    
    new WindowConfirm("Deseja Realmente <strong>APAGAR</strong> "+texto+" ? ",'/'+arq+'Actions.do?id='+id+'&m=DEL&'+param,container,opt);    
}

apagarImg = function(arq, texto, container, opt, param) {
    new WindowConfirm("Deseja Realmente <strong>APAGAR</strong> "+texto+" ? ",'/_modulos//_lib/'+arq+'Actions.jsp?m=DELIMG&'+param,container,opt);
}

winLoad = function (arquivo,titulo,btnOk,btnCancelar,opt,focus,params,modulo,componente,w,h) {
    new WindowLoad(arquivo,titulo,btnOk,btnCancelar,opt,focus,params,modulo,componente,w,h);
}

verificaCpf = function(value) {

    value = value.replace('.','');

    value = value.replace('.','');

    cpf = value.replace('-','');

    while(cpf.length < 11) cpf = "0"+ cpf;

    var expReg = /^0+$|^1+$|^2+$|^3+$|^4+$|^5+$|^6+$|^7+$|^8+$|^9+$/;

    var a = [];

    var b = new Number;

    var c = 11;

    for (i=0; i<11; i++){

        a[i] = cpf.charAt(i);

        if (i < 9) b += (a[i] * --c);

    }

    if ((x = b % 11) < 2) {
        a[9] = 0
    } else {
        a[9] = 11-x
    }

    b = 0;

    c = 11;

    for (y=0; y<10; y++) b += (a[y] * c--);

    if ((x = b % 11) < 2) {
        a[10] = 0;
    } else {
        a[10] = 11-x;
    }

    if ((cpf.charAt(9) != a[9]) || (cpf.charAt(10) != a[10]) || cpf.match(expReg)) return false;

    return true;

}

getPageSize = function() {

    var xScroll, yScroll;

    if (window.innerHeight && window.scrollMaxY) {
        xScroll = window.innerWidth + window.scrollMaxX;
        yScroll = window.innerHeight + window.scrollMaxY;
    } else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac
        xScroll = document.body.scrollWidth;
        yScroll = document.body.scrollHeight;
    } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
        xScroll = document.body.offsetWidth;
        yScroll = document.body.offsetHeight;
    }

    var windowWidth, windowHeight;

    if (self.innerHeight) {	// all except Explorer
        if(document.documentElement.clientWidth){
            windowWidth = document.documentElement.clientWidth;
        } else {
            windowWidth = self.innerWidth;
        }
        windowHeight = self.innerHeight;
    } else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
        windowWidth = document.documentElement.clientWidth;
        windowHeight = document.documentElement.clientHeight;
    } else if (document.body) { // other Explorers
        windowWidth = document.body.clientWidth;
        windowHeight = document.body.clientHeight;
    }

    // for small pages with total height less then height of the viewport
    if(yScroll < windowHeight){
        pageHeight = windowHeight;
    } else {
        pageHeight = yScroll;
    }

    // for small pages with total width less then width of the viewport
    if(xScroll < windowWidth){
        pageWidth = xScroll;
    } else {
        pageWidth = windowWidth;
    }

    return [pageWidth,pageHeight];
}

abreConteudo = function(container,modulo,componente,ondeIr,params) {
    if (componente != '' && modulo != '') {
        $('#'+container).fadeIn('500', loadPage('#'+container, '/_modulos/_'+modulo+'/_'+componente+'/_views/'+ondeIr+'.jsp?'+params));
    } else if (componente == '' && modulo != '') {
        $('#'+container).fadeIn('500', loadPage('#'+container, '/_modulos/_'+modulo+'/_views/'+ondeIr+'.jsp?'+params));
    } else if (componente == '' && modulo == '') {
        $('#'+container).fadeIn('500', loadPage('#'+container, '/_modulos/_'+ondeIr+'/index.jsp?'+params));
    }
}

var ativo = "";
mostra = function(src, isIe) {

    if (ativo != src) {

        $('#'+ativo).slideToggle('slow', null);

        $('#tit_'+ativo).css({
            color: '#333',
            fontWeight: 'bold',
            backgroundColor: "#efefef"            
        });
   
        $('#'+src).slideToggle('slow', null);

        $('#tit_'+src).css({
            color: '#00478b',
            fontWeight: "bold",
            backgroundColor: "#efefff"
        });

        ativo = src;
    }
}

listaCidades = function (container, estado, cod, parametro) {
    
    $('#'+container).removeOption(/./);
    $('#'+container+' option:selected').html('Carregando...');

    $('#'+container).ajaxAddOption('/CidadesActions.do',
    {
        m: 'LIST',
        cod: cod,
        estado: estado,
        parametro: parametro
    },
    false,
    function() {
        //$('#'+container).removeOption('temp');
        $('#'+container+' option:selected').attr('disabled', 'true').html('Selecione a Cidade');
        if (cod != '' && !parametro) {
            $('#'+container).val(cod);
        } else if (cod == '' && parametro != '') {
            $('#idCidade option').each(
                function(){ 
                    if(jQuery(this).text() == parametro) {                    
                        jQuery(this).attr("selected", "selected");                     
                    } 
                });
        } else {
            alert('Não implementado');
        }
    }
    );
}
listaServico = function (container,tipoServico , cod, parametro) {
    
    $('#'+container).removeOption(/./);
    $('#'+container+' option:selected').html('Carregando...');

    $('#'+container).ajaxAddOption('/ServicoActions.do',
    {
        m: 'LISTSERVICO',
        cod: cod,
        tipoServico: tipoServico,
        parametro: parametro
    },
    false,
    function() {
        //$('#'+container).removeOption('temp');
        $('#'+container+' option:selected').attr('disabled', 'true').html('Selecione o Servico');
        if (cod != '' && !parametro) {
            $('#'+container).val(cod);
        } else if (cod == '' && parametro != '') {
            $('#idServico option').each(
                function(){ 
                    if(jQuery(this).text() == parametro) {                    
                        jQuery(this).attr("selected", "selected");                     
                    } 
                });
        } else {
            alert('Não implementado');
        }
    }
    );
}
listaSetor = function (container,setor , cod, parametro) {
    
    $('#'+container).removeOption(/./);
    $('#'+container+' option:selected').html('Carregando...');

    $('#'+container).ajaxAddOption('PatrimonioActions.do',
    {
        m: 'LISTSETOR',
        cod: cod,
        setor: setor,
        parametro: parametro
    },
    false,
    function() {
        //$('#'+container).removeOption('temp');
        $('#'+container+' option:selected').attr('disabled', 'true').html('Selecione o Setor');
        if (cod != '' && !parametro) {
            $('#'+container).val(cod);
        } else if (cod == '' && parametro != '') {
            $('#idSetor option').each(
                function(){ 
                    if(jQuery(this).text() == parametro) {                    
                        jQuery(this).attr("selected", "selected");                     
                    } 
                });
        } else {
            alert('Não implementado');
        }
    }
    );
}

//-----------------------------------------------------------------------------------------------------------------

validaData = function(data) {
    var noData = data.split('/');
    if (noData[0] > "31") {
        top.alerta('O dia preenchido não pode ser maior que 31!<BR><BR>','Atenção','','2','idData');
        return false;
    } else if (noData[1] > "12") {
        top.alerta('O mês preenchido não pode ser maior que 12!<BR><BR>','Atenção','','2','idData');
        return false;
    }
    return true;
}

stringToNumber = function(str) {
    num = str.replace(',','.');
    return Number(num);
}

checkMail = function (mail){
    var er = new RegExp(/^[A-Za-z0-9_\-\.]+@[A-Za-z0-9_\-\.]{2,}\.[A-Za-z0-9]{2,}(\.[A-Za-z0-9])?/);
    if(typeof(mail) == "string"){
        if(er.test(mail)){
            return true;
        }
    }else if(typeof(mail) == "object"){
        if(er.test(mail.value)){
            return true;
        }
    }else{
        return false;
    }
    return false;
}



updateCoords = function(c) {
    $('#x').val(c.x);
    $('#y').val(c.y);
    $('#w').val(c.w);
    $('#h').val(c.h);
}

showPreview = function(coords) {
    if (parseInt(coords.w) > 0) {
        //        var rx = 128 / coords.w;
        //        var ry = 149 / coords.h;
        //
        //        jQuery('#preview').css({
        //            width: Math.round(rx * <?php echo $arquivo->getWidth();?>) + 'px',
        //            height: Math.round(ry * <?php echo $arquivo->getHeight();?>) + 'px',
        //            marginLeft: '-' + Math.round(rx * coords.x) + 'px',
        //            marginTop: '-' + Math.round(ry * coords.y) + 'px'
        //        });
        updateCoords(coords);

    }
}

ajaxRequest = function(urlSend,sendData,returnDataType,typeSend,asyncRequest){
                
    var result;
                
    if(!typeSend){
        typeSend='post';
    }
    if(!returnDataType){
        returnDataType='script';
    }
                
                
    $.ajax({
        type: typeSend,
        url: urlSend, 
        data: sendData, 
        async:  asyncRequest,
        dataType: returnDataType, 
        success: function(info){
            result= info;
        },
        error:  function(error){
        //alert('Error Request : '+error.message);
        },
        beforeSend: function(){
        //$('.loading').show();
        //$('#container').fadeOut(200);
        },
        complete: function(){
        //$('#container').fadeIn(200);
        //$('.loading').hide();
        }
  
    });
    return result;          
}

loadItens = function(url,mode,page,limit,params){
    ajaxRequest(urlSend=url, sendData={
        mode:mode,
        page:page,
        limit:limit,
        params:params
    }, returnDataType=null, typeSend=null, asyncRequest=true);
}


executaActions = function(container, ondeIr, params) {
    $('#'+container).fadeIn('500', loadPage('#'+container, ondeIr+'?'+params));
}

setOrder = function(id,value,ondeIr) {
    executaActions("processa", ondeIr, "id="+id+"&acao="+value+"&m=SAVEPOS");
}

setDestaque = function(id) {
    if (nDestaque == 4 && $('#dest_'+id).is(":checked")) {
        alerta("São permitidos no máximo 4 Destaques.<BR/>", "ATENÇÃO", '', '', '');
        $('#dest_'+id).attr("checked", false);
    } else {
        var acao = '0';
        if ($('#dest_'+id).is(":checked")) { 
            acao = '1';
            nDestaque += 1;
        } else {
            nDestaque -= 1;
        }
        //alert(nDestaque);
        executaActions("processa", "_modulos/_banners/_lib/BannerActions.jsp", "id="+id+"&acao="+acao+"&m=STATDESTAQUE");
    }   
}

setMostraNota = function(id) {
    
    if ($('#dest_'+id).is(":checked")) { 
        acao = '1';
    } else {
        acao= '0';
    }
    //alert(nDestaque);
    executaActions("processa", "_modulos/_processo_seletivo/_vestibulares/_lib/VestibularActions.jsp", "id="+id+"&acao="+acao+"&m=SETRES");
}

var qcms = {};
qcms.DestroyCKEditorInstance = function (instanceName){
    if(undefined !== window.CKEDITOR){
        if((document.getElementById(instanceName)) && CKEDITOR.instances[instanceName]){

            CKEDITOR.remove(CKEDITOR.instances[instanceName]);

            var ckeElement = document.getElementById('cke_' + instanceName) ;
            if(ckeElement)
                ckeElement.parentNode.removeChild(ckeElement);

        }

    }
}

function checkRequired(){
    var flagCheck = true;
    $('.required').each(function() {
        if(this.tagName=="INPUT"||this.tagName=="SELECT"||this.tagName=="TEXTAREA"){
            if($(this).val()==""){
                flagCheck = false;
                $('#'+$(this).attr('id')).effect("highlight", {}, 10000);
                var txtCampo = (this.tagName=="SELECT")?'selecionar':'preencher';
                top.alerta('É necessário '+txtCampo+'(s) o(s) campo(s)!<BR><BR>','Campos Necessários','','2',$(this).attr('id'));
                return flagCheck;
            }
        }
    });
    return flagCheck;
}

