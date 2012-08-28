
<%@page import="gestordeprocessos.util.Configuracoes"%>
<%@page import="helpDesk.entidades.Usuario"%>
<%@page import="java.util.Collection"%>
<%@page import="java.lang.Object"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.List"%>

<%
    Usuario user = (Usuario) session.getAttribute("usuarioSIST");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title> .:: <%=Configuracoes.TITULO_SITE.getConfig()%>  ::. </title>
        <jsp:include page="/_modulos/_sistema/_stylesAdm.jsp"/>
        <jsp:include page="/_modulos/_sistema/_scriptsAdm.jsp"/>
    </head>
    <body>
        <div class="containerPanel">

        </div>        
        <div style="background: black;width: 100%;">
            <div class="menuSecundario" style="padding: 1%;">
                <div class="miniIcon"style="float: left;"onclick="location.href='admin.jsp';"><img src="_modulos/_sistema/_images/_icones/_miniaturas/painel.png" alt="Painel" title="Painel" border="0" align="absmiddle" /></div>
                <% if (user.getPermissao() == 3 || user.getPermissao() == 4) {%>
                <div class="miniIcon"style="padding-left: 20%;padding-right: 15%;"id="acompChamado" onclick="loadPage('#conteudo','/_modulos/_acompanharChamado/admin.jsp?m=0');"><img src="_modulos/_sistema/_images/_icones/_miniaturas/acompanharChamado.png" alt="" title="Acompanhar Chamado" border="0" align="absmiddle" /></div>
                <div class="miniIcon"id="abrirChamado" onclick="loadPage('#conteudo','/_modulos/_abrirChamado/admin.jsp?m=2');"><img src="_modulos/_sistema/_images/_icones/_miniaturas/abrirChamado.png" alt="" title="Abrir Chamado" border="0" align="absmiddle" /></div>
                <%} else if (user.getPermissao() == 2) {%>
                <div class="miniIcon"style="padding-left: 5%;padding-right: 10%;background: url(_modulos/_sistema/_images/_icones/_miniaturas/realizarAtendimento.png)no-repeat"id="realizar" onclick="loadPage('#conteudo','/_modulos/_realizarAtendimento/admin.jsp?m=5');"></div>
                <%} else if (user.getPermissao() == 1) {%>
                <div class="miniIcon"style="padding-left: 10%;padding-right: 10%;background: url(_modulos/_sistema/_images/_icones/_miniaturas/realizarAtendimento.png)no-repeat;"id="acompChamado" onclick="loadPage('#conteudo','/_modulos/_realizarAtendimento/admin.jsp?m=5');"></div>
                <div class="miniIcon"style="padding-right: 10%;"id="cadastro" ><img src="_modulos/_sistema/_images/_icones/_miniaturas/cadastro.png" alt="" title="Cadastro" border="0" align="absmiddle" />
                    <ul class='ddMenu'>
                        <%
                            out.print("<li onclick=\"mClick(this); loadPage('#conteudo','/_modulos/_setor/admin.jsp?m=0');\">Setor</li>");
                            out.print("<li onclick=\"mClick(this); loadPage('#conteudo','/_modulos/_patrimonio/admin.jsp?m=1');\">Patrimonio</li>");
                            out.print("<li onclick=\"mClick(this); loadPage('#conteudo','/_modulos/_servico/admin.jsp?m=2');\">Servico</li>");
                            out.print("<li onclick=\"mClick(this); loadPage('#conteudo','/_modulos/_tipoServico/admin.jsp?m=3');\">TipoServico</li>");
                            out.print("<li onclick=\"mClick(this); loadPage('#conteudo','/_modulos/_usuario/admin.jsp?m=4');\">Usuario</li>");

                        %>
                    </ul>
                </div>
                <div class="miniIcon"style="padding-right: 10%;"id="abrirChamado" onclick="loadPage('#conteudo','/_modulos/_abrirChamado/admin.jsp?m=2');"><img src="_modulos/_sistema/_images/_icones/_miniaturas/abrirChamado.png" alt="" title="Abrir Chamado" border="0" align="absmiddle" /></div>
                <div class="miniIcon"style="padding-right: 13.5%;"id="acompChamado" onclick="loadPage('#conteudo','/_modulos/_acompanharChamado/admin.jsp?m=0');"><img src="_modulos/_sistema/_images/_icones/_miniaturas/acompanharChamado.png" alt="" title="Acompanhar Chamado" border="0" align="absmiddle" /></div>
                <div class="miniIcon"style="padding-right: 10%"id="acompChamado" onclick="loadPage('#conteudo','/_modulos/_agenda/admin.jsp?m=3');"><img src="_modulos/_sistema/_images/_icones/_miniaturas/agenda.png" alt="" title="Realizar Atendimento" border="0" align="absmiddle" /></div>

                <%} else {%>
                <div class="miniIcon"style="padding-left: 15%;padding-right: 15%;"id="acompChamado" onclick="loadPage('#conteudo','/_modulos/_realizarAtendimento/admin.jsp?m=5');"><img src="_modulos/_sistema/_images/_icones/_miniaturas/realizarAtendimento.png" alt="" title="Realizar Atendimento" border="0" align="absmiddle" /></div>
                <div class="miniIcon"style="padding-right: 8%;"id="acompChamado" onclick="loadPage('#conteudo','/_modulos/_agenda/admin.jsp?m=3');"><img src="_modulos/_sistema/_images/_icones/_miniaturas/agenda.png" alt="" title="Agenda" border="0" align="absmiddle" /></div>
                <div class="miniIcon"style="padding-right: 15%;"id="acompChamado" onclick="loadPage('#conteudo','/_modulos/_emitirRelatorio/admin.jsp?m=4');"><img src="_modulos/_sistema/_images/_icones/_miniaturas/emitirRelatorio.png" alt="" title="Emitir Relatorio" border="0" align="absmiddle" /></div>
                <%}%>
                <div class="miniIcon" style="float: right"onclick="location.href='/_modulos/_sistema/_admin/logout.jsp';"><img src="_modulos/_sistema/_images/_icones/_miniaturas/sair.png" alt="Sair" title="Sair" border="0" align="absmiddle" /></div>
            </div>
        </div>
        <div class="container">
            <div id="conteudo">
                <div style="padding: 100px;text-align: center">
                    <h2>Matricula: <%out.print(user.getId());%></h2>
                    <h2>Nome: <%out.print(user.getNome());%></h2>
                    <h2>Setor: <%out.print(user.getSetor().getNome());%></h2>
                    <h2>Permissao: <%if (user.getPermissao() == 4) {
                            out.print("Avançado");
                        } else if (user.getPermissao() == 3) {
                            out.print("Comum");
                        } else if (user.getPermissao() == 2) {
                            out.print("Estagiario");
                        } else if (user.getPermissao() == 1) {
                            out.print("Moderador");
                        } else {
                            out.print("Gerente");
                        }%></h2>
                    <div class="clear"></div>
                </div>
                <div class="colunaDireita">
                    <div class="btnWrap">
                        <div class="subMenu">
                            <div style="clear: both"></div>
                        </div>
                        <div class="clear"></div>
                    </div>                
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
        </div>
        <div id="divPreload" style="width: 50px; height: 50px; background-color: #fff; position: absolute; float: right; top: 10px; right: 30px; border: 1px solid #9e0000; display: none; z-index: 999999;">

            <table cellpadding="0" cellspacing="0" width="50" height="50">
                <tr>
                    <td width="60" align="center">
                        <img src='_modulos/_sistema/_images/preload.gif' alt=""/>
                    </td>
                </tr>
            </table>
        </div>

        <div id="rodapeWrapper">
            <div id="rodapeContainer">        
                <div class="main"style="text-align: center">
                    @2012 <span id="helv">Sistema de Gerenciamento de Suporte TI  <br />
                    </span><br />
                </div>
            </div>
        </div>
        <%=Configuracoes.DIV_PROCESSA.getConfig()%>

        <script>
            $('.container').css('minHeight',(jQuery(window).height()-71)+'px');           
                     
            $('.miniIcon').live("mouseenter mouseleave", function(event){
                var ct = $(this).children().size();
                if(ct > 1){
                    if (event.type == 'mouseenter') {
                        $('.miniIcon').stop(true, true);
                        createDdMenu($(this));
                    } else {
                        $('.miniIcon').stop(true, true);
                        hideDdMenu($(this));
                    }
                }
            });
            
        </script>
        <div class="clear"></div>
    </body>
</html>        

