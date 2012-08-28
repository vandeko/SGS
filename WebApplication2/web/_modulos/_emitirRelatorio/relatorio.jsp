<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
body {
	font-family:Arial, Helvetica, sans-serif;
}
table tr td {
	padding:15px;
}
.titulo {
	background:#CCC;
	color:#FFF;
	padding:7px;
	font-weight:bold;
}
.tituloColunas td{
	background:#f5f4f4;
	padding:5px;
	font-size:12px;
	font-weight:bold;
	border-bottom:1px solid #CCC;
	text-align:left;
}
.linhaRegistros td{
	padding:5px;
	border-bottom:1px solid #CCC;
	font-size:12px;
	text-align:left;
	
}
.data{
	text-align:right
}
</style>
<title>Relatório</title>
</head>
<body>
<table width="100%" border="0" align="center" class="tabelaContainer">
  <tr>
    <td><img src="/_modulos/_sistema/_images/tcc4.png" border="0" /></td>
  </tr>
  <tr>
    <td class="titulo">Funcionário</td>
  </tr>
  <tr>
    <td>
    	<table width="100%" border="0" align="center">
        	<tr class="tituloColunas">
          		<td>Numero do Chamado</td>
          		<td>Aberto por</td>
          		<td>Executando em Setor</td>
          		<td>Executador por</td>
          		<td>Data de Abertura</td>
        	</tr>
        	<tr class="linhaRegistros">
          		<td>1</td>
		        <td>Maria Jose Camargo</td>
          		<td>Administracao Financeira</td>
          		<td>123</td>
          		<td>11/07/12 00:00</td>
        	</tr>
        	<tr class="linhaRegistros">
          		<td>2</td>
		        <td>Maria Jose Camargo</td>
          		<td>Administracao Financeira</td>
          		<td>123</td>
          		<td>11/07/12 00:00</td>
        	</tr>
        	<tr class="linhaRegistros">
          		<td>4</td>
		        <td>Maria Jose Camargo</td>
          		<td>Administracao Financeira</td>
          		<td>123</td>
          		<td>11/07/12 00:00</td>
        	</tr>
      	</table>
      </td>
  </tr>
  <tr>
  	<td class="data">Sexta-feira 10 Agosto</td>
  </tr>
</table>
</body>
</html>
