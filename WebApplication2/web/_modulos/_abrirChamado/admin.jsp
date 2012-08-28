<%@page import="java.util.List"%>
<%@page import="gestordeprocessos.util.Configuracoes"%>


<div>
    <div id="flexiGrid" style="display:none;"></div>
    <div class="clear"></div>
</div>

<div class="clear"></div>

<div id="editaisWrap">
    <jsp:include page="_files/admin.jsp"/>
</div>

<script>
   $('#lista').css('height',(jQuery(window).height()-274)+'px');
   selecionaMenu('abrirChamado');
</script>