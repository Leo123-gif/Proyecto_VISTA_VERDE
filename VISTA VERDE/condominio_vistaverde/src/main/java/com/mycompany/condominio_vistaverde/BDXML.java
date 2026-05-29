package com.mycompany.condominio_vistaverde;

import java.io.File;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class BDXML {

    private static final String RUTA = "residencial.xml";

    public static Document obtenerDocumento() {
        try {
            File archivo = new File(RUTA);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            if (!archivo.exists()) {
                Document doc = builder.newDocument();
                Element raiz = doc.createElement("residencial");
                doc.appendChild(raiz);

                // Crear sección de casas e inicializar las 30 casas
                Element casasRaiz = doc.createElement("casas");
                for (int i = 1; i <= 30; i++) {
                    Element casa = doc.createElement("casa");
                    casa.setAttribute("numero", String.valueOf(i));
                    casasRaiz.appendChild(casa);
                }
                raiz.appendChild(casasRaiz);

                raiz.appendChild(doc.createElement("pagos"));

                Element configuracion = doc.createElement("configuracion");
                Element cuotaActual = doc.createElement("cuotaActual");
                cuotaActual.setTextContent("1500.00");
                configuracion.appendChild(cuotaActual);
                raiz.appendChild(configuracion);

                guardarDocumento(doc);
                return doc;
            }
            return builder.parse(archivo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener XML: " + e.getMessage());
            return null;
        }
    }

    public static void guardarDocumento(Document doc) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(RUTA));
            transformer.transform(source, result);
        } catch (Exception e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    // --- MÉTODOS PARA PROPIETARIOS ---

    public static void registrarPropietario(Propietario p) {
        try {
            Document doc = obtenerDocumento();
            NodeList listaCasas = doc.getElementsByTagName("casa");

            for (int i = 0; i < listaCasas.getLength(); i++) {
                Element casaElem = (Element) listaCasas.item(i);
                
                // Buscamos la casa por su atributo "numero"
                if (casaElem.getAttribute("numero").equals(String.valueOf(p.getNumeroCasa()))) {
                    
                    // Limpiar propietario anterior si existe
                    NodeList viejos = casaElem.getElementsByTagName("propietario");
                    while (viejos.getLength() > 0) {
                        casaElem.removeChild(viejos.item(0));
                    }

                    Element nuevoProp = doc.createElement("propietario");
                    
                    Element nom = doc.createElement("nombre");
                    nom.setTextContent(p.getNombre());
                    
                    Element tel = doc.createElement("telefono");
                    tel.setTextContent(p.getTelefono());
                    
                    Element cor = doc.createElement("correo");
                    cor.setTextContent(p.getCorreo());

                    nuevoProp.appendChild(nom);
                    nuevoProp.appendChild(tel);
                    nuevoProp.appendChild(cor);
                    casaElem.appendChild(nuevoProp);
                    break;
                }
            }
            guardarDocumento(doc);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar propietario: " + e.getMessage());
        }
    }

    public static String[] obtenerDatosPropietario(String casaBuscada) {
        try {
            Document doc = obtenerDocumento();
            if (doc == null) return null;

            // Extraer solo el número si viene como "CASA 1"
            String soloNumero = casaBuscada.replace("CASA", "").trim();
            NodeList listaCasas = doc.getElementsByTagName("casa");

            for (int i = 0; i < listaCasas.getLength(); i++) {
                Element casaElem = (Element) listaCasas.item(i);
                if (casaElem.getAttribute("numero").equals(soloNumero)) {
                    NodeList listaProp = casaElem.getElementsByTagName("propietario");
                    if (listaProp.getLength() > 0) {
                        Element prop = (Element) listaProp.item(0);
                        return new String[]{
                            prop.getElementsByTagName("nombre").item(0).getTextContent(),
                            prop.getElementsByTagName("telefono").item(0).getTextContent(),
                            prop.getElementsByTagName("correo").item(0).getTextContent()
                        };
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // --- MÉTODOS PARA PAGOS ---

    public static boolean existePago(String casa, String mes, String año) {
        try {
            Document doc = obtenerDocumento();
            NodeList listaPagos = doc.getElementsByTagName("pago");
            for (int i = 0; i < listaPagos.getLength(); i++) {
                Element pago = (Element) listaPagos.item(i);
                if (pago.getElementsByTagName("casa").item(0).getTextContent().equals(casa) &&
                    pago.getElementsByTagName("mes").item(0).getTextContent().equals(mes) &&
                    pago.getElementsByTagName("año").item(0).getTextContent().equals(año)) {
                    return true;
                }
            }
        } catch (Exception e) { }
        return false;
    }

    public static void registrarPago(String casa, String mes, String año, String cuota) {
        try {
            Document doc = obtenerDocumento();
            Element pagosRaiz = (Element) doc.getElementsByTagName("pagos").item(0);

            Element nuevoPago = doc.createElement("pago");
            
            Element elemCasa = doc.createElement("casa");
            elemCasa.setTextContent(casa);
            
            Element elemMes = doc.createElement("mes");
            elemMes.setTextContent(mes);
            
            Element elemAño = doc.createElement("año");
            elemAño.setTextContent(año);
            
            Element elemCuota = doc.createElement("cuota");
            elemCuota.setTextContent(cuota);

            nuevoPago.appendChild(elemCasa);
            nuevoPago.appendChild(elemMes);
            nuevoPago.appendChild(elemAño);
            nuevoPago.appendChild(elemCuota);
            
            pagosRaiz.appendChild(nuevoPago);
            guardarDocumento(doc);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar pago: " + e.getMessage());
        }
    }

    // --- MÉTODOS DE SOPORTE POO (Encapsulamiento) ---

    public static void registrarPagoPOO(int numCasa, Pago p) {
        registrarPago("CASA " + numCasa, p.getMes(), String.valueOf(p.getAño()), String.valueOf(p.getMonto()));
    }

    // --- CONFIGURACIÓN ---

    public static String obtenerCuotaActual() {
        try {
            Document doc = obtenerDocumento();
            NodeList lista = doc.getElementsByTagName("cuotaActual");
            if (lista.getLength() > 0) return lista.item(0).getTextContent();
        } catch (Exception e) { }
        return "1500.00";
    }

    public static void actualizarCuota(String nuevaCuota) {
        try {
            Document doc = obtenerDocumento();
            NodeList lista = doc.getElementsByTagName("cuotaActual");
            if (lista.getLength() > 0) {
                lista.item(0).setTextContent(nuevaCuota);
                guardarDocumento(doc);
            }
        } catch (Exception e) { }
    }
}