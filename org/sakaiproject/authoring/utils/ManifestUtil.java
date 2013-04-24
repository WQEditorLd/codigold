package org.sakaiproject.authoring.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.imsglobal.jaxb.content.Manifest;
import org.imsglobal.jaxb.content.Organizations;
import org.imsglobal.jaxb.ld.LearningDesign;
import org.imsglobal.jaxb.ld.ObjectFactory;

/*
 * ManifestUtil 
 * 
 * Classe acess�ria para acionamento do JAXB para leitura e grava��o do arquivo XML no formato IMS CP & IMS LD
 * 
 * Revis�o 1.0WQ - 15/04/2010 - Remo��o dos m�todos utilit�rios referentes ao Grafo (Graph), @see GraphUtil 
 */
public class ManifestUtil {

	private static org.imsglobal.jaxb.content.ObjectFactory imscpFactory = new org.imsglobal.jaxb.content.ObjectFactory();
	private static ObjectFactory imsldFactory = new ObjectFactory();

	@SuppressWarnings("unchecked")
	public static Manifest openManifestInXML(File file) throws JAXBException {
		JAXBContext jaxbContext;
		jaxbContext = JAXBContext
				.newInstance("org.imsglobal.jaxb.content:org.imsglobal.jaxb.ld");

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		JAXBElement<Manifest> object = (JAXBElement<Manifest>) unmarshaller
				.unmarshal(file);

		return object.getValue();
	}

	public static void saveManifestInXML(File file, Manifest manifest,
			LearningDesign ld) throws Exception {
		JAXBContext jaxbContext;
		jaxbContext = JAXBContext
				.newInstance("org.imsglobal.jaxb.content:org.imsglobal.jaxb.ld");

		if (manifest.getOrganizations().getAny().get(0) != null) {
			manifest.getOrganizations().getAny().remove(0);
		}
		
		manifest.getOrganizations().getAny().add(
				imsldFactory.createLearningDesign((LearningDesign) ld));

		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output", true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.marshal(imscpFactory.createManifest(manifest), file);
	}

	@SuppressWarnings("unchecked")
	public static LearningDesign getLearningDesign(Manifest manifest) {
		if (manifest.getOrganizations() != null) {
			return ((JAXBElement<LearningDesign>) manifest.getOrganizations()
					.getAny().get(0)).getValue();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static void setLearningDesignInManifest(Manifest manifest,
			LearningDesign learningDesign) {

		if (manifest.getOrganizations() == null) {
			manifest.setOrganizations(new Organizations());
		}

		if (manifest.getOrganizations().getAny().size() == 0) {
			manifest.getOrganizations().getAny().add(
					new JAXBElement<LearningDesign>(new QName(
							"http://www.imsglobal.org/xsd/imsld_v1p0",
							"learning-design", "imsld"), LearningDesign.class,
							null));
		}

		((JAXBElement<LearningDesign>) manifest.getOrganizations().getAny()
				.get(0)).setValue(learningDesign);

	}

}
