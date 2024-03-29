<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head id="Head1">
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
            <link href="/_modulos/_agenda/_files/css/dailog.css" rel="stylesheet" type="text/css"/>
            <link href="/_modulos/_agenda/_files/css/calendar.css" rel="stylesheet" type="text/css"/> 
            <link href="/_modulos/_agenda/_files/css/dp.css" rel="stylesheet" type="text/css"/>   
            <link href="/_modulos/_agenda/_files/css/alert.css" rel="stylesheet" type="text/css"/> 
            <link href="/_modulos/_agenda/_files/css/main.css" rel="stylesheet" type="text/css"/> 
            
            <script src="/_modulos/_agenda/_files/src/Plugins/jquery.ifrmdailog.js" defer="defer" type="text/javascript"></script>
            <script src="/_modulos/_agenda/_files/src/Plugins/wdCalendar_lang_US.js" type="text/javascript"></script>    
            <script src="/_modulos/_agenda/_files/src/Plugins/jquery.calendar.js" type="text/javascript"></script>   
            <script type="text/javascript">
                $(document).ready(function() {     
                    var view="week";          
                    var DATA_FEED_URL = "/ChamadoActions.do";
                    var op = {
                        view: view,
                        theme:3,
                        showday: new Date(),
                        ViewCmdhandler:View,    
                        onWeekOrMonthToDay:wtd,
                        onBeforeRequestData: cal_beforerequest,
                        onAfterRequestData: cal_afterrequest,
                        onRequestDataError: cal_onerror, 
                        autoload:true,
                        url: DATA_FEED_URL + "?m=DATEGRID"  
                    };
                    var $dv = $("#calhead");
                    var _MH = document.documentElement.clientHeight;
                    var dvH = $dv.height() + 2;
                    op.height = _MH - dvH;
                    op.eventItems =[];

                    var p = $("#gridcontainer").bcalendar(op).BcalGetOp();
                    if (p && p.datestrshow) {
                        $("#txtdatetimeshow").text(p.datestrshow);
                    }
                    $("#caltoolbar").noSelect();
            
                    $("#hdtxtshow").datepicker({ picker: "#txtdatetimeshow", showtarget: $("#txtdatetimeshow"),
                        onReturn:function(r){                          
                            var p = $("#gridcontainer").gotoDate(r).BcalGetOp();
                            if (p && p.datestrshow) {
                                $("#txtdatetimeshow").text(p.datestrshow);
                            }
                        } 
                    });
                    function cal_beforerequest(type)
                    {
                        var t="Loading data...";
                        switch(type)
                        {
                            case 1:
                                t="Loading data...";
                                break;
                            case 2:                      
                            case 3:  
                            case 4:    
                                t="The request is being processed ...";                                   
                                break;
                        }
                        $("#errorpannel").hide();
                        $("#loadingpannel").html(t).show();    
                    }
                    function cal_afterrequest(type)
                    {
                        switch(type)
                        {
                            case 1:
                                $("#loadingpannel").hide();
                                break;
                            case 2:
                            case 3:
                            case 4:
                                $("#loadingpannel").html("Success!");
                                window.setTimeout(function(){ $("#loadingpannel").hide();},2000);
                                break;
                        }              
               
                    }
                    function cal_onerror(type,data)
                    {
                        $("#errorpannel").show();
                    }
                      
                    function View(data)
                    {
                        var str = "";
                        $.each(data, function(i, item){
                            str += "[" + i + "]: " + item + "\n";
                        });
                        alert(str);               
                    }    
                    
                    function wtd(p)
                    {
                        if (p && p.datestrshow) {
                            $("#txtdatetimeshow").text(p.datestrshow);
                        }
                        $("#caltoolbar div.fcurrent").each(function() {
                            $(this).removeClass("fcurrent");
                        })
                        $("#showdaybtn").addClass("fcurrent");
                    }
                    //to show day view
                    $("#showdaybtn").click(function(e) {
                        //document.location.href="#day";
                        $("#caltoolbar div.fcurrent").each(function() {
                            $(this).removeClass("fcurrent");
                        })
                        $(this).addClass("fcurrent");
                        var p = $("#gridcontainer").swtichView("day").BcalGetOp();
                        if (p && p.datestrshow) {
                            $("#txtdatetimeshow").text(p.datestrshow);
                        }
                    });
                    //to show week view
                    $("#showweekbtn").click(function(e) {
                        //document.location.href="#week";
                        $("#caltoolbar div.fcurrent").each(function() {
                            $(this).removeClass("fcurrent");
                        })
                        $(this).addClass("fcurrent");
                        var p = $("#gridcontainer").swtichView("week").BcalGetOp();
                        if (p && p.datestrshow) {
                            $("#txtdatetimeshow").text(p.datestrshow);
                        }

                    });
                    //to show month view
                    $("#showmonthbtn").click(function(e) {
                        //document.location.href="#month";
                        $("#caltoolbar div.fcurrent").each(function() {
                            $(this).removeClass("fcurrent");
                        })
                        $(this).addClass("fcurrent");
                        var p = $("#gridcontainer").swtichView("month").BcalGetOp();
                        if (p && p.datestrshow) {
                            $("#txtdatetimeshow").text(p.datestrshow);
                        }
                    });
            
                    $("#showreflashbtn").click(function(e){
                        $("#gridcontainer").reload();
                    });
                    //go to today
                    $("#showtodaybtn").click(function(e) {
                        var p = $("#gridcontainer").gotoDate().BcalGetOp();
                        if (p && p.datestrshow) {
                            $("#txtdatetimeshow").text(p.datestrshow);
                        }
                    });
                    //previous date range
                    $("#sfprevbtn").click(function(e) {
                        var p = $("#gridcontainer").previousRange().BcalGetOp();
                        if (p && p.datestrshow) {
                            $("#txtdatetimeshow").text(p.datestrshow);
                        }

                    });
                    //next date range
                    $("#sfnextbtn").click(function(e) {
                        var p = $("#gridcontainer").nextRange().BcalGetOp();
                        if (p && p.datestrshow) {
                            $("#txtdatetimeshow").text(p.datestrshow);
                        }
                    });
            
                });
            </script>    
    </head>
    <body>
        <div>

            <div id="calhead" style="padding-left:1px;padding-right:1px;">          
                <div class="cHead"><div class="ftitle">Agenda</div>
                    <div id="loadingpannel" class="ptogtitle loadicon" style="display: none;">Carregando dados...</div>
                    <div id="errorpannel" class="ptogtitle loaderror" style="display: none;">Desculpe, n�o conseguimos carregar seus dados, por favor, tente novamente mais tarde</div>
                </div>          

                <div id="caltoolbar" class="ctoolbar">
                    <div id="showdaybtn" class="fbutton">
                        <div><span title='Dia' class="showdayview">Dia</span></div>
                    </div>
                    <div  id="showweekbtn" class="fbutton fcurrent">
                        <div><span title='Semana' class="showweekview">Semana</span></div>
                    </div>
                    <div  id="showmonthbtn" class="fbutton">
                        <div><span title='Mes' class="showmonthview">Mes</span></div>

                    </div>
                    <div class="btnseparator"></div>
                    <div  id="showreflashbtn" class="fbutton">
                        <div><span title='Atualizar ' class="showdayflash">Atualizar</span></div>
                    </div>
                    <div class="btnseparator"></div>
                    <div id="sfprevbtn" title="Voltar"class="fbutton">
                        <span class="fprev"></span>

                    </div>
                    <div id="sfnextbtn" title="Avan�ar" class="fbutton">
                        <span class="fnext"></span>
                    </div>
                    <div class="fshowdatep fbutton">
                        <div>
                            <input type="hidden" name="txtshow" id="hdtxtshow" />
                            <span id="txtdatetimeshow">Carregando...</span>

                        </div>
                    </div>

                    <div class="clear"></div>
                </div>
            </div>
            <div style="padding:1px;">

                <div class="t1 chromeColor">
                    &nbsp;</div>
                <div class="t2 chromeColor">
                    &nbsp;</div>
                <div id="dvCalMain" class="calmain printborder">
                    <div id="gridcontainer" style="overflow-y: visible;">
                    </div>
                </div>
                <div class="t2 chromeColor">

                    &nbsp;</div>
                <div class="t1 chromeColor">
                    &nbsp;
                </div>   
            </div>

        </div>

    </body>
</html>
