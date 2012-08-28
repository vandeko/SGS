<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:h="http://java.sun.com/jsf/html">
    <head>
        <title>.:: Sistema de Gerenciamento de Suporte ::.</title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>

    </head>
    <body style="margin:0;"> 

        <div id="header" style="float: left; width: 100%; height: 100px; background: #efefef; border-bottom: 1px #ccc solid;">        
        </div>

        <div id="menu" style="float: left; width: 1000px; height: 45px; background: #fafafa; border-radius: 8px;; margin-top: 5px;">        
        </div>

        <div id="container" style="float: left; margin: 0 auto; width: 100%; min-height: 45px; background: #efefef; margin-top: 5px;">        
            <div id="conteudo" style="float: left; margin: 0 auto; width: 1000px; height: 100%; background: #ffffff;margin-top: 2px;">        
            </div>
        </div>


    </body>


    <script>
        $('#container').css('height',(jQuery(window).height()-178)+'px');    
        $('#menu').css('marginLeft',(jQuery(window).width()-1000)/2+'px');
        $('#conteudo').css('height',($('#container').height()-4)+'px');
        $('#conteudo').css('marginLeft',(jQuery(window).width()-1000)/2+'px');
    </script>

    <% session.setAttribute("usuarioSIST", null);%>
</html> 