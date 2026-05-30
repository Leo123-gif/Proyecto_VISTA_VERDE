 package com.mycompany.condominio_vistaverde;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class BDXML {

    private static final String RUTA = "residencial.xml";

    // =====================================================
    // DOCUMENTO XML
    // =====================================================

    public static Document obtenerDocumento() {

        try {

            File archivo = new File(RUTA);

            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder builder =
                    factory.newDocumentBuilder();

            // =========================
            // SI EL XML NO EXISTE
            // =========================

            if (!archivo.exists()) {

                Document doc =
                        builder.newDocument();

                Element raiz =
                        doc.createElement("residencial");

                doc.appendChild(raiz);

                // =========================
                // CASAS
                // =========================

                Element casasRaiz =
                        doc.createElement("casas");

                for (int i = 1; i <= 30; i++) {

                    Element casa =
                            doc.createElement("casa");

                    casa.setAttribute(
                            "numero",
                            String.valueOf(i)
                    );

                    casasRaiz.appendChild(casa);
                }

                raiz.appendChild(casasRaiz);

                // =========================
                // PAGOS
                // =========================

                Element pagos =
                        doc.createElement("pagos");

                raiz.appendChild(pagos);

                // =========================
                // CONFIGURACION
                // =========================

                Element configuracion =
                        doc.createElement("configuracion");

                Element cuotaActual =
                        doc.createElement("cuotaActual");

                cuotaActual.setTextContent("1500.00");

                configuracion.appendChild(cuotaActual);

                raiz.appendChild(configuracion);

                guardarDocumento(doc);

                return doc;
            }

            Document doc =
                    builder.parse(archivo);

            doc.getDocumentElement().normalize();

            return doc;

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al obtener XML:\n"
                    + e.getMessage()
            );

            return null;
        }
    }

    public static void guardarDocumento(
            Document doc
    ) {

        try {

            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();

            Transformer transformer =
                    transformerFactory.newTransformer();

            transformer.setOutputProperty(
                    OutputKeys.INDENT,
                    "yes"
            );

            transformer.setOutputProperty(
                    OutputKeys.ENCODING,
                    "UTF-8"
            );

            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount",
                    "4"
            );

            DOMSource source =
                    new DOMSource(doc);

            StreamResult result =
                    new StreamResult(
                            new File(RUTA)
                    );

            transformer.transform(source, result);

        } catch (Exception e) {

            System.out.println(
                    "Error guardando XML:\n"
                    + e.getMessage()
            );
        }
    }

    // =====================================================
    // PROPIETARIOS
    // =====================================================

    public static boolean registrarPropietario(
            Propietario p
    ) {

        try {

            Document doc =
                    obtenerDocumento();

            NodeList listaCasas =
                    doc.getElementsByTagName("casa");

            for (int i = 0; i < listaCasas.getLength(); i++) {

                Element casaElem =
                        (Element) listaCasas.item(i);

                String numeroXML =
                        casaElem.getAttribute("numero");

                if (numeroXML.equals(
                        String.valueOf(
                                p.getNumeroCasa()
                        )
                )) {

                    // =========================
                    // VALIDAR SI YA EXISTE
                    // =========================

                    NodeList propietarios =
                            casaElem.getElementsByTagName(
                                    "propietario"
                            );

                    if (propietarios.getLength() > 0) {

                        return false;
                    }

                    // =========================
                    // CREAR PROPIETARIO
                    // =========================

                    Element propietario =
                            doc.createElement("propietario");

                    Element nombre =
                            doc.createElement("nombre");

                    nombre.setTextContent(
                            p.getNombre()
                    );

                    Element telefono =
                            doc.createElement("telefono");

                    telefono.setTextContent(
                            p.getTelefono()
                    );

                    Element correo =
                            doc.createElement("correo");

                    correo.setTextContent(
                            p.getCorreo()
                    );

                    propietario.appendChild(nombre);
                    propietario.appendChild(telefono);
                    propietario.appendChild(correo);

                    casaElem.appendChild(propietario);

                    guardarDocumento(doc);

                    return true;
                }
            }

        } catch (DOMException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error registrando propietario:\n"
                    + e.getMessage()
            );
        }

        return false;
    }

    public static Propietario obtenerPropietario(
            int numeroCasa
    ) {

        try {

            Document doc =
                    obtenerDocumento();

            NodeList listaCasas =
                    doc.getElementsByTagName("casa");

            for (int i = 0; i < listaCasas.getLength(); i++) {

                Element casaElem =
                        (Element) listaCasas.item(i);

                String numeroXML =
                        casaElem.getAttribute("numero");

                if (numeroXML.equals(
                        String.valueOf(numeroCasa)
                )) {

                    NodeList propietarios =
                            casaElem.getElementsByTagName(
                                    "propietario"
                            );

                    if (propietarios.getLength() > 0) {

                        Element prop =
                                (Element) propietarios.item(0);

                        String nombre =
                                prop.getElementsByTagName("nombre")
                                        .item(0)
                                        .getTextContent();

                        String telefono =
                                prop.getElementsByTagName("telefono")
                                        .item(0)
                                        .getTextContent();

                        String correo =
                                prop.getElementsByTagName("correo")
                                        .item(0)
                                        .getTextContent();

                        return new Propietario(
                                nombre,
                                telefono,
                                correo,
                                numeroCasa
                        );
                    }
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public static String[] obtenerDatosPropietario(
            int numeroCasa
    ) {

        Propietario p =
                obtenerPropietario(numeroCasa);

        if (p != null) {

            return new String[]{

                p.getNombre(),
                p.getTelefono(),
                p.getCorreo()
            };
        }

        return null;
    }

    
    // =====================================================
    // CASAS
    // =====================================================

    public static Casa obtenerCasa(
            int numeroCasa
    ) {

        Casa casa =
                new Casa(numeroCasa);

        // =========================
        // PROPIETARIO
        // =========================

        Propietario propietario =
                obtenerPropietario(numeroCasa);

        if (propietario != null) {

            casa.setPropietario(propietario);
        }

        // =========================
        // PAGOS
        // =========================

        List<Pago> pagos =
                obtenerPagosCasa(numeroCasa);

        for (Pago p : pagos) {

            casa.agregarPago(p);
        }

        return casa;
    }

    public static List<Casa> obtenerCasas() {

        List<Casa> lista =
                new ArrayList<>();

        for (int i = 1; i <= 30; i++) {

            lista.add(
                    obtenerCasa(i)
            );
        }

        return lista;
    }

    // =====================================================
    // PAGOS
    // =====================================================

    public static boolean registrarPagoPOO(
            int numCasa,
            Pago p
    ) {

        try {

            // =========================
            // VALIDAR DUPLICADO
            // =========================

            if (existePago(
                    numCasa,
                    p.getMes(),
                    p.getAño()
            )) {

                return false;
            }

            Document doc =
                    obtenerDocumento();

            Element pagosRaiz =
                    (Element)
                    doc.getElementsByTagName("pagos")
                            .item(0);

            // =========================
            // CREAR PAGO
            // =========================

            Element pago =
                    doc.createElement("pago");

            Element casa =
                    doc.createElement("casa");

            casa.setTextContent(
                    String.valueOf(numCasa)
            );

            Element mes =
                    doc.createElement("mes");

            mes.setTextContent(
                    p.getMes()
            );

            Element año =
                    doc.createElement("año");

            año.setTextContent(
                    String.valueOf(
                            p.getAño()
                    )
            );

            Element cuota =
                    doc.createElement("cuota");

            cuota.setTextContent(
                    String.valueOf(
                            p.getMonto()
                    )
            );

            Element estado =
                    doc.createElement("estado");

            estado.setTextContent(
                    p.getEstado()
            );

            pago.appendChild(casa);
            pago.appendChild(mes);
            pago.appendChild(año);
            pago.appendChild(cuota);
            pago.appendChild(estado);

            pagosRaiz.appendChild(pago);

            guardarDocumento(doc);

            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error registrando pago:\n"
                    + e.getMessage()
            );

            return false;
        }
    }

    public static boolean existePago(
            int numCasa,
            String mes,
            int año
    ) {

        try {

            Document doc =
                    obtenerDocumento();

            NodeList listaPagos =
                    doc.getElementsByTagName("pago");

            for (int i = 0; i < listaPagos.getLength(); i++) {

                Element pago =
                        (Element) listaPagos.item(i);

                int casaXML =
                        Integer.parseInt(
                                pago.getElementsByTagName("casa")
                                        .item(0)
                                        .getTextContent()
                        );

                String mesXML =
                        pago.getElementsByTagName("mes")
                                .item(0)
                                .getTextContent();

                int añoXML =
                        Integer.parseInt(
                                pago.getElementsByTagName("año")
                                        .item(0)
                                        .getTextContent()
                        );

                if (casaXML == numCasa
                        && mesXML.equals(mes)
                        && añoXML == año) {

                    return true;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public static List<Pago> obtenerPagosCasa(
            int numeroCasa
    ) {

        List<Pago> lista =
                new ArrayList<>();

        try {

            Document doc =
                    obtenerDocumento();

            NodeList pagos =
                    doc.getElementsByTagName("pago");

            for (int i = 0; i < pagos.getLength(); i++) {

                Element pagoElem =
                        (Element) pagos.item(i);

                int casa =
                        Integer.parseInt(
                                pagoElem.getElementsByTagName("casa")
                                        .item(0)
                                        .getTextContent()
                        );

                if (casa == numeroCasa) {

                    String mes =
                            pagoElem.getElementsByTagName("mes")
                                    .item(0)
                                    .getTextContent();

                    int año =
                            Integer.parseInt(
                                    pagoElem.getElementsByTagName("año")
                                            .item(0)
                                            .getTextContent()
                            );

                    double monto =
                            Double.parseDouble(
                                    pagoElem.getElementsByTagName("cuota")
                                            .item(0)
                                            .getTextContent()
                            );

                    String estado =
                            pagoElem.getElementsByTagName("estado")
                                    .item(0)
                                    .getTextContent();

                    Pago pago =
                            new Pago(
                                    mes,
                                    año,
                                    monto,
                                    estado
                            );

                    lista.add(pago);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }

    // =====================================================
    // CONFIGURACION
    // =====================================================

    public static double obtenerCuotaActual() {

        try {

            Document doc =
                    obtenerDocumento();

            NodeList lista =
                    doc.getElementsByTagName(
                            "cuotaActual"
                    );

            if (lista.getLength() > 0) {

                return Double.parseDouble(
                        lista.item(0)
                                .getTextContent()
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 1500.00;
    }

    public static void actualizarCuota(
            double nuevaCuota
    ) {

        try {

            Document doc =
                    obtenerDocumento();

            NodeList lista =
                    doc.getElementsByTagName(
                            "cuotaActual"
                    );

            if (lista.getLength() > 0) {

                lista.item(0).setTextContent(
                        String.valueOf(
                                nuevaCuota
                        )
                );

                guardarDocumento(doc);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
    public static boolean casaTienePropietario(int numeroCasa) {

    try {

        Document doc = obtenerDocumento();

        NodeList listaCasas =
                doc.getElementsByTagName("casa");

        for (int i = 0; i < listaCasas.getLength(); i++) {

            Element casaElem =
                    (Element) listaCasas.item(i);

            String numeroXML =
                    casaElem.getAttribute("numero");

            if (numeroXML.equals(
                    String.valueOf(numeroCasa))) {

                NodeList propietarios =
                        casaElem.getElementsByTagName(
                                "propietario"
                        );

                return propietarios.getLength() > 0;
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}
    
    
}