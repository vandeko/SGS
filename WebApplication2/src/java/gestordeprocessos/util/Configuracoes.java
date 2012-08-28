package gestordeprocessos.util;

/**
 * Enumeração das Configurações do sistema.
 */
public enum Configuracoes {

    W_ADM((String) "1000"),
    LOGO_ADM((String) "logo.png"),
    TITULO_SITE((String) "Sistema de Gerenciamento de Suporte"),
    DIV_PROCESSA((String) "<div id='processa' style='border: 0px solid #333; width: 1px; height: 1px; position: absolute; top: 0; left: 0; overflow:  hidden;'></div>"),
    OFFSET((String) "258"),
    FLEXIGRID_RELOAD((String) "<script> top.closeWindow('blackscreenWinconfirm'); top.closeWindow('blackscreenWinload'); top.$('#flexiGrid').flexReload(); </script>"),
    CAMPO_OBRIGATORIO((String) "<span style='color:red'> * </span>");
    private String config;

    private Configuracoes(String config) {
        this.config = config;
    }

    public String getConfig() {
        return config;
    }
}
