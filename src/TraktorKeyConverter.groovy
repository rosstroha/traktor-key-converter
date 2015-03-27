import groovy.xml.MarkupBuilder
import groovy.xml.StreamingMarkupBuilder
class TraktorKeyConverter
{
	public static void main(args)
	{

		println "*************************************************"
		println "*************Written by Ross Troha***************"
		println "*************September 11th 2013*****************"
		println "********linkedin.com/pub/ross-troha/42/39/252****"
		println "*************facebook.com/djtroha****************"
		println "*************soundcloud.com/djtroha**************"
		println "*************************************************"

		println "/////////////////////////////////////////////////"
		println "//////This script converts Traktor's open key////"
		println "//////to the more commonly used camelot scale.///"
		println "/////////////////////////////////////////////////"
		println "//////The converted keys will be stored in///////"
		println "//////the Key Text column in traktor/////////////"
		println "/////////////////////////////////////////////////"
		
		println "This script needs to be in the Traktor root directory or the same location as the collection file!"
		def xmlFile = new File("collection.nml")

        //The keys in a Traktor collection file are represented by an integer 0-23
        def keys = ["08B", "03B", "10B", "05B", "05B", "12B", "07B", "02B",
                    "09B", "04B", "11B", "06B", "01B", "05A", "12A", "07A",
                    "02A", "09A", "11A", "06A", "01A", "08A", "03A", "10A"]

		if(xmlFile.exists())
		{
			println "Collection file found!"
			def unanalyzedEntries = 0
			println "parsing collection"
			def xml = new XmlParser().parse(xmlFile)
			xml.COLLECTION.ENTRY.each {
				if (it.MUSICAL_KEY[0])
                    it.INFO.@KEY = keys[Integer.parseInt(it.MUSICAL_KEY[0].@VALUE)]
				else
                    unanalyzedEntries++
			}
			
			new File("collection_out.nml").withWriter('UTF-8') { out ->
			    out << new StreamingMarkupBuilder().bind { mkp.pi( xml:[ version:'1.0', encoding: 'UTF-8', standalone:'no' ] ) }
			    new XmlNodePrinter(new PrintWriter(out)).print(xml)
			}
			println unanalyzedEntries + " entries need to be analyzed and could not be parsed."
			println "New collection file is named collection_out.nml"
			println "Job done."
			println "Back up your old collection file somewhere, just in case, then rename the new file. Traktor should load it."
		}
		else
			println "No collection file detected."
	}
}