# XML

## 1. INTRODUCTION

This assignment is focused on XML. In particular the first part of the assignment consists in writing an XML
schema for the given XML file named **ACMTrento.xml**.

On the other hand, the second part of the assignment consists in writing an XSL Transformation (XSLT) that
generates a CSV file (comma-separated values) from the given XML file **ACMTrento.xml**. The CSV file should
contains the following fields:

* Unit Name
* Type
* Time

Moreover, a Java program was implemented in order to run the transformation.

## 2. IMPLEMENTATION
The XSD file **ACMTrento.xsd** contains the XML Schema that describes the structure of the XML file
**ACMTrento.xml**. The main element of the file is TAXONOMY that is of type TAXONOMY_type. This means
that TAXONOMY have an attribute named ID and is composed by a sequence of AREA elements. Also AREA
elements are composed by a sequence of other element and so on. Moreover, some attributes have
specific restrictions (i.e. AREA ID, UNIT ID and UNIT TYPE).

Part of the schema can be seen in the following picture:

```xml
<?xml version="1.0" encoding="ISO-8859-1"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
  <xs:element name="TAXONOMY" type="TAXONOMY_type"/>
  <xs:complexType name="TAXONOMY_type">
    <xs:sequence>
      <xs:element name="AREA" type="AREA_type" minOccurs="0" maxOccurs="unbounded"/>
    </xs:sequence>
    <xs:attribute name="ID" type="xs:string" use="required"/>
  </xs:complexType>
  <xs:complexType name="AREA_type">
    <xs:sequence>
      <xs:element name="AREA_NAME" type="xs:string"/>
      <xs:element name="SHORT_NAME" type="SHORT_NAME_type"/>
      <xs:element name="DESCRIPTION" type="xs:string"/>
      <xs:element name="UNITS" type="UNITS_type"/>
    </xs:sequence>
    <xs:attribute name="ID" use="required">
	<xs:simpleType>
        <xs:restriction base="xs:string">
            <xs:pattern value="[A-Z]{2}"/>
        </xs:restriction>
    </xs:simpleType>
	</xs:attribute>
  </xs:complexType>
  ```

The following XSLT file was used to generate a CSV file from **ACMTrento.xml**, containing the fields Unit
Name, Type and Time:

```xml
<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output omit-xml-declaration="yes"/>
	<xsl:template match="/">Unit Name;Type;Time;
		<xsl:for-each select="TAXONOMY/AREA/UNITS/UNIT">
			<xsl:value-of select="UNIT_NAME"/>;<xsl:value-of select="@TYPE"/>;<xsl:value-of select="TIME"/>;
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>
```

Each value is separated by a semicolon.

## 3. DEPLOYMENT

The following simple Java program XMLTransformator was used to run the transformation:
```java
try {
  TransformerFactory factory = TransformerFactory.newInstance();
  StreamSource xslt = new StreamSource(new File("ACMTrento.xsl"));
  Transformer transformer = factory.newTransformer(xslt);            
  StreamSource text = new StreamSource(new File("ACMTrento.xml"));
  transformer.transform(text, new StreamResult(new FileOutputStream("output.csv")));
} catch (FileNotFoundException | TransformerException ex) {
  System.err.println(ex);
}
```

The program transform the XML file **ACMTrento.xml** according to the XSL file **ACMTrento.xsl** and generates
the CSV file output.csv as output.

However, the program could fail because of the TAG **<!DOCTYPE ALL SYSTEM "ACMTrento.dtd">**, present
in **ACMTrento.xml**. The problem can be solved either creating a file called ACMTrento.dtd (which can be
empty) or simply removing the TAG from **ACMTrento.xml**.

Part of the CSV file **output.csv** looks like this when the file is opened using Microsoft Excel (or equivalent
programs):

![image](https://cloud.githubusercontent.com/assets/24565161/21266936/78bb7e6c-c3a8-11e6-81b5-78c208e816a3.png)
