package br.com.caelum.stella.nfe.fluid.enums;

public enum ICMSSituacaoTributaria {
    ENTRADA_COM_RECUPERACAO_DE_CREDITO("00"), ENTRADA_TRIBUTADA_COM_ALIQUOTA_ZERO("01"), ENTRADA_ISENTA("02"), ENTRADA_NAO_TRIBUTADA(
            "03"), ENTRADA_IMUNE("04"), ENTRADA_COM_SUSPENSAO("05"), OUTRAS_ENTRADAS("49"), SAIDAS_TRIBUTADA("50"), SAIDA_TRIBUTADA_COM_ALIQU0OTA_ZERO(
            "51"), SAIDA_ISENTA("52"), SAIDA_NAO_TRIBUTADA("53"), SAIDA_IMUNE("54"), SAIDA_COM_SUSPENSAO("55"), OUTRAS_SAIDAS(
            "99");

    private String codigo;

    private ICMSSituacaoTributaria(final String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
