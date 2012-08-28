$(function() {
    configuraImagemLoading();
    scripts();
});

scripts = function() {
//    stylesFieldsForm("true");

    jQuery('#cropbox').Jcrop({
        setSelect: [ 0, 0, 128, 149 ],
        onChange: showPreview,
        onSelect: showPreview,
        aspectRatio: 0.86
    });

    $('#divPreload').hide();

    $('form').ajaxForm({
        target: $('#idForm').attr("target")
    });

    $('a').unbind('click');
    $('a').click(function(){
        $($(this).attr("target")).load($(this).attr("href"), function() {
            scripts();
        });
        return false;
    });
    $('#gallery a.lightbox').lightBox();
    $('#galleryDestaque a.lightbox').lightBox();

}

selecionaMenu = function(src) {

    if (menuSel != "") {
        $('#btnInterno'+menuSel).css({
            color: '#107bc0'
        });
        $('#fundoBtnInterno'+menuSel).hide();
    }

    $('#btnInterno'+src).css({
        color: '#fff'
    });
    $('#fundoBtnInterno'+src).show();

    menuSel = src;

}

/*
 * ******************** SITE
 */

mOut = function(src,opt) {
    if (opt != "") {
        if (src != menuSel) {
            $ ('#btnInterno'+opt).css({
                color: '#107bc0'
            });
            $('#fundoBtnInterno'+src).hide();
        }
    } else {
        $('#fundoBtn'+src).hide();
    }
}

mOver = function(src,opt) {
    if (opt != "") {
        if (src != menuSel) {
            $ ('#btnInterno'+opt).css({
                color: '#fff'
            });
            $('#fundoBtnInterno'+src).show();
        }
    } else {
        $('#fundoBtn'+src).show();
    }
}

executaActions = function(container, ondeIr, params) {
    $('#'+container).fadeIn('500', loadPage('#'+container, ondeIr+'?'+params));
}

deleteImgAlbum = function(params, ondeIr, texto, container, opt,modulo,componente) {
    if (modulo != '' && componente != '') {
        new WindowConfirm("Deseja Realmente <strong>APAGAR</strong> "+texto+" ? ",'/_modulos/_'+modulo+'/_'+componente+'/_lib/'+ondeIr+'.php?'+params,container,opt);
    } else if (modulo != '' && componente == '') {
        new WindowConfirm("Deseja Realmente <strong>APAGAR</strong> "+texto+" ? ",'/_modulos/_'+modulo+'/_lib/'+ondeIr+'.php?'+params,container,opt);
    }
}

AddRemoveAmigo = function(arq, container, param, opt, acao, modulo, componente) {
    var txt = '';
    if (acao == "1") {
        txt = 'ADICIONAR O MEMBRO';
    } else if (acao == "2") {
        txt = 'REMOVER O MEMBRO';
    }
    if (modulo != '' && componente != '') {
        new WindowConfirm("Deseja Realmente <strong>"+txt+"</strong>? ",'/_modulos/_'+modulo+'/_lib/'+arq+'.php?'+param,container,opt);
    } else if (modulo != '' && componente == '') {
        new WindowConfirm("Deseja Realmente <strong>"+txt+"</strong>? ",'/_modulos/_'+modulo+'/_lib/'+arq+'.php?'+param,container,opt);
    }
}

showIcone = function(src) {
    $('#icone_'+src+'_verm').fadeIn('400');
    $('#sub_'+src).css({
        color: '#ed1c24'
    });
}

hideIcone = function(src) {
    $('#icone_'+src+'_verm').fadeOut('200');
    $('#sub_'+src).css({
        color: '#444'
    });
}

hideDivs = function(ondeIr) {
    if (ondeIr == 'inscricao') {
        ativo = "login";
        $('#txtImagem').hide();
        $('#txtConhecimento').css('height','21');
        $('#btnHome').fadeIn('500');
        $('#flag').fadeOut('500');
        $('#cursos').fadeOut('500');
        $('#postitFesb').fadeOut('500');
        $('#areaFoto').fadeOut('200');
        $('#conteudo').css({
            width: '490px',
            marginTop: '165px',
            height: '415px'
        });
    } else if (ondeIr == 'localizacao') {
        ativo = "login";
        $('#btnHome').hide();
        $('#txtConhecimento').css('height','36');
        $('#txtImagem').fadeIn('500');
        $('#flag').fadeOut('500');
        $('#cursos').fadeOut('500');
        $('#postitFesb').fadeOut('500');
        $('#areaFoto').fadeOut('200');
        $('#conteudo').css({
            marginTop: '180px',
            width: '470px',
            height: '390px'
        });
    }

    if (ondeIr != "manual") {
        $('#flag').fadeOut('500');
    }
}

