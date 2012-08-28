
<div id="navegacao" style="height: 355px">
    <div id="formulario">
        <form id="idForm" action="/SetorActions.do" method="POST" target="#processa">
            <input type="hidden" name="mode" value="SAVE"/>
            <input type="hidden" id="idId" name="id" value=""/>
            <ul class="markup">
                <li style="margin-top: 0;">
                    <!--TIPO CLIENTE-->
                    <div class="left rightFix" style="width: 560px;">
                        <label for="tipo" style='display: block'>Tipo Cliente</label>
                        <input type="radio" value="1" id="tipo" name="tipo" style="margin-top: 5px;" checked="checked"/> Pessoa Física
                        <input type="radio" value="2" id="tipo" name="tipo" style="margin-left: 25px;"  /> Pessoa Jurídica;
                    </div>
                    <div class="clear"></div>
                </li>

                <li class="pFisica">
                    <!--pFisica-->
                    <div class="left" style="width: 419px;">
                        <label for="idNome">Nome </label>
                        <input type="text" id="idNome" name="contato" class="inputAdm required" data-required="É Necessário escolher um Nome" value=""/>
                    </div>

                    <div class="left rightFix" style="width: 120px;">
                        <label for="idCpf">CPF </label>
                        <input type="text" id="idCpf" name="cnpjCpf" class="inputAdm  maskCpf required" data-required="É Necessário informar o seu CPF" value=""/>
                    </div>
                    <div class="clear"></div>
                </li>

                <li class="pJuridica">
                    <!--pFisica-->
                    <div class="left" style="width: 150px;">
                        <label for="idCnpj">CNPJ </label>
                        <input type="text" id="idCnpj" name="cnpjCpf" class="inputAdm maskCnpj required" data-required="É Necessário informar o CNPJ da empresa" value=""/>
                    </div>

                    <div class="left rightFix" style="width: 375px;">
                        <label for="idRazao">Razão Social</label>
                        <input type="text" id="idRazao" name="razao" class="inputAdm required" data-required="É Necessário informar a Razão Social da empresa" value=""/>
                    </div>
                    <div class="clear"></div>
                </li>

                <li class="pJuridica">
                    <!--pFisica-->
                    <div class="left" style="width: 150px;">
                        <label for="idInscricao">Inscrição Estadual</label>
                        <input type="text" id="idInscricao" name="ieRg" class="inputAdm requires" data-required="É Necessário informar a Inscrição Estadual da empresa" value=""/>
                    </div>

                    <div class="left" style="width: 200px;">
                        <label for="idFantasia">Nome Fantasia</label>
                        <input type="text" id="idFantasia" name="nomeFantasia" class="inputAdm required" data-required="É Necessário Informar o nome fantasia da empresa" value=""/>
                    </div>

                    <div class="left rightFix" style="width: 155px;">
                        <label>Nome Responsável</label>
                        <input type="text" id="idNome" name="contato" class="inputAdm" value=""/>
                    </div>
                    <div class="clear"></div>
                </li>

                <li style="margin-top: 19px;">
                    <hr style="height: 1px; background-color: #DDD; border: 0"/>
                </li>

                <li>
                    <!--EMAIL-->
                    <div class="left" style="width: 259px;">
                        <label for="idEmail">Email </label>
                        <input type="text" id="idEmail" name="email" class="inputAdm required" data-required="É Necessário informar um E-Mail válido" value="" onblur="unicoLogin($(this), 'idEmail', ''" />
                    </div>

                    <!--SENHA-->
                    <div class="left" style="width: 130px;">
                        <% if (id != "") {%>
                        <label for="idSenha">Alterar Senha</label>
                        <input type="password" id="idSenha" name="nsenha" class="inputAdm" value=""/>
                        <%} else {%>
                        <label for="idSenha">Senha </label>
                        <input type="password" id="idSenha" name="senha" class="inputAdm required" data-required="É Necessário escolher uma senha" value=""/><%                         }%>
                    </div>

                    <!--CONFIRMAR SENHA-->
                    <div class="left rightFix" style="width: 130px;">
                        <label for="idCSenha">Conf. Senha</label>
                        <input type="password" id="idCSenha" name="senha" class="inputAdm" value=""/>
                    </div>
                    <div class="clear"></div>
                </li>

                <li>
                    <!--CEP-->
                    <div class="left" style="width: 90px;">
                        <label for="idCep">Cep <></label>
                        <input type="text" id="idEndereco" name="cep" class="inputAdm maskCep required" data-required="É Necessário informar o CEP" value="<?php echo $object->getCep(); ?>" onblur="limpaCampos(); executaActions('processa', '_commons/load_endereco.php', 'cep='+this.value);"/>
                    </div>

                    <!--LOGRADOURO-->
                    <div class="left" style="width: 235px;">
                        <label for="idLogradouro">Logradouro</label>
                        <input type="text" id="idLogradouro" name="endereco" class="inputAdm required" data-required="É Necessário informar o Logradouro" value="" />
                    </div>

                    <!--BAIRRO-->
                    <div class="left rightFix" style="width: 194px;">
                        <label for="idBairro">Bairro </label>
                        <input type="text" id="idBairro" name="bairro" class="inputAdm required" data-required="É Necessário informar o Bairro" value="" />
                    </div>
                    <div class="clear"></div>
                </li>

                <li>
                    <!--UF-->
                    <div class="left" style="width: 70px;">
                        <label for="idEstado">Estado </label>
                        <select id="idEstado" name="uf" class="inputAdm required" data-required="É Necessário informar o Estado" onchange="listaCidades('idCidade', this.value);">
                            <option value="">----</option>

                            <option  value="">

                            </option>

                        </select>
                    </div>

                    <!--CIDADE-->
                    <div class="left" style="width: 249px;">
                        <label for="idCidade">Cidade </label>
                        <select id="idCidade" name="cidade" data-required="É Necessário informar a Cidade" class="inputAdm  required"></select>
                    </div>

                    <!--COMPLEMENTO-->
                    <div class="left" style="width: 90px;">
                        <label for="idComplemento">Complemento</label>
                        <input type="text" id="idComplemento" name="complemento" class="inputAdm" value="" />
                    </div>

                    <!--NUMERO-->
                    <div class="left rightFix" style="width: 90px;">
                        <label for="idNumero">Número </label>
                        <input type="text" id="idNumero" name="numero" class="inputAdm required" data-required="É Necessário informar o Número" value="" />
                    </div>
                    <div class="clear"></div>
                </li>

                <li>
                    <!--TELEFONE-->
                    <div class="left rightFix" style="width: 140px;">
                        <label for="idTelefone">Telefone</label>
                        <input type="text" id="idTelefone" name="fone1" class="inputAdm maskTelefone" value="" />
                    </div>
                    <div class="clear"></div>
                </li>

                <li>
                    <div class="right rightFix" style="width: 126px;">
                        <div class="tituloTab" style="text-align: right; width: 100%; height: 20px; font-size: 11px"><font color='#f00'>* Campos obrigatórios.</font></div>
                        <div class="btnPadraoRight" onclick="salvar()">Salvar</div>
                    </div>
                    <div class="clear"></div>
                </li>
            </ul>
        </form>
    </div>
    <div class="clear"></div>
