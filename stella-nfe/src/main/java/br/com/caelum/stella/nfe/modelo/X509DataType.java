//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.10.22 at 06:02:18 PM BRST 
//

package br.com.caelum.stella.nfe.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X509DataType", namespace = "http://www.w3.org/2000/09/xmldsig#", propOrder = { "x509Certificate" })
public class X509DataType {

    @XmlElement(name = "X509Certificate", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected byte[] x509Certificate;

    public byte[] getX509Certificate() {
        return x509Certificate;
    }

    public void setX509Certificate(final byte[] value) {
        x509Certificate = value;
    }

}
