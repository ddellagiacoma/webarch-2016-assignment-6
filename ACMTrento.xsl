<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output omit-xml-declaration="yes"/>
<xsl:template match="/">Unit Name;Type;Time;
<xsl:for-each select="TAXONOMY/AREA/UNITS/UNIT">
<xsl:value-of select="UNIT_NAME"/>;<xsl:value-of select="@TYPE"/>;<xsl:value-of select="TIME"/>;
</xsl:for-each>
</xsl:template>
</xsl:stylesheet>