</div>

<script>
    var ativo = 'empresa';

    salvar = function() {
        error = 0;
        if($('#idSenha').val() !=$('#idCSenha').val()){
            top.alerta('Os campos "senha" e "confirmação de senha" devem ser iguais<BR><BR>', 'Senha não confere', '', '2', focus);
            return false;
        }
        $('#idForm').find('.required').each(function(){
            $this = $(this);
            if($this.is(':visible') && $this.val() == ''){
                error++
                msg = $(this).data('required');
                return false;
            }
        })
        if(error > 0){
            top.alerta(msg+'<BR><BR>', 'Campo Necessário', '', '2', '');
        } else {
            $('#idForm').submit();
        }
    };

    clienteType = function(tipo){
        switch(tipo){
            case '2':
                $('.pJuridica').show().find('input, select').each(function(){
                    $(this).removeAttr('disabled');
                })
                $('.pFisica').hide().find('input, select').each(function(){
                    $(this).attr('disabled', 'disabled');
                })
                break;

            default:
                $('.pFisica').show().find('input, select').each(function(){
                    $(this).removeAttr('disabled');
                })
                $('.pJuridica').hide().find('input, select').each(function(){
                    $(this).attr('disabled', 'disabled');
                })
        }
    }

    $('.markup').on('change', 'input[name=tipo]', function(){
        clienteType($(this).val());
    });


    unicoLogin = function(obj, focus, id){
        if(obj.val() == '') {
            return false;
        }
        $.post("/ClienteActions.do", {mode: 'UNICOLOGIN', val: obj.val(), id: id}, function(data){
            if(data != 1){
                if(!typeof focus == 'undefined'){
                    top.alerta('Este E-mail já foi cadastrado em nosso banco de dados!<BR><BR>','Email Duplicado','','2',focus);
                } else {
                    top.alerta('Este E-mail já foi cadastrado em nosso banco de dados!<BR><BR>','Email Duplicado','','2',focus);
                }
                obj.val('');
            }
        })
    }

    $(document).ready(function() {
        clienteType("<%=id%>");
        limpaCampos = function() {
            $('#idLogradouro').val('');
            $('#idBairro').val('');
            $('#idEstado').val('0');
            $('#idCidade').removeOption(/./);
            $('#idCidade option:selected').html('Carregando...');
        }
      
    <% if (request.getParameter("id") != null) {%>
            listaCidades('idCidade','<%out.print(clientes.getCidade().getId_uf().getId_uf());%>','<%out.print(clientes.getCidade().getId_cidade());%>');
    <% }%>
      
        });
</script>