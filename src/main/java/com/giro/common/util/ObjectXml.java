/**
 * 
 */
package com.giro.common.util;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;



/**
 * @author caochun
 * 
 */
public class ObjectXml {
	private final static String DEF_CHARSET="UTF-8";
	/**
	 * 将DTO对象生成xml格式字符串，dto对象需要使用@XmlRootElement进行注释
	 * 
	 * @param <DTO>
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public static <DTO> String convertFromDTO(DTO dto) {

		try {
			JAXBContext jc = JAXBContext.newInstance(dto.getClass());
			Marshaller m = jc.createMarshaller();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			m.marshal(dto, os);
			m.toString();
			return new String(os.toByteArray(),DEF_CHARSET);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromXml(String xml, Class<T> clazz) throws JAXBException {

		StringReader reader = new StringReader(xml);
		JAXBContext jc = JAXBContext.newInstance(clazz);
		return (T) jc.createUnmarshaller().unmarshal(reader);
	}
}
