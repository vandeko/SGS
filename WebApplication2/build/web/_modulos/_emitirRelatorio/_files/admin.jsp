<%@page import="sun.swing.PrintColorUIResource"%>
<%@page import="net.sf.jasperreports.engine.JRException"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="helpDesk.DAO.HibernateConnection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="helpDesk.entidades.Usuario"%>
<%@page import="helpDesk.DAO.UsuarioDAO"%>
<%@page import="helpDesk.entidades.Setor"%>
<%@page import="helpDesk.DAO.SetorDAO"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="helpDesk.entidades.Chamado"%>
<%@page import="helpDesk.DAO.ChamadoDAO"%>
<%@page import="javax.swing.JOptionPane"%>

<html>
    <head>
        <meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
        <script type="text/javascript">	
            
            mostraDiv = function(src) {
                var ativo = "";
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
                if(src=="graficoFuncionario"){
                    
            <% List usuario = null;
                UsuarioDAO udao = new UsuarioDAO();
                udao.setClasse(Usuario.class);
                udao.setSession(HibernateConnection.getSession());
                usuario = udao.listAll(null, "Usuario.findAll");
                if (usuario != null) {%>
                            var myData = new Array( 
            <% for (int i = 0; i < usuario.size(); i++) {
                    Usuario user = (Usuario) usuario.get(i);
                    int count = HibernateConnection.getSession().createQuery("select count(*) from DetalheChamado where usuario =" + user.getId() + "").uniqueResult().hashCode();
            %>
                
                        ['<%out.print(user.getNome());%>',<%out.print(count);%>] 
            <% if (i < usuario.size() - 1) {
                        out.print(",");
                    } else {
                        out.print("");
                    }
                }%>);
                            var grafico = new JSChart('graficoFuncionario', 'bar');
                            grafico.setDataArray(myData);
                            grafico.setTitle('Funcionario que realizaram os chamados');
                            
            <%}%>              
               
                    }else if(src=="graficoSetor"){
            <%
                List setor = null;
                SetorDAO sdao = new SetorDAO();
                sdao.setClasse(Setor.class);
                sdao.setSession(HibernateConnection.getSession());
                setor = sdao.listAll(null, "Setor.findAll");

                if (setor != null) {%>
                            var myData1 = new Array( 
            <%for (int i = 0; i < setor.size(); i++) {
                    Setor set = (Setor) setor.get(i);
                    int count = HibernateConnection.getSession().createQuery("select count(*) from Chamado where setor =" + set.getId() + "").uniqueResult().hashCode();
            %>
                        ['<%out.print(set.getNome());%>',<%out.print(count);%>] 
            <% if (i < setor.size() - 1) {
                        out.print(",");
                    } else {
                        out.print("");
                    }
                }%>);
                            var grafico = new JSChart('graficoSetor', 'bar');
                            grafico.setDataArray(myData1);
                            grafico.setTitle('Setores com incidência de abertura de chamados');
                            
            <%}%>
                
                    }else if(src=="grafico"){
            <%  List lista = new ArrayList();
                lista.add(0);
                lista.add(1);
                lista.add(2);
                lista.add(3);
                lista.add(4);
                if (lista != null) {%>
                            var myDat = new Array(               
            <%for (int i = 0; i < lista.size(); i++) {
                    int count = HibernateConnection.getSession().createQuery("select count(*) from Chamado where situacao=" + lista.get(i) + "").uniqueResult().hashCode();
            %>
                        ['<%if (lista.get(i).equals(0)) {
                                out.print("Aguardando Atendimento");
                            } else if (lista.get(i).equals(1)) {
                                out.print("Em Atendimento");
                            } else if (lista.get(i).equals(2)) {
                                out.print("Aguardando Agendamento");
                            } else if (lista.get(i).equals(3)) {
                                out.print("Cancelado");
                            } else if (lista.get(i).equals(4)) {
                                out.print("Encerrado");
                            }%>',<%out.print(count);%>] 
            <% if (i < lista.size() - 1) {
                        out.print(",");
                    } else {
                        out.print("");
                    }
                }%>);
                                var grafico = new JSChart('grafico', 'bar');
                                grafico.setDataArray(myDat);
                                grafico.setTitle('Status dos chamados');
                            
            <%}%>                
                        }
                   
                        grafico.setTitleColor('#383535');
                        grafico.setAxisNameX('');
                        grafico.setAxisNameY('');
                        grafico.setAxisColor('#C4C4C4');
                        grafico.setAxisNameFontSize(16);
                        grafico.setAxisNameColor('#999');
                        grafico.setAxisValuesColor('#777');
                        grafico.setAxisColor('#B5B5B5');
                        grafico.setAxisWidth(1);
                        grafico.setBarValuesColor('#2F6D99');
                        grafico.setBarOpacity(0.5);
                        grafico.setAxisPaddingTop(60);
                        grafico.setAxisPaddingBottom(40);
                        grafico.setAxisPaddingLeft(45);
                        grafico.setTitleFontSize(12);
                        grafico.setBarBorderWidth(0);
                        grafico.setBarSpacingRatio(50);
                        grafico.setBarOpacity(0.9);
                        grafico.setFlagRadius(6);                
                        grafico.setTooltipOffset(3);
                        grafico.setSize(616, 321);
                        grafico.draw();
                
                    }
                    gerarRelatorio = function(){
            <%
                // try {
                //   JasperExportManager.exportReportToHtmlFile("D:/exemplo1.jrprint");
                //} catch (JRException ex) {
                //  out.print(ex.getMessage());
                //}
%>
        }
                
                
        </script>
    </head>
    <body>
        <div id="navegacao" style="height: 590px">
            <div id="formulario">
                <form id="idForm" action="" method="POST" target="#processa">
                    <div id="chart_div"></div>
                    <input type="hidden" name="mode" value="SAVE"/>
                    <input type="hidden" id="idId" name="id" value=""/>
                    <ul class="markup">
                        <li class="porPeriodo">
                            <div style="padding-top: 10px;text-align: center">
                                <input type="radio" name="group" value="Water"style="padding: 10px;"onclick="mostraDiv('graficoSetor')">Por Setor
                                <input type="radio" name="group" value="Beer"style="padding: 10px;"onclick="mostraDiv('grafico')">Chamado
                                <input type="radio" name="group" value="Wine"style="padding: 10px;"onclick="mostraDiv('graficoFuncionario')">Funcionario 
                            </div>
                            <div class="clear"></div>
                        </li>

                        <li>
                            <div id="grafico" style="display: none">Loading...
                                <div class="right rightFix" style="width: 126px;">
                                    <div class="btnPadraoR" onclick="gerarRelatorio()">Relatorio</div>
                                </div>
                                <div class="clear"></div>
                            </div>

                            <div class="clear"></div>
                        </li>
                        <li>
                            <div id="graficoFuncionario" style="display: none">Loading...
                                <div class="right rightFix" style="width: 126px;">
                                    <div class="btnPadraoR" onclick="gerarRelatorio()">Relatorio</div>
                                </div>
                                <div class="clear"></div>
                            </div>

                            <div class="clear"></div>
                        </li>
                        <li>
                            <div id="graficoSetor" style="display: none">Loading...
                                <div class="clear"></div>
                            </div>
                            <div class="clear"></div>
                        </li>
                        <div class="right rightFix" style="width: 126px;">
                            <div class="btnPadraoR" onclick="gerarRelatorio()">Relatorio</div>
                        </div>
                        <div class="clear"></div>
                    </ul>
                </form>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
        </div>

    </body>
</html>