showDivs = function(ondeIr) {
    if (ondeIr != 'inscricao' && ondeIr != 'localizacao') {
        $('#btnHome').hide();
        $('#txtConhecimento').css('height','36');
        $('#txtImagem').fadeIn('500');
        $('#cursos').fadeIn('500');
        $('#postitFesb').fadeIn('500');
        $('#areaFoto').fadeIn('200');
        $('#conteudo').css({
            marginTop: '180px',
            width: '330px',
            height: '260px'
        });
    }
    if ( ondeIr != 'agendar' && ondeIr != "inscricao" && ondeIr != "login" && ondeIr != "localizacao" && ondeIr != "logout" && ondeIr != "boleto" && ondeIr != "contato" && ondeIr != "resultado" && ondeIr != "dados_resultado") {
        $('#flag').fadeIn('200');
    }
}

selecionaMenu = function(src) {

    if (menuSel != "") {
        $('#btnInterno'+menuSel).css({
            color: '#107bc0'
        });
        $('#fundoBtnInterno'+menuSel).hide();
    }

    $('#btnInterno'+src).css({
        color: '#fff'
    });
    $('#fundoBtnInterno'+src).show();

    menuSel = src;

}

carregaBanner = function(id, p, isClick) {
    p = Number(p);
    click = isClick;

    $('#areaPublicidade').fadeOut(200, function() {
        $('#areaPublicidade').fadeIn(300, function() {
            loadPage('#areaPublicidade', '/_modulos/_banners/_views/publicidade.php?id='+id);
        })
    });
}

nextBanner = function() {
    if ((pos+1)<valores.length) {
        posAnt = pos;
        pos = pos +1;
    } else {
        pos = 0;
        posAnt = valores.length-1;
    }
    $('#areaPublicidade').html('');
    setaBtnBanner();
    carregaBanner(valores[pos], pos, false);
}

trocaBanner = function(p) {
    posAnt = pos;
    pos = p;
    setaBtnBanner();

    carregaBanner(valores[pos], pos, false);
}

setaBtnBanner = function() {
    for (i=0;i<valores.length;i++) {
        $('#btnBanner'+i).removeClass("btnBannerOn");
        $('#btnBanner'+i).addClass("btnBannerOff");
    }

    $('#btnBanner'+pos).removeClass("btnBannerOff");
    $('#btnBanner'+pos).addClass("btnBannerOn");
}

$('#menuInstitucional li').live({
        mouseenter:
           function()
           {
               $(this).find('div').css('background-color', '#f8e000');
               $(this).find('.primeiraImg').attr('src', '/_modulos/_site/_images/menu_esqH.png')
               $(this).find('.segundaImg').attr('src', '/_modulos/_site/_images/menu_dirH.png')
           },
        mouseleave:
           function()
           {
                $(this).find('div').css('background-color', '#fba61a');
                $(this).find('.primeiraImg').attr('src', '/_modulos/_site/_images/menu_esq.png')
                $(this).find('.segundaImg').attr('src', '/_modulos/_site/_images/menu_dir.png')
           }
       }
    );


facaBusca = function(){
    value = $('#busca').val();
    if(value == 'Busca'){
        top.alerta('Adicione algo no campo de busca<BR><BR>','Campos Necessários','','2','search');
        return false;
    } else if(value.length < 3) {
        top.alerta('Entre com pelo menos 3 caracteres.<BR><BR>','Campos Necessários','','2','search');
        return false;
    } else {
        $.post("/_modulos/_site/doSearch.php", {str: value },
            function(data) {
                $('#conteudo').html(data);
        });
    }
}

$('.btnBusca').live('click', function(){
    facaBusca();
});

$('input[id=busca]').live('keypress', function(e){
            if(e.which == 13){
                 facaBusca();
            }
 });