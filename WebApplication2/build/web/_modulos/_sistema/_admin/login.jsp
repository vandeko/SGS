<%@page import="gestordeprocessos.util.Configuracoes"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title> .:: <%=Configuracoes.TITULO_SITE.getConfig()%> ::. </title>
        <jsp:include page="/_modulos/_sistema/_stylesAdm.jsp"/>
        <jsp:include page="/_modulos/_sistema/_scriptsAdm.jsp"/>
    </head>
    <body>
       <div class="containerPanel">
           
        </div> 
        <div style="background: black;width: 100%;height: 5%;"></div>
        <div class="container">
            <div class="containerLogin" id="containerLogin">
                <table align="center"  valign="center" id="tableLogin">
                    <form method="POST" action="/UsuarioActions.do" id="formLogin" target="#message">
                        <input type="hidden" name="mode" value="LOGIN"/>
                        <tr>
                            <td class="txtLogin">Login: </td>
                            <td><input class="inputAdm" type="text" id="login" name="login" value="login" onfocus="cleanInputField('login', this); elasticContainer('show');" onblur="fillInputField('login', this);"/></td>
                        </tr>
                        <tr>
                            <td class="txtLogin">Senha: </td>
                            <td><input class="inputAdm" type="password" id="senha" name="senha" value="senha" onfocus="cleanInputField('senha', this); elasticContainer('show');" onblur="fillInputField('senha', this);"/></td>
                        </tr>
                        <tr>
                            <td colspan="2" style="text-align: right"><img class="btnLogin" src="/_modulos/_sistema/_images/btn_ok_login.png" style="margin-right: -5px; display: none; cursor: pointer" onclick="$('#message').hide(); $('#formLogin').submit();"/></td>
                        </tr>
                    </form>
                </table>
                <div class="clear"></div>
            </div>               

            <div class="containerDialogo">
                <div id="dialogoMensagem" class="dialogoMensagem" style="display:none;">
                    <div class="simbolo">!</div>
                    <div id="message" class="mensagem"></div>
                    <div class="fechar" onclick="$('#dialogoMensagem').fadeOut(200);">X</div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <section id="rodapeWrapper">
            <article id="rodapeContainer">        
                <div class="main">
                    @2012 <span id="helv">Sistema de Gerenciamento de Suporte TI  <br />
                    </span><br />
                </div>
            </article>
            <div id="copy">Desenvolvido por Edlaine, Sueli e Vanderlei</div>
        </section>

        <div id="divPreload" style="width: 50px; height: 50px; background-color: #fff; position: absolute; float: right; top: 10px; right: 30px; border: 1px solid #9e0000; display: none;">
            <table cellpadding="0" cellspacing="0" width="50" height="50">
                <tr>
                    <td width="60" align="center">
                        <img src='/_modulos/_sistema/_images/preload.gif' alt=""/>
                    </td>
                </tr>
            </table>
        </div>

        <%=Configuracoes.DIV_PROCESSA.getConfig()%>
        <script>
            $('.container').css('height',(jQuery(window).height()-69)+'px');

            function elasticContainer(option){
                switch (option){
                    case 'show' :
                        $('#containerLogin').animate({width: "300px"}, 700);
                        $('#containerLogin input').animate({width: "200px"}, 700);
                        $('.btnLogin').fadeIn(200);
                        break;
                    case 'close' :
                        $('#containerLogin').animate({width: "200px"}, 700);
                        $('#containerLogin input').animate({width: "100px"}, 700);
                        break;
                }
            }
            $('#senha').bind('keypress', function(e) {
                var code = (e.keyCode ? e.keyCode : e.which);
                if(code == 13) {
                    $('#message').hide();
                    $('#formLogin').submit();
                }
            });
        </script>
    </body>
</html>
