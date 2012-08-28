$('#navegacao #picWrapper').live({
   mouseenter:
      function(){
         $this = $(this);
         pic = $this.find('#pic');
         overlay = $this.find('#overlay');
         pic.animate({'opacity':'0.8'}, 'fast', function(){
            overlay.stop().animate({'left':'160px'}, 'fast', 'easeOutQuart');
         });
      },
   mouseleave:
      function(){
         $this = $(this);
         $this.find('#pic').animate({'opacity':'1'}, 'fast');
         $this.find('#overlay').stop().animate({'left':'200px'}, 'fast', 'easeOutQuart')
      }
});

$('#navegacao #picWrapperMin').live({
   mouseenter:
      function(){
         $this = $(this);
         pic = $this.find('#pic');
         overlay = $this.find('#overlay');
         pic.animate({'opacity':'0.8'}, 'fast', function(){
            overlay.stop().animate({'left':'80px'}, 'fast', 'easeOutQuart');
         });
      },
   mouseleave:
      function(){
         $this = $(this);
         $this.find('#pic').animate({'opacity':'1'}, 'fast');
         $this.find('#overlay').stop().animate({'left':'120px'}, 'fast', 'easeOutQuart')
      }
});


delPic = function(obj, action){
    arquivo = obj.data('arquivo');
    id = obj.data('id');
    input = obj.data('input');
    confirma('Deseja realmente apagar a foto?', action+'?m=DELPIC&id='+id+'&arquivo='+arquivo+'&input='+input, '#processa', '');
}

$(function() {
    configuraImagemLoading();
    scripts();
});

scripts = function() {
    $('#divPreload').hide();
    stylesFieldsForm(true);

    if ($('form').attr('id') != 'TestePS' && $('form').attr('target') != 'relatorio') {
        $('form').ajaxForm(
            {target: $('form').attr("target")}
        );
    }

   $('#gallery a.lightbox').lightBox();
   $('a.lightbox').lightBox();
}


selecionaMenu = function (opt) {
    for (i=0; i<menus.length; i++) {
        if (opt == menus[i]) {
            $('#'+opt).css({
//                backgroundColor:'#fCC',
                backgroundColor:'#FFF',
                borderRadius:'2px',
                boxShadow: '1px 1px 1px -1px #8A6464'
            });
        } else {
            $('#'+menus[i]).css({
                backgroundColor:'',
                boxShadow: ''
            });
        }
    }
}

mClick = function(src) {
    sel = $(src).attr('id');
    $('.menuAdmin').show();
    $('.menuSecundario').show();
}

mOut = function(src) {
    if ($(src).attr('id') == 'sel') {
        $(src).css({
            backgroundColor:'#ddd'
        });
    }
}

mOver = function(src) {
    if ($(src).attr('id') == 'sel') {
        $(src).css({
            backgroundColor:'#ccc'
        });
    }
}

createMenu = function(object){
    $('.subMenu').fadeOut();
    offset = object.position();
    var t = offset.top;
    var l = offset.left;
    submenu = object.parent().find('.subMenu');
    submenu.css({top: t, left:l+70}).fadeIn();
}

createDdMenu = function(object){
    object.addClass('hoverFix');
    object.find('ul').show();
}

hideDdMenu = function(object){
    object.removeClass('hoverFix');
    object.find('ul').hide();
}