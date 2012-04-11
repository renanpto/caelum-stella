package br.com.caelum.stella.boleto.bancos;

import java.net.URL;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Emissor;
import br.com.caelum.stella.boleto.bancos.GeradorDeDigitoDeBoleto;
import br.com.caelum.stella.boleto.exception.CriacaoBoletoException;

/**
 * 
 * @author Rafael
 *
 */
public class Santander implements Banco {
	
	private static final String NUMERO_SANTANDER = "033";
	
	private final GeradorDeDigitoDeBoleto dvGenerator = new GeradorDeDigitoDeBoleto();

	public String geraCodigoDeBarrasPara(Boleto boleto) {
		Emissor emissor = boleto.getEmissor();
		
		StringBuilder codigoDeBarras = new StringBuilder();
        codigoDeBarras.append(getNumeroFormatado()); // 01-03: Identificação do Banco = 033
        codigoDeBarras.append(String.valueOf(boleto.getCodigoEspecieMoeda())); // 04-04: Código da moeda = 9 (real)
        // 05-05: DV do código de barras

        codigoDeBarras.append(boleto.getFatorVencimento()); // 06-09: Fator de vencimento
        codigoDeBarras.append(boleto.getValorFormatado()); // 10-19: Valor nominal
        
        codigoDeBarras.append("9"); // 20-20: Fixo '9'
        codigoDeBarras.append(emissor.getNumeroConvenio()); // 21-27: número do PSK (Código do Cliente)
        codigoDeBarras.append(getNossoNumeroDoEmissorFormatado(emissor)); // 28-40: Nosso Número
        codigoDeBarras.append("0"); // 41-41: IOS – Seguradoras (Se 7% informar 7. Limitado a 9%). Demais clientes usar 0 (zero)
        codigoDeBarras.append(getCarteiraDoEmissorFormatado(emissor)); // 42-44: Tipo de Modalidade Carteira

        // Cálculo do DV:
        codigoDeBarras.insert(4, dvGenerator.geraDigitoMod11(codigoDeBarras.toString()));

        if (codigoDeBarras.length() != 44) {
            throw new CriacaoBoletoException("Erro na geração do código de barras. Número de digitos diferente de 44. Verifique todos os dados.");
        }

        return codigoDeBarras.toString();
	}

	public String getNumeroFormatado() {
		return NUMERO_SANTANDER;
	}

	public URL getImage() {
		return getClass().getResource("/img/santander.png");
	}

	public String getContaCorrenteDoEmissorFormatado(Emissor emissor) {
		return String.format("%08d", emissor.getContaCorrente());
	}

	public String getCarteiraDoEmissorFormatado(Emissor emissor) {
		return String.format("%02d", emissor.getCarteira());
	}

	public String getNossoNumeroDoEmissorFormatado(Emissor emissor) {
		String nossoNumero = String.format("%012d", emissor.getNossoNumero());
		Integer digitoVerificador = getDigitoNossoNumero(nossoNumero);
		return nossoNumero + digitoVerificador.toString();
	}
	
	private Integer getDigitoNossoNumero(String numero) {
        Integer soma = 0;
        for (int i = numero.length() - 1, multiplicador = 2; i >= 0; i--, multiplicador++) {
            if (multiplicador == 10) {
                multiplicador = 2;
            }
            soma += Integer.parseInt(String.valueOf(numero.charAt(i))) * multiplicador;
        }
        
        Integer resto = soma % 11;
        if(resto.intValue() == 10) {
        	return 1;
        }
        if(resto.intValue() == 1 || resto.intValue() == 0) {
        	return 0;
        }
        
        Integer digito = 11 - resto;
        return digito < 0 ? digito * -1 : digito;
    }
}
